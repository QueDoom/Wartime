package net.quedoom.wartime.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.quedoom.wartime.init.ModTags;
import net.quedoom.wartime.misc.MaceUtils;

import java.util.List;

public class SuperMace extends MaceItem {
    public SuperMace(Properties properties) {
        super(
                properties.component(
                        DataComponents.TOOL,
                        MaceUtils.createToolProperties()
                )
        );
    }

    private Tool createToolPropertiees(TagKey<Block> tagKey) {
        return new Tool(
                                List.of(
                                Tool.Rule.deniesDrops(BlockTags.INCORRECT_FOR_NETHERITE_TOOL),
                                Tool.Rule.minesAndDrops(tagKey, 11.5F)
                        ),
                                1.0F, 0);
    }


    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(itemAbility);
    }

    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 10.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -0.8F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
                )
                .build();
    }


    @Override
    public int getEnchantmentValue() {
        return 35;
    }

    private static double getKnockbackPower(Player p_338265_, LivingEntity p_338630_, Vec3 p_338866_) {
        return (3.5 - p_338866_.length())
                * 0.7F
                * (double)(p_338265_.fallDistance > 5.0F ? 2 : 1)
                * (1.0 - p_338630_.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
    }

    @Override
    public float getAttackDamageBonus(Entity p_344900_, float p_335575_, DamageSource p_344972_) {
        if (p_344972_.getDirectEntity() instanceof LivingEntity livingentity) {
            if (!canSmashAttack(livingentity)) {
                return 0.0F;
            } else {
                float f3 = 6.0F;
                float f = 16.0F;
                float f1 = livingentity.fallDistance * 2;
                float f2;
                if (f1 <= f3) {
                    f2 = 8.0F * f1;
                } else if (f1 <= f) {
                    f2 = 24.0F + 8.0F * (f1 - f3);
                } else {
                    f2 = 44.0F + f1 - f;
                }

                return livingentity.level() instanceof ServerLevel serverlevel
                        ? f2 + EnchantmentHelper.modifyFallBasedDamage(serverlevel, livingentity.getWeaponItem(), p_344900_, p_344972_, 0.0F) * f1
                        : f2;
            }
        } else {
            return 0.0F;
        }
    }


}
