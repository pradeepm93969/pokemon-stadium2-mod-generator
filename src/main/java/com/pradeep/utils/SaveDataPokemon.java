package com.pradeep.utils;

import com.pradeep.data.ItemsEnum;
import com.pradeep.data.HiddenPowerEnum;
import com.pradeep.data.MovesEnum;
import com.pradeep.data.PokemonsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveDataPokemon {

    private int address;
    private byte[] pokemon;

    // =========================
    // SETTERS (NEW FEATURE)
    // =========================

    public void setPokemonByName(String name) {
        PokemonsEnum pokemonEnum = PokemonsEnum.valueOf(name.toUpperCase());
        pokemon[3] = (byte) pokemonEnum.getId();
    }

    public void setHeldItem(String itemName) {
        ItemsEnum itemsEnum = ItemsEnum.valueOf(itemName.toUpperCase());
        pokemon[2] = itemsEnum.getHex();
    }

    public void setMove1(String move) {
        pokemon[1] = (byte) getMoveIndex(move);
    }

    public void setMove2(String move) {
        pokemon[0] = (byte) getMoveIndex(move);
    }

    public void setMove3(String move) {
        pokemon[7] = (byte) getMoveIndex(move);
    }

    public void setMove4(String move) {
        pokemon[6] = (byte) getMoveIndex(move);
    }

    /**
     * Sets the Gen II Attack and Defense DVs used to determine Hidden Power's type.
     * Byte 22 stores Attack in its high nibble and Defense in its low nibble.
     */
    public void setHiddenPowerType(String type) {
        HiddenPowerEnum hiddenPowerType;
        try {
            hiddenPowerType = HiddenPowerEnum.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Unknown Hidden Power type: " + type, exception);
        }

        pokemon[21] = (byte) ((hiddenPowerType.getAttackDV() << 4)
                | hiddenPowerType.getDefenseDV());
    }

    private int getMoveIndex(String move) {
        MovesEnum moveEnum = MovesEnum.valueOf(move.toUpperCase());
        return moveEnum.getId();
    }

    // Fill all stats with FF
    public void maxStats() {

        // trainer ID
        byte[] trainerId = { (byte) 0x5D, (byte) 0x1A };
        System.arraycopy(trainerId, 0, pokemon, 4, 2);

        // EXP
        int expValue = calculateExp(100, getPokemonEnum().getGrowthRate());
        pokemon[8] = (byte) (expValue & 0xFF);
        pokemon[9] = (byte) ((expValue >> 8) & 0xFF);
        pokemon[10] = (byte) ((expValue >> 16) & 0xFF);
        pokemon[11] = (byte) ((expValue >> 24) & 0xFF);

        // DV and IV
        byte[] maxDvIv = new byte[12];
        Arrays.fill(maxDvIv, (byte) 0xFF);
        System.arraycopy(maxDvIv, 0, pokemon, 12, 12);

        // PP and PPUps
        pokemon[24] = getPPValue(getMove4());
        pokemon[25] = getPPValue(getMove3());
        pokemon[26] = getPPValue(getMove2());
        pokemon[27] = getPPValue(getMove1());

        byte[] statsBlock = {
                (byte) 0x00, (byte) 0x04, (byte) 0x64, (byte) 0xFF
        };
        System.arraycopy(statsBlock, 0, pokemon, 28, 4);

        byte[] zeros = { 0, 0, 0, 0 };
        System.arraycopy(zeros, 0, pokemon, 32, 4);

        // Pokémon name (36 - 47)
        byte[] encodedName = GBStringEncoding.encodeName(getPokemonName());
        System.arraycopy(encodedName, 0, pokemon, 36, 12);

        // Trainer name
        byte[] trainerName = {
                (byte) 0xA3, (byte) 0xA0, (byte) 0xB1, (byte) 0x8F,
                (byte) 0x50, (byte) 0xAF, (byte) 0xA4, (byte) 0xA4,
                (byte) 0x00, (byte) 0x50, (byte) 0x50, (byte) 0x50
        };
        System.arraycopy(trainerName, 0, pokemon, 48, 12);
    }

    // =========================
    // HELPERS
    // =========================

    public PokemonsEnum getPokemonEnum() {
        PokemonsEnum pokemonsEnum = PokemonsEnum.fromId(pokemon[3] & 0xFF);
        return pokemonsEnum != null ? pokemonsEnum : PokemonsEnum.UNKNOWN;
    }

    public String getPokemonName() {
        PokemonsEnum pokemonsEnum = PokemonsEnum.fromId(pokemon[3] & 0xFF);
        return pokemonsEnum != null ? pokemonsEnum.getName() : "UNKNOWN";
    }

    public MovesEnum getMove1() {
        return getMoveEnum(1);
    }

    public MovesEnum getMove2() {
        return getMoveEnum(0);
    }

    public MovesEnum getMove3() {
        return getMoveEnum(7);
    }

    public MovesEnum getMove4() {
        return getMoveEnum(6);
    }

    public ItemsEnum getHeldItem() {
        ItemsEnum itemsEnum = ItemsEnum.fromHex(pokemon[2] & 0xFF);
        return itemsEnum != null ? itemsEnum : ItemsEnum.NONE;
    }

    public HiddenPowerEnum getHiddenPowerType() {
        int attackDV = (pokemon[22] >>> 4) & 0x0F;
        int defenseDV = pokemon[22] & 0x0F;
        return HiddenPowerEnum.fromDvs(attackDV, defenseDV);
    }

    private MovesEnum getMoveEnum(int offset) {
        MovesEnum movesEnum = MovesEnum.fromId(pokemon[offset] & 0xFF);
        return movesEnum != null ? movesEnum : MovesEnum.UNKNOWN;
    }

    public static int calculateExp(int lvl, String rate) {

        return switch (rate) {
            case "Medium_Fast" -> lvl * lvl * lvl;

            case "Fast" -> (4 * lvl * lvl * lvl) / 5;

            case "Slow" -> (5 * lvl * lvl * lvl) / 4;

            case "Medium_Slow" ->
                    (6 * lvl * lvl * lvl) / 5
                            - (15 * lvl * lvl)
                            + (100 * lvl)
                            - 140;
            default -> throw new IllegalStateException("Unexpected value: " + rate);
        };
    }

    public byte getPPValue(MovesEnum movesEnum) {
        // Clamp ppUp between 0 and 3
        int ppUp = 3;

        int basePp = movesEnum.getPp();

        // Official Game Boy Math: Each PP Up adds floor(basePP / 5), capped at 61 max PP
        int bonusPpPerUp = basePp / 5;
        int finalPP = Math.min(61, basePp + (bonusPpPerUp * ppUp));

        // Combine 2-bit PP Up count (bits 6-7) and 6-bit PP value (bits 0-5)
        int combinedValue = ((ppUp & 0x03) << 6) | (finalPP & 0x3F);

        return (byte) combinedValue;
    }

}
