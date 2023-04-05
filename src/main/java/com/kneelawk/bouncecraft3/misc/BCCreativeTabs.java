package com.kneelawk.bouncecraft3.misc;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

import net.minecraft.item.ItemGroup;

import com.kneelawk.bouncecraft3.block.BCBlocks;
import com.kneelawk.bouncecraft3.item.BCItems;

import static com.kneelawk.bouncecraft3.Constants.id;

public class BCCreativeTabs {
    public static ItemGroup BOUNCE_CRAFT;

    public static void init() {
        BOUNCE_CRAFT = FabricItemGroup.builder(id("bounce_craft")).entries((ctx, entries) -> {
            entries.add(BCItems.BOUNCIUM_BUCKET);

            entries.add(BCBlocks.WHITE_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.ORANGE_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.MAGENTA_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.LIGHT_BLUE_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.YELLOW_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.LIME_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.PINK_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.GRAY_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.LIGHT_GRAY_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.CYAN_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.PURPLE_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.BLUE_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.BROWN_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.GREEN_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.RED_BOUNCIUM_BLOCK);
            entries.add(BCBlocks.BLACK_BOUNCIUM_BLOCK);
        }).build();
    }
}
