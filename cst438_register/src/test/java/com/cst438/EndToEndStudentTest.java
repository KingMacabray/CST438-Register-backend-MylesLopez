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



@SpringBootTest
public class EndToEndStudentTest {

	// Initialize static variables used for testing
	public static final String CHROME_DRIVER_FILE_LOCATION = "/Users/lynnalopez/Desktop/chromedriver";

	public static final String URL = "http://localhost:3000";

	public static final String TEST_USER_EMAIL = "willingtest@csumb.edu";

	public static final int SLEEP_DURATION = 1000; // 1 second.
	
	public static final String TEST_NAME = "Kakashi Sensei";
	
	@Autowired
	StudentRepository studentRepository;
	
	@Test
	public void addStudentHomepageTest() throws Exception {

		
		// If student is already enrolled, then delete the enrollment.
		Student x = null;
		do {
			x = studentRepository.findByEmail(TEST_USER_EMAIL);
			if (x != null)
				{studentRepository.delete(x);}
		} while (x != null);
		
		
		// Set to chrome driver
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		
		// Puts an Implicit wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Attempt to run test
		try {

			
			// get URL and use sleep duration. Load homepage
			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);
			driver.findElement(By.xpath("//a"));

			// Find add student button on homepage
			WebElement we = driver.findElement(By.id("studentButton"));//.click();
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			// Load with information and submit to database
			driver.findElement(By.id("studName")).sendKeys(TEST_NAME);
			driver.findElement(By.id("studEmail")).sendKeys(TEST_USER_EMAIL);
			driver.findElement(By.id("AddStud")).click();
			Thread.sleep(SLEEP_DURATION);
			
			
			// Make sure information was saved to database
			Student s = studentRepository.findByEmail(TEST_USER_EMAIL);
			assertNotNull(s, "Student addition not found in database.");
			
			// Catch errors and or delete information created by test
		} catch (Exception ex) {
			throw ex;
		} finally {
			Student s = studentRepository.findByEmail(TEST_USER_EMAIL);
			if (s != null)
				studentRepository.delete(s);
			
			driver.quit();
		}
	//*/
	}
	@Test
	public void addStudentAdminTest() throws Exception {
		
		// If student is already enrolled, then delete the enrollment.
		Student x = null;
		do {
			x = studentRepository.findByEmail(TEST_USER_EMAIL);
			if (x != null)
				{studentRepository.delete(x);}
		} while (x != null);
		
		// Set to chrome driver
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		
		// Puts an Implicit wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Attempt to run test
		try {
			
			// get URL and use sleep duration
			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);
			
			
			
			// Load admin page to add students
			WebElement we = driver.findElement(By.id("toStudt"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			// Find add student button
			we = driver.findElement(By.id("studentButton"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			// Load with information and submit to database
			driver.findElement(By.id("studName")).sendKeys(TEST_NAME);
			driver.findElement(By.id("studEmail")).sendKeys(TEST_USER_EMAIL);
			driver.findElement(By.id("AddStud")).click();
			Thread.sleep(SLEEP_DURATION);
			
			
			
			// Make sure information was saved to database
			Student s = studentRepository.findByEmail(TEST_USER_EMAIL);
			assertNotNull(s, "Student addition not found in database.");
			
			// Catch errors and or delete information created by test
		} catch (Exception ex) {
			throw ex;
		} finally {
		Student s = studentRepository.findByEmail(TEST_USER_EMAIL);
		if (s != null)
			studentRepository.delete(s);
		
		driver.quit();
	}
		
	}
	
}
