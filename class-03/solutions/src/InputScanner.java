import java.util.Scanner;

public class InputScanner {
  public static void main(String[] args) {
    gatherCityStateZip();
  }

  public static void gatherCityStateZip() {
    Scanner input = new Scanner(System.in);

    System.out.print("City? ");
    String city = input.nextLine();
    System.out.print("State? ");
    String state = input.nextLine();
    System.out.print("Zip Code? ");
    String zip = input.nextLine();

    System.out.println(city + ", " + state + " " + zip);
  }
}
