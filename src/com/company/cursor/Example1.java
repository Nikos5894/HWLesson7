package com.company.cursor;

import java.time.LocalDate;
import java.util.*;

public class Example1 {
    static Scanner scanner = new Scanner(System.in);

    public static void startExample1() {

        List<String> books = new ArrayList<>();
        Collections.addAll(books, "Absalom, Absalom!", "After Many a Summer Dies the Swan", "Ah, Wilderness!", "All Passion Spent", "All the King's Men", "Alone on a Wide, Wide Sea");
        Map<LocalDate, String> libraryReport = new HashMap<>();
        takeBook(books, libraryReport);
        takeBook(books, libraryReport);

        System.out.println(books);
        System.out.println(libraryReport);
    }

    public static void takeBook(List<String> b, Map<LocalDate, String> lR) {
        System.out.println("Choose book, which you want to take: ");
        int i = 0;
        for (String j : b) {
            System.out.println(i + " - " + j);
            i++;
        }
        int choose = scanner.nextInt();
        if (!lR.containsKey(LocalDate.now())) {
            lR.put(LocalDate.now(), b.get(choose));
            b.remove(choose);
        }
    }

    public static void showTakenBooks(Map<LocalDate, List<String>> lR) {
        System.out.println("Enter data for example 2020-10-19:");
        Scanner scanner1 = new Scanner(System.in);
        String date = scanner.next();
        String[] dayList = date.split("-");
        int year = Integer.valueOf(dayList[0]);
        int month = Integer.valueOf(dayList[1]);
        int day = Integer.valueOf(dayList[2]);
        if (lR.containsKey(LocalDate.of(year, month, day))) {
            System.out.println(lR.get(LocalDate.of(year, month, day)));
        } else {
            System.out.println("There are no books for this date");
        }
    }
}
