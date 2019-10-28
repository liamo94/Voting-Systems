package votingsystems.methods;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import votingsystems.utilities.Generator;
import votingsystems.utilities.TestCases;

class BordaCountTest {
    
    private Generator generator;
    private VotingSystem bordaCount;
    
    @BeforeEach
    void setUp() {
        generator = new Generator();
    }

    @Test
    void testBordaCountDis() {
        generator.runTest(TestCases.DISSERTATION_EXAMPLE);
        bordaCount = new BordaCount(generator);
        bordaCount.run();
        List<Character> winners = Arrays.asList('C', 'B', 'A', 'E', 'D');
        assertEquals(bordaCount.getWinningOrder(), winners);
        assertEquals(bordaCount.getWinner(), 'C');
    }
    
    @Test
    void testBordaCountIncomplete() {
        generator.runTest(TestCases.INCOMPLETE_BALLOTS);
        bordaCount = new BordaCount(generator);
        bordaCount.run();
        List<Character> winners = Arrays.asList('E', 'B', 'A', 'D', 'C');
        assertEquals(bordaCount.getWinningOrder(), winners);
        assertEquals(bordaCount.getWinner(), 'E');
    }

}
