package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;

public class Controller implements ClassicMvcController {
  private final Model model;

  public Controller(Model model) {
    if (model != null) {
      this.model = model;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void clickNextPuzzle() {
    if (model.getActivePuzzleIndex() + 1 < model.getPuzzleLibrarySize()) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
      model.resetPuzzle();
    }
  }

  @Override
  public void clickPrevPuzzle() {
    if (model.getActivePuzzleIndex() - 1 >= 0) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
      model.resetPuzzle();
    }
  }

  @Override
  public void clickRandPuzzle() {
    int indexInt = 0;
    double index = model.getPuzzleLibrarySize() * Math.random();
    indexInt = (int) index;
    while (indexInt == model.getActivePuzzleIndex()) {
      index = model.getPuzzleLibrarySize() * Math.random();
      indexInt = (int) index;
    }
    model.setActivePuzzleIndex(indexInt);
    model.resetPuzzle();
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (model.isLamp(r, c)) {
      model.removeLamp(r, c);
    } else {
      model.addLamp(r, c);
    }
  }
}
