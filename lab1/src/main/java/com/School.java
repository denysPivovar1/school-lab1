package com;

import java.io.*;
import java.util.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

class School
{
    private final ArrayList<Student> students = new ArrayList<>();

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
        for (Student s : students)
        {
            if (s.getName().equalsIgnoreCase(name))
            {
                return s;
            }
        }
        return null;
    }

    public double calculateSchoolAverage()
    {
        if (students.isEmpty()) return 0;
        double sum = 0;
        for (Student s : students)
        {
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

        for (Student s: students)
        {
            System.out.println("Student: " + s.getName());
            if (s.getDisciplines().isEmpty())
            {
                System.out.println("No Disciplines");
                continue;
            }

            for (Discipline d : s.getDisciplines())
            {
                System.out.println("Discipline: " + d.getName() + ", grades: " + d.getGrades() + ", GPA: " + d.averageGrade());
            }
        }
    }

    public void sortStudentsByAverageGrade()
    {
        students.sort(Comparator.comparingDouble(Student::calculateAverageGrade).reversed());
    }

    public void exportToJson(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        sortStudentsByAverageGrade();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), students);
    }

    public void importFromJson(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Student[] imported = mapper.readValue(new File(filename), Student[].class);
        students.clear();
        students.addAll(Arrays.asList(imported));
    }

    public void exportToCsv(String filename) throws IOException
    {
        sortStudentsByAverageGrade();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
        {
            writer.write("Student,Discipline,Grades,GPA");
            writer.newLine();

            for (Student s : students)
            {
                if (s.getDisciplines().isEmpty())
                {
                    writer.write(s.getName() + ",,,");
                    writer.newLine();
                }
                else
                {
                    for (Discipline d : s.getDisciplines())
                    {
                        String grades = d.getGrades().toString().replaceAll("[\\[\\]]", "");
                        writer.write(s.getName() + "," + d.getName() + "," + grades + "," + d.averageGrade());
                        writer.newLine();
                    }
                }
            }
        }
    }

    public void importFromCsv(String filename) throws IOException
    {
        students.clear();
        Map<String, Student> studentMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",", -1);
                if (parts.length < 4)
                    continue;

                String studentName = parts[0].trim();
                String disciplineName = parts[1].trim();
                String gradesStr = parts[2].trim();

                Student student = studentMap.computeIfAbsent(studentName, Student::new);
                if (!disciplineName.isEmpty())
                {
                    Discipline discipline = new Discipline(disciplineName);
                    if (!gradesStr.isEmpty())
                    {
                        for (String g : gradesStr.split("\\s*,\\s*"))
                        {
                            discipline.addGrade(Integer.parseInt(g));
                        }
                    }
                    student.addDiscipline(discipline);
                }

                if (!students.contains(student))
                {
                    students.add(student);
                }
            }
        }
    }

    public void exportToXml(String filename) throws IOException
    {
        sortStudentsByAverageGrade();
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), students);
    }

    public void importFromXml(String filename) throws IOException
    {
        XmlMapper xmlMapper = new XmlMapper();
        Student[] imported = xmlMapper.readValue(new File(filename), Student[].class);
        students.clear();
        students.addAll(Arrays.asList(imported));
    }

    public void exportToTxt(String filename) throws IOException
    {
        sortStudentsByAverageGrade();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
        {
            for (Student s : students)
            {
                writer.write("Student: " + s.getName());
                writer.newLine();
                if (s.getDisciplines().isEmpty())
                {
                    writer.write("No disciplines");
                }
                else
                {
                    for (Discipline d : s.getDisciplines())
                    {
                        writer.write(String.format("Discipline: %s, Grades: %s, GPA: %.2f", d.getName(), d.getGrades(), d.averageGrade()));
                    }
                }
                writer.newLine();
            }
        }
    }

    public void importFromTxt(String filename) throws IOException
    {
        students.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            Student currentStudent = null;

            while ((line = reader.readLine()) != null)
            {
                line = line.trim();

                if (line.startsWith("Student: "))
                {
                    String name = line.substring(9).trim();
                    currentStudent = new Student(name);
                    students.add(currentStudent);
                }
                else if (line.startsWith("Discipline: ") && currentStudent != null)
                {
                    String[] parts = line.substring(11).split(", Grades: |, GPA: ");
                    String discName = parts[0].trim();
                    String gradesStr = parts[1].replaceAll("[\\[\\]\\s]", ""); // remove brackets
                    Discipline discipline = new Discipline(discName);

                    if (!gradesStr.isEmpty())
                    {
                        for (String g : gradesStr.split(","))
                        {
                            discipline.addGrade(Integer.parseInt(g));
                        }
                    }
                    currentStudent.addDiscipline(discipline);
                }
            }
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return Objects.equals(students, school.students);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(students);
    }
}