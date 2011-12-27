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
package org.youtestit.commons.utils;

import org.jboss.logging.Logger;
import org.slf4j.LoggerFactory;


/**
 * JUnitLogger
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Dec 26, 2011
 */
public class JUnitLogger extends Logger {

    private org.slf4j.Logger logger;
    
    public JUnitLogger(Class clazz){
        super(clazz.getName());
        logger = LoggerFactory.getLogger(clazz);
    }
    
    protected JUnitLogger(String name) {
        super(name);
        logger = LoggerFactory.getLogger(name);
    }

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4540588216175442696L;

    @Override
    public boolean isEnabled(Level level) {
        return true;
    }

    @Override
    protected void doLog(Level level, String loggerClassName, Object message, Object[] parameters, Throwable thrown) {
        writeLog(level,message,parameters,thrown);
    }

    @Override
    protected void doLogf(Level level, String loggerClassName, String format, Object[] parameters, Throwable thrown) {
        writeLog(level,format,parameters,thrown);
    }
    
    
    
    
    private void writeLog(Level level, Object format, Object[] parameters, Throwable thrown) {
        String message = null;
        if(format!=null){
            message = format.toString();
        }
        
        
        switch (level) {
        case INFO:
            logger.info(message, parameters, thrown);
            break;

        case TRACE:
            logger.trace(message, parameters, thrown);
            break;
            
        case WARN:
            logger.warn(message, parameters, thrown);
            break;
        case DEBUG:
            logger.debug(message, parameters, thrown);
            break;
            
        case ERROR:
            logger.error(message, parameters, thrown);
            break;
        case FATAL:
            logger.error(message, parameters, thrown);
            break;
        default:
            break;
        }
    }
}
