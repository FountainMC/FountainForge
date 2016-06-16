package org.fountainmc.forge.mixin.entity;

import org.fountainmc.api.entity.EntityLiving;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(net.minecraft.entity.EntityLivingBase.class)
public abstract class MixinEntityLivingBase implements EntityLiving {

    @Shadow
    public abstract float shadow$getHealth();

    @Shadow
    public abstract void shadow$setHealth(float i);

    @Override public int getHealth() {
        return Math.round(this.shadow$getHealth());
    }

    @Override public void setHealth(int i) {
        this.shadow$setHealth((float) i);
    }
}
