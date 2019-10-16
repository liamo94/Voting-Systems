package votingsystems.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import votingsystems.utilities.Generator;

public class Schulze extends VotingSystem {
	
	Generator generator;
	List<String> votes = new ArrayList<>();
	
	public Schulze(Generator generator) {
		this.generator = generator;
		votes = this.generator.createTest1();
		System.out.println(Arrays.asList(votes));
	}
	
	public void run() {
		System.out.println("Schulze method");
	}
	
	public String returnWinner() {
		return null;
	}
	
	public List<String> returnOrder() {
		return null;
	}
	
	private int[][] fillPairWise(int numberOfCandidates) {
		return new int [numberOfCandidates][numberOfCandidates];
	}
	

}
