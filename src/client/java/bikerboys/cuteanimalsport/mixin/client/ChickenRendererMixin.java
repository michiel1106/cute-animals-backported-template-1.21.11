package bikerboys.cuteanimalsport.mixin.client;

import bikerboys.cuteanimalsport.*;
import bikerboys.cuteanimalsport.models.chicken.*;
import bikerboys.cuteanimalsport.registry.*;
import com.google.common.collect.*;
import net.minecraft.client.model.*;
import net.minecraft.client.model.animal.chicken.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.animal.chicken.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

@Mixin(ChickenRenderer.class)
public class ChickenRendererMixin {

    @Unique
    private static final Identifier BABY_CHICKEN_TEMPERATE = Constants.of("textures/entity/chicken/chicken_temperate_baby.png");
    @Unique
    private static final Identifier BABY_CHICKEN_COLD = Constants.of("textures/entity/chicken/chicken_cold_baby.png");
    @Unique
    private static final Identifier BABY_CHICKEN_WARM = Constants.of("textures/entity/chicken/chicken_warm_baby.png");


    @Inject(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/ChickenRenderState;)Lnet/minecraft/resources/Identifier;", at = @At("HEAD"), cancellable = true)
    private void changeChickenTexture(ChickenRenderState state, CallbackInfoReturnable<Identifier> cir) {
        if (state.isBaby) {
            if (state.variant != null) {
                ClientAsset.ResourceTexture texture = state.variant.modelAndTexture().asset();

                if (texture.texturePath().getPath().contains("cold")) {
                    cir.setReturnValue(BABY_CHICKEN_COLD);
                } else if (texture.texturePath().getPath().contains("warm")) {
                    cir.setReturnValue(BABY_CHICKEN_WARM);
                } else {
                    cir.setReturnValue(BABY_CHICKEN_TEMPERATE);
                }

            }
        }
    }

    @Inject(method = "bakeModels", at = @At("HEAD"), cancellable = true)
    private static void changeChickenModel(EntityRendererProvider.Context context, CallbackInfoReturnable<Map<ChickenVariant.ModelType, AdultAndBabyModelPair<ChickenModel>>> cir) {
        EnumMap<ChickenVariant.ModelType, AdultAndBabyModelPair<ChickenModel>> modelTypeAdultAndBabyModelPairEnumMap = Maps.newEnumMap(
                Map.of(
                        ChickenVariant.ModelType.NORMAL,
                        new AdultAndBabyModelPair<>(new ChickenModel(context.bakeLayer(ModelLayers.CHICKEN)), new BabyChickenModel(context.bakeLayer(ModModelLayers.BABY_CHICKEN))),
                        ChickenVariant.ModelType.COLD,
                        new AdultAndBabyModelPair<>(
                                new ColdChickenModel(context.bakeLayer(ModelLayers.COLD_CHICKEN)), new BabyChickenModel(context.bakeLayer(ModModelLayers.BABY_CHICKEN))
                        )
                )
        );

        cir.setReturnValue(modelTypeAdultAndBabyModelPairEnumMap);

    }


}
