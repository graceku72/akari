package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelImpl implements Model {
    private PuzzleLibrary library;
    private int index;
    private int[][] lamps;
    private List<ModelObserver> observers;
    public ModelImpl(PuzzleLibrary library) {
        this.library = library;
        index = 0;
        lamps = new int[this.library.getPuzzle(index).getWidth()][this.library.getPuzzle(index).getHeight()];
        // set all vals in lamps to 0 ?
    }

    @Override
    public void addLamp(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getHeight() || c < 0 || c >= getActivePuzzle().getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        if (lamps[r][c] != 1) {
            lamps[r][c] = 1;
            for (int i = c; i >= 0; i--) {
                if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                    break;
                }
                if (i != c) {
                    lamps[r][i] = 2;
                }
            }
            for (int i = c; i < getActivePuzzle().getWidth(); i++) {
                if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                    break;
                }
                if (i != c) {
                    lamps[r][i] = 2;
                }            }
            for (int i = r; i >= 0; i--) {
                if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                    break;
                }
                if (i != r) {
                    lamps[i][c] = 2;
                }
            }
            for (int i = r; i < getActivePuzzle().getHeight(); i++) {
                if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                    break;
                }
                if (i != r) {
                    lamps[i][c] = 2;
                }
            }
        }
    }

    @Override
    public void removeLamp(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getHeight() || c < 0 || c >= getActivePuzzle().getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        if (lamps[r][c] == 1) {
            lamps[r][c] = 0;
            for (int i = c; i >= 0; i--) {
                if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                    break;
                }
                if (i != c) {
                    lamps[r][i] = 0;
                }
            }
            for (int i = c; i < getActivePuzzle().getWidth(); i++) {
                if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                    break;
                }
                if (i != c) {
                    lamps[r][i] = 0;
                }
            }
            for (int i = r; i >= 0; i--) {
                if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                    break;
                }
                if (i != r) {
                    lamps[i][c] = 0;
                }
            }
            for (int i = r; i < getActivePuzzle().getHeight(); i++) {
                if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                    break;
                }
                if (i != r) {
                    lamps[i][c] = 0;
                }
            }
        }
    }

    @Override
    public boolean isLit(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getHeight() || c < 0 || c >= getActivePuzzle().getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
            throw new IllegalArgumentException();
        }
        if (lamps[r][c] == 1 || lamps[r][c] == 2) {
            return true;
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
        if (lamps[r][c] == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isLampIllegal(int r, int c) {
        if (r < 0 || r >= getActivePuzzle().getHeight() || c < 0 || c >= getActivePuzzle().getWidth()) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = c; i >= 0; i--) {
            if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                break;
            }
            if (i != c && lamps[r][i] == 1) {
                return true;
            }
        }
        for (int i = c; i < getActivePuzzle().getWidth(); i++) {
            if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                break;
            }
            if (i != c && lamps[r][i] == 1) {
                return true;
            }
        }
        for (int i = r; i >= 0; i--) {
            if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                break;
            }
            if (i != r && lamps[i][c] == 1) {
                return true;
            }
        }
        for (int i = r; i < getActivePuzzle().getHeight(); i++) {
            if (getActivePuzzle().getCellType(r, i) == CellType.WALL || getActivePuzzle().getCellType(r, i) == CellType.CLUE) {
                break;
            }
            if (i != r && lamps[i][c] == 1) {
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
        lamps = new int[library.getPuzzle(index).getWidth()][library.getPuzzle(index).getHeight()];
        // r u supposed to change dimensions for lamps here?
    }

    @Override
    public int getPuzzleLibrarySize() {
        return library.size();
    }

    @Override
    public void resetPuzzle() {
        for (int i = 0; i < getActivePuzzle().getHeight(); i++) {
            for (int j = 0; j < getActivePuzzle().getWidth(); j++) {
                lamps[i][j] = 0;
            }
        }
    }

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public boolean isClueSatisfied(int r, int c) {
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
        observers.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }
}
