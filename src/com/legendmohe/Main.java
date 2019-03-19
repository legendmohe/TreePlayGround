package com.legendmohe;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        new Main().runTestCase();
    }

    private void runTestCase() {
        case1();
        case2();
    }

    private void case1() {
        BinarySearchTree tree = new BinarySearchTree();

        BSTreeNode node6 = new BSTreeNode(6);
        BSTreeNode node4 = new BSTreeNode(4);
        BSTreeNode node2 = new BSTreeNode(2);
        BSTreeNode node5 = new BSTreeNode(5);
        BSTreeNode node7 = new BSTreeNode(7);
        BSTreeNode node8 = new BSTreeNode(8);

        tree.insertNode(node6);
        tree.insertNode(node4);
        tree.insertNode(node2);
        tree.insertNode(node5);
        tree.insertNode(node7);
        tree.insertNode(node8);

        List<BSTreeNode> treeNodes = tree.preorderWalk();
        System.out.println(treeNodes);
        List<BSTreeNode> treeNodes1 = tree.inorderWalk();
        System.out.println(treeNodes1);
        List<BSTreeNode> treeNodes2 = tree.postorderWalk();
        System.out.println(treeNodes2);

        BSTreeNode BSTreeNode = tree.searchNode(6);
        System.out.println(BSTreeNode);
        BSTreeNode BSTreeNode1 = tree.searchNode(9);
        System.out.println(BSTreeNode1);
        BSTreeNode BSTreeNode2 = tree.searchNode(8);
        System.out.println(BSTreeNode2);
        BSTreeNode BSTreeNode3 = tree.searchNode(5);
        System.out.println(BSTreeNode3);

        BSTreeNode minimum = tree.minimum();
        System.out.println(minimum);
        BSTreeNode maximum = tree.maximum();
        System.out.println(maximum);

        BSTreeNode successor = tree.successor(node2);
        System.out.println(successor);
        BSTreeNode predecessor = tree.predecessor(node7);
        System.out.println(predecessor);

        tree.insertNode(new BSTreeNode(9));
        tree.insertNode(new BSTreeNode(3));
        tree.insertNode(new BSTreeNode(1));
        tree.insertNode(new BSTreeNode(10));
        tree.insertNode(new BSTreeNode(1)); // 重复add
        List<BSTreeNode> insertResult = tree.inorderWalk();
        System.out.println(insertResult);
    }

    private void case2() {
        BinarySearchTree tree = new BinarySearchTree();

        BSTreeNode[] nodes = new BSTreeNode[]{
                new BSTreeNode(2),
                new BSTreeNode(5),
                new BSTreeNode(7),
                new BSTreeNode(9),
                new BSTreeNode(11),
                new BSTreeNode(16),
                new BSTreeNode(17),
                new BSTreeNode(35),
                new BSTreeNode(29),
                new BSTreeNode(38),
        };

        List<BSTreeNode> nodeList = Arrays.asList(nodes);
        Collections.shuffle(nodeList);

        for (BSTreeNode node : nodeList) {
            tree.insertNode(node);
        }
        System.out.println(tree.inorderWalk());

        tree.deleteNode(new BSTreeNode(5));
        tree.deleteNode(new BSTreeNode(0));
        tree.deleteNode(new BSTreeNode(35));
        tree.deleteNode(new BSTreeNode(17));
        System.out.println(tree.inorderWalk());
    }
}
