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

import static org.youtestit.commons.utils.Constants.PATH_SPLIT;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.jboss.logging.Logger;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.datamodel.dao.ProjectDAO;
import org.youtestit.datamodel.entity.Document;
import org.youtestit.datamodel.services.TxHelper;
import org.youtestit.security.identification.CurrentUserManager;

import com.ocpsoft.pretty.PrettyContext;
import com.ocpsoft.pretty.faces.url.URL;


/**
 * RedirectHome
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 30, 2011
 */
@Named
@RequestScoped
public class Redirect implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long  serialVersionUID = -8966639370051990933L;

    @Inject
    private CurrentUserManager currentUserManager;

    @Inject
    private ProjectDAO         projectDAO;

    @PersistenceContext
    private EntityManager      entityManager;


    @Inject
    TxHelper txHelper;

    @Inject
    private Logger             log;

    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * Allow to gets the home page. If user is an administrator the home page is
     * the dashboardAdmin.xhtml. For other it's will be home.xhtml
     * 
     * @return the home URL
     * @throws ClientException the client exception
     */
    public String getHome() throws ClientException {
        String homePage = "/home.xhtml";

        if (currentUserManager.getCurrentAccount() != null && currentUserManager.isAdmin()) {
            homePage = "/dashboardAdmin.xhtml";
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
     * Gets the app home.
     * 
     * @return the app home
     * @throws ClientException the client exception
     * @throws SystemException
     * @throws NotSupportedException
     * @throws HeuristicRollbackException
     * @throws HeuristicMixedException
     * @throws RollbackException
     * @throws IllegalStateException
     * @throws SecurityException
     */
    public String getAppHome() throws ClientException {
        //
        URL currentPath = PrettyContext.getCurrentInstance().getRequestURL();
        Document currentDocument = null;

        if (currentPath != null && currentPath.toString().startsWith("/app/")) {
            final String requestUrl = currentPath.decode().toString().replace("/app", "");
            
            txHelper.begin();
            currentDocument = projectDAO.readDocByPath(requestUrl);
            txHelper.commit();

        }
        String result = "/app-unknown.xhtml";

        if (currentDocument != null) {
            StringBuilder url = new StringBuilder(PATH_SPLIT);
            url.append("app-");
            url.append(currentDocument.getClass().getSimpleName());
            url.append(".xhtml");

            result = url.toString();
        }
        return result;
    }

}
