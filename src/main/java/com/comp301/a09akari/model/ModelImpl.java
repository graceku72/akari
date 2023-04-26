package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private final PuzzleLibrary library;
  private int index = 0;
  private boolean[][] lamps;
  private final List<ModelObserver> observers;
  private Puzzle activePuzzle;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    }
    this.library = library;
    activePuzzle = library.getPuzzle(index);
    lamps = new boolean[activePuzzle.getHeight()][activePuzzle.getWidth()];
    observers = new ArrayList<ModelObserver>();
  }

  @Override
  public void addLamp(int r, int c) {
    if (r < 0 || r >= activePuzzle.getHeight() || c < 0 || c >= activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lamps[r][c] = true;
    notifyObservers();
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r < 0 || r >= activePuzzle.getHeight() || c < 0 || c >= activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lamps[r][c] = false;
    notifyObservers();
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r < 0 || r >= activePuzzle.getHeight() || c < 0 || c >= activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    for (int i = c; i >= 0; i--) {
      if (activePuzzle.getCellType(r, i) == CellType.WALL
          || activePuzzle.getCellType(r, i) == CellType.CLUE) {
        break;
      } else if (activePuzzle.getCellType(r, i) == CellType.CORRIDOR) {
        if (isLamp(r, i)) {
          return true;
        }
      }
    }
    for (int i = c; i < activePuzzle.getWidth(); i++) {
      if (activePuzzle.getCellType(r, i) == CellType.WALL
          || activePuzzle.getCellType(r, i) == CellType.CLUE) {
        break;
      } else if (activePuzzle.getCellType(r, i) == CellType.CORRIDOR) {
        if (isLamp(r, i)) {
          return true;
        }
      }
    }
    for (int i = r; i >= 0; i--) {
      if (activePuzzle.getCellType(i, c) == CellType.WALL
          || activePuzzle.getCellType(i, c) == CellType.CLUE) {
        break;
      } else if (activePuzzle.getCellType(i, c) == CellType.CORRIDOR) {
        if (isLamp(i, c)) {
          return true;
        }
      }
    }
    for (int i = r; i < activePuzzle.getHeight(); i++) {
      if (activePuzzle.getCellType(i, c) == CellType.WALL
          || activePuzzle.getCellType(i, c) == CellType.CLUE) {
        break;
      } else if (activePuzzle.getCellType(i, c) == CellType.CORRIDOR) {
        if (isLamp(i, c)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r < 0 || r >= activePuzzle.getHeight() || c < 0 || c >= activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return lamps[r][c];
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r < 0 || r >= activePuzzle.getHeight() || c < 0 || c >= activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (!isLamp(r, c)) {
      throw new IllegalArgumentException();
    }
    for (int i = c; i >= 0; i--) {
      if (activePuzzle.getCellType(r, i) == CellType.WALL
          || activePuzzle.getCellType(r, i) == CellType.CLUE) {
        break;
      } else if (i != c && isLamp(r, i)) {
        return true;
      }
    }
    for (int i = c; i < activePuzzle.getWidth(); i++) {
      if (activePuzzle.getCellType(r, i) == CellType.WALL
          || activePuzzle.getCellType(r, i) == CellType.CLUE) {
        break;
      } else if (i != c && isLamp(r, i)) {
        return true;
      }
    }
    for (int i = r; i >= 0; i--) {
      if (activePuzzle.getCellType(i, c) == CellType.WALL
          || activePuzzle.getCellType(i, c) == CellType.CLUE) {
        break;
      } else if (i != r && isLamp(i, c)) {
        return true;
      }
    }
    for (int i = r; i < activePuzzle.getHeight(); i++) {
      if (activePuzzle.getCellType(i, c) == CellType.WALL
          || activePuzzle.getCellType(i, c) == CellType.CLUE) {
        break;
      } else if (i != r && isLamp(i, c)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return activePuzzle;
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
    activePuzzle = library.getPuzzle(index);
    lamps = new boolean[activePuzzle.getHeight()][activePuzzle.getWidth()];
    notifyObservers();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return library.size();
  }

  @Override
  public void resetPuzzle() {
    lamps = new boolean[activePuzzle.getHeight()][activePuzzle.getWidth()];
    notifyObservers();
  }

  @Override
  public boolean isSolved() {
    int corridors = 0;
    int corridorsLit = 0;
    int clues = 0;
    int cluesSatisfied = 0;
    int illegalLamps = 0;
    for (int i = 0; i < activePuzzle.getHeight(); i++) {
      for (int j = 0; j < activePuzzle.getWidth(); j++) {
        if (activePuzzle.getCellType(i, j) == CellType.CORRIDOR) {
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
        if (activePuzzle.getCellType(i, j) == CellType.CLUE) {
          clues++;
          if (isClueSatisfied(i, j)) {
            cluesSatisfied++;
          }
        }
      }
    }
      return corridors == corridorsLit && clues == cluesSatisfied && illegalLamps == 0;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r < 0 || r >= activePuzzle.getHeight() || c < 0 || c >= activePuzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (activePuzzle.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    int count = 0;
    if (c - 1 >= 0 && activePuzzle.getCellType(r, c - 1) == CellType.CORRIDOR && isLamp(r, c - 1)) {
      count++;
    }
    if (c + 1 < activePuzzle.getWidth()
        && activePuzzle.getCellType(r, c + 1) == CellType.CORRIDOR
        && isLamp(r, c + 1)) {
      count++;
    }
    if (r - 1 >= 0 && activePuzzle.getCellType(r - 1, c) == CellType.CORRIDOR && isLamp(r - 1, c)) {
      count++;
    }
    if (r + 1 < activePuzzle.getHeight()
        && activePuzzle.getCellType(r + 1, c) == CellType.CORRIDOR
        && isLamp(r + 1, c)) {
      count++;
    }
      return count == activePuzzle.getClue(r, c);
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
