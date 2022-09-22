package com.file_handlers;

import com.custom_exceptions.NoSuchStudentException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main_classes.Student;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class StdsMngmtSystm {

    ObjectMapper mapper = new ObjectMapper();
    Path filePath = Paths.get("F:\\Java\\Projects\\testM\\src\\main\\java\\com\\student_files\\");


    public void saveStudent(Student student) throws IOException {
        Path path = Paths.get("F:\\Java\\Projects\\testM\\src\\main\\java\\com\\student_files\\"+student.getName()+".txt");
        mapper.writeValue(new File(path.toUri()),student);
        System.out.println("Created file for "+student.getName()+" successfully");
    }
    public ArrayList<Student> studentList() throws IOException {
        File folder;
        File[] listOfFiles;
        ArrayList<Student> students = new ArrayList<Student>();

        folder = new File(filePath.toUri());
        FilenameFilter fileFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };
        listOfFiles = folder.listFiles(fileFilter);

        for (File student:listOfFiles) {
            students.add(mapper.readValue(student,Student.class));
        }
        return students;

    }
    public void viewStudents() throws IOException {

        for (Student student:this.studentList()) {
            System.out.println(student.toString());
        }
    }
    public ArrayList<Student> searchByName(String name) throws IOException, NoSuchStudentException {
        ArrayList<Student> searchResult = new ArrayList<Student>();

        for (Student student:this.studentList()) {
            if (student.isEqualWithName(name)){
                searchResult.add(student);
            }
        }
        if (searchResult.isEmpty()){
            throw new NoSuchStudentException("There is not saved Student named "+name);
        }
        return searchResult;
    }

    public void deleteStudent(String name) throws IOException, NoSuchStudentException {
        System.out.println(this.searchByName(name));
        Path path = Paths.get("F:\\Java\\Projects\\testM\\src\\main\\java\\com\\student_files\\"+name+".txt");
        Files.delete(path);
        System.out.println(name+" student file deleted successfully..");
    }
}
