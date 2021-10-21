package structure;

import data.Node;

public class BinaryTreeOLD {
    Node root;

    public void inOrderTraverse(Node node) {
        if (node != null) {
            inOrderTraverse(node.getLeft()); // traverse left sub tree
            System.out.print(" " + node.getKey()); // root
            inOrderTraverse(node.getRight()); // traverse right sub tree
        }
    }

    public void postOrderTraverse(Node node) {
        if (node != null) {
            postOrderTraverse(node.getLeft()); // traverse left sub tree
            postOrderTraverse(node.getRight()); // traverse right sub tree
            System.out.print(" " + node.getKey()); // root
        }
    }


    public void preOrderTraverse(Node node) {
        if (node != null) {
            System.out.print(" " + node.getKey()); // root
            preOrderTraverse(node.getLeft()); // traverse left sub tree
            preOrderTraverse(node.getRight()); // traverse right sub tree
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
