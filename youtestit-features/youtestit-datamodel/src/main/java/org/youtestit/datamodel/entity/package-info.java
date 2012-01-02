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
 * Entity package containts all JPA entities.
 * <pre>
    .----------------------.
    |        User          |
    |----------------------|
    |String login          |
    |String email          |
    |String password (SHA1)|
    |String firstname      |
    |String lastname       |
    |String gravatar       |
    |String phoneNumber    |
    |String cellularNumber |
    |String office         |
    |String description    |
    |boolean enable        |
    '----------------------'        .---------------------.
                |                   |       Profile       |
                |   @ManyToOne      |---------------------|
                '-------------------|String name          |
                |                   |Boolean administrator|
                |                   |Boolean enable       |
                |                   '---------------------'
                |
                |                   .---------------------.
                |                   |       Group         |
                |   @ManyToMany     |---------------------|
                '-------------------|int uid              |
                                    |String name          |
                                    |List<User> users     |
                                    '---------------------'
 * </pre>
 */
package org.youtestit.datamodel.entity;



