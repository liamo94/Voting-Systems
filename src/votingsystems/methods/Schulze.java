package votingsystems.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import votingsystems.utilities.Generator;
import votingsystems.utilities.SortingHelper;
import votingsystems.utilities.VotingHelper;

public class Schulze extends VotingSystem {
    
    Generator generator;
    Map<String, Integer> votes = new HashMap<>();
    private List<Character> candidates = new ArrayList<>();
    private int numberOfCandidates;
    private Map<Character, Integer> results = new HashMap<>();
    private List<Character> winnerOrder = new ArrayList<>();
    private char winner;
    
    public Schulze(Generator generator) {
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
        int [][] winningMatrix = calculateStrongestPaths(VotingHelper.fillPairWise(votes, candidates, numberOfCandidates));
        int previousValue = 0;
        for (int i = 0; i < numberOfCandidates - 1; i++) {
            for (int j = i + 1; j < numberOfCandidates; j++) {
                if (winningMatrix[i][j] > winningMatrix[j][i]) {
                    previousValue = null == results.get(candidates.get(i)) ? 0 : results.get(candidates.get(i));
                    if (null == results.get(candidates.get(j))) {
                        results.put(candidates.get(j), 0);
                    }
                    results.put(candidates.get(i), previousValue + 1);
                } else {
                    previousValue = null == results.get(candidates.get(j)) ? 0 : results.get(candidates.get(j));
                    results.put(candidates.get(j), previousValue + 1);
                }
            }
        }
    }
    
    private int[][] calculateStrongestPaths(int[][] pairwiseComparison) {
        int [][] strongestPath = new int[numberOfCandidates][numberOfCandidates];
        for (int i = 0; i < numberOfCandidates; i++) {
            for (int j = 0; j < numberOfCandidates; j++) {
                if (i != j) {
                    strongestPath[i][j] = pairwiseComparison[i][j] > pairwiseComparison[j][i] ?
                            pairwiseComparison[i][j] :  0;
                }
            }
        }
        for (int i = 0; i < numberOfCandidates; i++) {
            for (int j = 0; j < numberOfCandidates; j++) {
                if (i != j) {
                    for (int k = 0; k < numberOfCandidates; k++) {
                        if (i != k && j != k){
                            strongestPath[j][k] = Math.max(strongestPath[j][k], (Math.min(strongestPath[j][i], strongestPath[i][k])));
                        }
                    }
                }
            }
        }
        return strongestPath;
    }

}
