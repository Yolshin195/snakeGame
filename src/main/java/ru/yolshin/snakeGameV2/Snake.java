package ru.yolshin.snakeGameV2;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Snake {
    private Pos[] body;
    private Brain brain;
    private int length = 5;
    private final int dot_size;
    private Pos direction;
    private GameField gameField;

    public Snake(int size, int dot_size, GameField gameField) {
        this.gameField = gameField;
        this.dot_size = dot_size;
        this.brain = new Brain();
        this.body = new Pos[size];
        this.direction = Pos.RIGHT;

        for (int i = length-1; i >= 0; i--) {
            body[i] = new Pos(i, 7);
        }
        System.out.println(Arrays.toString(body));
    }

    public void next() {
        if (checkCollision()) gameField.init();

        if (gameField.isApple(body[0])) length++;

        for (int i = length - 1; i > 0; i--) {
            body[i] = body[i - 1];
        }
        body[0] = Pos.add(body[0], direction);

    }

    public boolean checkCollision() {
        if (gameField.checkCollision(body[0])) return true;

        for (int i = length-1; i > 0; i--) {
            return body[0].equals(body[i]);
        }

        return false;
    }

    public void step() {
    }

    public void draw(Graphics g) {
        for (int i = length-1; i >= 0; i--) {
            g.fillRect(body[i].x * dot_size, body[i].y * dot_size, dot_size, dot_size);
        }
        System.out.println(Arrays.toString(body));
    }

    private enum Direction {
        UP, LIFT, RIGHT, DOWN
    }
}
