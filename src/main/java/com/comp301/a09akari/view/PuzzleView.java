package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.Controller;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PuzzleView implements FXComponent, ModelObserver {
    private Model model;
    private ClassicMvcController controller;
    public PuzzleView(Model model, ClassicMvcController controller) {
        this.model = model;
        this.controller = controller;
    }

    @Override
    public Parent render() {
        GridPane grid = new GridPane();
        grid.getChildren().clear();
        model.getActivePuzzle();
        // not sure what to do to make grid
        //pane.getChildren().add();
        return grid;
    }

    @Override
    public void update(Model model) {
        // dont need this method for this class
    }
}
