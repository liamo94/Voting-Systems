package votingsystems.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import votingsystems.utilities.Generator;
import votingsystems.utilities.SortingHelper;

/**
 * Single Transferable Vote implementation
 * 
 * @author liamodonnell
 *
 */
public class Stv extends VotingSystem{
	
	Generator generator;
	Map<String, Integer> votes = new HashMap<>();
	private List<Character> candidates = new ArrayList<>();
	private int numberOfCandidates;
	private Map<Character, Integer> results = new HashMap<>();
	private List<Character> winnerOrder = new ArrayList<>();
	private char winner;
	
	public Stv(Generator generator) {
		this.generator = generator;
		votes = generator.getVotes();
		numberOfCandidates = generator.getNumberOfCandidates();
		candidates = SortingHelper.getCandidates(numberOfCandidates);
	}
	
	public void run() {
		if (generator.getNumberOfCandidates() > 0) {
			calculateScores();
			findWinners();
		}
	}
	
	public char getWinner() {
		return winner;
	}
	
	public List<Character> getWinningOrder() {
		return winnerOrder;
	}
	
	private void findWinners() {
		winner = winnerOrder.isEmpty() ? '!' : winnerOrder.get(0);
	}
	
	private void calculateScores()  {
		int quota = findQuota();
		Map<Character, Integer> candidatesLeft = new HashMap<>(results); 
		char[] losers = new char[numberOfCandidates];
		while(!candidatesLeft.isEmpty()) {
			char candidateToCheck = canidateHitQuota(candidatesLeft, quota);
			if(candidateToCheck != '!') {
				winnerOrder.add(candidateToCheck);
				reallocateWinnerSeats(candidateToCheck, candidatesLeft);
				candidatesLeft.remove(candidateToCheck);
			} else {
				losers[losers.length] = candidateToCheck;
				reallocateLoserSeats(candidateToCheck, candidatesLeft);
				candidatesLeft.remove(candidateToCheck);
			}
		}
		//join the loser and winner arrays
		int losersLength = losers.length;
		for(int i = losersLength; i > 0; i--) {
			winnerOrder.add(losers[i]);
		}
	}
	
	//If there is no winning canidate, return !
	private char canidateHitQuota(Map<Character, Integer> candidatesLeft, int quota) {
		Iterator<Map.Entry<String, Integer>> it = votes.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, Integer> pair = it.next();
			if(pair.getValue() >= quota) {
				return pair.getKey().charAt(0);
			}
		}
		return '!';
	}
	
	private void reallocateWinnerSeats(char canidate, Map<Character, Integer> candidatesLeft) {
			//find how many votes they had above quota
			//find which candidates were voted second (percentage split)
			//assign the votes over quota to the other candiates (if they were voted first)
	}
	
	private void reallocateLoserSeats(char canidate, Map<Character, Integer> candidatesLeft) {
			//Find situation were the losing candidate was first
			//In this case, find who was second, if they were first, reallocate the seat to them
	}
	
	private int findQuota() {
		int totalVotes = 0;
		Iterator<Map.Entry<String, Integer>> it = votes.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, Integer> pair = it.next();
			totalVotes += pair.getValue();
		}
		return totalVotes/2;
	}

}
