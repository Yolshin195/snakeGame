package ru.yolshin.snakeGameV3;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
    private Food food;
    private Snake player;
    private Graphics g;

    private JFrame window;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public static final int DOT_SIZE = 20;

    public Game() {
        player = new Snake();
        food = new Food();
        g = new Graphics(this);

        window = new JFrame();
        window.setTitle("Snake game V3");
        window.setSize(WIDTH * DOT_SIZE, HEIGHT * DOT_SIZE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(g);
        window.validate();
    }

    public void start() {
        g.setStatus(Graphics.Status.RUNNING);
    }

    public void update() {
        if (g.getStatus() != Graphics.Status.RUNNING) return;

        if (checkFoodCollision()) {
            player.grow();
            food.randomSpawn(player);
        }
        else if (checkSnakeCollision() || checkWallCollision()) {
            g.setStatus(Graphics.Status.END);
        }
        else {
            player.move();
        }
    }

    public boolean checkWallCollision() {
        var head = player.getHead();
        return head.x < 0 || head.y < 0 || head.x >= WIDTH * DOT_SIZE || head.y >= HEIGHT * DOT_SIZE;
    }

    public boolean checkFoodCollision() {
        return player.getHead().equals(food.position);
    }

    public boolean checkSnakeCollision() {
        return player.getBody().stream().skip(1).anyMatch(player.getHead()::equals);
    }

    public Food getFood() {
        return food;
    }

    public Snake getPlayer() {
        return player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> player.UP();
            case KeyEvent.VK_S -> player.DOWN();
            case KeyEvent.VK_D -> player.RIGHT();
            case KeyEvent.VK_A -> player.LEFT();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
