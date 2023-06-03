package com.kneelawk.bouncecraft3.datagen;

import java.util.List;

import com.google.gson.JsonElement;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

import com.mojang.serialization.JsonOps;

import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.util.Identifier;

import com.kneelawk.bouncecraft3.BCLog;
import com.kneelawk.bouncecraft3.block.BCBlocks;
import com.kneelawk.kmodlib.client.blockmodel.JsonMaterial;
import com.kneelawk.kmodlib.client.blockmodel.KUnbakedModel;
import com.kneelawk.kmodlib.client.blockmodel.UnbakedLayeredModel;
import com.kneelawk.kmodlib.client.blockmodel.connector.ModelConnector;
import com.kneelawk.kmodlib.client.blockmodel.ct.UnbakedCTLayer;

import static com.kneelawk.bouncecraft3.Constants.id;

public class BCBlockModelGen extends FabricModelProvider {
    private static final Identifier BOUNCIUM_GLASS_NO_EDGES_TEX = id("block/bouncium_glass_no_edges");

    public BCBlockModelGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        for (Block block : BCBlocks.BOUNCIUM_BLOCKS) {
            blockStateModelGenerator.registerSimpleCubeAll(block);
        }

        for (Block glass : BCBlocks.BOUNCIUM_GLASSES) {
            connectedTextureGlass(glass, blockStateModelGenerator);
        }
    }

    private void connectedTextureGlass(Block glass, BlockStateModelGenerator blockStateModelGenerator) {
        Identifier modelId = ModelIds.getBlockModelId(glass);
        Identifier suffixedModelId = modelId.withSuffixedPath(".kml");

        Identifier ecTex = modelId.withSuffixedPath("_exterior_corners");
        Identifier heTex = modelId.withSuffixedPath("_horizontal_edges");
        Identifier icTex = modelId.withSuffixedPath("_interior_corners");
        Identifier veTex = modelId.withSuffixedPath("_vertical_edges");

        blockStateModelGenerator.registerSimpleState(glass);

        UnbakedCTLayer layer =
            new UnbakedCTLayer(ecTex, icTex, heTex, veTex, BOUNCIUM_GLASS_NO_EDGES_TEX, JsonMaterial.DEFAULT, 0.0f,
                true, true, -1, ModelConnector.DEFAULT);
        UnbakedLayeredModel model = new UnbakedLayeredModel(new Identifier("block/block"), ecTex, List.of(layer));
        JsonElement element =
            KUnbakedModel.CODEC.encodeStart(JsonOps.INSTANCE, model).getOrThrow(false, BCLog.log::error);

        blockStateModelGenerator.modelCollector.accept(suffixedModelId, () -> element);

        Models.CUBE_ALL.upload(ModelIds.getItemModelId(glass.asItem()), TextureMap.all(ecTex),
            blockStateModelGenerator.modelCollector);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }
}
