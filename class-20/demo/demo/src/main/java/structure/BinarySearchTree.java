package structure;

import data.Node;

public class BinarySearchTree {
    Node root;

    public void add(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            Node current = root;
            traverse(current, key);
        }
    }

    private void traverse(Node current, int key) {
        if (key > current.getKey()) {
            if (current.getRight() == null) {
                current.setRight(new Node(key));
                return;
            }

            current = current.getRight();
        } else {
            if (current.getLeft() == null) {
                current.setLeft(new Node(key));
                return;
            }

            current = current.getLeft();
        }
        traverse(current, key);
    }
}
