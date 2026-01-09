package bikerboys.cuteanimalsport.mixin.accessors;

import net.minecraft.core.*;
import net.minecraft.world.entity.animal.wolf.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin(Wolf.class)
public interface WolfMixin {

    @Invoker("getVariant")
    Holder<WolfVariant> getWolfVariant();


}
