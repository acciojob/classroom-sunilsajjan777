package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@Component
public class StudentRepository {

    static HashMap<String,Student> studentdb =new HashMap<>();
    static HashMap<String,Teacher> teacherDb=new HashMap<>();
    static HashMap<Student,Teacher> studentTeacherDb =new HashMap<>();

    public void addStudent(Student student){
        studentdb.put(student.getName(),student);
    }
    public void addTeacher(Teacher teacher){

        teacherDb.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student,String teacher){
        studentTeacherDb.put(studentdb.get(student),teacherDb.get(teacher));
    }

    public Student getStudentByName(String name){
       return studentdb.get(name);
    }
    public Teacher getTeacherByName(String name){
        return teacherDb.get(name);
    }


    public List<String> getStudentsByTeacherName(String name){

        List<String> listOfStudents = new ArrayList<>();
        for(Student s: studentTeacherDb.keySet()){
            if(studentTeacherDb.get(s).getName().equals(name))
                listOfStudents.add(s.getName());
        }
        return listOfStudents;
    }

    public List<String> getAllStudents()
    {
        List<String> students=new ArrayList<>();

        for (Student student :studentdb.values() ){
            students.add(student.getName());
        }
        return students;
    }

    public static void deleteTeacherByName(String teacher){

        teacherDb.remove(teacher);
        List<Student> students= new ArrayList<>();

        for (Student s: studentTeacherDb.keySet())
            if (studentTeacherDb.get(s).getName().equals(teacher))
            {
                students.add(s);

            }

        for (Student s:students){
            studentdb.remove(s.getName());
            studentTeacherDb.remove(s);
        }

    }

    public void deleteAllTeachers(){

        for(Student s: studentTeacherDb.keySet()){
            studentdb.remove(s.getName());
        }

        teacherDb.clear();
        studentTeacherDb.clear();

    }
}
