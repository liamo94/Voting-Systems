package votingsystems.main;

import java.util.Arrays;

import votingsystems.methods.BordaCount;
import votingsystems.methods.Copelands;
import votingsystems.methods.Fptp;
import votingsystems.methods.Pairwise;
import votingsystems.methods.Schulze;
import votingsystems.methods.Stv;
import votingsystems.methods.VotingSystem;
import votingsystems.utilities.Generator;

public class Main {
	
	public static void main(String[] args) {
		Generator generator = new Generator();
		generator.createDisTest();
//		VotingSystem fptp = new Fptp(generator);
//		VotingSystem schulze = new Schulze(generator);
//		fptp.run();
//		schulze.run();
		VotingSystem copelands = new Stv(generator);
		copelands.run();
//		System.out.println(fptp.getWinnigOrder());
//		System.out.println(schulze.getWinnigOrder());
		System.out.println(copelands.getWinningOrder());

	}

}
