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

package org.fountainmc.forge.mixin.server;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import net.minecraft.command.ICommandSender;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.server.management.PlayerList;
import org.fountainmc.api.Material;
import org.fountainmc.api.Server;
import org.fountainmc.api.command.CommandManager;
import org.fountainmc.api.plugin.PluginManager;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(net.minecraft.server.MinecraftServer.class)
@Implements(@Interface(iface = Server.class, prefix = "server$"))
public abstract class MixinMinecraftServer implements Server, ICommandSender {

    @Shadow
    public abstract String getMinecraftVersion();

    @Shadow
    public abstract PlayerList getPlayerList();

    @Shadow
    public abstract String getMOTD();

    @Shadow
    public abstract String getServerOwner();

    @Shadow
    public abstract String getServerHostname();

    @Shadow
    public abstract int getServerPort();

    private final PluginManager pluginManager = new PluginManager();
    private final CommandManager commandManager = new CommandManager();

    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public ImmutableList<String> getLaunchArguments() {
        Map<String, String> args = (Map<String, String>) Launch.blackboard.get("launchArgs");
        List<String> convertList = new ArrayList<String>();
        for (Map.Entry<String, String> arg : args.entrySet()) {
            convertList.add(arg.getKey());
            convertList.add(arg.getValue());
        }
        return ImmutableList.copyOf(convertList);
    }

    @Override
    public Material getMaterial(String s) {
        return null;
    }

    @Intrinsic
    public String server$getName() {
        return this.getName();
    }

    @Override
    public String getVersion() {
        return this.getMinecraftVersion();
    }

    @Override
    public String getMotd() {
        return this.getMOTD();
    }

    @Override
    public int getMaxPlayers() {
        return this.getPlayerList().getMaxPlayers();
    }

    @Override
    public String getOwner() {
        return this.getServerOwner();
    }

    @Override
    public InetSocketAddress getAddress() {
        return new InetSocketAddress(this.getServerHostname(), this.getServerPort());
    }

}
