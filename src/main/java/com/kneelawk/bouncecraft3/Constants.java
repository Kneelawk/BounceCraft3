package com.kneelawk.bouncecraft3;

import net.minecraft.util.Identifier;

public class Constants {
    public static final String MOD_ID = "bouncecraft3";

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}
