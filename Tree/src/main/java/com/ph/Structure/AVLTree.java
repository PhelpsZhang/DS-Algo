package com.ph.Structure;
import java.util.List;

public class AVLTree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public AVLTree(TreeNode<T> node) {
        this.root = node;
    }

    public AVLTree() {
        this.root = null;
    }

    /* Get Node Height */
    public int getNodeHeight(TreeNode<T> node) {
        return node == null ? -1 : node.height;
    }

    /* Update Node Height */
    private void updateNodeHeight(TreeNode<T> node) {
        // node height = Max(the height of subtrees of the node) + 1
        node.height = Math.max(getNodeHeight(node.left), getNodeHeight(node.right)) + 1;
    }

    public int getBalanceFactor(TreeNode<T> node) {
        if (node == null)   return 0;
        // Definition: height of left subtree - height of right subtree
        return getNodeHeight(node.left) - getNodeHeight(node.right);
    }

    /* Right Rotation. Return root node of Subtree after rotating. */
    private TreeNode<T> rightRotate(TreeNode<T> node) {
        TreeNode<T> child = node.left;
        TreeNode<T> grandChild = child.right;
        // right rotate the node 
        child.right = node;
        node.left = grandChild;
        // update node height;
        updateNodeHeight(node);
        updateNodeHeight(child);
        return child;
    }

    /* Left Rotation. Return root node of Subtree after rotating. */
    private TreeNode<T> leftRotate(TreeNode<T> node) {
        TreeNode<T> child = node.right;
        TreeNode<T> grandChild = child.left;
        // right rotate the node 
        child.left = node;
        node.right = grandChild;
        // update node height;
        updateNodeHeight(node);
        updateNodeHeight(child);
        return child;
    }

    public TreeNode<T> rotate(TreeNode<T> node) {
        int balanceFactor = getBalanceFactor(node);
        // 左偏树
        if (balanceFactor > 1) {    // 在案例中，事实上只会是2
            if (getBalanceFactor(node.left) >=0 ) {
                // right rotate
                return rightRotate(node);
            } else {
                // Left-Right rotate
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        // 右偏树
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) <= 0) {
                // left rotate
                return leftRotate(node);
            } else {
                // Right-Left rotate
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        // 满足平衡条件，直接返回
        return node;
    }

    // insert node
    public void insert(T value) {
        this.root = insertHelper(root, value);
    }

    // recursion insert function
    private TreeNode<T> insertHelper(TreeNode<T> node, T value) {
        if (node == null) return new TreeNode<T>(value);
        // find the position to insert
        if (value.compareTo(node.value) < 0)
            node.left = insertHelper(node.left, value);
        else if (value.compareTo(node.value) > 0)
            node.right = insertHelper(node.right, value);
        else
            return node;
        // update node height
        updateNodeHeight(node);
        // rotate adjust. return the root node of subtree after rotating.
        node = rotate(node);
        return node;
    }

    public TreeNode<T> search(T value) {
        TreeNode<T> cur = this.root;
        while(cur != null) {
            if (cur.value.compareTo(value) < 0) {
                cur = cur.right;
            } else if (cur.value.compareTo(value) > 0) {
                cur = cur.left;
            } else 
                break;
        }
        return cur;
    }

    public void remove(T value) {
        this.root = removeHelper(root, value);
    }

    private TreeNode<T> removeHelper(TreeNode<T> node, T value) {
        if (node == null) return null;
        // find the position to delete
        if (value.compareTo(node.value) < 0)
            node.left = removeHelper(node.left, value);
        else if (value.compareTo(node.value) > 0)
            node.right = removeHelper(node.right, value);
        else {
            if (node.left == null || node.right == null) {
                // 1 or 0 sub nodes
                TreeNode<T> child = node.left != null ? node.left : node.right;
                if (child == null) return null;
                else node = child;
            } else {
                // sub nodes = 2
                // find min next node and delete it, then subtitute it for current node.
                TreeNode<T> tmp = node.right;
                while(tmp.left != null) tmp = tmp.left;
                node.right = removeHelper(node.right, tmp.value);
                node.value = tmp.value;
            }
        }
        updateNodeHeight(node);
        node = rotate(node);
        return node;
    }

    public void preOrder(List<T> list) {
        preorderTraversal(root, list);
    }

    public void inOrder(List<T> list) {
        inorderTraversal(root, list);
    }

    public void postOrder(List<T> list) {
        postorderTraversal(root, list);
    }


    public void preorderTraversal(TreeNode<T> node, List<T> list) {
        if(node == null) return;
        list.add(node.value);
        inorderTraversal(node.left, list);
        inorderTraversal(node.right, list);
    }

    private void inorderTraversal(TreeNode<T> node, List<T> list) {
        if(node == null) return;  
        inorderTraversal(node.left, list);
        list.add(node.value);
        inorderTraversal(node.right, list);
    }

    public void postorderTraversal(TreeNode<T> node, List<T> list) {
        if(node == null) return;  
        inorderTraversal(node.left, list);
        inorderTraversal(node.right, list);
        list.add(node.value);
    }


}
