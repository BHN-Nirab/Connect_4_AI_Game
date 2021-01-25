package backend;

import UIComponent.Player;

import java.util.ArrayList;
import java.util.Random;


public class Minimax_Implementation {

    Board board;

    public Minimax_Implementation(Board board) {
        this.board = board;
    }

    public ArrayList<Integer> getAvailableCol(int[][] board) {
        ArrayList<Integer> availableCol = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            if (board[0][i] == 0)
                availableCol.add(i);
        }

        return availableCol;
    }

    public int getWInStatus(int[][] board) {
        int winner = -1;
        int columns = 7;
        int lines = 6;

        for (int l = 0; l < lines; l++) {
            for (int c = 0; c < columns; c++) {
                int player = board[l][c];

                if (player == 0)
                    continue;

                if (c + 3 < columns && player == board[l][c + 1]
                        && player == board[l][c + 2] && player == board[l][c + 3])
                    winner = winner < 0 ? player : 0;

                if (l + 3 < lines && player == board[l + 1][c]
                        && player == board[l + 2][c] && player == board[l + 3][c])
                    winner = winner < 0 ? player : 0;

                if (c + 3 < columns && l + 3 < lines && player == board[l + 1][c + 1]
                        && player == board[l + 2][c + 2] && player == board[l + 3][c + 3])
                    winner = winner < 0 ? player : 0;

                if (c >= 3 && l + 3 < lines && player == board[l + 1][c - 1]
                        && player == board[l + 2][c - 2] && player == board[l + 3][c - 3])
                    winner = winner < 0 ? player : 0;
            }
        }

        if (winner < 1)
            return 0;
        else
            return winner == 1 ? 1 : 2;
    }

    public Column Minimax(int[][] board, int depth, int alpha, int beta, boolean isAI) {
        ArrayList<Integer> availableCol = getAvailableCol(board);

        if (getWInStatus(board) == 1)
            return new Column(-1, -1000);

        else if (getWInStatus(board) == 2)
            return new Column(-1, 1000);

        else if (availableCol.size() == 0 || depth == 0)
            return new Column(-1, 0);


        if (isAI) {
            Column column = new Column(4, Integer.MIN_VALUE);

            for (int col : availableCol
            ) {
                int tempBoard[][] = new int[6][7];
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 7; j++)
                        tempBoard[i][j] = board[i][j];
                }

                dropPiece(tempBoard, col, Player.AI);


                Column newCol = Minimax(tempBoard, depth - 1, alpha, beta, false);

                if (newCol.value > column.value) {
                    column.value = newCol.value;
                    column.col = col;
                }

                alpha = Math.max(alpha, column.value);

                if (alpha >= beta)
                    break;
            }

            return column;
        } else {
            Column column = new Column(availableCol.get(getRandomIntinRange(0, availableCol.size() - 1)), Integer.MAX_VALUE);

            for (int col : availableCol
            ) {
                int tempBoard[][] = new int[6][7];
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 7; j++)
                        tempBoard[i][j] = board[i][j];
                }

                dropPiece(tempBoard, col, Player.HUMAN);

                Column newCol = Minimax(tempBoard, depth - 1, alpha, beta, true);

                if (newCol.value < column.value) {
                    column.value = newCol.value;
                    column.col = col;
                }

                beta = Math.min(beta, column.value);

                if (alpha >= beta)
                    break;
            }

            return column;
        }
    }

    public int getRandomIntinRange(int min, int max) {
        return new Random().nextInt((max + 1) - min) + min;
    }

    public int dropPiece(int[][] board, int col, Player playerType) {

        if (board[0][col] != 0) {
            return -1;
        } else {
            for (int i = 0; i < 6; i++) {
                if (board[i][col] != 0) {
                    if (playerType == Player.HUMAN)
                        board[i - 1][col] = 1;

                    else {
                        board[i - 1][col] = 2;
                    }


                    return col;

                }
            }
        }

        if (playerType == Player.HUMAN)
            board[6 - 1][col] = 1;
        else
            board[6 - 1][col] = 2;

        return col;
    }

}