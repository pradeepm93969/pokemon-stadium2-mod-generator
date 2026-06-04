package com.pradeep.modifier;

import com.pradeep.utils.GymAddress;
import com.pradeep.utils.GymPatcher;

public class SaveFilePatcher {

    private final GymAddress gymAddressR2 = new GymAddress("/R2-trainer-address.json");
    private final GymPatcher gymPatcherR2 = new GymPatcher("/R2-trainer-patcher.json");

    public void patch(Rom rom) {

    }
}
