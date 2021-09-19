import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayListElection {
  public static void main(String[] args) {
    List<String> votes = new ArrayList<>();
    votes.add("Bush");
    votes.add("Bush");
    votes.add("Bush");
    votes.add("Shrub");
    votes.add("Hedge");
    votes.add("Shrub");
    votes.add("Bush");
    votes.add("Hedge");
    votes.add("Bush");

    String winner = tally(votes);
    System.out.println(winner + " received the most votes!");
  }

  public static String tally(List<String> votes) {
    Map<String, Integer> tally = new HashMap<>();

    for (int i = 0; i < votes.size(); i++) {
      String candidate = votes.get(i);

      // start their value at zero if the candidate has never been seen.
      if (!tally.containsKey(candidate)) {
        tally.put(candidate, 0);
      }

      // add one to their number of votes and set it again.
      int voteCount = tally.get(candidate);
      tally.put(candidate, voteCount + 1);
    }

    String best = null;
    int max = 0;

    for (String candidate : tally.keySet()) {
      int voteCount = tally.get(candidate);
      if (voteCount > max) {
        max = voteCount;
        best = candidate;
      }
    }

    return best;
  }
}
