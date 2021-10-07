package demo.structure;

import demo.data.LinkedListNode;

public class LinkedList<T extends Comparable<T>> {
    private LinkedListNode<T> head;
    private int size;

    public void add(T data, int position) {
        if (isEmpty() || position >= size - 1) {
            head = new LinkedListNode<>(data);
            return;
        }

        int positionCounter = 0;
        LinkedListNode<T> previous = null;
        LinkedListNode<T> current = head;
        while (positionCounter != position) {
            previous = current;
            current = current.getNext();
            positionCounter++;
        }

        LinkedListNode<T> newNode = new LinkedListNode<>(data);
        if (previous != null) {
            previous.setNext(newNode);
            newNode.setNext(current);
        }
    }

    public void add(T data) {
        if (isEmpty()) {
            head = new LinkedListNode<>(data);
            size++;
            return;
        }

        LinkedListNode<T> current = head;
        LinkedListNode<T> newNode = new LinkedListNode<>(data);

        while (current.getNext() != null) {
            current = current.getNext();
        }

        current.setNext(newNode);
        size++;
    }

    public boolean search(T data) {
        if (isEmpty()) {
            return false;
        }

        LinkedListNode<T> current = head;
        while (current.getNext() != null) {
            if (current.getData() != data) {
                current = current.getNext();
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * Bubble sort algorithm
     *
     * @param data to sort
     * @return sorted data
     */
    public int[] sort(int[] data) {
        for (int indexOne = 0; indexOne < data.length - 1; indexOne++) {
            for (int indexTwo = 0; indexTwo < data.length - indexOne - 1; indexTwo++) {
                if (data[indexTwo] > data[indexTwo + 1]) {
                    int temp = data[indexTwo];
                    data[indexTwo] = data[indexTwo + 1];
                    data[indexTwo + 1] = temp;
                }
            }
        }

        return data;
    }

    public LinkedList<T> sort(LinkedList<T> list) {
        for (int indexOne = 0; indexOne < list.size - 1; indexOne++) {
            for (int indexTwo = 0; indexTwo < list.size - indexOne - 1; indexTwo++) {
                if (list.get(indexTwo).compareTo(list.get(indexTwo + 1)) > 0) {
                    T temp = list.get(indexTwo);
                    list.update(indexTwo, list.get(indexTwo + 1));
                    list.update(indexTwo + 1, temp);
                }
            }
        }

        return list;
    }

    public T get(int position) {
        if (isEmpty() || position >= size - 1) {
            return null;
        }

        int positionCounter = 0;
        LinkedListNode<T> current = head;
        while (positionCounter != position) {
            current = current.getNext();
            positionCounter++;
        }

        return current.getData();
    }

    public void delete(T data) {
        if (isEmpty()) {
            return;
        }

        LinkedListNode<T> previous = null;
        LinkedListNode<T> current = head;
        while (current.getNext() != null && current.getData() != data) {
            previous = current;
            current = current.getNext();
        }

        if (previous != null) {
            previous.setNext(current.getNext());
            size--;
        }
    }

    public void update(int position, T data) {
        if (isEmpty() || position >= size - 1) {
            return;
        }

        int positionCounter = 0;
        LinkedListNode<T> current = head;
        while (positionCounter != position) {
            current = current.getNext();
            positionCounter++;
        }

        current.setData(data);
    }

    public void traverse() {
        if (isEmpty()) {
            return;
        }

        System.out.print("\nHEAD -> ");

        LinkedListNode<T> current = head;
        while (current != null) {
            System.out.print(current.getData() + " -> ");
            current = current.getNext();
        }

        System.out.print("NULL\n");
    }

    public LinkedList<T> merge(LinkedList<T> linkedListOne, LinkedList<T> linkedListTwo) {
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
}
