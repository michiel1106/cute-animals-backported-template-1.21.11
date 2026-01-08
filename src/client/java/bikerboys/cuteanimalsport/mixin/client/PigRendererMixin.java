package bikerboys.cuteanimalsport.mixin.client;

import bikerboys.cuteanimalsport.*;
import bikerboys.cuteanimalsport.models.pig.*;
import bikerboys.cuteanimalsport.registry.*;
import com.google.common.collect.*;
import net.minecraft.client.model.*;
import net.minecraft.client.model.animal.pig.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.animal.pig.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

@Mixin(PigRenderer.class)
public class PigRendererMixin {

    private static final Identifier BABY_PIG_TEMPERATE = Constants.of("textures/entity/pig/pig_temperate_baby.png");
    private static final Identifier BABY_PIG_COLD = Constants.of("textures/entity/pig/pig_cold_baby.png");
    private static final Identifier BABY_PIG_WARM = Constants.of("textures/entity/pig/pig_warm_baby.png");

    @Inject(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/PigRenderState;)Lnet/minecraft/resources/Identifier;", at = @At("HEAD"), cancellable = true)
    private void getCustomTexture(PigRenderState state, CallbackInfoReturnable<Identifier> cir) {
        if (state.isBaby) {
            if (state.variant.modelAndTexture().model().equals(PigVariant.ModelType.COLD)) {
                cir.setReturnValue(BABY_PIG_COLD);
            } else if (state.variant.modelAndTexture().model().equals(PigVariant.ModelType.NORMAL)) {
                cir.setReturnValue(BABY_PIG_TEMPERATE);
            } else {
                cir.setReturnValue(BABY_PIG_WARM);
            }
        }
    }

    @Inject(method = "bakeModels", at = @At("HEAD"), cancellable = true)
    private static void changeModels(EntityRendererProvider.Context context, CallbackInfoReturnable<Map<PigVariant.ModelType, AdultAndBabyModelPair<PigModel>>> cir) {
        EnumMap<PigVariant.ModelType, AdultAndBabyModelPair<PigModel>> var = Maps.newEnumMap(
                Map.of(
                        PigVariant.ModelType.NORMAL,
                        new AdultAndBabyModelPair<>(new PigModel(context.bakeLayer(ModelLayers.PIG)), new BabyPigModel(context.bakeLayer(ModModelLayers.BABY_PIG))),
                        PigVariant.ModelType.COLD,
                        new AdultAndBabyModelPair<>(new ColdPigModel(context.bakeLayer(ModelLayers.COLD_PIG)), new BabyPigModel(context.bakeLayer(ModModelLayers.BABY_PIG)))
                )
        );


    }

}
