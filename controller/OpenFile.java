package controller;

import model.CSVModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OpenFile implements ActionListener {

    private MainWindow parent;

    private CSVModel model;

    public OpenFile(MainWindow p){
        parent = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser c = new JFileChooser();
        // Demonstrate "Open" dialog:
        int option = c.showOpenDialog(parent);
        if (option == JFileChooser.APPROVE_OPTION) {
//            System.out.println("Loading file "+c.getSelectedFile().getName());
//            System.out.println("File at directory "+c.getCurrentDirectory().toString());

            parent.updateFile(c.getSelectedFile());
        }
        if (option == JFileChooser.CANCEL_OPTION) {
//            System.out.println("No file choosen");
        }
    }
}
