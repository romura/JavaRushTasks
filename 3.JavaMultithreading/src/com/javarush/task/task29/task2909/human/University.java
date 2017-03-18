package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        Student student = null;
        for(Student s : students){
            if (s.getAverageGrade() == averageGrade){
                student = s;
                break;
            }
        }
        return student;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student student = new Student("null", 0, 0);
        for (Student s : students){
            if (s.getAverageGrade() > student.getAverageGrade()){
                student = s;
            }
        }
        return student;
    }

    public Student getStudentWithMinAverageGrade(){
        Student student = getStudentWithMaxAverageGrade();

        for (Student s : students){
            if (s.getAverageGrade() < student.getAverageGrade()){
                student = s;
            }
        }
        return student;
    }

    public void expel(Student student){
        if (students.contains(student)){
            students.remove(student);
        }
        else System.out.println("net takogo studenta");
    }
}