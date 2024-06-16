import java.util.List;

import Entity.Person;

public class Test {

    public static void main(String[] args) {
        Person p = new Person("Peter", 18, 98);
        System.out.println(p.toString());
        System.out.println("hello world");

        TreeNode<Person> node = new TreeNode<Person>(p);
        AVLTree<Person> avl = new AVLTree<Person>(node);
        avl.inorderTraversal(node);
        List<Person> list = avl.list;
        for(Person ps : list) {
            System.out.println(ps.toString());
        }
    }
}
