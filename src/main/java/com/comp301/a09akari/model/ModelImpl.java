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
            } else if (i != c && isLamp(r, i)) {
                return true;
            }
        }
        for (int i = c; i < getActivePuzzle().getWidth(); i++) {
            if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                break;
            } else if (i != c && isLamp(r, i)) {
                return true;
            }
        }
        for (int i = r; i >= 0; i--) {
            if (getActivePuzzle().getCellType(i, c) == CellType.WALL || getActivePuzzle().getCellType(i, c) == CellType.CLUE) {
                break;
            } else if (i != r && isLamp(i, c)) {
                return true;
            }
        }
        for (int i = r; i < getActivePuzzle().getHeight(); i++) {
            if (getActivePuzzle().getCellType(i, c) == CellType.WALL || getActivePuzzle().getCellType(i, c) == CellType.CLUE) {
                break;
            } else if (i != r && isLamp(i, c)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Puzzle getActivePuzzle() {
        notifyObservers();
        return library.getPuzzle(index);
    }

    @Override
    public int getActivePuzzleIndex() {
        notifyObservers();
        return index;
    }

    @Override
    public void setActivePuzzleIndex(int index) {
        notifyObservers();
        if (index < 0 || index >= library.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.index = index;
    }

    @Override
    public int getPuzzleLibrarySize() {
        notifyObservers();
        return library.size();
    }

    @Override
    public void resetPuzzle() {
        for (int i = 0; i < lamps[0].length; i++) {
            for (int j = 0; j < lamps.length; j++) {
                lamps[i][j] = false;
            }
        }
    }

    @Override
    public boolean isSolved() {
        int corridors = 0;
        int corridorsLit = 0;
        int clues = 0;
        int cluesSatisfied = 0;
        int illegalLamps = 0;
        for (int i = 0; i < getActivePuzzle().getHeight(); i++) {
            for (int j = 0; j < getActivePuzzle().getWidth(); j++) {
                if (getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR) {
                    corridors++;
                    if (isLamp(i, j)) {
                        if (isLampIllegal(i, j)) {
                            illegalLamps++;
                        }
                    }
                    if (isLit(i, j)) {
                        corridorsLit++;
                    }
                }
                if (getActivePuzzle().getCellType(i, j) == CellType.CLUE) {
                    clues++;
                    if (isClueSatisfied(i, j)) {
                        cluesSatisfied++;
                    }
                }
            }
        }
        if (corridors == corridorsLit && clues == cluesSatisfied && illegalLamps == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isClueSatisfied(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getHeight() || c < 0 || c >= getActivePuzzle().getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r, c) != CellType.CLUE) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        if (c - 1 >= 0 && isLamp(r, c - 1)) {
            count++;
        }
        if (c + 1 < getActivePuzzle().getWidth() && isLamp(r, c + 1)) {
            count++;
        }
        if (r - 1 >= 0 && isLamp(r - 1, c)) {
            count++;
        }
        if (r + 1 < getActivePuzzle().getHeight() && isLamp(r + 1, c)) {
            count++;
        }
        if (count == getActivePuzzle().getClue(r, c)) {
            return true;
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
        // where in this class should i use this method
    }
}
