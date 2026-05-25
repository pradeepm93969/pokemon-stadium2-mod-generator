package com.pradeep.model;

import com.pradeep.utils.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GymPokemonData {
    private List<Gym> gyms;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Gym {
        private String name;
        private List<Trainer> trainers;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Trainer {
        private String name;
        private List<TrainerPokemon> pokemon = new ArrayList<>();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TrainerPokemon {
        private String name;
        private String item;
        private String move1;
        private String move2;
        private String move3;
        private String move4;
        private String rawData;
    }
}
