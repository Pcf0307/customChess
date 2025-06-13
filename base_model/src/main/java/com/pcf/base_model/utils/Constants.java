package com.pcf.base_model.utils;

public class Constants {
    /**
     * 事件类型定义
     */
    public enum StmEventNameEnum{
        START_GAME_EVENT,
        STOP_GAME_EVENT,
        RESTART_GAME_EVENT,
        CANCEL_STOP_GAME_EVENT,
        EXIT_GAME_EVENT
    }
    /**
     * 状态名定义
     */
    public enum StmStatesNameEnum{
        INIT("init"),
        INIT_GAME("init_game"),
        START_GAME("start_game"),
        STOP_GAME("stop_game"),
        END_GAME("end_game"),
        FINAL("final");
        private final String name;
        StmStatesNameEnum(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
