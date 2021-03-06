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
package org.youtestit.core.controllers.app;

import static org.youtestit.commons.utils.constants.Constants.PATH_SPLIT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.youtestit.commons.utils.constants.Constants;
import org.youtestit.datamodel.dao.ProjectDAO;
import org.youtestit.datamodel.entity.Document;
import org.youtestit.datamodel.entity.Project;
import org.youtestit.datamodel.entity.TestCase;
import org.youtestit.datamodel.pojo.BreadCrumb;

/**
 * CurrentSection is managed bean who control different generic action on
 * project and test element. It's use for get the current project or test.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @author "<a href='mailto:clem.lardeur@gmail.com'>Clement Lardeur</a>"
 * @since Dec 30, 2011
 */
@Named
@ViewScoped
public class CurrentDocument implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4683368922352737218L;

    /** The project dao. */
    @Inject
    private ProjectDAO projectDAO;
    
    /** The log. */
    @Inject
    private Logger log;
    
    /** The test. */
    private Boolean test = null;

    /** The project. */
    private Boolean project = null;

    /** The path. */
    private String path = Constants.PATH_SPLIT;

    /** The document. */
    private Document document;

    /** The bread crumbs. */
    private List<BreadCrumb> breadCrumbs = new ArrayList<BreadCrumb>(0);

    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Load document.
     */
    protected void loadDocument() {
        log.debug("loadDocument()");
        if(!StringUtils.isEmpty(path)){
            document =projectDAO.readDocByPath(path);    
        }
    }

    /**
     * Initialize bread crumbs.
     */
    protected void initializeBreadCrumbs() {
        if (path != null && breadCrumbs.isEmpty()) {
            String[] paths = path.split(PATH_SPLIT);

            // skip home path
            if (paths.length > 1) {
                StringBuilder fullPath = new StringBuilder();
                for (int index = 1; index < paths.length; index++) {
                    fullPath.append(paths[index]);
                    breadCrumbs.add(new BreadCrumb(paths[index],
                            fullPath.toString()));
                    fullPath.append(PATH_SPLIT);
                }
            }
        }
    }

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
    /**
     * Gets the checks if is test.
     * 
     * @return the checks if is test
     */
    public boolean getIsTest() {
        if (test == null) {
            if (document == null) {
                loadDocument();
            }

            if (document == null) {
                test = false;
            } else {
                test = document instanceof TestCase;
            }

        }
        return test;
    }

    /**
     * Gets the checks if is project.
     * 
     * @return the checks if is project
     */
    public boolean getIsProject() {
        if (project == null) {
            if (document == null) {
                loadDocument();
            }

            if (document == null) {
                project = false;
            } else {
                project = document instanceof Project;
            }

        }
        return project;
    }

    /**
     * get url path to section.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets url path to section.
     *
     * @param path the new path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the document.
     * 
     * @return the document
     */
    public Document getDocument() {
        if (document == null) {
            loadDocument();
        }
        return document;
    }

    /**
     * Sets the document.
     * 
     * @param document the new document
     */
    public void setDocument(Document document) {
        this.document = document;
    }

    /**
     * Gets the bread crumbs.
     * 
     * @return the bread crumbs
     */
    public List<BreadCrumb> getBreadCrumbs() {
        initializeBreadCrumbs();
        return breadCrumbs;
    }

    /**
     * Sets the bread crumbs.
     * 
     * @param breadCrumbs the new bread crumbs
     */
    public void setBreadCrumbs(List<BreadCrumb> breadCrumbs) {
        this.breadCrumbs = breadCrumbs;
    }

}
