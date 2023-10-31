package ru.java2.lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneBook {

// Сщздаю мапу.
    Map<String, Set<String>> phoneBook = new HashMap<>();

// Метод для добавления записи в справочник.
    public void add(String surname, String phoneNumber) {
        if (!phoneBook.containsKey(surname)) {
            phoneBook.put(surname, new HashSet<>());
        }
        phoneBook.get(surname).add(phoneNumber);


    }

    // Метод для поиска записи по фамилии
    public void get(String surname) {
        phoneBook.get(surname);
        System.out.println(surname + ":" + phoneBook.get(surname));
    }

}
