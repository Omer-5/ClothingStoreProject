# Store Chain Management System

Welcome to the Store Chain Management System project repository for Internet Programming course at HIT - Holon Institute of Technology, Summer Semester 2023, under the guidance of Mr. Roi Zimon.

## Introduction
This project is a Client-Server application written in Java and using Azure SQL database.

In order to facilitate the Client-Server communication we developed a system to encode and decode actions and objects so we can send the data through a socket and take the appropriate action on the other side.


![Shop Preview](https://raw.githubusercontent.com/Omer-5/ClothingStoreProject/main/src/Store/Images/shop-preview.png)

## Main Features

1. **Real-Time Chat System:**
   - One-on-one chat system with employees in other branches. </br></br>
[![Chat Demonstration](https://markdown-videos-api.jorgenkh.no/url?url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3D9lE3vSYuEBA)](https://www.youtube.com/watch?v=9lE3vSYuEBA)

1. **Product Inventory Management:**
   - Interface to manage product inventory for each branch, facilitating buying and selling.

1. **Customer Management:**
   - Interfaces for managing customers across branches, an update is reflected in every branch.

1. **Sales Statistics Report:**
    - Produce a report of sales for a specific product or a category of products to help gain insight about the branch sales.
    - Download as a text file

1. **Admin Functionality:**
   - Admin screen for setting up employee accounts, including details and password policy.

1. **System Logs:**
    - Recording logs of actions such as employee and customer registration, sales, record chat history (if Admin enabled)

## Development Team

This project was developed by: @Omer-5, @smooth3x, @Muzikant-Oram-Rahamim

## Getting Started

To run the system locally, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/Omer-5/ClothingStoreProject.git

1. Update `Database/Secrets.properties` with [Azure DB credentials](https://learn.microsoft.com/en-us/azure/azure-sql/database/connect-query-content-reference-guide?view=azuresql)

1. Create the tables:
```SQL

 CREATE TABLE Customers (
    FullName nvarchar(255) NOT NULL,
	PhoneNumber nvarchar(255) NOT NULL,
    ID int NOT NULL,
    Type nvarchar(255) NOT NULL,
    PRIMARY KEY (ID)
);


 CREATE TABLE Employees (
    FullName nvarchar(255) NOT NULL,
	PhoneNumber nvarchar(255) NOT NULL,
    ID int NOT NULL,
    BankAccount int NOT NULL,
	Branch int NOT NULL,
	EmployeeNumber int IDENTITY(1,1) NOT NULL,
	Password nvarchar(255) NOT NULL,
	EmployeeTitle int NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE Inventory (
    branch nvarchar(255) NOT NULL,
    productID int IDENTITY(1,1) NOT NULL,
	name nvarchar(255) NOT NULL,
    category nvarchar(255) NOT NULL,
    quantity int NOT NULL,
    price float NOT NULL,
    PRIMARY KEY (productID)
);

CREATE TABLE PurchaseHistory(
    purchaseID int IDENTITY(1,1) NOT NULL,
    productID int NOT NULL,
    PRIMARY KEY (purchaseID)
);

```

4. Run `src\Store\Database\Server.java`

1. Run `src\Store\AppForms\Login.java`
