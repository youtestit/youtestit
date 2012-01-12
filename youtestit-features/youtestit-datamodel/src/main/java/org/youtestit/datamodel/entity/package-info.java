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
                                                                                                 |User             creator       |------------------------------------------------------------------.
                                                                                                 |Calendar         dateCreation  |                                                                  |
                                                                                                 |Calendar         dateLastModify|                                                                  |
                                                                                                 |Calendar         datePublish   |                                                                  |
                                                                                                 |String           language      |                                                                  |
                                                                   .------------------.          |String           rights        |                                                                  |
                                                                   |        Tag       |          |String           coverage      |                                                                  |
                                                                   |------------------|----------|List<Tag>        tags          |                                                                  |
                                                                   |String    name    |          '-------------------------------'                                                                  |
                                                                   '------------------'                          ^                                                                                  |
                                                                                                                 |                                                                                  |
                                                                                              .------------------------------------.                                                                |
                                                                                              |            Document                |                                                                |
                                                                                              |------------------------------------|                                                                |
                                                                                              |int                  importance     |                                                                |
                                                                                              |int                  complexity     |                                                                |
   .----------------------------. OneToMany                                                   |String               urlWiki        |                                                                |
   |             OS             |-------.                                                     |String               urlTracker     |                                                                |
   |----------------------------|       |        .--------------------------.                 |String               urlServer      |                                                                |
   |String         name         |       |        |        Portability       -----ManyToOne ---|List<Portability>    portabilities  |                                                                |
   |Enum<OsType>   type         |       |        |--------------------------|                 |Double               sucess         |                                                                |
   |Enum<OsArchi>  architecture |       |        |long       uid            |                 |boolean              lastBuildSucess|                                                                |
   '----------------------------'       |        |Os         operatingSystem|                 |long                 duration       |                                                                |
                                        '--------|Browser    browser        |                 '------------------------------------'                                                                |
                                        .--------|boolean    strict         |                                    ^                                                                                  |
                                        |        '--------------------------'                                    |                                                                                  |
                                        |                                               .------------------------'-------------------------.                                                        |
          .----------------.            |                                               |                                                  |                                                        |
          |     Browser    |------------'                                               |                                                  |                                                        |
          |----------------| OneToMany                                                  |                                                  |                                                        |
          |String   name   |                                                            |                                                  |                                                        |
          |String   version|                                           .--------------------------------.            .------------------------------------------.                                   |
          '----------------'                                           |             Project            |            |                 TestCase                 |                                   |
                                                                       |--------------------------------|            |------------------------------------------|     ManyToOne                     |
                                                     .-----------------|Map<Profile, Group>  team       |            |User                fonctionnalReferer    |-----------------------------------.
                                                     |                 |String               version    |            |User                tester                |-----------------------------------.
                                                     |                 |List<TestCase>       tests      |            |User                developper            |-----------------------------------.
                                                     |                 |ServerType           serverType |            |List<Dependancy>    dependancies          |---.                               |
                                                     |                 '--------------------------------'            |List<Instruction>   seleniumInstructions  |-. |                               |
                                                     |                                                               '------------------------------------------' | |                               |
                                                     |                                                                                                            | |                               |
                                                     |                                                      .-----------------------------------------------------' |                               |
                                                     |                                                      |OneToMany                             .----------------'                               |
                                                     |                                                      |                                      |   ManyToOne                                    |
                                                     |                                      .-------------------------------.      .------------------------------.                  .----------------------------.
                                                     |                                      |     Instruction               |      |          Dependancy          |-------------.    |            User            |
                                                     |                                      |-------------------------------|      |------------------------------|             |    |----------------------------|
                                                     |                                      |long               uid         |      |long              uid         |             |    |String       login          |
                                                     |                                      |SeleniumActionType type        |      |boolean           mustWaitting|             |    |String       email          |
                                                     |                                      |String             target      |      |Document          Document    | OneToMany   |    |String       password (SHA1)|
                                                     |                                      |String             value       |      |List<Dependency>  parents     |-------------|    |String       firstname      |
                                                     |                                      |String             errorMessage|      |List<Dependency>  children    |-------------'    |String       lastname       |
                                                     |                                      '-------------------------------'      '------------------------------'                  |String       gravatar       |
                                                     |                                                                                                                               |String       phoneNumber    |
                                                     |                                                                                                                               |String       cellularNumber |
                                                     |                                     .---------------------.                                                                   |String       office         |
                                                     |                                     |        Group        |                                                                   |String       description    |            .---------------------.
                                                     |                                     |---------------------|                      ManyToMany                                   |boolean      enable         |            |       Profile       |
                                                     |-------------------------------------|int uid              |-------------------------------------------------------------------|List<Groups> groups         | ManyToOne  |---------------------|
                                                     |                                     |String name          |                                                                   |Profile      profile        |------------|String name          |
                                                     |                                     |List<User> users     |                                                                   '----------------------------'            |Boolean administrator|
                                                     |                                     '---------------------'                                                                                                             |Boolean enable       |
                                                     |                                                                                                                                                                         '---------------------'
                                                     |                                                                                                                                                                                    |
                                                     '------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------'
 * </pre>
 */
package org.youtestit.datamodel.entity;



