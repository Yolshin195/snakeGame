package ru.yolshin.snakeGame;

import ru.yolshin.gameEngine.Game;

import java.awt.*;

public class Apple {
    private Game game;
    private int x;
    private int y;
    private static String skin = "\uD83C\uDF4E";
    private boolean isShow = true;

    public Apple(Game game) {
        this.game = game;
    }

    public boolean isCellApple(int x, int y) {
        return (this.x == x) && (this.y == y);
    }

    public void kill() {
        this.isShow = true;
    }

    public void draw() {
        if (isShow) {
            //game.setCellValue(x, y, "");

            x = game.getRandomNumber(game.getScreenWidth());
            y = game.getRandomNumber(game.getScreenHeight());
            game.setCellValueEx(x, y, Color.WHITE, skin, Color.RED, 50);

            isShow = false;
        } else {
            game.setCellValueEx(x, y, Color.WHITE, skin, Color.RED, 50);
        }
    }
}
