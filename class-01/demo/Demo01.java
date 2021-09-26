/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demo;

/**
 * the class name has to match the name of the file
 * The HelloWorldApp class implements an application that
 * simply prints "Hello World!" to standard output.
 *
 * This is the Demo class for the first class of
 * 401 Java d4 which demonstrates and introduces Java
 */
public class Demo01 {

    // constant in java
    public static final String MY_NAME = "JASON";

    // this belongs to the class
    public static String CLASS_NAME = "401 D3";

    // the following belong to instances
    protected String welcomeText = "Hello Class";

    // the following belong to instances
    public int myAge;
    public double test;
    public long salary;

    /**
     * Default constructor
     *
     * is to initialize variables of the class
     */
    public Demo01() {
        System.out.println("Hi my name is the Constructor");

        System.out.println("my age before initialization => " + myAge);

        myAge = 32;

        System.out.println("my age after initialization => " + myAge);
    }

    public Demo01(String welcomeText) {
        this.welcomeText = welcomeText;
        System.out.println(welcomeText);
    }

    public Demo01(String welcomeText, int myAge) {
        this.welcomeText = welcomeText;
        System.out.println(welcomeText);
        System.out.println(myAge);
    }

    /**
     * Prints some text
     */
    public void printText() {
        System.out.println(welcomeText + CLASS_NAME);
    }

    /**
     * Adds two numbers together
     *
     * @param numberOne first number to add
     * @param numberTwo second number to add
     * @return the sum of the two numbers
     */
    public int addNumbers(int numberOne, int numberTwo) {
        return numberOne + numberTwo;
    }

    public int addNumbers(int numberOne, int numberTwo, int numberThree) {
        return addNumbers(numberOne, numberTwo) + numberThree;
    }

    private void variables() {
        boolean isFinished;
        int account;
        double balance;
        float salary;
        String name;
    }

    public String getWelcomeText() {
        return welcomeText;
    }

    public void setWelcomeText(String welcomeText) {
        this.welcomeText = welcomeText;
    }
}
