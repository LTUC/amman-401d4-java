import demo.structure.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("J");
        linkedList.add("A");
        linkedList.add("S");
        linkedList.add("O");
        linkedList.add("N");

        linkedList.traverse();

        System.out.println("\nFound the data => " + linkedList.search("S"));

        linkedList.update(2, "A");

        linkedList.traverse();

        linkedList.add("X", 1);
        linkedList.traverse();

        int[] unsortedList = {7, 4, 5, 2, 8, 9, 10};

        int[] sortedList = linkedList.sort(unsortedList);
        for (int index = 0; index <= sortedList.length - 1; index++) {
            System.out.print(sortedList[index] + " ");
        }

        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.add(9);
        numbers.add(7);
        numbers.add(3);
        numbers.add(1);
        numbers.add(2);
        numbers.add(10);
        numbers.add(4);

//        numbers.sort(numbers);
//        numbers.traverse();
    }
}
