package votingsystems.methods;

import java.util.List;

public abstract class VotingSystem {
	
	abstract void run();
	abstract String returnWinner();
	abstract List<String> returnOrder();

}
