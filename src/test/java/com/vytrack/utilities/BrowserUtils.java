package com.vytrack.utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Set;

public class BrowserUtils {

    //Methods are static. Because we do not want to create an object to call those methods.
    // We just want to call those methods with class name. That is why they are static type

    // TC : Create utility method
    // 1. Create a new class BrowserUtils
    // 2. Create a method to make Multiple Windows logic re-usable
    // 3. When method is called, it should switch window title

    /** Method info:
     * Name: switchWindowAndVerify
     * Return type: void
     * Arg1: WebDriver
     * Arg2: String expectedInUrl
     * Arg3: String expectedTitle
     */

    public static void switchWindowAndVerify(String expectedUrl,String expectedTitle){
        Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();

        for (String eachWindow : allWindowHandles){
            Driver.getDriver().switchTo().window(eachWindow);  // syntax will switch each windows and driver will be able to
           // System.out.println("driver.getCurrentUrl() = " + driver.getCurrentUrl());

            if(Driver.getDriver().getCurrentUrl().contains(expectedUrl)){
                break;
            }
        }
        // 5. Assert: Title contains "Etsy"
        String actualTitle = Driver.getDriver().getTitle();

       // Assert.assertTrue("Title verification failed",actualTitle.contains(expectedTitle));  --> It is coming from jUnit
        Assert.assertTrue(actualTitle.contains(expectedTitle),"Title verification failed!");
    }




    /**
     * This method is used to pause the code for given seconds
     * It is static method we can call with class name
     * BrowsersUtils.sleep(3)
     * @param seconds
     */


    public static void sleep(int seconds){

        // 1 second = 1000 millis
        // millis = seconds * 1000

        try{
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("Exception happened in sleep method!");
        }
    }


//    Method info:
//            • Name: verifyTitle()
//            • Return type: void
//            • Arg1: WebDriver
//            • Arg2: String expectedTitle

    public static void verifyTitle_assertEquals(String expectedTitle){

      //  String actualTitle = driver.getTitle();
      //  Assert.assertEquals("Title verification failed",expectedTitle,Driver.getDriver().getTitle());   --> It is coming from jUnit
        Assert.assertEquals(Driver.getDriver().getTitle(),expectedTitle,"Title verification failed");
    }

    public static void verifyTitle_assertTrue(String expectedTitle){

        //  String actualTitle = driver.getTitle();
        // Assert.assertTrue(Driver.getDriver().getTitle().contains(expectedTitle));   --> It is coming from jUnit
        Assert.assertTrue(Driver.getDriver().getTitle().contains(expectedTitle));

    }


    public static void waitForInvisibilityOf(WebElement element){

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForVisibilityOf(WebElement element){

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


}
