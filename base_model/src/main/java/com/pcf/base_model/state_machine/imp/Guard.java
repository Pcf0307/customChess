package com.pcf.base_model.state_machine.imp;

import com.pcf.base_model.state_machine.interfaces.IGuard;

public class Guard implements IGuard {
    @Override
    public boolean guardCheck() {
        return false;
    }
}
