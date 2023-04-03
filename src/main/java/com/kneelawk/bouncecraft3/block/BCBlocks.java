package com.kneelawk.bouncecraft3.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import com.kneelawk.bouncecraft3.fluid.BCFluids;

import static com.kneelawk.bouncecraft3.Constants.id;
import static net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copyOf;

public class BCBlocks {
    public static Block BOUNCIUM;

    public static Block WHITE_BOUNCIUM_BLOCK;
    public static Block ORANGE_BOUNCIUM_BLOCK;
    public static Block MAGENTA_BOUNCIUM_BLOCK;
    public static Block LIGHT_BLUE_BOUNCIUM_BLOCK;
    public static Block YELLOW_BOUNCIUM_BLOCK;
    public static Block LIME_BOUNCIUM_BLOCK;
    public static Block PINK_BOUNCIUM_BLOCK;
    public static Block GRAY_BOUNCIUM_BLOCK;
    public static Block LIGHT_GRAY_BOUNCIUM_BLOCK;
    public static Block CYAN_BOUNCIUM_BLOCK;
    public static Block PURPLE_BOUNCIUM_BLOCK;
    public static Block BLUE_BOUNCIUM_BLOCK;
    public static Block BROWN_BOUNCIUM_BLOCK;
    public static Block GREEN_BOUNCIUM_BLOCK;
    public static Block RED_BOUNCIUM_BLOCK;
    public static Block BLACK_BOUNCIUM_BLOCK;

    public static void init() {
        BOUNCIUM = register(id("bouncium"),
            new BounciumFluidBlock(BCFluids.BOUNCIUM, copyOf(Blocks.WATER)));

        final FabricBlockSettings bounciumBlocks =
            FabricBlockSettings.of(Material.STONE).requiresTool().strength(1.5f, 6.0f);

        WHITE_BOUNCIUM_BLOCK = register(id("white_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.WHITE)));
        ORANGE_BOUNCIUM_BLOCK = register(id("orange_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.ORANGE)));
        MAGENTA_BOUNCIUM_BLOCK = register(id("magenta_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.MAGENTA)));
        LIGHT_BLUE_BOUNCIUM_BLOCK = register(id("light_blue_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.LIGHT_BLUE)));
        YELLOW_BOUNCIUM_BLOCK = register(id("yellow_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.YELLOW)));
        LIME_BOUNCIUM_BLOCK = register(id("lime_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.LIME)));
        PINK_BOUNCIUM_BLOCK = register(id("pink_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.PINK)));
        GRAY_BOUNCIUM_BLOCK = register(id("gray_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.GRAY)));
        LIGHT_GRAY_BOUNCIUM_BLOCK = register(id("light_gray_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.LIGHT_GRAY)));
        CYAN_BOUNCIUM_BLOCK = register(id("cyan_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.CYAN)));
        PURPLE_BOUNCIUM_BLOCK = register(id("purple_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.PURPLE)));
        BLUE_BOUNCIUM_BLOCK = register(id("blue_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.BLUE)));
        BROWN_BOUNCIUM_BLOCK = register(id("brown_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.BROWN)));
        GREEN_BOUNCIUM_BLOCK = register(id("green_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.GREEN)));
        RED_BOUNCIUM_BLOCK = register(id("red_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.RED)));
        BLACK_BOUNCIUM_BLOCK = register(id("black_bouncium_block"), new Block(copyOf(bounciumBlocks).mapColor(DyeColor.BLACK)));
    }

    private static Block register(Identifier id, Block block) {
        return Registry.register(Registries.BLOCK, id, block);
    }
}
