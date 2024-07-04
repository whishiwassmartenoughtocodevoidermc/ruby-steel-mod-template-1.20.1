package net.robbie.rubysteelmod.mixin;

import net.minecraft.client.util.math.MatrixStack;
import net.robbie.rubysteelmod.RubySteelMod;
import net.robbie.rubysteelmod.item.moditem;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useCustomModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (stack.isOf(moditem.RUBY_SWORD) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(RubySteelMod.MOD_ID, "ruby_sword_3d", "inventory"));
        }
        if (stack.isOf(moditem.RUBY_AXE) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(RubySteelMod.MOD_ID, "ruby_axe_3d", "inventory"));
        }
        if (stack.isOf(moditem.REDSTEEL_KATANA) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(RubySteelMod.MOD_ID, "redsteel_katana_3d", "inventory"));
        }
        // Add your ruby_arrow and ruby_bow here
        if (stack.isOf(moditem.RUBY_ARROW) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(RubySteelMod.MOD_ID, "ruby_arrow_3d", "inventory"));
        }
        if (stack.isOf(moditem.RUBY_BOW) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(RubySteelMod.MOD_ID, "ruby_bow_3d", "inventory"));
        }
        return value;
    }
}
