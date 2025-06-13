package com.pcf.base_model.state_machine.imp;

import com.pcf.base_model.state_machine.interfaces.IEvent;
import com.pcf.base_model.state_machine.interfaces.IState;
import com.pcf.base_model.state_machine.interfaces.IStateMachine;


public class CompositeState extends State {
    /**
     * 子状态机
     */
    private final IStateMachine subStateMachine;
    /**
     * 浅历史
     */
    private IState lastActiveSubState;

    public CompositeState(String name, IStateMachine subStateMachine, StateMachine owner) {
        super(name, owner);
        this.subStateMachine = subStateMachine;
    }

    @Override
    public void stateEntry() {
        super.stateEntry();
        if (lastActiveSubState != null) {
            // 恢复历史
            subStateMachine.setActiveState(lastActiveSubState);
            lastActiveSubState.stateEntry();
        } else {
            // 进入子状态的初始状态
            subStateMachine.stateMachineEntry();
        }
    }

    @Override
    public boolean stateDoEvent(IEvent event) {
        // 尝试由子状态机处理事件
        if (subStateMachine.stateMachineDoEvent(event)) {
            // 子状态内跳转，仍停留在组合状态
            lastActiveSubState = subStateMachine.GetActiveState();
            return true;
        }
        return super.stateDoEvent(event);
    }

}
