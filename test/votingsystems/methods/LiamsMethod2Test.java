package votingsystems.methods;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import votingsystems.utilities.Generator;
import votingsystems.utilities.TestCases;

class LiamsMethod2Test {
    
    private Generator generator;
    private VotingSystem liamsMethod2;
    
    @BeforeEach
    void setUp() {
        generator = new Generator();
    }

    @Test
    void testLiamsMethod2() {
        generator.runTest(TestCases.DISSERTATION_EXAMPLE);
        liamsMethod2 = new LiamsMethod2(generator);
        liamsMethod2.run();
        List<Character> winners = Arrays.asList('C', 'A', 'B', 'E', 'D');
        assertEquals(liamsMethod2.getWinningOrder(), winners);
        assertEquals(liamsMethod2.getWinner(), 'C');
    }
    
    @Test
    void testLiamsMethod2Incomplete() {
        generator.runTest(TestCases.INCOMPLETE_BALLOTS);
        liamsMethod2 = new LiamsMethod2(generator);
        liamsMethod2.run();
        List<Character> winners = Arrays.asList('E', 'B', 'A', 'C', 'D');
        assertEquals(liamsMethod2.getWinningOrder(), winners);
        assertEquals(liamsMethod2.getWinner(), 'E');
    }


}
