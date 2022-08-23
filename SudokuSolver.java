package com.company;
import java.util.*;

public class SudokuSolver {

    private static int boardSize = 9;

    //Checks if the current row has the same number we are trying to place
    private static boolean isInRow(int[][] board, int row, int placement) {

        for(int i=0;i<boardSize;i++) {
            if(board[row][i] == placement)
                return true;
        }
        return false;
    }

    //Checks if the current column has the same number we are trying to place
    private static boolean isInColumn(int[][] board, int column, int placement) {

        for(int i=0;i<boardSize;i++) {
            if(board[i][column] == placement)
                return true;
        }
        return false;
    }

    //Checks if the current '3x3' SubGrid has the same number we are trying to place
    private static boolean isInSubGrid(int[][] board, int row, int column, int placement) {

        //Gets the row and column of the first cell in the SubGrid
        int subGridFirstRow = row - row%3;
        int subGridFirstColumn = column - column%3;

        for(int i=subGridFirstRow;i<subGridFirstRow+3;i++) {
            for(int j=subGridFirstColumn;j<subGridFirstColumn+3;j++) {
                if(board[i][j] == placement)
                    return true;
            }
        }

        return false;
    }

    private static boolean isValidPlacement(int[][] board, int row, int column, int placement) {

        return  !isInRow(board,row,placement) && !isInColumn(board,column,placement) &&
                !isInSubGrid(board,row,column,placement);

    }

    private static boolean solve(int[][] board) {

        for(int row=0;row<boardSize;row++) {

            for(int column=0;column<boardSize;column++) {
               if(board[row][column] == 0) {

                   for(int placement=1;placement<=boardSize;placement++) {
                       if(isValidPlacement(board,row,column,placement)) {
                           board[row][column] = placement;

                           if(solve(board))
                               return true;
                           else
                               board[row][column] = 0;
                       }
                   }
                   return false;
               }
            }
        }
        return true;
    }

    private static void printBoard(int[][] board) {

        for(int i=0;i<boardSize;i++) {
            for(int j=0;j<boardSize;j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[boardSize][boardSize];

        for(int i=0;i<boardSize;i++) {
            for(int j=0;j<boardSize;j++) {
                board[i][j] = sc.nextInt();
            }
        }

        if(solve(board)) {
            System.out.println("Solved Successfully!");
            System.out.println();
            printBoard(board);
        }
        else
            System.out.println("Can't Solve!");

    }
}
