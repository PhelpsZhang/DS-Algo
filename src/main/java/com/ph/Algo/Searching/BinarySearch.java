package com.ph.Algo.Searching;

public class BinarySearch {
    
    // 双闭区间实现。
    // 其实左闭右开区间也行，但双闭区间中i和j的操作是对称的，更不容易出错。
    // 仅适用于能用index索引的数组，而不适用链表。
    public int binarySearch(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        // 我们考虑的是闭区间。当i > j时，闭区间为空。所以这里是<=
        while (i <= j) {
            // 取中点。不用(i+j)/2是为了避免int型的i和j相加后超出了整型范围。
            int m = i + (j-i)/2;
            if (nums[m] < target) {
                j = m - 1;
            } else if (nums[m] > target) {
                i = m + 1;
            } else {
                return m;
            }
        }
        // 没找到，返回-1。
        return -1;
    }

    // 拓展，寻找最左侧的target
    public int binarySearchInsertion(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        // 我们考虑的是闭区间。当i > j时，闭区间为空。所以这里是<=
        while (i <= j) {
            // 取中点。不用(i+j)/2是为了避免int型的i和j相加后超出了整型范围。
            int m = i + (j-i)/2;
            if (nums[m] < target) {
                j = m - 1;
            } else if (nums[m] > target) {
                i = m + 1;
            } else {
                // 当nums[m] == target，说明最左侧的target在i和m-1之间。
                j = m - 1;
            }
        }
        // 此时，i就是。
        return i;
    }

    // 上面的insertion方法，在数组中不存在target时可能有两种结果：
    // 1. return的i越界（i会越右界）
    // 2. nums[i]不等于target
    // 这两种情况都直接返回-1即可。
    public int binarySearchLeftEdge(int[] nums, int target) {
        int i = binarySearchInsertion(nums, target);
        if (i == nums.length || nums[i] != target)
            return -1;
        return i;
    }
    
}
