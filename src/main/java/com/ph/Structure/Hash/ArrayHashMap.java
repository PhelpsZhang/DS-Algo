package com.ph.Structure.Hash;

import java.util.ArrayList;
import java.util.List;

// 这个arrayHashMap，哈希【冲突】的要死。
// 冲突了怎么办？基于数组的实现就只能扩容，而扩容是很耗时的。
// 即需要把所有键值对从原哈希表迁移到新哈希表。并且由于哈希表容量改变，我们被迫要通过hashFunc重新计算所有键值对的存储位置。
// 所以需要预留足够大的哈希表容量，防止频繁扩容
// 负载因子：load factor
public class ArrayHashMap<T extends Comparable<T>, V extends Comparable<V>> {
    
    // 注意，数组的实现方式，这里用了List。
    // 但是千万不要下意识用List的API！我们实现的是HashMap！
    // 增删改查是要通过直接【索引访问】提高效率的O(1)，不是用List的O(n)
    private List<Pair<T, V>> buckets;
    // 这里指定给100个桶. not static
    private final int capacity = 100;

    public ArrayHashMap() {
        // 初始化数组
        buckets = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            buckets.add(null);
        }
    }

    public int size() {
        return buckets.size();
    }

    private int hashFunc(T key) {
        // index = hash(key) % capacity
        int h;
        if (key == null) return -1;
        h = key.hashCode();
        h ^= (h >>> 16);
        return h % capacity;
    }

    // Get Value by Key
    public V get(T key) {
        int index = hashFunc(key);
        // 以数组索引方式从List取值，O(1)
        Pair<T, V> pair = buckets.get(index);
        if (pair == null)   return null;
        return pair.value;
    }

    // Insert a pair
    public void put(T key, V value) {
        if (key == null)
            throw new IllegalArgumentException();
        Pair<T, V> pair = new Pair<>(key, value);
        int index = hashFunc(key);
        // 注意，这是hash表，不是用List.add()而是List.set(id, element);
        buckets.set(index, pair);
    }

    // remove a pair
    public void remove(T key) {
        int index = hashFunc(key);
        buckets.set(index, null);
    }

    // get all pair
    public List<Pair<T, V>> pairSet() {
        // 注意为什么不是直接返回buckets。
        // bucket是我们预先放的很多桶，但是不是每个桶都放了元素的
        // 这里要的是实际放了的元素，即键值对
        List<Pair<T, V>> pairSet = new ArrayList<>();
        for (Pair<T,V> pair : buckets) {
            if (pair != null) {
                pairSet.add(pair);
            }
        }
        return pairSet;
    }

    // get All Keys
    public List<T> keySet() {
        List<T> keySet = new ArrayList<>();
        for (Pair<T,V> pair : buckets) {
            if (pair != null)
                keySet.add(pair.key);
        }
        return keySet;
    }

    public List<V> valueSet() {
        List<V> valueSet = new ArrayList<>();
        for (Pair<T,V> pair : buckets) {
            if (pair != null)
                valueSet.add(pair.value);
        }
        return valueSet;
    }

    public void print() {
        for (Pair<T,V> kv : pairSet()) {
            System.out.println(kv.key + " -> " + kv.value);
        }
    }
}
