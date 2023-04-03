package com.kneelawk.bouncecraft3.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import com.kneelawk.bouncecraft3.fluid.BCFluids;

import static com.kneelawk.bouncecraft3.Constants.id;

public class BCBlocks {
    public static Block BOUNCIUM;

    public static void init() {
        BOUNCIUM = Registry.register(Registries.BLOCK, id("bouncium"),
            new BounciumFluidBlock(BCFluids.BOUNCIUM, FabricBlockSettings.copyOf(Blocks.WATER)));
    }
}
