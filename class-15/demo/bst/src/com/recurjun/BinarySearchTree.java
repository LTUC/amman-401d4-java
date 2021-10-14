package com.recurjun;

public class BinarySearchTree<T extends Comparable<T>> {

    private BinaryNode<T> root;

    public void insert(T data) {
        if (isEmpty()) { // tree empty
            root = new BinaryNode<>(data);
        } else {
            insertHelper(data, root);
        }
    }

    public void inorderTraversal() {
        if (isEmpty()) {
            return;
        }

        traverseInorder(root);
    }

    private void traverseInorder(BinaryNode<T> root) {
        if (root.getLeftNode() != null) { // traverse left
            traverseInorder(root.getLeftNode());
        }

        // visit / print the root of the tree (root could be a sub-tree)
        System.out.print(root.getData() + " -> ");

        if (root.getRightNode() != null) { // traverse right
            traverseInorder(root.getRightNode());
        }
    }

    private void insertHelper(T data, BinaryNode<T> root) {
        BinaryNode<T> binaryNode = new BinaryNode<>(data);
        if (data.compareTo(root.getData()) < 0) {
            if (root.getLeftNode() == null) {
                root.setLeftNode(binaryNode);
            } else {
                insertHelper(data, root.getLeftNode());
            }
        } else if(data.compareTo(root.getData()) > 0) {
            if (root.getRightNode() == null) {
                root.setRightNode(binaryNode);
            } else {
                insertHelper(data, root.getRightNode());
            }
        }
    }

    public boolean isEmpty() {
        return root == null;
    }
}
