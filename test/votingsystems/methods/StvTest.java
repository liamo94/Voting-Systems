package votingsystems.methods;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import votingsystems.utilities.Generator;
import votingsystems.utilities.TestCases;

class StvTest {
    
    private Generator generator;
    private VotingSystem stv;
    
    @BeforeEach
    void setUp() {
        generator = new Generator();
    }

    @Test
    void testStv() {
        generator.runTest(TestCases.DISSERTATION_EXAMPLE);
        stv = new Stv(generator);
        List<Character> winners = Arrays.asList('E', 'C', 'B', 'A', 'D');
        assertEquals(stv.getWinningOrder(), winners);
        assertEquals(stv.getWinner(), 'E');
    }
    
    @Test
    void testStvIncomplete() {
        generator.runTest(TestCases.INCOMPLETE_BALLOTS);
        stv = new Stv(generator);
        List<Character> winners = Arrays.asList('E', 'D', 'C', 'A', 'B');
        assertEquals(stv.getWinningOrder(), winners);
        assertEquals(stv.getWinner(), 'E');
    }


}
