import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayListElectionTest {
  @Test
  public void testTally() {
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

    String winner = ArrayListElection.tally(votes);
    assertEquals("Bush", winner);
  }
}
