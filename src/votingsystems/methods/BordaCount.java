package votingsystems.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import votingsystems.utilities.Generator;
import votingsystems.utilities.SortingHelper;

public class BordaCount extends VotingSystem {

    private Map<String, Integer> votes = new HashMap<>();
    private Map<Character, Integer> results = new HashMap<>();
    private int numberOfCandidates;
    private char winner;
    private List<Character> winnerOrder = new ArrayList<>();
    
    public BordaCount(Generator generator) {
        this.votes = generator.getVotes();
        votes = generator.getVotes();
        numberOfCandidates = generator.getNumberOfCandidates();
        run();
    }
    
    public void run() {
        calculateScores();
    }
    
    private void calculateScores() {
        Iterator<Map.Entry<String, Integer>> it = votes.entrySet().iterator();
        while(it.hasNext()) {
            Entry<String, Integer> ballot = it.next();
            int score = numberOfCandidates;
            for(int i = 0; i < ballot.getKey().length(); i++) {
                if (results.containsKey(ballot.getKey().charAt(i))) {
                    int previousValue = results.get(ballot.getKey().charAt(i));
                    results.put(ballot.getKey().charAt(i), previousValue + (ballot.getValue() * score));
                } else {
                    results.put(ballot.getKey().charAt(i), (ballot.getValue() * score));
                }
                score--;
            }
        }
        findWinners();
    }
    
    private void findWinners() {
        Map<Character, Integer> sorted = SortingHelper.getOrderedList(results);
        winnerOrder = new ArrayList<>(sorted.keySet());
        winner = winnerOrder.isEmpty() ? '!' : winnerOrder.get(0);
    }

    public char getWinner() {
        return winner;
    }

    public List<Character> getWinningOrder() {
        return winnerOrder;
    }

}
