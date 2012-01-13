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
package org.youtestit.core.references.datas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.jboss.seam.international.status.MessageFactory;
import org.youtestit.commons.utils.exceptions.YoutestitMSG;
import org.youtestit.datamodel.enums.ServerType;


/**
 * ProjectsRefDatas
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 12, 2012
 */
@Singleton
@Named
public class ProjectsRefDatas implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6748716189529729711L;

    @Inject
    private MessageFactory factory;

    
    
    // =========================================================================
    // METHODS
    // =========================================================================
    /**
     * Allow to grab server type.
     * 
     * @return the list of servers type.
     */
    public List<SelectItem> grabSeverType() {
        List<SelectItem> result = new ArrayList<SelectItem>();

        for (ServerType item : ServerType.values()) {
            final YoutestitMSG msgbundle = new YoutestitMSG("data.ref.project.server.type."+item.name());
            final String msg = factory.info(msgbundle).build().getText();
            result.add(new SelectItem(item,msg));
        }

        return result;
    }
    
    
}
