package com.musala.phonebook;

import java.util.TreeMap;

import com.musala.phonebook.model.Person;

public interface PhoneBook {

    boolean validate(String number);

    void addPerson(String name, String number);

    void deletePerson(String name);

    Person searchPerson(String name);

    TreeMap<String, Person> printPhoneBook();

    Person[] printMaxCallPerson();

    void addCall(String name);
}
