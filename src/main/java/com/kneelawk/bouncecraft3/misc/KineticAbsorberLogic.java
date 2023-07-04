package com.kneelawk.bouncecraft3.misc;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.poi.PointOfInterestStorage;

import com.kneelawk.bouncecraft3.block.BCBlocks;

public class KineticAbsorberLogic {
    public static void init() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (source.isOf(DamageTypes.FALL)) {
                if (entity.getEntityWorld() instanceof ServerWorld world) {
                    BlockPos entityPos = entity.getBlockPos();
                    return world.getPointOfInterestStorage().getInSquare(poi -> poi.matchesKey(BCBlocks.KINETIC_ABSORBER_POI), entityPos, BCBlocks.KINETIC_ABSORBER_RADIUS, PointOfInterestStorage.OccupationStatus.ANY).noneMatch(poi -> {
                        BlockPos poiPos = poi.getPos();
                        if (Math.abs(poiPos.getX() - entityPos.getX()) > BCBlocks.KINETIC_ABSORBER_RADIUS) return false;
                        if (Math.abs(poiPos.getZ() - entityPos.getZ()) > BCBlocks.KINETIC_ABSORBER_RADIUS) return false;
                        return Math.abs(poiPos.getY() - entityPos.getY()) <= BCBlocks.KINETIC_ABSORBER_RADIUS;
                    });
                }
            }

            return true;
        });
    }
}
