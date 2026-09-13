package org.example;

/**
 * Класс для вывода календаря по году
 */
public class CalendarPrinter {

    private static final String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    private static final int[] countOfDaysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] daysOfWeek = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};

    /**
     * Выводит календарь на год
     */
    public static void printCalendarForYear(int year) {
        int dayOfWeek = getFistDayOfWeekOfYear(year);
        setDaysInFebruary(year);
        for (int month = 0; month < months.length; month++) {
            System.out.println("\n\n      " + months[month]);
            printDaysOfweek();
            printSpacesForFirstDayOfMonth(dayOfWeek);

            // Вывод чисел
            int day = 1;
            for (; day <= countOfDaysInMonths[month]; day++) {
                System.out.print(day + " ");
                if (day < 10) {
                    System.out.print(" ");
                }
                dayOfWeek++;
                if (dayOfWeek == 7) {
                    dayOfWeek = 0;
                } else if (dayOfWeek == 1) {
                    System.out.println();
                }
            }
        }
    }

    /**
     * Выводит пробелы чтобы первое число оказалось на нужном месте
     */
    private static void printSpacesForFirstDayOfMonth(int dayOfWeek) {
        if (dayOfWeek == 0) {
            for (int j = 0; j < 6; j++) {
                System.out.print("   ");
            }
        } else {
            for (int j = 0; j < dayOfWeek - 1; j++) {
                System.out.print("   ");
            }
        }
    }

    /**
     * Выводит дни недели
     */
    private static void printDaysOfweek() {
        for (String week : daysOfWeek) {
            System.out.print(week + " ");
        }
        System.out.print("\n");
    }

    /**
     * Определяет номер дня недели для первого дня года
     */
    private static int getFistDayOfWeekOfYear(int year) {
        if (year < 1600) {
            throw new IllegalArgumentException("Год должен быть больше или равен 1600");
        }
        return (year + (year - 1) / 4 + 6) % 7;
    }

    /**
     * Определяет количество дней в феврале
     */
    private static void setDaysInFebruary(int year) {
        if (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0)) {
            countOfDaysInMonths[1] = 28;
        } else {
            countOfDaysInMonths[1] = 29;
        }
    }
}
