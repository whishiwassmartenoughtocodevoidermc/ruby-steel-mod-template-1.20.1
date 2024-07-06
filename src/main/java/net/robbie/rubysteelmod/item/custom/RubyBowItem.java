package net.robbie.rubysteelmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.MathHelper;
import net.robbie.rubysteelmod.item.moditem;

import java.util.function.Predicate;

public class RubyBowItem extends BowItem {
    public RubyBowItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!world.isClient) {
            ItemStack rubyArrowStack = findRubyArrow(user);
            if (!rubyArrowStack.isEmpty()) {
                fireRubyArrow(world, user, rubyArrowStack, stack.getMaxUseTime() - remainingUseTicks);
            }
        }
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return stack -> stack.getItem() == moditem.RUBY_ARROW;
    }


    public ItemStack getHeldProjectile(LivingEntity user) {
        return findRubyArrow(user);
    }

    private ItemStack findRubyArrow(LivingEntity user) {
        if (user instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) user;

            // Check offhand first
            ItemStack offHand = player.getOffHandStack();
            if (!offHand.isEmpty() && offHand.getItem() == moditem.RUBY_ARROW) {
                return offHand;
            }

            // Check inventory for Ruby Arrows
            for (int i = 0; i < player.getInventory().size(); ++i) {
                ItemStack itemstack = player.getInventory().getStack(i);
                if (!itemstack.isEmpty() && itemstack.getItem() == moditem.RUBY_ARROW) {
                    return itemstack;
                }
            }
        }
        return ItemStack.EMPTY; // Return an empty stack if no Ruby Arrows are found
    }

    private void fireRubyArrow(World world, LivingEntity user, ItemStack rubyArrowStack, int useTicks) {
        ProjectileEntity arrowEntity = createRubyArrow(world, rubyArrowStack, user);

        if (arrowEntity != null) {
            arrowEntity.setOwner(user);
            float velocity = getArrowVelocity(useTicks);

            Vec3d lookVec = user.getRotationVec(1.0F);
            arrowEntity.setVelocity(lookVec.x, lookVec.y, lookVec.z, velocity * 3.0F, 1.0F); // Adjust the arrow velocity and inaccuracy

            world.spawnEntity(arrowEntity);

            // Decrease arrow stack size after firing
            if (user instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) user;
                if (!player.isCreative()) {
                    rubyArrowStack.decrement(1);
                }
            }
        }
    }

    // Example method to create a Ruby arrow entity
    private ProjectileEntity createRubyArrow(World world, ItemStack stack, LivingEntity shooter) {
        RubyArrowItem rubyArrowItem = (RubyArrowItem) stack.getItem();
        return rubyArrowItem.createArrow(world, stack, shooter);
    }

    private float getArrowVelocity(int charge) {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }
}
