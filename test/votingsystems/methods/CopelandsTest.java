package votingsystems.methods;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import votingsystems.utilities.Generator;

class CopelandsTest {
	
	private Generator generator;
	private VotingSystem copelands;
	
	@BeforeEach
	void setUp() {
		generator = new Generator();
	}

	@Test
	void testCopelandsDis() {
		generator.createDisTest();
		copelands = new Copelands(generator);
		copelands.run();
		List<Character> winners = Arrays.asList('C', 'A', 'B', 'E', 'D');
		assertEquals(copelands.getWinningOrder(), winners);
		assertEquals(copelands.getWinner(), 'C');
	}
	
	@Test
	void testCopelandsIncomplete() {
		generator.createSomeIncompleteTest();
		copelands = new Copelands(generator);
		copelands.run();
		List<Character> winners = Arrays.asList('E', 'B', 'C', 'A', 'D');
		assertEquals(copelands.getWinningOrder(), winners);
		assertEquals(copelands.getWinner(), 'E');
	}

}
