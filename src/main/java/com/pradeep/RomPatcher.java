package com.pradeep;

import com.pradeep.model.GymAddressData;
import com.pradeep.utils.GymAddress;
import com.pradeep.utils.TrainerPokemon;

public class RomPatcher {

    private final GymAddress gymAddressR1 = new GymAddress("/R2-trainer-address.json");

    public void patch(Rom rom) {
        skipChecksum(rom);
        Battle6Pokemons(rom);
        ReadPokemons(rom);
    }

    private void ReadPokemons(Rom rom) {
        StringBuilder msg = new StringBuilder("Trainer Pokemon:\n");

        for (GymAddressData.Gym gym : gymAddressR1.gymAddressData.gyms) {

            for (GymAddressData.Trainer trainer : gym.trainers) {

                int address = Integer.decode(trainer.address);

                msg.append("address: [ ")
                        .append(Integer.toHexString(address).toUpperCase())
                        .append(" ] - ");

                int count = 6; // default like C#
                // if last trainer in gym → 3 pokémon (same logic as your j check)
                if (gym.name.equalsIgnoreCase("SecretCave") ) {
                    count = 3;
                }

                while (count-- > 0) {

                    TrainerPokemon poke = new TrainerPokemon();

                    poke.pokemon = rom.readSubArray(address, 0x18, rom.rom);

                    msg.append(poke.getPokemonName()).append(",");

                    address += 0x18;
                }

                msg.append("\n");
            }
        }

        System.out.println(msg);
    }

    private void Battle6Pokemons(Rom rom) {
        rom.write8((byte) 6, 0xE2583);
        rom.write8((byte) 6, 0xFB837);
        rom.write32(0x34020000 | 6, 0xE9E2C);
    }

    private void skipChecksum(Rom rom) {
        // Skip checksum verify
        rom.writeBytes(new byte[24], 0x638);
    }

}