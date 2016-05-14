package com.fareoffice.project_fareoffice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;



public class FairCarRentalTestSuite {
	private WebDriver driver;
	private final String BASE_URL = "http://localhost:8080/";	
	private StringBuffer verificationErrors = new StringBuffer();
	private HelpMethods helpMethods = new HelpMethods();
	
		
	@BeforeClass
	public static void setUpClass()  throws IOException{
		FileUtils.deleteDirectory(new File("src//test//resources//screenshots//"));
	}
		
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		
	}		
			
	@Test
	public void testLinkToIndexPage() throws Exception {
		String testName = "testLinkToIndexPage";
		helpMethods.printTestName(testName);	
		
		driver.get(BASE_URL);		
		driver.findElement(By.linkText("Fairtrade Car Rental")).click();
		String currentURL = driver.getCurrentUrl();
		int statusCode = helpMethods.getStatusCode(currentURL);
		
		helpMethods.getscreenshot(testName, driver);		
		assertTrue(statusCode<300);
		assertEquals("Fairtrade Car Rental", driver.getTitle());		
	}

	 @Test
	  public void testLinkToFareofficeHomePage() throws Exception {
		String testName = "testLinkToFareofficeHomePage";
		helpMethods.printTestName(testName);
			
	    driver.get(BASE_URL);	    
	    driver.findElement(By.linkText("Fareoffice")).click();
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    String currentURL = driver.getCurrentUrl();
		int statusCode = helpMethods.getStatusCode(currentURL);
		helpMethods.getscreenshot(testName, driver);
		assertTrue(statusCode<300);
	    assertEquals("Fareoffice", driver.getTitle());
	  }		

	@Test
	public void testBookCarAtLondonHeathrowAirport() throws Exception {
		String testName = "testBookCarAtLondonHeathrowAirport";
		helpMethods.printTestName(testName);	
		
		driver.get(BASE_URL);
		new Select(driver.findElement(By.id("pickuplocation"))).selectByVisibleText("London Heathrow Airport");
		helpMethods.fillFormLocationAndDates(driver);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();	
		helpMethods.chooseVehicle(driver);
		helpMethods.fillFormPersonalData(driver);	
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();	
		
		helpMethods.getscreenshot(testName, driver);		
		helpMethods.performAssertionsConfirmationPage(driver, "London Heathrow Airport (LHR)");		
		driver.findElement(By.linkText("Book")).click();
		
		helpMethods.getscreenshot(testName, driver);
		helpMethods.performAssertionsReservationSuccessful(driver, "London Heathrow Airport");		
	}

	@Test
	public void testBookCarAtLondonEdinburghAirport() throws Exception {
		String testName = "testBookarAtLondonEdinburghAirport";
		helpMethods.printTestName(testName);
		
		driver.get(BASE_URL);
		new Select(driver.findElement(By.id("pickuplocation"))).selectByVisibleText("Edinburgh Airport");
		helpMethods.fillFormLocationAndDates(driver);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();	
		helpMethods.chooseVehicle(driver);
		helpMethods.fillFormPersonalData(driver);	
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		
		helpMethods.getscreenshot(testName, driver);
		helpMethods.performAssertionsConfirmationPage(driver, "Edinburgh Airport (EDI)");
		driver.findElement(By.linkText("Book")).click();
		
		helpMethods.getscreenshot(testName, driver);
		helpMethods.performAssertionsReservationSuccessful(driver, "Edinburgh Airport");		
	}

	@Test
	public void testBookACarAtLondonManchesterAirport() throws Exception {
		String testName = "testBookCarAtLondonManchesterAirport";
		helpMethods.printTestName(testName);
		
		driver.get(BASE_URL);
		new Select(driver.findElement(By.id("pickuplocation"))).selectByVisibleText("Manchester Airport");
		helpMethods.fillFormLocationAndDates(driver);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();	
		helpMethods.chooseVehicle(driver);		
		helpMethods.fillFormPersonalData(driver);		
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		
		helpMethods.getscreenshot(testName, driver);
		helpMethods.performAssertionsConfirmationPage(driver, "Manchester Airport (MAN)");
		driver.findElement(By.linkText("Book")).click();
		
		helpMethods.getscreenshot(testName, driver);
		helpMethods.performAssertionsReservationSuccessful(driver, "Manchester Airport");		
	}
	
	@Test
	public void testBookACarAtLondonManchesterAirportWithEmptyFields() throws Exception {
		String testName = "testBookCarAtLondonManchesterAirportWithEmptyFields";
		helpMethods.printTestName(testName);
		
		driver.get(BASE_URL);
		new Select(driver.findElement(By.id("pickuplocation"))).selectByVisibleText("Manchester Airport");
		helpMethods.fillFormLocationAndDatesEmptyFields(driver);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		
		String currentURL = driver.getCurrentUrl();
		int statusCode = helpMethods.getStatusCode(currentURL);
		
		helpMethods.getscreenshot(testName, driver);
		assertTrue(statusCode<300);		
		
	}
	
	@Test
	public void testUsersInformationOnTheConfirmationPage() throws Exception {
		String testName = "testUsersInformationOnTheConfirmationPage";
		helpMethods.printTestName(testName);
		ArrayList<String> inputDates = new ArrayList<String>();
		ArrayList<String> inputNames = new ArrayList<String>();	
		
		driver.get(BASE_URL);
		new Select(driver.findElement(By.id("pickuplocation"))).selectByVisibleText("Manchester Airport");
		
		inputDates = helpMethods.fillFormLocationAndDatesSavedForConfirmationPage(driver);		
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		helpMethods.chooseVehicle(driver);		
		inputNames = helpMethods.fillFormPersonalDataForConfirmationPage(driver);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		helpMethods.getscreenshot(testName, driver);
	
		assertEquals(driver.findElement(By.cssSelector("body")).getText().contains(inputDates.get(0)), true);
		assertEquals(driver.findElement(By.cssSelector("body")).getText().contains(inputDates.get(1)), true);
		assertEquals(driver.findElement(By.cssSelector("body")).getText().contains(inputNames.get(0)), true);
		assertEquals(driver.findElement(By.cssSelector("body")).getText().contains(inputNames.get(1)), true);
				
		driver.findElement(By.linkText("Book")).click();
		helpMethods.getscreenshot(testName, driver);
		helpMethods.performAssertionsReservationSuccessful(driver, "Manchester Airport");
		
	}
	
	
	
	
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
