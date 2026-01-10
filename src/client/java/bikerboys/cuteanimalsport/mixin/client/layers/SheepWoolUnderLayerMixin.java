package bikerboys.cuteanimalsport.mixin.client.layers;

import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.resources.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(SheepWoolUndercoatLayer.class)
public class SheepWoolUnderLayerMixin {


    @WrapOperation(method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/SheepRenderState;FF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/SheepWoolUndercoatLayer;coloredCutoutModelCopyLayerRender(Lnet/minecraft/client/model/Model;Lnet/minecraft/resources/Identifier;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;II)V"))
    private void wrapthatshi(Model model, Identifier identifier, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, LivingEntityRenderState livingEntityRenderState, int i1, int i2, Operation<Void> original) {
        if (!livingEntityRenderState.isBaby) {
            original.call(model, identifier, poseStack, submitNodeCollector, i, livingEntityRenderState, i1, i2);
        }
    }
}
