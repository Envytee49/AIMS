package com.example.aims;

//import com.example.aims.dao.AIMSDB;
import com.example.aims.view.Starter;

public class App  {
    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.on(args);
    }
}