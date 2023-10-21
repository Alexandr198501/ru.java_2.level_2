package ru.java2.lesson1;


public class Track implements Obstruction {
    public static final int LENGTH = 100;

    @Override
    public boolean start(Participant participant) {
        if (LENGTH <= participant.getMaxRunDistance()) {
            participant.run(LENGTH);
            System.out.println(participant.getName() + " успешно пробежал " + LENGTH);
            return true;
        } else {
            System.out.println(participant.getName() + " не пробежал " + LENGTH);
            return false;
        }
    }

}
