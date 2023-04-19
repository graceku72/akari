package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelImpl implements Model {
    // unsure abt where to notify observers (mentioned in readme)
    private PuzzleLibrary library;
    private int index;
    private int[][] lamps;
    private List<ModelObserver> observers;
    public ModelImpl(PuzzleLibrary library) {
        this.library = library;
        index = 0;
        lamps = new int[getActivePuzzle().getWidth()][getActivePuzzle().getHeight()];
    }

    @Override
    public void addLamp(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getWidth() || c < 0 || c >= getActivePuzzle().getHeight()) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        if (lamps[r][c] != 1) {
            lamps[r][c] = 1;
            int i = 0;
            while (i < getActivePuzzle().getHeight()) {
                if (i != c) {
                    lamps[r][i] = 2;
                }
                i++;
            }
            i = 0;
            while (i < getActivePuzzle().getWidth()) {
                if (i != r) {
                    lamps[i][c] = 2;
                }
                i++;
            }
        }
    }

    @Override
    public void removeLamp(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getWidth() || c < 0 || c >= getActivePuzzle().getHeight()) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        if (lamps.containsKey(r) && lamps.get(r).contains(c)) {
            if (lamps.get(r).size() == 1) {
                lamps.remove(r);
            } else {
                int temp = lamps.get(r).indexOf(c);
                lamps.get(r).remove(temp);
            }
        }
    }

    @Override
    public boolean isLit(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getWidth() || c < 0 || c >= getActivePuzzle().getHeight()) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        return false;
    }

    @Override
    public boolean isLamp(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getWidth() || c < 0 || c >= getActivePuzzle().getHeight()) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        return false;
    }

    @Override
    public boolean isLampIllegal(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getWidth() || c < 0 || c >= getActivePuzzle().getHeight()) {
            throw new IndexOutOfBoundsException();
        }
        return false;
    }

    @Override
    public Puzzle getActivePuzzle() {
        return library.getPuzzle(index);
    }

    @Override
    public int getActivePuzzleIndex() {
        return index;
    }

    @Override
    public void setActivePuzzleIndex(int index) {
        if (index < 0 || index >= library.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.index = index;
    }

    @Override
    public int getPuzzleLibrarySize() {
        return library.size();
    }

    @Override
    public void resetPuzzle() {
        // how do u remove lamps
    }

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public boolean isClueSatisfied(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getWidth() || c < 0 || c >= getActivePuzzle().getHeight()) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r, c) != CellType.CLUE) {
            throw new IllegalArgumentException();
        }
        return false;
    }

    @Override
    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }
}
