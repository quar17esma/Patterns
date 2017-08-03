package com.sergii.shutyi.structural;

public class DecoratorApp {
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

abstract class Decorator implements PrintInterface{
    PrintInterface component;

    public Decorator(PrintInterface component) {
        this.component = component;
    }

    public void print(){
        component.print();
    }
}

class QuotesDecorator extends Decorator {

    public QuotesDecorator(PrintInterface component) {
        super(component);
    }

    @Override
    public void print() {
        System.out.print("\"");
        super.print();
        System.out.print("\"");
    }
}

class LeftBracketDecorator extends Decorator {

    public LeftBracketDecorator(PrintInterface component) {
        super(component);
    }

    @Override
    public void print() {
        System.out.print("[");
        super.print();
    }
}

class RightBracketDecorator extends Decorator {

    public RightBracketDecorator(PrintInterface component) {
        super(component);
    }

    @Override
    public void print() {
        super.print();
        System.out.print("]");
    }
}