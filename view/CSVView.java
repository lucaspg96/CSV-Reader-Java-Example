package view;

import controller.metrics.Mean;
import controller.metrics.MetricCalculator;
import controller.metrics.StandartDeviation;
import controller.metrics.Variance;
import observer.CSVObservable;
import observer.CSVObserver;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVView extends JPanel implements CSVObserver {


    private JLabel file;
    private JScrollPane metricsTablePane, dataTablePane;
    private List<MetricCalculator> metrics;
    private JPanel parent;

    public CSVView(JPanel parent){
        super(new BorderLayout());

        file = new JLabel("Nenhum arquivo inserido");
        JPanel filePanel = new JPanel(new BorderLayout());
        filePanel.add(file, BorderLayout.CENTER);
        filePanel.setSize(800,50);
        filePanel.setAlignmentX(200);
        add(filePanel, BorderLayout.NORTH);

        metrics = new ArrayList<>();

        metrics.add(new Mean());
        metrics.add(new Variance());
        metrics.add(new StandartDeviation());

        this.parent = parent;

        setSize(700,500);
    }

    public void update(CSVObservable model){
//        parent.remove(this);


        if(metricsTablePane != null)
            remove(metricsTablePane);
        if(dataTablePane != null)
            remove(dataTablePane);

        file.setText("Arquivo: "+model.fileName());
        updateMetricsTable(model);
        updateDataTable(model);

//        parent.add(this, BorderLayout.CENTER);
    }

    private void updateDataTable(CSVObservable model){
        String[] columns = model.getHeader();

        Double[][] data = new Double[model.frameSize()][model.headerSize()];

        int i = 0;
        for(Double[] line : model){
            data[i] = line;
            i++;
        }

        JTable dataTable = new JTable(data,columns);
        dataTablePane = new JScrollPane(dataTable);
        dataTablePane.setPreferredSize(new Dimension(800,300));
        add(dataTablePane,BorderLayout.CENTER);

    }

    private void updateMetricsTable(CSVObservable model){
        String[] columns = new String[model.headerSize()+1];
        columns[0] = "Métricas";

        Object[][] data = new Object[metrics.size()][model.headerSize()+1];

        int i = 1;
        for(String h : model.getHeader()) {
            columns[i] = h;
            i++;
        }

        for(int m = 0; m < metrics.size(); m++) {
            for (int c = 0; c < columns.length; c++) {
                if (c == 0)
                    data[m][c] = metrics.get(m).getName();
                else
                    data[m][c] = metrics.get(m).calculate(model, columns[c]);
            }
        }


        JTable metricsTable = new JTable(data,columns);
        metricsTablePane = new JScrollPane(metricsTable);
        metricsTablePane.setPreferredSize(new Dimension(800,100));
        add(metricsTablePane,BorderLayout.SOUTH);
    }
}
