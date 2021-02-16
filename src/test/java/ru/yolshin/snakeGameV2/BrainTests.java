package ru.yolshin.snakeGameV2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BrainTests {

    @Test
    void think() {
        var brain = new Brain();
        var input = new float[24];

        System.out.println(Arrays.toString(brain.thinks(input)));

    }
}
