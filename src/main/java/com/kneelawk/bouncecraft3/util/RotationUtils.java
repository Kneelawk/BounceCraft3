package com.kneelawk.bouncecraft3.util;

import org.joml.Vector3f;
import org.joml.Vector3fc;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import static net.minecraft.util.math.Direction.DOWN;
import static net.minecraft.util.math.Direction.EAST;
import static net.minecraft.util.math.Direction.NORTH;
import static net.minecraft.util.math.Direction.SOUTH;
import static net.minecraft.util.math.Direction.UP;
import static net.minecraft.util.math.Direction.WEST;

public class RotationUtils {
    private static final Direction[][] ROTATED_DIRECTIONS = {
        {DOWN, UP, NORTH, SOUTH, WEST, EAST},
        {UP, DOWN, SOUTH, NORTH, WEST, EAST},
        {NORTH, SOUTH, UP, DOWN, WEST, EAST},
        {SOUTH, NORTH, UP, DOWN, EAST, WEST},
        {WEST, EAST, UP, DOWN, SOUTH, NORTH},
        {EAST, WEST, UP, DOWN, NORTH, SOUTH}
    };

    /**
     * Rotates the given vector from {@link Direction#DOWN} to the given direction.
     *
     * @param input    the vector to be rotated.
     * @param rotation the destination direction to rotate to.
     * @return the rotated vector.
     */
    public static Vec3d rotate(Vec3d input, Direction rotation) {
        return switch (rotation) {
            case DOWN -> input;
            case UP -> new Vec3d(input.x, 1 - input.y, 1 - input.z);
            case NORTH -> new Vec3d(input.x, 1 - input.z, input.y);
            case SOUTH -> new Vec3d(1 - input.x, 1 - input.z, 1 - input.y);
            case WEST -> new Vec3d(input.y, 1 - input.z, 1 - input.x);
            case EAST -> new Vec3d(1 - input.y, 1 - input.z, input.x);
        };
    }

    /**
     * Rotates the given vector from {@link Direction#DOWN} to the given direction.
     *
     * @param input    the vector to be rotated.
     * @param rotation the destination direction to rotate to.
     * @return the rotated vector.
     */
    public static Vector3f rotate(Vector3fc input, Direction rotation) {
        return switch (rotation) {
            case DOWN -> new Vector3f(input);
            case UP -> new Vector3f(input.x(), 1 - input.y(), 1 - input.z());
            case NORTH -> new Vector3f(input.x(), 1 - input.z(), input.y());
            case SOUTH -> new Vector3f(1 - input.x(), 1 - input.z(), 1 - input.y());
            case WEST -> new Vector3f(input.y(), 1 - input.z(), 1 - input.x());
            case EAST -> new Vector3f(1 - input.y(), 1 - input.z(), input.x());
        };
    }

    /**
     * Rotates the given box from {@link Direction#DOWN} to the given direction.
     *
     * @param input    the box to be rotated.
     * @param rotation the destination direction to rotate to.
     * @return the rotated box.
     */
    public static Box rotate(Box input, Direction rotation) {
        return new Box(rotate(new Vec3d(input.minX, input.minY, input.minZ), rotation),
            rotate(new Vec3d(input.maxX, input.maxY, input.maxZ), rotation));
    }

    /**
     * Rotates the given direction from {@link Direction#DOWN} to the given direction.
     *
     * @param input    the direction to be rotated.
     * @param rotation the destination direction to rotate to.
     * @return the rotated direction.
     */
    public static Direction rotate(Direction input, Direction rotation) {
        return ROTATED_DIRECTIONS[rotation.getId()][input.getId()];
    }
}
