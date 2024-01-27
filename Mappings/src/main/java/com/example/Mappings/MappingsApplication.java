package com.example.Mappings;

import com.example.Mappings.dao.AppDAO;
import com.example.Mappings.dao.AppDaoImpl;
import com.example.Mappings.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MappingsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			Course c1 =new Course("Course title");
			Student s1  = new Student("Kareem","Afifi","kareem@gmail.com");
			c1.addStudent(s1);
			Student temp =  appDAO.findStudentAndCoursesByStudentId(1);
			System.out.println(temp.getCourses());

		};
	}

}
