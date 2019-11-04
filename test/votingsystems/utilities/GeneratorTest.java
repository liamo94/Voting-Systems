package votingsystems.utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GeneratorTest {

    @Test
    void testRandomGenerator() {
        Generator generator = new Generator(TestCases.RANDOM);
        assertFalse(generator.getVotes().isEmpty());
    }
    
}