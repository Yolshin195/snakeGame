package ru.yolshin.snakeGameV2;

import java.util.Arrays;
import java.util.Random;

public class Matrix {
    private float[][] heap;

    public Matrix(float[][] heap) {
        this.heap = heap;
    }

    public static Matrix random(int n, int m) {
        var random = new Random();
        var matrix = new float[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = random.nextFloat();
            }
        }

        return new Matrix(matrix);
    }

    public static Matrix multiple(Matrix a, Matrix b) {
        return a.multiple(b);
    }

    public Matrix multiple(Matrix b) {
        if (this.getRowSize() != b.getColumnSize()) {
            throw new RuntimeException("Matrices cannot be multiplied row a != column b");
        }
        var matrix = new float[this.getColumnSize()][b.getRowSize()];
        for (int n = 0; n < this.getColumnSize(); n++) {
            for (int m = 0; m < b.getRowSize(); m++) {
                float sum = 0;
                for (int i = 0; i < b.getColumnSize(); i++) {
                     sum += this.heap[n][i] * b.heap[i][m];
                }
                matrix[n][m] = sum;
            }
        }
        return new Matrix(matrix);
    }

    public float get(int n, int m) {
        return heap[n][m];
    }

    public int getRowSize() {
        return heap[0].length;
    }

    public int getColumnSize() {
        return heap.length;
    }

    @Override
    public String toString() {
        var str = new StringBuilder();
        str.append("Matrix {\n");
        for (var item : heap) {
            str.append("\t").append(Arrays.toString(item)).append("\n");
        }
        str.append("}");
        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Matrix)) return false;

        var b = (Matrix) obj;
        if (getColumnSize() != b.getColumnSize()) return false;
        if (getRowSize() != b.getRowSize()) return false;

        for (int row = 0; row < getColumnSize(); row++) {
            if (!Arrays.equals(heap[row], b.heap[row])) return false;
        }

        return true;
    }
}
