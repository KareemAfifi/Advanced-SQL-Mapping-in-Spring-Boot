package com.example.Mappings.dao;

import com.example.Mappings.entity.Course;
import com.example.Mappings.entity.Instructor;
import com.example.Mappings.entity.InstructorDetail;
import com.example.Mappings.entity.Student;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    List<Course> findCoursesByInstructorId(int id);
    Instructor findInstructorByIdJoinFetch(int id);

    void updateInstructor(Instructor instructor);
    void saveCourse(Course course);
    Course findCourseAndStudentsByCourseId(int id);
    Student findStudentAndCoursesByStudentId(int id);



}
