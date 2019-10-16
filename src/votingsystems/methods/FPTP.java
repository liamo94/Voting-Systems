package votingsystems.methods;

import java.util.List;

import votingsystems.utilities.Generator;

/**
 * Implementation for the First-Past-The-Post voting system
 * 
 * @author Liam O'Donnell
 *
 */
public class FPTP extends VotingSystem {
	
	Generator generator;
	public FPTP(Generator generator) {
		this.generator = generator;
	}
	
	void run() {
		
	}
	
	String returnWinner() {
		return null;
	}
	
	List<String> returnOrder() {
		return null;
	}

}
