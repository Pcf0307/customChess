package com.pcf.base_model.command.imp;

import com.pcf.base_model.command.interfaces.ICommand;
import com.pcf.base_model.command.interfaces.IInvoker;

public class Invoker implements IInvoker {
    protected ICommand command;

    public void setCommand(ICommand command) {
        this.command = command;
    }

    @Override
    public void execute() {
        if (command != null){
            command.execute();
        }
    }
}
