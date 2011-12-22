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
package org.youtestit.actions.user.controllers.subscrib;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.enterprise.inject.Instance;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.jboss.seam.international.status.Messages;
import org.youtestit.actions.user.helpers.Pages;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.YoutestitMSG;
import org.youtestit.datamodel.dao.UserDAO;
import org.youtestit.datamodel.entity.User;


/**
 * SubscribAction.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 12, 2011
 */
@ViewScoped
@Named
public class SubscribeAction implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -654958412208946032L;

    @Inject
    private Logger              log;

    /** The user dao. */
    @Inject
    private UserDAO           userDAO;


    @Inject
    private Messages          messages;

    /** The user. */
    private User              user             = new User();

    private String            passwordConfirm;


    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Allow to user subscrib.
     * 
     * @throws ClientException
     */
    public String subscrib() throws ClientException {
        log.info("subscrib");

        if (!StringUtils.isEmpty(user.getPassword()) && !StringUtils.isEmpty(passwordConfirm)
                && user.getPassword().equals(passwordConfirm)) {
            return createUser();

        } else {
            messages.error(new YoutestitMSG("error.subscrib.password.dismatch"));
        }

        return null;
    }



    // =========================================================================
    // PROTECTED
    // =========================================================================

    protected String createUser() throws ClientException {
        String result = null;
        final User dbUser = userDAO.getUserByLogin(user.getLogin());

        if (dbUser == null) {
            userDAO.createUser(user);
            result = Pages.login.toString();
        } else {
            messages.error(new YoutestitMSG("error.subscrib.user.exists"));
            result = Pages.subscrive.toString();
        }

        return result;
    }

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
    /**
     * Gets the user.
     * 
     * @return the user
     */
    public User getUser() {
        return user;
    }


    /**
     * Sets the user.
     * 
     * @param user the new user
     */
    public void setUser(User user) {
        this.user = user;
    }


    public String getPasswordConfirm() {
        return passwordConfirm;
    }


    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }


}
