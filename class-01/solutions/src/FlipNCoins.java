import java.util.Random;

public class FlipNCoins {
  public static void main(String[] args) {
    flipNHeads(3);
  }
  
  public static void flipNHeads(int n) {
    Random r = new Random();
    int flips = 0;
    int heads = 0;
    
    while (heads < n) {
      flips++;
      double flip = r.nextDouble();
      if (flip < .5) {
        // reset the number of heads every time a tails appears.
        heads = 0;
        System.out.println("tails");
      } else {
        heads++;
        System.out.println("heads");
      }
    }

    // It took FLIP flips to flip N heads"
    System.out.println("It took " + flips + " " + Pluralize.pluralize("flip", flips) +
      " to flip " + heads + " " + Pluralize.pluralize("heads", heads) + ".");
  }
}
