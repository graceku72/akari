package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.Controller;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import java.awt.*;
import javafx.scene.paint.Color;

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
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setAlignment(Pos.CENTER);

    // Board sizes will differ across puzzles, and computer screen sizes will different across users
    // – the entire board (and all other components) should be visible, without having to resize the
    // application window between puzzle changes.
    // See the “ULA Grading Procedures” for more information

        for (int r = 0; r < model.getActivePuzzle().getHeight(); r++) {
            for (int c = 0; c < model.getActivePuzzle().getWidth(); c++) {
                StackPane cell = new StackPane();
                Rectangle pane = new Rectangle();
                pane.setWidth(50);
                pane.setHeight(50);
                pane.setFill(Color.WHITE);
                pane.setStroke(Color.BLACK);
                cell.getChildren().add(pane);
                switch (model.getActivePuzzle().getCellType(r, c)) {
                    case CORRIDOR:
                        int finalR = r;
                        int finalC = c;
                        cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                controller.clickCell(finalR, finalC);
                            }
                        });
                        if (model.isLamp(r, c)) {
                            Image image = new Image("light-bulb.png");
                            ImageView lamp = new ImageView();
                            lamp.setImage(image);
                            lamp.setFitWidth(40);
                            lamp.setPreserveRatio(true);
                            cell.getChildren().add(lamp);
                        }
                        if (model.isLit(r, c)) {
                            pane.setFill(Color.YELLOW);
                        }
                        break;
                    case CLUE:
                        pane.setFill(Color.BLACK);
                        Label clueNum = new Label("" + model.getActivePuzzle().getClue(r, c));
                        clueNum.setTextFill(Color.WHITE);
                        cell.getChildren().add(clueNum);
                        if (model.isClueSatisfied(r, c)) {
                            pane.setFill(Color.GREEN);
                        }
                        break;
                    case WALL:
                        pane.setFill(Color.BLACK);
                        break;
                }
                grid.add(cell, c, r);
            }
        }

        return grid;
    }

    @Override
    public void update(Model model) {
    }
}
