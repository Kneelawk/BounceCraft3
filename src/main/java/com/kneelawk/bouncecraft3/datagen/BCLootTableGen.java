package com.kneelawk.bouncecraft3.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import net.minecraft.block.Block;

import com.kneelawk.bouncecraft3.block.BCBlocks;

public class BCLootTableGen extends FabricBlockLootTableProvider {
    protected BCLootTableGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        for (Block block : BCBlocks.BOUNCIUM_BLOCKS) {
            addDrop(block);
        }
        for (Block block : BCBlocks.BOUNCIUM_GLASSES) {
            addDrop(block);
        }

        addDrop(BCBlocks.BASE_BOUNCIUM_GRATE);
        addDrop(BCBlocks.KINETIC_ABSORBER);
    }
}
