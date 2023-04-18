package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
    int[][] board;
    public PuzzleImpl(int[][] board) {
        this.board = board;
    }

    @Override
    public int getWidth() {
        return board[0].length;
    }

    @Override
    public int getHeight() {
        return board.length;
    }

    @Override
    public CellType getCellType(int r, int c) {
        if (r < 0 || r >= getWidth() || c < 0 || c >= getHeight()) {
            throw new IndexOutOfBoundsException();
        }
        if (board[r][c] == 0) {
            return CellType.CLUE;
        }
        if (board[r][c] == 1) {
            return CellType.CLUE;
        }
        if (board[r][c] == 2) {
            return CellType.CLUE;
        }
        if (board[r][c] == 3) {
            return CellType.CLUE;
        }
        if (board[r][c] == 4) {
            return CellType.CLUE;
        }
        if (board[r][c] == 5) {
            return CellType.WALL;
        }
        return CellType.CORRIDOR;
    }

    @Override
    public int getClue(int r, int c) {
        if (r < 0 || r >= getWidth() || c < 0 || c >= getHeight()) {
            throw new IndexOutOfBoundsException();
        }
        if (getCellType(r, c) != CellType.CLUE) {
            throw new IllegalArgumentException();
        }
        return board[r][c];
    }
}
