package com.kneelawk.bouncecraft3.part;

import static com.kneelawk.bouncecraft3.Constants.id;

public class BCParts {
    public static BouncePadDefinition[] BOUNCE_PADS = {
        new BouncePadDefinition(id("low_bounce_pad"), 1.0, 0),
        new BouncePadDefinition(id("medium_bounce_pad"), 2.0, 1),
        new BouncePadDefinition(id("high_bounce_pad"), 3.0, 2),
        new BouncePadDefinition(id("extreme_bounce_pad"), 5.0, 3)
    };

    public static GrateDefinition[] GRATES = {
        new GrateDefinition(id("low_speed_grate"), 0.5, 0),
        new GrateDefinition(id("medium_speed_grate"), 2.0, 1),
        new GrateDefinition(id("high_speed_grate"), 3.0, 2),
        new GrateDefinition(id("extreme_speed_grate"), 5.0, 3)
    };

    public static void init() {
        for (BouncePadDefinition definition : BOUNCE_PADS) {
            definition.register();
        }
        for (GrateDefinition definition : GRATES) {
            definition.register();
        }
    }
}
