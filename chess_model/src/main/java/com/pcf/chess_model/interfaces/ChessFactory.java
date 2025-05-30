package com.pcf.chess_model.interfaces;

import com.pcf.base_model.model.BaseChessBoard;
import com.pcf.base_model.model.BaseChessPiece;

import java.util.List;

public interface ChessFactory {
    BaseChessBoard createChessBoard();
    BaseChessPiece createChessPiece();
    List<BaseChessPiece> createChessPieces(int num);
}
