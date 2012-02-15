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
package org.youtestit.commons.utils.exceptions.entities;

import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.commons.utils.exceptions.GenericErrorsMSG;

/**
 * Parent type  Exception, occur if document parent isn't valid type.
 * Example, you can't create a project in a test case. 
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Fev 15, 2012
 */
public class ParentTypeException extends ClientException {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5268973943819397808L;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new client exception.
     */
    public ParentTypeException() {
        super();
    }

    /**
     * Instantiates a new client exception.
     *
     * @param errorMsg a generic error message
     */
    public ParentTypeException(GenericErrorsMSG errorMsg) {
        super(errorMsg.toString());
    }
    
    /**
     * Instantiates a new client exception.
     *
     * @param errorMsg a generic error message
     * @param cause the cause
     */
    public ParentTypeException(GenericErrorsMSG errorMsg, Throwable cause) {
        super(errorMsg.toString(),cause);
    }
    
    
    /**
     * Instantiates a new client exception.
     * 
     * @param message the message
     * @param cause the cause
     */
    public ParentTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new client exception.
     * 
     * @param message the message
     */
    public ParentTypeException(String message) {
        super(message);
    }

    /**
     * Instantiates a new client exception.
     * 
     * @param cause the cause
     */
    public ParentTypeException(Throwable cause) {
        super(cause);
    }
}
