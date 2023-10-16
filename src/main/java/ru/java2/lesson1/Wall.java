package ru.java2.lesson1;

public class Wall implements Obstruction {
    public static final int HEIGHT = 5;

    @Override
    public boolean start(Participant participant) {
        if (HEIGHT <= participant.getMaxJumpHeight()) {
            participant.jump(HEIGHT);
            System.out.println(participant.getName() + " успешно перепрыгнул " + HEIGHT);
            return true;
        } else {
            System.out.println(participant.getName() + " не перепрыгнул " + HEIGHT);
            return false;
        }
    }

}
