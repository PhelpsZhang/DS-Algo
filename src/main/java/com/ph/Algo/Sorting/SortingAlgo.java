package com.ph.Algo.Sorting;

import java.util.ArrayList;
import java.util.List;

public class SortingAlgo<T extends Comparable<T>> {

    private void swap(T[] nums, int i, int j) {
        T temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /*
     * Selection Sorting
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     * Non-Stable
     * Non-Adaptive
     * In-place
     */
    public void selectionSort(T[] nums, boolean ascending) {
        int len = nums.length;
        if (len == 0 || len == 1) return;
        for (int i = 0; i < len - 1; i++) {
            int k = i;
            for (int j = i + 1; j < len; j++) {
                if (ascending ? (nums[j].compareTo(nums[k]) < 0) : (nums[j].compareTo(nums[k]) > 0)) {
                    // 找到最小值的索引即可
                    k = j;
                }
            }
            swap(nums, i, k);
        }
    }

    /*
     * TC: O(N^2)
     * SC: O(1)
     * Stable(遇到相等元素不交换）
     * Non-Adaptive
     * In-place
     */
    public void bubbleSort(T[] nums, boolean ascending) {
        int len = nums.length;
        if (len == 0 || len == 1) return;
        for (int i = len - 1; i > 0; i--) {
            for(int j = 0; j < i; j++) {
                if (ascending ? (nums[j].compareTo(nums[j+1]) > 0) : (nums[j].compareTo(nums[j+1]) < 0)) {
                    swap(nums, j, j+1);
                }
            }
        }
    }
    /*
     * TC: O(N^2)
     * SC: O(1)
     * Stable(遇到相等元素不交换）
     * Adaptive(经过flag优化，最佳和最差和平均复杂度不一样了。)
     * In-place
     */
    public void bubbleSortWithFlag(T[] nums, boolean ascending) {
        int len = nums.length;
        if (len == 0 || len == 1) return;
        for (int i = len - 1; i > 0; i--) {
            boolean flag = false;
            for(int j = 0; j < i; j++) {
                if (ascending ? (nums[j].compareTo(nums[j+1]) > 0) : (nums[j].compareTo(nums[j+1]) < 0)) {
                    swap(nums, j, j+1);
                    flag = true;
                }
            }
            // 如果一轮遍历没有经过任何交换，说明数组已经排序完成。
            if (!flag)
                return;
        }
    }

    /*
     * TC: O(N^2)
     * SC: O(1)
     * Stable
     * Adaptive:当输入数据完全有序时，O(N)
     * In-place
     */
    public void insertionSort(T[] nums, boolean ascending) {
        int len = nums.length;
        if (len == 0 || len == 1) return;
        for (int i = 1; i < len; i++) {
            // 抽出一张牌
            T base = nums[i];
            // 试图找到这张牌插在哪
            int j = i - 1;
            // 如果数组左侧全部比base小，那么就不会进内层循环。TC自适应。
            while (j >= 0 && (ascending ? (nums[j].compareTo(base) > 0) : (nums[j].compareTo(base) < 0))) {
                // 移动比该牌大(小)的牌。
                nums[j+1] = nums[j];
                j--;
            }
            // 插入牌
            nums[j+1] = base;
        }
    }

    private int medianThree(T[] nums, int left, int mid, int right) {
        T l = nums[left], m = nums[mid], r = nums[right];
        if ((l.compareTo(m) <= 0 && m.compareTo(r) <= 0) || (r.compareTo(m) <= 0 && m.compareTo(l) <= 0)) {
            return mid;
        }
        if ((m.compareTo(l) <= 0 && l.compareTo(r) <= 0) || (r.compareTo(l) <= 0 && l.compareTo(m) <= 0)) {
            return left;
        }
        return right;
    }

    /*
     * 这里的细致指针管理不太好理解，也可以用一种粗暴直观的方式来实现
     */
    private int partition(T[] nums, int left, int right) {
        // 选一个数作为base
        // pivot选取优化。找到首、尾、中三个数的中位数作为base，放到nums[left]
        int med = medianThree(nums, left, left + (right-left)/2, right);
        swap(nums, left, med);
        
        int i = left;
        int j = right;
        while (i < j) {
            // 两个内层while循环顺序不可交换。Why？
            // 从右到左，找到第一个比pivot小的元素
            while (i < j && (nums[j].compareTo(nums[left]) <= 0)) {
                j--;
            }
            // 从左到右，找到第一个比pivot大的元素
            while (i < j && (nums[i].compareTo(nums[left]) >= 0)) {
                i++;
            }
            swap(nums, i, j);
        }
        // 将基准数nums[left]交换到两个子数组的分界处。
        swap(nums, left, i);
        return i;
    }

    /*
     * partition的高效实现。但是它返回的是比pivot小的元素中最右一个元素的索引，而此时pivot元素本身并不在这个分界处。
     */
    private int hoaresPartition(T[] nums, int left, int right)
    {
        T pivot = nums[left];
        int i = left - 1;
        int j = right + 1;
        // 该方案把pivot本身也纳入进去算。最后成功把小于pivot的都放左边，大于pivot的都放右边，但是！
        // 但是pivot本身不在中间的分界。事实上这没有关系，只是quicksort调用时传参的边界要稍作修改。
        while (true) {
            do {
                // find leftmost greater than or equal to pivot
                i++;
            } while (nums[i].compareTo(pivot) < 0);
            // do-while 避免与pivot相等的元素交换后无法前进

            do {
                // find rightmost smaller than or equal to pivot
                j--;
            } while (nums[j].compareTo(pivot) > 0);

            // 如果此时i和j满足i>=j，那么一定满足：i指向大于等于pivot的，j指向小于等于pivot的。
            // 而右侧已经都是大于等于pivot，左侧已经都是小于等于pivot，所以，i和j顶多相差1(x,x,j,i,y,y,)，或者相等。
            // 此时返回的是最右一个小于pivot的index
            if (i >= j) return j;
            swap(nums, i, j);
        }
    }

    /*
     * 另一种partition实现
     */
    private int lomutoPartition(T[] nums, int left, int right)
    {
        // 选取最右侧的值为pivot
        T pivot = nums[right];
        // 所有小于pivot的元素放在左侧，用一个index来追踪这个区间的右边界。
        int index = (left - 1);
        for (int i = left; i <= right - 1; i++) {
            if (nums[i].compareTo(pivot) < 0) {
                // 发现了小于pivot的值，就把该新值加在右边界，右边界也因此向右扩张一位。
                index++;
                swap(nums, index, i);
            }
        }
        swap(nums, index + 1, right);
        return (index + 1);
    }
    
    /*
     * 直观、粗暴实现partition
     */
    private int naivePartition(int[] arr, int low, int high) {
        int pivot = arr[high]; // 选择最后一个元素作为基准
        int leftPointer = low; // 左指针，用于重新填充数组
        int rightPointer = high; // 右指针，用于重新填充数组
    
        // 创建两个临时数组
        int[] smaller = new int[high - low + 1];
        int[] larger = new int[high - low + 1];
    
        // 临时数组的索引
        int smallIndex = 0;
        int largeIndex = 0;
    
        // 遍历数组，分配到两个临时数组中
        for (int i = low; i < high; i++) {
            if (arr[i] < pivot) {
                smaller[smallIndex++] = arr[i];
            } else {
                larger[largeIndex++] = arr[i];
            }
        }
    
        // 将小于基准的元素复制回原数组
        for (int i = 0; i < smallIndex; i++) {
            arr[leftPointer++] = smaller[i];
        }
    
        // 记录基准的新位置
        int pivotIndex = leftPointer;
        arr[leftPointer++] = pivot; // 放置基准元素
    
        // 将大于基准的元素复制回原数组
        for (int i = 0; i < largeIndex; i++) {
            arr[leftPointer++] = larger[i];
        }
    
        return pivotIndex; // 返回基准的新位置
    }
    
    // private void quickSortHelper(T[] nums, int left, int right) {
    //     if (left >= right) return;
    //     int pivot = partition(nums, left, right);
    //     quickSortHelper(nums, left, pivot - 1);
    //     quickSortHelper(nums, pivot + 1, right);
    // }
    
    /*
     * 尾递归优化。将最糟糕情况下的栈帧使用(空间复杂度)从O(N)优化到O(LogN) 
     * 因为选取的是较短的子数组，而较短的子数组长度不会超过n/2，所以确保了递归深度不会超过logN。
     * 原始的最糟糕情况是不停地划分为[0, n-1]，在这种情况下递归的深度反而只有1，空间复杂度变成了O(1)。
     * 这里是while循环，每次递归子数组之后，原地调整未排序区间，然后进入while循环下一轮，对未排序区间再划分一次哨兵。
     * while内多次递归，但是每次递归深度都不会超过logN
     */
    private void quickSortHelper(T[] nums, int left, int right) {
        while (left < right) {
            int pivot = partition(nums, left, right);
            // 仅对两个子数组中较短的那个执行快排
            if (pivot - left < right - pivot) {
                // 递归排序左子数组
                quickSortHelper(nums, left, pivot - 1);
                // 未排序区间[pivot + 1, right]
                left = pivot + 1;
            } else {
                quickSortHelper(nums, pivot + 1, right);
                // 未排序区间[left, pivot - 1]
                right = pivot - 1;
            }
        }
    }

    private void quickSortHelperHoares(T[] nums, int left, int right) {
        while (left < right) {
            int pivot = hoaresPartition(nums, left, right);
            // 仅对两个子数组中较短的那个执行快排
            if (pivot - left < right - pivot) {
                // 递归排序左子数组
                quickSortHelperHoares(nums, left, pivot);
                // 未排序区间[pivot + 1, right]
                left = pivot + 1;
            } else {
                quickSortHelperHoares(nums, pivot + 1, right);
                // 未排序区间[left, pivot]
                right = pivot;
            }
        }
    }

    /*
     * TC: O(NlogN)。递归划分是logN，每层中的循环是N。
     * Adaptive: 自适应。平均O(NlogN)。但最差时，每次划分子数组都画成0和n-1两个子数组，相当于递归深度为N(递归了N次，正常是LogN)。相当于O(N^2)。
     * SC: O(N)。最差情况下递归深度为N，使用了O(N)的栈帧空间
     * In-Place
     * Non-Stable：partition最后一步，基准数可能会被交换到相等元素的右侧。
     */
    public void quickSort(T[] nums, boolean ascending) {
        int len = nums.length;
        if (len == 0 || len == 1) return;
        // quickSortHelper(nums, 0, len - 1);
        quickSortHelperHoares(nums, 0, len - 1);
    }
    
    private void merge(T[] nums, int left, int mid, int right) {
        // 左段是[left, mid],右段是[mid+1, right]
        // 一个tmp用于存放合并后的结果。
        // T[] tmps = new T[right-left+1]; // 泛型不能这样玩，暂时用List
        List<T> temp = new ArrayList<>(right-left+1);
        int i = left, j = mid+1;
        while (i <= mid && j <= right) {
            if (nums[i].compareTo(nums[j]) > 0) {
                // List没有值不能set，应该默认add
                temp.add(nums[j++]);
            } else {
                temp.add(nums[i++]);
            }
        }
        // 处理剩余的元素
        while (i <= mid) {
            temp.add(nums[i++]);
        }
        while (j <= right) {
            temp.add(nums[j++]);
        }
        // 元素放回去
        for (int k = 0; k < temp.size(); k++) {
            nums[left+k] = temp.get(k);
        }
    }

    public void mergeSortHelper(T[] nums, int left, int right) {
        // 终止条件
        if (left >= right)
            return;
        int mid = left + (right - left) / 2;
        // 划分阶段
        mergeSortHelper(nums, left, mid);
        mergeSortHelper(nums, mid+1, right);
        // 合并阶段
        merge(nums, left, mid, right);
    }

    /*
     * TC: O(NlogN)
     * Non-Adaptive: 产生高度为logN的递归树。
     * SC: O(N)。递归深度为logN，使用O(logN)的栈帧空间。合并操作需要额外的辅助数组，占O(N)
     * Non In-Place:非原地排序。
     * Stable: 排序过程中不打乱相等元素的原始顺序
     */
    public void mergeSort(T[] nums, boolean ascending) {
        mergeSortHelper(nums, 0, nums.length - 1);
    }

    private void siftDown(T[] nums, int n, int inode) {
        // 堆长度为n，从节点inode开始自顶向下堆化。
        while (true) {
            int l = inode * 2 + 1;
            int r = inode * 2 + 2;
            int mark = inode;
            // 左子节点比当前值大
            if (l < n && nums[l].compareTo(nums[mark]) > 0) {
                mark = l;
            }
            // 右子节点比当前值大
            if (r < n && nums[r].compareTo(nums[mark]) > 0) {
                mark = r;
            }
            // 如果节点inode最大，或者索引l,r越界，则无需继续堆化
            if (mark == inode)
                break;
            swap(nums, inode, mark);
            // 循环向下堆化
            inode = mark;
        }
    }

    /*
     * TC: O(NlogN)
     * Non-Adaptive:建堆用了O(N)，堆里提取最大元素O(LogN)，循环n-1轮
     * SC:O(1)
     * In-place: 元素和堆化都是在原数组上进行的
     * Non-Stable:交换堆顶和堆底元素时，可能无法保证stable
     */
    public void heapSort(T[] nums, boolean ascending) {
        // 建堆。堆化除了叶节点以外的所有其他节点
        for (int i = nums.length/2 - 1; i >= 0; i--) {
            siftDown(nums, nums.length, i);
        }
        // 从堆中提取最大元素，循环n-1轮。
        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            siftDown(nums, i, 0);
        }
    }
    
}
