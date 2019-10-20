package votingsystems.main;

import java.util.Arrays;

import votingsystems.methods.Fptp;
import votingsystems.methods.Schulze;
import votingsystems.methods.VotingSystem;
import votingsystems.utilities.Generator;

public class Main {
	
	public static void main(String[] args) {
		Generator generator = new Generator();
		VotingSystem fptp = new Fptp(generator);
		VotingSystem schulze = new Schulze(generator);
//		fptp.run();
		schulze.run();
		System.out.println(schulze.getWinnigOrder());
	}

}
