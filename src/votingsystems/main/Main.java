package votingsystems.main;

import votingsystems.methods.Schulze;
import votingsystems.methods.VotingSystem;
import votingsystems.utilities.Generator;

public class Main {
	
	public static void main(String[] args) {
		Generator generator = new Generator();
		VotingSystem schulze = new Schulze(generator);
	}

}
