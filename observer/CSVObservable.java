package observer;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface CSVObservable extends Iterable<Double[]> {
    void addObserver(CSVObserver o);

    String fileName();

    void notifyObservers();

    int headerSize();

    int frameSize();

    String[] getHeader();

    Double mean(String column);

    Map<String,Double> means();

    List<Double> getColumn(String k);
}
