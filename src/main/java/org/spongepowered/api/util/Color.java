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
package org.spongepowered.api.util;

import net.kyori.adventure.util.RGBLike;
import org.checkerframework.common.value.qual.IntRange;
import org.spongepowered.api.data.persistence.AbstractDataBuilder;
import org.spongepowered.api.data.persistence.DataContainer;
import org.spongepowered.api.data.persistence.DataSerializable;
import org.spongepowered.api.data.persistence.DataView;
import org.spongepowered.api.data.persistence.InvalidDataException;
import org.spongepowered.api.data.persistence.Queries;
import org.spongepowered.api.data.type.DyeColor;
import org.spongepowered.math.vector.Vector3d;
import org.spongepowered.math.vector.Vector3f;
import org.spongepowered.math.vector.Vector3i;

import java.util.Objects;
import java.util.Optional;

public final class Color implements DataSerializable, RGBLike {

    private static final int MASK = 0xFF;

    public static final Color BLACK = Color.ofRgb(0x000000);

    public static final Color GRAY = Color.ofRgb(0x808080);

    public static final Color WHITE = Color.ofRgb(0xFFFFFF);

    public static final Color BLUE = Color.ofRgb(0x0000FF);

    public static final Color GREEN = Color.ofRgb(0x008000);

    public static final Color LIME = Color.ofRgb(0x00FF00);

    public static final Color RED = Color.ofRgb(0xFF0000);

    public static final Color YELLOW = Color.ofRgb(0xFFFF00);

    public static final Color MAGENTA = Color.ofRgb(0xFF00FF);

    public static final Color PURPLE = Color.ofRgb(0xAA00FF);

    public static final Color DARK_CYAN = Color.ofRgb(0x008B8B);

    public static final Color DARK_GREEN = Color.ofRgb(0x006400);

    public static final Color DARK_MAGENTA = Color.ofRgb(0x8B008B);

    public static final Color CYAN = Color.ofRgb(0x00FFFF);

    public static final Color NAVY = Color.ofRgb(0x000080);

    public static final Color PINK = Color.ofRgb(0xFF00AA);

    /**
     * Gets a new {@link Color} based on the hexadecimal value
     * for a combined {@code red}, {@code green}, and {@code blue}
     * color. Note that colors do not utilize an alpha modifier
     *
     * @param hex The hexadecimal value of the color
     * @return The color object
     */
    public static Color ofRgb(final int hex) {
        return Color.ofRgb((hex >> 0x10) & Color.MASK, (hex >> 0x8) & Color.MASK, hex & Color.MASK);
    }

    /**
     * Gets a new {@link Color} based on the hexadecimal value
     * for a combined {@code red}, {@code green}, and {@code blue}
     * color. Note that colors do not utilize an alpha modifier
     *
     * @param red The red value
     * @param green The green value
     * @param blue The blue value
     * @return The color object
     */
    public static Color ofRgb(final int red, final int green, final int blue) {
        return new Color(red, green, blue);
    }

    /**
     * Converts the provided {@link java.awt.Color} object into a valid
     * {@link Color} object to be used throughout the API.
     *
     * @param color The java color object
     * @return The converted color object
     */
    public static Color of(final java.awt.Color color) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Converts the provided {@link Vector3i} into a {@link Color} object.
     *
     * @param vector3i The vector of three integers representing color
     * @return The color object
     */
    public static Color of(final Vector3i vector3i) {
        return new Color(vector3i.getX(), vector3i.getY(), vector3i.getZ());
    }

    /**
     * converts the provided {@link Vector3f} into a {@link Color} object.
     *
     * @param vector3f The vector of three floats representing color
     * @return The color object
     */
    public static Color of(final Vector3f vector3f) {
        return new Color(Math.round(vector3f.getX()), Math.round(vector3f.getY()), Math.round(vector3f.getZ()));
    }

    /**
     * converts the provided {@link Vector3d} into a {@link Color} object.
     *
     * @param vector3d The vector of three doubles representing color
     * @return The color object
     */
    public static Color of(final Vector3d vector3d) {
        return new Color((int) Math.round(vector3d.getX()), (int) Math.round(vector3d.getY()), (int) Math.round(vector3d.getZ()));
    }

    /**
     * Creates a new {@link Color} combining the provided {@link DyeColor}
     * objects. Since {@link DyeColor}s can be converted into {@link Color}
     * objects themselves, their summation and average is taken into effect
     * to properly mix the colors together.
     *
     * @param colors The colors to mix
     * @return The final output mixed color
     */
    public static Color mixDyeColors(final DyeColor... colors) {
        Objects.requireNonNull(colors, "No nulls allowed!");
        final Color[] actualColors = new Color[colors.length];
        for (int i = 0; i < colors.length; i++) {
            actualColors[i] = colors[i].color();
        }

        return Color.mixColors(actualColors);
    }

    /**
     * Creates a new {@link Color} combining the provided {@link Color}
     * objects, their summation and average is taken into effect
     * to properly mix the colors together.
     *
     * @param colors The colors to mix
     * @return The final output mixed color
     */
    public static Color mixColors(final Color... colors) {
        Objects.requireNonNull(colors, "No null colors allowed!");
        if (colors.length <= 0) {
            throw new IllegalArgumentException("Cannot have an empty color array!");
        }
        if (colors.length == 1) {
            return colors[0];
        }
        int red = colors[0].red();
        int green = colors[0].green();
        int blue = colors[0].blue();
        for (int i = 1; i < colors.length; i++) {
            Objects.requireNonNull(colors[i], "No null colors allowed!");
            red += colors[i].red();
            green += colors[i].green();
            blue += colors[i].blue();
        }
        final int averageRed = Math.round((float) red / colors.length);
        final int averageGreen = Math.round((float) green / colors.length);
        final int averageBlue = Math.round((float) blue / colors.length);
        return Color.ofRgb(averageRed, averageGreen, averageBlue);
    }

