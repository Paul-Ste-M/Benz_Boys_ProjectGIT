package com.calmly;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SongEditorController {

    @FXML
    private Label addButton;

    @FXML
    private Button addButton2;

    @FXML
    private ContextMenu addMenu;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label currentProjectText;

    @FXML
    private void clickedPlusButton(MouseEvent event) {
        addMenu.show(addButton, addButton.getLayoutX(), addButton.getLayoutY());
    }
//TODO
    @FXML
    private void clickedPlusButton2(ActionEvent event) {
        addMenu.show(anchorPane, addButton2.getLayoutX(), addButton2.getLayoutY());
    }

    @FXML
    private void showAddOptions(ActionEvent event) {
        addMenu.show(addButton, addButton.getLayoutX(), addButton.getLayoutY());
    }

}
