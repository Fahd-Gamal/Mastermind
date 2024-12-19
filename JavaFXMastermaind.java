package javafxmastermaind;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Arrays;

public class JavaFXMastermaind extends Application {

    private int[] correctColors = {1, 3, 5, 7};
    private int[][] attemptsGrid = new int[10][4];
    private int attemptIndex = 0; 
    private int currentColorIndex = 0;
    private final int maxAttempts = 10;

    @Override
    public void start(Stage primaryStage){
        Image backgroundImage = new Image("file:D:/Documents/NetBeansProjects/Mastermind/WhatsApp Image 2024-12-09 at 20.09.29_a908cdcc.jpg");  // تم تعديل المسار
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true));
        GridPane mainGrid = new GridPane();
        mainGrid.setBackground(new Background(background));
        mainGrid.setVgap(50); 
        mainGrid.setHgap(25);

        Label firstUserLabel = new Label("First User (Solution)");
        mainGrid.add(firstUserLabel, 0, 0, 4, 1);
        HBox solutionRow = new HBox();
        Button[] solutionButtons = new Button[4];
        for (int i = 0; i < 4; i++) {
            solutionButtons[i] = new Button();
            solutionButtons[i].setMinSize(40,40);
            solutionButtons[i].setStyle("-fx-background-color: grey; -fx-shape: \"M 0,20 A 20,20 0 1,0 40,20 A 20,20 0 1,0 0,20 Z\";");
            solutionRow.getChildren().add(solutionButtons[i]);
        }
        mainGrid.add(solutionRow, 0, 1, 4, 1);

        GridPane attemptsGridPane = new GridPane();
        attemptsGridPane.setVgap(17);  
        for (int row = 0; row < maxAttempts; row++) {
            for (int col = 0; col < 4; col++) {
                Button attemptButton = new Button();
                attemptButton.setMinSize(40, 40);
                attemptButton.setStyle("-fx-background-color: white; -fx-shape: \"M 0,20 A 20,20 0 1,0 40,20 A 20,20 0 1,0 0,20 Z\";");
                attemptsGridPane.add(attemptButton, col, row);
            }
        }
        mainGrid.add(attemptsGridPane, 0, 2, 4, maxAttempts);

        GridPane smallerGridPane = new GridPane();
        smallerGridPane.setVgap(27); 
        for (int row = 0; row < maxAttempts; row++) {
            for (int col = 0; col < 4; col++) {
                Button smallerButton = new Button();
                smallerButton.setMinSize(15, 15); 
                smallerButton.setStyle("-fx-background-color: white; -fx-shape: \"M 0,7.5 A 7.5,7.5 0 1,0 15,7.5 A 7.5,7.5 0 1,0 0,7.5 Z\";");
                smallerGridPane.add(smallerButton, col, row);
            }
        }
        mainGrid.add(smallerGridPane, 4, 2, 4, maxAttempts);

        Label colorLabel = new Label("Available Colors");
        mainGrid.add(colorLabel, 5, 0);
        HBox colorButtonsBox = new HBox(20);
        Button[] colorButtons = new Button[7];
        for (int i = 0; i < 6; i++) {
            int colorIndex = i + 1;
            colorButtons[i] = new Button();
            colorButtons[i].setMinSize(40, 40);
            colorButtons[i].setStyle("-fx-background-color: " + getColor(colorIndex) + "; -fx-shape: \"M 0,20 A 20,20 0 1,0 40,20 A 20,20 0 1,0 0,20 Z\";");
            colorButtons[i].setOnAction(e -> selectColor(colorIndex));
            colorButtonsBox.getChildren().add(colorButtons[i]);
        }
        mainGrid.add(colorButtonsBox, 5, 1);

        Button checkButton = new Button("Check Guess");
        checkButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        checkButton.setOnAction(e -> checkGuess());
        mainGrid.add(checkButton, 5, 2);

        Button resetButton = new Button("Reset Game");
        resetButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        resetButton.setOnAction(e -> resetGame());
        mainGrid.add(resetButton, 5, 3);

        Scene scene = new Scene(mainGrid, 800, 600);
        primaryStage.setTitle("Mastermind Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void selectColor(int colorIndex) {
        if (currentColorIndex < 4) {
            attemptsGrid[attemptIndex][currentColorIndex] = colorIndex;
            currentColorIndex++;
        }
    }

    private void checkGuess() {
        if (currentColorIndex == 4) {
            int correctPosition = 0;
            int correctColor = 0;

            for (int i = 0; i < 4; i++) {
                if (attemptsGrid[attemptIndex][i] == correctColors[i]) {
                    correctPosition++;
                } else {
                    int currentColor = attemptsGrid[attemptIndex][i]; 
                    if (Arrays.stream(correctColors).anyMatch(c -> c == currentColor)) {
                        correctColor++;
                    }
                }
            }

            System.out.println("Correct Position: " + correctPosition + ", Correct Colors: " + correctColor);

            attemptIndex++;
            currentColorIndex = 0;

            if (correctPosition == 4) {
                System.out.println("You Win!");
            } else if (attemptIndex >= maxAttempts) {
                System.out.println("Game Over!");
            }
        } else {
            System.out.println("Please select 4 colors before checking.");
        }
    }

    private void resetGame() {
        attemptIndex = 0;
        currentColorIndex = 0;
        attemptsGrid = new int[10][4];
        System.out.println("Game reset!");
    }

    private String getColor(int index) {
        switch (index) {
            case 1: return "red";
            case 2: return "blue";
            case 3: return "green";
            case 4: return "yellow";
            case 5: return "purple";
            case 6: return "orange";
            default: return "grey";
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
