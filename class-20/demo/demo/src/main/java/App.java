import data.BTNode;
import data.Node;
import structure.BinarySearchTree;
import structure.BinaryTree;
import structure.BinaryTreeOLD;

public class App {

    public static void main(String[] args) {
        System.out.println("Binary Tree");

        // creates the binary tree
        BinaryTreeOLD binaryTreeOLD = new BinaryTreeOLD();

        // adds nodes to the tree
        binaryTreeOLD.setRoot(new Node(1));
        binaryTreeOLD.getRoot().setLeft(new Node(2));
        binaryTreeOLD.getRoot().setRight(new Node(3));
        binaryTreeOLD.getRoot().getLeft().setLeft(new Node(4));

        System.out.println("inorder");
        binaryTreeOLD.inOrderTraverse(binaryTreeOLD.getRoot());

        System.out.println();
        System.out.println("postorder");
        binaryTreeOLD.postOrderTraverse(binaryTreeOLD.getRoot());

        System.out.println();
        System.out.println("preorder");
        binaryTreeOLD.preOrderTraverse(binaryTreeOLD.getRoot());

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.add(5);
        binarySearchTree.add(7);
        binarySearchTree.add(3);
        binarySearchTree.add(10);
    }

    private static void binaryTreeSimulator(BinaryTree binaryTree) {
        binaryTree.setRoot(new BTNode("J"));
        binaryTree.getRoot().setLeft(new BTNode("A"));
        binaryTree.getRoot().setRight(new BTNode("S"));
        binaryTree.getRoot().getLeft().setLeft(new BTNode("O"));
        binaryTree.getRoot().getLeft().setRight(new BTNode("N"));

        binaryTree.levelOrderTraversalLoop();
        System.out.println("\n");
        binaryTree.levelOrderTraversalRecursive();
    }
}
