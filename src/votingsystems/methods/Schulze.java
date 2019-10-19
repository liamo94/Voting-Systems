package votingsystems.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import votingsystems.utilities.Generator;

public class Schulze extends VotingSystem {
	
	Generator generator;
	private int noOfCandidates;
	Map<String, Integer> votes = new HashMap<>();
	private int [][] pairWiseComparison = new int[noOfCandidates][noOfCandidates];
	
	public Schulze(Generator generator) {
		this.generator = generator;
		votes = generator.createTest();
		noOfCandidates = generator.getNumberOfCandidates();
		System.out.println(Arrays.asList(votes));
	}
	
	public void run() {
		System.out.println("Schulze method");
	}
	
	public char returnWinner() {
		return 'a';
	}
	
	public List<Character> returnOrder() {
		return null;
	}
	
	private int[][] fillPairWise(int numberOfCandidates) {
		return new int [numberOfCandidates][numberOfCandidates];
	}
	

}
