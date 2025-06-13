package com.pcf.base_model.state_machine.imp;

import android.util.Log;

import com.pcf.base_model.state_machine.interfaces.IEvent;
import com.pcf.base_model.state_machine.interfaces.IState;
import com.pcf.base_model.state_machine.interfaces.ITransition;

import java.util.ArrayList;
import java.util.List;

/***
 * 状态类主要实现管理迁移、进入和退出操作，以及基础的实践操作等功能
 */
public class State implements IState {
    private static final String TAG = State.class.getSimpleName();
    private final String name;
    private StateMachine owner;

    /**
     * 状态迁移list
     */
    protected final List<ITransition> transitionList = new ArrayList<>();

    public State(String name, StateMachine owner) {
        this.name = name;
        this.owner = owner;
    }

    private void Do() {
        owner.activeState = this;
        Log.d(TAG, "state do: " + name);
    }

    @Override
    public void addTransition(ITransition transition) {
        transitionList.add(transition);
    }

    @Override
    public void stateEntry() {
        for (ITransition t : transitionList) {
            t.transitionEntry();
        }
        Do();
    }

    @Override
    public void stateExit() {

    }

    @Override
    public boolean stateDoEvent(IEvent event) {
        for (ITransition t : transitionList) {
            if (t.transitionDoEvent(event)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
