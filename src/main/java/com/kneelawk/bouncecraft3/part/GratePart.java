package com.kneelawk.bouncecraft3.part;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
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

import com.kneelawk.bouncecraft3.util.BoxUtils;
import com.kneelawk.bouncecraft3.util.MovementUtils;

public class GratePart extends AbstractPart {
    private static final VoxelShape SHAPE = VoxelShapes.cuboid(BoxUtils.px(4, 4, 4, 12, 12, 12));

    public final GrateDefinition definition;

    private Direction dir;

    public GratePart(GrateDefinition definition, MultipartHolder holder, Direction dir) {
        super(definition, holder);
        this.definition = definition;
        this.dir = dir;
    }

    public GratePart(GrateDefinition definition, MultipartHolder holder, NbtCompound nbt) {
        this(definition, holder, Direction.byId(nbt.getByte("dir")));
    }

    public GratePart(GrateDefinition definition, MultipartHolder holder, NetByteBuf buf, IMsgReadCtx ctx) {
        this(definition, holder, Direction.byId(buf.readFixedBits(3)));
    }

    @Override
    public NbtCompound toTag() {
        NbtCompound nbt = super.toTag();
        nbt.putByte("dir", (byte) dir.getId());
        return nbt;
    }

    @Override
    public void writeCreationData(NetByteBuf buffer, IMsgWriteCtx ctx) {
        super.writeCreationData(buffer, ctx);
        buffer.writeFixedBits(dir.getId(), 3);
    }

    @Override
    public void writeRenderData(NetByteBuf buffer, IMsgWriteCtx ctx) {
        super.writeRenderData(buffer, ctx);
        buffer.writeFixedBits(dir.getId(), 3);
    }

    @Override
    public void readRenderData(NetByteBuf buffer, IMsgReadCtx ctx) throws InvalidInputDataException {
        super.readRenderData(buffer, ctx);
        dir = Direction.byId(buffer.readFixedBits(3));
    }

    @Override
    public void onAdded(MultipartEventBus bus) {
        super.onAdded(bus);

        bus.addListener(this, PartEventEntityCollide.class, event -> {
            MovementUtils.setVelocity(event.entity, definition.velocityMagnitude, dir);
        });
    }

    @Override
    public VoxelShape getShape() {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape() {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getOutlineShape() {
        // TODO: make this smaller when there are sided parts in same block
        return VoxelShapes.fullCube();
    }

    @Override
    public VoxelShape getSidesShape() {
        return VoxelShapes.fullCube();
    }

    @Nullable
    @Override
    public PartModelKey getModelKey() {
        return new ModelKey(dir, definition.renderIndex);
    }

    @Override
    protected BlockState getClosestBlockState() {
        return Blocks.STONE.getDefaultState();
    }

    @Override
    public ItemStack getPickStack(@Nullable BlockHitResult hitResult) {
        return definition.pickItem.copy();
    }

    public static class ModelKey extends PartModelKey {
        public final Direction dir;
        public final int renderIndex;

        public ModelKey(Direction dir, int renderIndex) {
            this.dir = dir;
            this.renderIndex = renderIndex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ModelKey modelKey = (ModelKey) o;

            if (renderIndex != modelKey.renderIndex) return false;
            return dir == modelKey.dir;
        }

        @Override
        public int hashCode() {
            int result = dir.hashCode();
            result = 31 * result + renderIndex;
            return result;
        }
    }
}
