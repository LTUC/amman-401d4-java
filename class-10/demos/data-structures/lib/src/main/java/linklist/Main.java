package linklist;

import generic.JordanUni;
import generic.Luminus;
import generic.Petra;
import generic.Student;
import linklist.structure.LinkedList;
import queue.structure.Queue;
import stack.structure.Stack;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        linkedListDemo();
//        stackDemo();
//        queueDemo();

        genericsDemo();
    }

    private static void genericsDemo() {
        Luminus luminus = new Luminus("Luminus");
        Student<Luminus, String> luminusStudent = new Student<>(luminus, "Math");

        JordanUni jordanUni = new JordanUni("JordanUni");
        Student<JordanUni, String> jordanUniStudent = new Student<>(jordanUni, "Biology");

        Petra petra = new Petra("Petra");
        Student<Petra, String> petraStudent = new Student<>(petra, "Comp Sci");

        Integer integer = 34;
        Student<Integer, String> integerStudent = new Student<>(integer, "Electronics");

        List<Student<Petra, String>> list = new ArrayList<>();
        list.add(petraStudent);

        // TODO: 10/7/21 needs comparable interface implemented on the Student class
//        if (petraStudent.getInstitution() == jordanUniStudent.getInstitution()) {
//            System.out.println("");
//        }

        System.out.println("The institution => " + luminusStudent.getInstitution());
        System.out.println("The institution => " + jordanUniStudent.getInstitution());
        System.out.println("The institution => " + petraStudent.getInstitution());
        System.out.println("The institution => " + integerStudent.getInstitution());
    }

    private static void linkedListDemo() {
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

        LinkedList linkedList2 = new LinkedList();
        linkedList2.add("A");
        linkedList2.add("B");
        linkedList2.add("C");
        linkedList2.add("D");
        linkedList2.add("F");

        System.out.println(linkedList2.kFromEnd(1));
    }

    private static void stackDemo() {
        Stack stack = new Stack();

        stack.push("J");
        stack.push("A");
        stack.push("S");
        stack.push("O");
        stack.push("N");

        System.out.println("The top of the stack is => " + stack.peek());

        System.out.println(stack.pop()); // N comes off

        System.out.println("The top of the stack is => " + stack.peek()); // is O

        System.out.println(stack.pop()); // pop O
        System.out.println(stack.pop()); // pop s
        System.out.println(stack.pop()); // pop a
        System.out.println(stack.pop()); // pop j
        System.out.println(stack.pop()); // stack empty
    }

    private static void queueDemo() {
        Queue queue = new Queue();

        queue.enqueue("J");
        queue.enqueue("A");
        queue.enqueue("S");
        queue.enqueue("O");
        queue.enqueue("N");

        System.out.println(queue.dequeue()); // prints J
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println("The front of the Queue is => " + queue.peek()); // prints O
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue()); // prints the queue is empty
    }
}
