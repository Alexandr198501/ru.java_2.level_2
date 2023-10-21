package ru.java2.lesson1;

public class Robot implements Participant {

    public static final int MAX_RUN_DISTANCE = 100;
    public static final int MAX_JUMP_HEIGHT = 1;

    String name;

    public Robot(String name) {
        this.name = name;
    }


    @Override
    public void run(int distance) {
    }

    @Override
    public void jump(int height) {
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
