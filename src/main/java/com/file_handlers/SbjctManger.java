package com.file_handlers;

import com.custom_exceptions.NoSuchSubjectException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Subject;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SbjctManger {
    ObjectMapper mapper = new ObjectMapper();
    Path filePath = Paths.get("F:\\Java\\Projects\\testM\\src\\main\\java\\com\\subject_files\\");


    public void saveSubject(Subject subject) throws IOException {
        Path path = Paths.get("F:\\Java\\Projects\\testM\\src\\main\\java\\com\\subject_files\\"+subject.getName()+".txt");
        mapper.writeValue(new File(path.toUri()),subject);
        System.out.println("Created file for "+subject.getName()+" successfully");
    }
    public ArrayList<Subject> subjectList() throws IOException {
        File folder;
        File[] listOfFiles;
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        folder = new File(filePath.toUri());
        FilenameFilter fileFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };
        listOfFiles = folder.listFiles(fileFilter);
        for (File subject:listOfFiles) {
            subjects.add(mapper.readValue(subject,Subject.class));
        }
        return subjects;

    }
    public void viewSubjects() throws IOException {
        for (Subject subject:this.subjectList()) {
            System.out.println(subject.toString());
        }
    }
    public Subject searchByName(String sub_name) throws IOException, NoSuchSubjectException {
        for (Subject subject:this.subjectList()) {
            if (subject.isEqualWithName(sub_name)){
                return subject;
            }
        }
        throw new NoSuchSubjectException("There is no subject named "+sub_name);
    }

    public void deleteSubject(String name) throws IOException, NoSuchSubjectException {
        System.out.println(this.searchByName(name));
        Path path = Paths.get("F:\\Java\\Projects\\testM\\src\\main\\java\\com\\subject_files\\"+name+".txt");
        Files.delete(path);
        System.out.println(name+" subject file deleted successfully..");
    }
}
