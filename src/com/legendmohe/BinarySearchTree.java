package com.legendmohe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hexinyu on 2019/3/19.
 */
class BinarySearchTree extends Tree<BSTreeNode> {

    @Override
    public Tree insertNode(BSTreeNode node) {
        if (getRoot() == null) {
            setRoot(node);
        } else {
            insertNodeInternal(getRoot().parent, getRoot(), node);
        }
        return this;
    }

    @Override
    public Tree deleteNode(BSTreeNode node) {
        deleteNodeInternal(searchNode(node.value));
        return this;
    }

    @Override
    public BSTreeNode searchNode(int value) {
        return searchNodeInternal(getRoot(), value);
    }

    //////////////////////////////////////////////////////////////////////

    List<BSTreeNode> preorderWalk() {
        return preorderWalkInternal(getRoot(), new ArrayList<BSTreeNode>());
    }

    List<BSTreeNode> inorderWalk() {
        return inorderWalkInternal(getRoot(), new ArrayList<BSTreeNode>());
    }

    List<BSTreeNode> postorderWalk() {
        return postorderWalkInternal(getRoot(), new ArrayList<BSTreeNode>());
    }

    BSTreeNode minimum() {
        return findMinimumInternal(getRoot());
    }

    BSTreeNode maximum() {
        return findMaximumInternal(getRoot());
    }

    BSTreeNode successor(BSTreeNode root) {
        return findSuccessorInternal(root);
    }

    BSTreeNode predecessor(BSTreeNode root) {
        return findPredecessorInternal(root);
    }

    ///////////////////////////////////operation///////////////////////////////////

    protected List<BSTreeNode> preorderWalkInternal(BSTreeNode root, ArrayList<BSTreeNode> treeNodes) {
        if (root == null) {
            return treeNodes;
        }
        treeNodes.add(root);
        if (root.left != null) {
            preorderWalkInternal(root.left, treeNodes);
        }
        if (root.right != null) {
            preorderWalkInternal(root.right, treeNodes);
        }
        return treeNodes;
    }

    protected List<BSTreeNode> inorderWalkInternal(BSTreeNode root, ArrayList<BSTreeNode> treeNodes) {
        if (root == null) {
            return treeNodes;
        }
        if (root.left != null) {
            inorderWalkInternal(root.left, treeNodes);
        }
        treeNodes.add(root);
        if (root.right != null) {
            inorderWalkInternal(root.right, treeNodes);
        }
        return treeNodes;
    }

    protected List<BSTreeNode> postorderWalkInternal(BSTreeNode root, ArrayList<BSTreeNode> treeNodes) {
        if (root == null) {
            return treeNodes;
        }
        if (root.right != null) {
            postorderWalkInternal(root.right, treeNodes);
        }
        treeNodes.add(root);
        if (root.left != null) {
            postorderWalkInternal(root.left, treeNodes);
        }
        return treeNodes;
    }

    private BSTreeNode searchNodeInternal(BSTreeNode root, int value) {
        if (root == null || root.value == value) {
            return root;
        } else if (root.value > value) {
            return searchNodeInternal(root.left, value);
        } else {
            return searchNodeInternal(root.right, value);
        }
    }

    private BSTreeNode findMinimumInternal(BSTreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        return findMinimumInternal(root.left);
    }

    private BSTreeNode findMaximumInternal(BSTreeNode root) {
        if (root == null || root.right == null) {
            return root;
        }
        return findMaximumInternal(root.right);
    }

    private BSTreeNode findSuccessorInternal(BSTreeNode root) {
        if (root != null && root.right != null) {
            return findMinimumInternal(root.right);
        }
        BSTreeNode parent = root.parent;
        while (parent != null && parent.right == root) {
            root = parent;
            parent = parent.parent;
        }
        return parent;
    }

    private BSTreeNode findPredecessorInternal(BSTreeNode root) {
        if (root != null && root.left != null) {
            return findMaximumInternal(root.left);
        }
        BSTreeNode parent = root.parent;
        while (parent != null && parent.left == root) {
            root = parent;
            parent = parent.parent;
        }
        return parent;
    }

    private void insertNodeInternal(BSTreeNode parent, BSTreeNode root, BSTreeNode inserter) {
        if (root == null) {
            // 如果root为空，则表明找到插入点。剩下的工作就是判断插左边还是右边。
            if (parent.value > inserter.value) {
                parent.left = inserter;
                inserter.parent = parent;
            } else if (parent.value < inserter.value) {
                parent.right = inserter;
                inserter.parent = parent;
            }
            return;
        }
        // 节点要么和root相等，要么插入到左子树或者右子树
        if (root.value > inserter.value) {
            insertNodeInternal(root, root.left, inserter);
        } else if (root.value < inserter.value) {
            insertNodeInternal(root, root.right, inserter);
        }
    }

    private void deleteNodeInternal(BSTreeNode node) {
        if (node == null) {
            return;
        }
        if (node == getRoot()) {
            // 删根节点
            setRoot(null);
            return;
        }
        // 先处理少于两个child的情况，被删节点的子节点直接连接被删节点的父节点即可
        if (node.left == null) { // 没有左节点
            deleteAndMoveChildUp(node.parent, node, node.right);
        } else if (node.right == null) { // 没有右节点
            deleteAndMoveChildUp(node.parent, node, node.left);
        } else { // 两个child的情况下，用直接后继节点替换被删除节点
            BSTreeNode successor = findSuccessorInternal(node);
            if (successor != null) {
                // 这里先把successor删掉，使之游离
                // 注意这里successor肯定没有左子树.
                deleteNodeInternal(successor);
                // 然后直接替换
                successor.left = node.left;
                successor.right = node.right;
                deleteAndMoveChildUp(node.parent, node, successor);
            }
        }
    }

    private void deleteAndMoveChildUp(BSTreeNode newParent, BSTreeNode removal, BSTreeNode child) {
        if (newParent.left == removal) {// node 是 parent的左节点
            newParent.left = child;
        } else if (newParent.right == removal) {// node 是 parent的右节点
            newParent.right = child;
        }
        if (child != null) {
            child.parent = newParent;
        }
    }
}
