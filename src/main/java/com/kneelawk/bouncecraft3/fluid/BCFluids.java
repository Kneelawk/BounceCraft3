package com.kneelawk.bouncecraft3.fluid;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static com.kneelawk.bouncecraft3.Constants.id;

public class BCFluids {
    public static FlowableFluid BOUNCIUM;
    public static FlowableFluid BOUNCIUM_FLOWING;

    public static void init() {
        BOUNCIUM = Registry.register(Registries.FLUID, id("bouncium"), new BounciumFluid.Still());
        BOUNCIUM_FLOWING = Registry.register(Registries.FLUID, id("bouncium_flowing"), new BounciumFluid.Flowing());
    }
}
