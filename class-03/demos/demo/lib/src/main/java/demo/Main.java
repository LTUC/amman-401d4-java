package demo;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();

//        library.consoleReaderNewWay();

//        library.consoleReaderOldWay();

//        library.fileReader();

        library.fileReader(library.consoleReaderNewWay());

        library.createFile();

        System.out.println("LIST result => " + library.listDemo());

        System.out.println("SET result => " + library.setDemo());

        System.out.println("Map result => " + library.mapDemo());
    }
}
