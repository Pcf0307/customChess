package com.pcf.base_model.state_machine.interfaces;

public interface IState {
    /**
     * 状态迁移追加
     *
     * @param transition 迁移
     */
    void addTransition(ITransition transition);
    /***
     * 进入状态时实施的事件
     */
    void stateEntry();
    /***
     * 退出
     */
    void stateExit();
    /***
     * 发生事件时，实施状态迁移操作
     * @param event 发生的事件
     * @return 迁移后的状态，无法迁移返回null
     */
    boolean stateDoEvent(IEvent event);

    /***
     * 获取状态名
     * @return 状态名
     */
    String getName();
}
