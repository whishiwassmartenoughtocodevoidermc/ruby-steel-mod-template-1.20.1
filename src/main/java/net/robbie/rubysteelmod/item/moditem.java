package net.robbie.rubysteelmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.robbie.rubysteelmod.RubySteelMod;
import net.robbie.rubysteelmod.item.custom.RubyArrowItem;
import net.robbie.rubysteelmod.item.custom.RubyBowItem;
import net.robbie.rubysteelmod.item.custom.modarmoritem;
import net.minecraft.entity.LivingEntity;

import net.minecraft.item.ItemStack;

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
    public static final Item REDSTEEL_KATANA = registeritem("redsteel_katana",new FlamingkatanaItem(modtoolmaterial.REDSTEEL,8,-1.2f,new FabricItemSettings().fireproof()));
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
        entries.add(RUBY_HELMET);
        entries.add(RUBY_CHESTPLATE);
        entries.add(RUBY_LEGGINGS);
        entries.add(RUBY_BOOTS);
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
    public static class FlamingkatanaItem extends SwordItem {
    public FlamingkatanaItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setOnFireFor(1200); // Sets the target on fire for 1200 ticks (60 seconds)
        return super.postHit(stack, target, attacker);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            // Trigger the animation
            player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, 1.0F);
        }
        return ActionResult.SUCCESS;
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
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setOnFireFor(1200); // Sets the target on fire for 1200 ticks (60 seconds)
        return super.postHit(stack, target, attacker);
    }


    }}



