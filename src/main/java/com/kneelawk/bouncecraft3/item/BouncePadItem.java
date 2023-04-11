package com.kneelawk.bouncecraft3.item;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import alexiil.mc.lib.multipart.api.MultipartContainer;

import com.kneelawk.bouncecraft3.part.BouncePadDefinition;
import com.kneelawk.bouncecraft3.part.BouncePadPart;
import com.kneelawk.bouncecraft3.util.PlacementUtils;

public class BouncePadItem extends Item {
    private final BouncePadDefinition definition;

    public BouncePadItem(BouncePadDefinition definition, Settings settings) {
        super(settings);
        this.definition = definition;

        definition.pickItem = new ItemStack(this);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient) {
            return ActionResult.PASS;
        }

        @Nullable MultipartContainer.PartOffer offer = PlacementUtils.tryPlacePad(context,
            direction -> holder -> new BouncePadPart(definition, holder, direction));

        PlacementUtils.finishPlacement(context, offer, Blocks.STONE.getDefaultState());

        return offer != null ? ActionResult.SUCCESS : ActionResult.FAIL;
    }
}
