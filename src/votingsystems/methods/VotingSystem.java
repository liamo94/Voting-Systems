package votingsystems.methods;

import java.util.List;

public abstract class VotingSystem {
	
	public abstract void run();
	public abstract char getWinner();
	public abstract List<Character> getWinningOrder();

}
