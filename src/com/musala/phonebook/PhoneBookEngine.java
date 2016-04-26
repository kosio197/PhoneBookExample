package com.musala.phonebook;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import com.musala.phonebook.model.Person;

public class PhoneBookEngine {

    public PrintWriter writer;
    private static PhoneBookImpl phoneBook;

    public void init(String fileName) throws FileNotFoundException {
        phoneBook = new PhoneBookImpl(fileName);
    }

    public void processCommands(InputStream inp, OutputStream out) {
        Scanner s = null;
        writer = new PrintWriter(out, true);

        try {
            s = new Scanner(inp);

            while (s.hasNext()) {
                String string = s.nextLine();

                dispatchCommand(string, out);
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }

    public void dispatchCommand(String command, OutputStream out) {
        String[] str = command.trim().split(" ");

        switch (str[0]) {
            case "add":
                phoneBook.addPerson(str[1], str[2]);
                break;

            case "delete":
                phoneBook.deletePerson(str[1]);
                break;

            case "print":
                TreeMap<String, Person> personsByName = phoneBook.printPhoneBook();

                for (Entry<String, Person> person : personsByName.entrySet()) {
                    writer.println(person.getValue().toString());
                }
                break;
            case "addCall":
                phoneBook.addCall(str[1]);
                break;

            case "printMax":
                Person[] personsByCall = phoneBook.printMaxCallPerson();

                for (int i = 0; i < personsByCall.length; i++) {
                    if (personsByCall[i] == null) {
                        break;
                    }

                    writer.println(personsByCall[i].toString());
                }

                break;

            default:
                writer.println("Invalid command: " + command);
                break;
        }
    }
}
