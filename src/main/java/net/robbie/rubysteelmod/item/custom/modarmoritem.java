package net.robbie.rubysteelmod.item.custom;

import com.google.common.collect.ImmutableMap;
import net.robbie.rubysteelmod.item.modarmormaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class modarmoritem extends ArmorItem {
    private static final Map<ArmorMaterial, List<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP =
        (new ImmutableMap.Builder<ArmorMaterial, List<StatusEffectInstance>>())
            .put(modarmormaterial.RUBY, Arrays.asList(
                new StatusEffectInstance(StatusEffects.SPEED, 80, 1, false, false, true)
            ))
            .put(modarmormaterial.STEEL_INGOT, Arrays.asList(
                new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 80, 0, false, false, true)
            ))
            .build();

    private static final Map<ArmorMaterial, StatusEffectInstance> MATERIAL_TO_WITHER_EFFECT =
        (new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
            .put(modarmormaterial.RUBY, new StatusEffectInstance(StatusEffects.WITHER, 60, 2, false, false, true))
            .build();

    public modarmoritem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity instanceof PlayerEntity player) {
            if (hasFullSuitOfArmorOn(player)) {
                applyBaseEffects(player);

                if (player.isOnFire() && hasCorrectArmorOn(modarmormaterial.RUBY, player)) {
                    applyWitherEffect(player, modarmormaterial.RUBY);
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void applyBaseEffects(PlayerEntity player) {
        for (Map.Entry<ArmorMaterial, List<StatusEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial material = entry.getKey();
            List<StatusEffectInstance> effects = entry.getValue();

            if (hasCorrectArmorOn(material, player)) {
                for (StatusEffectInstance effect : effects) {
                    addOrRefreshEffect(player, effect);
                }
            }
        }
    }

    private void applyWitherEffect(PlayerEntity player, ArmorMaterial material) {
        StatusEffectInstance witherEffect = MATERIAL_TO_WITHER_EFFECT.get(material);
        if (witherEffect != null && (!player.hasStatusEffect(witherEffect.getEffectType()) || shouldRefreshEffect(player, witherEffect))) {
            player.addStatusEffect(new StatusEffectInstance(witherEffect.getEffectType(), witherEffect.getDuration() + 100, witherEffect.getAmplifier(),
                witherEffect.isAmbient(), witherEffect.shouldShowParticles(), witherEffect.shouldShowIcon()));
        }
    }

    private void addOrRefreshEffect(PlayerEntity player, StatusEffectInstance effect) {
        if (!player.hasStatusEffect(effect.getEffectType()) || shouldRefreshEffect(player, effect)) {
            player.addStatusEffect(new StatusEffectInstance(effect.getEffectType(), effect.getDuration() + 100, effect.getAmplifier(),
                effect.isAmbient(), effect.shouldShowParticles(), effect.shouldShowIcon()));
        }
    }

    private boolean shouldRefreshEffect(PlayerEntity player, StatusEffectInstance effect) {
        StatusEffectInstance currentEffect = player.getStatusEffect(effect.getEffectType());
        return currentEffect == null || currentEffect.getDuration() <= 3;
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack breastplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
            && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmorStack(1).getItem());
        ArmorItem breastplate = ((ArmorItem) player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmorStack(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
            leggings.getMaterial() == material && boots.getMaterial() == material;
    }
}
