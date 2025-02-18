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
package org.spongepowered.api.effect.potion;

import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.registry.DefaultedRegistryReference;
import org.spongepowered.api.registry.RegistryKey;
import org.spongepowered.api.registry.RegistryScope;
import org.spongepowered.api.registry.RegistryScopes;
import org.spongepowered.api.registry.RegistryTypes;

/**
 * An enumeration of all possible {@link PotionEffectType}s in vanilla Minecraft.
 */
@SuppressWarnings("unused")
@RegistryScopes(scopes = RegistryScope.GAME)
public final class PotionEffectTypes {

    // @formatter:off

    // SORTFIELDS:ON

    public static final DefaultedRegistryReference<PotionEffectType> ABSORPTION = PotionEffectTypes.key(ResourceKey.minecraft("absorption"));

    public static final DefaultedRegistryReference<PotionEffectType> BAD_OMEN = PotionEffectTypes.key(ResourceKey.minecraft("bad_omen"));

    public static final DefaultedRegistryReference<PotionEffectType> BLINDNESS = PotionEffectTypes.key(ResourceKey.minecraft("blindness"));

    public static final DefaultedRegistryReference<PotionEffectType> CONDUIT_POWER = PotionEffectTypes.key(ResourceKey.minecraft("conduit_power"));

    public static final DefaultedRegistryReference<PotionEffectType> DOLPHINS_GRACE = PotionEffectTypes.key(ResourceKey.minecraft("dolphins_grace"));

    public static final DefaultedRegistryReference<PotionEffectType> FIRE_RESISTANCE = PotionEffectTypes.key(ResourceKey.minecraft("fire_resistance"));

    public static final DefaultedRegistryReference<PotionEffectType> GLOWING = PotionEffectTypes.key(ResourceKey.minecraft("glowing"));

    public static final DefaultedRegistryReference<PotionEffectType> HASTE = PotionEffectTypes.key(ResourceKey.minecraft("haste"));

    public static final DefaultedRegistryReference<PotionEffectType> HEALTH_BOOST = PotionEffectTypes.key(ResourceKey.minecraft("health_boost"));

    public static final DefaultedRegistryReference<PotionEffectType> HERO_OF_THE_VILLAGE = PotionEffectTypes.key(ResourceKey.minecraft("hero_of_the_village"));

    public static final DefaultedRegistryReference<PotionEffectType> HUNGER = PotionEffectTypes.key(ResourceKey.minecraft("hunger"));

    public static final DefaultedRegistryReference<PotionEffectType> INSTANT_DAMAGE = PotionEffectTypes.key(ResourceKey.minecraft("instant_damage"));

    public static final DefaultedRegistryReference<PotionEffectType> INSTANT_HEALTH = PotionEffectTypes.key(ResourceKey.minecraft("instant_health"));

    public static final DefaultedRegistryReference<PotionEffectType> INVISIBILITY = PotionEffectTypes.key(ResourceKey.minecraft("invisibility"));

    public static final DefaultedRegistryReference<PotionEffectType> JUMP_BOOST = PotionEffectTypes.key(ResourceKey.minecraft("jump_boost"));

    public static final DefaultedRegistryReference<PotionEffectType> LEVITATION = PotionEffectTypes.key(ResourceKey.minecraft("levitation"));

    public static final DefaultedRegistryReference<PotionEffectType> LUCK = PotionEffectTypes.key(ResourceKey.minecraft("luck"));

    public static final DefaultedRegistryReference<PotionEffectType> MINING_FATIGUE = PotionEffectTypes.key(ResourceKey.minecraft("mining_fatigue"));

    public static final DefaultedRegistryReference<PotionEffectType> NAUSEA = PotionEffectTypes.key(ResourceKey.minecraft("nausea"));

    public static final DefaultedRegistryReference<PotionEffectType> NIGHT_VISION = PotionEffectTypes.key(ResourceKey.minecraft("night_vision"));

    public static final DefaultedRegistryReference<PotionEffectType> POISON = PotionEffectTypes.key(ResourceKey.minecraft("poison"));

    public static final DefaultedRegistryReference<PotionEffectType> REGENERATION = PotionEffectTypes.key(ResourceKey.minecraft("regeneration"));

    public static final DefaultedRegistryReference<PotionEffectType> RESISTANCE = PotionEffectTypes.key(ResourceKey.minecraft("resistance"));

    public static final DefaultedRegistryReference<PotionEffectType> SATURATION = PotionEffectTypes.key(ResourceKey.minecraft("saturation"));

    public static final DefaultedRegistryReference<PotionEffectType> SLOWNESS = PotionEffectTypes.key(ResourceKey.minecraft("slowness"));

    public static final DefaultedRegistryReference<PotionEffectType> SLOW_FALLING = PotionEffectTypes.key(ResourceKey.minecraft("slow_falling"));

    public static final DefaultedRegistryReference<PotionEffectType> SPEED = PotionEffectTypes.key(ResourceKey.minecraft("speed"));

    public static final DefaultedRegistryReference<PotionEffectType> STRENGTH = PotionEffectTypes.key(ResourceKey.minecraft("strength"));

    public static final DefaultedRegistryReference<PotionEffectType> UNLUCK = PotionEffectTypes.key(ResourceKey.minecraft("unluck"));

    public static final DefaultedRegistryReference<PotionEffectType> WATER_BREATHING = PotionEffectTypes.key(ResourceKey.minecraft("water_breathing"));

    public static final DefaultedRegistryReference<PotionEffectType> WEAKNESS = PotionEffectTypes.key(ResourceKey.minecraft("weakness"));

    public static final DefaultedRegistryReference<PotionEffectType> WITHER = PotionEffectTypes.key(ResourceKey.minecraft("wither"));

    // SORTFIELDS:OFF

    // @formatter:on

    private PotionEffectTypes() {
    }

    private static DefaultedRegistryReference<PotionEffectType> key(final ResourceKey location) {
        return RegistryKey.of(RegistryTypes.POTION_EFFECT_TYPE, location).asDefaultedReference(() -> Sponge.game().registries());
    }
}
