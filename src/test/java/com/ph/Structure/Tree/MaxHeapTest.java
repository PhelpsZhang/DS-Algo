package com.ph.Structure.Tree;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import com.ph.Entity.Person;
import com.ph.db.DatabaseUtils;

public class MaxHeapTest {
    
    @Test
    public void testMaxHeap(){
        // test function must be Public
        System.out.println("Test MaxHeap Code");

        List<Person> list = new LinkedList<>();
        
        try (Connection conn = DatabaseUtils.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Person");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                BigDecimal score = rs.getBigDecimal("score");
                Person person = new Person(name, age, score);
                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MaxHeap<Person> maxheap = new MaxHeap<>(list);
        maxheap.printX();
    }


    /*
     * 给定一个长度为n的无序数组 nums ，请返回数组中最大的k个元素。
     * 该函数与自己写的maxHeap无关
     */
    @Test
    public void topK() {
        // test function must be Public
        System.out.println("Test topK Problem");
        
        /*
         * 1. 初始化一个【小顶堆】
         * 2. 将数组的前k个元素依次入堆
         * 3. 从第k+1个元素开始，如果当前元素大于堆顶元素，则将堆顶元素出堆，并将当前元素入堆
         * 4. 遍历完成后，堆中就是最大的k个元素。
         * O(nlogk)
         * 在不断加入数据时，我们可以持续维护堆内的元素，从而实现最大的k个元素的动态更新。
         */
        int [] nums = {34,6,32,61,7,43,71,32,46,54,96,56,54,26,12,9,88};
        int k = 5;
        Queue<Integer> heap = new PriorityQueue<Integer>();
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        int len = nums.length;
        for (int i = k; i < len; i++) {
            if (nums[i] > heap.peek()) {
                heap.poll();
                heap.offer(nums[i]);
            }
        }
        // print
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }
}
