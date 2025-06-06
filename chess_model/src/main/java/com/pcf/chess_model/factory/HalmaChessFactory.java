package com.pcf.chess_model.factory;

import com.pcf.base_model.model.BaseChessBoard;
import com.pcf.base_model.model.BaseChessPiece;
import com.pcf.base_model.utils.Constants;
import com.pcf.chess_model.interfaces.ChessFactory;
import com.pcf.chess_model.model.HalmaChessBoard;
import com.pcf.chess_model.model.HalmaChessPiece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HalmaChessFactory implements ChessFactory {

    @Override
    public HalmaChessBoard createChessBoard() {
        HalmaChessBoard halmaChessBoard = new HalmaChessBoard();
        halmaChessBoard.setBoardRows(Constants.HALMA_CHESS_BOARD_ROWS);
        halmaChessBoard.setBoardCols(Constants.HALMA_CHESS_BOARD_COLS);
        return halmaChessBoard;
    }

    @Override
    public HalmaChessPiece createChessPiece() {
        return new HalmaChessPiece();
    }

    @Override
    public List<BaseChessPiece> createChessPieces(int num) {
        if (num <= 0) {
            return Collections.emptyList();
        }
        ArrayList<BaseChessPiece> chessPieces = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            chessPieces.add(new HalmaChessPiece());
        }
        return chessPieces;
    }

}
