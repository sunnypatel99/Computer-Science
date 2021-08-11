package cs1302.game;
  /**                                                                                                                                                                                                                                                                             This class is the Driver which contains the main method.                                                                                                                                                                                                                     It takes a seed file as an argument which contains information for the                                                                                                                                                                                                 
     user's minesweeper game.                                                                                                                                                                                                                                                
     @param args which contains the seed file.                                                                                                                                                                                                                               
  */
 
 public class MinesweeperDriver {
    public static void main(String [] args) {
        String seedFile = "";
        String genFile = "";
        int rows = 0;
        int cols = 0;
        int mines = 0;
        if (args[0].equals("--seed")) {
           seedFile = args[1]; //this line sets the argument equal to the file                                                                                                                                                                                            
        } else if (args[0].equals("--gen")) {
            genFile = args[1];
            rows = Integer.parseInt(args[2]);
            cols = Integer.parseInt(args[3]);
            mines = Integer.parseInt(args[4]);
            MinesweeperGame randomGame = new MinesweeperGame(genFile, rows, cols, mines);
            randomGame.play();
        } else {
            System.err.println("Unable to interpret command-line arguments.");
            System.exit(1);
        } //if                                                                                                                                                                                                                                                             
 
        if (seedFile.length() > 0) {
            MinesweeperGame game = new MinesweeperGame(seedFile);
            game.play();
 
         } //if                                                                                                                                                                                                                                                             
     } //main                                                                                                                                                                                                                                                               
 } //MinesweeperDriver        
