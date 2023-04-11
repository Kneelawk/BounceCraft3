package com.kneelawk.bouncecraft3.part;

import java.util.Objects;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import alexiil.mc.lib.net.IMsgReadCtx;
import alexiil.mc.lib.net.IMsgWriteCtx;
import alexiil.mc.lib.net.InvalidInputDataException;
import alexiil.mc.lib.net.NetByteBuf;

import alexiil.mc.lib.multipart.api.AbstractPart;
import alexiil.mc.lib.multipart.api.MultipartEventBus;
import alexiil.mc.lib.multipart.api.MultipartHolder;
import alexiil.mc.lib.multipart.api.event.PartEventEntityCollide;
import alexiil.mc.lib.multipart.api.render.PartModelKey;

import com.kneelawk.bouncecraft3.client.render.part.BouncePadBaker;
import com.kneelawk.bouncecraft3.util.BoxUtils;
import com.kneelawk.bouncecraft3.util.MovementUtils;
import com.kneelawk.bouncecraft3.util.RotationUtils;

public class BouncePadPart extends AbstractPart {
    private static final VoxelShape[] CONFLICT_SHAPES = new VoxelShape[Direction.values().length];
    private static final VoxelShape[] OUTLINE_SHAPES = new VoxelShape[Direction.values().length];

    static {
        for (Direction dir : Direction.values()) {
            CONFLICT_SHAPES[dir.ordinal()] =
                VoxelShapes.cuboid(RotationUtils.rotate(BoxUtils.fromHeightDiameterPx(1, 12), dir));
            OUTLINE_SHAPES[dir.ordinal()] = VoxelShapes.cuboid(RotationUtils.rotate(BoxUtils.fromHeightPx(1), dir));
        }
    }

    public final BouncePadDefinition definition;

    private Direction side;

    public BouncePadPart(BouncePadDefinition definition, MultipartHolder holder, Direction side) {
        super(definition, holder);
        this.definition = definition;
        this.side = side;
    }

    public BouncePadPart(BouncePadDefinition definition, MultipartHolder holder, NbtCompound nbt) {
        this(definition, holder, Direction.byId(nbt.getByte("side")));
    }

    public BouncePadPart(BouncePadDefinition definition, MultipartHolder holder, NetByteBuf buf, IMsgReadCtx ctx) {
        this(definition, holder, Direction.byId(buf.readFixedBits(3)));
    }

    @Override
    public NbtCompound toTag() {
        NbtCompound nbt = super.toTag();
        nbt.putByte("side", (byte) side.getId());
        return nbt;
    }

    @Override
    public void writeCreationData(NetByteBuf buffer, IMsgWriteCtx ctx) {
        super.writeCreationData(buffer, ctx);
        buffer.writeFixedBits(side.getId(), 3);
    }

    @Override
    public void writeRenderData(NetByteBuf buffer, IMsgWriteCtx ctx) {
        super.writeRenderData(buffer, ctx);
        buffer.writeFixedBits(side.getId(), 3);
    }

    @Override
    public void readRenderData(NetByteBuf buffer, IMsgReadCtx ctx) throws InvalidInputDataException {
        super.readRenderData(buffer, ctx);
        side = Direction.byId(buffer.readFixedBits(3));
    }

    @Override
    public void onAdded(MultipartEventBus bus) {
        super.onAdded(bus);

        bus.addListener(this, PartEventEntityCollide.class, event -> {
            MovementUtils.setVelocity(event.entity, definition.velocityMagnitude, side.getOpposite());
        });
    }

    @Override
    public VoxelShape getShape() {
        return CONFLICT_SHAPES[side.ordinal()];
    }

    @Override
    public VoxelShape getCollisionShape() {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getOutlineShape() {
        return OUTLINE_SHAPES[side.ordinal()];
    }

    @Override
    public VoxelShape getSidesShape() {
        return OUTLINE_SHAPES[side.ordinal()];
    }

    @Nullable
    @Override
    public PartModelKey getModelKey() {
        return new ModelKey(side, definition.renderIndex);
    }

    @Override
    protected BlockState getClosestBlockState() {
        return Blocks.STONE.getDefaultState();
    }

    @Override
    public ItemStack getPickStack(@Nullable BlockHitResult hitResult) {
        return definition.pickItem.copy();
    }

    @Environment(EnvType.CLIENT)
    @Override
    protected void spawnBreakParticles() {
        spawnBreakParticles(getClosestBlockState(),
            BouncePadBaker.BOUNCE_PADS[MathHelper.clamp(definition.renderIndex, 0, BouncePadBaker.BOUNCE_PADS.length)]);
    }

    public static final class ModelKey extends PartModelKey {
        public final Direction side;
        public final int renderIndex;

        public ModelKey(Direction side, int renderIndex) {
            this.side = side;
            this.renderIndex = renderIndex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ModelKey modelKey = (ModelKey) o;
            return renderIndex == modelKey.renderIndex && side == modelKey.side;
        }

        @Override
        public int hashCode() {
            return Objects.hash(side, renderIndex);
        }
    }
}
