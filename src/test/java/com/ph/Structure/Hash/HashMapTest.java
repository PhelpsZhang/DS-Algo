package com.ph.Structure.Hash;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ph.Entity.Person;

public class HashMapTest {
    
    @Test
    public void testHashMap() {

        Person[] persons = {
            new Person("aa", 18, BigDecimal.valueOf(59)),
            new Person("bb", 20, BigDecimal.valueOf(24)),
            new Person("dd", 45, BigDecimal.valueOf(68)),
            new Person("ff", 29, BigDecimal.valueOf(91)),
            new Person("ii", 20, BigDecimal.valueOf(85)),
            new Person("aa", 30, BigDecimal.valueOf(88)),
        };

        ChainHashMap<String, Person> hash = new ChainHashMap<>();
        for (Person p : persons) {
            hash.put(p.getName(), p);
        }
        hash.print();

    }
}
