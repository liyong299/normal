package org.topteam.ui.model;

import java.util.ArrayList;

/**
 * Created by jf on 15/2/25.
 */
public abstract class AbstractTreeModel extends ArrayList<TreeNode> {

    private TreeNode root;

    protected void wrapRoot() {
        if (getRoot() != null) {
            TreeNode root = getRoot();
            root.getChildren().addAll(this);
            this.clear();
            this.add(root);
            root = null;
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }


}
