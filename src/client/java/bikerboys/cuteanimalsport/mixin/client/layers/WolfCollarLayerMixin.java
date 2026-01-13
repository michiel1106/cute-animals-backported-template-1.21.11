package bikerboys.cuteanimalsport.mixin.client.layers;

import bikerboys.cuteanimalsport.*;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.llamalad7.mixinextras.sugar.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.resources.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(WolfCollarLayer.class)
public class WolfCollarLayerMixin {
    @Unique
    private static final ResourceLocation WOLF_BABY_COLLAR_LOCATION = Constants.of("textures/entity/wolf/wolf_collar_baby.png");


    @WrapOperation(method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/WolfRenderState;FF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;entityCutoutNoCull(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/renderer/RenderType;"))
    private RenderType changeCollarTexture(ResourceLocation identifier, Operation<RenderType> original, @Local(argsOnly = true) WolfRenderState state) {

        if (state.isBaby) {
            return RenderType.entityCutoutNoCull(WOLF_BABY_COLLAR_LOCATION);
        }


        return original.call(identifier);
    }

}
