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

package org.apache.roller.weblogger.business;

import java.util.List;
import org.apache.roller.weblogger.WebloggerException;
import org.apache.roller.weblogger.pojos.WeblogBookmark;
import org.apache.roller.weblogger.pojos.WeblogBookmarkFolder;
import org.apache.roller.weblogger.pojos.Weblog;


/**
 * Interface to Bookmark Management. Provides methods for retrieving, storing,
 * moving, removing and querying for folders and bookmarks.
 */
public interface BookmarkManager {
    
    
    /**
     * Save a Folder.  
     * 
     * Also saves any bookmarks in the folder.  This method should enforce the 
     * fact that a weblog cannot have 2 folders with the same path.
     *
     * @param folder The folder to be saved.
     * @throws WebloggerException If there is a problem.
     */
    void saveFolder(WeblogBookmarkFolder folder) throws WebloggerException;
    
    
    /**
     * Remove a Folder.  
     * 
     * Also removes any bookmarks it contains
     *
     * @param folder The folder to be removed.
     * @throws WebloggerException If there is a problem.
     */
    void removeFolder(WeblogBookmarkFolder folder) throws WebloggerException;
    
    
    /**
     * Lookup a folder by ID.
     *
     * @param id The id of the folder to lookup.
     * @return FolderData The folder, or null if not found.
     * @throws WebloggerException If there is a problem.
     */
    WeblogBookmarkFolder getFolder(String id) throws WebloggerException;
    
    
    /** 
     * Get all folders for a weblog.
     *
     * @param weblog The weblog we want the folders from.
     * @return List The list of FolderData objects from the weblog.
     * @throws WebloggerException If there is a problem.
     */
    List<WeblogBookmarkFolder> getAllFolders(Weblog weblog) throws WebloggerException;
    
    
    /** 
     * Get the weblog's default folder
     *
     * @param weblog The weblog we want the default folder from.
     * @return FolderData The default folder
     * @throws WebloggerException If the default folder was not found
     */
    WeblogBookmarkFolder getDefaultFolder(Weblog weblog) throws WebloggerException;
    
    
    /** 
     * Get a folder from a weblog based on its name.
     *
     * @param weblog The weblog we want the folder from.
     * @param name The folder name.
     * @return FolderData The folder from the given path, or null if not found.
     * @throws WebloggerException If there is a problem.
     */
    WeblogBookmarkFolder getFolder(Weblog weblog, String name)
            throws WebloggerException;
    
    
    /**
     * Save a Bookmark.
     *
     * @param bookmark The bookmark to be saved.
     * @throws WebloggerException If there is a problem.
     */
    void saveBookmark(WeblogBookmark bookmark) throws WebloggerException;
    
    
    /**
     * Remove a Bookmark.
     *
     * @param bookmark The bookmark to be removed.
     * @throws WebloggerException If there is a problem.
     */
    void removeBookmark(WeblogBookmark bookmark) throws WebloggerException;
    
    
    /** 
     * Lookup a Bookmark by ID.
     *
     * @param id The id of the bookmark to lookup.
     * @return BookmarkData The bookmark, or null if not found.
     * @throws WebloggerException If there is a problem.
     */
    WeblogBookmark getBookmark(String id) throws WebloggerException;
    
    
    /** 
     * Lookup all Bookmarks in a folder, optionally search recursively.
     *
     * @param folder The folder to get the bookmarks from.
     * @return List The list of bookmarks found.
     * @throws WebloggerException If there is a problem.
     */
    List<WeblogBookmark> getBookmarks(WeblogBookmarkFolder folder)
            throws WebloggerException;
    
    
    /** 
     * Import bookmarks and folders from OPML string into the specified folder.
     *
     * @param weblog The weblog to import the OPML into.
     * @param folder The NEW folder name to import the OPML into.
     * @param opml OPML data to be imported.
     */
    void importBookmarks(Weblog weblog, String folder, String opml)
            throws WebloggerException;
    
    
    /**
     * Release all resources associated with Roller session.
     */
    void release();
    
}
