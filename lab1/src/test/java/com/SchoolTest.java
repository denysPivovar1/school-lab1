package com;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.io.*;

class SchoolTest
{
    @Test
    void testCalculateSchoolAverage()
    {
        School school = new School();
        Student s1 = new Student("Anna");
        Discipline d1 = new Discipline("Math");
        d1.setGrades(List.of(12, 10));
        s1.setDisciplines(List.of(d1));

        Student s2 = new Student("Ivan");
        Discipline d2 = new Discipline("Math");
        d2.setGrades(List.of(6, 6));
        s2.setDisciplines(List.of(d2));

        school.addStudent(s1);
        school.addStudent(s2);

        assertEquals(8.5, school.calculateSchoolAverage(), 0.01);
    }

    @Test
    void testExportImportJson() throws IOException
    {
        School school = new School();
        Student student = new Student("Test");
        Discipline d = new Discipline("TestSubject");
        d.setGrades(List.of(10));
        student.setDisciplines(List.of(d));
        school.addStudent(student);

        String filename = "test_export.json";
        school.exportToJson(filename);

        School newSchool = new School();
        newSchool.importFromJson(filename);

        assertEquals(1, newSchool.getStudents().size());
        assertEquals("Test", newSchool.getStudents().get(0).getName());

        new File(filename).delete(); // clean up
    }

    @Test
    void testGetStudentByName()
    {
        School school = new School();
        Student s = new Student("Dmytro");
        school.addStudent(s);
        assertEquals(s, school.getStudentByName("Dmytro"));
        assertNull(school.getStudentByName("Unknown"));
    }
}