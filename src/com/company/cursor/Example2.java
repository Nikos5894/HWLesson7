package com.company.cursor;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/*
   Functionality so that we could take a few books in one day
*/
public class Example2 {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    static Scanner scanner = new Scanner(System.in);

    public static void startExample2() throws InterruptedException {

        List<String> books = new ArrayList<>();
        Collections.addAll(books, "Absalom, Absalom!", "After Many a Summer Dies the Swan", "Ah, Wilderness!", "All Passion Spent", "All the King's Men", "Alone on a Wide, Wide Sea");
        Map<LocalDate, List<String>> libraryReport = new TreeMap<>();

        takeBook(books, libraryReport, 0);
        takeBook(books, libraryReport, 0);
        takeBook(books, libraryReport, 4);
        takeBook(books, libraryReport, 1);
        takeBook(books, libraryReport, 2);
        takeBook(books, libraryReport, 52);

        showTakenBooks(libraryReport);

        printTakenBooks(libraryReport);

        showInRange(libraryReport);
    }

    //Taking a book and adding to the library report map
    public static void takeBook(List<String> b, Map<LocalDate, List<String>> lR, long minusDays) {
        if (b.isEmpty()) return;
        System.out.println(ANSI_RED + "\nChoose book, which you want to take: " + ANSI_RESET);
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

    //Show if we took books on a certain day
    public static void showTakenBooks(Map<LocalDate, List<String>> lR) {
        System.out.println(ANSI_RED + "\nEnter data for example 2020-10-19:" + ANSI_RESET);
        String date = scanner.next();
        String[] dayList = date.split("-");
        int year = Integer.valueOf(dayList[0]);
        int month = Integer.valueOf(dayList[1]);
        int day = Integer.valueOf(dayList[2]);
        if (lR.containsKey(LocalDate.of(year, month, day))) {
            System.out.println(lR.get(LocalDate.of(year, month, day)));
        } else {
            System.out.println("\nThere are no books for this date");
        }
    }

    //Print all taken books for 30 days
    public static void printTakenBooks(Map<LocalDate, List<String>> lR) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(ANSI_RED + "\nPrint taken books" + ANSI_RESET);
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
        TimeUnit.SECONDS.sleep(2);
    }

    public static void showInRange(Map<LocalDate, List<String>> lR) {
        System.out.println(ANSI_RED + "\nRange of dates of taken books" + ANSI_RESET);
        for (LocalDate key : lR.keySet()) {
            System.out.println(key);
        }

        List<String> listOfBooks = new ArrayList<>();
        System.out.println(ANSI_RED + "\nList of books for a whole range" + ANSI_RESET);
        for (List<String> value : lR.values()) {
            listOfBooks.add(String.valueOf(value));
        }
        System.out.println(listOfBooks);
    }
}
