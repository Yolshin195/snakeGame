package ru.yolshin.snakeGameV2;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Logger;

public class Snake {
    private Logger logger = Logger.getLogger(Snake.class.getName());
    private Brain brain;
    private int length = 5;
    private final int dot_size;
    private Pos direction;
    private GameField gameField;
    private LinkedList<Pos> body;

    public Snake(int size, int dot_size, GameField gameField) {
        this.gameField = gameField;
        this.dot_size = dot_size;
        this.brain = new Brain();
        this.body = new LinkedList<Pos>();
        this.direction = Pos.RIGHT;

        for (int i = length-1; i >= 0; i--) {
            body.addLast(new Pos(i + 2, 9));
        }
    }

    public int sensor(Pos start , Pos direction) {
        Pos current = start;
        for (int i = 0; i < 30; i++) {
            current = Pos.add(current, direction);
            if (current.x < 0 || current.x > 30 || current.y < 0 || current.y > 30) {
                return i;
            }
        }
        return 30;
    }

    public void next() {
        var timeStart = System.nanoTime();
        if (checkCollision()) gameField.init();

        System.out.println(sensor(body.getFirst(), Pos.RIGHT));

        body.addFirst(Pos.add(body.getFirst(), direction));
        if (!gameField.isApple(body.getFirst())) body.removeLast();

        System.out.println(System.nanoTime() - timeStart);
    }

    public boolean checkCollision() {
        if (gameField.checkCollision(body.getFirst())) return true;

        Pos head = body.getFirst();
        return body.stream().skip(1).anyMatch(head::equals);
    }

    public void step() {
    }

    public void draw(Graphics g) {
//        for (int i = length-1; i >= 0; i--) {
//            g.fillRect(body[i].x * dot_size, body[i].y * dot_size, dot_size, dot_size);
//        }
        body.forEach(i -> g.fillRect(i.x * dot_size, i.y * dot_size, dot_size, dot_size));
        System.out.println(body);
    }

}
