
package loginObj;

import org.testng.Assert;
import org.testng.AssertJUnit;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import loginPage.LoginPage;

/*Jayasurya N
Aug 2, 2024
Login.java */
public class Login {

	WebDriver driver;
	LoginPage loginValid ;
	LoginPage loginInvalid;
	WebDriverWait wait;

	@Parameters({ "url" })
	@BeforeMethod
	public void enterUrl(String url) throws InterruptedException {

		driver= new ChromeDriver();
		WebDriverManager.chromedriver().setup();

		driver.get(url);                               // getting url from testng.xml file
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}
	
	@BeforeMethod //(dependsOnMethods= {"enterUrl"})
	public void login() {
		
		LoginPage login= new LoginPage(driver);       //calling constructor
		login.loginfield().click();
	}
	
    @Parameters({"mobile"})
	@Test(priority = 1,enabled=true)
	public void login_ValidUsingMobile(String mobile) throws InterruptedException {
    	
    	loginValid = new LoginPage(driver);
		loginValid.loginMobEmailfield().click();                                //mobile no
		loginValid.loginMobEmailfield().sendKeys(mobile);                       //passing value to mobile number
		Thread.sleep(1000);
		
		loginValid.requestOtpfield().click();                              //clicking request OTP
		
		String actualText= loginValid.enterOtptext().getText();            // validating using text
        AssertJUnit.assertEquals("Please enter the OTP sent to", actualText);
        AssertJUnit.assertTrue(loginValid.verifyBut().isDisplayed());           //validating using button
        
	}
    @Parameters({"email"})
    @Test(priority=2, enabled=true)
    public void login_ValidUsingEmail (String email) throws InterruptedException {
    	loginValid = new LoginPage(driver);
    	loginValid.loginMobEmailfield().click();
    	loginValid.loginMobEmailfield().sendKeys(email);                  //login using email
    	Thread.sleep(1000);
    	
    	loginValid.requestOtpfield().click();
    	String actualText= loginValid.enterOtptext().getText();            // validating using text
        AssertJUnit.assertEquals("Please enter the OTP sent to", actualText);
        AssertJUnit.assertTrue(loginValid.verifyBut().isDisplayed());           //validating using button
        
    	}
    @Parameters({"dataInvalid"})
    @Test(priority=3,enabled=true)
    public void login_InvalidUsingEmail(String dataInvalid) throws InterruptedException {
    	loginInvalid= new LoginPage(driver);
    	loginInvalid.loginMobEmailfield().click();
    	loginInvalid.loginMobEmailfield().sendKeys(dataInvalid);             //trying to login using invalid data
    	Thread.sleep(1000);
    	
    	loginInvalid.requestOtpfield().click();
    	String actText= loginInvalid.invalidDatatext().getText();
    	AssertJUnit.assertEquals("Please enter valid Email ID/Mobile number", actText);
    }
    @Parameters({"mobile"})
    @Test(priority=4, enabled=true)
    public void login_noOtp(String mobile) throws InterruptedException {
    	loginValid= new LoginPage(driver);
    	loginValid.loginMobEmailfield().click();
    	loginValid.loginMobEmailfield().sendKeys(mobile);                  //login using email
    	Thread.sleep(1000);
    	
    	loginValid.requestOtpfield().click();
    	Thread.sleep(2000);
    	loginValid.verifyBut().click();
    	try {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    	driver.switchTo().alert();
    	String alertmsg=driver.switchTo().alert().getText();
    	System.out.println(alertmsg);
    	}
    	catch(Exception e){
    		e.getMessage();
    	}
    	System.out.println("Alert dispalyed");
    	
    }
    @Parameters({"emailunreg"})
    @Test(priority=5, enabled=true)
    public void loginWithoutRegEmail(String emailunreg) throws InterruptedException {
    	loginValid= new LoginPage(driver);
    	loginValid.loginMobEmailfield().click();
    	loginValid.loginMobEmailfield().sendKeys(emailunreg);           //passing unregistered email for login
    	
    	loginValid.requestOtpfield().click();
    	wait= new WebDriverWait(driver, Duration.ofSeconds(5));   
    	
    	WebElement unregWarning = loginValid.unregWarningtext();   
    	wait.until(ExpectedConditions.visibilityOf(unregWarning));      //wait until the webelement's visibilty
    	String actText= unregWarning.getText();                         //getting warning message
    	Assert.assertEquals("You are not registered with us. Please sign up.", actText);
    	System.out.println(actText);
   
    } @Parameters({"mobunreg"})
    @Test(priority=6, enabled=true)
    public void loginWithoutRegMob(String mobunreg) throws InterruptedException {
    	loginValid= new LoginPage(driver);
    	loginValid.loginMobEmailfield().click();
    	loginValid.loginMobEmailfield().sendKeys(mobunreg);           //passing unregistered email for login
    	
    	loginValid.requestOtpfield().click();
    	wait= new WebDriverWait(driver, Duration.ofSeconds(5));   
    	
    	WebElement unregWarning = loginValid.unregWarningtext();   
    	wait.until(ExpectedConditions.visibilityOf(unregWarning));      //wait until the webelement's visibilty
    	String actText= unregWarning.getText();                         //getting warning message
    	Assert.assertEquals("You are not registered with us. Please sign up.", actText);
    	System.out.println(actText);
   
    }
   
   
	@AfterMethod
	public void close() {
		driver.quit();
	}

}
