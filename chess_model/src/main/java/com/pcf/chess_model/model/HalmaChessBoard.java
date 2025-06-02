package com.pcf.chess_model.model;

import android.graphics.Point;

import com.pcf.base_model.model.BaseChessBoard;
import com.pcf.base_model.model.BaseChessPiece;
import com.pcf.base_model.utils.Constants;

import java.util.Set;

public class HalmaChessBoard extends BaseChessBoard {
    private int boardSize;
    //todo 可以用 Map<Point, BaseChessPiece>
    private BaseChessPiece[][] grid = new BaseChessPiece[Constants.HALMA_CHESS_BOARD_WIDTH][Constants.HALMA_CHESS_BOARD_LENGTH];
    private Set<Point> startingPositions;
    private Set<Point> targetPositions;
    //moveHistory

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public BaseChessPiece[][] getGrid() {
        return grid;
    }

    public void setGrid(BaseChessPiece[][] grid) {
        this.grid = grid;
    }

    public Set<Point> getStartingPositions() {
        return startingPositions;
    }

    public void setStartingPositions(Set<Point> startingPositions) {
        this.startingPositions = startingPositions;
    }

    public Set<Point> getTargetPositions() {
        return targetPositions;
    }

    public void setTargetPositions(Set<Point> targetPositions) {
        this.targetPositions = targetPositions;
    }
}
