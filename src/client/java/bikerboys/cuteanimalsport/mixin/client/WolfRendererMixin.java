package bikerboys.cuteanimalsport.mixin.client;

import bikerboys.cuteanimalsport.*;
import bikerboys.cuteanimalsport.models.wolf.*;
import bikerboys.cuteanimalsport.registry.*;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.llamalad7.mixinextras.sugar.*;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.animal.wolf.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(WolfRenderer.class)
public class WolfRendererMixin {

    @WrapOperation(
            method = "<init>",
            at = @At(value = "NEW", target = "Lnet/minecraft/client/model/WolfModel;", ordinal = 1)
    )
    private static WolfModel replaceBabyModel(ModelPart modelPart, Operation<WolfModel> original, @Local(argsOnly = true) EntityRendererProvider.Context context) {
        return new BabyWolfModel(context.bakeLayer(ModModelLayers.BABY_WOLF));
    }


    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/animal/wolf/Wolf;Lnet/minecraft/client/renderer/entity/state/WolfRenderState;F)V", at = @At("TAIL"))
    private void cuteanimals$babyWolfTextures(Wolf wolf, WolfRenderState state, float tickDelta, CallbackInfo ci) {
        if (!wolf.isBaby()) return;

        ResourceLocation original = state.texture;
        String path = original.getPath();

        // WOODS
        if (path.contains("wolf_woods")) {
            if (path.contains("angry")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_woods_angry_baby.png");
            } else if (path.contains("tame")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_woods_tame_baby.png");
            } else {
                state.texture = Constants.of("textures/entity/wolf/wolf_woods_baby.png");
            }
        }

        // SNOWY
        else if (path.contains("wolf_snowy")) {
            if (path.contains("angry")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_snowy_angry_baby.png");
            } else if (path.contains("tame")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_snowy_tame_baby.png");
            } else {
                state.texture = Constants.of("textures/entity/wolf/wolf_snowy_baby.png");
            }
        }

        // CHESTNUT
        else if (path.contains("wolf_chestnut")) {
            if (path.contains("angry")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_chestnut_angry_baby.png");
            } else if (path.contains("tame")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_chestnut_tame_baby.png");
            } else {
                state.texture = Constants.of("textures/entity/wolf/wolf_chestnut_baby.png");
            }
        }

        // BLACK
        else if (path.contains("wolf_black")) {
            if (path.contains("angry")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_black_angry_baby.png");
            } else if (path.contains("tame")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_black_tame_baby.png");
            } else {
                state.texture = Constants.of("textures/entity/wolf/wolf_black_baby.png");
            }
        }

        // ASHEN
        else if (path.contains("wolf_ashen")) {
            if (path.contains("angry")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_ashen_angry_baby.png");
            } else if (path.contains("tame")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_ashen_tame_baby.png");
            } else {
                state.texture = Constants.of("textures/entity/wolf/wolf_ashen_baby.png");
            }
        }

        // STRIPED
        else if (path.contains("wolf_striped")) {
            if (path.contains("angry")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_striped_angry_baby.png");
            } else if (path.contains("tame")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_striped_tame_baby.png");
            } else {
                state.texture = Constants.of("textures/entity/wolf/wolf_striped_baby.png");
            }
        }

        // SPOTTED
        else if (path.contains("wolf_spotted")) {
            if (path.contains("angry")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_spotted_angry_baby.png");
            } else if (path.contains("tame")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_spotted_tame_baby.png");
            } else {
                state.texture = Constants.of("textures/entity/wolf/wolf_spotted_baby.png");
            }
        }

        // RUSTY
        else if (path.contains("wolf_rusty")) {
            if (path.contains("angry")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_rusty_angry_baby.png");
            } else if (path.contains("tame")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_rusty_tame_baby.png");
            } else {
                state.texture = Constants.of("textures/entity/wolf/wolf_rusty_baby.png");
            }
        }

        else {
            if (path.contains("angry")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_angry_baby.png");
            } else if (path.contains("tame")) {
                state.texture = Constants.of("textures/entity/wolf/wolf_tame_baby.png");
            } else {
                state.texture = Constants.of("textures/entity/wolf/wolf_baby.png");
            }

        }

    }
}
