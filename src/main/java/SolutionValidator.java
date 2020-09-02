import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class represents SolutionValidator It runs the main code
 *
 * @author sarthak jain
 */
public class SolutionValidator {

  public static void main(String[] args) {

    // parse the input args to get the sudoku board from the file input
    int board[][] = parseSolutionFile(args);

    // Create the SudokuPlu
    GameBoard game = new GameBoard(board);

    game.validateSubmission();

    boolean isValid = game.result.isValid();
    if (isValid) {
      System.out.println("Valid");
    } else {
      System.out.println("InValid");
    }

    if (game.result.isValid() == false) {
      Map<String, String> gfg = game.result.getFaults();
      for (Map.Entry<String, String> entry : gfg.entrySet()) {
        if (entry.getValue().length() > 1)
          System.out.println("Key = " + entry.getKey() + ", Reappeared at = " + entry.getValue());
      }
    }
  }

  /** This method is for parsing the solution csv Check if the file exists or provided or noy */
  static int[][] parseSolutionFile(String[] args) {

    int gameBoard[][] = new int[9][9];

    if (args.length < 1) {
      System.out.println("File not provided");
      System.exit(1);
    }

    String inputFile = args[0];

    DataInputStream in = null;

    if ("".equals(inputFile)) {
      System.out.println("empty arg provided for InputFile");
      System.exit(1);
    }

    // check and read the file

    try {

      // Check File exists
      File file = new File(inputFile);
      if (!file.exists()) {
        System.out.println(
            "The given file doesn't exists. Please input the full path of the file!");
        System.exit(1);
      }

      // Get the Buffered Reader from the given file
      FileInputStream stream = new FileInputStream(inputFile);

      in = new DataInputStream(stream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String strLine;

      int noOfRows = 0;
      ArrayList<String> strRowList = new ArrayList<String>();

      // Read File Line By Line to array List
      while ((strLine = br.readLine()) != null) {
        strRowList.add(strLine);
      }

      noOfRows = strRowList.size();

      if (noOfRows != 9) {
        System.out.println("The solution provided is Invalid as it is not for a 9*9 Sudoku");
      }

      int noOfCols = 0;

      // form the board from the string array list
      for (int i = 0; i < noOfRows; i++) {
        String row = strRowList.get(i);
        String[] strCols = row.split(",");
        noOfCols = strCols.length;

        if (noOfCols != 9) {
          System.out.println("The solution provided is Invalid as it is not for a 9*9 Sudoku");
        }

        // validate given input is square
        if (noOfCols != noOfRows) {
          System.out.println(
              "File error:: No of rows should match the no of columns. Problem in line "
                  + i
                  + " of the file");
          System.exit(1);
        }

        // Fill the array
        for (int j = 0; j < strCols.length; j++) {
          gameBoard[i][j] = Integer.parseInt(strCols[j]);
        }
      }

    } catch (IOException e) {
      System.out.print(
          "Problem in the input File. Please input \n java SudokuPlusValidator <inputfilepath>");
    } catch (Exception ex) {
      System.out.println("Problem in input File parsing." + ex.getMessage());
      // ex.printStackTrace();
    } finally {
      // Close the input stream
      try {
        in.close();
      } catch (IOException ioe) {
        System.out.println("ioException during close!");
      }
    }

    return gameBoard;
  }
}
