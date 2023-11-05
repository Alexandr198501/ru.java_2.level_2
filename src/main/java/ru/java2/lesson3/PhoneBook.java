package ru.java2.lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneBook {

    // Создаю мапу.
    private Map<String, Set<String>> phoneBook = new HashMap<>();

    // Метод для добавления записи в справочник.
    public void add(String surname, String phoneNumber) {
        if (!phoneBook.containsKey(surname)) {
            phoneBook.put(surname, new HashSet<>());
        }
        phoneBook.get(surname).add(phoneNumber);


    }

    // Метод для поиска записи по фамилии.
    public Set<String> get(String surname) {
        return phoneBook.get(surname);
    }

}
