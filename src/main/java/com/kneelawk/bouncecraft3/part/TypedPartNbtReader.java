package com.kneelawk.bouncecraft3.part;

import net.minecraft.nbt.NbtCompound;

import alexiil.mc.lib.multipart.api.AbstractPart;
import alexiil.mc.lib.multipart.api.MultipartHolder;
import alexiil.mc.lib.multipart.api.PartDefinition;

/**
 * Like a {@link PartDefinition.IPartNbtReader} but allowing for the use of custom part definitions.
 *
 * @param <D> the type of part definition that this supplies.
 */
@FunctionalInterface
public interface TypedPartNbtReader<D extends PartDefinition> {
    /**
     * Reads a part from NBT.
     * <p>
     * Must not fail. If invalid data is read, fall back to sensible defaults.
     *
     * @param definition the part definition with which this part is registered.
     * @param holder     the holder responsible for holding this part within the multipart block.
     * @param nbt        the NBT compound from which this part is to be read.
     * @return the newly read part.
     */
    AbstractPart readFromNbt(D definition, MultipartHolder holder, NbtCompound nbt);

    /**
     * Creates an adapter {@link PartDefinition.IPartNbtReader} that invokes this when invoked.
     * <p>
     * <b>Only use this when you are sure the supplied definition will actually be the correct type,</b> like in said
     * definition's constructor.
     *
     * @return the adapter.
     */
    @SuppressWarnings("unchecked")
    default PartDefinition.IPartNbtReader adapter() {
        return (definition, holder, nbt) -> readFromNbt((D) definition, holder, nbt);
    }
}
