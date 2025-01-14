/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
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
package org.spongepowered.api.event.entity;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.util.annotation.eventgen.GenerateFactoryMethod;

/**
 * Called when an {@link Entity} has been killed and is being "harvested" (drops/etc). Happens
 * after {@link DestructEntityEvent}.
 */
@GenerateFactoryMethod
public interface HarvestEntityEvent extends Event, Cancellable {

    /**
     * Gets the {@link Entity} being harvested
     *
     * @return The entity
     */
    Entity entity();

    /**
     * An event where the target is a {@link Player}. Usually this will
     * have additional information regarding whether the player
     * {@link #keepsInventory()} and their current experience.
     */
    interface TargetPlayer extends HarvestEntityEvent, ChangeEntityExperienceEvent {

        @Override
        ServerPlayer entity();

        /**
         * Gets whether the player keeps their inventory on death.
         *
         * @return Whether the player keeps their inventory on death
         */
        boolean keepsInventory();

        /**
         * Sets if the player keeps their inventory on death.
         *
         * @param keepInventory Whether the player should keep inventory
         */
        void setKeepsInventory(boolean keepInventory);

        /**
         * Gets whether the player keeps all of their EXP on death.
         *
         * @return Whether the player keeps all of their EXP on death
         */
        boolean keepsLevel();

        /**
         * Sets if the player keeps all of their EXP on death.
         *
         * @param keepLevel Whether the player will keep experience on death
         */
        void setKeepsLevel(boolean keepLevel);

        /**
         * Gets the new level the player will have after death.
         *
         * @return The new level after death
         */
        int level();

        /**
         * Sets the new level the player will have after death.
         *
         * @param level The new level after death
         */
        void setLevel(int level);
    }
}
