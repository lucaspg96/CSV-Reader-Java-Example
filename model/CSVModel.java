package model;

import observer.CSVObservable;
import observer.CSVObserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.*;


public class CSVModel implements CSVObservable {

    private String[] header;
    private Map<String,List<Double>> frame;
    private List<CSVObserver> observers;

    private String fileName;

    public CSVModel(String filePath) throws IOException {
        constructFrame(new File(filePath));
    }

    public CSVModel(File file) throws IOException {
        constructFrame(file);
    }

    private void constructFrame(File file) throws IOException{
        fileName = file.getName();

        BufferedReader reader = new BufferedReader(
                new FileReader(file));

        header = reader.readLine().split(",");

        String line = reader.readLine();

        frame = new HashMap<>();
        while(line != null){
            String[] values = line.split(",");
            if(values.length != header.length) continue;


            for(int k = 0; k < header.length; k++) {
                List<Double> list = frame.getOrDefault(header[k],new ArrayList<>());
                list.add(Double.parseDouble(values[k]));
                frame.put(header[k], list);
            }

            line = reader.readLine();
        }
        notifyObservers();
    }

    public void update(String filePath) throws IOException{
        constructFrame(new File(filePath));
    }

    public void update(File file) throws IOException{
        constructFrame(file);
    }

    @Override
    public int frameSize(){
        return frame.getOrDefault(header[0], new ArrayList<>()).size();
    }

    @Override
    public int headerSize(){
        return header.length;
    }

    @Override
    public String[] getHeader() {
        return header;
    }

    public String toString(){
        List<String> lines = new ArrayList<>();
        lines.add(String.join(",",header));

        for(int i = 0; i < frameSize(); i++){
            List<String> line = new ArrayList<>();

            for(String k : header) line.add(frame.get(k).get(i).toString());

            lines.add(String.join(",",line));
        }

        return String.join("\n",lines);

    }

    @Override
    public List<Double> getColumn(String k){
        return frame.getOrDefault(k, new ArrayList<>());
    }

    @Override
    public Double mean(String column){
        List<Double> values = getColumn(column);

        double sum = 0;

        for(Double value : values) sum += value;

        return sum/frameSize();
    }

    @Override
    public Map<String,Double> means(){
        Map<String,Double> means = new HashMap<>();

        for(String h : header) means.put(h,mean(h));

        return means;
    }

    @Override
    public void notifyObservers(){
//        System.out.println(this);

        if(observers != null)
            for(CSVObserver o : observers) o.update(this);
    }

    @Override
    public void addObserver(CSVObserver o){
        if(observers == null) observers = new ArrayList<>();

        observers.add(o);

        o.update(this);
    }

    @Override
    public String fileName(){
        return fileName;
    }

    @Override
    public Iterator<Double[]> iterator() {
        return new RowIterator();
    }

    private class RowIterator implements Iterator<Double[]>{

        int i = 0;

        @Override
        public boolean hasNext() {
            return i<frameSize();
        }

        @Override
        public Double[] next() {
            Double[] line = new Double[headerSize()];

            for(int j = 0; j < headerSize(); j++)
                line[j] = frame.get(header[j]).get(i);

            i++;
            return line;
        }
    }

    public static void main(String[] args){
        try {
            CSVModel model = new CSVModel("resources/teste.csv");
            System.out.println(model);

            System.out.println(model.means());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
