package controller.metrics;

import observer.CSVObservable;

import java.util.Arrays;

public class Variance implements MetricCalculator{

    @Override
    public String getName() {
        return "Variância";
    }

    @Override
    public double calculate(CSVObservable model, String column) {
        double mean = model.mean(column);
        int index = Arrays.asList(model.getHeader()).indexOf(column);

        double variance = 0;
        for(Double[] line : model){
            variance += Math.pow(line[index] - mean, 2);
        }


        return variance/model.frameSize();
    }
}
