package votingsystems.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public Map<String, Integer> createTest(){
		noOfCandidates = 6;
		Map<String, Integer> votes = new HashMap<>();
		votes.put("ABCDEF", 5100);
		votes.put("BCDEAF", 200);
		votes.put("CEDABF", 20000);
		votes.put("CEBDAF", 900);
		return votes;
	}

}

enum TestCases {
	SIXCANDIDATES
}
