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

import org.apache.commons.lang.text.StrBuilder;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * TestCommons
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 12, 2012
 */
public class TestCommons {

    // =========================================================================
    // ATTRIBUTS
    // =========================================================================
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestCommons.class);

    /** The hello name. */
    private String              helloName;

    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Allow to Initialize unit test. It log a hello message too.
     */
    @Before
    public void initialize() {
        final String slide;
        slide = "=============================================================";
        if (helloName == null) {
            helloName = this.getClass().getSimpleName();
        }

        final StrBuilder helloMSG = new StrBuilder();
        helloMSG.appendln("");
        helloMSG.appendln(slide);
        helloMSG.appendln("- " + helloName);
        helloMSG.appendln(slide);

        LOGGER.info(helloMSG.toString());
    }


    /**
     * Log info message with text decorated.
     *
     * @param msg the message to display
     */
    protected void logInfoMSG(final String msg) {
        final String slide;
        slide = "-------------------------------------------------------------";
        if (helloName == null) {
            helloName = this.getClass().getSimpleName();
        }

        final StrBuilder helloMSG = new StrBuilder();
        helloMSG.appendln("");
        helloMSG.appendln(slide);
        helloMSG.appendln("- " + helloName + " : " + msg);
        helloMSG.appendln(slide);

        LOGGER.info(helloMSG.toString());
    }

}
