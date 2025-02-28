Feature: verify orangeHrm login functionality
  Scenario: verify login with valid credential
    Given user open orangehrm webpage
    Then user enter valid username
    And user enter valid password
    And user click on login button
    And user verify login succes

  Scenario: verify login with invalid credential
    Given user open orangehrm webpage
    Then user enter Invalid username
    And user enter Invalid password
    And user click on login button
    And user verify invalid ui response
