package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Game_UI.fxml"));
        primaryStage.setTitle("Connect 4");
        primaryStage.setScene(new Scene(root, 950, 850));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

        /*Board board = new Board(6,7, new GridPane());

        Minimax_Implementation minimax_implementation = new Minimax_Implementation(board);

        Scanner sc = new Scanner(System.in);

        while(true)
        {
            int col;
            System.out.print("Enter column number: ");
            col = sc.nextInt();

            minimax_implementation.dropPiece(board.board, col, Player.HUMAN);

            Column column = minimax_implementation.Minimax(board.board, 10, Integer.MIN_VALUE, Integer.MAX_VALUE, true);

            minimax_implementation.dropPiece(board.board, column.col, Player.AI);

            board.printBoard(board.board);

        }
*/

    }
}
