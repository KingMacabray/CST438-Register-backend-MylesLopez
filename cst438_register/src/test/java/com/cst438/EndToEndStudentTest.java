package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;

import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;

/*
 * This example shows how to use selenium testing using the web driver 
 * with Chrome browser.
 * 
 *  - Buttons, input, and anchor elements are located using XPATH expression.
 *  - onClick( ) method is used with buttons and anchor tags.
 *  - Input fields are located and sendKeys( ) method is used to enter test data.
 *  - Spring Boot JPA is used to initialize, verify and reset the database before
 *      and after testing.
 *      
 *    Make sure that TEST_COURSE_ID is a valid course for TEST_SEMESTER.
 *    
 *    URL is the server on which Node.js is running.
 */

@SpringBootTest
public class EndToEndStudentTest {

	//public static final String CHROME_DRIVER_FILE_LOCATION = "C:/chromedriver_win32/chromedriver.exe";
	public static final String CHROME_DRIVER_FILE_LOCATION = "/Users/lynnalopez/Desktop/chromedriver";

	public static final String URL = "http://localhost:3000";

	public static final String TEST_USER_EMAIL = "willingtest@csumb.edu";

	public static final int TEST_COURSE_ID = 40443; 

	public static final String TEST_SEMESTER = "2021 Fall";

	public static final int SLEEP_DURATION = 1000; // 1 second.
	
	
	public static final String TEST_NAME = "Kakashi Sensei";
	
	//public static final String TEST_EMAIL = "2021 Fall";

	/*
	 * When running in @SpringBootTest environment, database repositories can be used
	 * with the actual database.
	 */
	
	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;

	/*
	 * Student add course TEST_COURSE_ID to schedule for 2021 Fall semester.
	 */
	
	@Test
	public void addStudentHomepageTest() throws Exception {

		/*
		// * if student is already enrolled, then delete the enrollment.
		//*/ 
		
		Student x = null;
		do {
			x = studentRepository.findByEmail(TEST_USER_EMAIL);
			if (x != null)
				{studentRepository.delete(x);}
		} while (x != null);
		///*

		// set the driver location and start driver
		//@formatter:off
		// browser	property name 				Java Driver Class
		// edge 	webdriver.edge.driver 		EdgeDriver
		// FireFox 	webdriver.firefox.driver 	FirefoxDriver
		// IE 		webdriver.ie.driver 		InternetExplorerDriver
		//@formatter:on

		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		// Puts an Implicit wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//*/
		try {

			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.xpath("//a"));

			// select the last of the radio buttons on the list of semesters page.
			
			//WebElement we = driver.findElement(By.xpath("(//input[@type='radio'])[last()]"));
			//we.click();

			/*/ Locate and click "Add Student" button
			
			WebElement we = driver.findElement(By.xpath("//a[href='/student']"));//.click();
			we.click();
			Thread.sleep(SLEEP_DURATION);

			//////*/
			WebElement we = driver.findElement(By.id("studentButton"));//.click();
			we.click();
			Thread.sleep(SLEEP_DURATION);
			/*
			// Locate and click "Add Course" button which is the first and only button on the page.
			driver.findElement(By.xpath("//button")).click();
			Thread.sleep(SLEEP_DURATION);
			////
			// enter course no and click Add button
			
			driver.findElement(By.xpath("//button")).click();
			Thread.sleep(SLEEP_DURATION);
			/////
			
			
			
			driver.findElement(By.id("toStudt")).click();
			Thread.sleep(SLEEP_DURATION);
			////*/
			
			driver.findElement(By.id("studName")).sendKeys(TEST_NAME);
			driver.findElement(By.id("studEmail")).sendKeys(TEST_USER_EMAIL);
			driver.findElement(By.id("AddStud")).click();
			Thread.sleep(SLEEP_DURATION);
			/*
			driver.findElement(By.xpath("//input[@name='name']")).sendKeys(TEST_NAME);
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(TEST_USER_EMAIL);
			driver.findElement(By.xpath("//button[@id='Add']")).click();
			Thread.sleep(SLEEP_DURATION);
			*/
			
			// verify that new course shows in schedule.
			// get the title of all courses listed in schedule
			 
			
			//Course course = courseRepository.findById(TEST_COURSE_ID).get();
			//Student student = studentRepository.findByEmail(TEST_USER_EMAIL);
			
			/*
			List<WebElement> elements  = driver.findElements(By.xpath("//div[@data-field='title']/div[@class='MuiDataGrid-cellContent']"));
			boolean found = false;
			for (WebElement e : elements) {
				System.out.println(e.getText()); // for debug
				if (e.getText().equals(course.getTitle())) {
					found=true;
					break;
				}
			}
			
			assertTrue( found, "Course added but not listed in schedule.");
			////*/
			// verify that enrollment row has been inserted to database.
			/*
			Enrollment e = enrollmentRepository.findByEmailAndCourseId(TEST_USER_EMAIL, TEST_COURSE_ID);
			assertNotNull(e, "Course enrollment not found in database.");
			/////*/
			
			Student s = studentRepository.findByEmail(TEST_USER_EMAIL);
			assertNotNull(s, "Student addition not found in database.");
		} catch (Exception ex) {
			throw ex;
		} finally {

			// clean up database.
			/*
			Enrollment e = enrollmentRepository.findByEmailAndCourseId(TEST_USER_EMAIL, TEST_COURSE_ID);
			if (e != null)
				enrollmentRepository.delete(e);
			////*/
			Student s = studentRepository.findByEmail(TEST_USER_EMAIL);
			if (s != null)
				studentRepository.delete(s);
			
			driver.quit();
		}
	//*/
	}
	@Test
	public void addStudentAdminTest() throws Exception {
		Student x = null;
		do {
			x = studentRepository.findByEmail(TEST_USER_EMAIL);
			if (x != null)
				{studentRepository.delete(x);}
		} while (x != null);
		
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		// Puts an Implicit wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//*/
		try {
			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);
			
			
			WebElement we = driver.findElement(By.id("toStudt"));//.click();
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			
			we = driver.findElement(By.id("studentButton"));//.click();
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			
			driver.findElement(By.id("studName")).sendKeys(TEST_NAME);
			driver.findElement(By.id("studEmail")).sendKeys(TEST_USER_EMAIL);
			driver.findElement(By.id("AddStud")).click();
			Thread.sleep(SLEEP_DURATION);
			
			Student s = studentRepository.findByEmail(TEST_USER_EMAIL);
			assertNotNull(s, "Student addition not found in database.");
		} catch (Exception ex) {
			throw ex;
		} finally {

		// clean up database.
		/*
		Enrollment e = enrollmentRepository.findByEmailAndCourseId(TEST_USER_EMAIL, TEST_COURSE_ID);
		if (e != null)
			enrollmentRepository.delete(e);
		////*/
		Student s = studentRepository.findByEmail(TEST_USER_EMAIL);
		if (s != null)
			studentRepository.delete(s);
		
		driver.quit();
	}
		
	}
	
}
