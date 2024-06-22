package com.ph.Entity;

import java.math.BigDecimal;

public class Person implements Comparable<Person> {
    String name;
    Integer age;
    BigDecimal score;

    public Person() {}

    public Person(String name, Integer age, BigDecimal score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
    
    public void setScore(int score) {
        this.score = BigDecimal.valueOf(score);
    }

    @Override
    public int compareTo(Person other) {
        return this.score.compareTo(other.score);
    }

    @Override
    public String toString() {
        // return "name: " + this.name + " age: " + this.age + " score: " + this.score;
        return name + " (" + score + ")";
    }
}
