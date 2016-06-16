package org.fountainmc.forge.mixin.entity.player;

import java.util.UUID;

import javax.annotation.Nonnull;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;
import org.fountainmc.api.entity.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(net.minecraft.entity.player.EntityPlayerMP.class)
public abstract class MixinEntityPlayerMP implements Player, ICommandSender {

    @Shadow
    public abstract String shadow$getName();

    @Shadow
    public abstract UUID shadow$getUniqueID();

    @Nonnull @Override public String getName() {
        return this.shadow$getName();
    }

    @Nonnull @Override public UUID getUUID() {
        return this.shadow$getUniqueID();
    }

    @Override public void sendMessage(String s) {
        sendMessages(s);
    }

    @Override public void sendMessages(String... messages) {
        for (String message : messages) {
            this.addChatMessage(new TextComponentString(message));
        }
    }
}
