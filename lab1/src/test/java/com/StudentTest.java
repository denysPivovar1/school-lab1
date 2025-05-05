package com;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class StudentTest
{
    @Test
    void testCalculateAverageGrade()
    {
        Discipline math = new Discipline("Math");
        math.setGrades(List.of(10, 9));
        Discipline eng = new Discipline("English");
        eng.setGrades(List.of(8, 10));
        Student s = new Student("Ivan");
        s.setDisciplines(List.of(math, eng));
        assertEquals(9.25, s.calculateAverageGrade(), 0.01);
    }

    @Test
    void testEmptyDisciplines()
    {
        Student s = new Student("Ivan");
        assertEquals(0.0, s.calculateAverageGrade());
    }

    @Test
    void testEqualsAndHashCode()
    {
        Student s1 = new Student("Ivan");
        Student s2 = new Student("Ivan");
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }
}