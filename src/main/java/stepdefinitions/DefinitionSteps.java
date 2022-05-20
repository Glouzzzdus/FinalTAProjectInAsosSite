package stepdefinitions;

import PageFactoryManager.PageFactoryManager;
import Pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefinitionSteps
{
    private static final long DEFAULT_TIMEOUT = 120;
    private static final String DEFAULT_FIRST_NAME = "John";
    private static final String DEFAULT_LAST_NAME = "Smith";
    private static final String EMPTY_BAG_MESSAGE = "Your bag is empty";
    private static final String SIGN_IN_URL_CONTENT = "identity";



    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;
    SavedItemsPage savedItemsPage;
    ShoppingBagPage shoppingBagPage;
    PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp()
    {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }
    @After public void tearDown() {
    driver.close();
}
    @And("User opens {string} page")
    public void openPage(final String url)
    {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }
    @And("User checks search field visibility")
    public void checksSearchFieldVisibility()
    {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.isSearchInputIsDisplayed();
    }
    @And("User makes search by keyword {string}")
    public void makesSearchByKeyword(final String keyword)
    {
        homePage.enterTextToSearchInput(keyword);
    }
    @And("User clicks search button")
    public void clicksSearchButton()
    {
        homePage.searchButtonClick();
    }
    @And("User save list on {int} products")
    public void saveListOnAmountOfProductsProducts(final int amountOfProducts)
    {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getLoadMoreButton());
        searchResultsPage.setSaveForLaterButtons(amountOfProducts);
    }

    @Then("User checks that amount of products in wish list are {int}")
    public void checksThatAmountOfProductsInSaveListAreCorrect(final int amountOfProducts)
    {
        savedItemsPage = pageFactoryManager.getSavedItemsPage();
        savedItemsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        savedItemsPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        savedItemsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, savedItemsPage.getRecentlyAdded());
        assertEquals(savedItemsPage.savedItemsAmount(), amountOfProducts);
    }

    @And("User choose a product called {int}")
    public void chooseAProductCalledProductNumber(final int poductNumber)
    {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getLoadMoreButton());
        searchResultsPage.selectProductByNumber(poductNumber);
    }

    @And("User choose a size {string} of product")
    public void chooseASizeSizeOfProduct(final String sizeOfProduct)
    {
        productPage = pageFactoryManager.getProductPage();
        productPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        productPage.chooseOfProductSize(sizeOfProduct);
    }

    @And("User checks add to bag button visibility")
    public void checkAddToBagButtonVisibility()
    {
        productPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        productPage.isAddToBagButtonDisplayed();
    }
    @And("User check own bag")
    public void userCheckOwnBag()
    {
        productPage.bagIconHooverOver();
        productPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productPage.viewBagButton());
        productPage.viewBagButtonClick();
    }
    @And("User checks country selector visibility")
    public void checksCountrySelectorVisibility()
    {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.isCountrySelectorIsDisplayed();
    }
    @And("User choose a country {string}")
    public void chooseACountry(final String country)
    {
        homePage.startCountrySelection();
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getChooseOfCountriesSelector());
        homePage.openListOfCountries();
        homePage.chooseOfCountry(country);
    }

    @And("User applied a change")
    public void userAppliedAChange()
    {
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getApplyChangesButton());
        homePage.getApplyChangesButton().click();
    }

    @Then("User check that chosen {string} has actually")
    public void checkThatChosenCountryHasActually(final String country)
    {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertEquals(homePage.getShoppingFromCountry(), country);
    }

    @And("User choose join")
    public void userChooseJoin()
    {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        homePage.myAccountHooverOver();
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getSignUpLink());
        homePage.signUp();
    }

    @When("User input an Email {string}")
    public void inputAnEmail(final String Email)
    {
        registerPage = pageFactoryManager.getRegisterPage();
        registerPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        registerPage.emailInput(Email);
    }

    @Then("User can see a email popup with {string}")
    public void checkAPopupWithErrorMessage(final String message)
    {
        registerPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, registerPage.getEmailErrorPopup());
        assertEquals(registerPage.emailErrorMessage(), message);
    }

    @And("User input a first and last name")
    public void inputAFirstAndLastName()
    {
        registerPage.setFirstNameInput(DEFAULT_FIRST_NAME);
        registerPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        registerPage.setLastNameInput(DEFAULT_LAST_NAME);
        registerPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @And("User input an password {string}")
    public void inputAnPassword(final String password)
    {
        registerPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        registerPage.passwordInput(password);
    }

    @Then("User can see a password popup with {string}")
    public void checkAPasswordPopupWithErrorMessage(final String message)
    {
        registerPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, registerPage.getPasswordErrorPopup());
        assertEquals(registerPage.passwordErrorMessage(), message);
    }

    @And("User choose an category {string}")
    public void chooseAnProductCategory(final String categoryName)
    {
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        homePage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        homePage.categoryHooverOver(categoryName);
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getOpenedProductCategory());
    }

    @When("User choose an shopping by {string} in drop menu")
    public void chooseAnShoppingByProductInDropMenu(final String productName)
    {

        homePage.getProductSearchElement(productName);
    }

    @Then("User open a page with relative {string} name")
    public void checkAPageWithRelativeProductName(final String productName)
    {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(searchResultsPage.getCategoryTitle().contains(productName));
    }

    @Then("User can see a {string} for non existing search results")
    public void seeAMessageForNonExistingSearchResults(final String message)
    {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertEquals(searchResultsPage.getTextOfMessageForNonExistingSearchInput(), message);
    }

    @When("User choose an gender {string}")
    public void chooseAnGender(final String gender)
    {
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        searchResultsPage.getGenderDropdown(gender);

    }

    @And("User choose a category {string}")
    public void chooseACategory(final String category)
    {
        searchResultsPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        searchResultsPage.getCategoryDropdown(category);
    }

    @And("User choose a color {string}")
    public void chooseAColor(final String color)
    {
        searchResultsPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        searchResultsPage.getColorDropdown(color);
    }

    @Then("User can see an {int} search results")
    public void seeAnAmountSearchResults(final int amount)
    {
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertEquals(searchResultsPage.getAmountOfSearchResults(), amount);
    }

    @And("User check save list")
    public void checkSaveList()
    {
        searchResultsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, searchResultsPage.getSaveListButton());
        searchResultsPage.waitForElementToBeClicable(DEFAULT_TIMEOUT, searchResultsPage.getSaveListButton());
        searchResultsPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        searchResultsPage.setSaveListButtonClick();
    }
    @And("User choose a size of saved product")
    public void chooseASizeOfSavedProduct()
    {
        savedItemsPage = pageFactoryManager.getSavedItemsPage();
        savedItemsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        savedItemsPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        savedItemsPage.waitForElementToBeClicable(DEFAULT_TIMEOUT, savedItemsPage.getSizeDropdown());
        savedItemsPage.selectAnyAvaliableSizeOfSavedProduct();
    }

    @And("User check is add to bag button is enable")
    public void checkIsAddToBagButtonIsEnable()
    {
        savedItemsPage.isAddToBagButtonEnabled();
    }
    @And("User click add to bag button")
    public void clickAddToBagButton()
    {
        savedItemsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        savedItemsPage.getAddToBagButton();
    }

    @Then("User check a bag with {int} products")
    public void checkABagWithAmountOfProductsProducts(final int amountInBag)
    {
        savedItemsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, savedItemsPage.getMyBagButton());
        assertEquals(savedItemsPage.getAmountInBag(), amountInBag);
    }

    @And("User hoover over bag button")
    public void hooverOverBagButton()
    {
        savedItemsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, savedItemsPage.getMyBagButton());
        savedItemsPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        savedItemsPage.bagHooverOver();
        savedItemsPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, savedItemsPage.getViewBagButton());
        savedItemsPage.clickViewBagButton();
    }

    @And("User check a bag with products")
    public void checkABagWithProducts()
    {
        shoppingBagPage = pageFactoryManager.getShoppingBagPage();
        shoppingBagPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }


    @And("User click delete button")
    public void clickDeleteButton()
    {
        shoppingBagPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, shoppingBagPage.getItemRemoveButton());
        shoppingBagPage.clickRemoveButton();
        shoppingBagPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @Then("User can see that bag is empty")
    public void ceeThatTheItemHasBeenDeleted()
    {
        shoppingBagPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, shoppingBagPage.getBagContentsHolder());
        assertEquals(EMPTY_BAG_MESSAGE, shoppingBagPage.valueOfBagContentsHolder());
    }

    @And("User click checkout button")
    public void clickCheckoutButton()
    {
        shoppingBagPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, shoppingBagPage.getCheckoutButton());
        shoppingBagPage.clickCheckoutButton();
    }


    @Then("User check that register page are open")
    public void checkThatRegisterPageAreOpen()
    {
        registerPage = pageFactoryManager.getRegisterPage();
        registerPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        registerPage.waitForAjaxToComplete(DEFAULT_TIMEOUT);
        assertTrue(driver.getCurrentUrl().contains(SIGN_IN_URL_CONTENT));
    }
}
