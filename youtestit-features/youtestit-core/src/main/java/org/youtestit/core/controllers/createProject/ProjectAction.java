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
package org.youtestit.core.controllers.createProject;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;
import org.jboss.seam.international.status.Messages;
import org.youtestit.commons.utils.constants.Constants;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.YoutestitMSG;
import org.youtestit.core.controllers.app.CurrentDocument;
import org.youtestit.datamodel.dao.ProjectDAO;
import org.youtestit.datamodel.entity.Project;

/**
 * Controler for create/edit new project.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @author "<a href='mailto:clem.lardeur@gmail.com'>Clement Lardeur</a>"
 * @since Jan 12, 2012
 */
@ViewScoped
@Named
public class ProjectAction extends AbstractCreateDocument implements
        Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6418054593725481561L;

    /** The log. */
    @Inject
    private Logger log;

    /** The messages. */
    @Inject
    private Messages messages;

    /** The project dao. */
    @Inject
    private ProjectDAO projectDAO;
    
    /** The current document. */
    @Inject
    private CurrentDocument currentDocument;

    /** The project. */
    private Project project;

    // =========================================================================
    // INITIALIZE
    // =========================================================================

    /**
     * Allow to initialize CDI bean ProjectAction.
     */
    @PostConstruct
    public void initialize() {
        log.debug("initialize");
        if (currentDocument.getDocument() != null) {
            project = (Project) currentDocument.getDocument();
        } else {
            project = new Project();
        }
    }

    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Create/Update the project.
     *
     * @return the string
     */
    public String save(){
        if (currentDocument.getDocument() != null) {
            return edit();
        } else {
            return create();
        }
    }
    
    /**
     * Allow to create current project.
     * 
     * @return the string
     */
    public String create() {
        log.debug("ceate new project...");
        project = applyPath(project, Project.class);

        String pathResult = determineAppDocUrl(project);

        try {
            projectDAO.create(project);
        } catch (ClientException e) {
            log.error(e);
            messages.error(new YoutestitMSG("error.create.project"));
        }
        pathResult = Constants.PATH_HOME;
        return pathResult;
    }
    
    /**
     * Update the project.
     *
     * @return the string
     */
    public String edit() {
        log.debug("edit existing project...");
        try {
            project = projectDAO.updateProject(project);
        } catch (ClientException e) {
            log.error(e);
            messages.error(new YoutestitMSG("error.create.project"));
        }
        return Constants.PATH_HOME;
    }

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================

    /**
     * Gets the project.
     * 
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets the project.
     * 
     * @param project the new project
     */
    public void setProject(Project project) {
        this.project = project;
    }

}
