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

package org.apache.roller.weblogger.ui.struts2.core;

import org.apache.roller.weblogger.config.AuthMethod;
import org.apache.roller.weblogger.config.WebloggerConfig;
import org.apache.roller.weblogger.ui.struts2.util.UIAction;

/**
 * Handle user logins.
 *
 * The standard blog login buttons route to login-redirect.rol, which is
 * intercepted by the Spring security.xml to first require a standard login (this class).
 * After successful authentication, login-redirect will either route the user to
 * registration (if the user logged in via an external method such as LDAP and doesn't
 * yet have a Roller account), or directly to the user's blog.
 *
 * @see org.apache.roller.weblogger.ui.struts2.core.Register
 */
public class Login extends UIAction {
    
    private String error = null;

    private AuthMethod authMethod = WebloggerConfig.getAuthMethod();

    public Login() {
        this.pageTitle = "loginPage.title";
    }

    // override default security, we do not require an authenticated user
    public boolean isUserRequired() {
        return false;
    }
    
    // override default security, we do not require an action weblog
    public boolean isWeblogRequired() {
        return false;
    }

    public String getAuthMethod() {
        return authMethod.name();
    }

    public String execute() {
        
        // set action error message if there was login error
        if(getError() != null) {
            if (authMethod == AuthMethod.OPENID) {
                addError("error.unmatched.openid");
            } else {
                addError("error.password.mismatch");
            }
        }
        
        return SUCCESS;
    }

    
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
