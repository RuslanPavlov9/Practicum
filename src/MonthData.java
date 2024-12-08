import java.util.ArrayList;

public class MonthData {
    private static final int MAX_DAYS_IN_MONTH = 30;

    ArrayList<Integer> days = new ArrayList<>(MAX_DAYS_IN_MONTH);

    MonthData() {
        for (int i = 0; i < MAX_DAYS_IN_MONTH; i++) {
            days.add(0);
        }
    }

    void printDaysAndStepsFromMonth() {
        for (int i = 0; i < days.size(); i++) {
            System.out.printf(("%d день: %d%n"), (i + 1), days.get(i));
        }
        //return days.stream().mapToInt(Integer::intValue).sum(); // преобразуем массив в стрим (список элементов), приводит все к примитивному типу, суммируем элементы списка.
    }

    int sumStepsFromMonth() {
       return days.stream().mapToInt(Integer::intValue).sum();
    }

    int maxSteps() {
        return days.stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    int bestSeries(int goalByStepsPerDay) {
        int currentSeries = 0;
        int finalSeries = 0;
        for (int steps : days) {
            if (steps >= goalByStepsPerDay) {
                currentSeries++;
                if (currentSeries > finalSeries) {
                    finalSeries = currentSeries;
                }
            } else {
                currentSeries = 0;
            }
        }
        return finalSeries;
    }

}
