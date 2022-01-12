package com.company.cursor;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Example2 {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    static Scanner scanner = new Scanner(System.in);

    public static void startExample2() throws InterruptedException {

        List<String> books = new ArrayList<>();
        Collections.addAll(books, "Absalom, Absalom!", "After Many a Summer Dies the Swan", "Ah, Wilderness!", "All Passion Spent", "All the King's Men", "Alone on a Wide, Wide Sea");
        Map<LocalDate, List<String>> libraryReport = new HashMap<>();

        takeBook(books, libraryReport, 0);
        takeBook(books, libraryReport, 0);
        takeBook(books, libraryReport, 0);
        takeBook(books, libraryReport, 1);
        takeBook(books, libraryReport, 2);

        showTakenBooks(libraryReport);

        printTakenBooks(libraryReport);
    }

    public static void takeBook(List<String> b, Map<LocalDate, List<String>> lR, long minusDays) {
        if (b.isEmpty()) return;
        System.out.println(ANSI_RED + "Choose book, which you want to take: " + ANSI_RESET);
        int i = 0;
        for (String j : b) {
            System.out.println(i + " - " + j);
            i++;
        }
        int choose = scanner.nextInt();
        List<String> list = new ArrayList<>();
        if (lR.containsKey(LocalDate.now().minusDays(minusDays))) {
            list.addAll(lR.get(LocalDate.now().minusDays(minusDays)));
        }

        list.add(b.get(choose));
        lR.put(LocalDate.now().minusDays(minusDays), list);
        b.remove(choose);
        System.out.println(lR);
    }

    public static void showTakenBooks(Map<LocalDate, List<String>> lR) {
        System.out.println("Enter data for example 2020-10-19:");
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

    public static void printTakenBooks(Map<LocalDate, List<String>> lR) throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println(ANSI_RED + "Print taken books" + ANSI_RESET);
        TimeUnit.SECONDS.sleep(4);
        LocalDate date = LocalDate.now().minusMonths(1);
        do {
            if (lR.containsKey(date)) {
                List<String> list = lR.get(date);
                System.out.println(date + " - " + list.stream().count());
            } else {
                System.out.println(date + " - " + 0);

            }
            date = date.plusDays(1);
        } while (!date.equals(LocalDate.now().plusDays(1)));

    }
}
