package bikerboys.cuteanimalsport.mixin.client;

import bikerboys.cuteanimalsport.*;
import bikerboys.cuteanimalsport.misc.*;
import bikerboys.cuteanimalsport.mixin.client.animation.*;
import bikerboys.cuteanimalsport.models.rabbit.*;
import bikerboys.cuteanimalsport.registry.*;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.llamalad7.mixinextras.sugar.*;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.animal.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(RabbitRenderer.class)
public class RabbitRendererMixin {


    @WrapOperation(
            method = "<init>",
            at = @At(value = "NEW", target = "(Lnet/minecraft/client/model/geom/ModelPart;)Lnet/minecraft/client/model/RabbitModel;", ordinal = 0)
    )
    private static RabbitModel replaceAdultModel(ModelPart modelPart, Operation<RabbitModel> original, @Local(argsOnly = true) EntityRendererProvider.Context context) {
        return new AdultRabbitModel(context.bakeLayer(ModModelLayers.RABBIT_ADULT));
    }

    @WrapOperation(
            method = "<init>",
            at = @At(value = "NEW", target = "(Lnet/minecraft/client/model/geom/ModelPart;)Lnet/minecraft/client/model/RabbitModel;", ordinal = 1)
    )
    private static RabbitModel replaceBabyModel(ModelPart modelPart, Operation<RabbitModel> original, @Local(argsOnly = true) EntityRendererProvider.Context context) {
        return new BabyRabbitModel(context.bakeLayer(ModModelLayers.RABBIT_BABY));
    }



    @Inject(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/RabbitRenderState;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    private void cuteanimals$rabbitTextures(RabbitRenderState state, CallbackInfoReturnable<ResourceLocation> cir) {
        boolean baby = state.isBaby;

        if (state.isToast) {
            cir.setReturnValue(baby
                    ? Constants.of("textures/entity/rabbit/rabbit_toast_baby.png")
                    : Constants.of("textures/entity/rabbit/rabbit_toast.png"));
            return;
        }

        cir.setReturnValue(switch (state.variant) {
            case BROWN -> Constants.of(baby
                    ? "textures/entity/rabbit/rabbit_brown_baby.png"
                    : "textures/entity/rabbit/rabbit_brown.png");

            case WHITE -> Constants.of(baby
                    ? "textures/entity/rabbit/rabbit_white_baby.png"
                    : "textures/entity/rabbit/rabbit_white.png");

            case BLACK -> Constants.of(baby
                    ? "textures/entity/rabbit/rabbit_black_baby.png"
                    : "textures/entity/rabbit/rabbit_black.png");

            case GOLD -> Constants.of(baby
                    ? "textures/entity/rabbit/rabbit_gold_baby.png"
                    : "textures/entity/rabbit/rabbit_gold.png");

            case SALT -> Constants.of(baby
                    ? "textures/entity/rabbit/rabbit_salt_baby.png"
                    : "textures/entity/rabbit/rabbit_salt.png");

            case WHITE_SPLOTCHED -> Constants.of(baby
                    ? "textures/entity/rabbit/rabbit_white_splotched_baby.png"
                    : "textures/entity/rabbit/rabbit_white_splotched.png");

            case EVIL -> Constants.of(baby
                    ? "textures/entity/rabbit/rabbit_caerbannog_baby.png"
                    : "textures/entity/rabbit/rabbit_caerbannog.png");
        });
    }



    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void yourmod$copyAnimationStates(Rabbit rabbit, RabbitRenderState state, float tickDelta, CallbackInfo ci) {
        RabbitAnimationAccess entityAccess = (RabbitAnimationAccess) rabbit;

        RabbitRenderStateAccess stateAccess = (RabbitRenderStateAccess) state;

        stateAccess.yourmod$getHopAnimationState().copyFrom(entityAccess.yourmod$getHopAnimationState());

        stateAccess.yourmod$getIdleHeadTiltAnimationState().copyFrom(entityAccess.yourmod$getIdleHeadTiltAnimationState());
    }


}


