package net.quedoom.wartime.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.quedoom.wartime.Wartime;
import net.quedoom.wartime.item.SuperMace;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Wartime.MOD_ID);

    public static final DeferredItem<Item> SUPER_MEGA_RAPER_MACE = ITEMS.register("super_mace",
            () -> new SuperMace(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .fireResistant()
                    .attributes(PickaxeItem.createAttributes(Tiers.NETHERITE, 100.0F, -1.4F))));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

}
