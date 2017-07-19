/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package org.apache.roller.selenium.editor;

import org.apache.roller.selenium.AbstractRollerPage;
import org.openqa.selenium.WebDriver;

import java.lang.String;

/**
 * Base class for the new/edit entry pages
 */
public abstract class AbstractEntryPage extends AbstractRollerPage {

    public void setTitle(String value) {
        setFieldValue("entry_bean_title", value);
    }

    public void setText(String value) {
        setFieldValue("edit_content", value);
    }

    public void setSummary(String value) {
        setFieldValue("entry_bean_summary", value);
    }

    public EntryEditPage postBlogEntry() {
        clickById("entry_%{#mainAction}!publish");
        return new EntryEditPage(driver);
    }
}
