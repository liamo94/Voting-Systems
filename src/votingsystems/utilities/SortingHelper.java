package votingsystems.utilities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortingHelper {
	
	private SortingHelper() {}
	
	public static Map<Character, Integer> getOrderedList(Map<Character, Integer> results) {
		Map<Character, Integer> sortedByCount = results.entrySet()
	            .stream()
	            .sorted((Map.Entry.<Character, Integer>comparingByValue().reversed()))
	            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sortedByCount;
	}
	
	public static List<Character> getCandidates(int noOfCandidates) {
		List<Character> candidates = new ArrayList<>();
		for(int i = 65; i < 65 + noOfCandidates; i++) {
			candidates.add((char)i);
		}
		return candidates;
	}

}
