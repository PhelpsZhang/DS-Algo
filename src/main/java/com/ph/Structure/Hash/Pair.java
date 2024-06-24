package com.ph.Structure.Hash;

public class Pair<T extends Comparable<T>, V extends Comparable<V>> {
    public T key;
    public V value;

    public Pair(T key, V value) {
        this.key = key;
        this.value = value;
    }
}
