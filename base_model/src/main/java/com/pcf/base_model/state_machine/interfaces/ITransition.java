package com.pcf.base_model.state_machine.interfaces;

public interface ITransition {
    void transitionEntry();


    boolean transitionDoEvent(IEvent event);

    void addTrigger(ITrigger trigger);

    void SetGuard(IGuard guard);

    /**
     * 迁移动作实施
     *
     * @return 目标状态
     */
    IState transition();
}
