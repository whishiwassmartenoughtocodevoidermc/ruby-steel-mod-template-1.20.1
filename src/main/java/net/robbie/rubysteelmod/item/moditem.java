package net.robbie.rubysteelmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.robbie.rubysteelmod.RubySteelMod;
import net.robbie.rubysteelmod.item.custom.modarmoritem;
import net.minecraft.entity.LivingEntity;
import  net.minecraft.entity.projectile.PersistentProjectileEntity;

import java.util.function.Predicate;

import static net.minecraft.command.EntitySelectorReader.RANDOM;

public class moditem {
    public static final Item REDSTEEL = registeritem("redsteel",new Item(new FabricItemSettings()));
    public static final Item RAW_RUBY = registeritem("raw_ruby",new Item(new FabricItemSettings()));
    public static final Item RUBY = registeritem("ruby",new Item(new FabricItemSettings()));
    public static final Item TUNGSTEN = registeritem("tungsten",new Item(new FabricItemSettings()));
    public static final Item STEEL = registeritem("steel",new Item(new FabricItemSettings()));
    public static final Item RAW_TUNGSTEN = registeritem("raw_tungsten",new Item(new FabricItemSettings()));
    public static final Item RUBY_DUST = registeritem("ruby_dust",new Item(new FabricItemSettings()));
    public static final Item RUBY_SWORD = registeritem("ruby_sword",new FlamingSwordItem(modtoolmaterial.RUBY,4,-2.4f,new FabricItemSettings().fireproof()));
    public static final Item RUBY_BOW = registeritem("ruby_bow",new RubyBowItem(new FabricItemSettings().fireproof()));
    public static final Item RUBY_PICKAXE = registeritem("ruby_pickaxe",new FlamingPickaxeItem((modtoolmaterial.RUBY),1,0.6f,new FabricItemSettings().fireproof()));
    public static final Item RUBY_AXE = registeritem("ruby_axe", new FlamingAxeItem((modtoolmaterial.RUBY), 6, -3.0f, new FabricItemSettings().fireproof()));
    public static final Item RUBY_HELMET = registeritem("ruby_helmet", new modarmoritem(modarmormaterial.RUBY, ArmorItem.Type.HELMET, new FabricItemSettings().fireproof()));
    public static final Item RUBY_CHESTPLATE = registeritem("ruby_chestplate", new ArmorItem(modarmormaterial.RUBY, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().fireproof()));
    public static final Item RUBY_LEGGINGS = registeritem("ruby_leggings", new ArmorItem(modarmormaterial.RUBY, ArmorItem.Type.LEGGINGS, new FabricItemSettings().fireproof()));
    public static final Item RUBY_BOOTS = registeritem("ruby_boots", new ArmorItem(modarmormaterial.RUBY, ArmorItem.Type.BOOTS, new FabricItemSettings().fireproof()));
    public static final Item REDSTEEL_KATANA = registeritem("redsteel_katana",new FlamingSwordItem(modtoolmaterial.REDSTEEL,8,-1.2f,new FabricItemSettings().fireproof()));
    public static final Item RUBY_ARROW = registeritem("ruby_arrow", new RubyArrowItem(new FabricItemSettings().fireproof()));

    private static void addItemstoingrediantstab(FabricItemGroupEntries entries) {
        entries.add(RUBY);
        entries.add(TUNGSTEN);
        entries.add(STEEL);
        entries.add(REDSTEEL);
        entries.add(RAW_RUBY);
        entries.add(RAW_TUNGSTEN);
        entries.add(RUBY_DUST);
    }

    private static void addItemstoweponstab(FabricItemGroupEntries entries) {
     entries.add(RUBY_SWORD);
     entries.add(RUBY_AXE);
     entries.add(moditem.RUBY_HELMET);
     entries.add(moditem.RUBY_CHESTPLATE);
     entries.add(moditem.RUBY_LEGGINGS);
     entries.add(moditem.RUBY_BOOTS);
     entries.add(REDSTEEL_KATANA);
     entries.add(RUBY_ARROW);
     entries.add(RUBY_BOW);
    }

    private static void addItemstotoolstab(FabricItemGroupEntries entries) {
     entries.add(RUBY_PICKAXE);
    }

    private static Item registeritem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(RubySteelMod.MOD_ID, name), item);
    }

    public static void registermoditem() {
        RubySteelMod.LOGGER.info("Registering mod items for  " + RubySteelMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(moditem::addItemstoingrediantstab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(moditem::addItemstoweponstab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(moditem::addItemstotoolstab);
    }

    public static class FlamingSwordItem extends SwordItem {
        public FlamingSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            target.setOnFireFor(1200); // Sets the target on fire for 1200 ticks (60 seconds)
            return super.postHit(stack, target, attacker);
        }
    }

    public static class FlamingPickaxeItem extends PickaxeItem {
        public FlamingPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            target.setOnFireFor(1200); // Sets the target on fire for 1200 ticks (60 seconds)
            return super.postHit(stack, target, attacker);
        }
    }

    public static class FlamingAxeItem extends AxeItem {
        public FlamingAxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }
    }

public static class RubyBowItem extends BowItem {
    public RubyBowItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity)user;
            boolean creativeMode = playerEntity.getAbilities().creativeMode;
            ItemStack itemStack = playerEntity.getMainHandStack();

            if (!itemStack.isEmpty() || creativeMode) {
                if (itemStack.isEmpty()) {
                    itemStack = new ItemStack(moditem.RUBY_ARROW);  // Only use ruby arrows
                }

                int i = this.getMaxUseTime(stack) - remainingUseTicks;
                float f = getPullProgress(i);

                if ((double)f >= 0.1D) {
                    boolean bl = creativeMode && itemStack.getItem() == moditem.RUBY_ARROW;  // Only use ruby arrows

                    if (!world.isClient) {
                        ArrowItem arrowItem = (ArrowItem)((ArrowItem)(itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : moditem.RUBY_ARROW));  // Only use ruby arrows
                        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, itemStack, playerEntity);
                        persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 3.0F, 1.0F);

                        if (f == 1.0F) {
                            persistentProjectileEntity.setCritical(true);
                        }

                        stack.damage(1, playerEntity, (p) -> {
                            p.sendToolBreakStatus(playerEntity.getActiveHand());
                        });

                        if (bl || playerEntity.getAbilities().creativeMode && (itemStack.getItem() == moditem.RUBY_ARROW)) {
                            persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                        }

                        world.spawnEntity(persistentProjectileEntity);
                    }

                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!bl && !playerEntity.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                        if (itemStack.isEmpty()) {
                            playerEntity.getInventory().removeOne(itemStack);
                        }
                    }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
            }
        }
    }
}



    public static class RubyArrowItem extends ArrowItem {
        public RubyArrowItem(Settings settings) {
            super(settings);
        }

        @Override
        public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
            ArrowEntity arrowEntity = new ArrowEntity(world, shooter);
            arrowEntity.setOnFireFor(1200);  // Sets the arrow on fire for 5 seconds (100 ticks)
            return arrowEntity;
        }
    }
}
