package com.mycompany.ehCache.model;

import java.io.Serializable;
import java.util.Objects;

public class Fils implements Serializable {

    private String filsName;

    public Fils(String filsName) {
        this.filsName = filsName;
    }

    public String getFilsName() {
        return filsName;
    }

    public void setFilsName(String filsName) {
        this.filsName = filsName;
    }

    @Override
    public String toString() {
        return "Fils{" +
                "filsName='" + filsName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fils fils = (Fils) o;
        return Objects.equals(filsName, fils.filsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filsName);
    }
}
