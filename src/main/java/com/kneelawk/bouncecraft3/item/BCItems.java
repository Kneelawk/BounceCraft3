package com.kneelawk.bouncecraft3.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import com.kneelawk.bouncecraft3.fluid.BCFluids;

import static com.kneelawk.bouncecraft3.Constants.id;

public class BCItems {
    public static Item BOUNCIUM_BUCKET;

    public static void init() {
        final Item.Settings bucketSettings = new FabricItemSettings().maxCount(1);

        BOUNCIUM_BUCKET = Registry.register(Registries.ITEM, id("bouncium_bucket"),
            new BucketItem(BCFluids.BOUNCIUM, bucketSettings));
    }
}
