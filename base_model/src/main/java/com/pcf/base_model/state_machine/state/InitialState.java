package com.pcf.base_model.state_machine.state;

import com.pcf.base_model.state_machine.imp.State;
import com.pcf.base_model.state_machine.imp.StateMachine;
import com.pcf.base_model.state_machine.interfaces.ITransition;

public class InitialState extends State {

    public InitialState(String name, StateMachine owner) {
        super(name, owner);
    }

    @Override
    public void stateEntry() {
        // 无条件迁移
        for (ITransition t : transitionList) {
            t.transition();
        }
    }
}
