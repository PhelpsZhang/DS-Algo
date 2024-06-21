package com.ph.Structure;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ph.Entity.Person;
import com.ph.db.DatabaseUtils;

public class BSTreeTest {
    
    @Test
    public void testBST() {
        // test function must be Public
        System.out.println("Test BSTree Code");

        List<Person> list = new LinkedList<>();
        BSTree<Person> bst = new BSTree<>();

        try (Connection conn = DatabaseUtils.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Person");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                BigDecimal score = rs.getBigDecimal("score");
                Person person = new Person(name, age, score);
                bst.insert(person);
                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        list.clear();
        bst.inOrder(list);
        for (Person person : list) {
            System.out.println(person.toString());
        }
    }
}
