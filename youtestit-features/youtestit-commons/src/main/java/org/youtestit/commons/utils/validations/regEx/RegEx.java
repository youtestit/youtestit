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
package org.youtestit.commons.utils.validations.regEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * RegEx
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 12, 2012
 */
public final class RegEx {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The INSTANCE. */
    private final static RegEx INSTANCE = new RegEx();
    
    /** The PATH regex : ^([/]{1}[\\w\\d_-]*)([/\\w\\d_-]*)([\\w\\d]*)$ */
    public final static Pattern PATH = regex("^([/]{1}[\\w\\d_-]*)([/\\w\\d_-]*)([\\w\\d]*)$");

    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * unessary to instanciate a constants class.
     */
    private RegEx() {
        super();
    }

    /**
     * Gets the single instance of RegEx.
     *
     * @return single instance of RegEx
     */
    public static RegEx getInstance(){
        synchronized (RegEx.class) {
            return INSTANCE;
        }
    }
    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Allow to create a new Regex Pattern.
     * 
     * @param regex the regex
     * @return the pattern
     */
    private static Pattern regex(final String regex) {
        return Pattern.compile(regex);
    }

    
    /**
     * Allow to check matching regex.
     *
     * @param value the value to check
     * @param pattern the regex pattern 
     * @return true, if successful
     */
    public boolean matches(final String value, final Pattern pattern){
        boolean result = false;
        if(pattern!=null && value!=null){
            final Matcher matcher = pattern.matcher(value);
            result = matcher.matches();
        }
        return result;
    }

    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
}
