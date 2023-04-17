package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
    private PuzzleLibrary library;
    private int index;
    // lamp locations: hash map w rows & cols?
    //  algorithm for determining whether the active puzzle has been solved: arraylist?
    private List<ModelObserver> observers;
    private Puzzle curr;
    public ModelImpl(PuzzleLibrary library) {
        this.library = library;
        index = 0;
        curr = this.library.getPuzzle(0);
    }

    @Override
    public void addLamp(int r, int c) {
        if (r < 0 || r >= curr.getWidth() || c < 0 || c >= curr.getHeight()) {
            throw new IndexOutOfBoundsException();
        }
        if (curr.getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        // how do u add a lamp...
    }

    @Override
    public void removeLamp(int r, int c) {
        if (r < 0 || r >= curr.getWidth() || c < 0 || c >= curr.getHeight()) {
            throw new IndexOutOfBoundsException();
        }
        if (curr.getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        // how do u remove a lamp...
    }

    @Override
    public boolean isLit(int r, int c) {
        return false;
    }

    @Override
    public boolean isLamp(int r, int c) {
        return false;
    }

    @Override
    public boolean isLampIllegal(int r, int c) {
        return false;
    }

    @Override
    public Puzzle getActivePuzzle() {
        return null;
    }

    @Override
    public int getActivePuzzleIndex() {
        return 0;
    }

    @Override
    public void setActivePuzzleIndex(int index) {

    }

    @Override
    public int getPuzzleLibrarySize() {
        return 0;
    }

    @Override
    public void resetPuzzle() {

    }

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public boolean isClueSatisfied(int r, int c) {
        return false;
    }

    @Override
    public void addObserver(ModelObserver observer) {

    }

    @Override
    public void removeObserver(ModelObserver observer) {

    }
}
