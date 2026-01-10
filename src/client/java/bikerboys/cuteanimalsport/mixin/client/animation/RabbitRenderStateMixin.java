package bikerboys.cuteanimalsport.mixin.client.animation;

import bikerboys.cuteanimalsport.misc.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.world.entity.*;
import org.spongepowered.asm.mixin.*;

@Mixin(RabbitRenderState.class)
public class RabbitRenderStateMixin implements RabbitRenderStateAccess {

    @Unique
    private final AnimationState yourmod$hopAnimationState = new AnimationState();

    @Unique
    private final AnimationState yourmod$idleHeadTiltAnimationState = new AnimationState();

    @Override
    public AnimationState yourmod$getHopAnimationState() {
        return this.yourmod$hopAnimationState;
    }

    @Override
    public AnimationState yourmod$getIdleHeadTiltAnimationState() {
        return this.yourmod$idleHeadTiltAnimationState;
    }
}
