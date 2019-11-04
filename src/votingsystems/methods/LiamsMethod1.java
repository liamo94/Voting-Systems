package votingsystems.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import votingsystems.utilities.Generator;
import votingsystems.utilities.SortingHelper;
import votingsystems.utilities.VotingHelper;

public class LiamsMethod1 extends VotingSystem{
    
    Generator generator;
    Map<String, Integer> votes = new HashMap<>();
    private List<Character> candidates = new ArrayList<>();
    private int numberOfCandidates;
    private Map<Character, Integer> results = new HashMap<>();
    private List<Character> winnerOrder = new ArrayList<>();
    private char winner;
    
    public LiamsMethod1(Generator generator) {
        this.generator = generator;
        votes = generator.getVotes();
        numberOfCandidates = generator.getNumberOfCandidates();
        candidates = VotingHelper.getCandidates(numberOfCandidates);
        run();
    }
    
    public void run() {
        if (generator.getNumberOfCandidates() > 0) {
            findWinners();
            calculateScores();
        }
    }
    
    public char getWinner() {
        return winner;
    }

    public List<Character> getWinningOrder() {
        return winnerOrder;
    }

    private void calculateScores() {
        for(int i = 0; i < numberOfCandidates; i++) {
            results.put(candidates.get(i), 0);
        }
        Iterator<Map.Entry<String, Integer>> it = votes.entrySet().iterator();
        int score = 0;
        while(it.hasNext()) {
            Entry<String, Integer> ballot = it.next();
            for(int i = 0; i < ballot.getKey().length(); i++) {
                score = (ballot.getValue() * (numberOfCandidates - i)) - (ballot.getValue() * i);
                results.put(ballot.getKey().charAt(i), results.get(ballot.getKey().charAt(i)) + score);
            }
        }
        findWinners();
    }
    
    private void findWinners() {
        Map<Character, Integer> sorted = SortingHelper.getOrderedList(results);
        winnerOrder = new ArrayList<>(sorted.keySet());
        winner = winnerOrder.isEmpty() ? 0 : winnerOrder.get(0);
    }

}
