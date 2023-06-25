package com.kneelawk.bouncecraft3.part;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import alexiil.mc.lib.net.IMsgReadCtx;
import alexiil.mc.lib.net.InvalidInputDataException;
import alexiil.mc.lib.net.NetByteBuf;

import alexiil.mc.lib.multipart.api.AbstractPart;
import alexiil.mc.lib.multipart.api.MultipartHolder;
import alexiil.mc.lib.multipart.api.PartDefinition;

public class GrateDefinition extends PartDefinition {
    public final double velocityMagnitude;
    public final int renderIndex;

    public ItemStack pickItem;

    protected GrateDefinition(Identifier identifier, double velocityMagnitude, int renderIndex) {
        super(identifier);
        this.velocityMagnitude = velocityMagnitude;
        this.renderIndex = renderIndex;
    }

    @Override
    public AbstractPart readFromNbt(MultipartHolder holder, NbtCompound nbt) {
        return new GratePart(this, holder, nbt);
    }

    @Override
    public AbstractPart loadFromBuffer(MultipartHolder holder, NetByteBuf buffer, IMsgReadCtx ctx) {
        return new GratePart(this, holder, buffer, ctx);
    }
}
