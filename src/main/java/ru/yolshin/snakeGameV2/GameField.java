package ru.yolshin.snakeGameV2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    private static final int DOT_SIZE = 16;
    private static final int DOT_COUNT = 30;
    private static LinkedList<Pos> snake;
    private static Pos apple;
    private static Direction direction;
    private static boolean inGame;
    private static Random random;
    private static final Font font = new Font("SanSerif", Font.BOLD, 34);

    public GameField() {
        super();
        init();
        setBackground(Color.BLACK);

        Timer timer = new Timer(250, this);
        timer.start();

        addKeyListener(new Key());
        setFocusable(true);
    }

    public void init() {
        random = new Random();
        inGame = true;
        direction = Direction.RIGHT;

        createSnake();
        createApple();
    }

    public void createSnake() {
        snake = new LinkedList<>();
        for (int i = 5; i >= 0; i--) {
            snake.addLast(new Pos(i * DOT_SIZE, 7 * DOT_SIZE));
        }
    }

    public void createApple() {
        apple = new Pos(random.nextInt(DOT_COUNT) * DOT_SIZE, random.nextInt(DOT_COUNT) * DOT_SIZE);
    }

    public void checkCollision() {
        Pos p = snake.getFirst();
        if (p.x < 0 || p.x >= DOT_COUNT * DOT_SIZE) inGame = false;
        if (p.y < 0 || p.y >= DOT_COUNT * DOT_SIZE) inGame = false;
        snake.stream().skip(1).forEach(b -> {
            if (p.y == b.y && p.x == b.x) inGame = false;
        });
    }

    public boolean isApple() {
        Pos p = snake.getFirst();
        return apple.x == p.x && apple.y == p.y;
    }

    public void next() {
        checkCollision();

        Pos p = snake.getFirst();
        switch (direction) {
            case LIFT -> snake.addFirst(new Pos(p.x - DOT_SIZE, p.y));
            case RIGHT -> snake.addFirst(new Pos(p.x + DOT_SIZE, p.y));
            case DOWN -> snake.addFirst(new Pos(p.x, p.y + DOT_SIZE));
            case UP -> snake.addFirst(new Pos(p.x, p.y - DOT_SIZE));
        }

        if (isApple()) {
            createApple();
        } else {
            snake.removeLast();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            next();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame) {
            g.setColor(Color.GREEN);
            snake.forEach(p -> g.fillRect(p.x, p.y, DOT_SIZE, DOT_SIZE));

            g.setColor(Color.RED);
            g.fillRect(apple.x, apple.y, DOT_SIZE, DOT_SIZE);

            //g.drawString("Count: " + snake.size(), 10, 20);
        } else {
            g.setColor(Color.RED);
            g.setFont(font);
            g.drawString("Game Over!", 200, getHeight() / 2);
        }
    }

    private enum Direction {
        UP, LIFT, RIGHT, DOWN
    }

    private class Key extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> direction = Direction.LIFT;
                case KeyEvent.VK_RIGHT -> direction = Direction.RIGHT;
                case KeyEvent.VK_UP -> direction = Direction.UP;
                case KeyEvent.VK_DOWN -> direction = Direction.DOWN;
                case KeyEvent.VK_R -> init();
            }
        }
    }
}
