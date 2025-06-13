package com.pcf.chess_model.activity;

import static com.pcf.base_model.utils.ARouterPath.CHESS_GAME_ACTIVITY;
import static com.pcf.base_model.utils.Constants.StmEventNameEnum.CANCEL_STOP_GAME_EVENT;
import static com.pcf.base_model.utils.Constants.StmEventNameEnum.EXIT_GAME_EVENT;
import static com.pcf.base_model.utils.Constants.StmEventNameEnum.RESTART_GAME_EVENT;
import static com.pcf.base_model.utils.Constants.StmEventNameEnum.START_GAME_EVENT;
import static com.pcf.base_model.utils.Constants.StmEventNameEnum.STOP_GAME_EVENT;

import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pcf.base_model.BaseActivity;
import com.pcf.base_model.state_machine.imp.Event;
import com.pcf.chess_model.engine.HalmaGameEngine;
import com.pcf.chess_model.databinding.ChessGameActivityBinding;
import com.pcf.chess_model.factory.HalmaChessFactory;
import com.pcf.chess_model.model.HalmaChessBoard;
import com.pcf.chess_model.state_machine.ChessGameStateMachine;
import com.pcf.chess_model.viewmodel.ChessGameActivityViewModel;

@Route(path = CHESS_GAME_ACTIVITY)
public class ChessGameActivity extends BaseActivity<ChessGameActivityBinding> {
    private static final String TAG = ChessGameActivity.class.getSimpleName();
    private ChessGameActivityViewModel viewModel;
    @Override
    protected void onCreateData() {
        // todo 1、封装view model 2、实现原理
        viewModel = new ViewModelProvider(this).get(ChessGameActivityViewModel.class);
        binding.setModel(viewModel);

        HalmaGameEngine gameEngine = new HalmaGameEngine();
        binding.cusHalmaChessBoard.init(gameEngine);

        ChessGameStateMachine chessGame = new ChessGameStateMachine("chess_game");
        chessGame.init();

        binding.btnStartGame.setOnClickListener(v -> {
            chessGame.stateMachineDoEvent(new Event(START_GAME_EVENT));
        });
        binding.btnStopGame.setOnClickListener(v -> {
            chessGame.stateMachineDoEvent(new Event(STOP_GAME_EVENT));
        });
        binding.btnCancelStopGame.setOnClickListener(v -> {
            chessGame.stateMachineDoEvent(new Event(CANCEL_STOP_GAME_EVENT));
        });
        binding.btnExitGame.setOnClickListener(v -> {
            chessGame.stateMachineDoEvent(new Event(EXIT_GAME_EVENT));
        });
        binding.btnRestartGame.setOnClickListener(v -> {
            chessGame.stateMachineDoEvent(new Event(RESTART_GAME_EVENT));
        });

    }
}
