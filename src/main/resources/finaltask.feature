Feature: FinalTask
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  Scenario Outline: Check add product to wishlist
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User save list on <amountOfProducts> products
    And User check save list
    Then User checks that amount of products in wish list are <amountOfProducts>

    Examples:
      | homePage                            | keyword | amountOfProducts |
      | https://www.asos.com/men/           | Nike    | 3                |
      | https://www.asos.com/men/           | Topman  | 5                |

  Scenario Outline: Check that change country works correctly
    Given User opens '<homePage>' page
    And User checks country selector visibility
    When User choose a country '<country>'
    And User applied a change
    Then User check that chosen '<country>' has actually

    Examples:
    | homePage                 |country       |
    |https://www.asos.com/men/ | Switzerland  |
    |https://www.asos.com/men/ | Mexico       |

  Scenario Outline: Check sign up with invalid email
    Given User opens '<homePage>' page
    And User choose join
    When User input an Email '<testEmail>'
    Then User can see a email popup with '<errorMessage>'

    Examples:
    | homePage                | testEmail     | errorMessage                                         |
    |https://www.asos.com/men/|examplegmail.com  | Email fail! Please type in your correct email address|

  Scenario Outline: Check sign up with invalid password
    Given User opens '<homePage>' page
    And User choose join
    When User input an Email '<testEmail>'
    And User input a first and last name
    And User input an password '<testPassword>'
    Then User can see a password popup with '<errorMessage>'

    Examples:
      | homePage                | testEmail         | testPassword       | errorMessage                       |
      |https://www.asos.com/men/|example@gmail.com  |     12345          | Erm, you need 10 or more characters|

    Scenario Outline: Check the search throw drop menu
      Given User opens '<homePage>' page
      And User choose an category '<productCategory>'
      When User choose an shopping by '<product>' in drop menu
      Then User open a page with relative '<product>' name

    Examples:
      |homePage                  |productCategory |product    |
      |https://www.asos.com/men/ |Summer          |Sunglasses |
      |https://www.asos.com/men/ |Clothing        |Shorts     |

  Scenario Outline: Check the search with non existing search keyword
      Given User opens '<homePage>' page
      And User checks search field visibility
      When User makes search by keyword '<keyword>'
      And User clicks search button
      Then User can see a '<message>' for non existing search results

    Examples:
      | homePage                | keyword    | message                     |
      |https://www.asos.com/men/| diagnostics| NOTHING MATCHES YOUR SEARCH |
      |https://www.asos.com/men/| sda421aedb | NOTHING MATCHES YOUR SEARCH |

  Scenario Outline: Check that filters are working correctly
      Given User opens '<homePage>' page
      And User checks search field visibility
      And User makes search by keyword '<keyword>'
      And User clicks search button
      When User choose an gender '<gender>'
      And User choose a category '<category>'
      And User choose a color '<color>'
      Then User can see an <amount> search results

    Examples:
      | homePage                 |keyword|gender|category|color|amount|
      |https://www.asos.com/men/ | Nike  | Men  | Shorts |White|10    |

  Scenario Outline: Check is saved item is added to Bag
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User save list on <amountOfProducts> products
    And User check save list
    And User choose a size of saved product
    And User check is add to bag button is enable
    And User click add to bag button
    Then User check a bag with <amountOfProducts> products

    Examples:
      | homePage                            | keyword | amountOfProducts |
      | https://www.asos.com/men/           | Nike    | 1                |

  Scenario Outline: Check removing from bag
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User save list on <amountOfProducts> products
    And User check save list
    And User choose a size of saved product
    And User check is add to bag button is enable
    And User click add to bag button
    And User hoover over bag button
    And User check a bag with products
    And User click delete button
    Then User can see that bag is empty
    Examples:
      | homePage                            | keyword | amountOfProducts |
      | https://www.asos.com/men/           | Nike    | 1                |

  Scenario Outline: Check that checkout possible without sign in
    Given User opens '<homePage>' page
    And User checks search field visibility
    When User makes search by keyword '<keyword>'
    And User clicks search button
    And User save list on <amountOfProducts> products
    And User check save list
    And User choose a size of saved product
    And User check is add to bag button is enable
    And User click add to bag button
    And User hoover over bag button
    And User check a bag with products
    And User click checkout button
    Then User check that register page are open
    Examples:
      | homePage                            | keyword | amountOfProducts |
      | https://www.asos.com/men/           | Nike    | 1                |

