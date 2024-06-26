package com.ph.Structure.Graph;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.ph.Entity.Person;

public class GraphAdjListTest {
    
    @Test
    public void testAdjList() {
        

        /*
         *       ii --  aa
         *            /  |  \
         *      ee--bb--dd   ff
         *          |        /
         *         cc ______/
         */

        Vertex<Person> v0 = new Vertex<>(new Person("aa", 18, BigDecimal.valueOf(59)));
        Vertex<Person> v1 = new Vertex<>(new Person("bb", 20, BigDecimal.valueOf(24)));
        Vertex<Person> v2 = new Vertex<>(new Person("dd", 45, BigDecimal.valueOf(68)));
        Vertex<Person> v3 = new Vertex<>(new Person("ff", 29, BigDecimal.valueOf(91)));
        Vertex<Person> v4 = new Vertex<>(new Person("ii", 20, BigDecimal.valueOf(85)));
        Vertex<Person> v5 = new Vertex<>(new Person("cc", 33, BigDecimal.valueOf(80)));
        Vertex<Person> v6 = new Vertex<>(new Person("ee", 26, BigDecimal.valueOf(78)));

        Vertex<Person> [][] va = new Vertex[][] {
            {v0, v1},
            {v0, v2},
            {v0, v3},
            {v0, v4},
            {v1, v2},
            {v1, v5},
            {v1, v6},
            {v3, v5}
        };

        GraphAdjList<Person> graph = new GraphAdjList<>(va);
        graph.print();

        System.out.println("BFS start from v0");
        for (Vertex<Person> e : graph.graphBFS(v0)) {
            System.out.println(e.value.toString());
        }

        System.out.println("BFS start from v3");
        for (Vertex<Person> e : graph.graphBFS(v3)) {
            System.out.println(e.value.toString());
        }

        System.out.println("DFS start from v3");
        for (Vertex<Person> e : graph.graphDFS(v3)) {
            System.out.println(e.value.toString());
        }

        System.out.println("DFSIte start from v3");
        for (Vertex<Person> e : graph.graphDFSIter(v3)) {
            System.out.println(e.value.toString());
        }

        System.out.println("DFS start from v0");
        for (Vertex<Person> e : graph.graphDFS(v0)) {
            System.out.println(e.value.toString());
        }
    
        System.out.println("DFSIte start from v0");
        for (Vertex<Person> e : graph.graphDFSIter(v0)) {
            System.out.println(e.value.toString());
        }
    }
}
