package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    public WebDriver driver;
    String baseURL, nodeURL;

    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException {
        // BROWSER => chrome / firefox
        // HUB_HOST => localhost / 10.0.1.3 / hostname

        String host = "192.168.99.100";
//        DesiredCapabilities dc;
//       
////        if(System.getProperty("BROWSER") != null &&
////                System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
////            dc = DesiredCapabilities.firefox();
////            this.driver = new RemoteWebDriver(new URL(completeUrl), dc);
////        }else{
////            dc = DesiredCapabilities.chrome();
//           
////        }
//        System.out.println("entered1");
//        System.out.println("what the : "+System.getProperty("HUB_HOST"));
        
//        if(System.getProperty("HUB_HOST") != null){
//        	System.out.println("entered");
//            host = System.getProperty("HUB_HOST");
//        }
//        System.out.println("entered2");
//        String completeUrl = "http://" + host + ":4444/wd/hub";
//        String testName = ctx.getCurrentXmlTest().getName();
//        URL url=new URL(completeUrl);
//        ChromeOptions options = new ChromeOptions();
//        this.driver = new RemoteWebDriver(url, options);
        
//        dc.setCapability("name", testName);
    
        DesiredCapabilities dc;

        if(System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
            dc = DesiredCapabilities.firefox();
        }else{
            dc = DesiredCapabilities.chrome();
        }

        if(System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }

        String testName = ctx.getCurrentXmlTest().getName();

        String completeUrl = "http://" + host + ":4444/wd/hub";
        dc.setCapability("name", testName);
        this.driver = new RemoteWebDriver(new URL(completeUrl), dc);

    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }



}
