package linklist.structure;

import data.Node;

public class LinkedList {
    private Node head;
    private int size;

    public LinkedList() {
    }

    public void add(String data) {
        // check if list empty
        // if it is make head point to the new node
        if (head == null) {
            Node node = new Node(data);
            head = node;
            size++;
        } else {
            // else traverse the list and get to the end
            // make last node point to the new node
            Node current;
            current = head;
            while (current.getNext() != null) {
                // moves the current reference along the list
                current = current.getNext();
            }

            Node node = new Node(data);
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

            Node previous = null;
            Node current = head;

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
            Node current;
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

            Node current;
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
//        Node current;
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
