import java.util.HashSet;
import java.util.Set;

public class UnseenTemperatures {
  public static void main(String[] args) {
    int[][] temps = {
        {66, 64, 58, 65, 71, 57, 60},
        {57, 65, 65, 70, 72, 65, 51},
        {55, 54, 60, 53, 59, 57, 61},
        {65, 56, 55, 52, 55, 62, 57},
    };

    int min = temps[0][0];
    int max = temps[0][0];
    Set<Integer> seenTemps = new HashSet<>();
    for (int week = 0; week < temps.length; week++) {
      for (int day = 0; day < temps[week].length; day++) {
        int temp = temps[week][day];
        seenTemps.add(temp);

        min = Math.min(min, temp);
        max = Math.max(max, temp);
      }
    }

    System.out.println("High: " + max);
    System.out.println(" Low: " + min);
    for (int i = min; i < max; i++) {
      if (!seenTemps.contains(i)) {
        System.out.println("Never saw temperature: " + i);
      }
    }
  }
}
