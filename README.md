# Sharaf DG UI Test Automation Assignment

## Flows Tested

- Registration Flow
- Login Flow
- Product Purchase Flow (Add to Cart → Checkout → Payment)

## Tools Used

- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- Chrome browser

## Setup Instructions

1. Install Java/JDK 11+

2. Install Maven

3. Install git

4. Install Chrome browser (This framework is tested on latest version of Chrome browser - 138.0.7204.183)

5. Repo - https://github.com/Kiranramesh135/sharafdg-ui-automation.git

6. Clone the repository using 'git clone https://github.com/Kiranramesh135/sharafdg-ui-automation.git' command

7. Branch name - main

8. In pom.xml file, in <java.version>11</java.version> tag, update the value 11 to the Java version you are using. Example - 17, 21.

## Assumptions Made

- It is assumed that the pages will load within 3 minutes, if the page load takes more than 3 minutes, the framework execution fails due to timeout

- It is assumed that the automation scripts will run on the staging environment of SharafDG: https://uae.sdgstage.com.

- It is assumed that the system where the tests are run has Java (version 11 or above) and Maven installed and correctly configured with environment variables.

- Automation is primarily designed and tested for Google Chrome. Other browsers are not guaranteed to work unless additional setup is done.

- Web elements' locators (IDs, xpath, etc.) on the staging site are assumed to remain stable. Any major changes may require locator updates.

- It is assumed that the application under test has stable and predictable load.
Page load waits are handled using explicit waits

- WebDriverManager handles browser driver binaries automatically.

- Internet connection is available during execution for WebDriverManager to resolve drivers.

- No proxy or firewall restrictions block WebDriverManager or browser access.


## Test Execution Instructions

Execute command 'mvn test' using terminal/command prompt after navigating to  'sharafdg-ui-automation' folder





## Test Report

- Test report will be present under sharafdg-ui-automation/target/surefire-reports/emailable-report.html
- Open this report in a browser like chrome