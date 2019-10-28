package votingsystems.utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class SortingHelperTest {

    @Test
    void testCandiates() {
        List<Character> candidates = Arrays.asList('A', 'B', 'C', 'D','E');
        List<Character> testCandidates = SortingHelper.getCandidates(5);
        assertEquals(candidates, testCandidates);
    }
    
    @Test
    void testOrderedList() {
        Map<Character, Integer> results = new HashMap<>();
        results.put('A', 20);
        results.put('B', 40);
        results.put('C', 10);
        Map<Character, Integer> orderedResults = new HashMap<>();
        orderedResults.put('B', 40);
        orderedResults.put('A', 20);
        orderedResults.put('C', 10);
        assertEquals(SortingHelper.getOrderedList(results), orderedResults);
    }
    
    

}
