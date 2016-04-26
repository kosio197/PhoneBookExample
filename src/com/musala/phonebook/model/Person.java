package com.musala.phonebook.model;

public class Person implements Comparable<Person> {

    private String name;
    private String number;
    private int call;

    public Person(String name, String number) {

        this.name = name;
        this.number = number;
        this.call = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCall() {
        return call;
    }

    public void setCall(int call) {
        this.call = call;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person)) {
            return false;
        }

        Person p = (Person) o;
        if (this.name == p.getName()) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return name + " " + number;
    }

    @Override
    public int compareTo(Person o) {

        return this.name.compareTo(o.getName());
    }
}
