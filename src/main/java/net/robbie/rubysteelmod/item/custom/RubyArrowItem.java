package net.robbie.rubysteelmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RubyArrowItem extends ArrowItem {
    public RubyArrowItem(Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        ArrowEntity arrowEntity = new ArrowEntity(world, shooter);
        arrowEntity.setDamage(9.0); // Example damage, adjust as necessary
        arrowEntity.setOnFireFor(100); // Sets the arrow on fire for 5 seconds (100 ticks)
        return arrowEntity;
    }
}
