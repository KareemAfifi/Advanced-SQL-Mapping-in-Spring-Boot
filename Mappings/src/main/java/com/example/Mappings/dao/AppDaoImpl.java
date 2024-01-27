package com.example.Mappings.dao;

import com.example.Mappings.entity.Course;
import com.example.Mappings.entity.Instructor;
import com.example.Mappings.entity.InstructorDetail;
import com.example.Mappings.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AppDaoImpl implements AppDAO{
    private EntityManager entityManager;

    @Autowired
    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor x = findInstructorById(id);
        if(x!=null) {
            entityManager.remove(x);
        }
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    public void deleteInstructorDetailById(int id) {
        InstructorDetail tmp = entityManager.find(InstructorDetail.class, id);
        entityManager.remove(tmp);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("From Course where instructor.id =:data ", Course.class);
        query.setParameter("data", id);
        return query.getResultList();

    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i From Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail where i.id =:data ", Instructor.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
        entityManager.persist(course);


    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
        TypedQuery<Course> query =entityManager.createQuery("select c from Course c JOIN FETCH c.students where c.id =:data", Course.class);
        query.setParameter("data",id);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s JOIN FETCH s.courses where s.id=:data",Student.class);
        query.setParameter("data",id);
        return query.getSingleResult();
    }

}
