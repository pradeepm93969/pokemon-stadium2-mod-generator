package com.pradeep.model;

import java.util.List;

public class GymAddressData {
    public List<Gym> gyms;

    public static class Gym {
        public String name;
        public List<Trainer> trainers;
    }

    public static class Trainer {
        public String name;
        public String address;
    }
}
