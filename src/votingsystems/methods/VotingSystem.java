package votingsystems.methods;

import java.util.List;

public abstract class VotingSystem {
	
	public abstract void run();
	public abstract String returnWinner();
	public abstract List<String> returnOrder();

}
