/* Java program for Sudoku generator */
import java.lang.*;

public class SudokuMain
{
	private static int board[][];
	private static int SRN; 
	private static int nummd; 
	private static final int GRID_SIZE = 9;

	// Constructor
	SudokuMain(int nummd)
	{
		this.nummd = nummd;
		Double SRNd = Math.sqrt(GRID_SIZE);
		SRN = SRNd.intValue();

		board = new int[GRID_SIZE][GRID_SIZE];
	}

// Sudoku Generator
	
	private static boolean isNumberInRow(int[][] board, int number, int row) {
	    for (int i = 0; i < GRID_SIZE; i++) {
	      if (board[row][i] == number) {
	        return true;
	      }
	    }
	    return false;
	  }
	  
	  private static boolean isNumberInColumn(int[][] board, int number, int column) {
	    for (int i = 0; i < GRID_SIZE; i++) {
	      if (board[i][column] == number) {
	        return true;
	      }
	    }
	    return false;
	  }
	  
	  private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
	    int localBoxRow = row - row % 3;
	    int localBoxColumn = column - column % 3;
	    
	    for (int i = localBoxRow; i < localBoxRow + 3; i++) {
	      for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
	        if (board[i][j] == number) {
	          return true;
	        }
	      }
	    }
	    return false;
	  }
	  
	  private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
	    return !isNumberInRow(board, number, row) &&
	        !isNumberInColumn(board, number, column) &&
	        !isNumberInBox(board, number, row, column);
	  }
	
	  private static boolean fillBoard(int[][] board) {
		    for (int row = 0; row < GRID_SIZE; row++) {
		      for (int column = 0; column < GRID_SIZE; column++) {
		        if (board[row][column] == 0) {
		          for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
		        	int numtotry = randomGenerator(GRID_SIZE);
		            if (isValidPlacement(board, numtotry, row, column)) {
		              board[row][column] = numtotry;
		              
		              if (fillBoard(board)) {
		                return true;
		              }
		              else {
		                board[row][column] = 0;
		              }
		            }
		          }
		          return false;
		        }
		      }
		    }
		    return true;
		  }
	  
	
	public static int randomGenerator(int num){
		return (int) Math.floor((Math.random()*num+1));
	}
	
	// Removing an amount of digits from board 
	public static void removemd(){
		int count = nummd;
			while (count != 0){
				int cellId = randomGenerator(GRID_SIZE*GRID_SIZE)-1;
				// System.out.println(cellId);
				// extract coordinates i and j
				int i = (cellId/GRID_SIZE);
				int j = cellId%9;
				if (j != 0) {
					j = j - 1;}
	
				// System.out.println(i+" "+j);
				if (board[i][j] != 0){
					count--;
					board[i][j] = 0;
				}
			}
		}

	

	  private static boolean solveBoard(int[][] board) {
	    for (int row = 0; row < GRID_SIZE; row++) {
	      for (int column = 0; column < GRID_SIZE; column++) {
	        if (board[row][column] == 0) {
	          for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
	            if (isValidPlacement(board, numberToTry, row, column)) {
	              board[row][column] = numberToTry;
	              if (solveBoard(board)) {
	                return true;
	              }
	              else {
	                board[row][column] = 0;
	              }
	            }
	          }
	          return false;
	        }
	      }
	    }
	    return true;
	  }
	
	public void printSudoku()
	{
	    for (int row = 0; row < GRID_SIZE; row++) {
	        if (row % 3 == 0 && row != 0) {
	          System.out.println("- - - - - - - - - - -");
	        }
	        for (int column = 0; column < GRID_SIZE; column++) {
	          if (column % 3 == 0 && column != 0) {
	            System.out.print("|" + " ");
	          }
	          System.out.print(board[row][column] + " ");
	        }
	        System.out.println();
	      }
	    System.out.println();
	}
	public static void main(String[] args){		
		int nummd = 35;
		SudokuMain sudoku = new SudokuMain(nummd);
		fillBoard(board);
		removemd();
		sudoku.printSudoku();
	    if (solveBoard(board)) {
	        System.out.println("Solved successfully!");
	      }
	      else {
	        System.out.println("Unsolvable board :(");
	      }
	    System.out.println();
		sudoku.printSudoku();
	}
}

