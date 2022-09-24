package org.example;

import com.custom_exceptions.FilesAreEmptyException;
import com.custom_exceptions.NoSuchStudentException;
import com.custom_exceptions.NoSuchSubjectException;
import com.file_handlers.EntityManager;
import com.models.Entity;
import com.models.Student;
import com.models.Subject;
import com.operations.Display;
import com.operations.Grades;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Path filePath = Paths.get("F:\\Java\\Projects\\testM\\src\\main\\java\\mytext.txt");
        //Path filePath = Paths.get("F:\\Java\\Projects\\testM\\target");
        Display display = new Display();
        Scanner input = new Scanner(System.in);
        //StdsMngmtSystm sms = new StdsMngmtSystm();
        //SbjctManger sm = new SbjctManger();
        EntityManager em = new EntityManager();
        Grades grades = new Grades();

        while (true) {
            try {
                display.print("\nWhat you want to do?");
                display.print("1. Manage Students");
                display.print("2. Manage Subjects");
                display.print("3. Find Grades");
                display.print("4. Exit");
                int in_1 = 0;
                in_1 = Integer.parseInt(input.nextLine());

                if (in_1 == 1) {
                    display.print("\nStudent Manager");
                    display.print("1.Add Student");
                    display.print("2.View Student");
                    display.print("3.Find student");
                    display.print("4.Add Marks For Subject");
                    display.print("5.Delete student");
                    display.print("6.Back to main menu");
                    int in_1_2 = 0;
                    in_1_2 = Integer.parseInt(input.nextLine());

                    try {
                        if (in_1_2 == 1) {
                            String name;
                            int age = 0;
                            int grade = 0;

                            display.print("\nAdd New Student");
                            display.print("Enter the Student Name");
                            name = input.nextLine();

                            display.print("Enter the Student's Age");
                            age = Integer.parseInt(input.nextLine());

                            display.print("Enter the Student's Grade");
                            grade = Integer.parseInt(input.nextLine());

                            Student std = new Student(name, age, grade);
                            display.print("\nAdding a Student---------------------");
                            em.save(std);
                            continue;

                        } else if (in_1_2 == 2) {
                            display.print("\nRegistered Students list------------------");
                            em.view(new Student());
                            continue;
                        } else if (in_1_2 == 3) {
                            ArrayList<Entity> searchResult = new ArrayList<Entity>();
                            String name;

                            System.out.println("\nStudent search by name --------------------");
                            display.print("Enter name of the Student");
                            name = input.nextLine();
                            searchResult = em.searchByName(name, new Student());
                            for (Entity student : searchResult) {
                                student = student;
                                System.out.println(student.toString());
                            }
                            continue;
                        } else if (in_1_2 == 4) {
                            String name;
                            String sub_name;
                            int marks = 0;

                            display.print("\nAssign marks for a student");
                            display.print("Student name");
                            name = input.nextLine();
                            display.print("Subject name");
                            sub_name = input.nextLine();
                            display.print("What is marks for this subject");
                                marks = Integer.parseInt(input.nextLine());

                            em.addMarks(name,sub_name,marks);
                            continue;
                        } else if (in_1_2 == 5) {
                            String name;

                            display.print("\nSudent file deleting----------------------");
                            display.print("What is name of the student you want delete");
                            name = input.nextLine();
                            em.delete(name, new Student());
                            continue;
                        } else if (in_1_2 == 6) {
                            continue;
                        }
                    } catch (IOException e) {
                        display.print("Error " + e);
                    }

                } else if (in_1 == 2) {
                    display.print("\nSubject Manager");
                    display.print("1.Add a Subject");
                    display.print("2.View Subject List");
                    display.print("3.Delete a Subject");
                    display.print("4.Back to Main Menu");
                    int in_2_2 = 0;
                        in_2_2 = Integer.parseInt(input.nextLine());

                    try {
                        if (in_2_2 == 1) {
                            String name;

                            display.print("Enter name of new subject");
                            name = input.nextLine();
                            Subject subject = new Subject(name);
                            System.out.println("\nAdding subjects-----------------");
                            em.save(subject);
                            continue;
                        } else if (in_2_2 == 2) {
                            display.print("\nRegisterd subjects------------------");
                            em.view(new Subject());
                            continue;
                        } else if (in_2_2 == 3) {
                            String name;

                            System.out.println("\ndeleting a subject");
                            display.print("What is name of subject want to be deleted");
                            name = input.nextLine();
                            em.delete(name, new Subject());
                            continue;
                        }
                    } catch (IOException e) {
                        display.print("Error " + e);
                    }
                } else if (in_1 == 3) {
                    int grade = 0;

                    display.print("Find grade-------------");
                    display.print("In what Grade you want to know Ranks(1 to 13 grades)");
                    grade = Integer.parseInt(input.nextLine());
                    try {
                        grades.findGrades(grade);
                    } catch (IOException e) {
                        display.print("Error " + e);
                    }
                } else if (in_1 == 4) {
                    display.print("good bye");
                    break;
                }
            }catch (NumberFormatException ex) {
                display.print("wrong input.Input was not a number");
            //}catch (NoSuchSubjectException ex){
            //    display.print(ex+"..Please check again.");
            }
            catch (NoSuchStudentException ex) {
                display.print(ex.toString());
            }catch (FilesAreEmptyException ex){
                display.print(ex.toString());
            }catch (Exception ex){
                display.print("Error "+ex);
            }
        }
    }
}