package votingsystems.methods;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import votingsystems.utilities.Generator;

class FptpTest {
	
	private Generator generator;
	private VotingSystem fptp;
	
	@BeforeEach
	void setUp() {
		generator = new Generator();
	}

	@Test
	void testFptp() {
		generator.createDisTest();
		fptp = new Fptp(generator);
		fptp.run();
		List<Character> winners = Arrays.asList('E', 'B', 'C', 'A', 'D');
		assertEquals(fptp.getWinningOrder(), winners);
		assertEquals(fptp.getWinner(), 'E');
	}
	
	@Test
	void testFptpIncomplete() {
		generator.createSomeIncompleteTest();
		fptp = new Fptp(generator);
		fptp.run();
		List<Character> winners = Arrays.asList('E', 'C', 'A', 'D', 'B');
		assertEquals(fptp.getWinningOrder(), winners);
		assertEquals(fptp.getWinner(), 'E');
	}


}
