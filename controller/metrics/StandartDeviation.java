package controller.metrics;

import observer.CSVObservable;

public class StandartDeviation implements MetricCalculator {
    @Override
    public String getName() {
        return "Desvio Padrão";
    }

    @Override
    public double calculate(CSVObservable model, String column) {
        double variance = new Variance().calculate(model,column);
        return Math.sqrt(variance);
    }
}
