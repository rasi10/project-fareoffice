package com.fareoffice.project_fareoffice;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HelpMethods {	
		
	public void printTestName(String testName){
		System.out.println("Test Name: "+ testName);		
	}
	
	public int getStatusCode(String currentURL) throws Exception{		
		URL url = new URL(currentURL);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		int code = connection.getResponseCode();
		return code;
	}
	
	public void fillFormLocationAndDates(WebDriver driver) {		
		driver.findElement(By.id("pickupDate")).clear();
		driver.findElement(By.id("pickupDate")).sendKeys(generateRandomPickUpDate());
		driver.findElement(By.id("pickupTime")).click();
		new Select(driver.findElement(By.id("pickupTime"))).selectByVisibleText("11:00");
		driver.findElement(By.id("returnDate")).clear();
		driver.findElement(By.id("returnDate")).sendKeys(generateRandomReturnUpDate());
		driver.findElement(By.id("returnTime")).click();
		new Select(driver.findElement(By.id("returnTime"))).selectByVisibleText("10:00");	
	}
	
	public void fillFormLocationAndDatesEmptyFields(WebDriver driver) {		
		driver.findElement(By.id("pickupDate")).clear();
		driver.findElement(By.id("pickupDate")).sendKeys("");
		driver.findElement(By.id("pickupTime")).click();
		new Select(driver.findElement(By.id("pickupTime"))).selectByVisibleText("11:00");
		driver.findElement(By.id("returnDate")).clear();
		driver.findElement(By.id("returnDate")).sendKeys("");
		driver.findElement(By.id("returnTime")).click();
		new Select(driver.findElement(By.id("returnTime"))).selectByVisibleText("10:00");
		driver.findElement(By.id("returnTime")).click();
	//	driver.findElement(By.cssSelector("button.btn.btn-primary")).click();			
	}

	public void chooseVehicle(WebDriver driver) {
		int randomValue = new Random().nextInt(7);
		
		switch (randomValue) {
			case 0:  driver.findElement(By.linkText("Select")).click();
					 break;
			case 1:  driver.findElement(By.xpath("(//a[contains(text(),'Select')])[2]")).click();
	                 break;
	        case 2:  driver.findElement(By.xpath("(//a[contains(text(),'Select')])[3]")).click();
	                 break;
	        case 3:  driver.findElement(By.xpath("(//a[contains(text(),'Select')])[4]")).click();
	                 break;
	        case 4:  driver.findElement(By.xpath("(//a[contains(text(),'Select')])[5]")).click();
	                 break;
	        case 5:  driver.findElement(By.xpath("(//a[contains(text(),'Select')])[6]")).click();
	                 break;
	        case 6:  driver.findElement(By.xpath("(//a[contains(text(),'Select')])[7]")).click();
	                 break;
	        
		}		
	}
	
	public void getscreenshot(String testName, WebDriver driver) throws Exception{
		int randomNumber = new Random().nextInt(500);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);		
		FileUtils.copyFile(scrFile, new File("src//test//resources//screenshots//"+testName+""+randomNumber+"_scrshot.png"));
	}

	public void fillFormPersonalData(WebDriver driver) {			
		driver.findElement(By.id("firstname")).clear();		
		driver.findElement(By.id("firstname")).sendKeys(UUID.randomUUID().toString());
		driver.findElement(By.id("lastname")).clear();	
		driver.findElement(By.id("lastname")).sendKeys(UUID.randomUUID().toString());
		driver.findElement(By.id("email")).clear();	
		driver.findElement(By.id("email")).sendKeys(UUID.randomUUID().toString()+"@email.com");
		driver.findElement(By.id("phone")).clear();		
		driver.findElement(By.id("phone")).sendKeys(UUID.randomUUID().toString());
	}
	
	public void performAssertionsConfirmationPage(WebDriver driver, String location){
		assertEquals("The page should display a confirmation message!",	driver.findElement(By.cssSelector("body")).getText().contains("Confirm details"), true);
		assertEquals("The pickup location should be "+location+"!", driver.findElement(By.cssSelector("body")).getText().contains(location), true);
	}
	
	
	public void performAssertionsReservationSuccessful(WebDriver driver, String location){
		assertEquals("The page should display a successfull message for the reservation!", driver.findElement(By.cssSelector("body")).getText().contains("Reservation successful"), true);
		assertEquals("The pickup location should be "+location+"!", driver.findElement(By.cssSelector("body")).getText().contains(location), true);
	}
	
	public String generateRandomPickUpDate(){
		int randomNumberOfDays = new Random().nextInt(10)+1;		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		Calendar calendar = new GregorianCalendar();		
		calendar.add(Calendar.DATE, randomNumberOfDays);		
		String dateAsString = sdf.format(calendar.getTime());		
		return dateAsString;
		
	}
	
	public String generateRandomReturnUpDate(){
		int randomNumberOfDays = new Random().nextInt(10)+15;		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		Calendar calendar = new GregorianCalendar();		
		calendar.add(Calendar.DATE, randomNumberOfDays);		
		String dateAsString = sdf.format(calendar.getTime());		
		return dateAsString;
		
	}
	
	public ArrayList<String> fillFormLocationAndDatesSavedForConfirmationPage(WebDriver driver) {		
		ArrayList<String> returnArray = new ArrayList<String>();
		String pickupdate = generateRandomPickUpDate();
		String returnDate = generateRandomReturnUpDate();
		
		driver.findElement(By.id("pickupDate")).clear();
		driver.findElement(By.id("pickupDate")).sendKeys(pickupdate);
		driver.findElement(By.id("pickupTime")).click();
		new Select(driver.findElement(By.id("pickupTime"))).selectByVisibleText("11:00");
		driver.findElement(By.id("returnDate")).clear();
		driver.findElement(By.id("returnDate")).sendKeys(returnDate);
		driver.findElement(By.id("returnTime")).click();
		new Select(driver.findElement(By.id("returnTime"))).selectByVisibleText("10:00");
		
		returnArray.add(pickupdate);
		returnArray.add(returnDate);
		
		return returnArray;
		
	}
	
	public ArrayList<String> fillFormPersonalDataForConfirmationPage(WebDriver driver) {			
		ArrayList<String> returnArray = new ArrayList<String>();
		String name = UUID.randomUUID().toString();
		String surname =UUID.randomUUID().toString();
		
		driver.findElement(By.id("firstname")).clear();		
		driver.findElement(By.id("firstname")).sendKeys(name);
		driver.findElement(By.id("lastname")).clear();	
		driver.findElement(By.id("lastname")).sendKeys(surname);
		driver.findElement(By.id("email")).clear();	
		driver.findElement(By.id("email")).sendKeys(UUID.randomUUID().toString()+"@email.com");
		driver.findElement(By.id("phone")).clear();		
		driver.findElement(By.id("phone")).sendKeys(UUID.randomUUID().toString());
		returnArray.add(name);
		returnArray.add(surname);
		
		return returnArray;
	}

}