package com.ph.Structure.Graph;

public class Vertex<T extends Comparable<T>> {
    T value;

    public Vertex(){}
    
    public Vertex(T val) {
        this.value = val;
    }
}
