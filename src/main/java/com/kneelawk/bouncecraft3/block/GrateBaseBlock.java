package com.kneelawk.bouncecraft3.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import com.kneelawk.bouncecraft3.mixin.impl.LivingEntityAccessor;

public class GrateBaseBlock extends Block {
    public GrateBaseBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.fallDistance = 0f;
        Vec3d vel = entity.getVelocity();

        if (entity.isSneaking()) {
            entity.setVelocity(vel.x, -0.06f, vel.z);
        } else if (entity.getPos().y > pos.getY() + 2.5f && world.getBlockState(pos.up()).isAir()) {
            entity.setVelocity(vel.x, 0.1f, vel.z);
        } else if (entity instanceof LivingEntity living && ((LivingEntityAccessor) living).isJumping()) {
            entity.setVelocity(vel.x, 0.8f, vel.z);
        } else {
            entity.setVelocity(vel.x, 0.2f, vel.z);
        }
    }

    @Override
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.fullCube();
    }
}
