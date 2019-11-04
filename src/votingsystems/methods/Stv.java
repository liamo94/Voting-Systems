package votingsystems.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import votingsystems.utilities.Generator;
import votingsystems.utilities.SortingHelper;

/**
 * Single Transferable Vote implementation
 * 
 * @author liamodonnell
 *
 */
public class Stv extends VotingSystem{
    
    Generator generator;
    Map<String, Integer> votes = new HashMap<>();
    private Map<Character, Integer> results = new HashMap<>();
    private List<Character> winnerOrder = new ArrayList<>();
    private char winner;
    
    public Stv(Generator generator) {
        this.generator = generator;
        votes = generator.getVotes();
        run();
    }
    
    public void run() {
        if (generator.getNumberOfCandidates() > 0) {
            winnerOrder.clear();
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
        winner = winnerOrder.isEmpty() ? '!' : winnerOrder.get(0);
    }
    
    private void findFirstPlaces() {
        Iterator<Map.Entry<String, Integer>> it = votes.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Integer> ballot = it.next();
            if (results.containsKey(ballot.getKey().charAt(0))) {
                int previousValue = results.get(ballot.getKey().charAt(0));
                results.put(ballot.getKey().charAt(0), previousValue + ballot.getValue());
            } else {
                results.put(ballot.getKey().charAt(0), ballot.getValue());
            }
        }
    }
    
    private void calculateScores() {
        int quota = findQuota();
        findFirstPlaces();
        Map<Character, Integer> candidatesLeft = new HashMap<>(results);
        List<Character> losers = new ArrayList<>();
        while (!candidatesLeft.isEmpty()) {
            char candidateToCheck = canidateHitQuota(candidatesLeft, quota);
            if (candidateToCheck != '!') {
                winnerOrder.add(candidateToCheck);
                reallocateWinnerSeats(candidateToCheck, candidatesLeft, quota);
                candidatesLeft.remove(candidateToCheck);
            } else {
                char loser = checkLowestCandidate(candidatesLeft);
                losers.add(loser);
                reallocateLoserSeats(loser, candidatesLeft);
                candidatesLeft.remove(loser);
            }
        }
        for (int i = losers.size()-1; i >= 0; i--) {
            winnerOrder.add(losers.get(i));
        }
    }
    
    private char checkLowestCandidate(Map<Character, Integer> candidatesLeft) {
        Map<Character, Integer> sorted = SortingHelper.getOrderedList(candidatesLeft);
        List<Character> values = new ArrayList<>(sorted.keySet());
        return values.get(values.size() - 1);
    }
    
    private char canidateHitQuota(Map<Character, Integer> candidatesLeft, int quota) {
        Iterator<Map.Entry<Character, Integer>> it = candidatesLeft.entrySet().iterator();
        while (it.hasNext()) {
            Entry<Character, Integer> ballot = it.next();
            if (ballot.getValue() >= quota) {
                return ballot.getKey();
            }
        }
        return '!';
    }
    
    private void reallocateWinnerSeats(char canidate, Map<Character, Integer> candidatesLeft, int quota) {
        int surplusVotes = candidatesLeft.get(canidate) - quota;
        int totalVotes = 0;
        Map<Character, Integer> candidatesToAllocate = new HashMap<>();
        Iterator<Map.Entry<String, Integer>> votesIt = votes.entrySet().iterator();
        while (votesIt.hasNext()) {
            Entry<String, Integer> ballot = votesIt.next();
            if (ballot.getKey().charAt(0) == canidate) {
                int index = 1;
                while (true) {
                    if (index >= ballot.getKey().length()) {
                        break;
                    } if (candidatesLeft.containsKey(ballot.getKey().charAt(index))) {
                        totalVotes += ballot.getValue();
                        if (!candidatesToAllocate.containsKey(ballot.getKey().charAt(index))) {
                            candidatesToAllocate.put(ballot.getKey().charAt(index), ballot.getValue());
                        } else {
                            int newValue = ballot.getValue() + candidatesToAllocate.get(ballot.getKey().charAt(index));
                            candidatesToAllocate.put(ballot.getKey().charAt(index), newValue);
                        }
                        break;
                    }
                    index++;
                }
            }
        }
        Iterator<Map.Entry<Character, Integer>> it = candidatesToAllocate.entrySet().iterator();
        int redistirbutedVote = 0;
        while (it.hasNext()) {
            Entry<Character, Integer> ballot = it.next();
            redistirbutedVote =  (surplusVotes/totalVotes) * ballot.getValue();
            candidatesLeft.put(ballot.getKey(), candidatesLeft.get(ballot.getKey()) + redistirbutedVote);
        }
    }
    
    private void reallocateLoserSeats(char canidate, Map<Character, Integer> candidatesLeft) {
        Iterator<Map.Entry<String, Integer>> it = votes.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Integer> ballot = it.next();
            if (ballot.getKey().charAt(0) == canidate) {
                int index = 1;
                while (true) {
                    if (index >= ballot.getKey().length()) {
                        break;
                    } if (candidatesLeft.containsKey(ballot.getKey().charAt(index))) {
                        int newValue = ballot.getValue() + candidatesLeft.get(ballot.getKey().charAt(index));
                        candidatesLeft.put(ballot.getKey().charAt(index), newValue);
                        break;
                    }
                    index++;
                }
            }
        }
    }
    
    private int findQuota() {
        int totalVotes = 0;
        Iterator<Map.Entry<String, Integer>> it = votes.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Integer> ballot = it.next();
            totalVotes += ballot.getValue();
        }
        return totalVotes/2;
    }

}
