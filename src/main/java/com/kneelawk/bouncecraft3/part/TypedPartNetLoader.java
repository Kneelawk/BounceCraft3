package com.kneelawk.bouncecraft3.part;

import alexiil.mc.lib.net.IMsgReadCtx;
import alexiil.mc.lib.net.InvalidInputDataException;
import alexiil.mc.lib.net.NetByteBuf;

import alexiil.mc.lib.multipart.api.AbstractPart;
import alexiil.mc.lib.multipart.api.MultipartHolder;
import alexiil.mc.lib.multipart.api.PartDefinition;

/**
 * Like a {@link PartDefinition.IPartNetLoader} but allowing for custom part definitions.
 *
 * @param <D> the type of part definition that this supplies.
 */
@FunctionalInterface
public interface TypedPartNetLoader<D extends PartDefinition> {
    /**
     * Decodes a part from a {@link NetByteBuf}.
     * <p>
     * If this fails, an {@link InvalidInputDataException} should be thrown.
     *
     * @param definition the part definition with which this part is registered.
     * @param holder     the holder responsible for holding this part within the multipart block.
     * @param buffer     the buffer to decode this part from.
     * @param ctx        the message context to aid in decoding the part.
     * @return the newly decoded part.
     * @throws InvalidInputDataException if an error occurs while decoding the part.
     */
    AbstractPart loadFromBuffer(D definition, MultipartHolder holder, NetByteBuf buffer, IMsgReadCtx ctx)
        throws InvalidInputDataException;

    /**
     * Creates an adapter {@link PartDefinition.IPartNetLoader} that invokes this when invoked.
     * <p>
     * <b>Only use this when you are sure the supplied definition will actually be the correct type,</b> like in said
     * definition's constructor.
     *
     * @return the adapter.
     */
    default PartDefinition.IPartNetLoader adapter() {
        return (definition, holder, buffer, ctx) -> loadFromBuffer((D) definition, holder, buffer, ctx);
    }
}
