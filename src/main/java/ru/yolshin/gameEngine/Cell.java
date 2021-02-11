package ru.yolshin.gameEngine;

import java.awt.*;

public class Cell {
    private int x;
    private int y;
    private int textSize;
    private String value;
    private Color color;
    private Color textColor;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;

        this.textSize = 90;
        this.color = Color.WHITE;
        this.textColor = Color.BLACK;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
