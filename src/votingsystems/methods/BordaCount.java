package votingsystems.methods;

import java.util.ArrayList;
import java.util.Arrays;
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
	private List<Character> candidates = new ArrayList<>();
	private int numberOfCandidates;
	private char winner;
	private List<Character> winnerOrder = new ArrayList<>();
	
	public BordaCount(Generator generator) {
		this.votes = generator.getVotes();
		votes = generator.getVotes();
		numberOfCandidates = generator.getNumberOfCandidates();
		candidates = SortingHelper.getCandidates(numberOfCandidates);
	}
	
	public void run() {
		calculateScores();
	}
	
	private void calculateScores() {
		Iterator<Map.Entry<String, Integer>> it = votes.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, Integer> pair = it.next();
			int score = numberOfCandidates;
			for(int i = 0; i < pair.getKey().length(); i++) {
				if (results.containsKey(pair.getKey().charAt(i))) {
					int previousValue = results.get(pair.getKey().charAt(i));
					results.put(pair.getKey().charAt(i), previousValue + (pair.getValue() * score));
				} else {
					results.put(pair.getKey().charAt(i), (pair.getValue() * score));
				}
				score--;
			}
		}
		findWinners();
	}
	
	private void findWinners() {
		Map<Character, Integer> sorted = SortingHelper.getOrderedList(results);
		winnerOrder = new ArrayList<>(sorted.keySet());
		winner = winnerOrder.get(0);
	}

	public char getWinner() {
		return winner;
	}

	public List<Character> getWinningOrder() {
		return winnerOrder;
	}

}
