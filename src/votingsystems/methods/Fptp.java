package votingsystems.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import votingsystems.utilities.Generator;
import votingsystems.utilities.Sorting;

/**
 * Implementation for the First-Past-The-Post voting system
 * 
 * @author Liam O'Donnell
 *
 */
public class Fptp extends VotingSystem {
	
	private Generator generator;
	private Map<String, Integer> scores = new HashMap<>();
	private Map<Character, Integer> results = new HashMap<>();
	private char winner;
	
	public Fptp(Generator generator) {
		this.generator = generator;
		this.scores = this.generator.createTest();
	}
	
	public void run() {
		calculateScores();
		System.out.println(results);
	}
	
	public char returnWinner() {
		if(results.isEmpty()) {
			Map<Character, Integer> sorted = Sorting.getOrderedList(results);
			List<Character> values = new ArrayList<>(sorted.keySet());
			winner = values.get(0);
		}
		return winner;
	}
	
	public List<Character> returnOrder() {
		Map<Character, Integer> sorted = Sorting.getOrderedList(results);
		return new ArrayList<>(sorted.keySet());
	}
	
	private void calculateScores() {
		Iterator<Map.Entry<String, Integer>> it = scores.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, Integer> pair = it.next();
			if(results.containsKey(pair.getKey().charAt(0))) {
				int previousValue = results.get(pair.getKey().charAt(0));
				results.put(pair.getKey().charAt(0), previousValue + pair.getValue());
			} else {
				results.put(pair.getKey().charAt(0), pair.getValue());
			}
		}
	}
	

}
