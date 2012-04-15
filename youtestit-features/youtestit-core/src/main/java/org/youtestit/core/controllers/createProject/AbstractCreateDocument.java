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

import static org.youtestit.commons.utils.constants.Constants.PATH_SPLIT;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.apache.commons.lang.text.StrBuilder;
import org.jboss.logging.Logger;
import org.jboss.seam.international.status.Messages;
import org.youtestit.commons.utils.validations.annotations.Path;
import org.youtestit.core.controllers.app.CurrentDocument;
import org.youtestit.core.references.datas.ProjectsRefDatas;
import org.youtestit.datamodel.entity.Document;

/**
 * Helper class for create document
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Fev 14, 2012
 */
public abstract class AbstractCreateDocument implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7680198717882313954L;

    /** The Constant toReplace. */
    protected static final String[] TO_REPLACE = { "\"", "'", "!", "/", "\\",
            "?", "#", "`" };

    /** The current document. */
    @Inject
    private CurrentDocument currentDocument;

    @Path
    private String parentPath;
    

    
    /** The log. */
    @Inject
    private Logger log;

    
    @Inject
    private ProjectsRefDatas projectsRefDatas;
    
    

    // =========================================================================
    // INITIALIZE
    // =========================================================================

    /**
     * Allow to initialize CDI bean.
     */
    @PostConstruct
    public void initializeBean(){
        initialize();
    }
    
    
    /**
     * Allow to initialize CDI bean.
     */
    public abstract void initialize();
    
    
    /**
     * Allow to initialize parent path.
     */
    protected void initializeParentPath() {
        log.debug("initializeParentPath()");
        if (parentPath == null) {
            if (currentDocument == null || currentDocument.getPath() == null) {
                parentPath = PATH_SPLIT;
            } else {
                parentPath = currentDocument.getPath();
            }
        }

    }

    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Allow to generate path.
     * 
     * @return path String representation
     */
    protected String generatePath(final String title) {
        final StrBuilder result = new StrBuilder();
        result.append(parentPath);
        if (!parentPath.endsWith(PATH_SPLIT)) {
            result.append(PATH_SPLIT);
        }

        final StrBuilder projectPath = new StrBuilder();
        projectPath.append(Normalizer.normalize(title,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""));
        projectPath.replaceAll(" ", "_");

        for (String item : TO_REPLACE) {
            projectPath.replaceAll(item, "-");
        }
        result.append(projectPath.toString());

        return result.toString().trim();

    }
    
    
    /**
     * Apply path.
     *
     * @param <T> the generic type
     * @param doc the doc
     * @param clazz the clazz
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public <T> T applyPath(final Document doc, Class<T> clazz){
        doc.setPath(generatePath(doc.getTitle()));
        if(parentPath==null){
            doc.setParentPath(PATH_SPLIT);
        }else{
            doc.setParentPath(parentPath);
        }
        return (T)doc;
    }
    
    
    
    public String determineAppDocUrl(Document doc){
        StringBuilder result = new StringBuilder("/app");
        
        if(doc!=null && doc.getPath()!=null){
            result.append(doc.getPath());
        }
        return result.toString();
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
     * Gets the parent path.
     * 
     * @return the parent path
     */
    public String getParentPath() {
        if (parentPath == null && currentDocument != null) {
            initializeParentPath();
        }
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


    public CurrentDocument getCurrentDocument() {
        return currentDocument;
    }


    public void setCurrentDocument(CurrentDocument currentDocument) {
        this.currentDocument = currentDocument;
    }


    
    

}
