package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchResultsPage extends BasePage
{
    @FindBy(xpath = "//button[@data-auto-id='saveForLater']")
    private List<WebElement> saveForLaterButtons;
    @FindBy(xpath = "//span[@type='heartUnfilled']")
    private WebElement saveListButton;
    @FindBy(xpath = "//a[@data-auto-id='loadMoreProducts']")
    private WebElement loadMoreButton;
    @FindBy(xpath = "//article[@data-auto-id='productTile']")
    private List<WebElement> namesOfSearchResults;
    @FindBy(xpath = "//section[contains(@class,'grid-backgroundWrapper__row')]//h2[@class='grid-text__title ']")
    private WebElement messageForNonExistingSearch;
    @FindBy(xpath = "//section[@id='category-banner-wrapper']//h1")
    private WebElement categoryTitle;
    @FindBy(xpath = "//button[contains(@class,'_1om7l06')]/div[text()='Gender']")
    private WebElement genderDropdownButton;
    @FindBy(xpath = "//button[contains(@class,'_1om7l06')]/div[text()='Category']")
    private WebElement categoryDropdownButton;
    @FindBy(xpath = "//button[contains(@class,'_1om7l06')]/div[text()='Colour']")
    private WebElement colourDropdownButton;

    public void setSaveForLaterButtons(int quantityOfClicks)
    {
        Collections.shuffle(saveForLaterButtons);
        for(int i = 0; i < quantityOfClicks; i++ )
        {
            saveForLaterButtons.get(i).click();
        }
    }
    public WebElement getSaveListButton()
    {
        return saveListButton;
    }
    public void setSaveListButtonClick()
    {
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        waitForElementToBeClicable(90, saveListButton);
        saveListButton.click();
    }
    public WebElement getLoadMoreButton()
    {
        return loadMoreButton;
    }
    public void selectProductByNumber(int productNumber)
    {
        namesOfSearchResults.get(productNumber).click();
    }
    public String getCategoryTitle()  { return categoryTitle.getText(); }
    public String getTextOfMessageForNonExistingSearchInput() {
        return messageForNonExistingSearch.getText();
    }
    public void getGenderDropdown(String genderChoise)
    {
        genderDropdownButton.click();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//div[@class='kx2nDmW' and text()='"+genderChoise+"']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        waitForElementToBeClicable(120, genderDropdownButton);
        genderDropdownButton.click();
    }
    public void getCategoryDropdown(String categoryChoise)
    {
        categoryDropdownButton.click();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//div[@class='kx2nDmW'and text()='"+categoryChoise+"']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        waitForElementToBeClicable(120, categoryDropdownButton);
        categoryDropdownButton.click();
    }
    public void getColorDropdown(String colourChoice)
    {
        colourDropdownButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//div[@class='kx2nDmW'and text()='"+colourChoice+"']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        waitForElementToBeClicable(120, colourDropdownButton);
        colourDropdownButton.click();
    }
    public int getAmountOfSearchResults()
    {
        return namesOfSearchResults.size();
    }



    public SearchResultsPage(WebDriver driver) { super(driver); }
}
