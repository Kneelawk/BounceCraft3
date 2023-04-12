package com.kneelawk.bouncecraft3.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BounceCraft3DataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(BCLootTableGen::new);
        pack.addProvider(BCTagGen::new);
        pack.addProvider(BCBlockModelGen::new);
    }
}
