package ru.yolshin.snakeGameV2;

public class Brain {
    public static final int inputSize = 24;
    public static final int hiddenSize = 12;
    public static final int outputSize = 4;

    private final Matrix inputToHidden = Matrix.random(inputSize, hiddenSize + 1);
    private final Matrix hiddenToOutput = Matrix.random(hiddenSize, outputSize + 1);

    public float[] thinks(float[] inputs) {
        return new float[4];
    }
}
