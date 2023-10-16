package ru.java2.lesson1;

public class Human implements Participant {
    public static final int MAX_RUN_DISTANCE = 15;
    public static final int MAX_JUMP_HEIGHT = 6;
    private String name;

    public Human(String name) {
        this.name = name;
    }


    @Override
    public void run(int distance) {
    }

    @Override
    public void jump(int distance) {
    }

    @Override
    public int getMaxRunDistance() {
        return MAX_RUN_DISTANCE;
    }

    @Override
    public int getMaxJumpHeight() {
        return MAX_JUMP_HEIGHT;
    }

    @Override
    public String getName() {
        return name;
    }
}
