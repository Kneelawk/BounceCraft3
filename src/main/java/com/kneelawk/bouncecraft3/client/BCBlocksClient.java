package com.kneelawk.bouncecraft3.client;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;

import net.minecraft.client.render.RenderLayer;

import com.kneelawk.bouncecraft3.block.BCBlocks;
import com.kneelawk.bouncecraft3.fluid.BCFluids;

import static com.kneelawk.bouncecraft3.Constants.id;

public class BCBlocksClient {
    public static void init() {
        FluidRenderHandlerRegistry.INSTANCE.register(BCFluids.BOUNCIUM, BCFluids.BOUNCIUM_FLOWING,
            new SimpleFluidRenderHandler(id("block/bouncium_still"), id("block/bouncium_flowing")));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), BCFluids.BOUNCIUM,
            BCFluids.BOUNCIUM_FLOWING);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), BCBlocks.BOUNCIUM_GLASSES);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), BCBlocks.BASE_BOUNCIUM_GRATE);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), BCBlocks.KINETIC_ABSORBER);
    }
}
