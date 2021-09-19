import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class USPopulation {
  public static void main(String[] args) {
    processStatesByToken();
    processStatesByLine();
  }

  // This method processes the file with tokenized input. It makes heavy use
  // of the scanners .next(), .hasNext(), nextInt(), hasNextInt() methods to
  // detect what sort of information it's working with, and retrieve data in
  // the appropriate data types.
  public static void processStatesByToken() {
    try {
      String filename = "state_populations.txt";
      File file = new File(filename);
      Scanner scanner = new Scanner(file);

      // skip the first line in the file that has headers.
      scanner.nextLine();

      int totalPopulation = 0;
      while (scanner.hasNextLine()) {
        String name = scanner.next();
        // gather all words in the state name
        while (!scanner.hasNextInt()) {
          name += " " + scanner.next();
        }

        // consume the state rank number
        scanner.next();

        int population = scanner.nextInt();
        totalPopulation += population;

        System.out.println(name + " has " + population);
      }

      System.out.println("Total Population: " + totalPopulation);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Problem processing file. Program Aborted.");
    }
  }

  // This method processes the file and effectively gets the same results as
  // the other method. The difference here is that it grabs an entire line at
  // a time and does not take advantage of .nextInt(), hasNextInt() methods that
  // the Scanner provides. In this case processing the file line by line requires
  // some more manual work, especially when trying to parse file contents as
  // integers.
  public static void processStatesByLine() {
    try {
      String filename = "state_populations.txt";
      File file = new File(filename);
      Scanner scanner = new Scanner(file);

      // move past the first line to get past the initial column headers
      scanner.nextLine();

      int totalPopulation = 0;
      while (scanner.hasNextLine()) {
        // get the entire line
        String line = scanner.nextLine();

        // split up the line into words separated by spaces.
        String[] tokens = line.split("\t");


        String name = tokens[0];
        for (int i = 1; i < tokens.length - 2; i++) {
          name += " " + tokens[i];
        }

        // Using Integer.parseInt("30,332,521", 10) or any number with commas
        // leads to an error.
        // java.lang.NumberFormatException: For input string: "38,332,521"
        String pop = tokens[tokens.length - 1];
        pop = pop.replace(",", "");
        int population = Integer.parseInt(pop, 10);
        totalPopulation += population;

        System.out.println(name + " has " + population);
      }

      System.out.println("Total Population: " + totalPopulation);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Problem processing file. Program Aborted.");
    }
  }
}
