package com;

import java.util.*;

class Student
{
    private String name;
    private List<Discipline> disciplines = new ArrayList<>();

    public Student(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

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