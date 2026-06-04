package com.pradeep.modifier;

import com.pradeep.utils.Pokemon;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RomTester {

    public void test(Rom rom) {
        testingSave(rom);
    }

    private void testingSave(Rom rom) {
        byte[] dragonitePattern = new byte[] {
                (byte) 0x95,
        };
        //readDataByBytes(rom, dragonitePattern, 0x00, 0x1FF00);
        readData(rom, 0x4000, 0x40, 6);
    }

    private void testingRom(Rom rom) {
        byte[] dragonitePattern = new byte[] {
                (byte) 0x95, // rr: Pokémon ID (149)
                (byte) 0x5B, // ss: Base HP (91)
                (byte) 0x86, // tt: Base Attack (134)
                (byte) 0x5F, // uu: Base Defense (95)
                (byte) 0x50, // vv: Base Speed (80)
                (byte) 0x64, // ww: Base Special Attack (100)
                (byte) 0x64  // xx: Base Special Defense (100)
        };
        byte[] pattern = new byte[] {
                (byte) 0x04, // rr: Pokémon ID (004)
                (byte) 0x27, // ss: Base HP (39)
                (byte) 0x34, // tt: Base Attack (52)
                (byte) 0x2B, // uu: Base Defense (43)
                (byte) 0x41, // vv: Base Speed (65)
                (byte) 0x3C, // ww: Base Special Attack (60)
                (byte) 0x32  // xx: Base Special Defense (50)
        };
        byte[] dragnair = new byte[] {
                (byte) 0x05, // rr: Pokémon ID (005)
                (byte) 0x3A, // ss: Base HP (58)
                (byte) 0x40, // tt: Base Attack (64)
                (byte) 0x3A, // uu: Base Defense (58)
                (byte) 0x50, // vv: Base Speed (80)
                (byte) 0x50, // ww: Base Special Attack (80)
                (byte) 0x41  // xx: Base Special Defense (65)
        };
        byte[] zapdos = new byte[] {
                (byte) 0xF9, // rr: Pokémon ID (249)
                (byte) 0x6A, // ss: Base HP (106)
                (byte) 0x5A, // tt: Base Attack (90)
                (byte) 0x82, // uu: Base Defense (130)
                (byte) 0x6E, // vv: Base Speed (110)
                (byte) 0x5A, // ww: Base Special Attack (90)
                (byte) 0x9A  // xx: Base Special Defense (154)
        };

        readDataByBytes(rom, dragonitePattern, 0x98F20, 0x35EBFC0);
        readDataByBytes(rom, pattern, 0x98F20, 0x35EBFC0);
        readDataByBytes(rom, dragnair, 0x98F20, 0x35EBFC0);
        readDataByBytes(rom, zapdos, 0x98F20, 0x35EBFC0);
    }

    private void readDataByBytes(Rom rom, byte[] pattern, int startAddress, int endAddress) {
        byte[] romData = rom.getRom();


        for (int i = startAddress; i < endAddress; i++) {
            boolean match = true;
            for (int j = 0; j < pattern.length; j++) {
                if (romData[i + j] != pattern[j]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                readData(rom, i, 256, 1);
                //System.out.printf("Found pattern at: 0x%X%n", i);
                //return; // stop immediately
            }
        }
        System.out.println("Pattern search finished");
    }


    private void readData(Rom rom, int address, int lineBreakerLength, int lines) {

        for (int i = 0; i < lines ; i ++) {
            int currentAddress = address + i * lineBreakerLength;
            byte[] data = rom.readSubArray(currentAddress, lineBreakerLength, rom.getRom());
            System.out.println(
                    (i + 1) + " - " +
                            String.format("0x%X", currentAddress) + " - " +
                            IntStream.range(0, data.length)
                                    .mapToObj(j -> String.format("%02X", data[j] & 0xFF))
                                    .collect(Collectors.joining(" "))
            );
        }
    }

}