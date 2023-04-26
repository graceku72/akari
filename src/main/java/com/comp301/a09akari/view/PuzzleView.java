package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PuzzleView implements FXComponent, ModelObserver {
  private final Model model;
  private final ClassicMvcController controller;

  public PuzzleView(Model model, ClassicMvcController controller) {
    this.model = model;
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane grid = new GridPane();
    grid.getChildren().clear();
    grid.setHgap(5);
    grid.setVgap(5);
    grid.setAlignment(Pos.CENTER);

    for (int r = 0; r < model.getActivePuzzle().getHeight(); r++) {
      for (int c = 0; c < model.getActivePuzzle().getWidth(); c++) {
        StackPane cell = new StackPane();
        Rectangle pane = new Rectangle();
        pane.setWidth(35);
        pane.setHeight(35);
        pane.setFill(Color.WHITE);
        pane.setStroke(Color.BLACK);
        cell.getChildren().add(pane);
        switch (model.getActivePuzzle().getCellType(r, c)) {
          case CORRIDOR:
            int finalR = r;
            int finalC = c;
            cell.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                  @Override
                  public void handle(MouseEvent mouseEvent) {
                    controller.clickCell(finalR, finalC);
                  }
                });
            if (model.isLamp(r, c)) {
              Image image = new Image("light-bulb.png");
              ImageView lamp = new ImageView();
              lamp.setImage(image);
              lamp.setFitWidth(25);
              lamp.setPreserveRatio(true);
              cell.getChildren().add(lamp);
            }
            if (model.isLit(r, c)) {
              pane.setFill(Color.YELLOW);
              if (model.isLamp(r, c) && model.isLampIllegal(r, c)) {
                pane.setFill(Color.RED);
              }
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
  public void update(Model model) {}
}
