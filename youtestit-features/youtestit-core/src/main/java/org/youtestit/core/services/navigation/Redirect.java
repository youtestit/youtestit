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
package org.youtestit.core.services.navigation;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;
import org.youtestit.commons.utils.constants.Constants;
import org.youtestit.commons.utils.enums.DocTypes;
import org.youtestit.commons.utils.enums.Pages;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.datamodel.dao.ProjectDAO;
import org.youtestit.datamodel.entity.Document;
import org.youtestit.datamodel.services.TxHelper;
import org.youtestit.security.identification.CurrentUserManager;

import com.ocpsoft.pretty.PrettyContext;

/**
 * RedirectHome
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @author "<a href='mailto:clem.lardeur@gmail.com'>Clement Lardeur</a>"
 * @since Dec 30, 2011
 */
@Named
@RequestScoped
public class Redirect implements Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    @Inject
    private CurrentUserManager currentUserManager;

    @Inject
    private ProjectDAO projectDAO;

    @Inject
    TxHelper txHelper;

    @Inject
    private Logger log;

    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * Allow to gets the home page. If user is an administrator the home page is
     * the /admin/home.xhtml. For other it's will be /home.xhtml
     * 
     * @return the home URL
     * @throws ClientException the client exception
     */
    public String getHome() throws ClientException {
        String homePage = Pages.home.toString();
        if (currentUserManager.getCurrentAccount() != null
                && currentUserManager.isAdmin()) {
            homePage = "/admin/" + homePage;
        }
        return homePage;
    }

    /**
     * Redirect to home.
     * 
     * @throws ClientException the client exception
     */
    public void redirectToHome() throws ClientException {
        log.info("redirectToHome");
    }

    /**
     * Compute the path to view a Document depending of her type.
     * 
     * @return document view path
     * @throws ClientException the client exception
     */
    public String viewDocument() throws ClientException {
        PrettyContext pc = PrettyContext.getCurrentInstance();
        String path = pc.getRequestURL().decode().toString();
        path = path.replace(Constants.PATH_APPLICATION + "view/",
                Constants.PATH_SPLIT);
        Document currentDocument = setCurrentDocument(path);

        String result;
        if (currentDocument != null) {
            StringBuilder url = new StringBuilder(Constants.PATH_APPLICATION);
            url.append(currentDocument.getClass().getSimpleName());
            url.append(Constants.PATH_SPLIT);
            url.append(Pages.view.toString());
            result = url.toString();
        } else {
            result = Constants.PATH_APPLICATION + Pages.unknown.toString();
        }

        return result.toLowerCase();
    }

    /**
     * Compute the path to create a Document depending of her type.
     * 
     * @return document create path
     * @throws ClientException the client exception
     */
    public String createDocument() throws ClientException {
        PrettyContext pc = PrettyContext.getCurrentInstance();
        List<String> segments = pc.getRequestURL().getSegments();
        String docType = "";
        if (segments != null && !segments.isEmpty()) {
            docType = segments.get(2);
        }

        boolean existDocType = false;
        for (DocTypes dc : DocTypes.values()) {
            if (dc.name().toLowerCase().equals(docType)) {
                existDocType = true;
            }
        }

        String result;
        if (existDocType) {
            StringBuilder url = new StringBuilder(Constants.PATH_APPLICATION);
            url.append(docType);
            url.append(Constants.PATH_SPLIT);
            url.append(Pages.edit.toString());
            result = url.toString();
        } else {
            result = Constants.PATH_APPLICATION + Pages.unknown.toString();
        }

        return result.toLowerCase();
    }

    /**
     * Compute the path to edit a Document depending of her type.
     * 
     * @return document create path
     * @throws ClientException the client exception
     */
    public String editDocument() throws ClientException {
        PrettyContext pc = PrettyContext.getCurrentInstance();
        String path = pc.getRequestURL().decode().toString();
        path = path.replace(Constants.PATH_APPLICATION + "edit/",
                Constants.PATH_SPLIT);
        Document currentDocument = setCurrentDocument(path);

        String result;
        if (currentDocument != null) {
            StringBuilder url = new StringBuilder(Constants.PATH_APPLICATION);
            url.append(currentDocument.getClass().getSimpleName());
            url.append(Pages.edit.toString());
            result = url.toString();
        } else {
            result = Constants.PATH_APPLICATION + Pages.unknown.toString();
        }

        return result.toLowerCase();
    }

    /**
     * Sets the current document.
     * 
     * @param path the path
     * @return the document
     * @throws ClientException the client exception
     */
    private Document setCurrentDocument(String path) throws ClientException {
        if (path == null) {
            return null;
        }
        Document doc = null;
        txHelper.begin();
        doc = projectDAO.readDocByPath(path);
        txHelper.commit();
        return doc;
    }
}
