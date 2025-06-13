package com.pcf.base_model.command.interfaces;

public interface IInvoker {
    void execute();
    void setCommand(ICommand command);
}
