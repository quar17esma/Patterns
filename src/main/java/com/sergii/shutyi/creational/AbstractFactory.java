package com.sergii.shutyi.creational;


public class AbstractFactory {
    public static void main(String[] args) {
        DeviceFactory factory = getFactoryByCountryCode("RU");
//        DeviceFactory factory = getFactoryByCountryCode("EN");
        Mouse mouse = factory.getMouse();
        Keyboard keyboard = factory.getKeyboard();
        Touchpad touchpad = factory.getTouchpad();
        mouse.click();
        keyboard.print();
        keyboard.println();
        touchpad.track(10, 35);
    }

    private static DeviceFactory getFactoryByCountryCode(String lang) {
        switch (lang) {
            case "RU":
                return new RuDeviceFactory();
            case "EN":
                return new EnDeviceFactory();
            default:
                throw new RuntimeException("Unsupported country code: " + lang);
        }
    }
}

interface Mouse {
    void click();

    void dbclick();

    void scroll(int direction);
}

interface Keyboard {
    void print();

    void println();
}

interface Touchpad {
    void track(int deltaX, int deltaY);
}

interface DeviceFactory {
    Mouse getMouse();

    Keyboard getKeyboard();

    Touchpad getTouchpad();
}

class RuMouse implements Mouse {
    public void click() {
        System.out.println("Щелчок мишью");
    }

    public void dbclick() {
        System.out.println("Двойной щелчок мишью");
    }

    public void scroll(int direction) {
        if (direction > 0) {
            System.out.println("Скролим вверх");
        } else if (direction < 0) {
            System.out.println("Скролим вниз");
        } else {
            System.out.println("Не скролим");
        }
    }
}

class RuKeyboard implements Keyboard {
    public void print() {
        System.out.println("Печатаем строку");
    }

    public void println() {
        System.out.println("Печатаем строку с переводом строки");
    }
}

class RuTouchpad implements Touchpad {
    public void track(int deltaX, int deltaY) {
        int s = (int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        System.out.println("Передвинулись на " + s + " пикселей");
    }
}

class EnMouse implements Mouse {
    public void click() {
        System.out.println("Mouse click");
    }

    public void dbclick() {
        System.out.println("Mouse double click");
    }

    public void scroll(int direction) {
        if (direction > 0) {
            System.out.println("Scroll Up");
        } else if (direction < 0) {
            System.out.println("Scroll Down");
        } else {
            System.out.println("No Scroll");
        }
    }
}

class EnKeyboard implements Keyboard {
    public void print() {
        System.out.println("Print");
    }

    public void println() {
        System.out.println("Print Line");
    }
}

class EnTouchpad implements Touchpad {
    public void track(int deltaX, int deltaY) {
        int s = (int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        System.out.println("Moved " + s + " pixels");
    }
}

class RuDeviceFactory implements DeviceFactory {
    public Mouse getMouse() {
        return new RuMouse();
    }

    public Keyboard getKeyboard() {
        return new RuKeyboard();
    }

    public Touchpad getTouchpad() {
        return new RuTouchpad();
    }
}

class EnDeviceFactory implements DeviceFactory {
    public Mouse getMouse() {
        return new EnMouse();
    }

    public Keyboard getKeyboard() {
        return new EnKeyboard();
    }

    public Touchpad getTouchpad() {
        return new EnTouchpad();
    }
}