package com.kneelawk.bouncecraft3.block;

import java.util.Set;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.enums.Instrument;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.poi.PointOfInterestType;

import com.kneelawk.bouncecraft3.fluid.BCFluids;
import com.kneelawk.bouncecraft3.mixin.impl.PointOfInterestTypesAccessor;

import static com.kneelawk.bouncecraft3.Constants.id;
import static net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copyOf;

public class BCBlocks {
    public static Block BOUNCIUM;

    public static Block[] BOUNCIUM_BLOCKS = new Block[DyeColor.values().length];
    public static Block[] BOUNCIUM_GLASSES = new Block[DyeColor.values().length];

    public static Block BASE_BOUNCIUM_GRATE;

    public static Block KINETIC_ABSORBER;
    public static RegistryKey<PointOfInterestType> KINETIC_ABSORBER_POI =
        RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, id("kinetic_absorber"));
    public static int KINETIC_ABSORBER_RADIUS = 16;

    public static void init() {
        BOUNCIUM = register(id("bouncium"), new BounciumFluidBlock(BCFluids.BOUNCIUM, copyOf(Blocks.WATER)));

        final FabricBlockSettings bounciumBlocks =
            FabricBlockSettings.create().requiresTool().strength(1.5f, 6.0f).instrument(Instrument.BASEDRUM)
                .sounds(BlockSoundGroup.STONE);
        final FabricBlockSettings bounciumGlasses =
            FabricBlockSettings.create().requiresTool().strength(0.3f).instrument(Instrument.SNARE)
                .sounds(BlockSoundGroup.GLASS).nonOpaque().allowsSpawning(BCBlocks::never).solidBlock(BCBlocks::never)
                .suffocates(BCBlocks::never).blockVision(BCBlocks::never);

        for (DyeColor color : DyeColor.values()) {
            BOUNCIUM_BLOCKS[color.getId()] = registerWithItem(id(color.getName() + "_bouncium_block"),
                new Block(copyOf(bounciumBlocks).mapColor(color)));
        }

        for (DyeColor color : DyeColor.values()) {
            BOUNCIUM_GLASSES[color.getId()] = registerWithItem(id(color.getName() + "_bouncium_glass"),
                new GlassBlock(copyOf(bounciumGlasses).mapColor(color)));
        }

        BASE_BOUNCIUM_GRATE = registerWithItem(id("base_bouncium_grate"), new GrateBaseBlock(
            FabricBlockSettings.create().requiresTool().strength(1.5f).instrument(Instrument.BANJO)
                .sounds(BlockSoundGroup.STONE).nonOpaque().allowsSpawning(BCBlocks::never).solidBlock(BCBlocks::never)
                .suffocates(BCBlocks::never).blockVision(BCBlocks::never)));

        KINETIC_ABSORBER = registerWithItem(id("kinetic_absorber"), new Block(
            FabricBlockSettings.create().requiresTool().strength(1.5f, 6.0f).instrument(Instrument.BELL)
                .sounds(BlockSoundGroup.METAL).nonOpaque().allowsSpawning(BCBlocks::never).solidBlock(BCBlocks::never)
                .suffocates(BCBlocks::never).blockVision(BCBlocks::never)));
        PointOfInterestTypesAccessor.callRegister(Registries.POINT_OF_INTEREST_TYPE, KINETIC_ABSORBER_POI,
            Set.of(KINETIC_ABSORBER.getDefaultState()), 0, 1);
    }

    private static Block register(Identifier id, Block block) {
        return Registry.register(Registries.BLOCK, id, block);
    }

    private static Block registerWithItem(Identifier id, Block block) {
        Registry.register(Registries.ITEM, id, new BlockItem(block, new FabricItemSettings()));
        return Registry.register(Registries.BLOCK, id, block);
    }

    private static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return false;
    }

    private static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }
}
