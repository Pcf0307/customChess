package com.pcf.chess_model.activity;

import static com.pcf.base_model.utils.ARouterPath.CHESS_GAME_ACTIVITY;

import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pcf.base_model.BaseActivity;
import com.pcf.base_model.model.BaseChessBoard;
import com.pcf.chess_model.databinding.ChessGameActivityBinding;
import com.pcf.chess_model.factory.HalmaChessFactory;
import com.pcf.chess_model.interfaces.ChessFactory;
import com.pcf.chess_model.model.HalmaChessBoard;
import com.pcf.chess_model.viewmodel.ChessGameActivityViewModel;

@Route(path = CHESS_GAME_ACTIVITY)
public class ChessGameActivity extends BaseActivity<ChessGameActivityBinding> {
    private static final String TAG = ChessGameActivity.class.getSimpleName();
    ChessGameActivityViewModel viewModel;
    @Override
    protected void onCreateData() {
        // todo 1、封装viewmodel 2、实现原理
        viewModel = new ViewModelProvider(this).get(ChessGameActivityViewModel.class);
        binding.setModel(viewModel);
        ChessFactory halmaChessFactory = new HalmaChessFactory();
        BaseChessBoard chessBoard = halmaChessFactory.createChessBoard();
        viewModel.getChessBoard().setValue(chessBoard);
    }
}
