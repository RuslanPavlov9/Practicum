public class Converter {
    private static final int smPerStep = 75;
    private static  final int calPerStep = 50;
    private static final int smInKm = 100_000;
    private static final int calInKcal = 1_000;

    int convertToKm(int steps) {
        return steps * smPerStep / smInKm;
    }

    int convertStepsToKilocalories(int steps) {
        return steps * calPerStep / calInKcal;
    }

}
