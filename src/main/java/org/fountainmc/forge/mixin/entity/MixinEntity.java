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

package org.fountainmc.forge.mixin.entity;

import net.minecraft.util.math.BlockPos;
import org.fountainmc.api.entity.Entity;
import org.fountainmc.api.world.Location;
import org.fountainmc.api.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(net.minecraft.entity.Entity.class)
public abstract class MixinEntity implements Entity {

    @Shadow public boolean onGround;

    @Shadow
    public abstract BlockPos getPosition();

    @Shadow
    public abstract net.minecraft.world.World getEntityWorld();

    @Override
    public Location getLocation() {
        return new Location((World) this.getEntityWorld(),
                this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ());
    }

    @Override
    public boolean isOnGround() {
        return this.onGround;
    }
}
