package com.pcf.chess_model.model;

import android.graphics.Point;

import androidx.annotation.NonNull;

import com.pcf.base_model.model.BaseChessPiece;
import com.pcf.base_model.state.State;

public class HalmaChessPiece extends BaseChessPiece {
    private int id;
    private Point position;
    private String name;
    private String color;
    private String team;
    private State state;

    public HalmaChessPiece() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @NonNull
    @Override
    public String toString() {
        return hashCode() + " x: " + getPosition().x + ",y: " + getPosition().y;
    }
}
