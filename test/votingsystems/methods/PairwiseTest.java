package votingsystems.methods;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import votingsystems.utilities.Generator;
import votingsystems.utilities.TestCases;

class PairwiseTest {
	
	private Generator generator;
	private VotingSystem pairwise;
	
	@BeforeEach
	void setUp() {
		generator = new Generator();
	}

	@Test
	void testCopelandsDis() {
		generator.runTest(TestCases.DISSERTATION_EXAMPLE);
		pairwise = new Pairwise(generator);
		pairwise.run();
		List<Character> winners = Arrays.asList('C', 'A', 'B', 'E', 'D');
		assertEquals(pairwise.getWinningOrder(), winners);
		assertEquals(pairwise.getWinner(), 'C');
	}
	
	@Test
	void testCopelandsIncomplete() {
		generator.runTest(TestCases.INCOMPLETE_BALLOTS);
		pairwise = new Pairwise(generator);
		pairwise.run();
		List<Character> winners = Arrays.asList('E', 'B', 'C', 'A', 'D');
		assertEquals(pairwise.getWinningOrder(), winners);
		assertEquals(pairwise.getWinner(), 'E');
	}

}
