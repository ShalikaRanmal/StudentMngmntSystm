package com.file_handlers;

import com.custom_exceptions.NoSuchStudentException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Entity;
import com.models.Student;
import com.models.Subject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class EntityManager {
    ObjectMapper mapper = new ObjectMapper();

    Path subjectPath = Paths.get("F:\\Java\\Projects\\testM\\src\\main\\java\\com\\subject_files\\");
    private Path getPath(Entity entity) {
        if (entity instanceof Student){
            Path studentPath;
            if (((Student) entity).getGrade()==0){
                studentPath = Paths.get("src/main/java/com/student_files/");
            }else {
                studentPath = Paths.get("src/main/java/com/student_files/G" + ((Student) entity).getGrade());
            }
            return studentPath;
        }else {
            return this.subjectPath;
        }
    }


    public void save(Entity entity) throws IOException {
        Path path = Paths.get(this.getPath(entity)+File.separator+entity.getName()+".txt");
        mapper.writeValue(new File(path.toUri()),entity);
        System.out.println("Created file for "+entity.getName()+" successfully");
    }

    private ArrayList<Entity> entityList(File folder,Entity type) throws IOException {

        //File folder;
        File[] listOfFiles;
        ArrayList<Entity> entities = new ArrayList<Entity>();
        for (File fileEntry : folder.listFiles()){
            if(fileEntry.isDirectory()){
                entities.addAll(this.entityList(fileEntry,type));
                continue;
            }

        //folder = new File(getPath(type).toUri());
        FilenameFilter fileFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };

        ;
        if(type instanceof Student){

            entities.add(mapper.readValue(fileEntry,Student.class));

        }else {

                entities.add(mapper.readValue(fileEntry,Subject.class));

        }
        }
        return entities;
    }

    public ArrayList<Entity> entityList(Entity type) throws IOException {
        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities = this.entityList(new File(getPath(type).toUri()),type);
        return entities;
    }

    public void view(Entity type) throws IOException {

        for (Entity entity:this.entityList(new File(getPath(type).toUri()),type)) {
            System.out.println(entity.toString());
        }
    }

    public ArrayList<Entity> searchByName(String name, Entity type) throws IOException, NoSuchStudentException {
        ArrayList<Entity> searchResult = new ArrayList<Entity>();

        for (Entity entity:this.entityList(new File(getPath(type).toUri()),type)) {
            if (entity.isEqualWithName(name)){
                searchResult.add(entity);
            }
        }
        if (searchResult.isEmpty()){
            throw new NoSuchStudentException("There is not saved Student named "+name);
        }
        return searchResult;
    }
    public ArrayList<Student> searchByGrade(int grade) throws IOException, NoSuchStudentException {
        Student type = new Student();
        Student student;
        ArrayList<Student> searchResult = new ArrayList<Student>();

        for (Entity entity:this.entityList(new File(getPath(type).toUri()),type)) {
            student = ((Student)entity);
            if ((student).getGrade()==grade){
                searchResult.add(student);
            }
        }
        if (searchResult.isEmpty()){
            throw new NoSuchStudentException("There is no any students in grade "+grade);
        }
        return searchResult;
    }
    private ArrayList<Entity> duplicateFinder(String name, ArrayList<Entity> students){

        Scanner input = new Scanner(System.in);
        int grade = 0;

        System.out.println("There is greater than one students named "+name);
        System.out.println(students);
        System.out.println("\nWhat is "+name+"'s grade who want for process.");
        grade = Integer.parseInt(input.nextLine());
        //input.close();
        for (Entity std:students) {
            if ( ((Student)std).getGrade()==grade){
                students.clear();
                students.add(std);
                return students;
            }
        }
        return students;
    }

    public void addMarks(String name, String subject_name, Integer marks) throws IOException, NoSuchStudentException {
        ArrayList<Entity> students = new ArrayList<Entity>();

        students = this.searchByName(name,new Student());
        if (students.size()>1) {
            students=this.duplicateFinder(name,students);
        }
        Student student = (Student) students.get(0);
        Subject subject = (Subject) this.searchByName(subject_name,new Subject()).get(0);
        student.setMarks(subject, marks);
        this.save(student);

    }

    public void delete(String name, Entity type) throws IOException, NoSuchStudentException {
        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities = (this.searchByName(name, type));
        if (entities.size()>1){
            entities=duplicateFinder(name,entities);
        }
        Path path = Paths.get(this.getPath(entities.get(0))+File.separator+name+".txt");
        Files.delete(path);
        System.out.println(name+" student file deleted successfully..");
    }

}
