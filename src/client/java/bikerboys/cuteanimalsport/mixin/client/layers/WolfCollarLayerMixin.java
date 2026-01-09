package bikerboys.cuteanimalsport.mixin.client.layers;

import bikerboys.cuteanimalsport.*;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.llamalad7.mixinextras.sugar.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.client.renderer.rendertype.*;
import net.minecraft.resources.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(WolfCollarLayer.class)
public class WolfCollarLayerMixin {
    @Unique
    private static final Identifier WOLF_BABY_COLLAR_LOCATION = Constants.of("textures/entity/wolf/wolf_collar_baby.png");


    @WrapOperation(method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/WolfRenderState;FF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/rendertype/RenderTypes;entityCutoutNoCull(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/renderer/rendertype/RenderType;"))
    private RenderType changeCollarTexture(Identifier identifier, Operation<RenderType> original, @Local(argsOnly = true) WolfRenderState state) {

        if (state.isBaby) {
            return RenderTypes.entityCutoutNoCull(WOLF_BABY_COLLAR_LOCATION);
        }


        return original.call(identifier);
    }

}
