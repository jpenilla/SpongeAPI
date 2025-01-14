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
package org.spongepowered.api.advancement;

import org.spongepowered.math.vector.Vector2d;

/**
 * Represents a element in the {@link TreeLayout}.
 */
public interface TreeLayoutElement {

    /**
     * Gets the {@link Advancement}.
     *
     * @return The advancement
     */
    Advancement advancement();

    /**
     * Gets the position of the {@link Advancement}.
     *
     * @return The position
     */
    Vector2d position();

    /**
     * Sets the position of the {@link Advancement}.
     *
     * @param position The position
     */
    default void setPosition(Vector2d position) {
        this.setPosition(position.getX(), position.getY());
    }

    /**
     * Sets the position of the {@link Advancement}.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    void setPosition(double x, double y);

}
