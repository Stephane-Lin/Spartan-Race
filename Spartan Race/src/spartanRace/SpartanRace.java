/*  Name(s) and ID(s): Stéphane Lin, ID: 6980716
 *                     Awais Ali, ID: 6849040
 *  COMP249
 *  Assignment #2
 *  Due Date: Friday, March 21, 2014
 */

package spartanRace;

import java.util.Scanner;

import cells.DeckOfFortuneCell;
import cells.JokerCell;
import cells.RegularCell;
import cells.SkipCell;
import cells.WheelOfFortuneCell;
import java.io.File;
import java.io.FileNotFoundException;

import racers.Racer;
import racers.RacerHelot;
import racers.RacerPerioikoi;
import racers.RacerSpartiate;

/**
 * Driver class to execute the Spartan Race.
 * 
*/
public class SpartanRace {

    static File input;
    static MyWriter writer;
    static boolean testMode;
    
    public static void main(String[] args) {
        // Constants
        final int anotherGame = 1;

        // Variable declaration
        Racer[] player;
        Dice dice = new Dice();
        RegularCell regCell = new RegularCell();
        DeckOfFortuneCell deckCell = new DeckOfFortuneCell();
        WheelOfFortuneCell wheelCell = new WheelOfFortuneCell();
        JokerCell jokerCell = new JokerCell();
        SkipCell skipCell = new SkipCell();
        RaceTrack board = new RaceTrack();
        try {
            writer = new MyWriter("race_out.txt");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(SpartanRace.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner keyIn;
        int whoseTurn = 0, roll = 0, numOfWinners, numOfSlaves, play;

        //Welcome message
        writer.printBoth("Spartan Race - Version 3.0\r\n------------------------------------\r\n",true);
        do {
            keyIn = new Scanner(System.in);
            // Regular or test mode?
            writer.printBoth("Debug Mode? (Enter 1 for debug mode): ",false);
            String debugInput = getNext(keyIn);
            testMode = ("1".equals(debugInput));
            writer.println(debugInput);
            if(testMode){
                keyIn = setUpFile();
            }
            
            player = setUpRacers(keyIn);
            
            //And the race is on!!!
            writer.printBoth("\r\nWelcome racers ... how many of you will be sold as slaves today ....hahaha.\r\n"
                    + "Ready ...  Set  ... Go!",true);
            do {
                board.resetTrack(); // set count of each location back to 0

                // Each racers moves
                for (int i = 0; i < player.length; i++) {
                    //Check if racers energy is enough to continue the round
                    if (player[i].getEnergy() > 0) {
                        do {
                            //Keep rolling the dice in this case.
                            if(testMode){
                                whoseTurn = i + 1;
                                String prompt = "\r\nPlayer " + player[i].getName() + "  - Enter Die Value ";
                                try {
                                    dice.setDie1(getValidInput(prompt + "1: ",keyIn,"Die value is out of bounds."));
                                    dice.setDie2(getValidInput(prompt.substring(2) + "2: ",keyIn,"Die value is out of bounds."));
                                } catch (DieValueOutOfBoundsException ex) {
                                    exit("Die Value is out of bounds.");
                                }
                            }else{
                                whoseTurn = getValidInput("\r\nPlayer " + player[i].getName() + "  - Enter a " + (i + 1) + " to roll the dice ",keyIn,"Invalid Roll Value");//keyIn.nextInt();
                            }
                        } while (whoseTurn != (i + 1));

                            if(!testMode){
                                roll = dice.rollDice();
                            }else{
                                roll = dice.getDie1()+dice.getDie2();
                            }
                            writer.printBoth(dice.toString(),false);
                            player[i].advanceBy(roll);
                            writer.printBoth(" At location " + player[i].getPosition() + ". ",false);

                        //Check if the racers has landed on a special location
                        
                        String jChoice = "";
                        if(testMode){
                            jChoice = getNext(keyIn);
                            if(!(jChoice.equals("w") || jChoice.equals("c")))
                                exit("\r\n**Invalid Joker Option Error**");
                        }
                        switch (board.getCode(player[i].getPosition())) {
                            case "d":
                                //if landed on Deck of Fortune, update energy according to Deck
                                player[i].setPosition(deckCell.pickCard(player[i].getPosition(), writer));
                                deckCell.updateEnergy(roll, player[i]);
                                break;
                            case "s":
                                //if landed on Skip, update energy according to Skip
                                writer.printBoth("Moving to location 68. ",true);
                                player[i].setPosition(68);
                                skipCell.updateEnergy(roll, player[i]);
                                break;
                            case "w":
                                //if landed on Wheel of Fortune, update energy according to Wheel
                                player[i].setPosition(wheelCell.spinTheWheel(player[i].getPosition(), writer));
                                wheelCell.updateEnergy(roll, player[i]);
                                break;
                            case "?":
                                //if landed on Joker, update energy according to Joker
                                player[i].setPosition(jokerCell.cardOrWheel(player[i].getPosition(), writer, testMode, jChoice));
                                jokerCell.updateEnergy(roll, player[i]);
                                break;
                            default:
                                //if landed on a regular spot, update energy according normally
                                regCell.updateEnergy(roll, player[i]);
                                writer.printBoth("You reached a regular cell.",true);
                        }
                        
                        board.addPlayer(player[i].getPosition());

                        //After playing the round, check if racers energy is enough to continue on game
                        if (player[i].getEnergy() <= 0) {
                            writer.printBoth("Your energy level reached " + player[i].getEnergy() + ". \r\n" + player[i].getName() + " will be sold as a slave. \r\n",false);
                        } //If his/her energy is enough, display the energy level of the round
                        else {
                            writer.printBoth("Your energy level is currently " + player[i].getEnergy() + ". \r\n",false);
                        }
                    } //If the energy is not enough at the beggining of the round, eliminate racers's turn
                    else {
                        writer.printBoth("\r\nPlayer " + player[i].getName() + " will be sold as a slave at the end. \r\n",false);
                    }
                }

                writer.print(board.toString());  // Dispay board

                //End of game: How many winners? Or are all slaves?
                numOfWinners = 0;
                numOfSlaves = 0;
                for (Racer player1 : player) {
                    if (player1.getPosition() >= 90 && player1.getEnergy() > 0) {
                        numOfWinners++;
                    } else if (player1.getEnergy() < 0) {
                        numOfSlaves++;
                    }
                }
            } while (numOfWinners == 0 && (numOfSlaves < player.length));
            // And the winner(s)/slave(s) is/are
            winnersReport(numOfWinners, player);

            play = playAgain();  // Does the user want to play again?
        } while (play == anotherGame);
        // Closing message
        writer.printBoth("\r\n-->  Hope you enjoyed Version 3.0 of the Spartan Race game.  <--\r\n",true);
        writer.close();
    }// end of main()

    // Static methods
    //---------------
    public static Racer[] setUpRacers(Scanner keyIn) {
        int numOfRacers, playerType;

        // How many racers?
        do {
            numOfRacers = getValidInput("\r\nHow many racers will there be (2 to 4)? ",keyIn,"Number of Racers Out Of Bounds. ");//change this
            if (numOfRacers < 2 || numOfRacers > 4) {
                writer.printBoth("\t" + numOfRacers + " racers(s) is not possible. The track can accommodate 2 to 4 players only.\r\n", false);
                if(testMode)
                    exit("Wrong number format");//change this
            }
        } while (numOfRacers < 2 || numOfRacers > 4);

        // Get name of players and create Racer objects
        Racer[] player = new Racer[numOfRacers];
        for (int i = 0; i < numOfRacers; i++) {
            writer.printBoth("\r\nName of Racer #" + (i + 1) + " please: ",false);
            String name = getNext(keyIn);
            if(testMode){
                writer.printBoth(name, true);
            } else {
                writer.println(name);
            }
            do {
                playerType = getValidInput("\r\nSelect the type of racers: 1 - Spartiate | 2 - Helot | 3 - Perioikoi: ",keyIn,"Invalid Player Type.");//keyIn.nextInt();
                if (playerType < 1 || playerType > 3) {
                    if(testMode){
                        exit("\r\n**Invalid Type Selection Error**");
                    }
                    writer.printBoth("\t Player type " + playerType + " is not available.",true);
                }
            } while (playerType < 1 || playerType > 3);

            //Create each different type of racers
            switch (playerType) {
                case 1:
                    player[i] = new RacerSpartiate(name);
                    break;
                case 2:
                    player[i] = new RacerHelot(name);
                    break;
                case 3:
                    player[i] = new RacerPerioikoi(name);
                    break;
                default:
                    break;
            }
        }
        return player;
    } // method setUpRacers

    public static void winnersReport(int n, Racer[] p) {
        if (n > 0) {
            writer.printBoth("\r\nWe have " + n + (n > 1 ? " winners!" : " winner!")
                    + "\r\nCongratulations to:",true);
            for (Racer p1 : p) {
                if (p1.getPosition() > 89 && p1.getEnergy() > 0) {
                    writer.printBoth("\t" + p1.getName(), false);
                }
            }
            writer.printBoth("\r\nThe rest of you will be sold as slaves!\r\n",true);
        } else {
            for (Racer p1 : p) {
                writer.printBoth("\t" + p1.getName(), true);
            }
            writer.printBoth(" will all be sold as slaves!\r\n",true);
        }
    } //method winnersReport

    public static int playAgain() {
        Scanner keyIn = new Scanner(System.in);
        writer.printBoth("Would you like to play again? (1 for yes) ",false);
        String playAgain = getNext(keyIn);
        writer.println(playAgain);
        int again;
        try{
            again = Integer.parseInt(playAgain);
        }catch(Exception ex){
            again = 0;
        }
        return again;
    }// method playAgain
    
    public static Scanner setUpFile(){
        /* Uncomment File you wish to use in debug-mode */
        input = new File("input_files/debug_input.txt");
//        input = new File("input_files/joker_error_format.txt");
//        input = new File("input_files/premature.txt");
//        input = new File("input_files/wrong_debug_input.txt");
//        input = new File("input_files/wrong_dice.txt");
//        input = new File("input_files/wrong_joker.txt");
        try {
            return new Scanner(input);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found. Please create an input text file with the following name: \"debug_input.txt\" in the \"input_files\" folder.");
            System.exit(0);
        } 
        return null;
    }
    
    public static int getValidInput(String prompt, Scanner keyIn, String exitMsg){
        writer.printBoth(prompt, false);
        boolean check = true;
        String temp = "";
        int varToInit = -1;
        while(check){ //Controls the prompts and re-prompts for inputs in case of errors
            try{
                temp = getNext(keyIn);
                if(testMode){
                    writer.printBoth(temp, true);
                }else{
                    writer.println(temp);
                }
                varToInit = Integer.parseInt(temp);                
                check = false;
            }catch(Exception e){
                if(testMode){
                    exit(exitMsg);
                }else{
                    writer.printBoth("Invalid Input Error. Please try again.\r\n" + prompt,false);
                }
            }
        } 
        return varToInit;
    }    
    
    public static void exit(String msg){
        writer.printBoth(msg, true);
        writer.close();
        System.exit(0);
    }
    public static String getNext(Scanner keyIn){
        if(testMode && !keyIn.hasNext()){
            exit("\r\n**File Ended Prematurely**");
        }
        return keyIn.next();
    }
}
