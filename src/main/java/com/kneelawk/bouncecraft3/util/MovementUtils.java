package com.kneelawk.bouncecraft3.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class MovementUtils {
    public static void setVelocity(Entity entity, double magnitude, Direction direction) {
        Vec3d velocity = entity.getVelocity();
        switch (direction) {
            case DOWN -> entity.setVelocity(velocity.x, -magnitude, velocity.z);
            case UP -> entity.setVelocity(velocity.x, magnitude, velocity.z);
            case NORTH -> entity.setVelocity(velocity.x, velocity.y, -magnitude);
            case SOUTH -> entity.setVelocity(velocity.x, velocity.y, magnitude);
            case WEST -> entity.setVelocity(-magnitude, velocity.y, velocity.z);
            case EAST -> entity.setVelocity(magnitude, velocity.y, velocity.z);
        }
    }
}
