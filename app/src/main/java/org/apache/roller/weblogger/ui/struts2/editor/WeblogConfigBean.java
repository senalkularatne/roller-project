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

package org.apache.roller.weblogger.ui.struts2.editor;

import org.apache.commons.lang.StringUtils;
import org.apache.roller.weblogger.pojos.Weblog;


/**
 * Bean used to manage data submitted to WeblogConfig action.
 */
public class WeblogConfigBean {
    
    private String handle = null;
    private String name = null;
    private String tagline = null;
    private boolean enableBloggerApi = false;
    private String editorPage = null;
    private String blacklist = null;
    private boolean allowComments = false;
    private boolean defaultAllowComments = false;
    private String defaultCommentDays = "0";
    private boolean moderateComments = false;
    private boolean emailComments = false;
    private String emailAddress = null;
    private String locale = null;
    private String timeZone = null;
    private String defaultPlugins = null;
    private int entryDisplayCount = 15;
    private boolean active = true;
    private boolean commentModerationRequired = false;
    private boolean enableMultiLang = false;
    private boolean showAllLangs = true;
    private String icon = null;
    private String about = null;

    private String analyticsCode = null;
    
    private String bloggerCategoryId = null;
    private String[] defaultPluginsArray = null;
    private boolean applyCommentDefaults = false;
    
    
    public String getHandle() {
        return this.handle;
    }
    
