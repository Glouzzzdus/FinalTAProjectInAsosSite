package PageFactoryManager;

import Pages.*;
import org.openqa.selenium.WebDriver;

public class PageFactoryManager
{
    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public RegisterPage getRegisterPage() { return new RegisterPage(driver); }

    public SearchResultsPage getSearchResultsPage() {
        return new SearchResultsPage(driver);
    }

    public ProductPage getProductPage() {
        return new ProductPage(driver);
    }

    public SavedItemsPage getSavedItemsPage() {
        return new SavedItemsPage(driver);
    }
    public ShoppingBagPage getShoppingBagPage()  { return new ShoppingBagPage(driver); }
}
