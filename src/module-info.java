module MyDogHealth {
    requires javafx.controls;
    requires javafx.swing;
    requires javafx.web;

    requires java.sql;
    requires com.google.gson;
    requires okhttp3;
    requires jakarta.mail;

    exports view;
    exports controller;
    exports model;
    exports dao;
    exports util;
    exports config;
}
