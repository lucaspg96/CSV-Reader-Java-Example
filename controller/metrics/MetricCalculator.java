package controller.metrics;

import observer.CSVObservable;

public interface MetricCalculator {

    String getName();

    double calculate(CSVObservable model, String column);

}
