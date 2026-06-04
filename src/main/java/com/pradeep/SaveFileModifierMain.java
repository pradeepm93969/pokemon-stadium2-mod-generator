package com.pradeep;


import com.pradeep.modifier.Rom;
import com.pradeep.modifier.RomPatcher;
import com.pradeep.modifier.SaveFilePatcher;

public class SaveFileModifierMain {

    public static void main(String[] args) throws Exception {

        // 1. Load ROM from resources
        Rom rom = new Rom("/rom/POKEMON STADIUM 2.fla");

        // 2. Modify ROM in memory
        SaveFilePatcher saveFilePatcher = new SaveFilePatcher();
        saveFilePatcher.patch(rom);

        // 3. Save NEW file (safe)
        rom.saveAs("D:/POKEMON STADIUM 2.fla");
    }
}