Feature: Google Search Function
This feature file is for Google Search functionality

Scenario: Search Java
Given I launch google page
When I search Java Tutorial
Then Should display Java search result
And Close the browser

Scenario: Search Selenium
Given I launch google page
When I search Selenium Tutorial
Then Should display Selenium search result
And Close the browser

