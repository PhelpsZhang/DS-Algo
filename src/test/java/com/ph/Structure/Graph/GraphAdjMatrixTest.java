package com.ph.Structure.Graph;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.ph.Entity.Person;

public class GraphAdjMatrixTest {
    
    @Test
    public void testAdjMat() {

        /*
         *       ii --  aa
         *            /  |  \
         *          bb--dd   ff
         */
        Person[] perons = {
            new Person("aa", 18, BigDecimal.valueOf(59)),
            new Person("bb", 20, BigDecimal.valueOf(24)),
            new Person("dd", 45, BigDecimal.valueOf(68)),
            new Person("ff", 29, BigDecimal.valueOf(91)),
            new Person("ii", 20, BigDecimal.valueOf(85))
        };

        int[][] edges = {
            {0, 1},
            {0, 2},
            {0, 3},
            {0, 4},
            {1, 2}
        };

        GraphAdjMatrix<Person> graph = new GraphAdjMatrix<>(perons, edges);
        graph.print();
    }
}
