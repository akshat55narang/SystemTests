# SystemTests
Test Automation framework for practice website and apis. 
Note - this framework is a work in progress and all tests have not been automated...

About the framework
This test framework includes UI tests for practice website http://automationpractice.com/ and api tests for apis listed in https://reqres.in/. The framework is based on BDD achieved using Cucumber with WebDriver to handle the UI tests and Rest Assured library to handle the API tests.

Test Scenarios - src/main/resources/Feature

Post Test Execution - 
Report  - test-output/HtmlReport/ExtentHtml.html
Logs - logs

Tools Used
Programming Language - Java
Framework Details - BDD using Cucumber and Gherkin Syntax, Selenium WebDriver, Rest-assured
Reporting - Extent-Reports
JSON libraries - for manipulting JSON data

How To Run
1. mvn clean test ( uses default properties in data.properties and cucumber options in TestRunner.java )

2. mvn -DtestType=UI -DwebUrl=http://automationpractice.com -DapiUri=https://reqres.in/ -Dbrowser=chrome -DexplicitTimeout=30 clean test

Note - 
In point 2, properties in data.properties like default timeout, web url, api url can be overridden via maven properties. This feature enables to execute tests on multiple environments , without making changes to for example urls for each environment. 
Variable Options - 
-DtestType = UI - ( to execute either UI tests or API tests or both ).
-DtestType = API ( to execute only api tests , webdriver will not be initialized as its not required for api tests and will reduce execution time significantly).

