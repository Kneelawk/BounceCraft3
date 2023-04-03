package com.kneelawk.bouncecraft3.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import com.kneelawk.bouncecraft3.mixin.impl.LivingEntityAccessor;

public class BounciumFluidBlock extends FluidBlock {
    public BounciumFluidBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.fallDistance = 0;
        if (entity instanceof LivingEntity living) {
            if (!living.hasStatusEffect(StatusEffects.SPEED)) {
                living.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 2));
            }
            if (!living.hasStatusEffect(StatusEffects.JUMP_BOOST)) {
                living.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 2));
            }
            if (((LivingEntityAccessor) living).isJumping()) {
                Vec3d vel = living.getVelocity();
                living.setVelocity(vel.x, 0.8, vel.z);
            }
        }
    }
}
