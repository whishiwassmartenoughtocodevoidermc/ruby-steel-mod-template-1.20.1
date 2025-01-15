package net.robbie.rubysteelmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

public class SteelSwordItem extends SwordItem {

    public SteelSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public boolean postHit( LivingEntity target, LivingEntity attacker) {
        // Example: Apply custom effects or behaviors on hit
        if (target.isAlive()){
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS));
        }
        if (!target.getWorld().isClient()) {
            // Custom logic, e.g., apply fire damage

        }
        return super.postHit(target, attacker); // Retain default behavior (e.g., damage durability)
    }


}
