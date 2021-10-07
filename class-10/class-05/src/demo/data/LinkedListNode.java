package demo.data;

public class LinkedListNode<T extends Comparable<T>> implements Comparable<LinkedListNode<T>> {

    private T data;
    private LinkedListNode<T> next;

    public LinkedListNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    @Override
    public int compareTo(LinkedListNode<T> linkedListNode) {
        return getData().compareTo(linkedListNode.getData());
    }
}
