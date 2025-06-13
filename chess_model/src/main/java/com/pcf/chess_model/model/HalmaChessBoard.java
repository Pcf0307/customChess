package com.pcf.chess_model.model;

import android.graphics.Point;

import com.pcf.base_model.model.BaseChessBoard;

import java.util.ArrayList;
import java.util.List;

public class HalmaChessBoard extends BaseChessBoard {
    private int boardRows;
    private int boardCols;
    private List<HalmaChessPiece> blackChessPieces = new ArrayList<>();
    private List<HalmaChessPiece> whiteChessPieces = new ArrayList<>();
    private List<Point> whiteStartingPositions = new ArrayList<>();
    private List<Point> blackStartingPositions = new ArrayList<>();
    private Point[][] allPoints;

    //moveHistory
    public int getBoardRows() {
        return boardRows;
    }

    public void setBoardRows(int boardRows) {
        this.boardRows = boardRows;
    }

    public int getBoardCols() {
        return boardCols;
    }

    public void setBoardCols(int boardCols) {
        this.boardCols = boardCols;
    }

    public List<Point> getWhiteStartingPositions() {
        return whiteStartingPositions;
    }

    public void setWhiteStartingPositions(List<Point> whiteStartingPositions) {
        this.whiteStartingPositions = whiteStartingPositions;
    }

    public List<Point> getBlackStartingPositions() {
        return blackStartingPositions;
    }

    public void setBlackStartingPositions(List<Point> blackStartingPositions) {
        this.blackStartingPositions = blackStartingPositions;
    }

    public List<HalmaChessPiece> getBlackChessPieces() {
        return blackChessPieces;
    }

    public void setBlackChessPieces(List<HalmaChessPiece> blackChessPieces) {
        this.blackChessPieces = blackChessPieces;
    }

    public List<HalmaChessPiece> getWhiteChessPieces() {
        return whiteChessPieces;
    }

    public void setWhiteChessPieces(List<HalmaChessPiece> whiteChessPieces) {
        this.whiteChessPieces = whiteChessPieces;
    }

    public Point[][] getAllPoints() {
        return allPoints;
    }

    public void setAllPoints(Point[][] allPoints) {
        this.allPoints = allPoints;
    }
}
