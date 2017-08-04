package com.sergii.shutyi.behavioral.state;

public class StateAdvanced {
    public static void main(String[] args) {
        Human human = new Human();
        human.setState(new Work());
        for (int i = 0; i < 10; i++) {
            human.doSomething();
        }
    }
}

class Human{
    private Activity state;
    void setState(Activity s){
        this.state = s;
    }
    public void doSomething(){
        state.doSomething(this);
    }
}

interface Activity{
    void doSomething(Human context);
}

class Work implements Activity{
    @Override
    public void doSomething(Human context) {
        System.out.println("Work!!!");
        context.setState(new Weekend());
    }
}

class Weekend implements Activity{
    private int count = 0;
    @Override
    public void doSomething(Human context) {
        if (count < 3){
            System.out.println("Rest(Zzz..)");
            count++;
        } else {
            context.setState(new Work());
        }
    }
}