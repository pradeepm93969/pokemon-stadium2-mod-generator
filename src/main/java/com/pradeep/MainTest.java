package com.pradeep;

import com.pradeep.modifier.Rom;
import com.pradeep.modifier.RomTester;

public class MainTest {

    public static void main(String[] args) throws Exception {

        // 1. Load ROM from resources
        //Rom rom = new Rom("/rom/Pokemon Stadium 2 (U)_.z64");
        Rom rom = new Rom("/rom/POKEMON STADIUM 2.fla");

        // 2. Modify ROM in memory
        RomTester romTester = new RomTester();
        romTester.test(rom);
    }
}
