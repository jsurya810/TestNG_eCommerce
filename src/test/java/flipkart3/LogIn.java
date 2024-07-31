
package flipkart3;
/* Jayasurya N
Jul 28, 2024
LogIn.java */

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LogIn {
	
	WebDriver driver ;

@Parameters({"url"})
@Test(priority=1)
	public void logIn(String url) throws InterruptedException  {
	
	driver	= new ChromeDriver();
	WebDriverManager.chromedriver().setup();
	
	driver.get(url);                                   //getting url from testng.xml file
	driver.manage().window().maximize();               
	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	
	WebElement searchBox1 = driver.findElement(By.className("Pke_EE"));
	searchBox1.sendKeys("motorola 5g mobiles");                      //searching in the textbox field
	Actions a = new Actions(driver);                       //using keyboard
	searchBox1.sendKeys(Keys.ENTER);
	Thread.sleep(2000);
		}
    @Test(priority=2, enabled=true)                      // choosing the product we need
    public void chooseProduct() {
    //	WebElement product = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div/div/a/div[2]/div[1]/div[1]"));
    	WebElement product = driver.findElement(By.className("KzDlHZ"));
    	product.click();
    	
    }
    @Test(priority=3, enabled=true)
    public void chooseVariant() throws InterruptedException {
    	
    	Set<String> WindowIds = driver.getWindowHandles();        // for acccessing another window
        Iterator<String> itr = WindowIds.iterator();
        String mainwin= itr.next();                        //already in childwin. so itr.next will be main window,so naming it as mainwindow                                        
        String childwin= itr.next();
        driver.switchTo().window(childwin);
        Thread.sleep(2000);
        driver.findElement(By.linkText("256 GB")).click();        //selecting variants
        driver.findElement(By.linkText("12 GB")).click();
       
    	WebElement enterPin = driver.findElement(By.id("pincodeInputId"));
    	enterPin.sendKeys("600025");
    	WebElement check = driver.findElement(By.className("i40dM4"));
    	check.click();
    	Thread.sleep(2000);
    	 WebElement buyNow = driver.findElement(By.xpath("//button[text()='Buy Now']"));
         buyNow.click();
         Thread.sleep(2000);  	
    }
    @Test(priority=4, enabled=true)
     public void payment() throws InterruptedException{
   // 	driver.findElement(By.xpath("//span[normalize-space()='Delivery Address']")); 
    	  WebElement verify = driver.findElement(By.xpath("//input[@type='text']"));
         // Actions a = new Actions(driver);
         //  a.moveToElement(verify).click(verify).perform();
          verify.click();
    	  Thread.sleep(2000);
    	
    	System.out.println("payment page opened");
    }
    
    @Test(priority=5)
    public void end() {
    //	driver=new ChromeDriver();
    	driver.quit();
    }
    
     
}
