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

import static org.youtestit.commons.utils.Constants.PATH_SPLIT;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.text.StrBuilder;
import org.jboss.logging.Logger;
import org.jboss.seam.international.status.Messages;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.YoutestitMSG;
import org.youtestit.commons.utils.validations.annotations.Path;
import org.youtestit.core.controllers.app.CurrentDocument;
import org.youtestit.core.references.datas.ProjectsRefDatas;
import org.youtestit.datamodel.dao.ProjectDAO;
import org.youtestit.datamodel.entity.Project;

/**
 * Controler for create new project.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 12, 2012
 */
@ViewScoped
@Named
public class CreateProjectAction implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6418054593725481561L;
    
    /** The Constant toReplace. */
    private static final String[] TO_REPLACE = { "\"", "'","!","/","\\","?","#","`" };

    /** The log. */
    @Inject
    private Logger log;

    /** The messages. */
    @Inject
    private Messages messages;

    /** The current document. */
    @Inject
    private CurrentDocument currentDocument;

    @Inject
    private ProjectsRefDatas projectsRefDatas;

    @Inject
    private ProjectDAO projectDAO;

    /** The project. */
    private Project project;

    @Path
    private String parentPath;

    // =========================================================================
    // INITIALIZE
    // =========================================================================

    /**
     * Allow to initialize CDI bean ProjectAction.
     */
    @PostConstruct
    public void initialize() {
        log.debug(currentDocument);
        log.debug(messages);
        if (project == null) {
            project = new Project();
        }

    }

    /**
     * Allow to initialize parent path.
     */
    protected void initializeParentPath() {
        if (parentPath == null) {
            if (currentDocument == null || currentDocument.getPath() == null) {
                parentPath = "/";
            } else {
                parentPath = currentDocument.getPath();
            }
        }

    }

    // =========================================================================
    // METHODS
    // =========================================================================

    public String create() {
        log.debug("ceate new project...");
        project.setPath(generatePath());
        String pathResult = null;
        
        try {
            projectDAO.create(project);
            pathResult = "app"+ project.getPath();

        } catch (ClientException e) {
            log.error(e);
            messages.error(new YoutestitMSG("error.create.project"));
        }
        return pathResult;
    }

    /**
     * Allow to generate path.
     *
     * @return path String representation
     */
    protected String generatePath() {
        final StrBuilder result = new StrBuilder();
        result.append(parentPath);
        if (!parentPath.endsWith(PATH_SPLIT)) {
            result.append(PATH_SPLIT);
        }

        final StrBuilder projectPath = new StrBuilder();
        projectPath.append(Normalizer.normalize(project.getTitle(),
                Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""));
        projectPath.replaceAll(" ", "_");


        for (String item : TO_REPLACE) {
            projectPath.replaceAll(item, "-");
        }
        result.append(projectPath.toString());
        
        return result.toString().trim();

    }

    // =========================================================================
    // GRAB DATAS
    // =========================================================================
    /**
     * Allow to grab server type.
     * 
     * @return the list
     */
    public List<SelectItem> getSeverType() {
        return projectsRefDatas.grabSeverType();
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

    /**
     * Gets the parent path.
     * 
     * @return the parent path
     */
    public String getParentPath() {
        return parentPath;
    }

    /**
     * Sets the parent path.
     * 
     * @param parentPath the new parent path
     */
    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

}
