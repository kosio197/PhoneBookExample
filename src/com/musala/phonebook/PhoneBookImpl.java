package com.musala.phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.musala.phonebook.model.Person;

public class PhoneBookImpl implements PhoneBook {
    private TreeMap<String, Person> personsByName = new TreeMap<>();
    private Person[] personsByCall = new Person[5];

    public PhoneBookImpl(String fileName) throws FileNotFoundException {
        loadData(fileName);
    }

    @Override
    public boolean validate(String number) {
        String regExp = "[+359|00359|0]8[7|8|9][2|3|4|5|6|7|8|9]\\d{6}";

        Pattern p = Pattern.compile(regExp);

        Matcher m = p.matcher(number);

        return m.find();
    }

    @Override
    public void addPerson(String name, String number) {
        personsByName.put(name, new Person(name, number));
    }

    @Override
    public void deletePerson(String name) {

        personsByName.remove(name);

        for (Person person : personsByCall) {
            if (person != null && person.getName().equals(name)) {
                person = getNextPerson();
            }
        }
    }

    private Person getNextPerson() {
        Person person = null;
        int maxCall = 0;
        for (Entry<String, Person> pNum : personsByName.entrySet()) {
            for (Person pCall : personsByCall) {

                if (!pNum.equals(pCall) && pNum.getValue().getCall() > maxCall) {
                    person = pNum.getValue();
                }
            }
        }
        return person;
    }

    @Override
    public Person searchPerson(String name) {
        return personsByName.get(name);
    }

    @Override
    public TreeMap<String, Person> printPhoneBook() {
        return personsByName;
    }

    @Override
    public Person[] printMaxCallPerson() {
        return personsByCall;
    }

    @Override
    public void addCall(String name) {
        Person p = personsByName.get(name);
        p.setCall(p.getCall() + 1);

        boolean in = false;

        for (int i = 0; i < 5; i++) {
            if (personsByCall[i] == null) {
                personsByCall[i] = p;
                return;
            }

            if (personsByCall[i].equals(p)) {
                in = true;
                break;
            }
        }

        if (!in && personsByCall[4].getCall() < p.getCall()) {
            personsByCall[4] = p;
            in = true;
        }

        if (in) {
            Arrays.sort(personsByCall, new Comparator<Person>() {

                @Override
                public int compare(Person o1, Person o2) {
                    return o1.getCall() > o2.getCall() ? -1 : 1;
                }
            });

            return;
        }
    }

    private void loadData(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            String[] data = line.split(" ");

            if (data.length == 2 && validate(data[1])) {
                addPerson(data[0], data[1]);
            }
        }

        scanner.close();
    }
}
