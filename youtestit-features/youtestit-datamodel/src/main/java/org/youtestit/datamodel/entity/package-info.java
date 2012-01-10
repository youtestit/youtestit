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
                                                                                                 .-------------------------------.
                                                                                                 |          DublinCore           |
                                                                                                 |-------------------------------|
                                                                                                 |long             uid           |
                                                                                                 |String           title         |
                                                                                                 |DocumentType     type          |
                                                                                                 |String           path          |
                                                                                                 |String           subject       |
                                                                                                 |String           description   |
                                                                                                 |List<DublinCore> children      |
                                                                                                 |User             creator       |----------------------------------------------------------.
                                                                                                 |Calendar         dateCreation  |                                                          |
                                                                                                 |Calendar         dateLastModify|                                                          |
                                                                                                 |Calendar         datePublish   |                                                          |
                                                                                                 |String           language      |                                                          |
                                                                   .------------------.          |String           rights        |                                                          |
                                                                   |        Tag       |          |String           coverage      |                                                          |
                                                                   |------------------|----------|List<Tag>        tags          |                                                          |
                                                                   |String    name    |          '-------------------------------'                                                          |
                                                                   '------------------'                          ^                                                                          |
                                                                                                                 |                                                                          |
                                                                                                                 |                                                                          |
                                                                                               .-----------------------------------.                                                        |
                                                                                               |            Document               |                                                        |
                                                                                               |-------------------------------    |                                                        |
                                                                                               |int                  importance    |                                                        |
                                                                                               |int                  complexity    |                                                        |
   .----------------------------.  OneToMany                                                   |String               urlWiki       |                                                        |
   |             Os             |-------.                                                      |String               urlTracker    |                                                        |
   |----------------------------|       |        .--------------------------.     ManyToOne    |String               urlServer     |                                                        |
   |String         name         |       |        |        Portability       |------------------|List<Portability>    portabilities |                                                        |
   |Enum<OsType>   type         |       |        |--------------------------|                  '-----------------------------------'                                                        |
   |Enum<OsArchi>  architecture |       |        |long       uid            |                                    ^                                                                          |
   '----------------------------'       '--------|Os         operatingSystem|                                    |                                                                          |
                                        .--------|Browser    browser        |                                    |                                                                          |
                                        |        |boolean    strict         |                                    |                                                                          |
                                        |        '--------------------------'               .--------------------'-------------------------.                                                |
                                        |                                                   |                                              |                                                |
          .----------------.            |                                                   |                                              |                                                |
          |     Browser    |------------'                                                   |                                              |                                                |
          |----------------|  OneToMany                                                     |                                              |                                                |
          |String   name   |                                                 .-----------------------------.         .------------------------------------------.                           |
          |String   version|                                                 |          Project            |         |            Test                          |                           |
          '----------------'                                                 |-----------------------------|         |------------------------------------------|     ManyToOne             |
                                                                     .-------|Group        team            |         |User                fonctionnalReferer    |---------------------------.
                                                                     |       |String       version         |         |User                tester                |---------------------------.
                                                                     |       |List<Test>   tests           |         |User                developper            |---------------------------.
                                                                     |       |float        sucess          |         |List<Dependancy>    dependancies          |-------.                   |
                                                                     |       |boolean      lastBuildSucess |         |List<Instruction>   seleniumInstructions  |       |                   |
                                                                     |       '-----------------------------'         '------------------------------------------'       |                   |
                                                                     |                                                                     |                            |    .----------------------------.
                                                                     |                                                                     |                            |    |            User            |
                                                                     |                                                           OneToOne  |                            |    |----------------------------|
                                                                     |                                                                     |                            |    |String       login          |
                                                                     |                                                          .---------------------.  OneToMany      |    |String       email          |
                                                                     |                                                          |     Dependancy      |-----------------'    |String       password (SHA1)|
                                                                     |                                                          |---------------------|                      |String       firstname      |
                                                                     |                                                          |boolean   waitting   |                      |String       lastname       |
                                                                     |                                                          |Test test            |                      |String       gravatar       |
                                                                     |                                                          '---------------------'                      |String       phoneNumber    |
                                                                     |                                                                                                       |String       cellularNumber |
                                                                     |                                                              .---------------------.                  |String       office         |
                                                                     |                                                              |       Group         |                  |String       description    |                .---------------------.
                                                                     |                                                              |---------------------|     ManyToMany   |boolean      enable         |                |       Profile       |
                                                                     '--------------------------------------------------------------|int uid              |------------------|List<Groups> groups         |                |---------------------|
                                                                                                                                    |String name          |                  |Profile      profile        |----------------|String name          |
                                                                                                                                    |List<User> users     |                  '----------------------------'   ManyToOne    |Boolean administrator|
                                                                                                                                    '---------------------'                                                                |Boolean enable       |
                                                                                                                                                                                                                           '---------------------'
 * </pre>
 */
package org.youtestit.datamodel.entity;



