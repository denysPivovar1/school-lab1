package com;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

class Discipline
{
    private String name;
    private List<Integer> grades = new ArrayList<>();

    @JsonCreator
    public Discipline() {}

    public Discipline(String name)
    {
        this.name = name;
    }

    @JsonProperty("name")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @JsonProperty("grades")
    public List<Integer> getGrades()
    {
        return grades;
    }

    public void setGrades(List<Integer> grades)
    {
        this.grades = grades;
    }

    public void addGrade(int grade)
    {
        if (grade < 1 || grade > 12) throw new IllegalArgumentException("Wrong input");
        grades.add(grade);
    }

    public double averageGrade()
    {
        return grades.isEmpty() ? 0 : grades.stream().mapToInt(i -> i).average().orElse(0);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return Objects.equals(name, that.name) && Objects.equals(grades, that.grades);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, grades);
    }
}