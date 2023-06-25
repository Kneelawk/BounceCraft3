package com.kneelawk.bouncecraft3.util;

import java.util.function.Function;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import alexiil.mc.lib.multipart.api.MultipartContainer;
import alexiil.mc.lib.multipart.api.MultipartUtil;

public class PlacementUtils {
    public static @Nullable MultipartContainer.PartOffer tryPlacePad(ItemUsageContext context,
                                                                     Function<Direction, MultipartContainer.MultipartCreator> creatorFactory,
                                                                     boolean respectEntityBBs) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        Direction side = context.getSide();
        BlockPos offsetPos = pos.offset(side);

        MultipartContainer.PartOffer offer =
            MultipartUtil.offerNewPart(world, pos, creatorFactory.apply(side), respectEntityBBs);
        if (offer != null) return offer;

        return MultipartUtil.offerNewPart(world, offsetPos, creatorFactory.apply(side.getOpposite()), respectEntityBBs);
    }

    public static @Nullable MultipartContainer.PartOffer tryPlaceGrate(ItemUsageContext context,
                                                                       Function<Direction, MultipartContainer.MultipartCreator> creatorFactory,
                                                                       boolean respectEntityBBs) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        Direction side = context.getSide();
        BlockPos offsetPos = pos.offset(side);
        PlayerEntity player = context.getPlayer();
        Vec3d hitPos = context.getHitPos().subtract(Vec3d.of(pos));

        Direction direction;
        if (player != null) {
            direction = Direction.getEntityFacingOrder(player)[0];
        } else {
            direction = context.getHorizontalPlayerFacing();
        }

        double offset = side.getAxis().choose(hitPos.x, hitPos.y, hitPos.z);

        System.out.println("Offset: " + offset);
        if (0.001 < offset && offset < 0.999) {
            MultipartContainer.PartOffer offer =
                MultipartUtil.offerNewPart(world, pos, creatorFactory.apply(direction), respectEntityBBs);
            if (offer != null) return offer;
        }

        return MultipartUtil.offerNewPart(world, offsetPos, creatorFactory.apply(direction), respectEntityBBs);
    }

    public static void finishPlacement(ItemUsageContext context, @Nullable MultipartContainer.PartOffer offer,
                                       BlockState closestBlockState) {
        BlockSoundGroup soundGroup = closestBlockState.getSoundGroup();
        finishPlacement(context, offer, soundGroup.getPlaceSound(), soundGroup.volume, soundGroup.pitch);
    }

    public static void finishPlacement(ItemUsageContext context, @Nullable MultipartContainer.PartOffer offer,
                                       SoundEvent placeSound, float volume, float pitch) {
        if (offer != null) {
            PlayerEntity player = context.getPlayer();
            if (player == null || !player.getAbilities().creativeMode) {
                context.getStack().decrement(1);
            }

            context.getWorld().playSound(
                null,
                context.getBlockPos(),
                placeSound,
                SoundCategory.BLOCKS,
                (volume + 1f) / 2f,
                pitch * 0.8f
            );

            offer.apply();
            offer.getHolder().getPart().onPlacedBy(player, context.getHand());
        }
    }
}
