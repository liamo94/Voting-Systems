package votingsystems.utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import votingsystems.utilities.Generator;

class VotingHelperTest {
    
    private Generator generator;
    
    @BeforeEach
    void setUp() {
        generator = new Generator();
    }

    @Test
    void testFillPairWise() {
        int numberOfCandidates = 5;
        Map<String, Integer> votes = generator.runTest(TestCases.DISSERTATION_EXAMPLE);
        List<Character> candidates = VotingHelper.getCandidates(numberOfCandidates);
        int[][] pairwise = {
                {0, 27, 19, 35, 17},
                {22, 0, 24, 33, 28},
                {30, 25, 0, 33, 23},
                {14, 16, 16, 0, 31},
                {32, 21, 26, 18, 0}
        };
        int[][] calculatePairWise = VotingHelper.fillPairWise(votes, candidates, numberOfCandidates);
        for(int i = 0; i < numberOfCandidates; i++) {
            for(int j = 0; j < numberOfCandidates; j++) {
                assertEquals(pairwise[i][j], calculatePairWise[i][j]);
            }
        }
    }
    


}
