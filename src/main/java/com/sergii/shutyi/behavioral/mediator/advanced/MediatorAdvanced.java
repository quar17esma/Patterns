package com.sergii.shutyi.behavioral.mediator.advanced;

import java.util.ArrayList;
import java.util.List;

public class MediatorAdvanced {
    public static void main(String[] args) {
        TextChat chat = new TextChat();

        User admin = new Admin(chat, "John Smith");
        User u1 = new SimpleUser(chat,"Ivan");
        User u2 = new SimpleUser(chat,"Vova");
        User u3 = new SimpleUser(chat,"Alexander");
        u2.setIsEnable(false);

        chat.setAdmin(admin);
        chat.addUser(u1);
        chat.addUser(u2);
        chat.addUser(u3);

        u1.sendMessage("Hello");
    }
}

abstract class User{
    Chat chat;
    String name;
    boolean isEnable = true;

    public boolean isEnable() {
        return isEnable;
    }
    public void setIsEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }
    public User(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void sendMessage(String message){
        chat.sendMessage(message, this);
    }
    abstract void getMessage(String message);

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Admin extends User{
    public Admin(Chat chat, String name) {
        super(chat, name);
    }

    @Override
    void getMessage(String message) {
        System.out.println("Admin " + getName() + " gets message " + message);
    }
}

class SimpleUser extends User{
    public SimpleUser(Chat chat, String name) {
        super(chat, name);
    }

    @Override
    void getMessage(String message) {
        System.out.println("SimpleUser " + getName() + " gets message " + message);
    }
}

interface Chat{
    void sendMessage(String message, User user);
}

class TextChat implements Chat{
    User admin;
    List<User> users = new ArrayList<>();

    public void setAdmin(User admin){
        if (admin != null && admin instanceof Admin){
            this.admin = admin;
        } else {
            throw new RuntimeException("Not enough rights");
        }
    }

    public void addUser(User user){
        if (admin == null){
            throw new RuntimeException("There is no admin");
        }
        if (user instanceof SimpleUser){
            users.add(user);
        } else {
            throw new RuntimeException("Admin can not enter another chat");

        }
    }

    @Override
    public void sendMessage(String message, User user) {
        if (user instanceof Admin) {
            for (User u: users) {
                u.getMessage(user.getName() + ": " + message);
            }
        }
        if (user instanceof SimpleUser){
            for (User u: users) {
                if (u!=user&&u.isEnable()){
                    u.getMessage(user.getName() + ": " + message);
                }
                if (admin.isEnable()){
                    admin.getMessage(user.getName() + ": " + message);
                }
            }
        }
    }
}