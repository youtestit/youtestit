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
/**
 * Bootstrap initialization phase. Allow to initialize all you test it datas.
 * The initializer chain is make by Seam event.
 * <pre>
     ______________________
     \                     \
      ) Seam initializing   )
     /_____________________/
                 |
                 | bean.xml configuration           
                 | fire event initialize file system
                 |
      .----------v---------.
      |InitializeFileSystem|
      '--------------------'
                 |
                 | fire event InitializeUser 
                 |
      .----------v---------.
      |   InitializeUsers  |
      '--------------------'
                 |
                 | fire event InitializeOsAndBrowserEvent
                 |
     .-----------v----------.
     |InitializeOsAndBrowser|
     '----------------------'                                   
 * </pre>
 */
package org.youtestit.bootstrap;



