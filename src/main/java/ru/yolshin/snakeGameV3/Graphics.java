package ru.yolshin.snakeGameV3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics extends JPanel implements ActionListener {
    private Timer t;
    private Game game;
    private Snake player;
    private Food food;
    private Status status;

    public Graphics(Game game) {
        t = new Timer(100, this);
        t.start();
        status = Status.START;

        this.game = game;
        this.player = game.getPlayer();
        this.food = game.getFood();

        addKeyListener(game);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(Color.BLACK);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    @Override
    protected void printComponent(java.awt.Graphics g) {
        super.printComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (status == Status.START) {
            g2d.setColor(Color.WHITE);
            g2d.drawString("Press any key!",
                    Game.WIDTH / 2 * Game.DOT_SIZE, Game.HEIGHT / 2 * Game.DOT_SIZE);
        }
        else if (status == Status.RUNNING) {
            g2d.setColor(Color.RED);
            g2d.fill(food.position);

            g2d.setColor(Color.GREEN);
            player.getBody().forEach(g2d::fill);
        }
        else {
            g2d.setColor(Color.WHITE);
            g2d.drawString("Your score: " + (player.getBody().size() - 3),
                    Game.WIDTH / 2 * Game.DOT_SIZE, Game.HEIGHT / 2 * Game.DOT_SIZE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public enum Status {
        START, RUNNING, END
    }
}
