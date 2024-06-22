package com.ph.test;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.ph.Entity.Person;
import com.ph.Structure.Tree.AVLTree;
import com.ph.Structure.Tree.TreeNode;
import com.ph.db.DatabaseUtils;


public class Test {

    public static void main(String[] args) {
        Person p = new Person("Peter", 18, BigDecimal.valueOf(98));
        Person p2 = new Person("Jack", 20, BigDecimal.valueOf(88));
        System.out.println(p.toString());
        System.out.println("hello world");

        List<Person> list = new LinkedList<>();
        TreeNode<Person> node = new TreeNode<Person>(p);
        AVLTree<Person> avl = new AVLTree<Person>(null);

        try (Connection conn = DatabaseUtils.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Person");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                BigDecimal score = rs.getBigDecimal("score");
                Person person = new Person(name, age, score);
                avl.insert(person);
                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // for(Person e : list) {
        //     System.out.println(e.toString());
        //     avl.insert(e);
        // }
        list.clear();
        avl.inOrder(list);
        System.out.println("Now, inorder traversal of Tree");
        for(Person ps : list) {
            System.out.println(ps.toString());
        }
        Person sp = new Person("Utaha", 30, BigDecimal.valueOf(95));
        TreeNode<Person> tn = avl.search(sp);
        if (tn != null) System.out.println("find it! " + tn.value.toString());
        else System.out.println("not found");
    }
}
