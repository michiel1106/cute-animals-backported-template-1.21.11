package bikerboys.cuteanimalsport.mixin.client;

import bikerboys.cuteanimalsport.*;
import bikerboys.cuteanimalsport.models.cat.*;
import bikerboys.cuteanimalsport.registry.*;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.llamalad7.mixinextras.sugar.*;
import net.minecraft.client.model.animal.feline.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.animal.feline.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(CatRenderer.class)
public class CatRendererMixin {


    @WrapOperation(method = "<init>", at = @At(value = "NEW", target = "Lnet/minecraft/client/model/animal/feline/CatModel;", ordinal = 1))
    private static CatModel replaceBabyCatModel(ModelPart modelPart, Operation<CatModel> original, @Local(argsOnly = true) EntityRendererProvider.Context context) {
        return new BabyCatModel(context.bakeLayer(ModModelLayers.BABY_CAT));
    }



    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/animal/feline/Cat;Lnet/minecraft/client/renderer/entity/state/CatRenderState;F)V", at = @At("TAIL"))
    private void cuteanimals$babyCatTextures(Cat cat, CatRenderState state, float tickDelta, CallbackInfo ci) {
        if (!cat.isBaby()) return;

        Identifier original = state.texture;
        String path = original.getPath();

        if (path.contains("all_black")) {
            state.texture = Constants.of("textures/entity/cat/cat_all_black_baby.png");
        }
        else if (path.contains("black")) {
            state.texture = Constants.of("textures/entity/cat/cat_black_baby.png");
        }
        else if (path.contains("british_shorthair")) {
            state.texture = Constants.of("textures/entity/cat/cat_british_shorthair_baby.png");
        }
        else if (path.contains("calico")) {
            state.texture = Constants.of("textures/entity/cat/cat_calico_baby.png");
        }
        else if (path.contains("jellie")) {
            state.texture = Constants.of("textures/entity/cat/cat_jellie_baby.png");
        }
        else if (path.contains("persian")) {
            state.texture = Constants.of("textures/entity/cat/cat_persian_baby.png");
        }
        else if (path.contains("ragdoll")) {
            state.texture = Constants.of("textures/entity/cat/cat_ragdoll_baby.png");
        }
        else if (path.contains("red")) {
            state.texture = Constants.of("textures/entity/cat/cat_red_baby.png");
        }
        else if (path.contains("siamese")) {
            state.texture = Constants.of("textures/entity/cat/cat_siamese_baby.png");
        }
        else if (path.contains("tabby")) {
            state.texture = Constants.of("textures/entity/cat/cat_tabby_baby.png");
        }
        else if (path.contains("white")) {
            state.texture = Constants.of("textures/entity/cat/cat_white_baby.png");
        }
    }
}
