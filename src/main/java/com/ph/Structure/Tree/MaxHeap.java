package com.ph.Structure.Tree;

import java.util.*;

public class MaxHeap<T extends Comparable<T>> {
    
    private List<T> maxHeap;

    // construction function.
    public MaxHeap(List<T> nums) {
        maxHeap = new ArrayList<>(nums);
        for (int i = parent(size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int size() {
        return maxHeap.size();
    }

    // 获得左子节点的索引
    public int left(int i) {
        return 2 * i + 1;
    }

    // 获得右子节点的索引
    public int right(int i) {
        return 2 * i + 2;
    }

    // 获取父节点索引
    public int parent(int i) {
        return (i - 1) / 2;
    }

    // Access top of heap
    public T peek() {
        return maxHeap.get(0);
    }

    public void push(T value) {
        maxHeap.add(value);
        siftUp(size() - 1);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /* 元素出堆 */
    public T pop() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        swap(0, size() - 1);
        T value = maxHeap.remove(size() - 1);
        siftDown(0);
        return value;
    }

    /* 从节点i开始，从底至顶堆化 */
    public void siftUp(int i) {
        while (true) {
            // 获取节点i的父节点
            int p = parent(i);
            // 当超过根节点 或者 节点无需修复 时，结束堆化
            if (p < 0 || maxHeap.get(p).compareTo(maxHeap.get(i)) >= 0) {
                break;
            }
            // 交换节点
            swap(i, p);
            // 循环向上堆化
            i = p;
        }
    }

    public void siftDown(int i) {
        while (true) {
            int l = left(i);
            int r = right(i);
            int maxNodeId = i;
            if (l < size() && maxHeap.get(l).compareTo(maxHeap.get(maxNodeId)) > 0)
                maxNodeId = l;
            if (r < size() && maxHeap.get(r).compareTo(maxHeap.get(maxNodeId)) > 0)
                maxNodeId = r;
            if (maxNodeId == i)
                break;
            swap(i, maxNodeId);
            i = maxNodeId;
        }
    }

    /* 打印堆（二叉树） */
    // public void printHeap() {
    //     Queue<T> queue = new PriorityQueue<>((a, b) -> { return b.compareTo(a); });
    //     queue.addAll(maxHeap);
    //     for (T t : queue) {
    //         System.out.println(t.toString());
    //     }
    // }
    
    public void printX() {
        for (T t : maxHeap) {
            System.out.println(t.toString());
        }
    }

    private void swap(int x, int y) {
        T tmp = maxHeap.get(x);
        maxHeap.set(x, maxHeap.get(y));
        maxHeap.set(y, tmp);
    }


}
