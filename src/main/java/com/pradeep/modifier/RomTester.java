package com.pradeep.modifier;

import com.pradeep.utils.Pokemon;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RomTester {

    public void test(Rom rom) {
        testingRom(rom);
    }

    private void testingRom(Rom rom) {

        //Moves Names
        readData(rom, 0x1D80DA0, 12, 252);

        //Moves stats
        //readData(rom, 0x98430, 6, 252);

        //Pokemon stats
        //readData(rom, 0x98F20, 22, 250);
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