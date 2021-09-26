import challenge.Challenge01;
import demo.Demo01;
import lab.Lab01;

/**
 * This is the main class which has the main
 * method used to kickstart my demo program
 */
public class Main {
    /**
     * tyhings to nevewr writre as aprogrammer
     *
     * foo
     * x
     * y
     * z
     * test
     *
     * the entire alphabet as variable names
     */

    // primitive types
    int myAge = 32;
    boolean isLecturer = true;
    double salary = 300.00;
    byte weight = 67;
    float ageOfUniverse = 8989898.0909f;
    long jeffBezosWealth = 8734873874L;
    char letter = 'A';
    int tribePopulation = 32767;

    // reference type but declared as a string literal
    String name = "jason";
    Object myFirstObject = new Object();

    public static void main(String[] args) {
        lab01();
//        challenge01();
//        demoO1();
    }

    private static void lab01() {
        Lab01 lab01 = new Lab01();

        System.out.println("\nPluralise");
        int dogCount = 1;
        System.out.println("I own " + dogCount + " " + lab01.pluralize("dog", dogCount) + ".");

        int catCount = 2;
        System.out.println("I own " + catCount + " " + lab01.pluralize("cat", catCount) + ".");

        int turtleCount = 0;
        System.out.println("I own " + turtleCount + " " + lab01.pluralize("turtle", turtleCount) + ".");

        System.out.println("\nFlipNHeads");
        int number = 7;
        System.out.println("It took " + lab01.flipNHeads(number) + " flips to flip " + number + " heads in a row");

        System.out.println("\nClock");
//        lab01.clock1();
//        lab01.clock2();
//        lab01.clockUpgrade();
    }

    private static void challenge01() {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8};
        Challenge01 challenge01 = new Challenge01();
        int[] result = challenge01.reverseArray(data);

        System.out.println("Array in reverse");

        for (int index = 0; index < data.length; index++) {
            System.out.print(result[index]);
        }
    }

    private static void demoO1() {
        Demo01.CLASS_NAME = "";

        Demo01 demo01 = new Demo01();
        Demo01 demo02 = new Demo01("You all will pass with high marks");
        Demo01 demo03 = new Demo01("You all will get great jobs", 23);

        demo01.myAge = 45;
        demo02.myAge = 45;
        demo03.myAge = 45;

        final int year = 2021;

//        System.out.println(demo01.getWelcomeText());
//        demo01.addNumbers(2, 3);
//        demo01.addNumbers(2, 3, 4);
//        demo01.printText();
//        demo01.setWelcomeText("You have to work hard");
//        System.out.println(demo01.getWelcomeText());
        System.out.println(Demo01.CLASS_NAME);
//        demo01.variables(); // throws error cause the method is private
    }
}
