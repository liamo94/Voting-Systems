package votingsystems.methods;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import votingsystems.utilities.Generator;
import votingsystems.utilities.TestCases;

class LiamsMethod1Test {
    
    private Generator generator;
    private VotingSystem liamsMethod1;
    
    @BeforeEach
    void setUp() {
        generator = new Generator();
    }

    @Test
    void testLiamsMethod1() {
        generator.runTest(TestCases.DISSERTATION_EXAMPLE);
        liamsMethod1 = new LiamsMethod1(generator);
        List<Character> winners = Arrays.asList('C', 'B', 'A', 'E', 'D');
        assertEquals(liamsMethod1.getWinningOrder(), winners);
        assertEquals(liamsMethod1.getWinner(), 'C');
    }
    
    @Test
    void testLiamsMethod1Incomplete() {
        generator.runTest(TestCases.INCOMPLETE_BALLOTS);
        liamsMethod1 = new LiamsMethod1(generator);
        List<Character> winners = Arrays.asList('E', 'B', 'A', 'C', 'D');
        assertEquals(liamsMethod1.getWinningOrder(), winners);
        assertEquals(liamsMethod1.getWinner(), 'E');
    }


}
