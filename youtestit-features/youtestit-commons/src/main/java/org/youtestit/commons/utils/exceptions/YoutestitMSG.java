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

import org.jboss.seam.international.status.builder.BundleKey;


/**
 * YoutestitMSG
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 16, 2011
 */
public class YoutestitMSG extends BundleKey {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1698557184411919174L;
    
    private static final String BUNDLE_NAME = "messages";


    // =========================================================================
    // CONSTRUCTORS
    // =========================================================================
    /**
     * Instantiates a new youtestit msg.
     * 
     * @param bundle the bundle
     * @param key the key
     */
    public YoutestitMSG(String bundle, String key) {
        super(bundle, key);
    }

    /**
     * default constructor for instantiates a new youtestit msg with messages
     * bundle.
     *
     * @param key the key
     */
    public YoutestitMSG(String key) {
        super(BUNDLE_NAME, key);
    }

    
}
