USE master
GO

DROP DATABASE DeliciousCateringDBD
GO

CREATE DATABASE DeliciousCateringDBD
GO

USE DeliciousCateringDBD
GO

CREATE TABLE Client
(
[ClientID] int PRIMARY KEY IDENTITY(1,1),
[FirstName] VARCHAR(50) NOT NULL, --Name of customer
[LastName] VARCHAR(50) NOT NULL,  --Surname of customer
[Email] VARCHAR(100) NOT NULL,  --Email of customer
[Password] VARCHAR(30) NOT NULL,  --Password for login purposes for customer
[P_Number] VARCHAR(10) NOT NULL  --Phone number of customer
)
GO

CREATE TABLE Menu
(
[FoodID] int PRIMARY KEY IDENTITY(1,1), --FoodID
[Type] VARCHAR(20) NOT NULL, --Adult,Kids,Drinks,Dessert
[Price] MONEY NOT NULL, --Price per food type
)
GO

CREATE TABLE EventDetails
(
EventID int PRIMARY KEY IDENTITY(1,1),
[ClientID] int FOREIGN KEY REFERENCES Client(ClientID),
[Type] VARCHAR(20),  --Type of event Example; Wedding, Barbeque
[Date] DATE,  --Date of event
[Time] TIME,  --Time of event
[Address] VARCHAR(200),  --Address for event example : 56 Langeberg Avenue, Kempton Park, Gauteng
[TotalAdults] int,  --Total adults attending event
[TotalKids] int,  --Total Kids attending event
[AdultMeals] int,  --amount needed for event
[KidMeals] int ,  --amount needed for event
[Drinks] int , --amount needed for event
[Desserts] int , --amount needed for event
[Payed] VARCHAR(10),  --Yes/No/Deposit
[Amnt_Outstanding] MONEY,  --
[Total_Price] MONEY ,
[Decorations] VARCHAR(200), --N/a   or  Yes-Blue balloons. Please make cake red, have banners
[DecorPrice] MONEY
)
GO

INSERT INTO Client 
VALUES
('Brent', 'Plage', 'Plage@gmail.com', 'plagemaster', '0866598745'),
('Claude', 'Pretorius', 'ClaudeP@gmail.com', 'pretoriusmaster', '0866598745')
GO


INSERT INTO Menu
VALUES
('Adult-Meal', 110),
('Kid-Meal', 75),
('Alcohol-Drink', 30),
('Non-Alcohol-Drink', 20),
('Dessert',55)
GO

INSERT INTO EventDetails
values
(2,'Wedding','2021/12/17', '13:30', '56 Platberg Avenue, Kempton Park, Gauteng', 10, 5, 8, 4, 15, 15,'Deposit', 253,506, 'N/a', 0),
(1,'Barbeque','2021/12/18', '11:25', '84 Johnson Avenue, Boksburg, Gauteng', 11, 9, 11, 9, 20, 20 ,'Yes', 0, 600, 'Yes- 50 blue balloons on the front gate and 10 surprise wrappers per meal with a toy in', 50)
GO

SELECT * FROM Client
GO
SELECT * FROM Menu
GO
SELECT * FROM EventDetails
GO
