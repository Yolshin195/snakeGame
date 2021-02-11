package ru.yolshin.snakeGameV2;

public class Pos {
    public final int x;
    public final int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Pos add(Pos a, Pos b) {
        return new Pos(0, 0);
    }

    public static Pos multiple(Pos a, int n) {
        return new Pos(0, 0);
    }
}
