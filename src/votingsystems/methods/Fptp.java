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
 * Implementation for the First-Past-The-Post voting system
 * 
 * @author Liam O'Donnell
 *
 */
public class Fptp extends VotingSystem {
	
	private Map<String, Integer> votes = new HashMap<>();
	private Map<Character, Integer> results = new HashMap<>();
	private char winner;
	private List<Character> winnerOrder = new ArrayList<>();
	
	public Fptp(Generator generator) {
		this.votes = generator.createTest();
	}
	
	public void run() {
		calculateScores();
	}
	
	public char getWinner() {
		return winner;
	}
	
	public List<Character> getWinnigOrder() {
		return winnerOrder;
	}
	
	private void calculateScores() {
		Iterator<Map.Entry<String, Integer>> it = votes.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, Integer> pair = it.next();
			if(results.containsKey(pair.getKey().charAt(0))) {
				int previousValue = results.get(pair.getKey().charAt(0));
				results.put(pair.getKey().charAt(0), previousValue + pair.getValue());
			} else {
				results.put(pair.getKey().charAt(0), pair.getValue());
			}
		}
		findWinners();
	}
	
	private void findWinners() {
		Map<Character, Integer> sorted = SortingHelper.getOrderedList(results);
		winnerOrder = new ArrayList<>(sorted.keySet());
		winner = winnerOrder.get(0);
	}

}
