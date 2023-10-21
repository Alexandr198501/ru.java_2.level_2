package ru.java2.lesson2;

import java.util.Arrays;

public class HomeWork2 {

    /*
1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При
подаче массива другого размера необходимо бросить исключение MyArraySizeException.
2. Далее метод должен пройтись по всем элементам массива, преобразовать в int и
просуммировать. Если в каком-то элементе массива преобразование не удалось (например, в
ячейке лежит символ или текст вместо числа), должно быть брошено исключение
MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
3. В методе main() вызвать полученный метод, обработать возможные исключения
MyArraySizeException и MyArrayDataException и вывести результат расчета (сумму элементов,
при условии, что подали на вход корректный массив).
Заметка: Для преобразования строки к числу используйте статический метод класса Integer:
Integer.parseInt(сюда_подать_строку);
Заметка: Если Java не сможет преобразовать входную строку (в строке число криво написано), то
будет сгенерировано исключение NumberFormatException.
     */
    public static void main(String[] args) {

        String[][] array = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] arrayNotFourRows = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"},
                {"13", "14", "15", "16"}
        };

        String[][] arrayNotFourColomns = {
                {"1", "2", "3", "5", "6"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] arrayWithText = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"hi", "14", "15", "16"}
        };


        try {
            arrayProcessing(arrayWithText);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }


    }

    public static void arrayProcessing(String[][] arr) {
        if (arr.length != 4 | arr[0].length != 4 | arr[1].length != 4 | arr[2].length != 4 | arr[3].length != 4) {
            throw new MyArraySizeException("[ERROR] Размер массива не равен 4х4");
        }
        int sum = 0;
        int stringToInt;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    stringToInt = Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Невозможно преобразовать к целому числу значение из ячейки " + i + "|" + j);
                }
                sum += stringToInt;
            }
        }
        System.out.println("Сумма элементов двумерного массива = " + sum);
    }


}
