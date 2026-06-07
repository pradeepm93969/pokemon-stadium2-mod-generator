package com.pradeep.utils;

import com.pradeep.data.ItemsEnum;
import com.pradeep.data.MovesEnum;
import com.pradeep.data.PokemonsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    private int address;
    private byte[] pokemon;


    // =========================
    // SETTERS (NEW FEATURE)
    // =========================

    public void setPokemonByName(String name) {
        PokemonsEnum pokemonEnum = PokemonsEnum.valueOf(name.toUpperCase());
        pokemon[1] = (byte) pokemonEnum.getId();
    }

    public void setHeldItem(String itemName) {
        ItemsEnum itemsEnum = ItemsEnum.valueOf(itemName.toUpperCase());
        pokemon[2] = itemsEnum.getHex();
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

    private int getMoveIndex(String move) {
        MovesEnum moveEnum = MovesEnum.valueOf(move.toUpperCase());
        return moveEnum.getId();
    }

    //GETTERS

    public String getPokemonName() {
        PokemonsEnum pokemonsEnum = PokemonsEnum.fromId(pokemon[1] & 0xFF);
        return pokemonsEnum != null ? pokemonsEnum.getName() : "UNKNOWN";
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
        ItemsEnum itemsEnum = ItemsEnum.fromHex(pokemon[2] & 0xFF);
        return itemsEnum != null ? itemsEnum.getDisplayName() : "NONE";
    }

    private String getMoveName(int offset) {
        MovesEnum movesEnum = MovesEnum.fromId(pokemon[offset] & 0xFF);
        return movesEnum != null ? movesEnum.getName() : "UNKNOWN";
    }
}