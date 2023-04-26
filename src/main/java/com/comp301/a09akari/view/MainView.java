package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainView implements FXComponent, ModelObserver {
  private final FXComponent PuzzleView;
  private final FXComponent ControlView;
  private final FXComponent MessageView;
  private final Scene scene;

  public MainView(Model model, ClassicMvcController controller) {
    PuzzleView = new PuzzleView(model, controller);
    ControlView = new ControlView(model, controller);
    MessageView = new MessageView(model, controller);
    scene = new Scene(render(), 600, 600);
    scene.getStylesheets().add("main.css");
    model.addObserver(this);
  }

  public Scene getScene() {
    return scene;
  }

  @Override
  public void update(Model model) {
    scene.setRoot(render());
  }

  @Override
  public Parent render() {
    BorderPane pane = new BorderPane();
    pane.setCenter(PuzzleView.render());
    pane.setBottom(ControlView.render());
    pane.setTop(MessageView.render());
    return pane;
  }
}
