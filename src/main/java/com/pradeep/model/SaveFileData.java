package com.pradeep.model;

import lombok.Data;

import java.util.List;

@Data
public class SaveFileData {

    private List<Box> boxes;

    @Data
    public static class Box {
        private String name;
        private List<Party> parties;
    }

    @Data
    public static class Party {
        private List<Pokemon> pokemons;
    }

    @Data
    public static class Pokemon {
        private String name;
        private String item;
        private String move1;
        private String move2;
        private String move3;
        private String move4;
        private String hiddenPowerType;
    }

    public Box getBox(String boxname) {
        Box boxOutput = null;
        for (Box box : boxes) {
            if (box.getName().equalsIgnoreCase(boxname)) {
                boxOutput = box;
            }
        }
        if (boxOutput == null) {
            boxname = "MY_TEAM";
            for (Box box : boxes) {
                if (box.getName().equalsIgnoreCase(boxname)) {
                    boxOutput = box;
                }
            }
        }
        return boxOutput;
    }
}
