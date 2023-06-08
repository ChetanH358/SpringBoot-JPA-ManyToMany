package com.demo.many_to_many;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.many_to_many.entity.Category;
import com.demo.many_to_many.entity.Course;
import com.demo.many_to_many.entity.Product;
import com.demo.many_to_many.entity.Student;
import com.demo.many_to_many.repository.CategoryRepository;
import com.demo.many_to_many.repository.CourseRepository;
import com.demo.many_to_many.repository.StudentRepository;

@SpringBootTest
class DemoJpaManyToManyApplicationTests {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	@Test
	void SaveDetails() {

		// 2-Categories
		Category c_1 = new Category();
		c_1.setcId("CA401");
		c_1.setTitle("Electronics");

		Category c_2 = new Category();
		c_2.setcId("CA402");
		c_2.setTitle("Mobile phone");

		// 3-Products
		Product p_1 = new Product();
		p_1.setpId("101");
		p_1.setpName("I phone 14 max-pro");

		Product p_2 = new Product();
		p_2.setpId("102");
		p_2.setpName("samsung S-22 Ultra");

		Product p_3 = new Product();
		p_3.setpId("103");
		p_3.setpName("Samsung TV1234");

		List<Product> product_list_1 = new ArrayList<>();
		product_list_1.add(p_1);
		product_list_1.add(p_2);
		product_list_1.add(p_3);

		List<Product> product_list_2 = new ArrayList<>();
		product_list_2.add(p_1);
		product_list_2.add(p_3);

		c_1.setProducts(product_list_1);
		c_2.setProducts(product_list_2);

		categoryRepository.saveAll(List.of(c_1, c_2));

	}
	
/*
 * ***************************************************************************************
 */

	@Test
	void SaveStudentDetails() {
		// 2 Students
		Student s_1 = new Student();
		s_1.setStd_Id(101);
		s_1.setStd_Name("Chetan");
		s_1.setStd_Dept("Engg");
		s_1.setStd_Age(23);

		Student s_2 = new Student();
		s_2.setStd_Id(102);
		s_2.setStd_Name("Chinmay");
		s_2.setStd_Dept("Engg");
		s_2.setStd_Age(24);

		// 4 courses
		Course c_1 = new Course();
		c_1.setC_Id(401);
		c_1.setC_Title("Java");
		c_1.setC_Modules(4);
		c_1.setC_Fees(2500);

		Course c_2 = new Course();
		c_2.setC_Id(402);
		c_2.setC_Title("python");
		c_2.setC_Modules(6);
		c_2.setC_Fees(3500);

		Course c_3 = new Course();
		c_3.setC_Id(403);
		c_3.setC_Title("Hibernate");
		c_3.setC_Modules(5);
		c_3.setC_Fees(3000);

		Course c_4 = new Course();
		c_4.setC_Id(404);
		c_4.setC_Title("Java-Script");
		c_4.setC_Modules(6);
		c_4.setC_Fees(4000);

		Set<Course> list_courses_1 = new HashSet<Course>();
		list_courses_1.add(c_1);
		list_courses_1.add(c_2);
		list_courses_1.add(c_3);

		Set<Course> list_courses_2 = new HashSet<Course>();
		list_courses_2.add(c_2);
		list_courses_2.add(c_3);
		list_courses_2.add(c_4);

		s_1.setCourses(list_courses_1);
		s_2.setCourses(list_courses_2);

		studentRepository.saveAll(List.of(s_1, s_2));
		
	}
	
	@Test
	void StudentFindById() {
		int id = 101;
		Student res = studentRepository.findById(id).get();
		System.out.println("*********************************************");
		System.out.println(res.getStd_Id());
		System.out.println(res.getStd_Name());
		System.out.println(res.getStd_Dept());
		System.out.println(res.getStd_Age());
		Set<Course> course_res = res.getCourses();
		course_res.forEach((c) -> {
			System.out.println("<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(c.getC_Id());
			System.out.println(c.getC_Title());
			System.out.println(c.getC_Modules());
			System.out.println(c.getC_Fees());
		});
	}
	
	@Test
	void GetAllStudentCourses() {
		List<Student> std_res = studentRepository.findAll();
		std_res.forEach((s) -> {
			System.out.println("************************************");
			
			System.out.println(s.getStd_Id());
			System.out.println(s.getStd_Name());
			System.out.println(s.getStd_Dept());
			System.out.println(s.getStd_Age());
			
			Set<Course> cour_res = s.getCourses();
			cour_res.forEach((c) -> {
				System.out.println("<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println(c.getC_Id());
				System.out.println(c.getC_Title());
				System.out.println(c.getC_Modules());
				System.out.println(c.getC_Fees());
				System.out.println("<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>");
			});
		
			System.out.println("************************************");
		});
	}
	
	@Test
	void updateStudent() {
		int id = 101;
		Student res = studentRepository.findById(id).get();
		res.setStd_Age(26);
		res.setStd_Dept("Engg-civil");
		
		studentRepository.save(res);
	}
	
	@Test
	void updateCourse() {
		// Before updation
		// 403|5000|5|machine learning
		
		// After updation
		// 403|6000|10|Hibernate
		
//		after doing this it will automatically get reflected in all of them 
//		whoever had taken that course
		
		int id = 101;
		Student res = studentRepository.findById(id).get();
		Set<Course> res_cours = res.getCourses();
		res_cours.forEach((c) -> {
			if(c.getC_Id()==403) {
				c.setC_Fees(6000);
				c.setC_Modules(10);
			}
		});
		studentRepository.save(res);
		
	}
	
	@Test
	void updateCoursesDireclty() {
		Course res = courseRepository.findById(402).get();
		res.setC_Fees(8000);
		res.setC_Modules(20);
		courseRepository.save(res);
	}
	
	
	@Test
	void DeleteById() {
/*
 * 	Cannot delete or update a parent row: a foreign key constraint fails
 *  (`jpa_manytomany`.`student_courses`, CONSTRAINT `FKsyiswv7fglc2ynsv9wmk14iwm` 
 *  FOREIGN KEY (`courses_c_id`) REFERENCES `course` (`c_id`))
 *  
 */
		int id = 101;
		studentRepository.deleteById(id);
	}
	
	void DeleteAllMethod() {
/*
 * Getting Terminated Automatically
 */
		studentRepository.deleteAll();
	}
	
	@Test
	void IndividualdeleteMethod() {
/*
* 	Cannot delete or update a parent row: a foreign key constraint fails
*  (`jpa_manytomany`.`student_courses`, CONSTRAINT `FKsyiswv7fglc2ynsv9wmk14iwm` 
*  FOREIGN KEY (`courses_c_id`) REFERENCES `course` (`c_id`))
*  
*/
		int id = 101;
		Student s = studentRepository.findById(id).get();
		studentRepository.delete(s);
	}
	
	@Test
	void DeleteStudentd() {
		
		/*
		 * Not Working
		 */
		int id = 101;
		Student res = studentRepository.findById(id).get();
		Set<Course> course_res = res.getCourses();
		Set<Course> mylist = new HashSet<Course>();
		course_res.forEach((c) -> {
			mylist.add(c);
		});
		studentRepository.delete(res);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
