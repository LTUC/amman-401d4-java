package demo;

public class Main {

    public static void main(String[] args) {
        System.out.println("hello world");

        Library demo = new Library();
        demo.someLibraryMethod();
        demo.looping(2, "Jason");
        demo.scrapper("https://en.wikipedia.org/");
        demo.list();
    }
}