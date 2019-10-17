package votingsystems.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import votingsystems.utilities.Generator;

/**
 * Implementation for the First-Past-The-Post voting system
 * 
 * @author Liam O'Donnell
 *
 */
public class Fptp extends VotingSystem {
	
	Generator generator;
	List<Integer> scores = new ArrayList<>();
	Map<String, Integer> results = new HashMap<>();
	
	public Fptp(Generator generator) {
		this.generator = generator;
	}
	
	public void run() {
		System.out.println(Arrays.asList(generator.createTest1()));
		calculateScores();
		System.out.println(results);
	}
	
	public String returnWinner() {
		return null;
	}
	
	public List<String> returnOrder() {
		List<String> keys = new ArrayList<>(results.keySet());
		Collections.sort(keys);
		return keys;
	}
	
	private void calculateScores() {
		for (String candidate: generator.createTest1()) {
			if(results.containsKey(String.valueOf(candidate.charAt(0)))) {
				Integer previousValue = results.get(String.valueOf(candidate.charAt(0)));
				results.put(String.valueOf(candidate.charAt(0)), previousValue + 1);
			} else {
				results.put("" + candidate.charAt(0), 1);
			}
		}
	}
	

}
