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
package org.spongepowered.api.world.chunk;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.entity.BlockEntity;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.world.ProtoWorld;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.biome.BiomeType;

import java.util.function.Supplier;

public final class ChunkStates {

    // SORTFIELDS:ON

    /**
     * A {@link ProtoChunk} that is having its {@link BiomeType biomes}
     * assinged.
     */
    public static final Supplier<ChunkState> BIOMES = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ChunkState.class, "biomes");

    /**
     * A {@link ProtoChunk} that is being "carved out" for general terrain
     * features that require things like "caves" or "canyons".
     */
    public static final Supplier<ChunkState> CARVERS = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ChunkState.class, "carvers");

    /**
     * A {@link ProtoChunk} state that is being populated by world generation,
     * usually provided by {@link BiomeType}s.
     */
    public static final Supplier<ChunkState> DECORATED = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ChunkState.class, "decorated");

    /**
     * Identifies a {@link ProtoChunk} that is considered empty. The method
     * {@link ProtoChunk#isEmpty()} would return {@code true}. Identifies the
     * chunk has nothing contained within it, but can be used as a dummy chunk
     * in some regards for world generation.
     */
    public static final Supplier<ChunkState> EMPTY = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ChunkState.class, "empty");

    /**
     * A {@link ProtoChunk} has been carved out, and is now being decorated with
     * features, such as leaves and tall grass.
     */
    public static final Supplier<ChunkState> FEATURES = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ChunkState.class, "features");

    /**
     * State for a {@link ProtoChunk} marking it being used by a world, and not
     * in the process of either world generation, or deserialization from
     * storage. Only {@link Chunk}s should provide this state, other
     * {@link ProtoChunk}s would be invalid with this state.
     */
    public static final Supplier<ChunkState> FULL = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ChunkState.class, "full");

    /**
     * A {@link ProtoChunk} state that is "cleaning" up remnant objects of a
     * chunk in process of world generation. Generally, height maps are being
     * calculated at this point as entity spawning can affect block placement.
     */
    public static final Supplier<ChunkState> HEIGHTMAPS = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ChunkState.class, "heightmaps");

    /**
     * A {@link ProtoChunk} state that is being "carved" with liquid cave
     * features, such as underwater ravines, underwater caves, etc.
     */
    public static final Supplier<ChunkState> LIQUID_CARVERS = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ChunkState.class, "liquid_carvers");

    /**
     * A {@link ProtoChunk} state that has yet been processed with lighting in
     * respects to the {@link ProtoWorld} that contains it. This is the second
     * to last step in the world generation pipeline for a chunk to be marked
     * as ready for being added to a {@link World}.
     */
    public static final Supplier<ChunkState> LIGHT = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ChunkState.class, "light");

    /**
     * A {@link ProtoChunk} where the {@link BlockState block states} are being
     * set and structure locations are set.
     */
    public static final Supplier<ChunkState> NOISE = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ChunkState.class, "noise");

    /**
     * A {@link ProtoChunk} state that is being used for entity spawning.
     * Generally requires that the neighboring chunks are adequately populated,
     * and requires that this chunk has proper lighting, for mob placement
     * logic.
     */
    public static final Supplier<ChunkState> SPAWN = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ChunkState.class, "spawn");

    /**
     * A {@link ProtoChunk} where the structures to be placed in the chunk are
     * being determined and primed for placement.
     */
    public static final Supplier<ChunkState> STRUCTURE_STARTS = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ChunkState.class, "structure_starts");

    /**
     * A {@link ProtoChunk} where final validity checks are being performed on
     * structures that are primed to be placed in the chunk.
     */
    public static final Supplier<ChunkState> STRUCTURE_REFERENCES = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ChunkState.class, "structure_references");

    /**
     * A {@link ProtoChunk} that is at this state means that it is being
     * generated with a "base" layer of terrain.
     *
     * <p>The chunk should not have any {@link Entity} instances or
     * {@link BlockEntity} instances and may have a valid {@link ProtoWorld}
     * used for world generation.</p>
     */
    public static final Supplier<ChunkState> SURFACE = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ChunkState.class, "surface");

    // SORTFIELDS:OFF

    private ChunkStates() {
        throw new AssertionError("You should not be attempting to instantiate this class.");
    }
}
