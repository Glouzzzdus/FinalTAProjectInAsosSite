package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage
{
    @FindBy(xpath = "//input[@alt='Email']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[contains(@class,'qa-firstname-textbox')]")
    private WebElement firstNameInput;
    @FindBy(xpath = "//input[contains(@class,'qa-lastname-textbox')]")
    private WebElement lastNameInput;
    @FindBy(xpath = "//input[contains(@class,'qa-password-textbox')]")
    private WebElement passwordInput;
    @FindBy(id = "Email-error")
    private WebElement emailErrorPopup;
    @FindBy(id = "Password-error")
    private WebElement passwordErrorPopup;


    public void emailInput(String testEmail)  { emailInput.sendKeys(testEmail, Keys.ENTER); }
    public void passwordInput(String testPassword)  { passwordInput.sendKeys(testPassword, Keys.ENTER); }
    public void setFirstNameInput (String firstName)  {firstNameInput.sendKeys(firstName, Keys.ENTER);}
    public void setLastNameInput (String lastName)  {lastNameInput.sendKeys(lastName, Keys.ENTER);}
    public WebElement getEmailErrorPopup()  { return emailErrorPopup; }
    public WebElement getPasswordErrorPopup()  { return passwordErrorPopup; }
    public String passwordErrorMessage() { return  passwordErrorPopup.getText(); }
    public String emailErrorMessage()  { return emailErrorPopup.getText(); }
    public RegisterPage(WebDriver driver) {super(driver);}
}
