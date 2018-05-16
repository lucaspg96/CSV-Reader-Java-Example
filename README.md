# CSV-Reader-Java-Example
Repository used to hold an MVC example to Object-Oriented Discipline at Federal University of Ceará


## Design Patterns

At this project I used 3 patterns:

### Model, View, Controller (MVC)
The MVC pattern is used to define the architecture of this project. We have the *MainWindow* as the **controller**, that will create the view and the model and update the model; we have the *CSVModel* as our **model**, that holds the data of a numeric CSV and the *CSVView* as the **view** of the project. This view extends JPanel class from javax.swing, so the controller can just add it to the main interface.

### Observer
I used the Observer pattern to deal with the View/Model **comunication**. Everytime that the Model (**observable**) change, it notifies all the Views (**observer**) about the change and let them update the interface. At this implementation, we have just 1 (one) View.

### Strategy
To deal with the metrics calculation, I used an interface named *MetricCalculator* and each of its implementations is a *Strategy*. The project contains 3 (three) strategies to calculate metrics: Mean, Variance, Standart Deviation.

## Class Diagram

This is the *class diagram* of this project:

![Class Diagram](https://github.com/lucaspg96/CSV-Reader-Java-Example/raw/master/Class%20Diagram.png)
