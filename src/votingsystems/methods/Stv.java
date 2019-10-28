package votingsystems.methods;

import java.util.ArrayList;
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
	private Map<Character, Integer> results = new HashMap<>();
	private List<Character> winnerOrder = new ArrayList<>();
	private char winner;
	
	public Stv(Generator generator) {
		this.generator = generator;
		votes = generator.getVotes();
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
	
	private void findFirstPlaces() {
		Iterator<Map.Entry<String, Integer>> it = votes.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Integer> pair = it.next();
			if (results.containsKey(pair.getKey().charAt(0))) {
				int previousValue = results.get(pair.getKey().charAt(0));
				results.put(pair.getKey().charAt(0), previousValue + pair.getValue());
			} else {
				results.put(pair.getKey().charAt(0), pair.getValue());
			}
		}
	}
	
	private void calculateScores()  {
		int quota = findQuota();
		findFirstPlaces();
		Map<Character, Integer> candidatesLeft = new HashMap<>(results);
		List<Character> losers = new ArrayList<>();
		while (!candidatesLeft.isEmpty()) {
			char candidateToCheck = canidateHitQuota(candidatesLeft, quota);
			if (candidateToCheck != '!') {
				winnerOrder.add(candidateToCheck);
				reallocateWinnerSeats(candidateToCheck, candidatesLeft, quota);
				candidatesLeft.remove(candidateToCheck);
			} else {
				char loser = checkLowestCandidate(candidatesLeft);
				losers.add(loser);
				reallocateLoserSeats(loser, candidatesLeft);
				candidatesLeft.remove(loser);
			}
		}
		for (int i = losers.size()-1; i >= 0; i--) {
			winnerOrder.add(losers.get(i));
		}
	}
	
	private char checkLowestCandidate(Map<Character, Integer> candidatesLeft) {
		Map<Character, Integer> sorted = SortingHelper.getOrderedList(candidatesLeft);
		List<Character> values = new ArrayList<>(sorted.keySet());
		return values.get(values.size() - 1);
	}
	
	private char canidateHitQuota(Map<Character, Integer> candidatesLeft, int quota) {
		Iterator<Map.Entry<Character, Integer>> it = candidatesLeft.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Character, Integer> pair = it.next();
			if(pair.getValue() >= quota) {
				return pair.getKey();
			}
		}
		return '!';
	}
	
	private void reallocateWinnerSeats(char canidate, Map<Character, Integer> candidatesLeft, int quota) {
		int surplusVotes = candidatesLeft.get(canidate) - quota;
		int totalVotes = 0;
		Map<Character, Integer> candidatesToAllocate = new HashMap<>();
		Iterator<Map.Entry<String, Integer>> votesIt = votes.entrySet().iterator();
		while (votesIt.hasNext()) {
			Entry<String, Integer> pair = votesIt.next();
			if (pair.getKey().charAt(0) == canidate) {
				int index = 1;
				while (true) {
					if (index >= pair.getKey().length()) {
						break;
					} if (candidatesLeft.containsKey(pair.getKey().charAt(index))) {
						totalVotes += pair.getValue();
						if (!candidatesToAllocate.containsKey(pair.getKey().charAt(index))) {
							candidatesToAllocate.put(pair.getKey().charAt(index), pair.getValue());
						} else {
							int newValue = pair.getValue() + candidatesToAllocate.get(pair.getKey().charAt(index));
							candidatesToAllocate.put(pair.getKey().charAt(index), newValue);
						}
						break;
					}
					index++;
				}
			}
		}
		Iterator<Map.Entry<Character, Integer>> it = candidatesToAllocate.entrySet().iterator();
		int redistirbutedVote = 0;
		while (it.hasNext()) {
			Entry<Character, Integer> pair = it.next();
			redistirbutedVote =  (surplusVotes/totalVotes) * pair.getValue();
			candidatesLeft.put(pair.getKey(), candidatesLeft.get(pair.getKey()) + redistirbutedVote);
		}
	}
	
	private void reallocateLoserSeats(char canidate, Map<Character, Integer> candidatesLeft) {
		Iterator<Map.Entry<String, Integer>> it = votes.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Integer> pair = it.next();
			if (pair.getKey().charAt(0) == canidate) {
				int index = 1;
				while (true) {
					if (index >= pair.getKey().length()) {
						break;
					} if (candidatesLeft.containsKey(pair.getKey().charAt(index))) {
						int newValue = pair.getValue() + candidatesLeft.get(pair.getKey().charAt(index));
						candidatesLeft.put(pair.getKey().charAt(index), newValue);
						break;
					}
					index++;
				}
			}
		}
	}
	
	private int findQuota() {
		int totalVotes = 0;
		Iterator<Map.Entry<String, Integer>> it = votes.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Integer> pair = it.next();
			totalVotes += pair.getValue();
		}
		return totalVotes/2;
	}

}
