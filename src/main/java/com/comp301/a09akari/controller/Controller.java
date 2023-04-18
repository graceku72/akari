package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;

public class Controller implements ClassicMvcController {
    private Model model;
    public Controller(Model model) {
        this.model = model;
    }

    @Override
    public void clickNextPuzzle() {
        model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
        // what else do i need to do
    }

    @Override
    public void clickPrevPuzzle() {
        model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
        // what else do i need to do
    }

    @Override
    public void clickRandPuzzle() {
        // import random class or smth? look up?
        // use rand on int to choose random index?
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
        // not sure what to do here ?
    }
}
