package ru.yolshin.snakeGameV2;

import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        super("Snake Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new GameField());
        setSize(480, 480);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}
