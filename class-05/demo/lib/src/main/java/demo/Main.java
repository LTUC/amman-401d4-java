package demo;

import demo.structure.LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("\nLinked List Demo \n");

        LinkedList linkedList = new LinkedList();
        linkedList.printList();
        linkedList.add("J");
        linkedList.add("A");
        linkedList.add("S");
        linkedList.add("O");
        linkedList.add("N");

        // try with 1_000_000_000
        // try with 1_000_000
//        for (int index = 0; index <= 1_000; index++) {
//            linkedList.add("A");
//        }

        linkedList.printList();
        System.out.println("The List is => " + linkedList.size());

        try {
            System.out.println("The deleted node was => " + linkedList.remove(-3));;
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        linkedList.printList();

        System.out.println("\n----------------------------------------------------------------");
    }
}
