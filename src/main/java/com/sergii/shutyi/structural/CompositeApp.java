package com.sergii.shutyi.structural;

import java.util.ArrayList;
import java.util.List;

public class CompositeApp {
    public static void main(String[] args) {
        Shape shape = new MySquare();
        Shape shape1 = new MySquare();
        Shape shape2 = new MyTriangle();

        Shape shape3 = new MyTriangle();
        Shape shape4 = new MyCircle();

        Composite composite = new Composite();
        Composite composite1 = new Composite();
        Composite composite2 = new Composite();

        composite1.addComponent(shape);
        composite1.addComponent(shape1);
        composite1.addComponent(shape2);

        composite2.addComponent(shape3);
        composite2.addComponent(shape4);

        composite.addComponent(composite1);
        composite.addComponent(composite2);

        composite.draw();
    }
}

interface Shape{
    void draw();
}

class MySquare implements Shape{
    @Override
    public void draw() {
        System.out.println("I am Square");
    }
}

class MyTriangle implements Shape{
    @Override
    public void draw() {
        System.out.println("I am Triangle");
    }
}

class MyCircle implements Shape{
    @Override
    public void draw() {
        System.out.println("I am circle");
    }
}

class Composite implements Shape{
    private List<Shape> components = new ArrayList<>();

    void addComponent(Shape component){
        components.add(component);
    }

    void removeComponent(Shape shape){
        components.remove(shape);
    }
    @Override
    public void draw() {
        for (Shape shape:components) {
            shape.draw();
        }
    }
}