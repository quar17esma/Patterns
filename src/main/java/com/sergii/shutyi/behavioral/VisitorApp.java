package com.sergii.shutyi.behavioral;

public class VisitorApp {
    public static void main(String[] args) {
//        Element body = new BodyElement();
//        Element engine = new EngineElement();
//        Visitor hooligan = new HooliganVisitor();
//
//        body.accept(hooligan);
//        engine.accept(hooligan);

        Element car = new CarElement();
        car.accept(new HooliganVisitor());
        System.out.println();
        car.accept(new MechanicVisitor());
    }
}

interface Visitor{
    void visit(BodyElement bodyElement);
    void visit(EngineElement engineElement);
    void visit(CarElement carElement);
    void visit(WheelElement wheelElement);
}

interface Element{
    void accept(Visitor visitor);
}

class BodyElement implements Element{
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class EngineElement implements Element{
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class CarElement implements Element{
    Element[] elements;
    public CarElement(){
        this.elements = new Element[]{
                new WheelElement("front left"), new WheelElement("front right"),
                new WheelElement("back right"), new WheelElement("back left"),
                new BodyElement(), new EngineElement()};
    }
    @Override
    public void accept(Visitor visitor) {
        for (Element elem:elements) {
            elem.accept(visitor);
        }
    }
}
class WheelElement implements Element{
    private String name;

    public WheelElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class HooliganVisitor implements Visitor{
    @Override
    public void visit(BodyElement bodyElement) {
        System.out.println("hits the body");
    }

    @Override
    public void visit(EngineElement engineElement) {
        System.out.println("starts engine");
    }

    @Override
    public void visit(CarElement carElement) {
        System.out.println("smokes inside the car");
    }

    @Override
    public void visit(WheelElement wheelElement) {
        System.out.println("Hits " + wheelElement.getName() + " wheel");
    }
}

class MechanicVisitor implements Visitor{
    @Override
    public void visit(BodyElement bodyElement) {
        System.out.println("wash body");
    }

    @Override
    public void visit(EngineElement engineElement) {
        System.out.println("check engine");
    }

    @Override
    public void visit(CarElement carElement) {
        System.out.println("Check the car view");
    }

    @Override
    public void visit(WheelElement wheelElement) {
        System.out.println("Pumped " + wheelElement.getName() + " wheel");
    }
}