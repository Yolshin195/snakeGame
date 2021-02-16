package ru.yolshin.snakeGameV3;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Rectangle> body;
    private int w = Game.WIDTH;
    private int h = Game.HEIGHT;
    private int d = Game.DOT_SIZE;
    private Direction direction = Direction.NOTHING;

    public Snake() {
        body = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            var temp = new Rectangle(d, d);
            temp.setLocation((w/2 - i) * d, (h/2 - i) * d);
            body.add(temp);
        }
    }

    public void move() {
        step();
        body.remove(body.size() - 1);
    }

    public void grow() {
        step();
    }

    public void step() {
        if (direction == Direction.NOTHING) return;

        var head = body.get(0);
        var temp = new Rectangle(d, d);

        switch (direction) {
            case UP -> temp.setLocation(head.x, head.y - d);
            case DOWN -> temp.setLocation(head.x, head.y + d);
            case LIEF -> temp.setLocation(head.x - d, head.y);
            case RIGHT -> temp.setLocation(head.x + d, head.y);
        }

        body.add(0, temp);
    }

    public Rectangle getHead() {
        return body.get(0);
    }

    public List<Rectangle> getBody() {
        return body;
    }

    public void setBody(List<Rectangle> body) {
        this.body = body;
    }

    public void UP() {
        direction = Direction.UP;
    }

    public void DOWN() {
        direction = Direction.DOWN;
    }

    public void LEFT() {
        direction = Direction.LIEF;
    }
    public void RIGHT() {
        direction = Direction.RIGHT;
    }

    private enum Direction {
        UP, DOWN, LIEF, RIGHT, NOTHING
    }
}
