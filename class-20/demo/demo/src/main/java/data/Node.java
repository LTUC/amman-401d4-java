package data;

public class Node {
    private final int key;
    private Node left;
    private Node right;

    public Node(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
