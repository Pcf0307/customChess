package com.pcf.chess_model.factory;

import com.pcf.base_model.model.BaseChessPiece;
import com.pcf.chess_model.interfaces.ChessFactory;
import com.pcf.chess_model.model.HalmaChessBoard;
import com.pcf.chess_model.model.HalmaChessPiece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HalmaChessFactory implements ChessFactory {
    @Override
    public HalmaChessBoard createChessBoard() {
        return new HalmaChessBoard();
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
