package ru.yolshin.snakeGameV2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTests {

    @Test
    void multiple() {
        var a = new Matrix(new float[][]{{2,2}, {2,2}, {2,2}});
        var b = new Matrix(new float[][]{{1,1,1}, {1,1,1}});
        var result = new Matrix(new float[][]{{4,4,4}, {4,4,4}, {4,4,4}});
        System.out.println(a.multiple(b));
        System.out.println(b.multiple(a));
        assertEquals(true, result.equals(a.multiple(b)));
    }

    @Test
    void equals() {
        var a = new Matrix(new float[][]{{2,2}, {2,2}, {2,2}});
        var b = new Matrix(new float[][]{{2,2}, {2,2}, {2,2}});

        assertEquals(b, a);
        assertEquals(a, a);
    }

    @Test
    void notEquals() {
        var a = new Matrix(new float[][]{{2,2}, {2,2}, {2,2}});
        var b = new Matrix(new float[][]{{1,1}, {1,1}, {1,1}});
        var c = new Matrix(new float[][]{{2,2}, {2,2}});

        assertNotEquals(b, a);
        assertFalse(a.equals(c));
    }

    @Test
    void get() {
        var a = new Matrix(new float[][]{{2,2}, {2,2}, {2,2}});
        assertEquals(2, a.get(0,0));
    }

    @Test
    void getRowSize() {
        var a = new Matrix(new float[][]{{2,2}, {2,2}, {2,2}});
        assertEquals(2, a.getRowSize());
    }

    @Test
    void getColumnSize() {
        var a = new Matrix(new float[][]{{2,2}, {2,2}, {2,2}});
        assertEquals(3, a.getColumnSize());
    }

}
