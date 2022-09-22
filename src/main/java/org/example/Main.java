package org.example;

import com.custom_exceptions.FilesAreEmptyException;
import com.custom_exceptions.NoSuchStudentException;
import com.custom_exceptions.NoSuchSubjectException;
import com.file_handlers.SbjctManger;
import com.file_handlers.StdsMngmtSystm;
import com.main_classes.Student;
import com.main_classes.Subject;
import com.operations.Display;
import com.operations.Grades;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Path filePath = Paths.get("F:\\Java\\Projects\\testM\\src\\main\\java\\mytext.txt");
        //Path filePath = Paths.get("F:\\Java\\Projects\\testM\\target");
        Display display = new Display();
        Scanner input = new Scanner(System.in);
        StdsMngmtSystm sms = new StdsMngmtSystm();
        SbjctManger sm = new SbjctManger();
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
                    display.print("wrong input.Input was not a number");

                    try {
                        if (in_1_2 == 1) {
                            display.print("\nAdd New Student");
                            display.print("Enter the Student Name");
                            String name = input.nextLine();
                            display.print("Enter the Student's Age");
                            int age = 0;
                                age = Integer.parseInt(input.nextLine());
                            Student std = new Student(name, age);
                            display.print("\nAdding a Student---------------------");
                            sms.saveStudent(std);
                            continue;
                        } else if (in_1_2 == 2) {
                            display.print("\nRegistered Students list------------------");
                            sms.viewStudents();
                            continue;
                        } else if (in_1_2 == 3) {
                            ArrayList<Student> searchResult = new ArrayList<Student>();
                            String name;

                            System.out.println("\nStudent search by name --------------------");
                            display.print("Enter name of the Student");
                            name = input.nextLine();
                            searchResult = sms.searchByName(name);
                            for (Student student : searchResult) {
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

                            Student student = sms.searchByName(name).get(0);
                            Subject subject = sm.searchByName(sub_name);

                            student.setMarks(subject, marks);
                            sms.saveStudent(student);
                            continue;
                        } else if (in_1_2 == 5) {
                            String name;

                            display.print("\nSudent file deleting----------------------");
                            display.print("What is name of the student you want delete");
                            name = input.nextLine();
                            sms.deleteStudent(name);
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
                    int in_2_2 = 0;
                        in_2_2 = Integer.parseInt(input.nextLine());

                    try {
                        if (in_2_2 == 1) {
                            String name;

                            display.print("Enter name of new subject");
                            name = input.nextLine();
                            Subject subject = new Subject(name);
                            System.out.println("\nAdding subjects-----------------");
                            sm.saveSubject(subject);
                            continue;
                        } else if (in_2_2 == 2) {
                            display.print("\nRegisterd subjects------------------");
                            sm.viewSubjects();
                            continue;
                        } else if (in_2_2 == 3) {
                            String name;

                            System.out.println("\ndeleting a subject");
                            display.print("What is name of subject want to be deleted");
                            name = input.nextLine();
                            sm.deleteSubject(name);
                            continue;
                        }
                    } catch (IOException e) {
                        display.print("Error " + e);
                    }
                } else if (in_1 == 3) {
                    display.print("Find grade-------------");
                    try {
                        grades.findGrades();
                    } catch (IOException e) {
                        display.print("Error " + e);
                    }
                } else if (in_1 == 4) {
                    display.print("good bye");
                    break;
                }
            }catch (NumberFormatException ex) {
                display.print("wrong input.Input was not a number");
            }catch (NoSuchSubjectException ex){
                display.print(ex+"..Please check again.");
            }catch (NoSuchStudentException ex) {
                display.print(ex.toString());
            }catch (FilesAreEmptyException ex){
                display.print(ex.toString());
            }catch (Exception ex){
                display.print("Error "+ex);
            }
        }
    }
}