package ru.yolshin.snakeGameV2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    public final int DOT_SIZE = 16;
    public final int DOT_COUNT = 30;
    private static LinkedList<Pos> snake;
    private static Pos apple;
    private static Direction direction;
    private static boolean inGame;
    private static Random random;
    private static final Font font = new Font("SanSerif", Font.BOLD, 34);
    private static Snake snakeV2;

    public GameField() {
        super();
        init();
        setBackground(Color.BLACK);

        Timer timer = new Timer(250, this);
        timer.start();

        addKeyListener(new Key());
        setFocusable(true);

        setFont(font);
    }

    public void init() {
        random = new Random();
        inGame = true;
        direction = Direction.RIGHT;
        snakeV2 = new Snake(DOT_COUNT, DOT_SIZE, this);

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

    public boolean checkCollision(Pos p) {
        if (p.x >= DOT_COUNT || p.x < 0) return true;
        if (p.y >= DOT_COUNT || p.y < 0) return true;
        return false;
    }

    public boolean isApple(Pos p) {
        return apple.x == p.x && apple.y == p.y;
    }

    public void next() {
        snakeV2.next();
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
        g.setColor(Color.BLUE);
        g.fillRect(0,0,DOT_SIZE, DOT_SIZE);
        g.fillRect((DOT_COUNT-1)*DOT_SIZE,(DOT_COUNT-3)*DOT_SIZE,DOT_SIZE, DOT_SIZE);
        g.fillRect((DOT_COUNT-1)*DOT_SIZE,0,DOT_SIZE, DOT_SIZE);
        g.fillRect(0,(DOT_COUNT-3)*DOT_SIZE,DOT_SIZE, DOT_SIZE);

        if (inGame) {
            g.setColor(Color.GREEN);
            //snake.forEach(p -> g.fillRect(p.x, p.y, DOT_SIZE, DOT_SIZE));
            snakeV2.draw(g);

            g.setColor(Color.RED);
            g.fillRect(apple.x, apple.y, DOT_SIZE, DOT_SIZE);

            //g.drawString("Count: " + snake.size(), 10, 20);
        } else {
            g.setColor(Color.RED);
            g.drawString("Game Over!", 130, 240);
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
