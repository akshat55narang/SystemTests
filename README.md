# SystemTests
Test Automation framework for practice website and apis. 
<br/>Note - All tests have not been automated. However, the project provides an insight in the architechture of a test automation framework to test API and UI in a BDD framewok implemented using Cucumber, Selenium Webdriver and Rest Assured.

Overview<br/> This test framework includes UI tests for practice website http://automationpractice.com/ and api tests for apis listed in https://reqres.in/. The framework is based on BDD achieved using Cucumber with WebDriver to handle the UI tests and Rest Assured library to handle the API tests.

Test Scenarios - src/main/resources/Feature

Post Test Execution<br/>
Report  - test-output/HtmlReport/ExtentHtml.html<br/>
Logs - logs

# How To Run

1. mvn -DwebUrl=http://automationpractice.com -DapiUri=https://reqres.in/ -Dbrowser=chrome -DexplicitTimeout=30 "-Dcucumber.options=--tags @api" clean test
( run only api tests based on cucumber tags, webdriver will not be initialized as its not required for api tests and will reduce execution time significantly )

2. mvn -DwebUrl=http://automationpractice.com -DapiUri=https://reqres.in/ -Dbrowser=chrome -DexplicitTimeout=30 "-Dcucumber.options=--tags @ui" clean test
( run only ui tests based on cucumber tags )

3. mvn -DwebUrl=http://automationpractice.com -DapiUri=https://reqres.in/ -Dbrowser=chrome -DexplicitTimeout=30 "-Dcucumber.options=--tags '@ui or @api'" clean test
(Run both ui and api tests based on cucumber tags )


# Tools Used
Programming Language - Java<br/>
Framework Details - BDD using Cucumber and Gherkin Syntax, Selenium WebDriver, Rest-assured<br/>
Reporting - Extent-Reports<br/>
JSON libraries - for manipulting JSON data<br/>

#To-Do
 1. Capture screen shot on UI test failures.
 2. Store request and response in the logs in case of api test failure.

