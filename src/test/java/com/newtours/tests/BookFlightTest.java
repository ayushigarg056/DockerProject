package com.newtours.tests;

import com.newtours.pages.*;
import com.tests.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookFlightTest{
	public static String path = System.getProperty("user.dir") ;
    private String noOfPassengers;
    private String expectedPrice;
    WebDriver driver;
    
    @BeforeTest
    @Parameters({"noOfPassengers","expectedPrice"})
    public void setUpDriver(String noOfPassengers, String expectedPrice)
    {
    	this.noOfPassengers=noOfPassengers;
    	this.expectedPrice=expectedPrice;
    	String chromeDriverPath = path + "\\src\\Executables\\chromedriver.exe" ;
    	System.setProperty("webdriver.chrome.driver",chromeDriverPath);
  		this.driver = new ChromeDriver();
//        
    }
   
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setupParameters(String noOfPassengers, String expectedPrice){
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
    }

    @Test
    public void registrationPage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("selenium", "docker");
        registrationPage.enterUserCredentials("selenium", "docker");
        registrationPage.submit();
    }

    @Test(dependsOnMethods = "registrationPage")
    public void registrationConfirmationPage(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.goToFlightDetailsPage();
    }

    @Test(dependsOnMethods = "registrationConfirmationPage")
    public void flightDetailsPage(){
        FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
        flightDetailsPage.selectPassengers(noOfPassengers);
        flightDetailsPage.goToFindFlightsPage();
    }

    @Test(dependsOnMethods = "flightDetailsPage")
    public void findFlightPage(){
        FindFlightPage findFlightPage = new FindFlightPage(driver);
        findFlightPage.submitFindFlightPage();
        findFlightPage.goToFlightConfirmationPage();
    }

    @Test(dependsOnMethods = "findFlightPage")
    public void flightConfirmationPage(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        String actualPrice=flightConfirmationPage.getPrice();
        System.out.println("actualPrice : "+actualPrice);
        Assert.assertEquals(actualPrice, expectedPrice);
    }

}
