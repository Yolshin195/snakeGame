package ru.yolshin.gameEngine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Game implements Displayed {
    public static int WINDOWS_WIDTH = 600;
    public static int WINDOWS_HEIGHT = 600;

    private int width;
    private int height;
    private int cellSizeWidth;
    private int cellSizeHeight;
    private boolean isShowGrid;
    private Cell[][] cells;
    private Font monoFont;

    Random random;

    public Game() {
        random = new Random();
        this.showGrid(true);
    }

    public void initialize() {
        setScreenSize(5, 3);
        showGrid(false);
        //  Меняем фон центральной клетки на синий, и отображаем в ней “Х”
        setCellValueEx(1, 1, Color.BLUE, "X", Color.ORANGE, 70);
    }

    public void setScreenSize(int width, int height) {
        this.width = width;
        this.height = height;

        this.cellSizeWidth = WINDOWS_WIDTH / width;
        this.cellSizeHeight = WINDOWS_HEIGHT / height;

        this.monoFont = new Font("Monospaced", Font.BOLD
                | Font.ITALIC, this.cellSizeHeight);

        this.cells = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.cells[y][x] = new Cell(x, y);
            }
        }
    }

    /**
     * Возвращает щирену экрана (Количество клеток)
     * @return
     */
    public int getScreenWidth() {
        return this.width;
    }

    /**
     * Возвращает высоту экрана (Количество клеток)
     * @return
     */
    public int getScreenHeight() {
        return this.height;
    }

    /**
     * Включает и выключает сетку, разделяющую клетки.
     * По умолчанию сетка отображается.
     * Чтобы ее выключить, вызовите этот метод с параметром false:
     * showGrid(false);
     *
     * @param isShow
     */
    public void showGrid(boolean isShow) {
        this.isShowGrid = isShow;
    }

    /**
     * Устанавливает клетке с кординатами (x, y) цвет color
     *     setCellColor(0, 0, Color.RED);
     *     setCellColor(3, 6, Color.BLACK);
     *     setCellColor(6, 8, Color.NONE);
     * @param x
     * @param y
     * @param color
     */
    public void setCellColor(int x, int y, Color color) {
        cells[y][x].setColor(color);
    }

    /**
     * Возвращает цвет клетки с кординатами (x, y)
     * Color myColor = getCellColor(2, 0);
     * @param x
     * @param y
     */
    public Color getCellColor(int x, int y) {
        return cells[y][x].getColor();
    }

    /**
     * Помещает в клетку с координатами (x, y) текст value:
     *     setCellValue(3, 3, "text");
     *     setCellValue(0, 8, "W");
     *     setCellValue(4, 1, "2222");
     *     setCellValue(6, 6, "");
     * @param x
     * @param y
     * @param value
     */
    public void setCellValue(int x, int y, String value) {
        cells[y][x].setValue(value);
    }

    /**
     * Возвращает текст, содержащийся в клетке с координатами (x, y):
     *     String s = getCellValue(3, 3);
     *     System.out.println(getCellValue(4, 1));
     * @param x
     * @param y
     * @return
     */
    public String getCellValue(int x, int y) {
        return cells[y][x].getValue();
    }

    /**
     * устанавливает размер контента в клетке с координатами (x, y).
     * size – высота текста в процентах от высоты клетки:
     *  setCellTextSize(2 , 0, 70); //  70% высоты клетки
     * @param x
     * @param y
     * @param size
     */
    public void setCellTextSize(int x, int y, int size) {
        cells[y][x].setTextSize(size);
    }

    /**
     * Возвращает размер контента в клетке с координатами (x, y):
     *     int size = getCellTextSize(2 , 0);
     * @param x
     * @param y
     * @return
     */
    public int getCellTextSize(int x, int y) {
        return cells[y][x].getTextSize();
    }

    /**
     * Помещает в клетку с координатами (x, y) число value:
     *     setCellNumber(3, 3, 40);
     *     setCellNumber(0, 8, -8);
     *     setCellNumber(4, 1, 2222);
     *     setCellNumber(6, 6, 0);
     * @param x
     * @param y
     * @param value
     */
    public void setCellNumber(int x, int y, int value) {
        cells[y][x].setValue(Integer.toString(value));
    }


    /**
     * Возвращает число, содержащееся в клетке с координатами (x, y).
     * Если в клетке содержится не число, возвращает 0:
     *     int i = getCellNumber(3, 3);
     *     System.out.println(getCellNumber(4, 1));
     * @param x
     * @param y
     * @return
     */
    public int getCellNumber(int x, int y) {
        return Integer.parseInt(cells[y][x].getValue());
    }

    /**
     * Устанавливает цвет контента(текста) в клетке с координатами (x, y):
     *     setCellTextColor(2, 1, Color.GREEN);
     *     setCellTextColor(0, 1, Color.NONE);
     * @param x
     * @param y
     * @param color
     */
    public void setCellTextColor(int x, int y, Color color) {
        cells[y][x].setTextColor(color);
    }

    /**
     * Возвращает цвет контента (текста) в клетке с координатами (x, y):
     *     Color textColor = getCellTextColor(1, 3);
     * @param x
     * @param y
     * @return
     */
    public Color getCellTextColor(int x, int y) {
        return cells[y][x].getTextColor();
    }

    /**
     * Устанавливает клетке с координатами (x, y) цвет фона cellColor и контент value:
     *     setCellValueEx(0, 2, Color.BLUE, "56");
     * @param x
     * @param y
     * @param cellColor
     * @param value
     */
    public void setCellValueEx(int x, int y, Color cellColor, String value) {
        cells[y][x].setColor(cellColor);
        cells[y][x].setValue(value);
    }


    /**
     * Устанавливает клетке с координатами (x, y) цвет фона cellColor, контент value и цвет контента textColor:
     *     setCellValueEx(0, 2, Color.BLACK, "56", Color.GREEN);
     * @param x
     * @param y
     * @param cellColor
     * @param value
     * @param textColor
     */
    public void setCellValueEx(int x, int y, Color cellColor, String value, Color textColor) {
        cells[y][x].setColor(cellColor);
        cells[y][x].setValue(value);
        cells[y][x].setTextColor(textColor);
    }

    /**
     * Устанавливает клетке с координатами (x, y)
     * цвет фона cellColor, контент value, цвет контента textColor и размер контента textSize:
     *     setCellValueEx(0, 2, Color.BLACK, "56", Color.GREEN, 70);
     * @param x
     * @param y
     * @param cellColor
     * @param value
     * @param textColor
     * @param textSize
     */
    public void setCellValueEx(int x, int y, Color cellColor, String value, Color textColor, int textSize) {
        cells[y][x].setColor(cellColor);
        cells[y][x].setValue(value);
        cells[y][x].setTextColor(textColor);
        cells[y][x].setTextSize(textSize);
    }

    /**
     * В конце игры игроку нужно сообщить о победе или поражении.
     * Для этого есть специальный метод, который выводит диалоговое окно на игровой экран:
     *     cellColor — цвет фона диалогового окна;
     *     message — текст сообщения;
     *     textColor — цвет текста сообщения;
     *     textSize — размер текста сообщения.
     *
     * Диалоговое окно закрывается само по себе, если пользователь нажал пробел.
     *
     * @param cellColor
     * @param message
     * @param textColor
     * @param textSize
     */
    public void showMessageDialog(Color cellColor, String message, Color textColor, int textSize) {

    }


    /**
     * Возвращает случайное целое число от 0 до (max–1) включительно.
     *
     * @param max
     * @return
     */
    public int getRandomNumber(int max) {
        return random.nextInt(max);
    }

    /**
     * Возвращает случайное целое число от min до (max–1) включительно.
     *
     * @param min
     * @param max
     * @return
     */
    public int getRandomNumber(int min, int max) {
        return  random.nextInt(max + 1 - min) + min;
    }


    public void onMouseLeftClick(int x, int y) {

    }

    public void onMouseRightClick(int x, int y) {

    }

    public void onKeyPress (KeyEvent key) {

    }

    public void onKeyReleased (KeyEvent key) {
    }

    public void setTurnTimer(int timeMs) {

    }

    public void onTurn(int step) {

    }

    public void stopTurnTimer() {

    }

    public void paint(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = cells[y][x];
                int c_x = x * cellSizeWidth;
                int c_y = y * cellSizeHeight;

                g.setColor(cell.getColor());
                g.fillRect(x * cellSizeWidth, y * cellSizeHeight, cellSizeWidth, cellSizeHeight);

                if (cell.getValue() != null) {
                    g.setColor(cell.getTextColor());
                    int fontSize = Math.round((cellSizeHeight / 100f) * cell.getTextSize());
                    g.setFont(new Font("SanSerif", Font.BOLD, fontSize));
                    FontMetrics fm = g.getFontMetrics();
                    var w = fm.stringWidth(cell.getValue());
                    var h = fm.getAscent();
                    g.drawString(cell.getValue(), c_x + (cellSizeWidth - w) / 2, c_y + Math.round(h * .9f) + (cellSizeHeight - h) / 2);
                }
            }
        }

        if (isShowGrid) {
            g.setColor(Color.BLACK);
            for (int x = 0; x < WINDOWS_WIDTH; x += cellSizeWidth) {
                g.drawLine(x, 0, x, WINDOWS_HEIGHT);
            }
            for (int y = 0; y < WINDOWS_HEIGHT; y += cellSizeHeight) {
                g.drawLine(0, y, WINDOWS_WIDTH, y);
            }
        }
    }

}
