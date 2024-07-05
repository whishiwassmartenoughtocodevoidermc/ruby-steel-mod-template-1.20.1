package net.robbie.rubysteelmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.Vec3d;
import net.robbie.rubysteelmod.item.moditem;

public class RubyBowItem extends BowItem {
    public RubyBowItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!world.isClient) {
            ItemStack arrowStack = findAmmo(user);
            if (!arrowStack.isEmpty()) {
                ProjectileEntity arrowEntity;
                if (arrowStack.getItem() == moditem.RUBY_ARROW) {
                    RubyArrowItem rubyArrowItem = (RubyArrowItem) arrowStack.getItem();
                    arrowEntity = rubyArrowItem.createArrow(world, arrowStack, user);
                } else {
                    arrowEntity = createArrow(world, arrowStack, user); // Create a regular arrow entity
                }
                if (arrowEntity != null) {
                    arrowEntity.setOwner(user);
                    float velocity = 2.0F; // Adjust velocity as needed
                    arrowEntity.setVelocity(arrowEntity.getVelocity().normalize().multiply(velocity)); // Set arrow velocity
                    world.spawnEntity(arrowEntity);
                }
            }
        }
    }

    private ItemStack findAmmo(LivingEntity user) {
        if (user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;
            ItemStack offHand = player.getOffHandStack();
            if (!offHand.isEmpty() && isArrow(offHand)) {
                return offHand;
            }
            for (int i = 0; i < player.getInventory().size(); ++i) {
                ItemStack itemstack = player.getInventory().getStack(i);
                if (!itemstack.isEmpty() && isArrow(itemstack)) {
                    return itemstack;
                }
            }
        }
        return ItemStack.EMPTY; // Return an empty stack if no arrows are found
    }

    private boolean isArrow(ItemStack stack) {
        return stack.getItem() instanceof net.minecraft.item.ArrowItem; // Check for Minecraft's ArrowItem
    }

    // Example method to create a regular arrow entity
    private ProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        ArrowEntity arrowEntity = new ArrowEntity(world, shooter);
        arrowEntity.setDamage(2.0); // Set arrow damage
        arrowEntity.setPierceLevel((byte) 1); // Example pierce level
        return arrowEntity;
    }
}
