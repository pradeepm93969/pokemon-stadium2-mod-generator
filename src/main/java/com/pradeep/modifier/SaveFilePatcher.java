package com.pradeep.modifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pradeep.model.SaveFileData;
import com.pradeep.utils.FileLoader;
import com.pradeep.utils.SaveDataPokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SaveFilePatcher {

    private final SaveFileData saveFileData = FileLoader.load(
            "/save-file-patcher.json", SaveFileData.class);

    private final Map<String, Integer> boxAddress = new HashMap<>() {{
        put("FREE_BATTLE", 0x0000);
//        put("LITTLE_CUP",  0x0F00);
//        put("POKE_CUP",    0x1E00);
//        put("PRIME_CUP",   0x2D00);
        put("GYM_CASTLE",  0x4000);
        put("GYM_CASTLE2", 0x4F00);
    }};

    private static final byte[] STARTING_ARRAY = new byte[] {
            (byte) 0x5D, (byte) 0x1A, (byte) 0x00, (byte) 0x01,
            (byte) 0xA3, (byte) 0xA0, (byte) 0xB1, (byte) 0x8F,
            (byte) 0x50, (byte) 0xAF, (byte) 0xA4, (byte) 0xA4,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
    };

    private static final byte[] ENDING_ARRAY = new byte[] {
            (byte) 0x33, (byte) 0x50, (byte) 0x00, (byte) 0x00
    };

    private static final byte[] EMPTY_PARTY_FILLER = new byte[376];


    public void patch(Rom rom) {
        modPokemons(rom, saveFileData);
        readPokemons(rom);
    }

    private void modPokemons(
            Rom rom, SaveFileData saveFileData) {
        Integer address;

        for (SaveFileData.Box box : saveFileData.getBoxes()) {
            address = boxAddress.get(box.getName());
            int offset = 0;
            for (SaveFileData.Party party : box.getParties()) {
                int partyAddress = address + offset;

                rom.writeBytes(STARTING_ARRAY, address + offset);
                offset += STARTING_ARRAY.length;

                for (int i = 0 ; i < 6; i ++) {
                    SaveFileData.Pokemon p = party.getPokemons().get(i);

                    SaveDataPokemon poke = new SaveDataPokemon();
                    poke.setPokemon(rom.readSubArray((address + offset), 0x3C, rom.getRom()));

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
                    rom.writeBytes(poke.getPokemon(), address + offset);

                    // move to next Pokémon slot
                    offset += 60;
                }
                rom.writeBytes(ENDING_ARRAY, (address + offset));
                offset += ENDING_ARRAY.length;

                byte[] endBytes = computeChecksum(rom, partyAddress);
                rom.writeBytes(endBytes, (address + offset));
                offset += 4;
            }

            for (int i = box.getParties().size(); i < 10; i ++) {
                rom.writeBytes(EMPTY_PARTY_FILLER, address + offset);
                offset += EMPTY_PARTY_FILLER.length;
                rom.writeBytes(ENDING_ARRAY, (address + offset));
                offset += ENDING_ARRAY.length;
            }
        }
    }

    private byte[] computeChecksum(Rom rom, Integer address) {
        int sum = 0;
        byte[] romData = rom.getRom();

        // 1. Calculate the checksum over the 380 bytes (0x180 - 4)
        int length = 0x180 - 4;
        for (int i = 0; i < length; i++) {
            sum += (romData[address + i] & 0xFF);
        }

        // 2. Add the constant trailer values manually matching the original logic
        sum += 0x76;
        sum += 0x30;

        // 3. Keep it strictly to a 16-bit unsigned value
        int finalChecksum = sum & 0xFFFF;

        // 4. Return the exact 4 bytes matching the C# WriteInt structure
        return new byte[] {
                (byte) (finalChecksum & 0xFF),        // Lower Checksum Byte (0x0B)
                (byte) ((finalChecksum >> 8) & 0xFF), // Upper Checksum Byte (0xBD)
                0x30,                                 // Lower Trailer Byte
                0x76                                  // Upper Trailer Byte
        };
    }

    private void readPokemons(Rom rom) {

        SaveFileData saveFileData = new SaveFileData();
        saveFileData.setBoxes(new ArrayList<>());

        for (Map.Entry<String, Integer> entry : boxAddress.entrySet()) {

            String boxName = entry.getKey();
            int baseAddress = entry.getValue(); // important: unsigned

            SaveFileData.Box box = new SaveFileData.Box();
            box.setName(boxName);
            box.setParties(new ArrayList<>());
            saveFileData.getBoxes().add(box);

            int offset = 0;
            for (int i = 0; i < 10; i++) {
                if (rom.read8(baseAddress + offset) == (byte) 0x00) {
                    continue;
                }
                // 1. skip STARTING ARRAY
                offset += STARTING_ARRAY.length;

                SaveFileData.Party party = new SaveFileData.Party();
                party.setPokemons(new ArrayList<>());
                box.getParties().add(party);

                for (int j = 0; j < 6; j++) {
                    byte[] raw = rom.readSubArray((baseAddress + offset), 0x3C, rom.getRom());

                    SaveDataPokemon poke = new SaveDataPokemon();
                    poke.setPokemon(raw);
                    System.out.println(
                            (j + 1) + " - " +
                                    IntStream.range(0, raw.length)
                                            .mapToObj(k -> String.format("%02X", raw[k] & 0xFF))
                                            .collect(Collectors.joining(" "))
                    );

                    SaveFileData.Pokemon p = new SaveFileData.Pokemon();
                    party.getPokemons().add(p);

                    p.setName(poke.getPokemonName());
                    p.setItem(poke.getHeldItem().getDisplayName());
                    p.setMove1(poke.getMove1().getName());
                    p.setMove2(poke.getMove2().getName());
                    p.setMove3(poke.getMove3().getName());
                    p.setMove4(poke.getMove4().getName());

                    offset += 60;
                }

                // 3. skip ENDING ARRAY
                offset += ENDING_ARRAY.length + 4;


            }
        }

        // debug output
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(saveFileData));

    }
}
