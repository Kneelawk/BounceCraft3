package com.kneelawk.bouncecraft3.misc;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import com.kneelawk.bouncecraft3.block.BCBlocks;
import com.kneelawk.bouncecraft3.item.BCItems;

import static com.kneelawk.bouncecraft3.Constants.id;

public class BCCreativeTabs {
    public static ItemGroup BOUNCE_CRAFT;

    public static void init() {
        BOUNCE_CRAFT = FabricItemGroup.builder(id("bounce_craft")).entries((ctx, entries) -> {
            entries.add(BCItems.BOUNCIUM_BUCKET);

            for (Item pad : BCItems.BOUNCE_PADS) {
                entries.add(pad);
            }

            for (Block block : BCBlocks.BOUNCIUM_BLOCKS) {
                entries.add(block);
            }

            for (Block glass : BCBlocks.BOUNCIUM_GLASSES) {
                entries.add(glass);
            }
        }).icon(() -> new ItemStack(BCItems.BOUNCE_PADS[3])).build();
    }
}
