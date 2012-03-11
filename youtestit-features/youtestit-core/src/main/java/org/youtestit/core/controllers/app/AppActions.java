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

import static org.youtestit.commons.utils.Constants.PATH_APPLICATION;
import static org.youtestit.commons.utils.Constants.PATH_SPLIT;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.MenuModel;
import org.primefaces.model.TreeNode;
import org.youtestit.commons.utils.exceptions.ClientException;
import org.youtestit.datamodel.dao.ProjectDAO;
import org.youtestit.datamodel.entity.Document;
import org.youtestit.datamodel.entity.DublinCore;
import org.youtestit.datamodel.pojo.BreadCrumb;


/**
 * AppActions
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since 6 févr. 2012
 */
@Named
@ViewScoped
public class AppActions implements Serializable {


    // =========================================================================
    // ATTRIBUTES
    // =========================================================================

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1679635671732315892L;


    /** The logger. */
    @Inject
    private Logger            log;


    private MenuModel         breadCrumbs      = null;

    private TreeNode          tree;


    @Inject
    private CurrentDocument   currentDocument;


    @Inject
    private ProjectDAO        projectDAO;


    // =========================================================================
    // METHODS
    // =========================================================================

    /**
     * Allow to initialize bread crumbs.
     */
    protected void initializeBreadCrumbs() {
        log.debug("initializeBreadCrumbs");
        breadCrumbs = new DefaultMenuModel();


        final MenuItem home = new MenuItem();


        home.setUrl(PATH_APPLICATION);
        home.setStyleClass("homeIcon");
        home.setValue("home");
        home.setId("breadcrumbHome");
        breadCrumbs.addMenuItem(home);

        if (currentDocument != null && !currentDocument.getBreadCrumbs().isEmpty()) {
            int index = 0;
            for (BreadCrumb breadItem : currentDocument.getBreadCrumbs()) {
                final MenuItem item = new MenuItem();
                item.setValue(breadItem.getName());
                item.setUrl(PATH_APPLICATION + breadItem.getPath());
                item.setId("breadcrumbItem-" + index);
                breadCrumbs.addMenuItem(item);
                index++;
            }
        }
    }


    /**
     * Initialize tree.
     * 
     * @throws ClientException
     */
    protected void initializeTree() throws ClientException {
        tree = new DefaultTreeNode("root", null);


        List<Document> documents = projectDAO.readDocByPathParent(PATH_SPLIT);

        if (documents != null) {
            for (Document doc : documents) {
                grabChildrenNode(doc, tree);
            }
        }

        log.debug(tree);
    }


    /**
     * Grab children node.
     * 
     * @param parent the parent
     * @param parentNode the parent node
     * @return the tree node
     * @throws ClientException the client exception
     */
    protected TreeNode grabChildrenNode(final Document parent, final TreeNode parentNode) throws ClientException {
        TreeNode result = new DefaultTreeNode(parent.getClass().getSimpleName(), parent, parentNode);

        final boolean startsWith = currentDocument.getPath().startsWith(parent.getPath() + PATH_SPLIT);
        final boolean sizeValid = parent.getPath().length() <= currentDocument.getPath().length();
        final boolean same = currentDocument.getPath().equals(parent.getPath());

        // setExpanded .........................................................
        final boolean expanded = (startsWith && sizeValid) || same;
        result.setExpanded(expanded);

        // add children ........................................................
        if (parent.getChildren() != null && !parent.getChildren().isEmpty()) {
            for (DublinCore doc : parent.getChildren()) {
                if (doc instanceof Document) {
                    grabChildrenNode((Document) doc, result);
                }
            }
        }

        return result;
    }


    // =========================================================================
    // GETTERS & SETTERS
    // =========================================================================

    /**
     * Gets the bread crumbs.
     * 
     * @return the bread crumbs
     */
    public MenuModel getBreadCrumbs() {
        if (breadCrumbs == null) {
            initializeBreadCrumbs();
        }
        return breadCrumbs;
    }

    /**
     * Sets the bread crumbs.
     * 
     * @param breadCrumbs the new bread crumbs
     */
    public void setBreadCrumbs(MenuModel breadCrumbs) {
        this.breadCrumbs = breadCrumbs;
    }

    /**
     * Gets the tree.
     * 
     * @return the tree
     * @throws ClientException
     */
    public TreeNode getTree() throws ClientException {
        if (tree == null) {
            initializeTree();
        }
        return tree;
    }

    /**
     * Sets the tree.
     * 
     * @param tree the new tree
     */
    public void setTree(TreeNode tree) {
        this.tree = tree;
    }

}
