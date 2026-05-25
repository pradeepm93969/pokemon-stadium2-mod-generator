package com.pradeep;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rom {

    private byte[] rom;
    private byte[] backupRom;

    // =========================
    // LOAD FROM RESOURCES
    // =========================
    public Rom(String resourcePath) {
        loadFromResources(resourcePath);
    }

    private void loadFromResources(String resourcePath) {
        try (InputStream is = getClass().getResourceAsStream(resourcePath)) {

            if (is == null) {
                throw new RuntimeException("ROM not found: " + resourcePath);
            }

            this.rom = is.readAllBytes();
            this.backupRom = Arrays.copyOf(rom, rom.length);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load ROM", e);
        }
    }

    public byte[] readSubArray(int address, int size, byte[] arr) {
        // Equivalent to ArraySegment<byte>(arr, address, size).ToArray()

        return Arrays.copyOfRange(arr, address, address + size);
    }

    public int read8(int address) {
        // Convert byte to unsigned int (VERY IMPORTANT in Java)
        return rom[address] & 0xFF;
    }

    // =========================
    // WRITE METHODS (IN MEMORY ONLY)
    // =========================
    public void write8(int value, int address) {
        rom[address] = (byte) value;
    }

    public void write16(int value, int address) {
        rom[address]     = (byte) (value >> 8);
        rom[address + 1] = (byte) value;
    }

    public void write32(int value, int address) {
        rom[address]     = (byte) (value >> 24);
        rom[address + 1] = (byte) (value >> 16);
        rom[address + 2] = (byte) (value >> 8);
        rom[address + 3] = (byte) value;
    }

    public void writeBytes(byte[] data, int address) {
        System.arraycopy(data, 0, rom, address, data.length);
    }

    // =========================
    // SAVE AS NEW FILE (IMPORTANT PART)
    // =========================
    public void saveAs(String outputPath) {
        try {

            // Ensure output directory exists
            Path path = Path.of(outputPath);
            Files.createDirectories(path.getParent());

            // Write NEW FILE (never overwrite resource)
            Files.write(path, rom);

            System.out.println("ROM saved to: " + outputPath);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save ROM", e);
        }
    }

    // =========================
    // DEFAULT OUTPUT GENERATOR
    // =========================
    public void saveDefault() {
        String output = "output/pokemon_stadium_2_mod.z64";
        saveAs(output);
    }

    // =========================
    // BACKUP RESTORE
    // =========================
    public void restoreBackup() {
        rom = Arrays.copyOf(backupRom, backupRom.length);
    }

    // =========================
    // GET RAW ROM
    // =========================
    public byte[] getRaw() {
        return rom;
    }
}