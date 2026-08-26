package com.pradeep.data;

import lombok.Getter;

@Getter
public enum HiddenPowerEnum {

    FIGHTING(12, 12), // 0
    FLYING(12, 13),   // 1
    POISON(12, 14),   // 2
    GROUND(12, 15),   // 3

    ROCK(13, 12),     // 4
    BUG(13, 13),      // 5
    GHOST(13, 14),    // 6
    STEEL(13, 15),    // 7

    FIRE(14, 12),     // 8
    WATER(14, 13),    // 9
    GRASS(14, 14),    // 10
    ELECTRIC(14, 15), // 11

    PSYCHIC(15, 12),  // 12
    ICE(15, 13),      // 13
    DRAGON(15, 14),   // 14
    DARK(15, 15);     // 15

    private final int attackDV;
    private final int defenseDV;

    HiddenPowerEnum(int attackDV, int defenseDV) {
        this.attackDV = attackDV;
        this.defenseDV = defenseDV;
    }

    public static HiddenPowerEnum fromDvs(int attackDV, int defenseDV) {
        for (HiddenPowerEnum hiddenPowerType : values()) {
            if (hiddenPowerType.attackDV == attackDV && hiddenPowerType.defenseDV == defenseDV) {
                return hiddenPowerType;
            }
        }
        return null;
    }
}
