package controller.metrics;

import observer.CSVObservable;

public class Mean implements MetricCalculator {

    @Override
    public String getName() {
        return "Média";
    }

    @Override
    public double calculate(CSVObservable model, String column) {
        return model.mean(column);
    }
}
