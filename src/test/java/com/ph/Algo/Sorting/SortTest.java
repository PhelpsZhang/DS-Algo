package com.ph.Algo.Sorting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ph.Entity.Person;

public class SortTest {
    
    @Test
    public void testSort() {
        Person[] persons = {
            new Person("aa", 18, BigDecimal.valueOf(59)),
            new Person("bb", 20, BigDecimal.valueOf(24)),
            new Person("dd", 45, BigDecimal.valueOf(68)),
            new Person("ff", 29, BigDecimal.valueOf(91)),
            new Person("ii", 20, BigDecimal.valueOf(85)),
            new Person("aa", 30, BigDecimal.valueOf(88)),
        };

        SortingAlgo<Person> sa = new SortingAlgo<>();

        // sa.selectionSort(persons, true);
        // System.out.println("Test Selection Sort");
        // for (Person p : persons) {
        //     System.out.println(p.toString());
        // }

        // System.out.println("Test Insertion Sort");
        // sa.insertionSort(persons, true);
        // for (Person p : persons) {
        //     System.out.println(p.toString());
        // }


        // System.out.println("Test Quick Sort");
        // sa.quickSort(persons, true);
        // for (Person p : persons) {
        //     System.out.println(p.toString());
        // }

        // System.out.println("Test Merge Sort");
        // sa.mergeSort(persons, true);
        // for (Person p : persons) {
        //     System.out.println(p.toString());
        // }

        System.out.println("Test Heap Sort");
        sa.heapSort(persons, true);
        for (Person p : persons) {
            System.out.println(p.toString());
        }

        System.out.println("Test Bucket Sort");
        double[] dn = {0.364, 0.99, 0.257, 0.100, 0.3, 0.253, 0.885, 0.462, 0.9993, 0.629};
        sa.bucketSort(dn);
        for (double e : dn) {
            System.out.println(e);
        }
    }
}
