/*
 *   YouTestit source code:
 *   ======================
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *  
        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   Links:
 *   ======
 *   Homepage : http://www.youtestit.org
 *   Git      : https://github.com/youtestit
 */
package org.youtestit.commons.utils.constants;


/**
 * ConstantsProperties
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 26, 2011
 */
public class ConstantsProperties {
    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The application version. */
    private String applicationVersion   = null;

    /** The application commit. */
    private String applicationCommit    = null;

    /** The application build date. */
    private String applicationBuildDate = null;

    /** The user admin. */
    private String userAdmin            = null;

    /** The user admin password. */
    private String userAdminPassword    = null;

    private String userAdminEmail       = null;

    /** The group administrator. */
    private String groupAdministrator   = null;

    /** The profile administrator. */
    private String profileAdministrator = null;


    // =========================================================================
    // OVERRIDES
    // =========================================================================
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("ConstantsProperties [");
        result.append("applicationVersion=" + applicationVersion);
        result.append(", applicationCommit=" + applicationCommit);
        result.append(", applicationBuildDate=" + applicationBuildDate);
        result.append(", userAdmin=" + userAdmin);
        result.append(", userAdminEmail=" + userAdminEmail);
        result.append(", userAdminPassword=" + userAdminPassword);
        result.append(", groupAdministrator=" + groupAdministrator);
        result.append(", profileAdministrator=" + profileAdministrator);
        result.append("]");

        return result.toString();
    }

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
    public String getApplicationVersion() {
        return applicationVersion;
    }


    public String getApplicationCommit() {
        return applicationCommit;
    }


    public String getApplicationBuildDate() {
        return applicationBuildDate;
    }


    public String getUserAdmin() {
        return userAdmin;
    }


    public String getUserAdminPassword() {
        return userAdminPassword;
    }


    public String getGroupAdministrator() {
        return groupAdministrator;
    }


    public String getProfileAdministrator() {
        return profileAdministrator;
    }

    public String getUserAdminEmail() {
        return userAdminEmail;
    }


    // -------------------------------------------------------------------------

    protected void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    protected void setApplicationCommit(String applicationCommit) {
        this.applicationCommit = applicationCommit;
    }


    protected void setApplicationBuildDate(String applicationBuildDate) {
        this.applicationBuildDate = applicationBuildDate;
    }

    protected void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }


    protected void setUserAdminPassword(String userAdminPassword) {
        this.userAdminPassword = userAdminPassword;
    }

    protected void setGroupAdministrator(String groupAdministrator) {
        this.groupAdministrator = groupAdministrator;
    }

    protected void setProfileAdministrator(String profileAdministrator) {
        this.profileAdministrator = profileAdministrator;
    }


    protected void setUserAdminEmail(String userAdminEmail) {
        this.userAdminEmail = userAdminEmail;
    }


}
