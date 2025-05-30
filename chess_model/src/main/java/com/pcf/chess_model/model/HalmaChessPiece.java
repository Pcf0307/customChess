package com.pcf.chess_model.model;

import android.graphics.Point;

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

}
