package com.sergii.shutyi.behavioral;

public class ChainOfResponsibility {
    public static void main(String[] args) {
        Logger logger = new SMSLogger(Level.ERROR);
        Logger fileLogger = new FileLogger(Level.DEBUG);
        logger.setNext(fileLogger);
        Logger emailLogger = new EmailLogger(Level.INFO);
        fileLogger.setNext(emailLogger);

        logger.writeMessage("All good", Level.INFO);
        logger.writeMessage("debug is going", Level.DEBUG);
        logger.writeMessage("system error", Level.ERROR);
    }
}

class Level {
    public static final int ERROR = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
}

abstract class Logger {
    int priority;
    Logger next;

    public Logger(int priority) {
        this.priority = priority;
    }

    public void writeMessage(String message, int level) {
        if (level <= priority) {
            write(message);
        }
        if (next != null) {
            next.writeMessage(message, level);
        }
    }
    abstract void write(String message);

    public void setNext(Logger next) {
        this.next = next;
    }
}

class SMSLogger extends Logger {

    public SMSLogger(int priority) {
        super(priority);
    }

    @Override
    public void write(String message) {
        System.out.println("SMS: " + message);
    }
}

class FileLogger extends Logger {

    public FileLogger(int priority) {
        super(priority);
    }

    @Override
    public void write(String message) {
        System.out.println("Write to file: " + message);
    }
}

class EmailLogger extends Logger {

    public EmailLogger(int priority) {
        super(priority);
    }

    @Override
    public void write(String message) {
        System.out.println("Send to email: " + message);
    }
}