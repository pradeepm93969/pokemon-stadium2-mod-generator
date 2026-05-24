package com.pradeep.utils;

import com.google.gson.Gson;
import com.pradeep.model.GymAddressData;

import java.io.InputStreamReader;

public class GymAddress {

    public GymAddressData gymAddressData;

    public GymAddress(String file) {
        try {
            Gson gson = new Gson();
            InputStreamReader reader = new InputStreamReader(
                    getClass().getResourceAsStream(file)
            );
            this.gymAddressData = gson.fromJson(reader, GymAddressData.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load trainer JSON", e);
        }
    }

}
