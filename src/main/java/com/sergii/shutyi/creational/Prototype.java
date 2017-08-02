package com.sergii.shutyi.creational;

public class Prototype {
    public static void main(String[] args) {
        Human original = new Human(18, "Vasya");
        System.out.println(original);

        Human copy = (Human) original.copy();
        System.out.println(copy);

        HumanFactory factory = new HumanFactory(copy);
        Human human1 = factory.makeCopy();
        System.out.println(human1);

        factory.setPrototype(new Human(30, "Валерия"));
        Human human2 = factory.makeCopy();
        System.out.println(human2);
    }
}

interface Copyable{
    Object copy();
}

class Human implements Copyable{
    int age;
    String name;

    public Human(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public Object copy() {
        Human copy = new Human(age, name);
        return copy;
    }

    @Override
    public String toString() {
        return "Human{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

class HumanFactory {
    Human human;

    public HumanFactory(Human human){
        setPrototype(human);
    }

    public void setPrototype(Human human){
        this.human = human;
    }

    Human makeCopy(){
        return (Human) human.copy();
    }
}