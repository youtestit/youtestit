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
package org.youtestit.datamodel.entity;

import static javax.persistence.FetchType.LAZY;
import static org.youtestit.commons.utils.Constants.SEP;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * Test.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 10, 2012
 */
@Entity
public class TestCase extends Document {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1317496408018574593L;

    /** The fonctionnal referer. */
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = LAZY)
    private User              fonctionnalReferer;

    /** The tester. */
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = LAZY)
    private User              tester;

    /** The developper. */
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = LAZY)
    private User              developper;

    /** The dependancies. */
    @OneToMany(cascade = {CascadeType.REMOVE,CascadeType.PERSIST}, targetEntity = Dependency.class, orphanRemoval = true, fetch = LAZY)
    private List<Dependency>  dependencies;

    /** The selenium instructions. */
    @OneToMany(cascade = {CascadeType.REMOVE,CascadeType.PERSIST}, targetEntity = Instruction.class, orphanRemoval = true, fetch = LAZY)
    private List<Instruction> seleniumInstructions;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new test.
     */
    public TestCase() {
        super();
    }

    /**
     * Instantiates a new test.
     * 
     * @param title the title
     * @param path the path
     * @param subject the subject
     * @param creator the creator
     * @param dateCreation the date creation
     */
    public TestCase(String title, String path, String subject, User creator, Calendar dateCreation) {
        super(title, path, subject, creator, dateCreation);
    }

    /**
     * Instantiates a new test.
     * 
     * @param name the name
     * @param path the path
     */
    public TestCase(String name, String path) {
        super(name, path);
    }


    // =========================================================================
    // OVERRIDES
    // =========================================================================
    /**
     * {@inheritDoc}
     */
    @Override
    protected String toStringContent() {
        final StringBuilder result = new StringBuilder(super.toStringContent());

        result.append(", fonctionnalReferer=");
        result.append(fonctionnalReferer == null ? super.getNullObj() : fonctionnalReferer.getLogin());

        result.append(", tester=");
        result.append(tester == null ? super.getNullObj() : tester.getLogin());

        result.append(", developper=");
        result.append(developper == null ? super.getNullObj() : developper.getLogin());

        result.append(dependanciesToString());

        result.append(seleniuminstructionsToString());

        return result.toString();
    }


    /**
     * Allow to render dependancies to string.
     * 
     * @return the string
     */
    private String dependanciesToString() {
        final StringBuilder result = new StringBuilder();

        result.append(", dependancies=");
        if (dependencies == null) {
            result.append(super.getNullObj());
        } else {
            result.append(super.getItemOpen());
            for (Dependency item : dependencies) {
                result.append(item.getUid());
            }
            result.append(super.getItemClose());
        }

        return result.toString();
    }


    /**
     * Allow to render seleniuminstructions to string.
     * 
     * @return the string
     */
    private String seleniuminstructionsToString() {
        final StringBuilder result = new StringBuilder();

        result.append(", seleniumInstructions=");
        if (seleniumInstructions == null) {
            result.append(super.getNullObj());
        } else {
            result.append(super.getItemOpen());
            for (Instruction item : seleniumInstructions) {
                result.append(item.getUid());
                result.append(SEP);
            }
            result.append(super.getItemClose());
        }

        return result.toString();
    }


    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
    /**
     * Gets the fonctionnal referer.
     * 
     * @return the fonctionnal referer
     */
    public User getFonctionnalReferer() {
        return fonctionnalReferer;
    }


    /**
     * Sets the fonctionnal referer.
     * 
     * @param fonctionnalReferer the new fonctionnal referer
     */
    public void setFonctionnalReferer(User fonctionnalReferer) {
        this.fonctionnalReferer = fonctionnalReferer;
    }

    /**
     * Gets the tester.
     * 
     * @return the tester
     */
    public User getTester() {
        return tester;
    }

    /**
     * Sets the tester.
     * 
     * @param tester the new tester
     */
    public void setTester(User tester) {
        this.tester = tester;
    }

    /**
     * Gets the developper.
     * 
     * @return the developper
     */
    public User getDevelopper() {
        return developper;
    }

    /**
     * Sets the developper.
     * 
     * @param developper the new developper
     */
    public void setDevelopper(User developper) {
        this.developper = developper;
    }

    /**
     * Gets the dependancies.
     * 
     * @return the dependancies
     */
    public List<Dependency> getDependancies() {
        return dependencies;
    }

    /**
     * Sets the dependancies.
     * 
     * @param dependancies the new dependancies
     */
    public void setDependancies(List<Dependency> dependancies) {
        this.dependencies = dependancies;
    }

    /**
     * Gets the selenium instructions.
     * 
     * @return the selenium instructions
     */
    public List<Instruction> getSeleniumInstructions() {
        return seleniumInstructions;
    }

    /**
     * Sets the selenium instructions.
     * 
     * @param seleniumInstructions the new selenium instructions
     */
    public void setSeleniumInstructions(List<Instruction> seleniumInstructions) {
        this.seleniumInstructions = seleniumInstructions;
    }

}
