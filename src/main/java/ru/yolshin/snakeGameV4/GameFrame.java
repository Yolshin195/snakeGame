package ru.yolshin.snakeGameV4;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        add(new GamePanel());
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }
}
