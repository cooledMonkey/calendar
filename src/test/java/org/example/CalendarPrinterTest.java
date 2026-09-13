package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CalendarPrinterTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        // Перенаправляем System.out в наш поток для чтения текста
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        // Возвращаем стандартный вывод обратно
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Проверка смещения, если 1 января выпадает на воскресенье (2023 год)")
    void testFirstJanuaryIsSunday() {
        CalendarPrinter.printCalendarForYear(2023);
        String output = outputStreamCaptor.toString();

        // 1 января 2023 — воскресенье. Перед числом 1 должно быть 6 отступов (18 пробелов)
        String expectedJanuaryStart = "Пн Вт Ср Чт Пт Сб Вс \n                  1  ";
        assertTrue(output.contains(expectedJanuaryStart));
    }

    @Test
    @DisplayName("Проверка високосного 2024 года (число 29 встречается 12 раз)")
    void testLeapYear() {
        CalendarPrinter.printCalendarForYear(2024);
        String output = outputStreamCaptor.toString();

        // В високосном году 29-е число есть в каждом из 12 месяцев
        long countOf29 = countOccurrencesOf(output, "29");
        assertEquals(12, countOf29);
    }

    @Test
    @DisplayName("Проверка обычного 2026 года (число 29 встречается 11 раз)")
    void testNonLeapYear() {
        CalendarPrinter.printCalendarForYear(2026);
        String output = outputStreamCaptor.toString();

        // В обычном году в феврале нет 29 числа, поэтому всего их 11
        long countOf29 = countOccurrencesOf(output, "29");
        assertEquals(11, countOf29);
    }

    /**
     * Вспомогательный метод, который считает, сколько раз конкретное число
     * встретилось в выводе календаря.
     */
    private long countOccurrencesOf(String fullOutput, String targetDay) {
        return Arrays.stream(fullOutput.split("\\s+"))
                .filter(token -> token.equals(targetDay))
                .count();
    }
}