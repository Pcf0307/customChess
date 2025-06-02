package com.pcf.chess_model.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pcf.base_model.model.BaseChessBoard;

public class ChessGameActivityViewModel extends ViewModel {
    private MutableLiveData<BaseChessBoard> chessBoard = new MutableLiveData<>();

    public MutableLiveData<BaseChessBoard> getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(MutableLiveData<BaseChessBoard> chessBoard) {
        this.chessBoard = chessBoard;
    }
}
