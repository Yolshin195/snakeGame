package ru.yolshin.snakeGame;

import ru.yolshin.gameEngine.Cell;
import ru.yolshin.gameEngine.Game;

import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private String headSkin = "\uD83D\uDC7E";
    private String bodySkin = "\u2B24";
    private Direction direction;
    private LinkedList<Pos> body;
    private Game game;

    public Snake(Game game) {
        this.game = game;
        this.direction = Direction.UP;
        body = new LinkedList<>();
        body.addLast(new Pos(5, 5));
        body.addLast(new Pos(4, 5));
        body.addLast(new Pos(3, 5));
        body.addLast(new Pos(2, 5));
    }

    public void draw() {
        for (int i = 0; i < body.size(); i++) {
            if (i == 0) {
                Pos head = body.getFirst();
                game.setCellValueEx(head.x, head.y, Color.WHITE, headSkin, Color.BLACK, 50);
                continue;
            }
            Pos item = body.get(i);
            game.setCellValueEx(item.x, item.y, Color.WHITE, bodySkin, Color.BLACK, 40);
        }
        step();
    }

    public boolean isApple(int x, int y) {
        return false;
    }

    public void step() {
        Pos posHead = body.getFirst();
        System.out.println(direction);

        switch (direction) {
            case DOWN -> {
                int y = posHead.y + 1;
                if (y >= game.getScreenHeight()) y = 0;
                body.addFirst(new Pos(posHead.x, y));
            }
            case UP -> {
                int y = posHead.y - 1;
                if (y < 0) y = game.getScreenHeight() - 1;
                body.addFirst(new Pos(posHead.x, y));
            }
            case LEFT -> {
                int x = posHead.x - 1;
                if (x < 0) x = game.getScreenWidth() - 1;
                body.addFirst(new Pos(x, posHead.y));
            }
            case RIGHT -> {
                int x = posHead.x + 1;
                if (x >= game.getScreenWidth()) x = 0;
                body.addFirst(new Pos(x, posHead.y));
            }
        }

        Pos head = body.getFirst();
        if (!SnakeGame.apple.isCellApple(head.x, head.y)) {
            Pos removePos = body.removeLast();
            game.setCellValue(removePos.x, removePos.y, "");
        } else {
            SnakeGame.apple.kill();
        }

    }

    public void setDirection(Direction direction) {
        System.out.println("Изменение направления: " + direction);
        if ((this.direction == Direction.UP || this.direction == Direction.DOWN) && (direction == Direction.LEFT || direction == Direction.RIGHT)) {
            this.direction = direction;
        }

        if ((this.direction == Direction.LEFT || this.direction == Direction.RIGHT) && (direction == Direction.UP || direction == Direction.DOWN)) {
            this.direction = direction;
        }

        //this.direction = direction;
    }

    public class Pos {
        public final int x;
        public final int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }
}
