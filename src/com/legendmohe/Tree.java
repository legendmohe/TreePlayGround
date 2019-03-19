package com.legendmohe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hexinyu on 2019/3/19.
 */
abstract class Tree<T extends TreeNode> {

    T root;

    abstract Tree insertNode(T node);

    abstract Tree deleteNode(T node);

    abstract T searchNode(int value);

    protected void setRoot(T root) {
        this.root = root;
    }

    protected T getRoot() {
        return this.root;
    }
}
