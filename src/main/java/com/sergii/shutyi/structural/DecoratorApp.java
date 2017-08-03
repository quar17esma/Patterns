package com.sergii.shutyi.structural;

public class Decorator {
    public static void main(String[] args) {
        PrintInterface printer = new QuotesDecorator(new LeftBracketDecorator(new RightBracketDecorator(new Printer("hello"))));
        printer.print();
    }
}

interface PrintInterface {
    void print();
}

class Printer implements PrintInterface {
    String value;

    public Printer(String value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.print(value);
    }
}

abstract class 

class QuotesDecorator implements PrintInterface {
    PrintInterface component;

    public QuotesDecorator(PrintInterface component) {
        this.component = component;
    }

    @Override
    public void print() {
        System.out.print("\"");
        component.print();
        System.out.print("\"");
    }
}

class LeftBracketDecorator implements PrintInterface {
    PrintInterface component;

    public LeftBracketDecorator(PrintInterface component) {
        this.component = component;
    }

    @Override
    public void print() {
        System.out.print("[");
        component.print();
    }
}

class RightBracketDecorator implements PrintInterface {
    PrintInterface component;

    public RightBracketDecorator(PrintInterface component) {
        this.component = component;
    }

    @Override
    public void print() {
        component.print();
        System.out.print("]");
    }
}