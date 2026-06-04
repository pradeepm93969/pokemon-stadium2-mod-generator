package com.pradeep.utils;

import java.util.Arrays;

public class GBStringEncoding {

    private static final char[] GB_ENCODING = {
            ' ', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
            ' ', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
            ' ', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
            ' ', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
            ' ', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
            ' ', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
            ' ', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
            ' ', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',

            'A', 'B','C','D','E','F','G','H','I','J','K','L','M','N','O','P',
            'Q', 'R','S','T','U','V','W','X','Y','Z','(',')',':',';','[',']',

            'a', 'b','c','d','e','f','g','h','i','j','k','l','m','n','o','p',
            'q', 'r','s','t','u','v','w','x','y','z','é','d','l','s','t','v',

            ' ', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',
            ' ', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',

            ' ', '\'',' ','-','r','m','?','!','.',' ',' ',' ','▷','▶','▼','♂',
            ' ', ' ',' ','.','/',',','♀','1','2','3','4','5','6','7','8','9'
    };

    public static int encodeChar(char c) {
        // Special Case: Real Space character in Gen 1/2 is 0x7F
        if (c == ' ') {
            return 0x7F;
        }

        // Start scanning from 0x80 to skip the empty padding zones at the beginning
        for (int i = 0x80; i < GB_ENCODING.length; i++) {
            if (GB_ENCODING[i] == c) {
                return i;
            }
        }
        return 0x50; // Fallback to terminator if character is unknown
    }

    public static String decodeString(byte[] nameBytes) {
        StringBuilder str = new StringBuilder();
        for (byte b : nameBytes) {
            int c = b & 0xFF;
            if (c == 0x50) break; // Stop immediately when hitting the string terminator

            if (c < GB_ENCODING.length) {
                str.append(GB_ENCODING[c]);
            }
        }
        return str.toString();
    }

    public static byte[] encodeName(String name) {
        byte[] result = new byte[12];

        // 1. Initialize the whole array with Game Boy terminator padding (0x50)
        java.util.Arrays.fill(result, (byte) 0x50);

        // 2. Encode characters into their raw values first
        byte[] rawEncoded = new byte[12];
        java.util.Arrays.fill(rawEncoded, (byte) 0x50);
        for (int i = 0; i < name.length() && i < 12; i++) {
            rawEncoded[i] = (byte) encodeChar(name.charAt(i));
        }

        // 3. Flip each 4-byte block individually to match the WriteInt behavior
        for (int block = 0; block < 3; block++) { // 3 blocks of 4 bytes = 12 bytes
            int base = block * 4;
            result[base + 0] = rawEncoded[base + 3]; // Move 4th char to 1st position
            result[base + 1] = rawEncoded[base + 2]; // Move 3rd char to 2nd position
            result[base + 2] = rawEncoded[base + 1]; // Move 2nd char to 3rd position
            result[base + 3] = rawEncoded[base + 0]; // Move 1st char to 4th position
        }

        return result;
    }
}