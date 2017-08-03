package com.sergii.shutyi.structural;

import java.util.*;

public class FlyweightApp {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        List<ShapeMy> shapes = new ArrayList<>();
        shapes.add(shapeFactory.getShapeMy("square"));
        shapes.add(shapeFactory.getShapeMy("circle"));
        shapes.add(shapeFactory.getShapeMy("circle"));
        shapes.add(shapeFactory.getShapeMy("point"));
        shapes.add(shapeFactory.getShapeMy("square"));
        shapes.add(shapeFactory.getShapeMy("circle"));

        Random random = new Random();
        for (ShapeMy shape:shapes) {
            int x = random.nextInt(100);
            int y = random.nextInt(100);
            shape.draw(x, y);
        }

    }
}

interface ShapeMy {
    void draw(int x, int y);
}

class PointMy implements ShapeMy {
    @Override
    public void draw(int x, int y) {
        System.out.println("(" + x + ", " + y + ") draw point ");
    }
}

class CircleMy implements ShapeMy {
    int r = 5;

    @Override
    public void draw(int x, int y) {
        System.out.println("(" + x + ", " + y + ") draw circle with radius " + r);
    }
}

class SquareMy implements ShapeMy {
    int a = 10;

    @Override
    public void draw(int x, int y) {
        System.out.println("(" + x + ", " + y + ") draw square with side " + a);
    }
}

class ShapeFactory {
    private static final Map<String, ShapeMy> shapes = new HashMap<>();

    public ShapeMy getShapeMy(String shapeName) {
        ShapeMy shape = shapes.get(shapeName);

        if (shape == null) {
            switch (shapeName) {
                case "circle":
                    shape = new CircleMy();
                    break;
                case "square":
                    shape = new SquareMy();
                    break;
                case  "point":
                    shape = new PointMy();
                    break;
            }
            shapes.put(shapeName, shape);
        }
        return shape;

    }
}