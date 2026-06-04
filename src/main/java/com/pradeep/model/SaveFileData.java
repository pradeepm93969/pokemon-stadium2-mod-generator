package com.pradeep.model;

import lombok.Data;

import java.util.List;

@Data
public class SaveFileData {

    private List<Team> gymLeaderCastle1;
    private List<Team> gymLeaderCastle2;

    @Data
    public static class Team {
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