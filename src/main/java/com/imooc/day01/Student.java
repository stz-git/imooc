package com.imooc.day01;

public class Student {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public static void main(String[] args) {
        HomeWorkArray<Student> studentExtArray = new HomeWorkArray<Student>();
        studentExtArray.addLast(new Student("kobe",100));
        studentExtArray.addLast(new Student("curry",80));
        studentExtArray.addLast(new Student("james",90));

        studentExtArray.remove(1);

        Student student = studentExtArray.get(0);
        System.out.println(student.toString());

        System.out.println(studentExtArray.toString());
    }
}
