package ru.java2.lesson1;

public class Cat implements Participant {

    public static final int MAX_RUN_DISTANCE = 100;
    public static final int MAX_JUMP_HEIGHT = 50;

    String name;

    public Cat(String name) {
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
