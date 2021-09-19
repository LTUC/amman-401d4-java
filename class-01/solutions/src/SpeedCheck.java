import java.time.LocalDateTime;
import java.util.Random;

public class SpeedCheck {
  public static void main(String[] args) {
    speedCheck();
  }

  public static void speedCheck() {
    Random r = new Random();
    long start = System.currentTimeMillis();
    long end;

    long cycles = 0;
    long billion = 1_000_000_000;

    while (cycles < billion) {
      cycles++;

      // comment or uncomment these lines and see their effect on speed.
      // 3.574 GHz
      // int x = 0;

      // 3.59 GHz
      // int y = 2 * 2;

      // 3.65 GHz
      // int z = 13 * 97;

      // 44.65 MHz w/ double
      // double x = 43 * r.nextDouble();
    }

    end = System.currentTimeMillis();
    long delta = end - start;
    long cyclesPerSecond = 1000 * cycles / delta;

    String speed = "Hz";
    long decimals = 0;
    if (cyclesPerSecond > 1_000_000_000){
      decimals = cyclesPerSecond % 1_000_000;
      cyclesPerSecond /= 1_000_000_000.0;
      speed = "GHz";
    } else if (cyclesPerSecond > 1_000_000){
      decimals = cyclesPerSecond % 1_000;
      cyclesPerSecond /= 1_000_000.0;
      speed = "MHz";
    } else if (cyclesPerSecond > 1000) {
      decimals = cyclesPerSecond % 1_000;
      cyclesPerSecond /= 1000.0;
      speed = "KHz";
    }

    System.out.println("Max INT: " + Integer.MAX_VALUE);
    System.out.println("Max LONG: " + Double.MAX_VALUE);
    System.out.println("Max LONG: " + Long.MAX_VALUE);
    System.out.println(cyclesPerSecond + "." + decimals + " " + speed);
  }
}
