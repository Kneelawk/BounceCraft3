package com.kneelawk.bouncecraft3.client.render.util;

import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;

import org.joml.Vector3f;

import net.minecraft.util.math.Direction;

import com.kneelawk.bouncecraft3.util.RotationUtils;

public class SideQuadTransform implements RenderContext.QuadTransform {
    public final Direction side;

    public SideQuadTransform(Direction side) {
        this.side = side;
    }

    @Override
    public boolean transform(MutableQuadView quad) {
        for (int i = 0; i < 4; i++) {
            Vector3f vec = new Vector3f(quad.x(i), quad.y(i), quad.z(i));
            vec = RotationUtils.rotate(vec, side);
            quad.pos(i, vec);

            if (quad.hasNormal(i)) {
                Vector3f norm = new Vector3f(quad.normalX(i), quad.normalY(i), quad.normalZ(i));
                norm = RotationUtils.rotate(norm, side);
                quad.normal(i, norm);
            }
        }

        Direction cullFace = quad.cullFace();
        if (cullFace != null) {
            quad.cullFace(RotationUtils.rotate(cullFace, side));
        }

        Direction nominalFace = quad.nominalFace();
        if (nominalFace != null) {
            quad.nominalFace(RotationUtils.rotate(nominalFace, side));
        }

        return true;
    }
}
