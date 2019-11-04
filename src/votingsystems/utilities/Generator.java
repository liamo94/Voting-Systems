package votingsystems.utilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Generator {
    
    private int numberOfCandidates = 0;
    private Map<String, Integer> votes = new HashMap<>();
    
    public Generator() {
    }
    
    public Generator(TestCases test) {
        runTest(test);
    }
    
    public int getNumberOfCandidates() {
        return this.numberOfCandidates;
    }
    
    public Map<String, Integer> getVotes() {
        return votes;
    }
    
    public Map<String, Integer> runTest(TestCases test) {
        votes.clear();
        switch(test) {
        case THREE_CANDIDATES:
            return createThreeCandidates();
        case FOUR_CANDIDATES:
            return createFourCandidates();
        case FIVE_CANDIDATES:
            return createDisTest();
        case SIX_CANDIDATES:
            return createSixCandidates();
        case TEN_CANDIDATES:
            return createTenCandidates();
        case TEST_CASE_1:
            return createTest1();
        case TEST_CASE_2:
            return createTest2();
        case TEST_CASE_3:
            return createTest3();
        case TEST_CASE_4:
            return createTest4();
        case TEST_CASE_5:
            return createTest5();
        case TEST_CASE_6:
            return createTest6();
        case DISSERTATION_EXAMPLE:
            return createDisTest();
        case INCOMPLETE_BALLOTS:
            return createSomeIncompleteTest();
        case RANDOM:
            return createRandomVote();
        default:
            return createDefaultTest();
        }
    }
    
    private Map<String, Integer> createDefaultTest(){
        numberOfCandidates = 6;
        votes.put("ABCDEF", 5100);
        votes.put("BCDEAF", 200);
        votes.put("CEDABF", 20000);
        votes.put("CEBDAF", 900);
        return votes;
    }
    
    /**
     * USED IN TESTING
     */
    private Map<String, Integer> createDisTest() {
        numberOfCandidates = 5;
        votes.put("ACDBE", 8);
        votes.put("BEACD", 5);
        votes.put("BDCEA", 6);
        votes.put("CABDE", 9);
        votes.put("DECAB", 8);
        votes.put("EBACD", 4);
        votes.put("EBCAD", 7);
        votes.put("EABDC", 2);
        return votes;
    }
    
    /**
     * USED IN TESTING
     */
    private Map<String, Integer> createSomeIncompleteTest() {
        numberOfCandidates = 5;
        votes.put("AE", 8);
        votes.put("EACD", 5);
        votes.put("BDCE", 6);
        votes.put("CABDE", 9);
        votes.put("DEB", 8);
        votes.put("EBAD", 4);
        votes.put("EBCA", 7);
        votes.put("E", 2);
        return votes;
    }
    
    private Map<String, Integer> createSixCandidates(){
        numberOfCandidates = 6;
        votes.put("CAFEBD", 17000);
        votes.put("DFABCE", 9000);
        votes.put("BACFED", 13000);
        votes.put("FCADBE", 21000);
        votes.put("EDCAFD", 3000);
        votes.put("BACEDF", 17000);
        votes.put("FADE", 20000);
        return votes;
    }

    private Map<String, Integer> createThreeCandidates(){
        numberOfCandidates = 3;
        votes.put("ABC", 16000);
        votes.put("ACB", 13000);
        votes.put("BAC", 20000);
        votes.put("BCA", 17000);
        votes.put("CBA", 13000);
        votes.put("CAB", 21000);
        return votes;
    }
    
    //D wins all
    private Map<String, Integer> createTest1(){
        numberOfCandidates = 6;
        votes.put("ABCDEF", 17000);
        votes.put("FEDCBA", 21000);
        votes.put("CBAFED", 7000);
        votes.put("DEFCBA", 32000);
        votes.put("BADCFE", 3000);
        votes.put("EFDCBA", 1000);
        votes.put("DCABEF", 9000);
        votes.put("FADECB", 4000);
        votes.put("BCAFED", 2000);
        votes.put("CDEABF", 4000);
        return votes;
    }

    private Map<String, Integer> createTest2(){
        numberOfCandidates = 6;
        votes.put("ABCFED", 1700);
        votes.put("FCAEBD", 1600);
        votes.put("CABDEF", 700);
        votes.put("DEFCBA", 3200);
        votes.put("BACDEF", 800);
        votes.put("ECDFBA", 100);
        votes.put("BFADEC", 900);
        votes.put("AFDECB", 400);
        votes.put("BCAFED", 200);
        votes.put("ACBDEF", 400);
        return votes;
    }

    private Map<String, Integer> createTest3(){
        numberOfCandidates = 6;
        votes.put("ABCD", 1700);
        votes.put("FCD", 1600);
        votes.put("CAEF", 700);
        votes.put("DEFCBA", 3200);
        votes.put("BAC", 800);
        votes.put("ECDFBA", 100);
        votes.put("ECDFBA", 900);
        votes.put("AFDE", 400);
        votes.put("BCAFD", 200);
        votes.put("ACBDEF", 400);
        return votes;
    }
    
    private Map<String, Integer> createTest4(){
        numberOfCandidates = 3;
        votes.put("ABC", 2);
        votes.put("AC", 3);
        votes.put("BA", 1);
        return votes;
    }

    private Map<String, Integer> createTest5(){
        numberOfCandidates = 5;
        votes.put("ABCDE", 2000);
        votes.put("EDCBA", 2000);
        votes.put("BAEDC", 2000);
        votes.put("CBDAE", 2000);
        votes.put("DCBEA", 2000);
        return votes;
    }

    private Map<String, Integer> createTest6(){
        numberOfCandidates = 5;
        votes.put("ACDBE", 2000);
        votes.put("CABDE", 2000);
        votes.put("EBACD", 2000);
        votes.put("BDCEA", 2000);
        votes.put("DECAB", 2000);
        return votes;
    }

    
    private Map<String, Integer> createTenCandidates(){
        numberOfCandidates = 10;
        votes.put("ACDBEFGHIJ", 2000);
        votes.put("CFABGDE", 2000);
        votes.put("EBACDHI", 2000);
        votes.put("BJGHDCEA", 2000);
        votes.put("JDEICGHAFB", 2000);
        return votes;
    }

    private Map<String, Integer> createFourCandidates(){
        numberOfCandidates = 4;
        votes.put("ACDB", 2000);
        votes.put("CABD", 2000);
        votes.put("BACD", 2000);
        votes.put("BDCA", 2000);
        votes.put("DCAB", 2000);
        return votes;
    }
    
    /**
     * Creates a list of random ballots, of random lengths with random candidates and a random number of candidates
     * @return ballots;
     */
    private Map<String, Integer> createRandomVote() {
        int maxCandidates = 10, minCandidates = 3;
        Random rand = new Random();
        numberOfCandidates = rand.nextInt((maxCandidates - minCandidates) + 1) + minCandidates;
        return createRandomVote(numberOfCandidates);
    }

    private Map<String, Integer> createRandomVote(int numberOfCandidates) {
        this.numberOfCandidates = numberOfCandidates;
        int maxBallots = 1000000, minBallots = 100;
        Random rand = new Random();
        List<Character> candidates = VotingHelper.getCandidates(numberOfCandidates);
        int numberOfVoters = rand.nextInt((maxBallots - minBallots) + 1) + minBallots;
        for (int i = 0; i < numberOfVoters; i++) {
            StringBuilder sb = new StringBuilder();
            int fullCandidateList = rand.nextInt((2 - 1) + 1) + 1;
            //50% of the time I want to ensure the ballot has all candidates selected, otherwise let the ballot have a random number of candidates
            int numberOfVotes = fullCandidateList == 2 ? numberOfCandidates : rand.nextInt((numberOfCandidates - 1) + 1) + 1;
            Collections.shuffle(candidates);
            for (int j = 0; j < numberOfVotes; j++) {
                sb.append(candidates.get(j));
            }
            int count = votes.containsKey(sb.toString()) ? votes.get(sb.toString()) : 0;
            votes.put(sb.toString(), count + 1);
        }
        return votes;
    }

}