    public void setHandle( String handle ) {
        this.handle = handle;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName( String name ) {
        this.name = name;
    }
    
    public String getTagline() {
        return this.tagline;
    }
    
    public void setTagline( String tagline ) {
        this.tagline = tagline;
    }
    
    public boolean getEnableBloggerApi() {
        return this.enableBloggerApi;
    }
    
    public void setEnableBloggerApi( boolean enableBloggerApi ) {
        this.enableBloggerApi = enableBloggerApi;
    }
    
    public String getEditorPage() {
        return this.editorPage;
    }
    
    public void setEditorPage( String editorPage ) {
        this.editorPage = editorPage;
    }
    
    public String getBlacklist() {
        return this.blacklist;
    }
    
    public void setBlacklist( String blacklist ) {
        this.blacklist = blacklist;
    }
    
    public boolean getAllowComments() {
        return this.allowComments;
    }
    
    public void setAllowComments( boolean allowComments ) {
        this.allowComments = allowComments;
    }
    
    public boolean getDefaultAllowComments() {
        return this.defaultAllowComments;
    }
    
    public void setDefaultAllowComments( boolean defaultAllowComments ) {
        this.defaultAllowComments = defaultAllowComments;
    }
    
    public String getDefaultCommentDays() {
        return this.defaultCommentDays;
    }
    
    public void setDefaultCommentDays( String defaultCommentDays ) {
        this.defaultCommentDays = defaultCommentDays;
    }
    
    public boolean getModerateComments() {
        return this.moderateComments;
    }
    
    public void setModerateComments( boolean moderateComments ) {
        this.moderateComments = moderateComments;
    }
    
    public boolean getEmailComments() {
        return this.emailComments;
    }
    
    public void setEmailComments( boolean emailComments ) {
        this.emailComments = emailComments;
    }
    
    public String getEmailAddress() {
        return this.emailAddress;
    }
    
    public void setEmailAddress( String emailAddress ) {
        this.emailAddress = emailAddress;
    }
    
    public String getLocale() {
        return this.locale;
    }
    
    public void setLocale( String locale ) {
        this.locale = locale;
    }
    
    public String getTimeZone() {
        return this.timeZone;
    }
    
    public void setTimeZone( String timeZone ) {
        this.timeZone = timeZone;
    }
    
    public int getEntryDisplayCount() {
        return this.entryDisplayCount;
    }
    
    public void setEntryDisplayCount( int entryDisplayCount ) {
        this.entryDisplayCount = entryDisplayCount;
    }
    
    public boolean getCommentModerationRequired() {
        return this.commentModerationRequired;
    }
    
    public void setCommentModerationRequired( boolean commentModerationRequired ) {
        this.commentModerationRequired = commentModerationRequired;
    }
    
    public boolean isEnableMultiLang() {
        return this.enableMultiLang;
    }
    
    public void setEnableMultiLang( boolean enableMultiLang ) {
        this.enableMultiLang = enableMultiLang;
    }
    
    public boolean isShowAllLangs() {
        return this.showAllLangs;
    }
    
    public void setShowAllLangs( boolean showAllLangs ) {
        this.showAllLangs = showAllLangs;
    }
    
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
    
    public String getBloggerCategoryId() {
        return bloggerCategoryId;
    }
    
    public void setBloggerCategoryId(String bloggerCategoryId) {
        this.bloggerCategoryId = bloggerCategoryId;
    }

    public String[] getDefaultPluginsArray() {
        return defaultPluginsArray;
    }

    public void setDefaultPluginsArray(String[] strings) {
        defaultPluginsArray = strings;
    }
    public boolean getApplyCommentDefaults() {
        return applyCommentDefaults;
    }
    
    public void setApplyCommentDefaults(boolean applyCommentDefaults) {
        this.applyCommentDefaults = applyCommentDefaults;
    }
    
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAnalyticsCode() {
        return analyticsCode;
    }

    public void setAnalyticsCode(String analyticsCode) {
        this.analyticsCode = analyticsCode;
    }

    public void copyFrom(Weblog dataHolder) {
        
        this.handle = dataHolder.getHandle();
        this.name = dataHolder.getName();
        this.tagline = dataHolder.getTagline();
        this.enableBloggerApi = dataHolder.getEnableBloggerApi();
        this.editorPage = dataHolder.getEditorPage();
        this.blacklist = dataHolder.getBlacklist();
        this.allowComments = dataHolder.getAllowComments();
        this.defaultAllowComments = dataHolder.getDefaultAllowComments();
        this.defaultCommentDays = ""+dataHolder.getDefaultCommentDays();
        this.moderateComments = dataHolder.getModerateComments();
        this.emailComments = dataHolder.getEmailComments();
        this.emailAddress = dataHolder.getEmailAddress();
        this.locale = dataHolder.getLocale();
        this.timeZone = dataHolder.getTimeZone();
        this.defaultPlugins = dataHolder.getDefaultPlugins();
        this.entryDisplayCount = dataHolder.getEntryDisplayCount();
        setActive(dataHolder.getActive());
        this.commentModerationRequired = dataHolder.getCommentModerationRequired();
        this.enableMultiLang = dataHolder.isEnableMultiLang();
        this.showAllLangs = dataHolder.isShowAllLangs();
        this.analyticsCode = dataHolder.getAnalyticsCode();
        setIcon(dataHolder.getIconPath());
        setAbout(dataHolder.getAbout());
        if (dataHolder.getBloggerCategory() != null) {
            bloggerCategoryId = dataHolder.getBloggerCategory().getId();
        }
        if (dataHolder.getDefaultPlugins() != null) {
            defaultPluginsArray = StringUtils.split(dataHolder.getDefaultPlugins(), ",");
        }
    }
    
    
    public void copyTo(Weblog dataHolder) {
        dataHolder.setName(this.name);
        dataHolder.setTagline(this.tagline);
        dataHolder.setEnableBloggerApi(this.enableBloggerApi);
        dataHolder.setEditorPage(this.editorPage);
        dataHolder.setBlacklist(this.blacklist);
        dataHolder.setAllowComments(this.allowComments);
        dataHolder.setDefaultAllowComments(this.defaultAllowComments);
        dataHolder.setModerateComments(this.moderateComments);
        dataHolder.setEmailComments(this.emailComments);
        dataHolder.setEmailAddress(this.emailAddress);
        dataHolder.setLocale(this.locale);
        dataHolder.setTimeZone(this.timeZone);
        dataHolder.setDefaultPlugins(StringUtils.join(this.defaultPluginsArray, ","));
        dataHolder.setEntryDisplayCount(this.entryDisplayCount);
        dataHolder.setActive(this.getActive());
        dataHolder.setCommentModerationRequired(this.commentModerationRequired);
        dataHolder.setEnableMultiLang(this.enableMultiLang);
        dataHolder.setShowAllLangs(this.showAllLangs);
        dataHolder.setIconPath(getIcon());
        dataHolder.setAbout(getAbout());
        dataHolder.setAnalyticsCode(this.analyticsCode);
        dataHolder.setDefaultCommentDays(Integer.parseInt(this.defaultCommentDays));
    }
    


}
