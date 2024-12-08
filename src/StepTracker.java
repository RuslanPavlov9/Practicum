import java.util.ArrayList;
import java.util.Scanner;

public class StepTracker {
    //В нём будет реализована логика по сохранению и изменению количества шагов. Также в нём будет выводиться статистика.

    private static final int MONTHS_IN_YEAR = 12;
    private static final int MAX_DAYS_IN_MONTH = 30;
    private static final int DEFAULT_GOAL_STEPS = 10_000;

    private final Scanner scanner;

    private final ArrayList<MonthData> monthToData = new ArrayList<>(MONTHS_IN_YEAR);

    private final Converter converter  = new Converter();
    private int goalByStepsPerDay = DEFAULT_GOAL_STEPS;


    StepTracker(Scanner scan) {  // конструктор, инициализация ArrayList
        this.scanner = scan;
        for (int i = 0; i < MONTHS_IN_YEAR; i++) {
            monthToData.add(new MonthData());
        }
    }

    void addNewNumberStepsPerDay() {

        System.out.println("Введите номер месяца:");
        int month = getValidatedMonth();
        if (month == -1) return;

        System.out.println("Введите день от 1 до 30 (включительно):");
        int day = getValidatedDay();
        if (day == -1) return;

        System.out.println("Введите количество шагов:");
        int steps = getValidatedSteps();
        if (steps == -1) return;

        MonthData monthData = monthToData.get(month-1);
        monthData.days.set(day-1,steps);

    }

    void changeStepGoal() {
        System.out.println("Введите новую цель по количеству шагов в день");
        int newDailyGoal = scanner.nextInt();
        if (newDailyGoal <= 0) {
            System.out.println("Цель не может быть ниже или равно нулю. Введите положительное число");
        }
        else {
            goalByStepsPerDay = newDailyGoal;
            System.out.println("Новая цель успешно сохранена! Теперь ежедневная цель составляет " + goalByStepsPerDay + " шагов.");
        }
    }
    private int getValidatedMonth() {
        int month = scanner.nextInt();
        if (month < 1 || month > MONTHS_IN_YEAR) {
            System.out.println("Указан некорректный номер месяца. Введите номер месяца от 1 до 12.");
            return -1;
        }
        return month;
    }

    private int getValidatedDay() {
        int day = scanner.nextInt();
        if (day < 1 || day > MAX_DAYS_IN_MONTH) {
            System.out.println("Указан некорректный номер дня. Введите номер дня от 1 до 30");
            return -1;
        }
        return day;
    }

    private int getValidatedSteps() {
        int steps = scanner.nextInt();
        if (steps < 0) {
            System.out.println("Указано неверное значение. Количество шагов не может быть отрицательным.");
            return -1;
        }
        return steps;
    }

    void printStats() {
        System.out.println("Введите номер месяца");
        int month = getValidatedMonth();
        if (month == -1) return;

        MonthData monthData = monthToData.get(month - 1);
        int sumSteps = monthData.sumStepsFromMonth();

        System.out.println("Ваша статистика за выбранный месяц:");
        monthData.printDaysAndStepsFromMonth();
        System.out.println("Всего за месяц вы прошли " + sumSteps + " шагов");
        System.out.println("Максимальное пройденное количество шагов в день: " + monthData.maxSteps());
        System.out.println("Среднее количество пройденных шагов в день за месяц: " + (sumSteps / monthData.days.size()));
        System.out.println("Пройденная за месяц дистанция составила " + converter.convertToKm(sumSteps) + " километров");
        System.out.println("Всего за месяц при ходьбе вы сожгли " + converter.convertStepsToKilocalories(sumSteps) + " килокалорий");
        System.out.println("Лучшая серия по достижению цели составила " + monthData.bestSeries(goalByStepsPerDay) + " дней подряд");
        System.out.println();
    }


}
