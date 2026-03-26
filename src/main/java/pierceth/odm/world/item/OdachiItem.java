package pierceth.odm.world.item;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Blocks;
import yesman.epicfight.world.item.WeaponItem;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class OdachiItem extends WeaponItem {
    public OdachiItem(Properties properties) {
        super(properties.component(DataComponents.TOOL, createToolProperties()));
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 25;
    }

    static Tool createToolProperties() {
        return new Tool(List.of(Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), 15.0F), Tool.Rule.overrideSpeed(BlockTags.SWORD_EFFICIENT, 1.5F)), 1.0F, 2);
    }

    @Override
    public int getEnchantmentLevel(ItemStack stack, Holder<Enchantment> enchantment) {
        return 22;
    }

    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return toRepair.getItem() == Items.IRON_BARS;
    }
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("")); // a space
        tooltipComponents.add(Component.literal("Example Description"));
    }
}
