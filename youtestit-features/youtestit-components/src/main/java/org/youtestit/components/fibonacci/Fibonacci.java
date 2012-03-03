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
package org.youtestit.components.fibonacci;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;


/**
 * Fibonacci.
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Feb 17, 2012
 */
@ApplicationScoped
@Named
public class Fibonacci implements Serializable {


    /** The Constant serialVersionUID. */
    private static final long         serialVersionUID = 3444895693731952328L;

    /** The Constant SUITE. */
    private static final List<Double> SUITE            = initializeSuite();


    /**
     * allow to initialize Fibonacci suite.
     *
     * @return the Fibonacci suite
     */
    private static List<Double> initializeSuite() {
        List<Double> result = new ArrayList<Double>();
        final double[] simpleSuite = { 0, 0.5, 1, 2, 3, 5, 8, 13, 20, 40, 100 };

        for (double item : simpleSuite) {
            result.add(item);
        }
        return result;
    }




    /**
     * Gets the suite.
     * 
     * @return the suite
     */
    public List<Double> getSuite() {
        return SUITE;
    }


}
