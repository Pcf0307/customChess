package com.pcf.base_model.state_machine.state;

import com.pcf.base_model.state_machine.imp.State;
import com.pcf.base_model.state_machine.imp.StateMachine;

public class FinalState extends State {
    public FinalState(String name, StateMachine owner) {
        super(name, owner);
    }
}
