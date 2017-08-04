package com.sergii.shutyi.behavioral.state;

public class StateApp {
    public static void main(String[] args) {
        Station dfm = new RadioDFM();
        Radio radio = new Radio();
        radio.setStation(dfm);

        for (int i = 0; i < 10; i++) {
            radio.play();
            radio.nextStation();
        }
    }
}

interface Station{
    void play();
}

class Radio7 implements Station{
    @Override
    public void play() {
        System.out.println("Radio 7 ... ");
    }
}

class RadioDFM implements Station{
    @Override
    public void play() {
        System.out.println("Radio DFM ... ");
    }
}

class VestiFM implements Station{
    @Override
    public void play() {
        System.out.println("Vesti FM ... ");
    }
}

class Radio{
    Station station;
    void setStation(Station station) {
        this.station = station;
    }
    void nextStation(){
        if (station instanceof Radio7){
            setStation(new RadioDFM());
        } else if (station instanceof RadioDFM){
            setStation(new VestiFM());
        } else if (station instanceof VestiFM){
            setStation(new Radio7());
        }
    }
    void play(){
        station.play();
    }
}