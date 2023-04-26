package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MessageView implements FXComponent, ModelObserver {
  private final Model model;
  private final ClassicMvcController controller;

  public MessageView(Model model, ClassicMvcController controller) {
    this.model = model;
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox pane = new VBox();
    pane.getChildren().clear();
    pane.setAlignment(Pos.CENTER);

    Label puzzleCount = new Label("Puzzles in Library: " + model.getPuzzleLibrarySize());
    pane.getChildren().add(puzzleCount);

    Label curr = new Label("Current Puzzle: " + (model.getActivePuzzleIndex() + 1));
    pane.getChildren().add(curr);

    if (model.isSolved()) {
      Label success = new Label("Solved!");
      pane.getChildren().add(success);
    } else {
      Label notSuccess = new Label();
      pane.getChildren().add(notSuccess);
    }

    return pane;
  }

  @Override
  public void update(Model model) {}
}