    private final byte red;
    private final byte green;
    private final byte blue;
    private final int rgb;

    private Color(final int red, final int green, final int blue) {
        this.red = (byte) (red & Color.MASK);
        this.green = (byte) (green & Color.MASK);
        this.blue = (byte) (blue & Color.MASK);
        this.rgb = ((this.red & Color.MASK) << 0x10)
                   | ((this.green & Color.MASK) << 0x8)
                   | ((this.blue & Color.MASK) << 0);
    }

    /**
     * Gets the {@code red} value of this {@link Color}.
     *
     * @return The red value
     */
    public @IntRange(from = 0x0, to = 0xff) int red() {
        return Color.MASK & this.red;
    }

    /**
     * Creates a new {@link Color} by using the provided
     * {@code red} color, while retaining the current {@link #green()}
     * and {@link #blue()} values.
     *
     * @param red The red value to use
     * @return The new color object
     */
    public Color withRed(final int red) {
        return Color.ofRgb(red, this.green(), this.blue());
    }

    /**
     * Gets the {@code red} value of this {@link Color}.
     *
     * @return The red value
     */
    @Override
    public @IntRange(from = 0x0, to = 0xff) int green() {
        return Color.MASK & this.green;
    }

    /**
     * Creates a new {@link Color} by using the provided
     * {@code green} color, while retaining the current {@link #red()}
     * and {@link #blue()} values.
     *
     * @param green The green value to use
     * @return The new color object
     */
    public Color withGreen(final int green) {
        return Color.ofRgb(this.red(), green, this.blue());
    }

    /**
     * Gets the current {@code blue} value of this {@link Color}.
     *
     * @return The blue value
     */
    @Override
    public @IntRange(from = 0x0, to = 0xff) int blue() {
        return Color.MASK & this.blue;
    }

    /**
     * Creates a new {@link Color} by using the provided
     * {@code blue} color, while retaining the current {@link #green()}
     * and {@link #red()} values.
     *
     * @param blue The blue value to use
     * @return The new color object
     */
    public Color withBlue(final int blue) {
        return Color.ofRgb(this.red(), this.green(), blue);
    }

    /**
     * Converts this {@link Color} into a {@link java.awt.Color} object for use
     * in other APIs.
     *
     * @return The java awt color object
     */
    public java.awt.Color asJavaColor() {
        return new java.awt.Color(this.red(), this.green(), this.blue());
    }

    /**
     * Gets the {@code red green blue} representation of this color in
     * a "hexadecimal" format.
     *
     * @return The current color value in a hexadecimal format
     */
    public int rgb() {
        return this.rgb;
    }

    /**
     * Creates a new color with the provided {@code Colors}.
     *
     * @param colors The provided colors to mix
     * @return The new color
     */
    public Color mixWithColors(final Color... colors) {
        final Color[] newColorArray = new Color[colors.length + 1];
        newColorArray[0] = this;
        System.arraycopy(colors, 0, newColorArray, 1, colors.length);
        return Color.mixColors(newColorArray);
    }

    /**
     * Similar to {@link #mixWithColors(Color...)}, mixes the
     * provided {@link DyeColor}s with this {@link Color}.
     *
     * @param dyeColors The dye colors to mix
     * @return The new color
     */
    public Color mixWithDyes(final DyeColor... dyeColors) {
        final Color[] newColorArray = new Color[dyeColors.length + 1];
        newColorArray[0] = this;
        for (int i = 0; i < dyeColors.length; i++) {
            newColorArray[i + 1] = dyeColors[i].color();
        }
        return Color.mixColors(newColorArray);
    }

    @Override
    public int contentVersion() {
        return 1;
    }

    @Override
    public DataContainer toContainer() {
        return DataContainer.createNew()
            .set(Queries.CONTENT_VERSION, this.contentVersion())
            .set(Queries.COLOR_RED, this.red())
            .set(Queries.COLOR_GREEN, this.green())
            .set(Queries.COLOR_BLUE, this.blue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(Color.class, this.red, this.green, this.blue);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final Color other = (Color) obj;
        return this.red == other.red && this.green == other.green && this.blue == other.blue;
    }

    @Override
    public String toString() {
        return com.google.common.base.MoreObjects.toStringHelper(this)
            .add("red", this.red())
            .add("green", this.green())
            .add("blue", this.blue())
            .toString();
    }

    public static final class Builder extends AbstractDataBuilder<Color> {

        /**
         * Creates a new {@link Builder} for building {@link Color} objects, either
         * from {@link DataView}s, or otherwise.
         */
        public Builder() {
            super(Color.class, 1);
        }

        @Override
        protected Optional<Color> buildContent(final DataView container) throws InvalidDataException {
            if (!container.contains(Queries.COLOR_RED, Queries.COLOR_GREEN, Queries.COLOR_BLUE)) {
                return Optional.empty();
            }
            try {
                final int red = container.getInt(Queries.COLOR_RED).get();
                final int green = container.getInt(Queries.COLOR_GREEN).get();
                final int blue = container.getInt(Queries.COLOR_BLUE).get();
                return Optional.of(Color.ofRgb(red, green, blue));
            } catch (final Exception e) {
                throw new InvalidDataException("Could not parse some data.", e);
            }
        }
    }
}
