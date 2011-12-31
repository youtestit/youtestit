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
package org.youtestit.commons.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang.text.StrBuilder;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.sha1.Sha1Encryption;


/**
 * Constants class is using for set all generic applicatin properties like
 * administrator login, groups, email, ...
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 26, 2011
 */
public final class Constants {
    // =========================================================================
    // CONSTANTS
    // =========================================================================
    /** The Constant INSTANCE. */
    private final static Constants INSTANCE                = new Constants();

    /** User home path. */
    public final static String     USER_HOME               = (String) System.getProperties().get("user.home") + "/";

    /** Folder name for file system storage. */
    public final static String     STORAGE_FOLDER_NAME     = ".youtestit";

    /** full path to file system storage. */
    public final static String     STORAGE_FOLDER          = USER_HOME + STORAGE_FOLDER_NAME;

    /** full path to configuration storage. */
    public final static String     STORAGE_FOLDER_CONFIG   = STORAGE_FOLDER + "/config";

    /** full path to projects storage. */
    public final static String     STORAGE_FOLDER_PROJECTS = STORAGE_FOLDER + "/projects";

    /** Global properties file (youtestit.properties). */
    public final static String     PROPERTIES_FILE         = "youtestit.properties";

    // =========================================================================
    // ATTRIBUTS
    // =========================================================================
    /** The properties. */
    private Properties             properties;

    private ConstantsProperties    constantsProperties = new ConstantsProperties();

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Private constructor, it's a utility class.
     */
    private Constants() {
        // it's a utility class with only constants attributs.
    }

    /**
     * Gets the single instance of Constants.
     * 
     * @return single instance of Constants
     */
    public static Constants getInstance() {
        return INSTANCE;
    }

    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Load all properties.
     * 
     * @throws ClientException the client exception
     */
    protected void loadAllProperties() throws ClientException {
        Sha1Encryption sha1 = Sha1Encryption.getInstance();
        
        constantsProperties.setApplicationVersion(getProperty("application.version", ""));
        constantsProperties.setApplicationCommit(getProperty("application.commit", ""));
        constantsProperties.setApplicationBuildDate(getProperty("application.build.date", ""));
        constantsProperties.setUserAdmin(getProperty("user.admin", "admin"));
        constantsProperties.setUserAdminEmail(getProperty("user.admin.email", "administrator@youtestit.org"));
        constantsProperties.setUserAdminPassword(sha1.encryptToSha1(getProperty("user.admin.password", "admin")));
        constantsProperties.setGroupAdministrator(getProperty("group.administrator", "Administrator"));
        constantsProperties.setProfileAdministrator(getProperty("profile.administrator", "Administrator"));

    }


    /**
     * Reset properties.
     * 
     * @throws ClientException the client exception
     */
    public void resetProperties() throws ClientException {
        final String proptertiesPath = Constants.STORAGE_FOLDER_CONFIG + "/" + Constants.PROPERTIES_FILE;
        final File propertiesFile = new File(proptertiesPath);


        if (!propertiesFile.exists()) {

            final InputStream input = getClass().getClassLoader().getResourceAsStream(Constants.PROPERTIES_FILE);

            final DataInputStream dataInput = new DataInputStream(input);
            final BufferedReader bufferReader = new BufferedReader(new InputStreamReader(dataInput));
            final StrBuilder contentProperties = new StrBuilder();

            String strLine;

            try {

                do {
                    strLine = bufferReader.readLine();
                    if (strLine != null) {
                        contentProperties.appendln(strLine);
                    }
                } while (strLine != null);

            } catch (IOException ioException) {
                throw new ClientException(ioException);
            } finally {
                closeInputStream(input);
            }


            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(proptertiesPath);
            } catch (IOException e) {
                throw new ClientException(e);
            }
            final BufferedWriter out = new BufferedWriter(fileWriter);

            try {
                out.write(contentProperties.toString());
            } catch (IOException e) {
                throw new ClientException(e);
            } finally {
                closeOutPutStream(out);
            }
        }
        
        reloadProperties();
    }


    /**
     * Reload properties.
     * 
     * @throws ClientException the client exception
     */
    public void reloadProperties() throws ClientException {
        final String proptertiesPath = Constants.STORAGE_FOLDER_CONFIG + "/" + Constants.PROPERTIES_FILE;
        InputStream propertiesFile;
        try {
            propertiesFile = new FileInputStream(proptertiesPath);
        } catch (FileNotFoundException e) {
            throw new ClientException(e);
        }

        properties = new Properties();
        try {
            properties.load(propertiesFile);
        } catch (IOException e) {
            throw new ClientException(e);
        } finally {
            closeInputStream(propertiesFile);
        }
        
        loadAllProperties();
    }


    /**
     * Allow to close input stream.
     * 
     * @param input the input stream
     * @throws ClientException the client exception
     */
    private void closeInputStream(final InputStream input) throws ClientException {
        try {
            input.close();
        } catch (IOException e) {
            throw new ClientException(e);
        }
    }

    /**
     * Close out put stream.
     * 
     * @param out the out
     * @throws ClientException the client exception
     */
    private void closeOutPutStream(final BufferedWriter out) throws ClientException {
        try {
            out.close();
        } catch (IOException e) {
            throw new ClientException(e);
        }
    }


    /**
     * Gets the property.
     * 
     * @param key the key
     * @param defaultValue the default value
     * @return the property
     * @throws ClientException the client exception
     */
    protected String getProperty(final String key, final String defaultValue) throws ClientException {
        String result = null;
        if (key != null && properties != null) {
            result = properties.getProperty(key);
        }

        if (result == null) {
            return defaultValue;
        } else {
            return result;
        }
    }


    // =========================================================================
    // GETTERS
    // =========================================================================
    /**
     * Gets the properties.
     * 
     * @return the properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Gets the constants properties.
     *
     * @return the constants properties
     */
    public ConstantsProperties getConstantsProperties() {
        return constantsProperties;
    }
    
}
