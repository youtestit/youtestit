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
 * Generic errors messages.
 *
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 10, 2011
 */
public enum ErrorsMSG {
    
    /** The VALU e_ no t_ null. */
    VALUE_NOT_NULL("value musn't be null!");
    
    
    /** The message. */
    private String message;
    
    /**
     * Instantiates a new errors msg.
     *
     * @param msg the msg
     */
    private ErrorsMSG(final String msg){
        this.message=msg;
    }
    
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(){
        return String.format("[code-%s] >>> %s", this.ordinal(), message);
    }


    /**
     * Gets the message.
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    
    
}


