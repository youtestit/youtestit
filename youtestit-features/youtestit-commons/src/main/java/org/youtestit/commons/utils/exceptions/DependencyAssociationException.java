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
 * Exception for projects and tests dependancies. For resolve the depandancies
 * graph it's necessary to have some constraints. Example you can't have a same
 * dependancy in parent and children on a same job.
 * A good dependancies will like :
 <pre>
                        °
               parents  ° children
      <---------------- ° ------------------->
                        °
                        °
     .---.              °
     | A |----.         °
     '---'    |         °              .---.
              |         °         .--->| D |
     .---.    |    .---------.    |    '---'
     | B |-------->| Current |----.
     '---'    |    '---------'    |    .---.
              |         °         '--->| E |
     .---.    |         °              '---'
     | C |----'         °
     '---'              °
                        °
                        °
 </pre>
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 9, 2011
 */
public class DependencyAssociationException extends ClientException {

    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7935484209927278910L;

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new client exception.
     */
    public DependencyAssociationException() {
        super("Cyclic dependency !");
    }

    /**
     * Instantiates a new client exception.
     *
     * @param errorMsg a generic error message
     */
    public DependencyAssociationException(GenericErrorsMSG errorMsg) {
        super(errorMsg.toString());
    }
    
    /**
     * Instantiates a new client exception.
     *
     * @param errorMsg a generic error message
     * @param cause the cause
     */
    public DependencyAssociationException(GenericErrorsMSG errorMsg, Throwable cause) {
        super(errorMsg.toString(),cause);
    }
    
    
    /**
     * Instantiates a new client exception.
     * 
     * @param message the message
     * @param cause the cause
     */
    public DependencyAssociationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new client exception.
     * 
     * @param message the message
     */
    public DependencyAssociationException(String message) {
        super(message);
    }

    /**
     * Instantiates a new client exception.
     * 
     * @param cause the cause
     */
    public DependencyAssociationException(Throwable cause) {
        super(cause);
    }
}
