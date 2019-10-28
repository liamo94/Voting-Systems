package votingsystems.utilities;

import java.util.HashMap;
import java.util.Map;

public class Generator {
	
	private int noOfCandidates = 0;
	private Map<String, Integer> votes = new HashMap<>();
	
	public Generator() {
	}
	
	public int getNumberOfCandidates() {
		return this.noOfCandidates;
	}
	
	public Map<String, Integer> getVotes() {
		return votes;
	}
	
	public Map<String, Integer> runTest(TestCases test) {
		switch(test) {
		case SIXCANDIDATES:
			return createTest();
		case DISSERTATION_EXAMPLE:
			return createDisTest();
		case INCOMPLETE_BALLOTS:
			return createSomeIncompleteTest();
		}
		return createTest();
	}
	
	private Map<String, Integer> createTest(){
		votes.clear();
		noOfCandidates = 6;
		votes.put("ABCDEF", 5100);
		votes.put("BCDEAF", 200);
		votes.put("CEDABF", 20000);
		votes.put("CEBDAF", 900);
		return votes;
	}
	
	/**
	 * USED IN TESTING
	 */
	private Map<String, Integer> createDisTest() {
		votes.clear();
		noOfCandidates = 5;
		votes.put("ACDBE", 8);
		votes.put("BEACD", 5);
		votes.put("BDCEA", 6);
		votes.put("CABDE", 9);
		votes.put("DECAB", 8);
		votes.put("EBACD", 4);
		votes.put("EBCAD", 7);
		votes.put("EABDC", 2);
		return votes;
	}
	
	/**
	 * USED IN TESTING
	 */
	private Map<String, Integer> createSomeIncompleteTest() {
		votes.clear();
		noOfCandidates = 5;
		votes.put("AE", 8);
		votes.put("EACD", 5);
		votes.put("BDCE", 6);
		votes.put("CABDE", 9);
		votes.put("DEB", 8);
		votes.put("EBAD", 4);
		votes.put("EBCA", 7);
		votes.put("E", 2);
		return votes;
	}

}
