package com.kneelawk.bouncecraft3.client.render.part;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;

import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import alexiil.mc.lib.multipart.api.render.PartStaticModelRegisterEvent;

import com.kneelawk.bouncecraft3.part.BouncePadPart;
import com.kneelawk.bouncecraft3.part.GratePart;

public class BCPartsClient {
    public static void init() {
        PartStaticModelRegisterEvent.EVENT.register(BCPartsClient::registerStatic);
        ModelLoadingRegistry.INSTANCE.registerModelProvider(BCPartsClient::provideExtraModels);
    }

    private static void registerStatic(PartStaticModelRegisterEvent.StaticModelRenderer renderer) {
        renderer.register(BouncePadPart.ModelKey.class, BouncePadBaker.INSTANCE);
        renderer.register(GratePart.ModelKey.class, GrateBaker.INSTANCE);
    }

    private static void provideExtraModels(ResourceManager manager, Consumer<Identifier> out) {
        for (Identifier id : BouncePadBaker.BOUNCE_PADS) {
            out.accept(id);
        }
    }
}
