package controller;

import model.CSVModel;
import view.CSVView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {

    private CSVModel model;
    private CSVView view;

    public MainWindow(){
        JPanel p = new JPanel(new BorderLayout());
        setResizable(false);
        JButton loadButton = new JButton("Load File");
        loadButton.addActionListener(new OpenFile(this));
        p.add(loadButton, BorderLayout.NORTH);

        view = new CSVView(p);
        p.add(view);

        Container cp = getContentPane();
        cp.add(p, BorderLayout.CENTER);

        try {
            model = new CSVModel("resources/teste.csv");
            model.addObserver(view);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    void updateFile(File file){
        try{
            model.update(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
