package com.pradeep.utils;

import com.google.gson.Gson;
import com.pradeep.model.GymAddressData;
import com.pradeep.model.GymPokemonData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStreamReader;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GymPatcher {

    private GymPokemonData gymPokemonData;

    public GymPatcher(String file) {
        try {
            Gson gson = new Gson();
            InputStreamReader reader = new InputStreamReader(
                    getClass().getResourceAsStream(file)
            );
            this.gymPokemonData = gson.fromJson(reader, GymPokemonData.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load patcher JSON", e);
        }
    }

}
