package com.pcf.base_model.state_machine.imp;

import com.pcf.base_model.state_machine.interfaces.IEvent;

public class Event implements IEvent {
    private Object value;

    public Event(Object value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
