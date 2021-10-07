package queue.structure;

import data.Node;

public class Queue {

    private Node front;
    private Node rear;

    public Queue() {
    }

    public void enqueue(String data) {
        if (isEmpty()) {
            Node node = new Node(data);
            front = node;
            rear = node;
        } else {
            Node node = new Node(data);
            rear.setNext(node);
            rear = node;
        }
    }

    public String dequeue() {
         if (isEmpty()) {
             return "Queue is empty";
         } else {
             String data = front.getData();
             front = front.getNext();
             return data;
         }
    }

    public String peek() {
        return front.getData();
    }

    public boolean isEmpty() {
        return front == null;
    }
}
