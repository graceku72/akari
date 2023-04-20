package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.Controller;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;

import static com.comp301.a09akari.SamplePuzzles.*;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    PuzzleLibrary library = new PuzzleLibraryImpl();
    Puzzle puzzle_01 = new PuzzleImpl(PUZZLE_01);
    library.addPuzzle(puzzle_01);
    Puzzle puzzle_02 = new PuzzleImpl(PUZZLE_02);
    library.addPuzzle(puzzle_02);
    Puzzle puzzle_03 = new PuzzleImpl(PUZZLE_03);
    library.addPuzzle(puzzle_03);
    Puzzle puzzle_04 = new PuzzleImpl(PUZZLE_04);
    library.addPuzzle(puzzle_04);
    Puzzle puzzle_05 = new PuzzleImpl(PUZZLE_05);
    library.addPuzzle(puzzle_05);

    Model model = new ModelImpl(library);
    ClassicMvcController controller = new Controller(model);

    MainView view = new MainView(model, controller);

    stage.setScene(view.getScene());
    stage.setTitle("Akari");
    stage.show();
  }
}
