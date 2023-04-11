package com.kneelawk.bouncecraft3.client.render.part;

import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;

import net.minecraft.client.render.model.BakedModel;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import alexiil.mc.lib.multipart.api.render.PartModelBaker;
import alexiil.mc.lib.multipart.api.render.PartRenderContext;

import com.kneelawk.bouncecraft3.client.render.BCMaterials;
import com.kneelawk.bouncecraft3.client.render.util.RenderUtils;
import com.kneelawk.bouncecraft3.client.render.util.SideQuadTransform;
import com.kneelawk.bouncecraft3.part.BouncePadPart;

import static com.kneelawk.bouncecraft3.Constants.id;

public class BouncePadBaker implements PartModelBaker<BouncePadPart.ModelKey> {
    public static final BouncePadBaker INSTANCE = new BouncePadBaker();

    public static final Identifier[] BOUNCE_PADS = {
        id("block/low_bounce_pad"),
        id("block/medium_bounce_pad"),
        id("block/high_bounce_pad"),
        id("block/extreme_bounce_pad")
    };

    private BouncePadBaker() {
    }

    @Override
    public void emitQuads(BouncePadPart.ModelKey key, PartRenderContext ctx) {
        ctx.pushTransform(new SideQuadTransform(key.side));

        QuadEmitter emitter = ctx.getEmitter();
        BakedModel model = RenderUtils.getModel(BOUNCE_PADS[MathHelper.clamp(key.renderIndex, 0, BOUNCE_PADS.length)]);
        RenderUtils.fromVanilla(model, emitter, BCMaterials.SOLID);

        ctx.popTransform();
    }
}
