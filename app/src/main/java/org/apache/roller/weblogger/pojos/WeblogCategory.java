/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
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

package org.apache.roller.weblogger.pojos;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.roller.weblogger.WebloggerException;
import org.apache.roller.weblogger.business.WebloggerFactory;
import org.apache.roller.weblogger.business.WeblogEntryManager;
import org.apache.roller.weblogger.pojos.WeblogEntry.PubStatus;
import org.apache.roller.util.UUIDGenerator;


/**
 * Weblog Category.
 */
public class WeblogCategory implements Serializable, Comparable<WeblogCategory> {
    
    public static final long serialVersionUID = 1435782148712018954L;
    
    // attributes
    private String id = UUIDGenerator.generateUUID();
    private String name = null;
    private String description = null;
    private String image = null;
    private int position;

    // associations
    private Weblog weblog = null;

    public WeblogCategory() {
    }
    
    public WeblogCategory(
            Weblog weblog,
            String name,
            String description,
            String image) {
        
        this.name = name;
        this.description = description;
        this.image = image;
        
        this.weblog = weblog;
        weblog.getWeblogCategories().add(this);
        calculatePosition();
    }

    public void calculatePosition() {
        int size = weblog.getWeblogCategories().size();
        if (size == 1) {
            this.position = 0;
        } else {
            this.position = weblog.getWeblogCategories().get(size - 2).getPosition() + 1;
        }
    }

    //------------------------------------------------------- Good citizenship

    public String toString() {
        return "{" + getId() + ", " + getName() + "}";
    }
    
    @Override
    public boolean equals(Object other) {
        
        if (other == null) {
            return false;
        }
        
        if (other instanceof WeblogCategory) {
            WeblogCategory o = (WeblogCategory) other;
            return new EqualsBuilder()
                .append(getName(), o.getName())
                .append(getWeblog(), o.getWeblog())
                .isEquals();
        }        
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getName())
            .append(getWeblog())
            .toHashCode();
    }
    
    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(WeblogCategory other) {
        return getName().compareTo(other.getName());
    }

    /**
     * Database surrogate key.
     */
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    
    /**
     * The display name for this category.
     */
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    /**
     * A full description for this category.
     */
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * A 0-based position indicator for desired display order of that category.
     */
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * An image icon to represent this category.
     */
    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    /**
     * Get the weblog which owns this category.
     */
    public Weblog getWeblog() {
        return weblog;
    }
    
    public void setWeblog(Weblog weblog) {
        this.weblog = weblog;
    }

    /**
     * Retrieve all weblog entries in this category.
     *
     * @param publishedOnly True if desired to return only published entries
     * @return List of WeblogEntryData objects.
     * @throws WebloggerException
     */
    public List<WeblogEntry> retrieveWeblogEntries(boolean publishedOnly) throws WebloggerException {
        WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
        WeblogEntrySearchCriteria wesc = new WeblogEntrySearchCriteria();
        wesc.setWeblog(weblog);
        wesc.setCatName(this.getName());
        if (publishedOnly) {
            wesc.setStatus(PubStatus.PUBLISHED);
        }
        return wmgr.getWeblogEntries(wesc);
    }
    
    /**
     * Returns true if category is in use.
     */
    public boolean isInUse() {
        try {
            return WebloggerFactory.getWeblogger().getWeblogEntryManager().isWeblogCategoryInUse(this);
        } catch (WebloggerException e) {
            throw new RuntimeException(e);
        }
    }

}
