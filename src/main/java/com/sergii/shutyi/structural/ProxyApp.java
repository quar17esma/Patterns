package com.sergii.shutyi.structural;

public class ProxyApp {
    public static void main(String[] args) {
        Image image = new RealImage("D:/images/my.jpg");
        image.display();

        image = new Proxy("D:/images/my.jpg");
        image.display();
    }
}

interface Image{
    void display();
}

class RealImage implements Image{
    String file;

    public RealImage(String file) {
        this.file = file;
        load();
    }

    public void load(){
        System.out.println("Loading " + file);
    }

    @Override
    public void display() {
        System.out.println("Watching file");
    }
}

class Proxy implements Image{
    String file;
    Image image;

    public Proxy(String file) {
        this.file = file;
    }

    @Override
    public void display() {
        if (image == null){
            image = new RealImage(file);
        }
        image.display();
    }
}