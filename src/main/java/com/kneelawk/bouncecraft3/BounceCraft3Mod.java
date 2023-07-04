package com.kneelawk.bouncecraft3;

import net.fabricmc.api.ModInitializer;

import com.kneelawk.bouncecraft3.block.BCBlocks;
import com.kneelawk.bouncecraft3.fluid.BCFluids;
import com.kneelawk.bouncecraft3.item.BCItems;
import com.kneelawk.bouncecraft3.misc.BCCreativeTabs;
import com.kneelawk.bouncecraft3.misc.KineticAbsorberLogic;
import com.kneelawk.bouncecraft3.part.BCParts;

public class BounceCraft3Mod implements ModInitializer {
    @Override
    public void onInitialize() {
        BCFluids.init();
        BCItems.init();
        BCBlocks.init();
        BCParts.init();
        BCCreativeTabs.init();
        KineticAbsorberLogic.init();
    }
}
