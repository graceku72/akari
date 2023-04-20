package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;

public class Controller implements ClassicMvcController {
    private Model model;
    public Controller(Model model) {
        if (model != null) {
            this.model = model;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void clickNextPuzzle() {
        model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
    }

    @Override
    public void clickPrevPuzzle() {
        model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    }

    @Override
    public void clickRandPuzzle() {
        // import random class or smth? look up?
        // use rand on int to choose random index?
        // since u can impl controller as seen fit, is this part even needed?
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
