package ru.yolshin.snakeGame;

import ru.yolshin.gameEngine.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.nio.charset.StandardCharsets;


/**
 * Давай напишем игру "Змейка".
 *
 * Правила игры:
 *
 *     - Игрок управляет движением змейки, которая ползает по квадратному полю: вверх, вниз, вправо и влево.
 *     - Остановить движение змейки нельзя.
 *     - Цель игры — съесть как можно большее количество яблок, которые появляются поочередно,
 *       в случайном месте игрового поля, как только змейка "проглотит" предыдущее.
 *     - С каждым новым яблоком змейка растет на один сегмент (занимает на одну ячейку больше).
 *       В нашей игре на старте змейка занимает три ячейки (имеет три сегмента) и может дорасти до 28.
 *     - По мере того, как растет змейка, скорость игры увеличивается.
 *     - Змейка не может проходить "сквозь себя" или выходить за пределы игрового поля.
 *     - Игра оканчивается победой, если длина змейки увеличилась до 28 сегментов.
 *     - Игра оканчивается проигрышем, если касается сама себя или края поля.
 *     Правила игры взяты с javarush
 */
public class SnakeGame extends Game {
    public static final int HEIGHT = 15;
    public static final int WIDTH = 15;
    private int step = 0;
    private Snake snake;
    public static Apple apple;

    @Override
    public void initialize() {
        setScreenSize(15, 15);

        snake = new Snake(this);
        apple = new Apple(this);
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> snake.setDirection(Snake.Direction.UP);
            case KeyEvent.VK_DOWN -> snake.setDirection(Snake.Direction.DOWN);
            case KeyEvent.VK_LEFT -> snake.setDirection(Snake.Direction.LEFT);
            case KeyEvent.VK_RIGHT -> snake.setDirection(Snake.Direction.RIGHT);
            default -> System.out.println("Нет совпадений!!");
        }
    }

    @Override
    public void onTurn(int step) {
        snake.draw();
        apple.draw();
    }
}
