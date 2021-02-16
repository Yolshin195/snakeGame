package ru.yolshin.snakeGameV3;

import java.awt.*;

public class Food {
    public final Rectangle position;
    private int w = Game.WIDTH;
    private int h = Game.HEIGHT;
    private int d = Game.DOT_SIZE;

    Food() {
        position = new Rectangle(d, d);
    }

    public void randomSpawn(Snake snake) {
        boolean inSnake;
        do {
            int x = (int) (Math.random() * w);
            int y = (int) (Math.random() * h);
            position.setLocation(x * d, y * d);
            inSnake = snake.getBody().contains(position);

        } while (inSnake);
    }
}
