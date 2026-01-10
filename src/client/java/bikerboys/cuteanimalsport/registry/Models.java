package bikerboys.cuteanimalsport.registry;

import bikerboys.cuteanimalsport.models.base.*;
import bikerboys.cuteanimalsport.models.cat.*;
import bikerboys.cuteanimalsport.models.chicken.*;
import bikerboys.cuteanimalsport.models.cow.*;
import bikerboys.cuteanimalsport.models.ocelot.*;
import bikerboys.cuteanimalsport.models.pig.*;
import bikerboys.cuteanimalsport.models.rabbit.*;
import bikerboys.cuteanimalsport.models.sheep.*;
import bikerboys.cuteanimalsport.models.wolf.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.minecraft.client.model.geom.builders.*;

public class Models {

    public static void register() {

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BABY_OCELOT, BabyOcelotModel::createBabyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BABY_PIG, () -> BabyPigModel.createBodyLayer(CubeDeformation.NONE));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BABY_COW, BabyCowModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BABY_MOOSHROOM, BabyCowModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BABY_CHICKEN, BabyChickenModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BABY_WOLF, BabyWolfModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BABY_SHEEP, BabySheepModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BABY_SHEEP_WOOL, BabySheepModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BABY_CAT, BabyCatModel::createBabyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BABY_CAT_COLLAR, () -> BabyCatModel.createBabyLayer().apply(BabyCatModel.COLLAR_TRANSFORMER));
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.RABBIT_ADULT, AdultRabbitModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.RABBIT_BABY, BabyRabbitModel::createBodyLayer);
    }

}
