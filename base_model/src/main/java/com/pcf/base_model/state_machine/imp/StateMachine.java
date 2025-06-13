package com.pcf.base_model.state_machine.imp;

import com.pcf.base_model.state_machine.interfaces.IEvent;
import com.pcf.base_model.state_machine.interfaces.IState;
import com.pcf.base_model.state_machine.interfaces.IStateMachine;

public class StateMachine implements IStateMachine {
    /**
     * 状态机名
     */
    private final String name;
    /**
     * 当前状态
     */
    protected IState activeState;
    /**
     * 初始状态
     */
    protected State initialState;

    public StateMachine(String name) {
        this.name = name;
    }

    @Override
    public void stateMachineEntry() {
        activeState = initialState;
        if (activeState != null){
            activeState.stateEntry();
        }
    }

    @Override
    public boolean stateMachineDoEvent(IEvent event) {
        if (activeState != null) {
            return activeState.stateDoEvent(event);
        }
        return false;
    }

    /**
     * 获取当前状态
     *
     * @return 当前状态
     */
    @Override
    public IState GetActiveState() {
        return activeState;
    }

    @Override
    public void setActiveState(IState state) {
        activeState = state;
    }

    /**
     * 获取状态机名
     *
     * @return 状态机名
     */
    public String getName() {
        return name;
    }
}
