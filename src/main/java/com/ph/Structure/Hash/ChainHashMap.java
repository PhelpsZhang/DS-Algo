package com.ph.Structure.Hash;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// 1. 我们需要保证哈希表在哈希冲突出现时可以正常工作
// 2. 为了尽可能避免扩容，仅仅在冲突十分严重时才扩容
// 因此，不能仅仅用一个普通的数组实现，而采用数组+组合的【链式地址】实现。
// 原来每个桶只能存储一个key-value对，现在改为一个【列表】(这里依然用了ArrayList)，所有发生冲突的key-value对都在一个列表中。
public class ChainHashMap<T extends Comparable<T>, V extends Comparable<V>> {
    
    int size;   //  键值对数量
    int capacity;   // 哈希表容量
    double loadThreshold;   // loadFactor的阈值
    int extendRatio;
    
    // 外层是长度为capacity的ArrayList，每个位置存放一个ArrayList(即：桶)，便于我们index确定桶位置。
    // 内层List可以用LinkedList甚至是RBTree，因为当确定桶后在桶里找就不再能够用index索引了。但这里的实现还是先简单地用ArrayList
    List<List<Pair<T, V>>> buckets;
    

    public ChainHashMap() {
        size = 0;
        capacity = 4;
        loadThreshold = 2.0 / 3.0;
        extendRatio = 2;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
    }
    
    private int hashFunc(T key) {
        // index = hash(key) % capacity
        int h;
        if (key == null) return -1;
        h = key.hashCode();
        h ^= (h >>> 16);
        return h % capacity;
    }

    private double loadFactor() {
        return (double) size / capacity;
    }

    public V get(T key) {
        int index = hashFunc(key);
        // 得到对应的桶
        List<Pair<T, V>> bucket = buckets.get(index);
        for (Pair<T,V> pair : bucket) {
            // hash冲突就是多个key被hashfunc映射到了同一个桶里。因此我们要遍历桶，看哪个key才是我们真的需要的key。
            if (pair.key == key) {
                return pair.value;
            }
        }
        return null;
    }

    // 添加
    public void put(T key, V value) {
        // 当负载因子超过了设定的阈值，扩容
        if (loadFactor() > this.loadThreshold)
            extend();
        int index = hashFunc(key);
        List<Pair<T, V>> bucket = buckets.get(index);
        for (Pair<T,V> pair : bucket) {
            if (pair.key == key) {
                // 如果找到了这个key，就更新值，结束。
                pair.value = value;
                return;
            }
        }
        // 如果没有这个key，就添加进去。
        bucket.add(new Pair<>(key, value));
        this.size++;
    }

    // 删除
    public void remove(T key) {
        int index = hashFunc(key);
        List<Pair<T,V>> bucket = buckets.get(index);
        for (Pair<T,V> pair : bucket) {
            if (key == pair.key) {
                bucket.remove(pair);
                size--;
                break;
            }
        }
    }

    // 扩容
    public void extend() {
        // 1. 暂存原hash表
        List<List<Pair<T,V>>> tmpBuckets = buckets;
        // 2. 初始化新的哈希表
        capacity *= extendRatio;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
        size = 0;
        // 3. 将旧的键值对搬运过来
        for (List<Pair<T,V>> bucket : tmpBuckets) {
            for (Pair<T,V> pair : bucket) {
                put(pair.key, pair.value);
            }
        }
    }


    public void print() {
        for (List<Pair<T,V>> bucket : buckets) {
            List<String> res = new ArrayList<>();
            for (Pair<T,V> pair : bucket) {
                res.add(pair.key + " -> " + pair.value);
            }
            System.out.println(res);
        }
    }
}
