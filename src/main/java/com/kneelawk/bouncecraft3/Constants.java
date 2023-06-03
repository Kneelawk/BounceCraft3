package com.kneelawk.bouncecraft3;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Constants {
    public static final String MOD_ID = "bouncecraft3";

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static MutableText tt(String prefix, String suffix, Object... args) {
        return Text.translatable(prefix + "." + MOD_ID + "." + suffix, args);
    }
}
