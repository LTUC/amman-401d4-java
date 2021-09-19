public class ArraysOfArrays {
  public static void main(String[] args) {
    int[][] monthTemps = {
        {66, 64, 58, 65, 71, 57, 60},
        {57, 65, 65, 70, 72, 65, 51},
        {55, 54, 60, 53, 59, 57, 61},
        {65, 56, 55, 52, 55, 62, 57},
    };

    for (int week = 0; week < monthTemps.length; week++) {
      for (int day = 0; day < monthTemps[week].length; day++) {
        System.out.println(monthTemps[week][day]);
      }
    }
  }
}
