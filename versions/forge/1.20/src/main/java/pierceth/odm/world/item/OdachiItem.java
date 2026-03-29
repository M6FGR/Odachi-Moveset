package pierceth.odm.world.item;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.world.item.WeaponItem;

import javax.annotation.Nullable;
import java.util.List;

public class OdachiItem extends WeaponItem {
    public OdachiItem(Item.Properties build) {
        super(Tiers.NETHERITE, 6, -2.7F, build);
    }

    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return toRepair.getItem() == Items.IRON_BARS;
    }

    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable(EpicFightMod.format("item.%s.uchigatana.tooltip")));
    }

    public float getDestroySpeed(ItemStack itemstack, BlockState blockstate) {
        if (blockstate.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            return blockstate.is(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
        }
    }

    public boolean isCorrectToolForDrops(BlockState blockstate) {
        return blockstate.is(Blocks.COBWEB);
    }
}
