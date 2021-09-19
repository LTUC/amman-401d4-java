import java.time.LocalDateTime;
import java.util.Date;

public class CommandLineClock {
  public static void main(String[] args) {
    printClock();
  }

  public static void printClock() {
    LocalDateTime now = LocalDateTime.now();
    int lastSeenSecond = now.getSecond();
    double cycles = 0;

    while (true) {
      cycles++;
      now = LocalDateTime.now();

      // only actually print the time if the seconds have changed.
      if (now.getSecond() != lastSeenSecond) {
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        // update the last seen second.
        lastSeenSecond = second;

        String sHour = "" + hour;
        String sMinute = "" + minute;
        String sSecond = "" + second;

        // zero-pad all single-digit numbers.
        if (hour < 10) {
          sHour = "0" + sHour;
        }
        if (minute < 10) {
          sMinute = "0" + sMinute;
        }
        if (second < 10) {
          sSecond = "0" + sSecond;
        }

        String speed = "Hz";
        if (cycles > 1_000_000_000){
          cycles /= 1_000_000_000;
          speed = "GHz";
        } else if (cycles > 1_000_000){
          cycles /= 1_000_000;
          speed = "MHz";
        } else if (cycles > 1000) {
          cycles /= 1000;
          speed = "KHz";
        }

        System.out.println(sHour + ":" + sMinute + ":" + sSecond + " " + cycles + " " + speed);

        cycles = 0;
      }
    }
  }
}
