package pierceth.odm.world.capabilities.item;

import net.minecraft.network.chat.Component;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

public enum OdachiCategories implements WeaponCategory {
    ODACHI(Component.translatable("weapon_category.odm.odachi"));
    final int id;
    final Component component;

    OdachiCategories(Component component) {
        this.id = WeaponCategory.ENUM_MANAGER.assign(this);
        this.component = component;
    }

    @Override
    public int universalOrdinal() {
        return this.id;
    }

    @Override
    public Component getTranslatable() {
        return this.component;
    }

}
