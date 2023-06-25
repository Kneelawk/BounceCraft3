package com.kneelawk.bouncecraft3.client.render.part;

import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;

import org.joml.Vector3f;

import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import alexiil.mc.lib.multipart.api.render.PartModelBaker;
import alexiil.mc.lib.multipart.api.render.PartRenderContext;

import com.kneelawk.bouncecraft3.client.render.util.RenderUtils;
import com.kneelawk.bouncecraft3.client.render.util.SideQuadTransform;
import com.kneelawk.bouncecraft3.part.GratePart;

import static com.kneelawk.bouncecraft3.Constants.id;

public class GrateBaker implements PartModelBaker<GratePart.ModelKey> {
    public static final GrateBaker INSTANCE = new GrateBaker();

    public static final Identifier[] SIDES = {
        id("block/low_speed_grate_side"),
        id("block/medium_speed_grate_side"),
        id("block/high_speed_grate_side"),
        id("block/extreme_speed_grate_side")
    };

    public static final Identifier[] FRONTS = {
        id("block/low_speed_grate_front"),
        id("block/medium_speed_grate_front"),
        id("block/high_speed_grate_front"),
        id("block/extreme_speed_grate_front")
    };

    public static final Identifier[] BACKS = {
        id("block/low_speed_grate_back"),
        id("block/medium_speed_grate_back"),
        id("block/high_speed_grate_back"),
        id("block/extreme_speed_grate_back")
    };

    private GrateBaker() {
    }

    @Override
    public void emitQuads(GratePart.ModelKey key, PartRenderContext ctx) {
        ctx.pushTransform(new SideQuadTransform(key.dir));
        QuadEmitter emitter = ctx.getEmitter();

        Sprite sideSprite = RenderUtils.getBlockSprite(SIDES[key.renderIndex]);
        Sprite frontSprite = RenderUtils.getBlockSprite(FRONTS[key.renderIndex]);
        Sprite backSprite = RenderUtils.getBlockSprite(BACKS[key.renderIndex]);

        for (Direction side : Direction.values()) {
            for (int i = 0; i < 4; i++) {
                emitter.pos(i, RenderUtils.facePoses[side.getId()][i]);
                emitter.color(i, -1);
                emitter.uv(i, RenderUtils.faceUVs[i]);
                emitter.normal(i, new Vector3f(side.getOffsetX(), side.getOffsetY(), side.getOffsetZ()));
            }

            emitter.nominalFace(side);

            if (side.getAxis() != Direction.Axis.Y) {
                emitter.spriteBake(sideSprite, QuadEmitter.BAKE_NORMALIZED);
            } else if (side == Direction.DOWN) {
                emitter.spriteBake(frontSprite, MutableQuadView.BAKE_NORMALIZED);
            } else if (side == Direction.UP) {
                emitter.spriteBake(backSprite, QuadEmitter.BAKE_NORMALIZED);
            }

            emitter.emit();
        }

        ctx.popTransform();
    }
}
