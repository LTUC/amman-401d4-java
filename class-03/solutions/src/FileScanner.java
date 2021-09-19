import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileScanner {
  public static void main(String[] args) {
    processMovies();
  }

  public static void processMovies() {
    try {
      String filename = "top100.txt";
      File file = new File(filename);
      Scanner scanner = new Scanner(file);

      int movies = 0;
      int totalYears = 0;

      while (scanner.hasNextLine()) {
        movies++;

        int year = scanner.nextInt();
        totalYears += year;

        // consume the next line to move the Scanner forward
        scanner.nextLine();
      }

      System.out.println("Average top 100 year: " + (totalYears / movies));

    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Problem opening file. Program aborted");
    }
  }
}
