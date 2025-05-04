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
   [Test Cases - DailyFinance Automation Suite](https://docs.google.com/spreadsheets/d/your-link-here)

---
##  Postman Collection

 [Click here to view the Postman collection](https://documenter.getpostman.com/view/33823808/2sB2j689rL)  

 ---

 ## Allure Report  
 ![Overview](https://github.com/user-attachments/assets/db6b6033-52a3-4f2d-944b-dc694fb580da)  

 ![Behaviors](https://github.com/user-attachments/assets/6566d199-c6db-4293-892d-83f4a26ad820)

 ---

 

