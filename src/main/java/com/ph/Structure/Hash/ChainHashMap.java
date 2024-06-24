package com.ph.Structure.Hash;

import java.math.BigDecimal;
import java.util.List;

// 1. 我们需要保证哈希表在哈希冲突出现时可以正常工作
// 2. 为了尽可能避免扩容，仅仅在冲突十分严重时才扩容
// 因此，不采用数组实现，而采用链式地址实现
public class ChainHashMap<T extends Comparable<T>, V extends Comparable<V>> {
    
    int size;   //  键值对数量
    int capacity;   // 哈希表容量
    double loadFactor;
    int extendRatio;
    List<List<Pair<T, V>>> buckets;
    
}
