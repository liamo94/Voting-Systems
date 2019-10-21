package votingsystems.methods;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import votingsystems.utilities.Generator;

class PairwiseTest {
	
	private Generator generator;
	private VotingSystem pairwise;
	
	@BeforeEach
	void setUp() {
		generator = new Generator();
	}

	@Test
	void testCopelandsDis() {
		generator.createDisTest();
		pairwise = new Pairwise(generator);
		pairwise.run();
		List<Character> winners = Arrays.asList('C', 'A', 'B', 'E', 'D');
		assertEquals(pairwise.getWinningOrder(), winners);
		assertEquals(pairwise.getWinner(), 'C');
	}
	
	@Test
	void testCopelandsIncomplete() {
		generator.createSomeIncompleteTest();
		pairwise = new Pairwise(generator);
		pairwise.run();
		List<Character> winners = Arrays.asList('E', 'B', 'C', 'A', 'D');
		assertEquals(pairwise.getWinningOrder(), winners);
		assertEquals(pairwise.getWinner(), 'E');
	}

}
