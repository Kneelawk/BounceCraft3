package com.kneelawk.bouncecraft3.util;

import net.minecraft.util.math.Box;

public class BoxUtils {
    public static Box px(double x1, double y1, double z1, double x2, double y2, double z2) {
        return new Box(x1 / 16.0, y1 / 16.0, z1 / 16.0, x2 / 16.0, y2 / 16.0, z2 / 16.0);
    }

    public static Box fromHeightPx(double height) {
        return fromHeight(height / 16.0);
    }

    public static Box fromHeight(double height) {
        return new Box(0.0, 0.0, 0.0, 1.0, height, 1.0);
    }

    public static Box fromHeightDiameterPx(double height, double diameter) {
        return fromHeightDiameter(height / 16.0, diameter / 16.0);
    }

    public static Box fromHeightDiameter(double height, double diameter) {
        return new Box(0.5 - diameter / 2, 0.0, 0.5 - diameter / 2, 0.5 + diameter / 2, height, 0.5 + diameter / 2);
    }
}
