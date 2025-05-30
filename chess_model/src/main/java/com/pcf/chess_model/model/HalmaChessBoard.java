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

}
