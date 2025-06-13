package com.pcf.base_model.state_machine.imp;

import com.pcf.base_model.state_machine.interfaces.IEvent;
import com.pcf.base_model.state_machine.interfaces.IGuard;
import com.pcf.base_model.state_machine.interfaces.IState;
import com.pcf.base_model.state_machine.interfaces.ITransition;
import com.pcf.base_model.state_machine.interfaces.ITrigger;

import java.util.ArrayList;
import java.util.List;

/***
 * 负责控制状态之间的迁移动作的必要性判断和发起
 */
public class Transition implements ITransition {
    /**
     * 迁移名
     */
    private String name;
    /**
     * 源状态
     */
    private IState source;
    /**
     * 目标状态
     */
    private IState target;
    /**
     * 保护
     */
    private IGuard guard;

    /**
     * 触发条件list
     */
    private final List<ITrigger> triggerList = new ArrayList<>();

    /**
     * 初始化，会将自己给源状态添加迁移
     *
     * @param name   迁移名
     * @param source 源状态
     * @param target 目标状态
     */
    public Transition(String name, IState source, IState target) {
        this.name = name;
        this.source = source;
        this.target = target;
        this.source.addTransition(this);
    }

    /**
     * todo
     */
    @Override
    public void transitionEntry() {
        for (ITrigger t : triggerList) {
            t.triggerEntry();
        }
    }

    /**
     * 事件实施
     *
     * @return 如果可以迁移返回目标状态，不能迁移返回null
     */
    @Override
    public boolean transitionDoEvent(IEvent event) {
        if (guard != null && !guard.guardCheck()) {
            return false;
        }

        for (ITrigger t : triggerList) {
            if (t.triggerCheck(event)) {
                transition();
                return true;
            }
        }

        return false;
    }

    /**
     * 添加触发条件
     *
     * @param trigger 触发条件
     */
    @Override
    public void addTrigger(ITrigger trigger) {
        triggerList.add(trigger);
    }

    /***
     * 保护设定
     * @param guard 保护
     */
    @Override
    public void SetGuard(IGuard guard) {
        this.guard = guard;
    }

    @Override
    public IState transition() {
        source.stateExit();
        target.stateEntry();
        return target;
    }

}
