package com.pradeep.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    private int address;
    private byte[] pokemon;

    private static final List<String> POKEMON_DB = loadPokemonDb();
    private static final List<String> MOVES_DB = loadMovesDb();
    private static final Map<Integer, String> ITEM_DB = loadItemDb();


    // =========================
    // SETTERS (NEW FEATURE)
    // =========================

    public void setPokemonByName(String name) {
        int index = POKEMON_DB.indexOf(name.toUpperCase());
        if (index == -1) throw new RuntimeException("Unknown Pokémon: " + name);

        pokemon[1] = (byte) index;
    }

    public void setHeldItem(String itemName) {
        int index = getItemIndex(itemName);
        pokemon[2] = (byte) index;
    }

    public void setMove1(String move) {
        pokemon[4] = (byte) getMoveIndex(move);
    }

    public void setMove2(String move) {
        pokemon[5] = (byte) getMoveIndex(move);
    }

    public void setMove3(String move) {
        pokemon[6] = (byte) getMoveIndex(move);
    }

    public void setMove4(String move) {
        pokemon[7] = (byte) getMoveIndex(move);
    }

    // Fill all stats with FF
    public void maxStats() {
        for (int i = 10; i < pokemon.length - 2 ; i++) {
            pokemon[i] = (byte) 0xFF;
        }
    }

    // =========================
    // HELPERS
    // =========================

    private int getMoveIndex(String move) {
        int idx = MOVES_DB.indexOf(move.toUpperCase());
        if (idx == -1) throw new RuntimeException("Unknown move: " + move);
        return idx;
    }

    private int getItemIndex(String itemName) {
        for (Map.Entry<Integer, String> e : ITEM_DB.entrySet()) {
            if (e.getValue().equalsIgnoreCase(itemName)) {
                return e.getKey();
            }
        }
        throw new RuntimeException("Unknown item: " + itemName);
    }

    public String getPokemonName() {
        int index = pokemon[1] & 0xFF;

        if (index >= 0 && index < POKEMON_DB.size()) {
            return POKEMON_DB.get(index);
        }

        return "UNKNOWN";
    }

    public String getMove1() {
        return getMoveName(4);
    }

    public String getMove2() {
        return getMoveName(5);
    }

    public String getMove3() {
        return getMoveName(6);
    }

    public String getMove4() {
        return getMoveName(7);
    }

    public String getHeldItem() {
        int itemId = pokemon[2] & 0xFF;
        return ITEM_DB.getOrDefault(itemId, "NONE");
    }

    private String getMoveName(int offset) {
        int moveId = pokemon[offset] & 0xFF;

        if (moveId >= 0 && moveId < MOVES_DB.size()) {
            return MOVES_DB.get(moveId);
        }

        return "UNKNOWN";
    }

    private static List<String> loadPokemonDb() {
        try {
            InputStream inputStream = Pokemon.class
                    .getClassLoader()
                    .getResourceAsStream("pokemon-db.json");

            InputStreamReader reader = new InputStreamReader(
                    inputStream,
                    StandardCharsets.UTF_8
            );

            Type type = new TypeToken<List<String>>() {}.getType();

            return new Gson().fromJson(reader, type);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load pokemon-db.json", e);
        }
    }

    private static List<String> loadMovesDb() {
        try {
            InputStream inputStream = Pokemon.class
                    .getClassLoader()
                    .getResourceAsStream("pokemon-moves-db.json");

            InputStreamReader reader = new InputStreamReader(
                    inputStream,
                    StandardCharsets.UTF_8
            );

            Type type = new TypeToken<List<String>>() {}.getType();

            return new Gson().fromJson(reader, type);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load pokemon-moves-db.json", e);
        }
    }

    private static Map<Integer, String> loadItemDb() {
        try {
            InputStream inputStream = Pokemon.class
                    .getClassLoader()
                    .getResourceAsStream("item-db.json");

            InputStreamReader reader = new InputStreamReader(
                    inputStream,
                    StandardCharsets.UTF_8
            );

            Type type = new TypeToken<List<Item>>() {}.getType();

            List<Item> items = new Gson().fromJson(reader, type);

            Map<Integer, String> map = new HashMap<>();

            for (Item item : items) {
                int value = Integer.decode(item.hex);
                map.put(value, item.name);
            }

            return map;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load item-db.json", e);
        }
    }

    public static class Item {
        public String name;
        public String hex;
    }
}