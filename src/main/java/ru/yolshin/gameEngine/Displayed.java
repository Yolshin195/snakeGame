package ru.yolshin.gameEngine;

import java.awt.*;
import java.awt.event.KeyEvent;

public interface Displayed {
    void initialize();
    void paint(Graphics g);
    void onMouseLeftClick(int x, int y);
    void onMouseRightClick(int x, int y);
    void onKeyPress (KeyEvent key);
    void onKeyReleased (KeyEvent key);

    // Timer
    void setTurnTimer(int timeMs);
    void onTurn(int step);
    void stopTurnTimer();
}
