package votingsystems.methods;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import votingsystems.utilities.Generator;

class SchulzeTest {
	
	private Generator generator;
	private VotingSystem schulze;
	
	@BeforeEach
	void setUp() {
		generator = new Generator();
	}

	@Test
	void testSchulzeDis() {
		generator.createDisTest();
		schulze = new Schulze(generator);
		schulze.run();
		List<Character> winners = Arrays.asList('C', 'B', 'E', 'A', 'D');
		assertEquals(schulze.getWinningOrder(), winners);
		assertEquals(schulze.getWinner(), 'C');
	}
	
	@Test
	void testSchulzeIncomplete() {
		generator.createSomeIncompleteTest();
		schulze = new Schulze(generator);
		schulze.run();
		List<Character> winners = Arrays.asList('E', 'B', 'C', 'A', 'D');
		assertEquals(schulze.getWinningOrder(), winners);
		assertEquals(schulze.getWinner(), 'E');
	}

}
