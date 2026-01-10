package bikerboys.cuteanimalsport.misc;

import net.minecraft.world.entity.*;

public interface RabbitAnimationAccess {
    AnimationState yourmod$getHopAnimationState();
    AnimationState yourmod$getIdleHeadTiltAnimationState();
    int yourmod$getIdleAnimationTimeout();
    void yourmod$setIdleAnimationTimeout(int value);
    void yourmod$decrementIdleAnimationTimeout();
}
