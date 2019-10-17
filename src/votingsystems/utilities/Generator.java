package votingsystems.utilities;

import java.util.ArrayList;
import java.util.List;

public class Generator {
	
	private int noOfCandidates;
	
	public Generator() {
	}
	
	public int getNumberOfCandidates() {
		return this.noOfCandidates;
	}
	
	public List<String> createTest1(){
		List<String> votes = new ArrayList<>();
		noOfCandidates = 6;
		for(int n = 0; n < 5100; n++)
			votes.add("ABCDEF");
		for(int n = 0; n < 2000; n++)
			votes.add("BCDEAF");
		for(int n = 0; n < 20000; n++)
			votes.add("CEDABF");
		for(int n = 0; n < 900; n++)
			votes.add("CEBDAF");
		return votes;
	}

}

enum TestCases {
	SIXCANDIDATES
}
