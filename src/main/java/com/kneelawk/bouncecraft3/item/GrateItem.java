package com.kneelawk.bouncecraft3.item;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import alexiil.mc.lib.multipart.api.MultipartContainer;

import com.kneelawk.bouncecraft3.part.GrateDefinition;
import com.kneelawk.bouncecraft3.part.GratePart;
import com.kneelawk.bouncecraft3.util.PlacementUtils;

public class GrateItem extends Item {
    private final GrateDefinition definition;

    public GrateItem(GrateDefinition definition, Settings settings) {
        super(settings);
        this.definition = definition;

        definition.pickItem = new ItemStack(this);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient) {
            return ActionResult.CONSUME;
        }

        @Nullable MultipartContainer.PartOffer offer = PlacementUtils.tryPlaceGrate(context,
            direction -> holder -> new GratePart(definition, holder, direction), false);

        PlacementUtils.finishPlacement(context, offer, Blocks.STONE.getDefaultState());

        return offer != null ? ActionResult.SUCCESS : ActionResult.FAIL;
    }
}
