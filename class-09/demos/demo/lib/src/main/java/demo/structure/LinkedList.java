package demo.structure;

import demo.data.LinkedListNode;

public class LinkedList {
    private LinkedListNode head;
    private int size;

    public LinkedList() {
    }

    public void add(String data) {
        // check if list empty
        // if it is make head point to the new node
        if (head == null) {
            LinkedListNode node = new LinkedListNode(data);
            head = node;
            size++;
        } else {
            // else traverse the list and get to the end
            // make last node point to the new node
            LinkedListNode current;
            current = head;
            while (current.getNext() != null) {
                // moves the current reference along the list
                current = current.getNext();
            }

            LinkedListNode node = new LinkedListNode(data);
            current.setNext(node);
            size++;
        }
    }

    public String remove(int index) throws IndexOutOfBoundsException {
        String deletedNode = "";
        if (head == null) {
            System.out.println("List is empty");
        } else {
            if (index > size || index < 0) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }

            // if deleting first node
            if (index == 0) {
                deletedNode = head.getData();
                head = head.getNext();
                size--;
                return deletedNode;
            }

            int counter = 0;

            LinkedListNode previous = null;
            LinkedListNode current = head;

            while (counter != index) {
                previous = current;
                current = current.getNext();
                counter++;
            }

            previous.setNext(current.getNext());
            current.setNext(null);
            deletedNode = current.getData();
            size--;
        }

        return deletedNode;
    }

    public void printList() {
        if (head == null) {
            System.out.println("List is empty");
        } else {
            LinkedListNode current;
            current = head;

            System.out.print("HEAD -> ");

            while (current != null) {
                // moves the current reference along the list
                System.out.print(current + " -> ");
                current = current.getNext();
            }

            System.out.println("NULL");
        }
    }

    public int size() {
        return size;
    }

    public String kFromEnd(int k) {
        if (head == null) {
            return "The list is empty";
        } else {
            // this is the index starting
            // from the beginning
            int index = (size - k) - 1;

            LinkedListNode current;
            current = head;

            int counter = 0;

            while (counter != index) {
                current = current.getNext();
                counter++;
            }

            return current.getData();
        }
    }

    // Big O of n
//    public int size() {
//        // inconsequnntial
//        int counter = 0;
//        LinkedListNode current;
//        current = head;
//
//        // consequnetial
//        while (current != null) {
//            current = current.getNext();
//            counter++;
//        }
//
//        return counter;
//    }
}
