package structure;

import data.BTNode;

import java.util.Queue;

public class BinaryTree {

    private BTNode root;

    public BTNode getRoot() {
        return root;
    }

    public void setRoot(BTNode root) {
        this.root = root;
    }

    public void levelOrderTraversalLoop() {
        if (root != null) {
            Queue<BTNode> queue = new Queue<>();
            queue.enqueue(root);

            BTNode node;
            while (!queue.isQueueEmpty()) {
                node = queue.dequeue();
                System.out.print(node.getData() + " => ");
                if (node.getLeft() != null) {
                    queue.enqueue(node.getLeft());
                }

                if (node.getRight() != null) {
                    queue.enqueue(node.getRight());
                }
            }
        } else {
            System.out.println("Tree empty");
        }
    }

    public void levelOrderTraversalRecursive() {
        if (root != null) {
            Queue<BTNode> queue = new Queue<>();
            queue.enqueue(root);
            levelOrderTraversalRecursive(queue);
        }
    }

    private void levelOrderTraversalRecursive(Queue<BTNode> queue) {
        if (!queue.isQueueEmpty()) {
            BTNode node = queue.dequeue();
            System.out.print(node.getData() + " => ");
            if (node.getLeft() != null) {
                queue.enqueue(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.enqueue(node.getRight());
            }

            levelOrderTraversalRecursive(queue);
        }
    }
}
