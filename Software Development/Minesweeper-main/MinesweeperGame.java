package cs1302.game;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.io.PrintWriter;
/**
   This class {@code MinesweeperGame} contains all the methods for the
   Minesweeper Game. The bulk of the game is in this class.
*/

public class MinesweeperGame {
    private String[][] grid; //minesweeper grid
    private boolean[][] containMine; //shows whether there is a mine or not
    private int mines = 0; //total mines
    private int rounds = 0; //rounds taken to win
    private String[][] noFog; //this shows where the bombs are
    int rows = 0; //amount of rows in grid
    int cols = 0; //amount of columns in grid
    Scanner keyboard = new Scanner(System.in);

    /**
       Constructs and object of {@link MinesweeperGame} using the seed file.
       @param seed which is the seed file.
    */

    public MinesweeperGame(String seed) {
        try { //try catch is used for displaying an error for an invalid seed file
            File seedFile = new File(seed);
            Scanner configScanner = new Scanner(seedFile);
            if (configScanner.hasNextInt()) {
                rows = configScanner.nextInt();
                if (configScanner.hasNextInt()) {
                    cols = configScanner.nextInt();
                    if (configScanner.hasNextInt()) {
                        mines = configScanner.nextInt();
                    }
                }
            } else {
                System.err.println("Seedfile Format Error: Cannot create game with " + seedFile);
                System.err.print(", because it is not formatted correctly.");
                System.exit(1);
            }
            if (mines > (rows * cols)) {
                System.err.println("Seedfile Format Error: Cannot create game with " + seedFile);
                System.err.print(", because it is not formatted correctly.");
                System.exit(1);
            }
            if (rows < 5 || cols < 5) {
                lessThanFiveError();
            }
            setUpGrid();
            int row = 0;
            int col = 0;main
            for (int i = 0; i < mines; i++) {
                if (configScanner.hasNextInt()) {
                    row = configScanner.nextInt();
                    if (configScanner.hasNextInt()) {
                        col = configScanner.nextInt();
                        if ((row < rows && row >= 0) && (col < cols && col >= 0)) {
                            containMine[row][col] = true;
                        } 81              } // for                                              else {
                            System.err.println("Seedfile Format Error: Cannot create game with ");
                            System.err.print(seedFile + ", because it is not formatted correctly.");
                            System.out.println();
                            System.exit(1);
                        }
                    } else {
                        System.err.println("Seedfile Format Error: Cannot create game with ");
                        System.err.print(seedFile + ", because it is not formatted correctly.");
                        System.out.println();
                        System.exit(1);
                     }
                 } else {
                     System.err.println("Seedfile Format Error: Cannot create game with ");
                     System.err.print(seedFile + ", because it is not formatted correctly.");
                     System.out.println();
                     System.exit(1);
                 }
         } catch (FileNotFoundException e) {
             printSeedFileNotFoundError();
         } //catch
     } //MinesweeperGame constructor

     /**
        This is the random generator contructor.
        @param rows this is the user input for rows
        @param cols this is the user input for cols
        @param mines this is the total number of mines
        @param file this is where all the info about the game is written.
     */

     public MinesweeperGame(String file, int rows, int cols, int mines) {
         try {
             PrintWriter writer = new PrintWriter(file);
             this.rows = rows;
             this.cols = cols;
             this.mines = mines;
             writer.println("Rows: " + rows);
             writer.println("Columns: " + cols);
             writer.println("Mines: " + mines);
             if (mines > (rows * cols)) {
                 System.err.println("Invalid amount of mines");
                 System.exit(1);
             } //if
             Random xPosGenerator = new Random(); // This generates the x position
             Random yPosGenerator = new Random(); // This generates the y position
             if (rows < 5 || cols < 5) {
                 lessThanFiveError();
             } else {
                 setUpGrid();
                 //This below section generates the random location for the mines.
                 //It also makes sure not to place a mine in a place where there already is a mine.
                 int counter = 0;
                 int mineCounter = 1;
                 boolean check = false;
                 while (counter < mines) {
                     int row = xPosGenerator.nextInt(rows);
                     int col = yPosGenerator.nextInt(cols);
                     if (containMine[row][col] == true) {
                         counter --;
                         check = true;
                     } //if
                     counter++;
                     containMine[row][col] = true;
                     if (check == false) {
                         writer.println("Mine " + mineCounter + ": " + row + " " + col);
                         mineCounter++;
                     } //if
                     check = false;
                 } //while
                 writer.close();
             } //else
         } catch (FileNotFoundException e) {
             printGenFileNotFoundError();
         } //catch
     } //Minesweeper Random Generator Constructor

     /**
      * This sets up the grid for the Constructor.
      */

     public void setUpGrid() {
         grid = new String[rows][cols];
         containMine = new boolean[rows][cols];
         noFog = new String[rows][cols];
         for (int i = 0; i < rows; i++) {
             for (int j = 0; j < cols; j++) {
                 if (cols > 9) {
                     grid[i][j] = "    ";
                     containMine[i][j] = false;
                     noFog[i][j] = "    ";
                 } else {
                     grid[i][j] = "   ";
                     containMine[i][j] = false;
                     noFog[i][j] = "   ";
                 } //else
             } //for
         } //for
     } //setUpGrid

     /**
      * This prints the Seed File Not Found error.
      */

     public void printSeedFileNotFoundError() {
         System.err.println("Seedfile Not Found Error: Cannot create game with seed file");
         System.err.print("because it cannot be found or cannot be read due to permission.");
         System.out.println();
         System.exit(1);
     } //printSeedFileNotFoundError

     /**
      * This prints the file not found error for --gen option.
      */

     public void printGenFileNotFoundError() {
         System.err.println("File Not Found Error: Cannot write info into specified file");
         System.err.print("because it cannot be found or cannot be written on due to permission.");
         System.out.println();
         System.exit(1);
     } //printGenFileNotFoundError

     /**
      * This error prints if the amount of rows or columns is less than 5.
      */

     public void lessThanFiveError() {
         String words1 = "Seedfile Value Error: Cannot create a mine ";
         String words2 = "field with that many rows and/or columns!";
         System.err.println(words1 + words2);
         System.exit(3);
     } //lessThanFiveError
     /**
        This is the welcome message for the game.
     */

     public void printWelcome() {
         String line = " /    \\| | '_ \\ / _ \\/ __\\ \\ /\\ / / _ \\/ _ \\ '_ \\ / _ \\ '__|";
         System.out.println("        _");
         System.out.println("  /\\/\\ (_)_ __   ___  _____      _____  ___ _ __   ___ _ __");
         System.out.println( " /    \\| | '_ \\ / _ \\/ __\\ \\ /\\ / / _ \\/ _ \\ '_ \\ / _ \\ '__|");
         System.out.println("/ /\\/\\ \\ | | | |  __/\\__ \\\\ V  V /  __/  __/ |_) |  __/ |");
         System.out.println("\\/    \\/_|_| |_|\\___||___/ \\_/\\_/ \\___|\\___| .__/ \\___|_|");
         System.out.println("                                     ALPHA |_| EDITION");
         System.out.println();
     } //printWelcome
     /**
        This prints the mine field.
     */

     public void mineField() {
         for (int i = 0; i < grid.length; i++) {
             if (i <= 9) {
                 System.out.print(" ");
             }
             System.out.print(i + " |");
             for (int j = 0; j < grid[i].length; j++) {
                 System.out.print(grid[i][j]);
                 if (j < grid[i].length - 1) {
                     System.out.print("|");
                 }
             } System.out.println("|");
         }
         if (cols > 9) {
             System.out.print("      ");
         } else {
             System.out.print("     ");
         }
         for (int i = 0; i < grid[0].length; i++) {
             if (cols > 9) {
                 System.out.print(i + "   ");
             } else {
                 System.out.print(i + "  ");
             }
             if (i < 9) {
                 System.out.print(" ");
             }
         }
         System.out.println();
     } //printMineField
     /**
        This prompts the user for their command with respect to the game.
     */

     public void promptUser() {
         int inputRow = 0;
         int inputCol = 0;
         System.out.print("minesweeper-alpha: ");
         String command = keyboard.next();
         if (command.equals("help") || command.equals("h")) {
             help();
         } else if (command.equals("quit") || command.equals("q")) {
             quit();
         } else if (command.equals("nofog")) {
             printNoFogGrid();
         } else if (command.equals("reveal") || command.equals("r")) {
             inputRow = keyboard.nextInt();
             inputCol = keyboard.nextInt();
             if (inputRow > rows || inputCol > cols || inputRow < 0 || inputCol < 0) {
                 System.err.println("Input Error: Error out of bounds");
             }
             reveal(inputRow, inputCol);
         } else if (command.equals("m") || command.equals("mark")) {
             inputRow = keyboard.nextInt();
             inputCol = keyboard.nextInt();
             if (inputRow > rows || inputCol > cols || inputRow < 0 || inputCol < 0) {
                 System.err.println("Input Error: Error out of bounds");
             }
             mark(inputRow,inputCol);
         } else if (command.equals("guess") || command.equals("g")) {
             inputRow = keyboard.nextInt();
             inputCol = keyboard.nextInt();
             if (inputRow > rows || inputCol > cols || inputRow < 0 || inputCol < 0) {
                 System.err.println("Input Error: Error out of bounds");
             }
             guess(inputRow,inputCol);
         } else {
             System.err.println("Input Error: Command not recognized!");
         }
     } //promptUser
     /**
        This determins if the player lost the game which happens if the player selects a mine.
        @return allMinesRevealed && allSquaresRevealed which should be true if the person lost.
     */

     public boolean isLost() {
         boolean allMinesRevealed = true;
         boolean allSquaresRevealed = true;
         for (int i = 0; i < grid.length; i++) {
             for (int j = 0; j < grid[0].length; j++) {
                 if (containMine[i][j]) {
                     if (grid[i][j] != " F ") {
                         allMinesRevealed = false;
                     }
                 } else {
                     if (grid[i][j] == " F " || grid[i][j] == " ? " || grid[i][j] == "   ") {
                         allSquaresRevealed = false;
                     }
                 }
             }
         }
         return allMinesRevealed && allSquaresRevealed;
     } //isLost
     /**
        This determines if the player won the game which happens if the player marks all the mines
        and all squares that aren't mines are revealed.
        @return allMinesRevealed && allSquaresRevealed which should be true if the person won
     */

     public boolean isWon() {
         boolean allMinesRevealed = true;
         boolean allSquaresRevealed = true;
         for (int i = 0; i < grid.length; i++) {
             for (int j = 0; j < grid[0].length; j++) {
                 if (containMine[i][j]) {
                     if (grid[i][j] != " F ") {
                         allMinesRevealed = false;
                     }
                 } else {
                     if (grid[i][j] == " F " || grid[i][j] == " ? " || grid[i][j] == "   ") {
                         allSquaresRevealed = false;
                     }
                 }
             }
         }
         return allMinesRevealed && allSquaresRevealed;
     } //isWon

     /**
        This is the print win message for the game.
     */

     public void printWin() {
         System.out.println();
         System.out.println(" ░░░░░░░░░▄░░░░░░░░░░░░░░▄░░░░  \"So Doge\"");
         System.out.println(" ░░░░░░░░▌▒█░░░░░░░░░░░▄▀▒▌░░░");
         System.out.println(" ░░░░░░░░▌▒▒█░░░░░░░░▄▀▒▒▒▐░░░  \"Such Score\"");
         System.out.println(" ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐░░░");
         System.out.println(" ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐░░░  \"Much Minesweeping\"");
         System.out.println(" ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌░░░");
         System.out.println(" ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒▌░░  \"Wow\"");
         System.out.println(" ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐░░");
         System.out.println(" ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄▌░");
         System.out.println(" ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▌░");
         System.out.println(" ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒▐░");
         System.out.println(" ▐▒▒▐▀▐▀▒░▄▄▒▄▒▒▒▒▒▒░▒░▒░▒▒▒▒▌");
         System.out.println(" ▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒▒▒░▒░▒░▒▒▐░");
         System.out.println(" ░▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒░▒░▒░▒░▒▒▒▌░");
         System.out.println(" ░▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▄▒▒▐░░");
         System.out.println(" ░░▀▄▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▄▒▒▒▒▌░░");
         System.out.println(" ░░░░▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀░░░  CONGRATULATIONS!");
         System.out.println(" ░░░░░░▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀░░░░░  YOU HAVE WON!");
         System.out.println(" ░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▀▀░░░░░░░░  SCORE: " + score());
         System.out.println("");
         System.exit(0);
     } //printWin
     /**
        This is the print loss message for the game.
     */

     public void printLoss() {
         System.out.println();
         System.out.println(" Oh no... You revealed a mine!");
         System.out.println("  __ _  __ _ _ __ ___   ___    _____   _____ _ __ ");
         System.out.println(" / _` |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '__|");
         System.out.println("| (_| | (_| | | | | | |  __/ | (_) \\ V /  __/ |   ");
         System.out.println(" \\__, |\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|   ");
         System.out.println(" |___/                                            ");
         System.out.println();
         System.exit(0);
     } //printLoss
     /**
        Reveals one square which could either be a mine or it could show
        the number of adjacent mines.
        @param inputRow which is the row that the user wants to be shown
        @param inputCol which is the column that the user wants to be shown
     */

     public void reveal(int inputRow, int inputCol) {
         if (isInBounds(inputRow, inputCol)) {
             rounds++;
             if (containMine[inputRow][inputCol] == false) {
                 if (cols < 9) {
                     grid[inputRow][inputCol] = " " + getNumAdjMines(inputRow,inputCol) + " ";
                 } else {
                     grid[inputRow][inputCol] = "  " + getNumAdjMines(inputRow,inputCol) + " ";
                 }
             } else {
                 printLoss();
             }
         }
     } //reveal
     /**
        Marks the square with an F which represents a definite mine location according to the user.
        @param markedRow is the row that the user wants to mark
        @param markedCol is the column that the user wants to mark
     */

     public void mark(int markedRow, int markedCol) {
         if (isInBounds(markedRow, markedCol)) {
             rounds++;
             if (cols < 9) {
                 grid[markedRow][markedCol] = " F ";
             } else {
                 grid[markedRow][markedCol] = "  F ";
             }
         }
     } //mark
     /**
        Guess marks a square with a question mark which is the user's guess to where a mine is.
        @param guessedRow which is the row the user guesses to where a mine is.
        @param guessedCol which is the column the user guesses to where a mine is.
     */

     public void guess(int guessedRow, int guessedCol) {

         if (isInBounds(guessedRow, guessedCol)) {
             rounds++;
             if (cols < 9) {
                 grid[guessedRow][guessedCol] = " ? ";
             } else {
                 grid[guessedRow][guessedCol] = "  ? ";
             }
         }
     } //guess
     /**
        Prints nofog grid.
     */

     public void printNoFogGrid() {
         rounds++;
         for (int i = 0; i < noFog.length; i++) {
             if (i <= 9) {
                 System.out.print(" ");
             }
             System.out.print(i + " |");
             for (int j = 0; j < noFog[i].length; j++) {
                 if (containMine[i][j]) {
                     if (cols > 9) {
                         System.out.print("<" + grid[i][j].substring(1,3) + ">");
                     } else {
                         System.out.print("<" + grid[i][j].substring(1,2) + ">");
                     }
                 } else {
                     System.out.print(grid[i][j]);
                 }
                 if (j < noFog[i].length - 1) {
                     System.out.print("|");
                 }
             }
             System.out.println("|");
         } if (cols > 9) {
             System.out.print("      ");
         } else {
             System.out.print("     ");
         }
         for (int i = 0; i < grid[0].length; i++) {
             if (cols > 9 && i < 9) {
                 System.out.print(i + "    ");
             } else if (cols > 9 && i > 9) {
                 System.out.print(i + "   ");
             } else {
                 System.out.print(i + "   ");
             }
         }
     } //printNoFogGrid
     /**
        Helps user with which options they can select.
     */

     public void help() {
         rounds++;
         System.out.println("Commands Avaliable...");
         System.out.println(" - Reveal: r/reveal row col");
         System.out.println(" -   Mark: m/mark   row col");
         System.out.println(" -  Guess: g/guess  row col");
         System.out.println(" -   Help: h/help");
         System.out.println(" -   Quit: q/quit");
     } //help
     /**
        This lets the user quit the game.
     */

     public void quit() {
         System.out.println("Quitting the game...");
         System.out.println("Bye!");
         System.exit(0);
     } //quit
     /**
        This returns the number of mines around the square.
        @param row is the row the user wants to select
        @param col is the column the user wants to select
        @return numAdjMines which is the number of surrounding mines.
     */

     public int getNumAdjMines (int row, int col) {
         int numAdjMines = 0;
         for (int i = row - 1; i <= row + 1; i++) {
             if (!(i >= 0 && i < grid.length)) {
                 continue;
             }
             for (int j = col - 1; j <= col + 1; j++) {
                 if ((i == row && j == col) || (!(j >= 0 && j < grid[0].length))) {
                     continue;
                 } else {
                     if (containMine[i][j] == true) {
                         numAdjMines++;
                     }
                 }
             }
         }
         return numAdjMines;
     } //getNumAdjMines
     /**
        This is the main loop in the game. This is the only method called in the main.
     */

     public void play() {
         printWelcome();
         while (true) {
             System.out.println();
             System.out.println("Rounds Completed: " + rounds);
             printMineField();
             promptUser();
             if (isWon()) {
                 printWin();
             }
             if (isLost()) {
                 printLoss();
             }
         }
     } //play
     /**
        This checks whether the present square is within bounds.
        @param row is the row that the user has selected
        @param col is the column that the user has selected
        @return false if the row and col are not within the bounds and it returns true if they are
        in bounds.
     */

     public boolean isInBounds(int row, int col) {
         return (row >= 0 && row < containMine.length && col >= 0 && col < containMine[0].length);
     } //isInBounds

     /**
        This calculates and returns the score.
        @return score which is the score.
     */

     public double score() {
         double score = 0;
         score = 100.0 * rows * cols / rounds;
         return score;
     } //score
 } //MinesweeperGame class

