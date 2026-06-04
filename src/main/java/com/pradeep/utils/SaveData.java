package com.pradeep.utils;


import com.google.gson.Gson;
import com.pradeep.model.GymAddressData;
import com.pradeep.model.SaveFileData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStreamReader;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveData {

    private SaveFileData saveFileData;

    public SaveData(String file) {
        try {
            Gson gson = new Gson();
            InputStreamReader reader = new InputStreamReader(
                    getClass().getResourceAsStream(file)
            );
            this.saveFileData = gson.fromJson(reader, SaveFileData.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load trainer JSON", e);
        }
    }

}