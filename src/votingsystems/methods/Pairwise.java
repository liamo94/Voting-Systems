package votingsystems.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import votingsystems.utilities.Generator;
import votingsystems.utilities.SortingHelper;
import votingsystems.utilities.VotingHelper;

public class Pairwise extends VotingSystem {

	Generator generator;
	Map<String, Integer> votes = new HashMap<>();
	private List<Character> candidates = new ArrayList<>();
	private int numberOfCandidates;
	private Map<Character, Integer> results = new HashMap<>();
	private List<Character> winnerOrder = new ArrayList<>();
	private char winner;
	
	public Pairwise(Generator generator) {
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
		winner = winnerOrder.get(0);
		
	}

	private void calculateScores() {
		int [][] winningMatrix = VotingHelper.fillPairWise(votes, candidates, numberOfCandidates);
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

}
