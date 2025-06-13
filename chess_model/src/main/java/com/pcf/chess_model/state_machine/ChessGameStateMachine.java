package com.pcf.chess_model.state_machine;

import static com.pcf.base_model.utils.Constants.StmEventNameEnum.CANCEL_STOP_GAME_EVENT;
import static com.pcf.base_model.utils.Constants.StmEventNameEnum.EXIT_GAME_EVENT;
import static com.pcf.base_model.utils.Constants.StmEventNameEnum.RESTART_GAME_EVENT;
import static com.pcf.base_model.utils.Constants.StmEventNameEnum.START_GAME_EVENT;
import static com.pcf.base_model.utils.Constants.StmEventNameEnum.STOP_GAME_EVENT;
import static com.pcf.base_model.utils.Constants.StmStatesNameEnum.END_GAME;
import static com.pcf.base_model.utils.Constants.StmStatesNameEnum.FINAL;
import static com.pcf.base_model.utils.Constants.StmStatesNameEnum.INIT;
import static com.pcf.base_model.utils.Constants.StmStatesNameEnum.INIT_GAME;
import static com.pcf.base_model.utils.Constants.StmStatesNameEnum.START_GAME;
import static com.pcf.base_model.utils.Constants.StmStatesNameEnum.STOP_GAME;

import com.pcf.base_model.state_machine.imp.State;
import com.pcf.base_model.state_machine.imp.StateMachine;
import com.pcf.base_model.state_machine.imp.Transition;
import com.pcf.base_model.state_machine.imp.ValueTrigger;
import com.pcf.base_model.state_machine.interfaces.IState;
import com.pcf.base_model.state_machine.interfaces.ITrigger;
import com.pcf.base_model.state_machine.state.InitialState;

public class ChessGameStateMachine extends StateMachine {
    public ChessGameStateMachine(String name) {
        super(name);
    }
    public void init(){
        // 初期状态
        initialState = new InitialState(INIT.getName(),this);
        IState initGameState = new State(INIT_GAME.getName(),this);
        IState startGameState = new State(START_GAME.getName(),this);
        IState stopGameState = new State(STOP_GAME.getName(),this);
        IState endGameState = new State(END_GAME.getName(),this);
        IState finalState = new State(FINAL.getName(),this);

        Transition initToInitGame = new Transition("initToInitGame", initialState, initGameState);

        Transition initToStart = new Transition("initToStart", initGameState, startGameState);
        ITrigger startTrigger = new ValueTrigger(START_GAME_EVENT);
        initToStart.addTrigger(startTrigger);

        Transition startToStop = new Transition("startToStop", startGameState, stopGameState);
        ITrigger stopTrigger = new ValueTrigger(STOP_GAME_EVENT);
        startToStop.addTrigger(stopTrigger);

        Transition stopToStart = new Transition("stopToStart", stopGameState, startGameState);
        ITrigger reStartTrigger = new ValueTrigger(CANCEL_STOP_GAME_EVENT);
        stopToStart.addTrigger(reStartTrigger);

        Transition startToEnd = new Transition("startToEnd", startGameState, endGameState);
        ITrigger endTrigger = new ValueTrigger(EXIT_GAME_EVENT);
        startToEnd.addTrigger(endTrigger);

        Transition endToStart = new Transition("endToStart", endGameState, startGameState);
        ITrigger restartTrigger = new ValueTrigger(RESTART_GAME_EVENT);
        endToStart.addTrigger(restartTrigger);

        Transition endToFinal = new Transition("endToFinal", endGameState, finalState);

        // 启动状态机
        stateMachineEntry();
    }
}
