package com;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class DisciplineTest
{
    @Test
    void testAverageGrade() {
        Discipline d = new Discipline("Math");
        d.setGrades(Arrays.asList(10, 11, 12));
        assertEquals(11.0, d.averageGrade(), 0.01);
    }

    @Test
    void testEmptyGrades() {
        Discipline d = new Discipline("Math");
        assertEquals(0.0, d.averageGrade());
    }

    @Test
    void testAddInvalidGrade() {
        Discipline d = new Discipline("Math");
        assertThrows(IllegalArgumentException.class, () -> d.addGrade(15));
    }
}