package org.example;

import javax.persistence.*;


import javax.persistence.Entity;

@Entity
@Table(name = "computers")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String name;

    @Column
    private int value;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }


    

    @Override
    public String toString() {
        return "Award{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
