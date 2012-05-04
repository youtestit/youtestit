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
package org.youtestit.actions.admin.controllers.dashboard;

import java.io.File;
import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.youtestit.commons.utils.constants.Constants;

/**
 * ReadProperties.
 * 
 * @author joand
 * @since 27 avr. 2012
 */
@Named
@SessionScoped
public class ReadProperties implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2220721531008409722L;

    /** The DELIMITER. */
    private static final String DELIMITER = " : ";

    /** End of line. */
    private static final String EOL = "<br/>";

    /** The Constant VERSION. */
    private static final String VERSION = "application.version";

    /** The Constant COMMIT. */
    private static final String COMMIT = "application.commit";

    /** The Constant BUILD_DATE. */
    private static final String BUILD_DATE = "application.build.date";

    /** The Constant ADMIN_LOGIN. */
    private static final String ADMIN_LOGIN = "user.admin";

    /** The Constant ADMIN_PASSWORD. */
    private static final String ADMIN_PASSWORD = "user.admin.password";

    /** The Constant ADMIN_EMAIL. */
    private static final String ADMIN_EMAIL = "user.admin.email";

    /** The Constant GROUP_ADMIN. */
    private static final String GROUP_ADMIN = "group.administrator";

    /** The Constant PROFILE_ADMIN. */
    private static final String PROFILE_ADMIN = "profile.administrator";

    /** The file config. */
    @Singleton
    private static final FileConfiguration FILE_CONFIG = new PropertiesConfiguration();

    /** The version. */
    private String version;

    /** The commit. */
    private String commit;

    /** The build date. */
    private String buildDate;

    /** The admin login. */
    private String adminLogin;

    /** The admin password. */
    private String adminPassword;

    /** The admin email. */
    private String adminEmail;

    /** The group admin. */
    private String groupAdmin;

    /** The profile admin. */
    private String profileAdmin;

    /** The foobar. */
    private String foobar;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================

    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * Run.
     * 
     * @return a string displaying key : value
     * @throws ConfigurationException the configuration exception
     */
    public String getRun() throws ConfigurationException {
        final StringBuilder res = new StringBuilder();

        // load the file
        if (FILE_CONFIG.getFile() == null) {
            // TODO find a way not to write here the absolute path 
            final File youtestit = new File(
                    Constants.USER_HOME+".youtestit/config/youtestit.properties");
            FILE_CONFIG.setFile(youtestit);
        }
        FILE_CONFIG.clear();
        // http://commons.apache.org/configuration/howto_filebased.html#Loading
        // http://commons.apache.org/configuration/howto_properties_1_2.html#Loading
        FILE_CONFIG.load();

        // read the file
        version = FILE_CONFIG.getString(VERSION);
        commit = FILE_CONFIG.getString(COMMIT);
        buildDate = FILE_CONFIG.getString(BUILD_DATE);
        adminLogin = FILE_CONFIG.getString(ADMIN_LOGIN);
        adminPassword = FILE_CONFIG.getString(ADMIN_PASSWORD);
        adminEmail = FILE_CONFIG.getString(ADMIN_EMAIL);
        groupAdmin = FILE_CONFIG.getString(GROUP_ADMIN);
        profileAdmin = FILE_CONFIG.getString(PROFILE_ADMIN);

        res.append(EOL).append(VERSION).append(DELIMITER).append(version);
        res.append(EOL).append(COMMIT).append(DELIMITER).append(commit);
        res.append(EOL).append(BUILD_DATE).append(DELIMITER).append(buildDate);
        res.append(EOL).append(ADMIN_LOGIN).append(DELIMITER).append(adminLogin);
        res.append(EOL).append(ADMIN_PASSWORD).append(DELIMITER).append(
                adminPassword);
        res.append(EOL).append(ADMIN_EMAIL).append(DELIMITER).append(adminEmail);
        res.append(EOL).append(GROUP_ADMIN).append(DELIMITER).append(groupAdmin);
        res.append(EOL).append(PROFILE_ADMIN).append(DELIMITER).append(
                profileAdmin);
        return res.toString();
    }

    // =========================================================================
    // OVERRIDES
    // =========================================================================

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
    /**
     * Gets the version.
     * 
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the version.
     * 
     * @param version the new version
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * Gets the commit.
     * 
     * @return the commit
     */
    public String getCommit() {
        return commit;
    }

    /**
     * Sets the commit.
     * 
     * @param commit the new commit
     */
    public void setCommit(final String commit) {
        this.commit = commit;
    }

    /**
     * Gets the builds the date.
     * 
     * @return the builds the date
     */
    public String getBuildDate() {
        return buildDate;
    }

    /**
     * Sets the builds the date.
     * 
     * @param buildDate the new builds the date
     */
    public void setBuildDate(final String buildDate) {
        this.buildDate = buildDate;
    }

    /**
     * Gets the admin login.
     * 
     * @return the admin login
     */
    public String getAdminLogin() {
        return adminLogin;
    }

    /**
     * Sets the admin login.
     * 
     * @param adminLogin the new admin login
     */
    public void setAdminLogin(final String adminLogin) {
        this.adminLogin = adminLogin;
    }

    /**
     * Gets the admin password.
     * 
     * @return the admin password
     */
    public String getAdminPassword() {
        return adminPassword;
    }

    /**
     * Sets the admin password.
     * 
     * @param adminPassword the new admin password
     */
    public void setAdminPassword(final String adminPassword) {
        this.adminPassword = adminPassword;
    }

    /**
     * Gets the admin email.
     * 
     * @return the admin email
     */
    public String getAdminEmail() {
        return adminEmail;
    }

    /**
     * Sets the admin email.
     * 
     * @param adminEmail the new admin email
     */
    public void setAdminEmail(final String adminEmail) {
        this.adminEmail = adminEmail;
    }

    /**
     * Gets the group admin.
     * 
     * @return the group admin
     */
    public String getGroupAdmin() {
        return groupAdmin;
    }

    /**
     * Sets the group admin.
     * 
     * @param groupAdmin the new group admin
     */
    public void setGroupAdmin(final String groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    /**
     * Gets the profile admin.
     * 
     * @return the profile admin
     */
    public String getProfileAdmin() {
        return profileAdmin;
    }

    /**
     * Sets the profile admin.
     * 
     * @param profileAdmin the new profile admin
     */
    public void setProfileAdmin(final String profileAdmin) {
        this.profileAdmin = profileAdmin;
    }

    /**
     * Gets the serialversionuid.
     * 
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * Gets the foobar.
     * 
     * @return the foobar
     */
    public String getFoobar() {
        return foobar;
    }

    /**
     * Sets the foobar.
     * 
     * @param foobar the new foobar
     */
    public void setFoobar(final String foobar) {
        this.foobar = foobar;
    }

}
