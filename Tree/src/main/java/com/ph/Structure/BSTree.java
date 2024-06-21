package com.ph.Structure;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BSTree<T extends Comparable<T>> {
    private TreeNode<T> root;

    /*
     * traversal: preorder + inorder + postorder
     * search
     * insert
     * remove
     */

    
    public BSTree(TreeNode<T> node) {
        this.root = node;
    }

    public BSTree() {
        this.root = null;
    }

    // iterative implementation
    public void preOrder(List<T> list) {
        if (root == null) return;
        TreeNode<T> cur = this.root;
        Stack<TreeNode<T> > stk = new Stack<>();
        while(cur != null || !stk.isEmpty()) {
            while (cur != null) {
                stk.push(cur);
                list.add(cur.value);
                cur = cur.left;
            }
            cur = stk.pop();
            cur = cur.right;
        }
    }

    public void inOrder(List<T> list) {
        if (root == null) return;
        TreeNode<T> cur = this.root;
        Stack<TreeNode<T> > stk = new Stack<>();
        while(cur != null || !stk.isEmpty()) {
            while (cur != null) {
                stk.push(cur);
                cur = cur.left;
            }
            cur = stk.pop();
            list.add(cur.value);
            cur = cur.right;
        }
    }

    public void postOrder(List<T> list) {
        if (root == null) return;
        TreeNode<T> cur = this.root;
        Stack<TreeNode<T> > stk = new Stack<>();
        while(cur != null || !stk.isEmpty()) {
            while (cur != null) {
                stk.push(cur);
                list.add(cur.value);
                cur = cur.right;
            }
            cur = stk.pop();
            cur = cur.left;
        }
        Collections.reverse(list);
    }

    public TreeNode<T> search(T value) {
        TreeNode<T> cur = root;
        while (cur != null) {
            if (value.compareTo(cur.value) > 0) {
                cur = cur.right;
            } else if (value.compareTo(cur.value) < 0) {
                cur = cur.left;
            } else {
                break;
            }
        }
        return cur;
    }

    public void insert(T value) {
        if (this.root == null) {
            this.root = new TreeNode<T>(value);
            return;
        }
        TreeNode<T> cur = root;
        TreeNode<T> pre = null;
        while (cur != null) {
            if (value.compareTo(cur.value) == 0)
                return;
            pre = cur;
            if (value.compareTo(cur.value) > 0)
                cur = cur.right;
            else 
                cur = cur.left;
        }
        TreeNode<T> node = new TreeNode<T>(value);
        if (value.compareTo(pre.value) > 0) {
            pre.right = node;
        } else {
            pre.left = node;
        }
    }

    /*
     * 1. Find node cur.
     * 2. delete cur.
     *      2.1 find the next node of cur (inorder): nex
     *      2.2 recursively delete nex
     *      2.3 assign the value of nex to cur
     */
    public void remove(T value) {
        if (this.root == null)
            return;
        TreeNode<T> cur = root;
        TreeNode<T> pre = null;
        while (cur != null) {
            if(value.compareTo(cur.value) == 0) 
                break;
            pre = cur;
            if (value.compareTo(cur.value) > 0)
                cur = cur.right;
            else
                cur = cur.left;
        }
        // node not found
        if (cur == null)    return;
        // the num of sub nodes is 0 or 1
        if (cur.left == null || cur.right == null) {
            TreeNode<T> child = cur.left != null ? cur.left : cur.right;
            // delete node cur
            if (cur != this.root) {
                if (pre.left == cur)
                    pre.left = child;
                else
                    pre.right = child;
            } else {
                this.root = child;
            }
        } else {
            // the num of sub nodes is 2
            // get nex
            TreeNode<T> tmp = cur.right;
            while (tmp.left != null)
                tmp = tmp.left;
            remove(tmp.value);
            cur.value = tmp.value;
        }
    }
}
