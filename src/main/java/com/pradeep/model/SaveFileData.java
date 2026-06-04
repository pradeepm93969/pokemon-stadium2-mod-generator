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
    }
}