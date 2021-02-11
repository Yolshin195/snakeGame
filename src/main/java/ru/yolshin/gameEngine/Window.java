package ru.yolshin.gameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

public class Window extends Canvas implements Runnable {
    public static final int WINDOWS_WIDTH = 600;
    public static final int WINDOWS_HEIGHT = 600;
    private Boolean runnable = false;
    Displayed displayed;

    private double averageFPS;
    private TimerTask task;

    public Window(Displayed displayed) {
        this.displayed = displayed;

        setMinimumSize(new Dimension(WINDOWS_WIDTH, WINDOWS_HEIGHT));
        setMaximumSize(new Dimension(WINDOWS_WIDTH, WINDOWS_HEIGHT));
        setPreferredSize(new Dimension(WINDOWS_WIDTH, WINDOWS_HEIGHT));

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                int row = e.getX() / SIZE;
//                int column = e.getY() / SIZE;
//
//                map[column][row] = !map[column][row];
                if (true) {
                    displayed.onMouseLeftClick(e.getX(), e.getY());
                } else {
                    displayed.onMouseRightClick(e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                displayed.onKeyPress(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                displayed.onKeyReleased(e);
            }
        });

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        task = new TimerTask() {
            @Override
            public void run() {
                displayed.onTurn(0);
            }
        };

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(task, 1000, 250);
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        displayed.paint(g);

        g.drawString("FPS: " + averageFPS, 10, 30);

        g.dispose();

        bs.show();
    }

    @Override
    public void run() {
        displayed.initialize();
        int FPS = 30;
        long startTime;
        long totalTime = 0;
        long URDTimerMillis;
        long waitTime;
        long targetTime = 1000 / FPS;

        int frameCount = 0;
        int maxFrameCount = FPS;

        while (runnable) {
            startTime = System.nanoTime();

            //if (frameCount % 2 == 0) {
                //displayed.onTurn(frameCount);
            //}

            render();
            URDTimerMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - URDTimerMillis;

            if (waitTime < 0) waitTime = 5;

            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == maxFrameCount) {
                averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
            }
        }
    }

    public synchronized void start() {
        this.runnable = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        this.runnable = false;
    }
}
