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

    private final GymAddressData gymAddressData = FileLoader.load(
            "/R2-trainer-address.json", GymAddressData.class);
    private final GymPokemonData gymPokemonData = FileLoader.load(
            "/R2-trainer-patcher.json", GymPokemonData.class);

    public void patch(Rom rom) {
        skipChecksum(rom);
        battle6Pokemons(rom);

        modifyDragonite(rom, 0x23E9922);
        modifyDragonite(rom, 0x23EC81D);

        modPokemons(rom, gymAddressData, gymPokemonData);
        readPokemons(rom, gymAddressData);
    }

    private void modifyDragonite(Rom rom, int address) {
        byte[] data = {
                (byte) 0x95, // rr: Pokémon ID (149)
                (byte) 0x5B, // ss: Base HP (91)
                (byte) 0x86, // tt: Base Attack (134)
                (byte) 0xC8, // uu: Base Defense (95)
                (byte) 0xC8, // vv: Base Speed (80)
                (byte) 0x64, // ww: Base Special Attack (100)
                (byte) 0xC8, // xx: Base Special Defense (100)
                (byte) 0x1A, // type1
                (byte) 0x15, // type2
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

        for (int k = 0; k < 11; k++) {
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