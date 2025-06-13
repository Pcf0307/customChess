package com.pcf.chess_model.engine;

import android.graphics.Point;

import com.pcf.chess_model.factory.HalmaChessFactory;
import com.pcf.chess_model.model.HalmaChessBoard;
import com.pcf.chess_model.model.HalmaChessPiece;
import com.pcf.chess_model.utils.HalmaMoveCalculator;

import java.util.ArrayList;
import java.util.List;

public class HalmaGameEngine{
    private final HalmaChessBoard board;
    private HalmaChessPiece selectPiece;
    private List<Point> highlightPath = new ArrayList<>();
    HalmaChessFactory halmaChessFactory = new HalmaChessFactory();
    public static final int HALMA_CHESS_BOARD_COLS = 9;
    public static final int HALMA_CHESS_BOARD_ROWS = 10;
    private static final int START_ZONE_SIZE = 4;

    public HalmaGameEngine() {
        board = halmaChessFactory.createChessBoard();
        board.setBoardRows(HALMA_CHESS_BOARD_ROWS);
        board.setBoardCols(HALMA_CHESS_BOARD_COLS);
    }
    public void initPiece(Point[][] points){
        board.setAllPoints(points);
        for (int j = 0; j < START_ZONE_SIZE; j++) {
            for (int i = 0; i < START_ZONE_SIZE; i++) {
                board.getWhiteStartingPositions().add(points[i][j]);
                HalmaChessPiece chessPiece = halmaChessFactory.createChessPiece();
                chessPiece.setPosition(points[i][j]);
                board.getWhiteChessPieces().add(chessPiece);
            }
        }
        for (int j = board.getBoardRows() - 1; j > board.getBoardRows() - START_ZONE_SIZE - 1; j--) {
            for (int i = board.getBoardCols() - 1; i > board.getBoardCols() - START_ZONE_SIZE - 1; i--) {
                board.getBlackStartingPositions().add(points[i][j]);
                HalmaChessPiece chessPiece = halmaChessFactory.createChessPiece();
                chessPiece.setPosition(points[i][j]);
                board.getBlackChessPieces().add(chessPiece);
            }
        }
    }
    public void handlePieceClick(HalmaChessPiece piece){
        setSelectPiece(piece);
        setHighlightPath(HalmaMoveCalculator.findValidMoves(piece,board));
    }

    public void handleBoardClick(Point point){
        if (selectPiece != null && highlightPath.contains(point)){
            selectPiece.setPosition(point);
            selectPiece = null;
            highlightPath.clear();
        }
    }

    public HalmaChessPiece getSelectPiece() {
        return selectPiece;
    }

    public List<Point> getHighlightPath() {
        return highlightPath;
    }

    public void setSelectPiece(HalmaChessPiece selectPiece) {
        this.selectPiece = selectPiece;
    }

    public void setHighlightPath(List<Point> highlightPath) {
        this.highlightPath = highlightPath;
    }

    public HalmaChessBoard getBoard() {
        return board;
    }
}
