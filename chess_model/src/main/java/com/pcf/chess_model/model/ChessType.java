package com.pcf.chess_model.model;

public class ChessType {
    private String name;
    private String color;
    private String team;

    public ChessType(String name, String color, String team) {
        this.name = name;
        this.color = color;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
