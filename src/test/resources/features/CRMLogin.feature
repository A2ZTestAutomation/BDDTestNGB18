Feature: CRM Login Feature

Scenario: Valid Login
Given User is on Login Page
When User enters login credentials
Then User is navigated to Home Page
Then Close the Browser