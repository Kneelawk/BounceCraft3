package com.kneelawk.bouncecraft3;

import net.fabricmc.api.ModInitializer;

import com.kneelawk.bouncecraft3.block.BCBlocks;
import com.kneelawk.bouncecraft3.fluid.BCFluids;
import com.kneelawk.bouncecraft3.item.BCItems;
import com.kneelawk.bouncecraft3.misc.BCCreativeTabs;

public class BounceCraft3Mod implements ModInitializer {
    @Override
    public void onInitialize() {
        BCFluids.init();
        BCItems.init();
        BCBlocks.init();
        BCCreativeTabs.init();
    }
}
