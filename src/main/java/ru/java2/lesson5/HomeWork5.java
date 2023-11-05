package ru.java2.lesson5;

/*
Необходимо написать два метода, которые делают следующее:
+1) Создают одномерный длинный массив, например:
static final int SIZE = 10_000_000;
static final int HALF = size / 2;
float[] arr = new float[size];
+2) Заполняют этот массив единицами.
+3) Засекают время выполнения: long a = System.currentTimeMillis().
+4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
Math.cos(0.4f + i / 2));
+5) Проверяется время окончания метода System.currentTimeMillis().
+6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
Отличие первого метода от второго:
● Первый просто бежит по массиву и вычисляет значения.
● Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и
потом склеивает эти массивы обратно в один.
Пример деления одного массива на два:
● System.arraycopy(arr, 0, a1, 0, h);
● System.arraycopy(arr, h, a2, 0, h).
Пример обратной склейки:
● System.arraycopy(a1, 0, arr, 0, h);
● System.arraycopy(a2, 0, arr, h, h).

 */

import java.util.Arrays;

public class HomeWork5 {
    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;
    static float[] arr = new float[SIZE];
    static float[] secondArr = new float[SIZE];


    public static void main(String[] args) throws Exception {
        firstMethod();
        secondMethod();
    }

    public static void firstMethod() {
        long startTime1 = System.currentTimeMillis();  //  Засекаем время начала работы 1-го метода

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }


        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        //  Считаем время за которое отработал первый метод
        System.out.println("Первый метод отработал за " + (System.currentTimeMillis() - startTime1) + " миллисекунд");
    }

    public static void secondMethod() throws Exception {
        long startTime2 = System.currentTimeMillis();  // Засекаем время начала работы 2-го метода

        for (int i = 0; i < secondArr.length; i++) {
            secondArr[i] = 1.0f;
        }

        //  Разбиваем массив secondArray на 2 половины
        float[] leftHalf = new float[HALF];
        float[] rightHalf = new float[HALF];
        System.arraycopy(secondArr, 0, leftHalf, 0, HALF);
        System.arraycopy(secondArr, HALF, rightHalf, 0, HALF);
        //  Создаем 2 потока для обработки двух половин массива
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < leftHalf.length; i++) {
                leftHalf[i] = (float) (leftHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                        Math.cos(0.4f + i / 2));
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < rightHalf.length; i++) {
                rightHalf[i] = (float) (rightHalf[i] * Math.sin(0.2f + (HALF + i) / 5) * Math.cos(0.2f + (HALF + i) / 5) *
                        Math.cos(0.4f + (HALF + i) / 2)); // Формулу доработал, т.к. вычисление завязано на индексе
            }
        });
        //  Запускаем потоки
        thread1.start();
        thread2.start();
        //  Ожидаем окончания работы потоков
        thread1.join();
        thread2.join();

        //  Склеиваем 2 половинки в 1 массив
        float[] mergedArray = new float[SIZE];
        System.arraycopy(leftHalf, 0, mergedArray, 0, HALF);
        System.arraycopy(rightHalf, 0, mergedArray, HALF, HALF);
        //  Считаем время за которое отработал второй метод
        System.out.println("Второй метод отработал за " + (System.currentTimeMillis() - startTime2) + " миллисекунд");
        // Проверка, что на выходе получились одинаковые массивы в обоих методах.
        System.out.println(Arrays.toString(arr).equals(Arrays.toString(mergedArray)));

    }

}
