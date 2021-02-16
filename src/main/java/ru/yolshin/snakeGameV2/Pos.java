package ru.yolshin.snakeGameV2;

public class Pos {
    public static final Pos UP = new Pos(0, -1);
    public static final Pos DOWN = new Pos(0, 1);
    public static final Pos LEFT = new Pos(-1, 0);
    public static final Pos RIGHT = new Pos(1, 0);
    public static final Pos LEFT_UP = new Pos(-1, -1);
    public static final Pos RIGHT_UP = new Pos(1, -1);
    public static final Pos LEFT_DOWN = new Pos(-1, 1);
    public static final Pos RIGHT_DOWN = new Pos(1, 1);

    public final int x;
    public final int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Pos[] getDirections() {
        return new Pos[] {
                UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN
        };
    }

    public static Pos add(Pos a, Pos b) {
        return new Pos(a.x + b.x, a.y + b.y);
    }

    public static Pos multiple(Pos a, int n) {
        return new Pos(a.x * n, a.y * n);
    }

    @Override
    public String toString() {
        return "Pos{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pos)) return false;

        Pos pos = (Pos) o;

        if (x != pos.x) return false;
        return y == pos.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
