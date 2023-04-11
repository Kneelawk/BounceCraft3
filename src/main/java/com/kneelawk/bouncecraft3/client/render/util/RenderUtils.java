package com.kneelawk.bouncecraft3.client.render.util;

import java.util.List;

import net.fabricmc.fabric.api.client.model.BakedModelManagerHelper;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

public class RenderUtils {
    public static Sprite getBlockSprite(Identifier id) {
        return MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).apply(id);
    }

    public static BakedModel getModel(Identifier id) {
        BakedModelManager manager = MinecraftClient.getInstance().getBakedModelManager();
        BakedModel model = BakedModelManagerHelper.getModel(manager, id);
        if (model == null) return manager.getMissingModel();
        return model;
    }

    public static void fromVanilla(BakedModel from, QuadEmitter to, RenderMaterial material) {
        Random random = Random.create(42);

        for (Direction dir : Direction.values()) {
            List<BakedQuad> quads = from.getQuads(null, dir, random);
            for (BakedQuad quad : quads) {
                to.fromVanilla(quad, material, dir);
                to.emit();
            }
        }

        List<BakedQuad> quads = from.getQuads(null, null, random);
        for (BakedQuad quad : quads) {
            to.fromVanilla(quad, material, null);
            to.emit();
        }
    }
}
