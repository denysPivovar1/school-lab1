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
        for (Discipline d : disciplines) {
            sum += d.averageGrade();
        }
        return sum / disciplines.size();
    }

    public Discipline getOrCreateDiscipline(String discName) {
        for (Discipline d : disciplines) {
            if (d.getName().equalsIgnoreCase(discName)) return d;
        }
        Discipline newDisc = new Discipline(discName);
        disciplines.add(newDisc);
        return newDisc;
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