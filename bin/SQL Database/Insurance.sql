create database Insurance;
use Insurance;

CREATE TABLE User (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE Client (
    clientId INT PRIMARY KEY AUTO_INCREMENT,
    clientName VARCHAR(100) NOT NULL,
    contactInfo VARCHAR(200) NOT NULL,
    policyId INT,
    FOREIGN KEY (policyId) REFERENCES Policy(policyId) ON DELETE SET NULL
);

CREATE TABLE Policy (
    policyId INT PRIMARY KEY AUTO_INCREMENT,
    policyName VARCHAR(100) NOT NULL,
    coverageAmount DECIMAL(15, 2) NOT NULL,
    terms TEXT
);

CREATE TABLE Claim (
    claimId INT PRIMARY KEY AUTO_INCREMENT,
    claimNumber VARCHAR(100) NOT NULL,
    dateFiled DATE NOT NULL,
    claimAmount DECIMAL(15, 2) NOT NULL,
    status VARCHAR(50),
    policyId INT,
    clientId INT,
    FOREIGN KEY (policyId) REFERENCES Policy(policyId) ON DELETE CASCADE,
    FOREIGN KEY (clientId) REFERENCES Client(clientId) ON DELETE CASCADE
);

CREATE TABLE Payment (
    paymentId INT PRIMARY KEY AUTO_INCREMENT,
    paymentDate DATE NOT NULL,
    paymentAmount DECIMAL(15, 2) NOT NULL,
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES Client(clientId) ON DELETE CASCADE
);

select * from policy;