package com.ph.Structure.Tree;

public class TreeNode<T extends Comparable<T>> {
    public T value;
    public int height;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T value) {
        this.value = value;
        left = right = null;
    }
}