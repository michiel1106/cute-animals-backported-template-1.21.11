package bikerboys.cuteanimalsport.registry;

import bikerboys.cuteanimalsport.models.base.*;
import bikerboys.cuteanimalsport.models.ocelot.*;
import bikerboys.cuteanimalsport.models.pig.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.minecraft.client.model.geom.builders.*;

public class Models {

    public static void register() {

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BABY_OCELOT, BabyOcelotModel::createBabyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BABY_PIG, () -> BabyPigModel.createBodyLayer(CubeDeformation.NONE));

    }

}
