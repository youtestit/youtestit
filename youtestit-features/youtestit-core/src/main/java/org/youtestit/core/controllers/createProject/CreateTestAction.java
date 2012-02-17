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
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.YoutestitMSG;
import org.youtestit.commons.utils.exceptions.entities.EntityExistsException;
import org.youtestit.commons.utils.exceptions.entities.ParentNullException;
import org.youtestit.commons.utils.exceptions.entities.ParentTypeException;
import org.youtestit.datamodel.dao.TestCaseDAO;
import org.youtestit.datamodel.entity.TestCase;

/**
 * Controler for create new test case.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Fev 15, 2012
 */
@ViewScoped
@Named
public class CreateTestAction extends AbstractCreateDocument implements
        Serializable {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8442777558418202030L;

    /** The log. */
    @Inject
    private Logger log;

    /** The messages. */
    @Inject
    private Messages messages;
    
    @Inject
    private TestCaseDAO testCaseDAO;

    /** The project. */
    private TestCase test;

    // =========================================================================
    // INITIALIZE
    // =========================================================================

    /**
     * Allow to initialize CDI bean ProjectAction.
     */
    @PostConstruct
    public void initialize() {
        log.debug("initialize");
        if (test == null) {
            test = new TestCase();
        }

    }

    // =========================================================================
    // METHODS
    // =========================================================================

    public String create() {
        log.debug("ceate new project...");
        test = applyPath(test, TestCase.class);

        String pathResult = determineAppDocUrl(test);

        try {
            testCaseDAO.create(test);
        } catch (EntityExistsException e) {
            messages.error(new YoutestitMSG("error.create.test.already.exists"));
        } catch (ParentNullException e) {
            messages.error(new YoutestitMSG("error.create.test.parent.null"));
        } catch (ParentTypeException e) {
            messages.error(new YoutestitMSG("error.create.test.parent.type"));
        } catch (ClientException e) {
            messages.error(new YoutestitMSG("error.create.test"));
        }

        return pathResult;
    }

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================

    /**
     * Gets the test.
     * 
     * @return the test
     */
    public TestCase getTest() {
        return test;
    }

    /**
     * Sets the test.
     * 
     * @param test the new test
     */
    public void setTest(TestCase test) {
        this.test = test;
    }

}
