package votingsystems.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import votingsystems.methods.BordaCount;
import votingsystems.methods.Copelands;
import votingsystems.methods.Fptp;
import votingsystems.methods.LiamsMethod1;
import votingsystems.methods.LiamsMethod2;
import votingsystems.methods.Schulze;
import votingsystems.methods.Stv;
import votingsystems.methods.VotingSystem;
import votingsystems.utilities.Generator;
import votingsystems.utilities.TestCases;

public class Cli {
    
    public void run() {
        Generator generator = new Generator(TestCases.DISSERTATION_EXAMPLE);
        System.out.println("Running voting systems");
        Scanner user_input = new Scanner(System.in);
        Scanner keyboard = new Scanner(System.in);
        int choice = 0;
        while (choice != 10) {
            System.out.println("***************** VOTING SYSTEMS PROJECT *****************");
            System.out
            .println("\nPlease select an option.\n\n1. Run All Methods\n2. Run FPTP\n3. Run Borda Count\n4. Run STV\n5. Run Schulze"
                    + "\n6. Run Copeland's\n7. Run Liam's Method 1\n8. Run Liam's Method 2\n9. About\n10. Quit");
            System.out.println("\n" + "©Liam O'Donnell 2016");
            try {
                choice = keyboard.nextInt();

                switch (choice) {
                case 1:
                    System.out.println("Running all methods....\n");
                    runFptp(generator);
                    runBorda(generator);
                    runStv(generator);
                    runSchulze(generator);
                    runCopelands(generator);
                    runLiamsMethod1(generator);
                    runLiamsMethod2(generator);
                    break;

                case 2:
                    runFptp(generator);
                    break;

                case 3:
                    runBorda(generator);
                    break;

                case 4:
                    runStv(generator);
                    break;

                case 5:
                    runSchulze(generator);
                    break;

                case 6:
                    runCopelands(generator);
                    break;

                case 7:
                    runLiamsMethod1(generator);
                    break;

                case 8:
                    runLiamsMethod2(generator);
                    break;

                case 9:
                    System.out.println("Created by Liam O'Donnell for the Univerity of StrathClyde Bsc indivdual project");
                    break;

                case 10:
                    System.out.println("\nExit complete.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Enter a number <= 10");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Characters are not allowed, program quitting.");
                break;
            }

        }
        user_input.close();
        keyboard.close();

    }
    
    private void runFptp(Generator generator) {
        System.out.println("Running FPTP (First Past the Post)");
        VotingSystem fptp = new Fptp(generator);
        System.out.printf("Winner is %s, winning order - ", fptp.getWinner());
        System.out.println(fptp.getWinningOrder());
    }
    
    private void runSchulze(Generator generator) {
        System.out.println("Running Schulze");
        VotingSystem schulze = new Schulze(generator);
        System.out.printf("Winner is %s, winning order - ", schulze.getWinner());
        System.out.println(schulze.getWinningOrder());
    }
    
    private void runBorda(Generator generator) {
        System.out.println("Running Borda Count");
        VotingSystem borda = new BordaCount(generator);
        System.out.printf("Winner is %s, winning order - ", borda.getWinner());
        System.out.println(borda.getWinningOrder());
    }
    
    private void runStv(Generator generator) {
        System.out.println("Running STV (Single Transferable Vote)");
        VotingSystem stv = new Stv(generator);
        System.out.printf("Winner is %s, winning order - ", stv.getWinner());
        System.out.println(stv.getWinningOrder());
    }
    
    private void runCopelands(Generator generator) {
        System.out.println("Running Copelands Method");
        VotingSystem copelands = new Copelands(generator);
        System.out.printf("Winner is %s, winning order - ", copelands.getWinner());
        System.out.println(copelands.getWinningOrder());
    }
    
    private void runLiamsMethod1(Generator generator) {
        System.out.println("Running Liams Method 1");
        VotingSystem liamsMethod1 = new LiamsMethod1(generator);
        System.out.printf("Winner is %s, winning order - ", liamsMethod1.getWinner());
        System.out.println(liamsMethod1.getWinningOrder());
    }
    
    private void runLiamsMethod2(Generator generator) {
        System.out.println("Running Liams Method 2");
        VotingSystem liamsMethod2 = new LiamsMethod2(generator);
        System.out.printf("Winner is %s, winning order - ", liamsMethod2.getWinner());
        System.out.println(liamsMethod2.getWinningOrder());
    }

}
