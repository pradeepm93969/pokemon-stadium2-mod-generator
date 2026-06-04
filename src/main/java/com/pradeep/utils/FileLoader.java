package com.pradeep.utils;

import com.google.gson.Gson;
import lombok.Getter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Getter
public class FileLoader {

    private static final Gson GSON = new Gson();

    private final Object data;

    private FileLoader(Object data) {
        this.data = data;
    }

    public static <T> T load(String filePath, Class<T> clazz) {
        try (InputStream stream = FileLoader.class.getResourceAsStream(filePath)) {

            if (stream == null) {
                throw new RuntimeException("File not found: " + filePath);
            }

            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            return GSON.fromJson(reader, clazz);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load JSON: " + filePath, e);
        }
    }

    // Convenience factory methods (optional but clean)
    public static FileLoader gymAddress(String file) {
        return new FileLoader(load(file, com.pradeep.model.GymAddressData.class));
    }

    public static FileLoader gymPokemon(String file) {
        return new FileLoader(load(file, com.pradeep.model.GymPokemonData.class));
    }

    public static FileLoader saveFile(String file) {
        return new FileLoader(load(file, com.pradeep.model.SaveFileData.class));
    }

    @SuppressWarnings("unchecked")
    public <T> T getData(Class<T> clazz) {
        return (T) data;
    }
}