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
package org.spongepowered.api.entity;

import net.kyori.adventure.text.ComponentLike;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.registry.DefaultedRegistryValue;
import org.spongepowered.api.util.annotation.CatalogedBy;

/**
 * Describes a type of entity.
 */
@CatalogedBy(EntityTypes.class)
public interface EntityType<A extends Entity> extends DefaultedRegistryValue, ComponentLike {

    /**
     * If true {@link Entity entities} of this type will not be saved to disk.
     *
     * @return If the type is transient
     */
    boolean isTransient();

    /**
     * If true {@link Entity entities} of this type may be summoned naturally or via command.
     *
     * @return If the type is summonable
     */
    boolean isSummonable();

    /**
     * If true {@link Entity entities} of this type may be caught on fire.
     *
     * @return If the type is flammable
     */
    boolean isFlammable();

    /**
     * If true {@link Entity entities} of this type may spawn out of range from {@link ServerPlayer players}.
     *
     * @return If the type can spawn far away from a player
     */
    boolean canSpawnAwayFromPlayer();
}
