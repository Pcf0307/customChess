package com.pcf.base_model.state_machine.interfaces;

public interface ITrigger {
    void triggerEntry();
    boolean triggerCheck(IEvent event);
}
