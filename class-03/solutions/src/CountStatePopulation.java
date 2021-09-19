import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CountStatePopulation {
    public static void main(String[] args) {
        String filename = "state_populations.txt";
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            processStateFile(scanner);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    public static void processStateFile(Scanner scanner) {
        int total = 0;

        // throw away the first line
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] cells = line.split("\t");
            String population = cells[cells.length - 1];
            population = population.replace(",", "");

            // 10 says it's a base ten number,
            // as opposed to being binary, or hexadecimal
            // Integer.parseInt("1101", 2) => 8 + 4 + 1 = 13
            int pop = Integer.parseInt(population, 10);
            total += pop;
        }
        System.out.println("Total population: " + total);
    }
}
