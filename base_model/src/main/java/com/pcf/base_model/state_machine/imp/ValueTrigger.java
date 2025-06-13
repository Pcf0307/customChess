package com.pcf.base_model.state_machine.imp;

import com.pcf.base_model.state_machine.interfaces.IEvent;
import com.pcf.base_model.state_machine.interfaces.ITrigger;

public class ValueTrigger implements ITrigger {
    private final Object target;

    public ValueTrigger(Object target) {
        this.target = target;
    }

    @Override
    public void triggerEntry() {

    }

    @Override
    public boolean triggerCheck(IEvent event) {
        return target.equals(event.getValue());
    }
}
