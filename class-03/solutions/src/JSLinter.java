import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by moonmayor on 11/14/17.
 */
public class JSLinter {
  public static void main(String[] args) {
    try {
      lint();
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Sorry, there was an error processing the file.");
    }
  }

  public static void lint() throws IOException {
    String filename = "gates.js";
    File file = new File(filename);
    Scanner scanner = new Scanner(file);

    int lineNo = 0;
    while (scanner.hasNextLine()) {
      lineNo++;
      String line = scanner.nextLine();

      // ignore empty lines. they don't need semicolons.
      if (line.length() > 0) {
        // look at the last letter and see if there's curly braces.
        char lastChar = line.charAt(line.length() - 1);
        if (lastChar != '{' && lastChar != '}' && lastChar != ';') {
          if (!line.contains("if") && !line.contains("else")) {
            String error = "LINE " + lineNo + ": missing semicolon " + line;
            System.out.println(error);
          }
        }
      }
    }

  }
}
