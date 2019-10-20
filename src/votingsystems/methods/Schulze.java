package votingsystems.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import votingsystems.utilities.Generator;
import votingsystems.utilities.SortingHelper;

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
		votes = generator.createTest();
		numberOfCandidates = generator.getNumberOfCandidates();
		candidates = SortingHelper.getCandidates(numberOfCandidates);
	}
	
	public void run() {
		System.out.println("Schulze method");
		if (generator.getNumberOfCandidates() > 0) {
			long startTime = System.currentTimeMillis();
			calculateScores();
			findWinners();
			long totalTime = System.currentTimeMillis() - startTime;
			System.out.println("Excecution time: " + totalTime + "ms\n");
		}
	}
	
	public char getWinner() {
		return winner;
	}
	
	public List<Character> getWinnigOrder() {
		return winnerOrder;
	}
	
	private void findWinners() {
		Map<Character, Integer> sorted = SortingHelper.getOrderedList(results);
		winnerOrder = new ArrayList<>(sorted.keySet());
		winner = winnerOrder.get(0);
	}
	
	private void calculateScores() {
		int [][] winningMatrix = new int[numberOfCandidates][numberOfCandidates];
		winningMatrix = calculateStrongestPaths(fillPairWise(numberOfCandidates));
		int previousValue = 0;
		for(int i = 0; i < numberOfCandidates - 1; i++) {
			for(int j = i + 1; j < numberOfCandidates; j++) {
				if(winningMatrix[i][j] > winningMatrix[j][i]){
					previousValue = null == results.get(candidates.get(i)) ? 0 : results.get(candidates.get(i));
					if (results.get(candidates.get(j)) == null) {
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
	
	private int[][] fillPairWise(int numberOfCandidates) {
		int [][] pairwiseComparison = new int[numberOfCandidates][numberOfCandidates];
		Iterator<Map.Entry<String, Integer>> it = votes.entrySet().iterator();
		int voteLength;
		String vote;
		while(it.hasNext()) {
			Entry<String, Integer> pair = it.next();
			voteLength = pair.getKey().length();
			vote = pair.getKey();
			for(int i = 0; i < voteLength - 1; i++) {
				for(int j = i + 1; j < voteLength; j++) {
					pairwiseComparison[candidates.indexOf(vote.charAt(i))][candidates.indexOf(vote.charAt(j))] += pair.getValue();
				}
			}
			for(char candidate: vote.toCharArray()) {
				for(char unselected: findUnselectedCandidates(vote)) {
					pairwiseComparison[candidates.indexOf(candidate)][candidates.indexOf(unselected)] += pair.getValue();
				}
			}

		}
		return pairwiseComparison;
	}
	
	private List<Character> findUnselectedCandidates(String vote) {
		List<Character> unusedCharacters = new ArrayList<>();
		for (Character candidate: candidates) {
			if (vote.indexOf(candidate) == -1) {
				unusedCharacters.add(candidate);
			}
		}
		return unusedCharacters;
	}
	
	private int[][] calculateStrongestPaths(int[][] pairwiseComparison) {
		int [][] strongestPath = new int[numberOfCandidates][numberOfCandidates];
		for (int i = 0; i < numberOfCandidates; i++) {
			for (int j = 0; j < numberOfCandidates; j++) {
				if(i != j){
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
