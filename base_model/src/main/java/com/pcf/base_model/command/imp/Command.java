package com.pcf.base_model.command.imp;

import com.pcf.base_model.command.interfaces.ICommand;
import com.pcf.base_model.command.interfaces.IReceive;

public class Command implements ICommand {
    protected IReceive receive;

    public void setReceive(IReceive receive) {
        this.receive = receive;
    }

    @Override
    public void execute() {
        if (receive != null){
            receive.action(this);
        }
    }
}
