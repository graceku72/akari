package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PuzzleView implements FXComponent {
    private Controller controller;
    public PuzzleView(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Parent render() {
        GridPane gridPane = new GridPane();
        gridPane.addRow(10);
        gridPane.addColumn(10);
        Parent parent = gridPane;
        return parent;
    }
}
