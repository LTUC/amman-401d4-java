import java.util.ArrayList;
import java.util.List;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class Library {
    public boolean someLibraryMethod() {
        return true;
    }
    public static ArrayList<String> reverse(ArrayList<String> stuffToReverse) {
        ArrayList<String> ans = new ArrayList<>();
        for(int i = stuffToReverse.size() - 1; i >= 0; i--) {
            ans.add(stuffToReverse.get(i));
        }
        return ans;
    }

    public static void printArrayList(ArrayList<Integer> list) {

    }

    public static void main(String[] args) {
        ArrayList<String> letters = new ArrayList<>();

        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");

        for (String letter :
                letters) {
            System.out.println(letter);
        }

        System.out.println("\n\n");

        for (String letter :
                reverse(letters)) {
            System.out.println(letter);
        }
    }
}
