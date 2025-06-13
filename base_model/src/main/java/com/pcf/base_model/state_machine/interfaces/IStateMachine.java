package com.pcf.base_model.state_machine.interfaces;

public interface IStateMachine {
    void stateMachineEntry();
    /***
     * 事件实施
     * @param event 事件
     * @return 迁移后的状态，不迁移返回null
     */
    boolean stateMachineDoEvent(IEvent event);

    /**
     * 返回当前状态
     * @return 当前状态
     */
    IState GetActiveState();

    /**
     * 设置当前状态
     * @param state 状态
     */
    void setActiveState(IState state);
}
