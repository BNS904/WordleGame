package com.example.wordle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class GameController {

    @FXML
    private Button qKey;

    @FXML
    private Button wKey;

    @FXML
    private Button eKey;

    @FXML
    private Button rKey;

    @FXML
    private Button tKey;

    @FXML
    private Button yKey;

    @FXML
    private Button uKey;

    @FXML
    private Button iKey;

    @FXML
    private Button oKey;

    @FXML
    private Button pKey;

    @FXML
    private Button aKey;

    @FXML
    private Button sKey;

    @FXML
    private Button dKey;

    @FXML
    private Button fKey;

    @FXML
    private Button gKey;

    @FXML
    private Button hKey;

    @FXML
    private Button jKey;

    @FXML
    private Button kKey;

    @FXML
    private Button lKey;

    @FXML
    private Button zKey;

    @FXML
    private Button xKey;

    @FXML
    private Button cKey;

    @FXML
    private Button vKey;

    @FXML
    private Button bKey;

    @FXML
    private Button nKey;

    @FXML
    private Button mKey;

    @FXML
    private Button enterKey;

    @FXML
    private Button backKey;

    @FXML
    private TextField cell1;

    @FXML
    private TextField cell2;

    @FXML
    private TextField cell3;

    @FXML
    private TextField cell4;

    @FXML
    private TextField cell5;

    @FXML
    private TextField cell6;

    @FXML
    private TextField cell7;

    @FXML
    private TextField cell8;

    @FXML
    private TextField cell9;

    @FXML
    private TextField cell10;

    @FXML
    private TextField cell11;

    @FXML
    private TextField cell12;

    @FXML
    private TextField cell13;

    @FXML
    private TextField cell14;

    @FXML
    private TextField cell15;

    @FXML
    private TextField cell16;

    @FXML
    private TextField cell17;

    @FXML
    private TextField cell18;

    @FXML
    private TextField cell19;

    @FXML
    private TextField cell20;

    @FXML
    private TextField cell21;

    @FXML
    private TextField cell22;

    @FXML
    private TextField cell23;

    @FXML
    private TextField cell24;

    @FXML
    private TextField cell25;

    @FXML
    private TextField cell26;

    @FXML
    private TextField cell27;

    @FXML
    private TextField cell28;

    @FXML
    private TextField cell29;

    @FXML
    private TextField cell30;

    @FXML
    private Button resetButton;

    ArrayList<Button> keyboardButtons;

    ArrayList<TextField> letterCells;
    ArrayList<TextField> rowOneCells;
    ArrayList<TextField> rowTwoCells;
    ArrayList<TextField> rowThreeCells;
    ArrayList<TextField> rowFourCells;
    ArrayList<TextField> rowFiveCells;
    ArrayList<TextField> rowSixCells;

    ArrayList<ArrayList<TextField>> rowList;

    ArrayList<String> listOfWords;

    int rowListCounter = 0;
    int activeCell = 0;
    String winningWord;
    boolean shouldDelete;

    public void initialize() {

        keyboardButtons = new ArrayList<>(Arrays.asList(qKey, wKey, eKey, rKey, tKey, yKey, uKey,
                iKey, oKey, pKey, aKey, sKey, dKey, fKey, gKey, hKey, jKey, kKey, lKey, zKey, xKey,
                cKey, vKey, bKey, nKey, mKey, enterKey, backKey, resetButton));
        keyboardButtons.forEach(button -> {
            checkKey(button);
            button.setFocusTraversable(false);
        });

        letterCells = new ArrayList<>(Arrays.asList(cell1, cell2, cell3, cell4, cell5, cell6, cell7,
                cell8, cell9, cell10, cell11, cell12, cell13, cell14, cell15, cell16, cell17, cell18, cell19,
                cell20, cell21, cell22, cell23, cell24, cell25, cell26, cell27, cell28, cell29, cell30));
        letterCells.forEach(textField -> {
            textField.setFocusTraversable(false);
            textField.setDisable(false);
            textField.setEditable(false);
            textField.clear();
            textField.setStyle("-fx-background-color: white; -fx-text-fill: black");
        });
         listOfWords = new ArrayList<>(Arrays.asList("HELLO", "WORLD", "SUSHI", "DEATH", "MOOSE", "STONE", "PIZZA"));

        rowOneCells = new ArrayList<>(Arrays.asList(cell1, cell2, cell3, cell4, cell5));
        rowTwoCells = new ArrayList<>(Arrays.asList(cell6, cell7, cell8, cell9, cell10));
        rowThreeCells = new ArrayList<>(Arrays.asList(cell11, cell12, cell13, cell14, cell15));
        rowFourCells = new ArrayList<>(Arrays.asList(cell16, cell17, cell18, cell19, cell20));
        rowFiveCells = new ArrayList<>(Arrays.asList(cell21, cell22, cell23, cell24, cell25));
        rowSixCells = new ArrayList<>(Arrays.asList(cell26, cell27, cell28, cell29, cell30));

        rowList = new ArrayList<>(Arrays.asList(rowOneCells, rowTwoCells, rowThreeCells, rowFourCells, rowFiveCells,
                rowSixCells));

        randomizeWord();
        System.out.println(winningWord);
    }

    private void checkKey(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            if (button.equals(backKey)) {
                clearCell();
            } else if(button.equals(enterKey)) {
                checkEnteredRow();

            } else if(button.equals(resetButton)) {
                initialize();
            } else {
                modifyCell(button);
            }
        });
    }

    private void checkEnteredRow() {
        int counter = 0;
        for (int i = 0; i < 5;i++) {
            if (rowList.get(rowListCounter).get(i).getText().isEmpty()) {

            } else {
                counter++;
                System.out.println(counter);
            }
        }

        if (counter == 5) {
            checkForWin();
        } else {
            System.out.println("try again");
        }
    }

    private String validWord(String givenWord) {
        for (String listOfWord : listOfWords) {
            if (listOfWord.equals(givenWord)) {
                return givenWord;
            }
        }
        return null;
    }

    private void checkForWin() {

        String combined = rowList.get(rowListCounter).get(0).getText() + rowList.get(rowListCounter).get(1).getText() +
                rowList.get(rowListCounter).get(2).getText() + rowList.get(rowListCounter).get(3).getText() +
                rowList.get(rowListCounter).get(4).getText();

        if(Objects.equals(combined, winningWord)) {
            setWin();
        }
        else if (Objects.equals(combined, validWord(combined))){
            continueToNextRow(combined);
        } else {
        }

    }

    private void setWin() {
        rowList.get(rowListCounter).forEach(textField -> textField.setStyle("-fx-background-color: lightgreen; -fx-text-fill: white"));
    }

    private void clearCell() {
        shouldDelete = true;
        rowList.get(rowListCounter).get(checkActiveCellSpace(true)).setText("");
    }

    private void modifyCell(Button button) {
            String keyChar = button.getText();
            shouldDelete = false;
            setActiveCell(0);
            System.out.println(checkActiveCellSpace(false));
            rowList.get(rowListCounter).get(checkActiveCellSpace(shouldDelete)).setText(keyChar);
    }

    private void setActiveCell(int aCell){
        activeCell = aCell;
    }


    private int checkActiveCellSpace(boolean delete) {
        if(delete) {
            if (activeCell == 0) {
                return 0;
            }
            for (int i = 4; i > -1; i--) {
                setActiveCell(i);
                if (!Objects.equals(rowList.get(rowListCounter).get(i).getText(), "")) {
                    return i;
                }
            }
        } else {
            if (activeCell == 4) {
                return 4;
            }
            for (int i = 0; i < 5; i++) {
                setActiveCell(i);
                if (Objects.equals(rowList.get(rowListCounter).get(i).getText(), "")) {
                    return i;
                }
            }
        }
        return 3;
    }


    private void continueToNextRow(String givenWord) {
        char[] givenWordCharArray = givenWord.toCharArray();

        //System.out.println(rowList.get(rowListCounter).get(0).getStyle().charAt(22));


        for(int i = 0; i < 5; i++) {
            if(givenWordCharArray[i] == winningWord.charAt(i)) {
                rowList.get(rowListCounter).get(i).setStyle("-fx-background-color: lightgreen; -fx-text-fill: white");
            }
        }
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                if (givenWordCharArray[j] == winningWord.charAt(k) &&
                        rowList.get(rowListCounter).get(j).getStyle().charAt(22) != 'l') {
                    rowList.get(rowListCounter).get(j).setStyle("-fx-background-color: gold; -fx-text-fill: white");
                }
            }
        }
        if(rowListCounter == 5) {
            gameLost();
        }
        rowListCounter++;
    }

    private void gameLost() {
        char[] winningWordCharArray= winningWord.toCharArray();

        rowList.forEach(ArrayList -> ArrayList.forEach(textField -> {
            textField.setText("");
            textField.setStyle("-fx-background-color: white; -fx-text-fill: black");
        }));

        for(int i = 0; i < 5; i++) {
            rowList.get(rowListCounter).get(i).setText(String.valueOf(winningWordCharArray[i]));
            rowList.get(rowListCounter).get(i).setStyle("-fx-background-color: white; -fx-text-fill: black");
        }
    }

    private void randomizeWord() {
        int rng = (int)(Math.random() * listOfWords.size());
        winningWord = listOfWords.get(rng);
        rowListCounter = 0;
    }
}
