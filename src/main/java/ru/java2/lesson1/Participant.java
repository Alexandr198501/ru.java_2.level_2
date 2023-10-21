package ru.java2.lesson1;

public interface Participant {
    void run(int distance);

    void jump(int distance);

    int getMaxRunDistance();

    int getMaxJumpHeight();

    String getName();

}
