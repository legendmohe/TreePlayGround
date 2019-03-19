package com.legendmohe;

/**
 * Created by hexinyu on 2019/3/19.
 */
class BSTreeNode extends TreeNode {
    BSTreeNode left;
    BSTreeNode right;
    BSTreeNode parent;

    BSTreeNode(int value) {
        super(value);
    }

    @Override
    public String toString() {
        return "{" +
                value +
                '}';
    }
}
