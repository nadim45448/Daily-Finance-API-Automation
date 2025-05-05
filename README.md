# Daily Finance API Automation 

##  Project Overview

This project demonstrates API automation testing using **Rest Assured** on the website [https://dailyfinance.roadtocareer.net](https://dailyfinance.roadtocareer.net). The API endpoints were first explored and tested using **Postman**, and then automated using Java, Rest Assured, TestNG, and Allure reporting following the **Page Object Model (POM)** architecture.

---

##  Features Covered

The following API functionalities were tested:

###  Authentication
- Register a new user
- Login by admin
- Login by any user

###  User Management
- Get user list
- Search user by ID
- Edit user info 

###  Item Management
- Get item list
- Add a new item
- Edit an item name
- Delete any item

---

##  Negative Test Cases

Where applicable, negative test cases were added, such as:
- Register with an existing email
- Login with wrong credentials
- Search user with invalid ID
- Add/edit/delete item without authorization

---
##  Tech Stack

| Tool/Framework          | Purpose                    |
|-------------------------|----------------------------|
| **Java**                | Programming Language       |
| **Selenium WebDriver**  | UI Automation              |
| **TestNG**              | Test Framework             |
| **Rest Assured**        | API Testing                |
| **Gradle**              | Build Tool                 |
| **Allure**              | Reporting                  |
| **Java Faker**          | Random Data Generation     |

---
##  Test Case Documentation

 All standard and negative test cases are documented in this Google Sheet:  
   [Test Cases - DailyFinance Automation Suite](https://docs.google.com/spreadsheets/d/1NlfUyR8sto8RplPIzQyfnMtbWHXMFjLeLZUeaKXwPEs/edit?usp=sharing)

---
##  Postman Collection

 [Click here to view the Postman collection](https://documenter.getpostman.com/view/33823808/2sB2j689rL)  

 ---

 ## Allure Report  
 ![Overview](https://github.com/user-attachments/assets/e970cc5d-2018-44de-aba4-e08fe894b5ee)

 ![Behaviors](https://github.com/user-attachments/assets/088ba8fa-fd2b-4dac-8cd6-9b9f64fe66a5)


 ---
 ##  Pre-requisites

Before running the project, make sure the following are installed:

1. **Java JDK 17+**  
    [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)

2. **Gradle**  
    [Install Gradle](https://gradle.org/install/)

3. **IntelliJ IDEA** (Recommended IDE)

4. **Allure CLI (for reports)**  
    [Install Allure](https://docs.qameta.io/allure/#_installing_a_commandline)

5. **Chrome WebDriver**  
   Ensure it's compatible with your Chrome version. Place it in your system PATH.

6. **Gmail API access**  
   Set up credentials for accessing Gmail inbox programmatically (for password/email assertions).
   
---
##  Project Setup & Execution
1. Clone the repository:
   ```bash
   git clone https://github.com/nadim45448/Daily-Finance-API-Automation.git
   ```
    - cd DailyFinance-FullStackAutomation
2. Configure Gmail Credentials:  
    - Setup Gmail API token securely for reading mails.
3. Run Tests via Gradle:
   - gradle clean test
4. Generate and Serve Allure Report
   ```bash
   allure generate allure-results --clean -o allure-report
   allure serve allure-results
   ```
   ---

 

