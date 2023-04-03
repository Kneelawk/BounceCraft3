package com.kneelawk.bouncecraft3.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;

import net.minecraft.client.render.RenderLayer;

import com.kneelawk.bouncecraft3.fluid.BCFluids;

import static com.kneelawk.bouncecraft3.Constants.id;

public class BounceCraft3ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(BCFluids.BOUNCIUM, BCFluids.BOUNCIUM_FLOWING,
            new SimpleFluidRenderHandler(id("block/bouncium_still"), id("block/bouncium_moving")));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), BCFluids.BOUNCIUM,
            BCFluids.BOUNCIUM_FLOWING);
    }
}
