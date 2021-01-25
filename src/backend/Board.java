package backend;

import UIComponent.Cell;
import UIComponent.Piece;
import UIComponent.Player;
import javafx.scene.layout.GridPane;

public class Board {

    public int[][] board;
    int row, col;
    GridPane UIBoard;
    public WinStatus winStatus;
    public Minimax_Implementation minimax;

    public Board(int row, int col, GridPane UIBoard)
    {
        this.row = row;
        this.col = col;
        this.UIBoard = UIBoard;

        winStatus = WinStatus.HASEMPTYCELL;

        board = new int[this.row][this.col];
        initializeBoard();

    }

    void initializeBoard()
    {
        for(int i=0; i<row; i++)
        {
            for(int j=00; j<col; j++)
                board[i][j] = 0;
        }

        minimax = new Minimax_Implementation(this);
    }

    public boolean isEmptyBoard()
    {
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<col; j++)
            {
                if(board[i][j] == 0)
                    return true;
            }
        }

        return false;
    }

    public Cell turn(Player playerType, int col)
    {
        Cell cell = new Cell(-1, -1);

        if (playerType == Player.HUMAN) {
            if (board[0][col] != 0) {
                return cell;
            } else {
                for (int i = 0; i < row; i++) {
                    if (board[i][col] != 0) {

                        board[i-1][col] = 1;

                        cell = new Cell(i - 1, col);

                        Piece piece = new Piece(playerType, cell, UIBoard);
                        piece.addPieceToCell();

                        return cell;
                    }
                }
            }

            board[row - 1][col] = 1;

            cell = new Cell(row - 1, col);

            Piece piece = new Piece(playerType, cell, UIBoard);
            piece.addPieceToCell();

            return cell;
        } else {
            col = minimax.Minimax(board, 10, Integer.MIN_VALUE, Integer.MAX_VALUE, true).col;

            if (board[0][col] != 0) {
                col = minimax.Minimax(board, 10, Integer.MIN_VALUE, Integer.MAX_VALUE, true).col;
            } else {
                for (int i = 0; i < row; i++) {
                    if (board[i][col] != 0) {

                        board[i-1][col] = 2;

                        cell = new Cell(i - 1, col);

                        Piece piece = new Piece(playerType, cell, UIBoard);
                        piece.addPieceToCell();

                        return cell;
                    }
                }
            }

            board[row-1][col] = 2;

            cell = new Cell(row - 1, col);

            Piece piece = new Piece(playerType, cell, UIBoard);
            piece.addPieceToCell();

            return cell;

        }
    }

    public void printBoard(int[][] board)
    {
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<col; j++)
                System.out.print(board[i][j] + "  ");
            System.out.println();
        }
        System.out.println();
    }

    public int getWInStatus()
    {
        int winner = -1;
        int columns = this.col;
        int lines = this.row;

        for (int l = 0; l < lines; l++)
        {
            for (int c = 0; c < columns; c++)
            {
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



}
