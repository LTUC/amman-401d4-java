package demo;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Class");

        Table ikeaTable = new Table();

        System.out.println(Table.TABLE_COLOR);

        System.out.println(ikeaTable);

        ikeaTable.setColor("Blue");
        System.out.println(ikeaTable.toString());

        Table mcDonalds = new Table(true, "Wooden", "Brown", new Dimensions(23, 34, 67));

        System.out.println(mcDonalds);

        Human ghadeer = new Human(25, 5.78, 67.7, "Ghadeer", "XX");
        Human sanaa = new Human(33, 5.2, 60.4, "Sanaa", "XX");

        ghadeer.setFriend(sanaa);
        ghadeer.getName();
        sanaa.setFriend(ghadeer);

        System.out.println(ghadeer);
        System.out.println(sanaa);

        System.out.println("The factorial is => " + factorial(5));

        int[] numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        printArrayRecursive(numbers, 0);

        // lines of code
        // lines of code
        // lines of code
        // lines of code
        // lines of code
    }

    /**
     * 5!
     * 5 * 4 * 3 * 2 * 1 = 120
     *
     * math formula
     * n! = n * (n - 1)!
     *
     * @param num
     */
    private static int factorial(int num) {
        // base case condition
        if (num == 1) {
            return 1;
        }

        // recursive call
        return num * factorial(num - 1);
    }

    private static void printArrayRecursive(int[] arr, int index) {
        if (index == arr.length - 1) {
            System.out.println(arr[index]);
            return;
        }

        System.out.println(arr[index]);
        printArrayRecursive(arr, index + 1);
        return;
    }

    private static void printArrayLoop(int[] arr) {
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
