package votingsystems.methods;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import votingsystems.utilities.Generator;
import votingsystems.utilities.TestCases;

class CopelandsTest {
    
    private Generator generator;
    private VotingSystem copelands;
    
    @BeforeEach
    void setUp() {
        generator = new Generator();
    }

    @Test
    void testCopelandsDis() {
        generator.runTest(TestCases.DISSERTATION_EXAMPLE);
        copelands = new Copelands(generator);
        List<Character> winners = Arrays.asList('C', 'A', 'B', 'E', 'D');
        assertEquals(copelands.getWinningOrder(), winners);
        assertEquals(copelands.getWinner(), 'C');
    }
    
    @Test
    void testCopelandsIncomplete() {
        generator.runTest(TestCases.INCOMPLETE_BALLOTS);
        copelands = new Copelands(generator);
        List<Character> winners = Arrays.asList('E', 'B', 'C', 'A', 'D');
        assertEquals(copelands.getWinningOrder(), winners);
        assertEquals(copelands.getWinner(), 'E');
    }

}
