package com.musala.phonebook.main;

import java.io.FileNotFoundException;

import com.musala.phonebook.PhoneBookEngine;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        PhoneBookEngine engine = new PhoneBookEngine();
        engine.init("phones.txt");
        engine.processCommands(System.in, System.out);
    }
}
