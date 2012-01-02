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
package org.youtestit.core.controllers.app;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;


/**
 * CurrentSection is managed bean who control different generic action on
 * project and test element. It's use for get the current project or test.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 30, 2011
 */
@Named
@ViewScoped
public class CurrentDocument implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4683368922352737218L;

    /** The test. */
    private Boolean test = null;
    
    /** The project. */
    private Boolean project = null;
    
    private String path = "";

    // =========================================================================
    // METHODS
    // =========================================================================
    



    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================
    /**
     * Gets the checks if is test.
     *
     * @return the checks if is test
     */
    public boolean getIsTest(){
        if(test==null){
            test = false;
        }
        return test;
    }
    
    /**
     * Gets the checks if is project.
     *
     * @return the checks if is project
     */
    public boolean getIsProject(){
        if(project==null){
            project = false;
        }
        return project;
    }

    
    /**
     * get url path to section
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets url path to section
     *
     * @param path the new path
     */
    public void setPath(String path) {
        this.path = "/"+path;
    }
    
    
}
