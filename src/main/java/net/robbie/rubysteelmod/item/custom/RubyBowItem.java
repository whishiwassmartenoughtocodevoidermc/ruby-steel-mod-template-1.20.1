package net.robbie.rubysteelmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.robbie.rubysteelmod.item.moditem;

public class RubyBowItem extends BowItem {
    public RubyBowItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!world.isClient) {
            ItemStack arrowStack = findAmmo(user);
            if (arrowStack.getItem() == moditem.RUBY_ARROW) {
                RubyArrowItem rubyArrowItem = (RubyArrowItem) arrowStack.getItem();
                PersistentProjectileEntity arrowEntity = rubyArrowItem.createArrow(world, arrowStack, user);
                arrowEntity.setOwner(user);
                arrowEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 3.0F, 1.0F); // Adjust the velocity and inaccuracy as needed
                world.spawnEntity(arrowEntity);
            }
        }
    }

    private ItemStack findAmmo(LivingEntity user) {
        if (user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;
            ItemStack offHand = player.getOffHandStack();
            if (offHand.getItem() == moditem.RUBY_ARROW) {
                return offHand;
            }
            for (int i = 0; i < player.getInventory().size(); ++i) {
                ItemStack itemstack = player.getInventory().getStack(i);
                if (itemstack.getItem() == moditem.RUBY_ARROW) {
                    return itemstack;
                }
            }
        }
        return ItemStack.EMPTY; // Return an empty stack if no RubyArrows are found
    }
}
