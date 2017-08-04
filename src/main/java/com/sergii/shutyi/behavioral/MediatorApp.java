package com.sergii.shutyi.behavioral;

import java.util.ArrayList;
import java.util.List;

public class MediatorApp {
    public static void main(String[] args) {
        TextChat chat = new TextChat();

        Userr admin = new Admin(chat);
        Userr userr1 = new SimpleUser(chat);
        Userr userr2 = new SimpleUser(chat);

        chat.setAdmin(admin);
        chat.addUser(userr1);
        chat.addUser(userr2);

        userr1.sendMessage("Hello, I am user");
        admin.sendMessage("Admin entered chat!!!");
    }
}

interface Userr{
    void sendMessage(String message);
    void getMessage(String message);
}

class Admin implements Userr{
    Chat chat;

    public Admin(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("Admin gets message: " + message);
    }
}

class SimpleUser implements Userr{
    Chat chat;

    public SimpleUser(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("SimpleUser gets message: " + message);
    }
}

interface Chat{
    void sendMessage(String message, Userr user);
}

class TextChat implements Chat{
    Userr admin;
    List<Userr> users = new ArrayList<>();

    public void addUser(Userr userr){
        users.add(userr);
    }

    @Override
    public void sendMessage(String message, Userr user) {
        for (Userr u:
             users) {
            u.getMessage(message);
        }
        admin.getMessage(message);
    }

    public void setAdmin(Userr admin) {
        this.admin = admin;
    }
}