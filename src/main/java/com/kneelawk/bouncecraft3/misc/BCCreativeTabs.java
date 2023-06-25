package com.kneelawk.bouncecraft3.misc;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import com.kneelawk.bouncecraft3.block.BCBlocks;
import com.kneelawk.bouncecraft3.item.BCItems;

import static com.kneelawk.bouncecraft3.Constants.id;
import static com.kneelawk.bouncecraft3.Constants.tt;

public class BCCreativeTabs {
    public static ItemGroup BOUNCE_CRAFT = FabricItemGroup.builder().entries((ctx, entries) -> {
        entries.add(BCItems.BOUNCIUM_BUCKET);

        for (Item pad : BCItems.BOUNCE_PADS) {
            entries.add(pad);
        }

        entries.add(BCBlocks.BASE_BOUNCIUM_GRATE);

        for (Item grate : BCItems.GRATES) {
            entries.add(grate);
        }

        for (Block block : BCBlocks.BOUNCIUM_BLOCKS) {
            entries.add(block);
        }

        for (Block glass : BCBlocks.BOUNCIUM_GLASSES) {
            entries.add(glass);
        }
    }).displayName(tt("itemGroup", "bounce_craft")).icon(() -> new ItemStack(BCItems.BOUNCE_PADS[3])).build();

    public static void init() {
        Registry.register(Registries.ITEM_GROUP, id("bounce_craft"), BOUNCE_CRAFT);
    }
}
