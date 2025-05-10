package com;

import java.util.*;
import java.io.*;

public class Main
{
    private static final Scanner scanner = new Scanner(System.in);
    private static final School school = new School();

    public static void main(String[] args)
    {
        boolean running = true;
        while (running)
        {
            System.out.println();
            System.out.println("----- MENU -----");
            System.out.println("0. Exit");
            System.out.println("1. Add student");
            System.out.println("2. Add student's discipline");
            System.out.println("3. Show student's performance");
            System.out.println("4. Show school's performance");
            System.out.println("5. Make student take a test");
            System.out.println("6. Show student's information");
            System.out.println("7. Export data");
            System.out.println("8. Import data");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            try
            {
                switch (choice)
                {
                    case "0" -> running = false;
                    case "1" -> addStudent();
                    case "2" -> addDisciplineToStudent();
                    case "3" -> showStudentPerformance();
                    case "4" -> showSchoolPerformance();
                    case "5" -> takeTest();
                    case "6" -> school.displayAllStudentInfo();
                    case "7" -> exportData();
                    case "8" -> importData();
                    default -> System.out.println("Wrong input");
                }
            }
            catch (Exception e)
            {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addStudent()
    {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();
        school.addStudent(new Student(name));
        System.out.println("Success!");
    }

    private static void addDisciplineToStudent()
    {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine().toLowerCase();
        Student student = school.getStudentByName(name);

        if (student == null)
        {
            System.out.println("Student wasn't found");
            return;
        }

        System.out.print("Enter discipline's name: ");
        String disc = scanner.nextLine().toLowerCase();

        if (student.CheckIfContains(disc))
        {
            System.out.println("Discipline already exists");
            return;
        }
            Discipline d = new Discipline(disc);
            student.addDiscipline(d);
            System.out.println("Success!");
    }

    private static void showStudentPerformance()
    {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine().toLowerCase();
        Student student = school.getStudentByName(name);

        if (student == null)
        {
            System.out.println("Student wasn't found");
            return;
        }
        System.out.println("GPA: " + student.calculateAverageGrade());
    }

    private static void showSchoolPerformance()
    {
        System.out.println("School's GPA: " + school.calculateSchoolAverage());
    }

    private static void takeTest()
    {
        Discipline d;
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine().toLowerCase();
        Student student = school.getStudentByName(name);

        if (student == null)
        {
            System.out.println("Student was not found");
            return;
        }

        System.out.print("Enter discipline's name: ");
        String disc = scanner.nextLine().toLowerCase();

        if (student.CheckIfContains(disc))
        {
            d = student.GetDisciplineOfStudent(disc);
        }
        else
        {
            d = new Discipline(disc);
            student.addDiscipline(d);
        }

        System.out.print("Enter test grade(1-12): ");
        int grade = Integer.parseInt(scanner.nextLine());
        d.addGrade(grade);
        System.out.println("Success!");
    }

    private static void exportData() throws IOException
    {
        System.out.print("Enter format (json/csv/xml/txt): ");
        String format = scanner.nextLine().toLowerCase();

        switch (format)
        {
            case "json" -> school.exportToJson("students.json");
            case "csv" -> school.exportToCsv("students.csv");
            case "xml" -> school.exportToXml("students.xml");
            case "txt" -> school.exportToTxt("students.txt");
            default -> System.out.println("Wrong input");
        }
    }

    private static void importData() throws IOException
    {
        System.out.print("Enter format (json/csv/xml/txt): ");
        String format = scanner.nextLine().toLowerCase();

        switch (format)
        {
            case "json" -> school.importFromJson("students.json");
            case "csv" -> school.importFromCsv("students.csv");
            case "xml" -> school.importFromXml("students.xml");
            case "txt" -> school.importFromTxt("students.txt");
            default -> System.out.println("Wrong input");
        }
    }
}