package com.operations;

import com.custom_exceptions.FilesAreEmptyException;
import com.custom_exceptions.NoSuchStudentException;
import com.file_handlers.EntityManager;
import com.file_handlers.SbjctManger;
import com.file_handlers.StdsMngmtSystm;
import com.models.Entity;
import com.models.Student;
import com.models.Subject;

import java.io.IOException;
import java.util.*;

public class Grades implements Comparator<Student> {
    public Grades(){ }
    //StdsMngmtSystm sms = new StdsMngmtSystm();
    //SbjctManger sm = new SbjctManger();
    EntityManager em = new EntityManager();

    //ArrayList<Entity> students = new ArrayList<Entity>();
    //ArrayList<Entity> subjects = new ArrayList<Entity>();

    HashMap<Student,Integer>  total_grades = new HashMap<Student,Integer>();


    public int findTotal(Student student){
        int total = 0;
        for (int value: student.getMarks().values()){
            total +=value;
        }
        return total;
    }
    public void findGrades(int grade) throws IOException, FilesAreEmptyException, NoSuchStudentException {
        //students = sms.studentList();
        //subjects = sm.subjectList();
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Subject> subjects = new ArrayList<Subject>();

        for (Entity std: em.searchByGrade(grade)) {
            students.add((Student) std);
        }
        for (Entity sbj:em.entityList(new Subject())) {
            subjects.add((Subject) sbj);
        }

        int i = 1;

        if(students.isEmpty() || subjects.isEmpty()){
            throw new FilesAreEmptyException("There is no files save students or subjects");
        }
        Collections.sort(students,new Grades());

        for (Student student:students) {
            Integer total = findTotal(student);
            String name =student.getName();

            System.out.println(i+". "+name+" | totla marks - " +total);
            i ++;
        }
    }

    @Override
    public int compare(Student o1, Student o2) {
        Integer total_1 = this.findTotal(o1);
        Integer total_2 = this.findTotal(o2);
        return total_2.compareTo(total_1);
    }
}
