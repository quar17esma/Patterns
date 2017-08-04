package com.sergii.shutyi.behavioral.observer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ObserverApp {
    public static void main(String[] args) {
        MeteoStation station = new MeteoStation();

        station.addObserver(new ConsoleObserver());
        station.addObserver(new FileObserver());

        station.setMeasurements(30, 765);
        station.setMeasurements(-5, 755);
    }
}

interface Observed {
    void addObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}

class MeteoStation implements Observed {
    int temperature;
    int pressure;
    List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.handleEvent(temperature, pressure);
        }
    }

    public void setMeasurements(int temperature, int pressure) {
        this.temperature = temperature;
        this.pressure = pressure;
        notifyObservers();
    }
}

interface Observer {
    void handleEvent(int temperature, int pressure);
}

class ConsoleObserver implements Observer {
    @Override
    public void handleEvent(int temperature, int pressure) {
        System.out.println("The weather changed. Temperature: "
                + temperature + ", Pressure: " + pressure);
    }
}

class FileObserver implements Observer{
    File file;
    @Override
    public void handleEvent(int temperature, int pressure) {
        try {
            file = File.createTempFile("TempPressure", "_txt");
            PrintWriter pw = new PrintWriter(file);
            pw.print("The weather changed. Temperature: "
                    + temperature + ", Pressure: " + pressure);
            pw.println();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}