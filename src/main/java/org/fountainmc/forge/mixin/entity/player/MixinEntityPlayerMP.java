/*
 * This file is part of FountainForge, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2016 Fountain
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.fountainmc.forge.mixin.entity.player;

import java.util.UUID;

import javax.annotation.Nonnull;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;
import org.fountainmc.api.entity.Player;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(net.minecraft.entity.player.EntityPlayerMP.class)
public abstract class MixinEntityPlayerMP implements Player, ICommandSender {

    @Shadow
    public abstract String shadow$getName();

    @Shadow
    public abstract UUID shadow$getUniqueID();

    @Nonnull
    @Override
    public String getName() {
        return this.shadow$getName();
    }

    @Intrinsic
    @Nonnull
    @Override
    public UUID getUUID() {
        return this.shadow$getUniqueID();
    }

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
