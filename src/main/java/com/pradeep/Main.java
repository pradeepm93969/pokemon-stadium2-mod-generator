package com.pradeep;


public class Main {

    public static void main(String[] args) throws Exception {

        // 1. Load ROM from resources
        Rom rom = new Rom("/rom/Pokemon Stadium 2 (U)_.z64");

        // 2. Modify ROM in memory
        RomPatcher romPatcher = new RomPatcher();
        romPatcher.patch(rom);

        // 3. Save NEW file (safe)
        rom.saveAs("E:/igi/Project64 3.0/Rom/Pokemon Stadium 2 (U).z64");
    }
}