package votingsystems.methods;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import votingsystems.utilities.Generator;
import votingsystems.utilities.TestCases;

class SchulzeTest {
    
    private Generator generator;
    private VotingSystem schulze;
    
    @BeforeEach
    void setUp() {
        generator = new Generator();
    }

    @Test
    void testSchulzeDis() {
        generator.runTest(TestCases.DISSERTATION_EXAMPLE);
        schulze = new Schulze(generator);
        List<Character> winners = Arrays.asList('C', 'B', 'E', 'A', 'D');
        assertEquals(schulze.getWinningOrder(), winners);
        assertEquals(schulze.getWinner(), 'C');
    }
    
    @Test
    void testSchulzeIncomplete() {
        generator.runTest(TestCases.INCOMPLETE_BALLOTS);
        schulze = new Schulze(generator);
        List<Character> winners = Arrays.asList('E', 'B', 'C', 'A', 'D');
        assertEquals(schulze.getWinningOrder(), winners);
        assertEquals(schulze.getWinner(), 'E');
    }

}
