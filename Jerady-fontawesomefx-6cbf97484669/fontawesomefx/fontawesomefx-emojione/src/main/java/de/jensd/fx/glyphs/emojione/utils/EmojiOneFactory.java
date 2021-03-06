/**
 * Copyright (c) 2013-2016 Jens Deters http://www.jensd.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *
 */
package de.jensd.fx.glyphs.emojione.utils;

import de.jensd.fx.glyphs.GlyphsFactory;
import de.jensd.fx.glyphs.emojione.EmojiOneView;

/**
 *
 * @author Jens Deters
 */
public class EmojiOneFactory extends GlyphsFactory {

    private static EmojiOneFactory me;

    private EmojiOneFactory() {
        super(EmojiOneView.class);
    }

    public static EmojiOneFactory get() {
        if (me == null) {
            me = new EmojiOneFactory();
        }
        return me;
    }

}
