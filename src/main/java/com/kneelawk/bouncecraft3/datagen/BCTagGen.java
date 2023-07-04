package com.kneelawk.bouncecraft3.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import com.kneelawk.bouncecraft3.block.BCBlocks;

public class BCTagGen extends FabricTagProvider.BlockTagProvider {
    public BCTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(BCBlocks.BOUNCIUM_BLOCKS).add(BCBlocks.BOUNCIUM_GLASSES)
            .add(BCBlocks.BASE_BOUNCIUM_GRATE, BCBlocks.KINETIC_ABSORBER);
    }
}
