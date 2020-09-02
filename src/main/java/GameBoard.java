import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the GameBoard It solutions for sudoku solution by checking rows columns and
 * all mini squares
 *
 * @author sarthak jain
 */
public class GameBoard {

  int rows = 9;
  int columns = 9;
  int maxSquareSize = 9;
  int minSquareSize = 3;
  int board[][];
  Result result;

  /**
   * Get the no of rows in the game
   *
   * @return result
   */
  public Result getResult() {
    return result;
  }

  /**
   * set the no of rows
   *
   * @param result
   */
  public void setResult(Result result) {
    this.result = result;
  }

  enum checkType {
    ROW,
    COLUMN
  }

  /**
   * Constructor that accepts the board input
   *
   * @param board
   */
  public GameBoard(int board[][]) {
    result = new Result();
    this.board = board;
  }

  /** default constructor */
  public GameBoard() {}

  /**
   * Get the no of rows
   *
   * @return rows
   */
  public int getRows() {
    return rows;
  }

  /**
   * set the no of rows
   *
   * @param rows
   */
  private void setRows(int rows) {
    this.rows = rows;
  }

  /**
   * Get the no of columns
   *
   * @return columns
   */
  public int getColumns() {
    return columns;
  }

  /**
   * set the no of columns
   *
   * @param columns
   */
  private void setColumns(int columns) {
    this.columns = columns;
  }

  /**
   * Get the no of maxSquareSize
   *
   * @return maxSquareSize
   */
  public int getMaxSquareSize() {
    return maxSquareSize;
  }

  /**
   * set the no of maxSquareSize
   *
   * @param maxSquareSize
   */
  private void setMaxSquareSize(int maxSquareSize) {
    this.maxSquareSize = maxSquareSize;
  }

  /**
   * Get the no of minSquareSize
   *
   * @return minSquareSize
   */
  public int getMinSquareSize() {
    return minSquareSize;
  }

  /**
   * set the no of minSquareSize
   *
   * @param minSquareSize
   */
  private void setMinSquareSize(int minSquareSize) {
    this.minSquareSize = minSquareSize;
  }

  /**
   * Get the no of board
   *
   * @return board
   */
  public int[][] getBoard() {
    return board;
  }

  /**
   * set the no of board
   *
   * @param board
   */
  public void setBoard(int board[][]) {
    this.board = board;
  }

  /**
   * This method is for validating if the submitted file follows all the rules for rows,columns and
   * all the mini squares
   */
  public void validateSubmission() {

    int boardSize = getMaxSquareSize();

    // Check for each row of solution
    for (int i = 0; i < boardSize; i++) {
      checkRowColumn(board[i], i, 0, checkType.ROW);
    }

    // Check for each columns of solution
    for (int j = 0; j < boardSize; j++) {
      int[] boardCol = new int[boardSize];

      for (int i = 0; i < boardSize; i++) {
        boardCol[i] = board[i][j];
      }

      checkRowColumn(boardCol, 0, j, checkType.COLUMN);
    }

    // Check for each square of solution
    valid_subsquares(board);
  }

  /** This method is for validating all the mini squares in the submission */
  public void valid_subsquares(int[][] grid) {
    for (int row = 0; row < 9; row = row + 3) {
      for (int col = 0; col < 9; col = col + 3) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (int r = row; r < row + 3; r++) {
          for (int c = col; c < col + 3; c++) {
            // Checking for repeated values.
            if (grid[r][c] != 0) {
              if (map.containsKey(grid[r][c])) {
                String val = map.get(grid[r][c]).concat(",[" + (r + 1) + "," + (c + 1) + "]");
                if (result.getFaults().containsKey("[" + (r + 1) + "," + (c + 1) + "]")) {
                  val =
                      val.concat("," + result.getFaults().get("[" + (r + 1) + "," + (c + 1) + "]"));
                }
                result.getFaults().put("[" + (r + 1) + "," + (c + 1) + "]", val);
                result.setValid(new Boolean(false));
              } else {
                map.put(grid[r][c], "[" + (r + 1) + "," + (c + 1) + "]");
              }
            }
          }
        }
      }
    }
  }

  /** This method is for validating all the rows and columns in the submission */
  private void checkRowColumn(int[] boardPortion, int row, int col, checkType type) {

    int boardLength = maxSquareSize;
    Map<Integer, String> hashmap = new HashMap();
    int[] hashArray = new int[10];
    for (int i = 0; i < boardLength; i++) {
      hashArray[boardPortion[i]]++;
      if (hashmap.containsKey(boardPortion[i])) {
        String val = hashmap.get(boardPortion[i]);
        switch (type) {
          case ROW:
            val = val.concat(",[" + (row + 1) + "," + (i + 1) + "]");
            hashmap.put(boardPortion[i], val);
            result.getFaults().put("[" + (row + 1) + "," + (i + 1) + "]", val);
            break;
          case COLUMN:
            val = val.concat(",[" + (i + 1) + "," + (col + 1) + "]");
            hashmap.put(boardPortion[i], val);
            result.getFaults().put("[" + (i + 1) + "," + (col + 1) + "]", val);
            break;
        }
        result.setValid(new Boolean(false));
      } else {
        switch (type) {
          case ROW:
            hashmap.put(boardPortion[i], "[" + (row + 1) + "," + (i + 1) + "]");
            break;
          case COLUMN:
            hashmap.put(boardPortion[i], "[" + (i + 1) + "," + (col + 1) + "]");
            break;
        }
      }
    }
  }
}
