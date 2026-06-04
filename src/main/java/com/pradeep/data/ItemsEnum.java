package com.pradeep.data;

import lombok.Getter;

@Getter
public enum ItemsEnum {

    BRIGHTPOWDER((byte)0x03, "BRIGHTPOWDER"),
    LUCKY_PUNCH((byte)0x1E, "LUCKY_PUNCH"),
    METAL_POWDER((byte)0x23, "METAL_POWDER"),

    QUICK_CLAW((byte)0x49, "QUICK_CLAW"),
    PSNCUREBERRY((byte)0x4A, "PSNCUREBERRY"),
    SOFT_SAND((byte)0x4C, "SOFT_SAND"),
    SHARP_BEAK((byte)0x4D, "SHARP_BEAK"),
    PRZCUREBERRY((byte)0x4E, "PRZCUREBERRY"),
    BURNT_BERRY((byte)0x4F, "BURNT_BERRY"),
    ICE_BERRY((byte)0x50, "ICE_BERRY"),

    POISON_BARB((byte)0x51, "POISON_BARB"),
    KINGS_ROCK((byte)0x52, "KINGS_ROCK"),
    BITTER_BERRY((byte)0x53, "BITTER_BERRY"),
    MINT_BERRY((byte)0x54, "MINT_BERRY"),

    SILVERPOWDER((byte)0x59, "SILVERPOWDER"),

    MYSTIC_WATER((byte)0x60, "MYSTIC_WATER"),
    TWISTEDSPOON((byte)0x61, "TWISTEDSPOON"),
    BLACKBELT((byte)0x63, "BLACKBELT"),

    BLACKGLASSES((byte)0x67, "BLACKGLASSES"),
    PINK_BOW((byte)0x69, "PINK_BOW"),
    STICK((byte)0x6A, "STICK"),

    NEVERMELTICE((byte)0x6B, "NEVERMELTICE"),
    MAGNET((byte)0x6C, "MAGNET"),
    MIRACLEBERRY((byte)0x6D, "MIRACLEBERRY"),

    SPELL_TAG((byte)0x72, "SPELL_TAG"),
    MIRACLE_SEED((byte)0x75, "MIRACLE_SEED"),
    THICK_CLUB((byte)0x76, "THICK_CLUB"),
    FOCUS_BAND((byte)0x77, "FOCUS_BAND"),

    HARD_STONE((byte)0x7C, "HARD_STONE"),

    CHARCOAL((byte)0x8A, "CHARCOAL"),
    BERRY_JUICE((byte)0x8B, "BERRY_JUICE"),
    SCOPE_LENS((byte)0x8C, "SCOPE_LENS"),

    METAL_COAT((byte)0x8F, "METAL_COAT"),
    DRAGON_FANG((byte)0x90, "DRAGON_FANG"),

    LEFTOVERS((byte)0x92, "LEFTOVERS"),
    MYSTERYBERRY((byte)0x96, "MYSTERYBERRY"),
    DRAGON_SCALE((byte)0x97, "DRAGON_SCALE"),
    BERSERK_GENE((byte)0x98, "BERSERK_GENE"),

    LIGHT_BALL((byte)0xA3, "LIGHT_BALL"),
    POLKADOT_BOW((byte)0xA7, "POLKADOT_BOW"),

    BERRY((byte)0xAA, "BERRY"),
    GOLD_BERRY((byte)0xAB, "GOLD_BERRY"),

    NONE((byte)0xFF, "NONE");

    private final byte hex;
    private final String displayName;

    ItemsEnum(byte hex, String displayName) {
        this.hex = hex;
        this.displayName = displayName;
    }

    private static final ItemsEnum[] VALUES = values();

    public static ItemsEnum fromHex(int hex) {
        for (ItemsEnum i : VALUES) {
            if ((i.hex & 0xFF) == hex) return i;
        }
        return NONE;
    }
}
