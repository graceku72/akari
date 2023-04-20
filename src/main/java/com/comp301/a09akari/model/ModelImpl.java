package com.comp301.a09akari.model;

import java.util.List;

public class ModelImpl implements Model {
    private PuzzleLibrary library;
    private int index;
    private boolean[][] lamps;
    private List<ModelObserver> observers;
    public ModelImpl(PuzzleLibrary library) {
        this.library = library;
        index = 0;
        lamps = new boolean[this.library.getPuzzle(index).getWidth()][this.library.getPuzzle(index).getHeight()];
    }

    @Override
    public void addLamp(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getHeight() || c < 0 || c >= getActivePuzzle().getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        lamps[r][c] = true;
        //notifyObservers();
    }

    @Override
    public void removeLamp(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getHeight() || c < 0 || c >= getActivePuzzle().getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        lamps[r][c] = false;
        //notifyObservers();
    }

    @Override
    public boolean isLit(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getHeight() || c < 0 || c >= getActivePuzzle().getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        for (int i = c; i >= 0; i--) {
            if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                break;
            } else if (getActivePuzzle().getCellType(r, i) == CellType.CORRIDOR) {
                if (isLamp(r, i)) {
                    return true;
                }
            }
        }
        for (int i = c; i < getActivePuzzle().getWidth(); i++) {
            if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                break;
            } else if (getActivePuzzle().getCellType(r, i) == CellType.CORRIDOR) {
                if (isLamp(r, i)) {
                    return true;
                }
            }
        }
        for (int i = r; i >= 0; i--) {
            if (getActivePuzzle().getCellType(i, c) == CellType.WALL || getActivePuzzle().getCellType(i, c) == CellType.CLUE) {
                break;
            } else if (getActivePuzzle().getCellType(i, c) == CellType.CORRIDOR) {
                if (isLamp(i, c)) {
                    return true;
                }
            }
        }
        for (int i = r; i < getActivePuzzle().getHeight(); i++) {
            if (getActivePuzzle().getCellType(i, c) == CellType.WALL || getActivePuzzle().getCellType(i, c) == CellType.CLUE) {
                break;
            } else if (getActivePuzzle().getCellType(i, c) == CellType.CORRIDOR) {
                if (isLamp(i, c)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isLamp(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getHeight() || c < 0 || c >= getActivePuzzle().getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        return lamps[r][c];
    }

    @Override
    public boolean isLampIllegal(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getHeight() || c < 0 || c >= getActivePuzzle().getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (!isLamp(r, c)) {
            throw new IllegalArgumentException();
        }
        for (int i = c; i >= 0; i--) {
            if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                break;
            } else if (isLamp(r, i)) {
                return true;
            }
        }
        for (int i = c; i < getActivePuzzle().getWidth(); i++) {
            if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                break;
            } else if (isLamp(r, i)) {
                return true;
            }
        }
        for (int i = r; i >= 0; i--) {
            if (getActivePuzzle().getCellType(i, c) == CellType.WALL || getActivePuzzle().getCellType(i, c) == CellType.CLUE) {
                break;
            } else if (isLamp(i, c)) {
                return true;
            }
        }
        for (int i = r; i < getActivePuzzle().getHeight(); i++) {
            if (getActivePuzzle().getCellType(i, c) == CellType.WALL || getActivePuzzle().getCellType(i, c) == CellType.CLUE) {
                break;
            } else if (isLamp(i, c)) {
                return true;
            }
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
        for (int i = 0; i < getActivePuzzle().getHeight(); i++) {
            for (int j = 0; j < getActivePuzzle().getWidth(); j++) {
                lamps[i][j] = false;
            }
        }
        //notifyObservers();
    }

    @Override
    public boolean isSolved() {
        // get a count on # corridors to see that theyre all lit & make int var
        // get a count on # clues to see that theyre all solved & make int var
//        for (int i = 0; i < getActivePuzzle().getHeight(); i++) {
//            for (int j = 0; j < getActivePuzzle().getWidth(); j++) {
//                if ((lamps[i][j] == 1 || lamps[i][j] == 2) && !isLampIllegal(i, j)) {
//
//                }
//            }
//        }
        //notifyObservers();
        return false;
    }

    @Override
    public boolean isClueSatisfied(int r, int c) {
        // start on
        if (r < 0 || r >= getActivePuzzle().getHeight() || c < 0 || c >= getActivePuzzle().getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        return false;
    }

    @Override
    public void addObserver(ModelObserver observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        if (observer != null) {
            observers.remove(observer);
        }
    }

    private void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.update(this);
        }
    }
}
