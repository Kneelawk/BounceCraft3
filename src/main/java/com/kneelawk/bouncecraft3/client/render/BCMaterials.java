package com.kneelawk.bouncecraft3.client.render;

import java.util.Objects;

import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;

public class BCMaterials {
    public static final RenderMaterial SOLID =
        Objects.requireNonNull(RendererAccess.INSTANCE.getRenderer(), "No RendererAccess").materialFinder()
            .blendMode(0, BlendMode.SOLID).find();
}
