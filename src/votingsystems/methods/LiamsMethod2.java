package votingsystems.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import votingsystems.utilities.Generator;
import votingsystems.utilities.SortingHelper;
import votingsystems.utilities.VotingHelper;

public class LiamsMethod2 extends VotingSystem{

    Generator generator;
    Map<String, Integer> votes = new HashMap<>();
    private List<Character> candidates = new ArrayList<>();
    private int numberOfCandidates;
    private Map<Character, Integer> results = new HashMap<>();
    private List<Character> winnerOrder = new ArrayList<>();
    private char winner;
    
    public LiamsMethod2(Generator generator) {
        this.generator = generator;
        votes = generator.getVotes();
        numberOfCandidates = generator.getNumberOfCandidates();
        candidates = SortingHelper.getCandidates(numberOfCandidates);
    }
    
    public void run() {
        if (generator.getNumberOfCandidates() > 0) {
            calculateScores();
            findWinners();
        }
    }
    
    public char getWinner() {
        return winner;
    }

    public List<Character> getWinningOrder() {
        return winnerOrder;
    }

    private void findWinners() {
        Map<Character, Integer> sorted = SortingHelper.getOrderedList(results);
        winnerOrder = new ArrayList<>(sorted.keySet());
        winner = winnerOrder.isEmpty() ? '!' : winnerOrder.get(0);
        
    }

    private void calculateScores() {
        int [][] winningMatrix = VotingHelper.fillPairWise(votes, candidates, numberOfCandidates);
        int previousValuei = 0;
        int previousValuej = 0;
        int difference = 0;
        for (int i = 0; i < numberOfCandidates - 1; i++) {
            for (int j = i + 1; j < numberOfCandidates; j++) {
                previousValuei = null == results.get(candidates.get(i)) ? 0 : results.get(candidates.get(i));
                previousValuej = null == results.get(candidates.get(j)) ? 0 : results.get(candidates.get(j));
                if (winningMatrix[i][j] > winningMatrix[j][i]) {
                    if (null == results.get(candidates.get(j))) {
                        results.put(candidates.get(j), 0);
                    }
                    difference = winningMatrix[i][j] - winningMatrix[j][i];
                    results.put(candidates.get(i), previousValuei + difference);
                    
                } else {
                    difference = winningMatrix[j][i] - winningMatrix[i][j];
                    results.put(candidates.get(j), previousValuej + difference);
                }
            }
        }
    }

}
