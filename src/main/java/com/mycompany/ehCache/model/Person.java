package com.mycompany.ehCache.model;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {

    private String name;

    private Fils fils;

    public Person() {
    }

    public Person(String name, String filsName) {
        this.name = name;
        this.fils = new Fils(filsName);
    }

    public Fils getFils() {
        return fils;
    }

    public void setFils(Fils fils) {
        this.fils = fils;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", fils=" + fils +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(fils, person.fils);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fils);
    }
}
