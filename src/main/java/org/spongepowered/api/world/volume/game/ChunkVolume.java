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
package org.spongepowered.api.world.volume.game;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.api.world.Locatable;
import org.spongepowered.api.world.ProtoWorld;
import org.spongepowered.api.world.chunk.ProtoChunk;
import org.spongepowered.api.world.server.ServerLocation;
import org.spongepowered.api.world.volume.block.BlockVolume;
import org.spongepowered.math.vector.Vector3i;

import java.util.Objects;

/**
 * Presents a volume of {@link ProtoChunk}s that can exist
 * without a {@link ProtoWorld} volume.
 */
public interface ChunkVolume extends BlockVolume {

    /**
     * Gets the loaded chunk at the given chunk coordinate position. The position
     * is the same as {@link ProtoChunk#chunkPosition()}. The difference
     * between a block placed within a {@link ProtoWorld} is different from a
     * {@link ProtoChunk}'s position, and therefore care should be taken when
     * requesting a chunk. It is not guaranteed that the returned {@link ProtoChunk}
     * is {@link ProtoChunk#isEmpty() empty} or not, nor the {@link ProtoChunk#state() state}
     * of the chunk.
     *
     * <p>In Vanilla, the y coordinate will always be 0.</p>
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate
     * @return The chunk, may be empty
     */
    ProtoChunk<@NonNull ?> chunk(int x, int y, int z);

    /**
     * Gets the loaded chunk at the given chunk coordinate position. The position
     * is the same as {@link ProtoChunk#chunkPosition()}. The difference
     * between a block placed within a {@link ProtoWorld} is different from a
     * {@link ProtoChunk}'s position, and therefore care should be taken when
     * requesting a chunk. It is not guaranteed that the returned {@link ProtoChunk}
     * is {@link ProtoChunk#isEmpty() empty} or not, nor the {@link ProtoChunk#state() state}
     * of the chunk.
     *
     * @param chunkPosition The position
     * @return The chunk, if available
     */
    default ProtoChunk<@NonNull ?> chunk(final Vector3i chunkPosition) {
        Objects.requireNonNull(chunkPosition, "chunkPosition");
        return this.chunk(chunkPosition.getX(), chunkPosition.getY(), chunkPosition.getZ());
    }

    /**
     * Gets the loaded chunk at the given block coordinate position.
     *
     * @param blockPosition The position
     * @return The chunk, if available
     */
    default ProtoChunk<@NonNull ?> chunkAtBlock(final Vector3i blockPosition) {
        Objects.requireNonNull(blockPosition, "blockPosition");
        return this.chunkAtBlock(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
    }

    /**
     * Gets the loaded chunk at the given chunk coordinate position. The position
     * is the block position relative to the {@link ProtoChunk#chunkPosition()},
     * and therefor is going to return a different chunk from {@link #chunk(Vector3i)}.
     * This is more usable from {@link ServerLocation}s or a {@link Locatable} that returns
     * a {@link Vector3i position} in relation to a {@link ProtoWorld}.
     *
     * @param bx The x coordinate
     * @param by The y coordinate
     * @param bz The z coordinate
     * @return The chunk, if available
     */
    ProtoChunk<@NonNull ?> chunkAtBlock(final int bx, final int by, final int bz);

    boolean isChunkLoaded(int x, int y, int z, boolean allowEmpty);

    default boolean isChunkLoaded(final Vector3i position, final boolean allowEmpty) {
        Objects.requireNonNull(position, "position");
        return this.isChunkLoaded(position.getX(), position.getY(), position.getZ(), allowEmpty);
    }

    boolean hasChunk(int x, int y, int z);

    boolean hasChunk(Vector3i position);
}
