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
package org.youtestit.commons.utils.exceptions;


/**
 * FatalException.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 26, 2011
 */
public class FatalException extends RuntimeException {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 883530408236866686L;


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new fatal exception.
     */
    public FatalException() {
        super();
    }

    /**
     * Instantiates a new fatal exception.
     * 
     * @param message the message
     * @param cause the cause
     */
    public FatalException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new fatal exception.
     * 
     * @param message the message
     */
    public FatalException(String message) {
        super(message);
    }


    /**
     * Instantiates a new fatal exception.
     * 
     * @param errorMsg the error msg
     * @param cause the cause
     */
    public FatalException(GenericErrorsMSG errorMsg, Throwable cause) {
        super(errorMsg.toString(), cause);
    }

    /**
     * Instantiates a new fatal exception.
     * 
     * @param errorMsg the error msg
     */
    public FatalException(GenericErrorsMSG errorMsg) {
        super(errorMsg.toString());
    }


    /**
     * Instantiates a new fatal exception.
     * 
     * @param cause the cause
     */
    public FatalException(Throwable cause) {
        super(cause);
    }


}
