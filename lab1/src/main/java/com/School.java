package com;

import java.io.*;
import java.util.*;
import com.fasterxml.jackson.databind.*;

class School
{
    private final List<Student> students = new ArrayList<>();

    public List<Student> getStudents()
    {
        return students;
    }

    public void addStudent(Student student)
    {
        students.add(student);
    }

    public void removeStudent(String name)
    {
        students.removeIf(s -> s.getName().equals(name));
    }

    public Student getStudentByName(String name)
    {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    public double calculateSchoolAverage()
    {
        if (students.isEmpty()) return 0;
        double sum = 0;
        for (Student s : students) {
            sum += s.calculateAverageGrade();
        }
        return sum / students.size();
    }

    public void displayAllStudentInfo()
    {
        if (students.isEmpty())
        {
            System.out.println("No students found");
            return;
        }

        for (Student student : students)
        {
            System.out.println("Student: " + student.getName());
            if (student.getDisciplines().isEmpty())
            {
                System.out.println("No Disciplines");
                continue;
            }

            for (Discipline d : student.getDisciplines())
            {
                System.out.println("Discipline: " + d.getName() + ", grades: " + d.getGrades() + ", GPA: " + d.averageGrade());
            }
        }
    }

    public void sortStudentsByAverageGrade()
    {
        students.sort(Comparator.comparingDouble(Student::calculateAverageGrade).reversed());
    }

    public void exportToJson(String filename) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        sortStudentsByAverageGrade();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), students);
    }

    public void importFromJson(String filename) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        Student[] imported = mapper.readValue(new File(filename), Student[].class);
        students.clear();
        students.addAll(Arrays.asList(imported));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof School school)) return false;
        return Objects.equals(students, school.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }
}