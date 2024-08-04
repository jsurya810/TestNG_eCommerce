
package loginPage;
/* Jayasurya N
Aug 2, 2024
LoginPage.java */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
  WebDriver driver;

	public  LoginPage(WebDriver driver) {           //conecting both drivers using constructors
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[contains(text(),'Login')]")
	private WebElement login;
	
	@FindBy(xpath="//*[@id=\"container\"]/div/div[3]/div/div[2]/div/form/div[1]/input")
	private WebElement loginMobEmail;
	
	@FindBy(xpath=("//button[text()='Request OTP']"))
	private WebElement requestOtp;
   
	@FindBy(xpath=("//*[contains(text(),'enter the OTP')]"))
	private WebElement enterOtp;
	
	@FindBy(xpath=("(//button[@type='submit'])[2]"))
	private WebElement verifyButton;
	
	@FindBy(xpath="//*[contains(text(),'Please enter valid')]")
	private WebElement invalidData;
	
	@FindBy(xpath=("//*[contains(text(),'not registered')]"))
	private WebElement unregWarning;
	
public WebElement loginfield() {
	return login;
}

public WebElement loginMobEmailfield() {
	return loginMobEmail;
}
public WebElement requestOtpfield() {
	return requestOtp;
}
public WebElement enterOtptext() {
	return enterOtp;
}
public WebElement verifyBut() {
	return verifyButton;
}
public WebElement invalidDatatext() {
	return invalidData;
}
public WebElement unregWarningtext() {
	return unregWarning;
}


}
