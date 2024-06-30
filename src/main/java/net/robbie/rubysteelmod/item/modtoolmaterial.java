package net.robbie.rubysteelmod.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum modtoolmaterial implements ToolMaterial {
    RUBY(7,3503,22.5f,5.0f,  20.5f, ()-> Ingredient.ofItems(moditem.RUBY));

    private final int mineinglevel;
    private final int itemDurability;
    private final float mineingSpeed;
    private final float attackdamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairingredient;

    modtoolmaterial(int mineinglevel, int itemDurability, float mineingSpeed, float attackdamage, float enchantability, Supplier<Ingredient> repairingredient) {
        this.mineinglevel = mineinglevel;
        this.itemDurability = itemDurability;
        this.mineingSpeed = mineingSpeed;
        this.attackdamage = attackdamage;
        this.enchantability = (int) enchantability;
        this.repairingredient = repairingredient;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.mineingSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackdamage;
    }

    @Override
    public int getMiningLevel() {
        return this.mineinglevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairingredient.get();
    }
}
