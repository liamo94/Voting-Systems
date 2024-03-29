package votingsystems.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class VotingHelper {
    
    private VotingHelper() { }
    
    public static int[][] fillPairWise(Map<String, Integer> votes, List<Character> candidates, int numberOfCandidates) {
        int [][] pairwiseComparison = new int[numberOfCandidates][numberOfCandidates];
        Iterator<Map.Entry<String, Integer>> it = votes.entrySet().iterator();
        int voteLength;
        String vote;
        while(it.hasNext()) {
            Entry<String, Integer> ballot = it.next();
            voteLength = ballot.getKey().length();
            vote = ballot.getKey();
            for(int i = 0; i < voteLength - 1; i++) {
                for(int j = i + 1; j < voteLength; j++) {
                    pairwiseComparison[candidates.indexOf(vote.charAt(i))][candidates.indexOf(vote.charAt(j))] += ballot.getValue();
                }
            }
            for(char candidate: vote.toCharArray()) {
                for(char unselected: findUnselectedCandidates(vote, candidates)) {
                    pairwiseComparison[candidates.indexOf(candidate)][candidates.indexOf(unselected)] += ballot.getValue();
                }
            }

        }
        return pairwiseComparison;
    }
    
    private static List<Character> findUnselectedCandidates(String vote, List<Character> candidates) {
        List<Character> unusedCharacters = new ArrayList<>();
        for (Character candidate: candidates) {
            if (vote.indexOf(candidate) == -1) {
                unusedCharacters.add(candidate);
            }
        }
        return unusedCharacters;
    }
    
    public static List<Character> getCandidates(int noOfCandidates) {
        List<Character> candidates = new ArrayList<>();
        for(int i = 65; i < 65 + noOfCandidates; i++) {
            candidates.add((char)i);
        }
        return candidates;
    }

}
