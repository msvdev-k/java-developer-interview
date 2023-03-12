package org.msvdev.example.hibernate;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        int N = 10; // 1000;
        List<Student> students = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            students.add(new Student("student " + i, 2 + i % 4));
        }


        //////////////////////////////////////////////////////////
        // INSERT
        //////////////////////////////////////////////////////////
        studentDAO.insert(new Student("st", 10));
        studentDAO.insert(students);


        //////////////////////////////////////////////////////////
        // SELECT
        //////////////////////////////////////////////////////////
        System.out.println("\n===SELECT============================");
        System.out.println(studentDAO.findByID(5));

        System.out.println("\n===SELECT============================");
        studentDAO.findAll().forEach(System.out::println);


        System.out.println("\n===============================");
        //////////////////////////////////////////////////////////
        // UPDATE
        //////////////////////////////////////////////////////////
        Student student = studentDAO.findByID(7);
        student.setName("updated");
        student.setMark(15);

        studentDAO.update(student);

        System.out.println("\n===UPDATE============================");
        studentDAO.findAll().forEach(System.out::println);


        System.out.println("\n===============================");
        //////////////////////////////////////////////////////////
        // DELETE
        //////////////////////////////////////////////////////////
        studentDAO.delete(3);
        studentDAO.delete(9);

        System.out.println("\n===DELETE============================");
        studentDAO.findAll().forEach(System.out::println);
    }
}