package com;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

class Student
{
    private String name;
    private List<Discipline> disciplines = new ArrayList<>();

    public Student() {}

    public Student(String name)
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

    @JsonProperty("disciplines")
    public List<Discipline> getDisciplines()
    {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines)
    {
        this.disciplines = disciplines;
    }

    public void addDiscipline(Discipline d)
    {
        disciplines.add(d);
    }

    public void removeDiscipline(Discipline d)
    {
        disciplines.remove(d);
    }

    public double calculateAverageGrade()
    {
        if (disciplines.isEmpty()) return 0;
        double sum = 0;
        for (Discipline d : disciplines)
        {
            sum += d.averageGrade();
        }
        return sum / disciplines.size();
    }

    public boolean CheckIfContains(String name)
    {
        for (Discipline d: disciplines)
        {
            if (d.getName().equals(name)) return true;
        }
        return false;
    }

    public Discipline GetDisciplineOfStudent(String name)
    {
        for (Discipline d: disciplines)
        {
            if (d.getName().equals(name)) return d;
        }
        return null;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}