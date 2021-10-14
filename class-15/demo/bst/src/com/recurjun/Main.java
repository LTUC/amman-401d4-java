package com.recurjun;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.insert(50);
        binarySearchTree.insert(10);
        binarySearchTree.insert(60);
        binarySearchTree.insert(5);
        binarySearchTree.insert(20);
        binarySearchTree.insert(55);
        binarySearchTree.insert(70);

        BinarySearchTree<String> jason = new BinarySearchTree<>();
        jason.insert("J");
        jason.insert("J");
        jason.insert("A");
        jason.insert("S");
        jason.insert("O");
        jason.insert("N");

//        System.out.println("Inorder traversal");
//        binarySearchTree.inorderTraversal();

        System.out.println("Inorder traversal of Jason");
        jason.inorderTraversal();
    }
}
