package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.Controller;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControlView implements FXComponent, ModelObserver {
    private Model model;
    private ClassicMvcController controller;
    private Button nextButton;
    private Button prevButton;
    private Button randButton;
    private Button resetButton;
    public ControlView(Model model, ClassicMvcController controller) {
        this.model = model;
        this.controller = controller;
    }

    @Override
    public Parent render() {
        HBox pane = new HBox();
        pane.getChildren().clear();
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);

        if (nextButton == null) {
            nextButton = new Button("Next");
            nextButton.setOnAction((ActionEvent event) -> controller.clickNextPuzzle());
        }
        pane.getChildren().add(nextButton);

        if (prevButton == null) {
            prevButton = new Button("Previous");
            prevButton.setOnAction((ActionEvent event) -> controller.clickPrevPuzzle());
        }
        pane.getChildren().add(prevButton);

        if (randButton == null) {
            randButton = new Button("Select Random");
            randButton.setOnAction((ActionEvent event) -> controller.clickRandPuzzle());
        }
        pane.getChildren().add(randButton);

        if (resetButton == null) {
            resetButton = new Button("Reset");
            resetButton.setOnAction((ActionEvent event) -> controller.clickResetPuzzle());
        }
        pane.getChildren().add(resetButton);

        return pane;
    }

    @Override
    public void update(Model model) {
    }
}
