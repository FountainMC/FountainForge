package org.fountainmc.forge.mixin.entity.player;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;
import org.fountainmc.api.entity.Player;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(net.minecraft.entity.player.EntityPlayerMP.class)
public abstract class MixinEntityPlayerMP implements Player, ICommandSender {

    @Override
    public void sendMessage(String s) {
        sendMessages(s);
    }

    @Override
    public void sendMessages(String... messages) {
        for (String message : messages) {
            this.addChatMessage(new TextComponentString(message));
        }
    }

}
