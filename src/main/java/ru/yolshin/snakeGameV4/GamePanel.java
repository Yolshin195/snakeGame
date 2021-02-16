package ru.yolshin.snakeGameV4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    private final int WHILE = 600;
    private final int HEIGHT = 600;
    private final int UNIT_SIZE = 25;
    private final int DELAY = 100;
    private final int[] snake_x = new int[(WHILE * HEIGHT) / UNIT_SIZE];
    private final int[] snake_y = new int[(WHILE * HEIGHT) / UNIT_SIZE];
    private int snake_size = 4;
    private int appleEaten = 0;
    private int apple_x;
    private int apple_y;
    private Direction direction = Direction.D;

    private Random random;
    private Timer timer;
    private boolean inGame;

    public GamePanel() {
        random = new Random();
        setPreferredSize(new Dimension(WHILE, HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
        startGame();
    }

    public void startGame() {
        createApple();
        inGame = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void createApple() {
        apple_x = random.nextInt((WHILE / UNIT_SIZE)) * UNIT_SIZE;
        apple_y = random.nextInt((HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void checkApple() {
        if (apple_x == snake_x[0] && apple_y == snake_y[0]) {
            appleEaten++;
            createApple();
        }
    }

    public void checkCollision() {
        for (int i = snake_size + appleEaten; i > snake_size; i--) {
            if (snake_x[0] == snake_x[i] && snake_y[0] == snake_y[i]) {
                inGame = false;
                break;
            }
        }

        if (snake_x[0] < 0 || snake_x[0] > WHILE || snake_y[0] < 0 || snake_y[0] > HEIGHT) {
            inGame = false;
        }
    }

    public void next() {
        for (int i = snake_size + appleEaten; i > 0; i--) {
            snake_x[i] = snake_x[i - 1];
            snake_y[i] = snake_y[i - 1];
        }
        switch (direction) {
            case L -> snake_x[0] = snake_x[0] - UNIT_SIZE;
            case R -> snake_x[0] = snake_x[0] + UNIT_SIZE;
            case U -> snake_y[0] = snake_y[0] - UNIT_SIZE;
            case D -> snake_y[0] = snake_y[0] + UNIT_SIZE;
        }

    }

    public void draw(Graphics g) {

        g.setColor(Color.RED);
        g.fillOval(apple_x, apple_y, UNIT_SIZE, UNIT_SIZE);

        g.setColor(Color.GREEN);
        for (int i = 0; i < snake_size + appleEaten; i++) {
            g.fillRect(snake_x[i], snake_y[i], UNIT_SIZE, UNIT_SIZE);
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Game over!", 100, HEIGHT / 2 );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            draw(g);
        }
        else {
            gameOver(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            next();
        }

        repaint();
    }

    private enum Direction {
        U, D, R, L
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP: if (direction != Direction.D) direction = Direction.U; break;
                case KeyEvent.VK_DOWN: if (direction != Direction.U) direction = Direction.D; break;
                case KeyEvent.VK_LEFT: if (direction != Direction.R) direction = Direction.L; break;
                case KeyEvent.VK_RIGHT: if (direction != Direction.L) direction = Direction.R; break;
            }
        }
    }
}
