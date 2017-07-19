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

package org.apache.roller.weblogger.ui.rendering.model;

import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.roller.weblogger.WebloggerException;
import org.apache.roller.weblogger.util.Utilities;


/**
 * Helps with model loading process.
 */
public class ModelLoader {
    
    private static Log log = LogFactory.getLog(ModelLoader.class);
    
    /**
     * Convenience method to load a comma-separated list of page models.
     *
     * Optionally fails if any exceptions are thrown when initializing
     * the Model instances.
     */
    public static void loadModels(String modelsString, Map<String, Object> modelMap,
                                   Map initData, boolean fail) 
            throws WebloggerException {
        
        String[] models = Utilities.stringToStringArray(modelsString, ",");
        if (models != null) {
            for (String model : models) {
                try {
                    Class modelClass = Class.forName(model);
                    Model pageModel = (Model) modelClass.newInstance();
                    pageModel.init(initData);
                    modelMap.put(pageModel.getModelName(), pageModel);
                } catch (WebloggerException re) {
                    if(fail) {
                        throw re;
                    } else {
                        log.warn("Error initializing model: " + model);
                    }
                } catch (ClassNotFoundException cnfe) {
                    if(fail) {
                        throw new WebloggerException("Error finding model: " + model, cnfe);
                    } else {
                        log.warn("Error finding model: " + model);
                    }
                } catch (InstantiationException ie) {
                    if(fail) {
                        throw new WebloggerException("Error instantiating model: " + model, ie);
                    } else {
                        log.warn("Error instantiating model: " + model);
                    }
                } catch (IllegalAccessException iae) {
                    if(fail) {
                        throw new WebloggerException("Error accessing model: " + model, iae);
                    } else {
                        log.warn("Error accessing model: " + model);
                    }
                }
            }
        }
    }
    
}
