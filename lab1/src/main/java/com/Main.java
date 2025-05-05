package com;

import java.util.*;
import java.io.*;

public class Main
{
    private static final Scanner scanner = new Scanner(System.in);
    private static final School school = new School();

    public static void main(String[] args)
    {
        //Сonsole menu
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
            System.out.println("7. Export to JSON");
            System.out.println("8. Import from JSON");
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
                    default -> System.out.println("Wrong option");
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
        System.out.println("Student added!");
    }

    private static void addDisciplineToStudent()
    {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine().toLowerCase();
        Student student = school.getStudentByName(name);

        if (student == null)
        {
            System.out.println("Student is not found");
            return;
        }

        System.out.print("Enter discipline name: ");
        String disc = scanner.nextLine().toLowerCase();

        if (student.CheckIfContains(disc))
        {
            System.out.println("Discipline already exists");
        }
        else
        {
            Discipline d = new Discipline(disc);
            student.addDiscipline(d);
            System.out.println("Discipline added!");
        }
    }

    private static void showStudentPerformance()
    {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();
        Student student = school.getStudentByName(name);
        if (student == null)
        {
            System.out.println("Student is not found");
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
            System.out.println("Student is not found");
            return;
        }

        System.out.print("Enter discipline name: ");
        String disc = scanner.nextLine().toLowerCase();
        if (!student.CheckIfContains(disc))
        {
            d = new Discipline(disc);
            student.addDiscipline(d);
        }
        else
        {
            d = student.GetDisciplineOfStudent(disc);
        }

        System.out.print("Enter test grade: (1–12): ");
        int grade = Integer.parseInt(scanner.nextLine());
        d.addGrade(grade);
        System.out.println("Grade was added");
    }

    private static void exportData() throws IOException
    {
        school.exportToJson("students.json");
        System.out.println("Export completed!");
    }

    private static void importData() throws IOException
    {
        school.importFromJson("students.json");
        System.out.println("Import completed!");
    }
}