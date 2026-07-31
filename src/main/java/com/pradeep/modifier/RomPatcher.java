package com.pradeep.modifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pradeep.model.GymAddressData;
import com.pradeep.model.GymPokemonData;
import com.pradeep.utils.FileLoader;
import com.pradeep.utils.Pokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RomPatcher {

    private final GymAddressData gymAddressDataR1 = FileLoader.load(
            "/R1-trainer-address.json", GymAddressData.class);
    private final GymPokemonData gymPokemonDataR1 = FileLoader.load(
            "/R1-trainer-patcher.json", GymPokemonData.class);

    private final GymAddressData gymAddressDataR2 = FileLoader.load(
            "/R2-trainer-address.json", GymAddressData.class);
    private final GymPokemonData gymPokemonDataR2 = FileLoader.load(
            "/R2-trainer-patcher.json", GymPokemonData.class);

    public void patch(Rom rom) {
        skipChecksum(rom);
        battle6Pokemons(rom);

        //modifyOutrage(rom);

        modifyDragonite(rom, 0x99BD9);
        //modifyGengar(rom, 0x9971F);

        modPokemons(rom, gymAddressDataR1, gymPokemonDataR1);

        System.out.println();
        System.out.println();
        modPokemons(rom, gymAddressDataR2, gymPokemonDataR2);

        //readPokemons(rom, gymAddressData);
    }

    private void modifyOutrage(Rom rom) {
        byte[] data = {
                (byte) 0x00, // Effect ID
                (byte) 0x82, // Power
                (byte) 0x1A, // Type
                (byte) 0xFF, // Accuracy
                (byte) 0x0A, // PP
                (byte) 0x00, // Probability
        };
        rom.writeBytes(data, 0x988DA);

        byte[] nameData = {
                (byte) 0x44, // D
                (byte) 0x52, // R
                (byte) 0x41, // A
                (byte) 0x43, // C
                (byte) 0x4F, // O
                (byte) 0x20, // Space
                (byte) 0x4D, // M
                (byte) 0x00  // String terminator
        };

        rom.writeBytes(nameData, 0x1D814FD);
    }

    private void modifyGengar(Rom rom, int address) {
        byte[] data = {
                (byte) 0x3C, // Base HP              (Original: 60 / 0x3C)
                (byte) 0x41, // Base Attack          (Original: 65 / 0x41)
                (byte) 0x82, // Base Defense         (Original: 60 / 0x3C)
                (byte) 0x6E, // Base Speed           (Original: 110 / 0x6E)
                (byte) 0x82, // Base Special Attack  (Original: 130 / 0x82)
                (byte) 0x82, // Base Special Defense (Original: 75 / 0x4B)
                (byte) 0x08, // Type 1 (Ghost)
                (byte) 0x08, // Type 2 (Ghost)
        };
        rom.writeBytes(data, address);
    }

    private void modifyDragonite(Rom rom, int address) {
        byte[] data = {
                (byte) 0xA1, // Base HP              (Original: 91 / 0x5B)
                (byte) 0xA1, // Base Attack          (Original: 134 / 0x86)
                (byte) 0xA1, // Base Defense         (Original: 95 / 0x5F)
                (byte) 0xA1, // Base Speed           (Original: 80 / 0x50)
                (byte) 0xA1, // Base Special Attack  (Original: 100 / 0x64)
                (byte) 0xA1, // Base Special Defense (Original: 100 / 0x64)
                (byte) 0x1A, // Type 1 (Dragon)
                (byte) 0x15, // Type 2 (Water)
        };
        rom.writeBytes(data, address);
    }

    private void modPokemons(
            Rom rom, GymAddressData addressData, GymPokemonData pokemonData) {

        // index address lookup for fast matching
        Map<String, String> trainerAddressMap = new HashMap<>();

        for (GymAddressData.Gym gym : addressData.getGyms()) {
            for (GymAddressData.Trainer t : gym.getTrainers()) {
                trainerAddressMap.put(t.getName(), t.getAddress());
            }
        }

        for (int k = 0; k < pokemonData.getGyms().size(); k++) {
            GymPokemonData.Gym gym = pokemonData.getGyms().get(k);

            for (GymPokemonData.Trainer trainer : gym.getTrainers()) {
                String addrStr = trainerAddressMap.get(trainer.getName());

                if (addrStr == null) {
                    System.out.println("⚠ Missing address for: " + trainer.getName());
                    continue;
                }

                int address = Integer.decode(addrStr);

                for (GymPokemonData.TrainerPokemon p : trainer.getPokemon()) {

                    Pokemon poke = new Pokemon();
                    poke.setPokemon(rom.readSubArray(address, 0x18, rom.getRom()));

                    // =========================
                    // WRITE DATA BACK
                    // =========================

                    poke.setPokemonByName(p.getName());

                    if (p.getItem() != null) {
                        poke.setHeldItem(p.getItem());
                    }

                    if (p.getMove1() != null) poke.setMove1(p.getMove1());
                    if (p.getMove2() != null) poke.setMove2(p.getMove2());
                    if (p.getMove3() != null) poke.setMove3(p.getMove3());
                    if (p.getMove4() != null) poke.setMove4(p.getMove4());

                    // stats FF if needed
                    poke.maxStats();

                    // write back to ROM
                    rom.writeBytes(poke.getPokemon(), address);


//                    System.out.println(IntStream.range(0, poke.getPokemon().length)
//                            .mapToObj(j -> String.format("%02X", poke.getPokemon()[j] & 0xFF))
//                            .collect(Collectors.joining(" ")));

                    // move to next Pokémon slot
                    address += 0x18;
                }
                System.out.println("Finished for trainer : " + trainer.getName() + " (" + gym.getName() + ")");
            }
        }
    }

    private void readPokemons(Rom rom, GymAddressData addressData) {

        List<GymPokemonData.Gym> gymList = new ArrayList<>();
        for (GymAddressData.Gym gym : addressData.getGyms()) {
            List<GymPokemonData.Trainer> trainerList = new ArrayList<>();
            for (GymAddressData.Trainer trainer : gym.getTrainers()) {
                int address = Integer.decode(trainer.getAddress());
                int count = gym.getName().equalsIgnoreCase("SecretCave") ? 3 : 6;

                List<GymPokemonData.TrainerPokemon> pokemonList = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    Pokemon poke = new Pokemon();
                    poke.setPokemon(
                            rom.readSubArray(address, 0x18, rom.getRom())
                    );
                    GymPokemonData.TrainerPokemon tp = new GymPokemonData.TrainerPokemon();

                    tp.setName(poke.getPokemonName());
                    tp.setItem(poke.getHeldItem());

                    tp.setMove1(poke.getMove1());
                    tp.setMove2(poke.getMove2());
                    tp.setMove3(poke.getMove3());
                    tp.setMove4(poke.getMove4());

                    tp.setRawData(IntStream.range(0, poke.getPokemon().length)
                            .mapToObj(j -> String.format("%02X", poke.getPokemon()[j] & 0xFF))
                            .collect(Collectors.joining(" ")));

                    pokemonList.add(tp);
                    address += 0x18;
                }

                GymPokemonData.Trainer t = new GymPokemonData.Trainer();
                t.setName(trainer.getName());
                t.setPokemon(pokemonList);
                trainerList.add(t);
            }

            GymPokemonData.Gym g = new GymPokemonData.Gym();
            g.setName(gym.getName());
            g.setTrainers(trainerList);
            gymList.add(g);
        }

        GymPokemonData data = new GymPokemonData();
        data.setGyms(gymList);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(data));
    }

    private void battle6Pokemons(Rom rom) {
        rom.write8((byte) 6, 0xE2583);
        rom.write8((byte) 6, 0xFB837);
        rom.write32(0x34020000 | 6, 0xE9E2C);
    }

    private void skipChecksum(Rom rom) {
        // Skip checksum verify
        rom.writeBytes(new byte[24], 0x638);
    }

}