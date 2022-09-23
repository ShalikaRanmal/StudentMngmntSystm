package com.file_handlers;

import com.custom_exceptions.NoSuchStudentException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Entity;
import com.models.Student;
import com.models.Subject;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class EntityManager {
    ObjectMapper mapper = new ObjectMapper();

    Path subjectPath = Paths.get("F:\\Java\\Projects\\testM\\src\\main\\java\\com\\subject_files\\");
    Path studentPath = Paths.get("F:\\Java\\Projects\\testM\\src\\main\\java\\com\\student_files\\");
    private Path getPath(Entity entity){
        if (entity instanceof Student){
            return this.studentPath;
        }else {
            return this.subjectPath;
        }
    }


    public void save(Entity entity) throws IOException {
        Path path = Paths.get(this.getPath(entity)+File.separator+entity.getName()+".txt");
        mapper.writeValue(new File(path.toUri()),entity);
        System.out.println("Created file for "+entity.getName()+" successfully");
    }

    public ArrayList<Entity> entityList(Entity type) throws IOException {
        File folder;
        File[] listOfFiles;
        ArrayList<Entity> entities = new ArrayList<Entity>();

        folder = new File(getPath(type).toUri());
        FilenameFilter fileFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };
        listOfFiles = folder.listFiles(fileFilter);
        if(type instanceof Student){
        for (File entity:listOfFiles) {
            entities.add(mapper.readValue(entity,Student.class));
        }
        }else {
            for (File entity:listOfFiles) {
                entities.add(mapper.readValue(entity,Subject.class));
            }
        }
        return entities;
    }

    public void view(Entity type) throws IOException {

        for (Entity entity:this.entityList(type)) {
            System.out.println(entity.toString());
        }
    }

    public ArrayList<Entity> searchByName(String name, Entity type) throws IOException, NoSuchStudentException {
        ArrayList<Entity> searchResult = new ArrayList<Entity>();

        for (Entity entity:this.entityList(type)) {
            if (entity.isEqualWithName(name)){
                searchResult.add(entity);
            }
        }
        if (searchResult.isEmpty()){
            throw new NoSuchStudentException("There is not saved Student named "+name);
        }
        return searchResult;
    }

    public void delete(String name, Entity type) throws IOException, NoSuchStudentException {
        System.out.println(this.searchByName(name, type));
        Path path = Paths.get(this.getPath(type)+File.separator+name+".txt");
        Files.delete(path);
        System.out.println(name+" student file deleted successfully..");
    }

}
