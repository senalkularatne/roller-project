/*
 * Copyright 2004 Sun Microsystems, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.apache.roller.planet.util.rome;

import org.jdom2.Element;

import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.io.impl.DateParser;
import com.rometools.rome.io.impl.RSS091UserlandParser;
import java.util.Locale;

/**
 */
public class PlanetRSS091NParser extends RSS091UserlandParser {

    public PlanetRSS091NParser() {
        this("rss_0.91N");
    }

    protected PlanetRSS091NParser(String type) {
        super(type);
    }

    @Override
    protected Item parseItem(Element rssRoot,Element eItem, Locale locale) {
        Item item = super.parseItem(rssRoot, eItem, locale);
        Element e = eItem.getChild("pubDate",getRSSNamespace());
        if (e!=null) {
            item.setPubDate(DateParser.parseRFC822(e.getText(), locale));
        }
        return item;
    }
}
