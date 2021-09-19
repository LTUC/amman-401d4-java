import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TokenizedInput {
  public static void main(String[] args) {
    try {
      String filename = "example.txt";
      File file = new File(filename);
      Scanner scanner = new Scanner(file);


      boolean doYouWantThisProgramToCrash = false;
      if (doYouWantThisProgramToCrash) {
        blindTokenEater(scanner);
      } else {
        lookAheadTokenEater(scanner);
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Problem accessing file.");
    }
  }

  public static void blindTokenEater(Scanner scanner) {
    while (true) {
      String token = scanner.next();
      System.out.println(token);
    }
  }

  public static void lookAheadTokenEater(Scanner scanner) {
    while (scanner.hasNext()) {
      // notice that the token "400!" with the exclamation mark doesn't
      // count as an integer. "400!" counts as a string and would require
      // our program to do more manual processing on individual tokens to
      // detect and manipulate integers mixed up with punctuation like this.
      if (scanner.hasNextInt()) {
        int n = scanner.nextInt();
        System.out.println("NUMBER: " + n);
      } else {
        String token = scanner.next();
        System.out.println(token);
      }
    }
  }
}
