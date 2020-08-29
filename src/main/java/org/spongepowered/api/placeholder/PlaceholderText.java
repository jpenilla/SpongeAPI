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
package org.spongepowered.api.text.placeholder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextComponent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.util.ResettableBuilder;

/**
 * A {@link ComponentLike} that can be used in {@link Component} building methods
 * that represents a placeholder in text.
 *
 * <p>A {@link PlaceholderText} is the collection of a {@link PlaceholderParser}
 * along with contextual data in the supplied {@link PlaceholderContext},
 * enabling its use in a {@link TextComponent} object.</p>
 *
 * <p>Such placeholders will generally be built from tokenized strings, however
 * these objects make no assumption about the format of text templating. Such a
 * system can therefore be used by other templating engines without conforming
 * to a particular standard.</p>
 *
 * <p>The {@link PlaceholderContext} is fixed when this object is created, but
 * {@link PlaceholderParser#parse(PlaceholderContext)} is not called until
 * {@link #asComponent()} is called. Thus, any {@link TextComponent} object that is created
 * will reflect the time that the {@link TextComponent} object was requested, and not when
 * this object itself was created. It therefore follows that implementations must
 * not cache the result of {@link #asComponent()} unless it is known that the supplied
 * parser is <strong>not</strong> sensitive to the time of invocation.</p>
 */
public interface PlaceholderText extends ComponentLike {

    /**
     * Gets a builder for creating {@link PlaceholderText}.
     *
     * @return A {@link Builder}
     */
    static PlaceholderText.Builder builder() {
        return Sponge.getRegistry().getBuilderRegistry().provideBuilder(Builder.class);
    }

    /**
     * Gets the {@link PlaceholderContext} that is to be supplied to the
     * {@link PlaceholderParser} to create the {@link TextComponent}.
     *
     * @return The {@link PlaceholderContext}
     */
    PlaceholderContext getContext();

    /**
     * Gets the {@link PlaceholderParser} that handles this placeholder.
     *
     * @return The {@link PlaceholderParser}
     */
    PlaceholderParser getParser();

    /**
     * Creates a {@link TextComponent} from the supplied {@link PlaceholderParser} in the
     * context of the supplied {@link PlaceholderContext}.
     *
     * <p>This will always return a {@link TextComponent} object, however, if the parser
     * could not handle the provided context, this will be {@link TextComponent#empty()}.
     * </p>
     *
     * @return The parsed {@link TextComponent}
     */
    @Override
    default @NonNull TextComponent asComponent() {
        return getParser().parse(getContext());
    }

    /**
     * A builder for {@link PlaceholderText} objects.
     */
    interface Builder extends ResettableBuilder<PlaceholderText, Builder> {

        /**
         * Sets the token that represents a {@link PlaceholderParser} for use
         * in this {@link PlaceholderText}.
         *
         * @param parser The {@link PlaceholderParser} to use
         * @return This, for chaining
         */
        Builder setParser(PlaceholderParser parser);

        /**
         * Sets the {@link PlaceholderContext} that will be provided to the
         * {@link PlaceholderParser} to create the {@link TextComponent} when
         * {@link ComponentLike#asComponent()} ()} is called.
         *
         * @param context The {@link PlaceholderContext} to use.
         * @return This, for chaining
         */
        Builder setContext(PlaceholderContext context);

        /**
         * Builds and returns the placeholder.
         *
         * @return The appropriate {@link PlaceholderText}
         * @throws IllegalStateException if the builder has not been completed,
         *  or the associated {@link PlaceholderParser} could not validate the
         *  built {@link PlaceholderText}, if applicable.
         */
        PlaceholderText build() throws IllegalStateException;

    }

}