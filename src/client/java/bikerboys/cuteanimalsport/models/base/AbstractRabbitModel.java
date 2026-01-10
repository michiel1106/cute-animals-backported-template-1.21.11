package bikerboys.cuteanimalsport.models.base;

import net.minecraft.client.model.animal.rabbit.*;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.RabbitRenderState;
import net.minecraft.util.*;

public abstract class AbstractRabbitModel extends RabbitModel {
    protected static final String FRONT_LEGS = "frontlegs";
    protected static final String BACK_LEGS = "backlegs";
    protected static final String LEFT_HAUNCH = "left_haunch";
    protected static final String RIGHT_HAUNCH = "right_haunch";
    private final KeyframeAnimation hopAnimation;
    private final KeyframeAnimation idleHeadTiltAnimation;

    public AbstractRabbitModel(final ModelPart root, final AnimationDefinition hop, final AnimationDefinition idleHeadTilt) {
        super(root);
        this.hopAnimation = hop.bake(root);
        this.idleHeadTiltAnimation = idleHeadTilt.bake(root);
    }

    public void setupAnim(final RabbitRenderState rabbitRenderState) {
        super.setupAnim(rabbitRenderState);

        ModelPart body = getChildPart("body");

        ModelPart head = body.getChild("head");
        ModelPart tail = body.getChild("tail");

        ModelPart frontLegs = body.getChild("frontlegs");
        ModelPart rightFrontLeg = frontLegs.getChild("right_front_leg");
        ModelPart leftFrontLeg = frontLegs.getChild("left_front_leg");

        ModelPart backLegs = getChildPart("backlegs");
        ModelPart rightHaunch = backLegs.getChild("right_hind_leg").getChild("right_haunch");
        ModelPart leftHaunch = backLegs.getChild("left_hind_leg").getChild("left_haunch");


        head.xRot = rabbitRenderState.xRot * (float) (Math.PI / 180.0);
        head.yRot = rabbitRenderState.yRot * (float) (Math.PI / 180.0);
        float f = Mth.sin(rabbitRenderState.jumpCompletion * (float) Math.PI);
        leftHaunch.xRot += f * 50.0F * (float) (Math.PI / 180.0);
        rightHaunch.xRot += f * 50.0F * (float) (Math.PI / 180.0);
        leftFrontLeg.xRot += f * -40.0F * (float) (Math.PI / 180.0);
        rightFrontLeg.xRot += f * -40.0F * (float) (Math.PI / 180.0);


    }
}
