-- ============================================================
--  PMIS Database Schema — MySQL
--  Database: pmis_db
--  Run: mysql -u root -p < pmis_db_mysql.sql
-- ============================================================

CREATE DATABASE IF NOT EXISTS pmis_db;
USE pmis_db;

-- ------------------------------------------------------------
-- 1. patient  (patientID auto-generated as PAT-0001, PAT-0002 …)
-- ------------------------------------------------------------
CREATE TABLE patient (
    patientID        VARCHAR(10)   PRIMARY KEY,
    fullName         VARCHAR(100)  NOT NULL,
    age              INT           NOT NULL CHECK (age BETWEEN 0 AND 120),
    gender           VARCHAR(10)   NOT NULL,
    diagnosis        VARCHAR(255)  NOT NULL,
    consultationFee  DECIMAL(10,2) NOT NULL,
    registrationDate DATE          NOT NULL DEFAULT (CURRENT_DATE)
);

-- Sequence table to track the next patient number
CREATE TABLE patient_id_seq (
    next_val INT NOT NULL DEFAULT 1
);
INSERT INTO patient_id_seq VALUES (1);

-- Trigger to auto-generate PAT-#### on INSERT
DELIMITER $$
CREATE TRIGGER before_patient_insert
BEFORE INSERT ON patient
FOR EACH ROW
BEGIN
    DECLARE next_num INT;
    SELECT next_val INTO next_num FROM patient_id_seq FOR UPDATE;
    SET NEW.patientID = CONCAT('PAT-', LPAD(next_num, 4, '0'));
    UPDATE patient_id_seq SET next_val = next_num + 1;
END$$
DELIMITER ;

-- ------------------------------------------------------------
-- 2. insurance_company
-- ------------------------------------------------------------
CREATE TABLE insurance_company (
    companyID          INT           PRIMARY KEY AUTO_INCREMENT,
    companyName        VARCHAR(100)  NOT NULL UNIQUE,
    coveragePercentage DECIMAL(5,2)  NOT NULL,
    contactPhone       VARCHAR(20),
    contactEmail       VARCHAR(100)
);

-- ------------------------------------------------------------
-- 3. patient_insurance
-- ------------------------------------------------------------
CREATE TABLE patient_insurance (
    patientInsuranceID INT          PRIMARY KEY AUTO_INCREMENT,
    patientID          VARCHAR(10)  NOT NULL,
    companyID          INT          NOT NULL,
    policyNumber       VARCHAR(50)  NOT NULL UNIQUE,
    valid              TINYINT(1)   NOT NULL DEFAULT 1,
    FOREIGN KEY (patientID)  REFERENCES patient(patientID)          ON DELETE CASCADE,
    FOREIGN KEY (companyID)  REFERENCES insurance_company(companyID) ON DELETE RESTRICT
);

-- ------------------------------------------------------------
-- 4. receipt
-- ------------------------------------------------------------
CREATE TABLE receipt (
    receiptID                INT           PRIMARY KEY AUTO_INCREMENT,
    patientID                VARCHAR(10)   NOT NULL,
    receiptDate              DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    originalFee              DECIMAL(10,2) NOT NULL,
    ageDiscountPercent       DECIMAL(5,2)  NOT NULL DEFAULT 0,
    ageDiscountAmount        DECIMAL(10,2) NOT NULL DEFAULT 0,
    insuranceCoveragePercent DECIMAL(5,2)  NOT NULL DEFAULT 0,
    insuranceCoverageAmount  DECIMAL(10,2) NOT NULL DEFAULT 0,
    finalAmountPaid          DECIMAL(10,2) NOT NULL,
    paymentStatus            VARCHAR(20)   NOT NULL DEFAULT 'PENDING',
    FOREIGN KEY (patientID) REFERENCES patient(patientID) ON DELETE RESTRICT,
    CONSTRAINT chk_status CHECK (paymentStatus IN ('PENDING', 'PAID', 'CANCELLED'))
);

-- ------------------------------------------------------------
-- Sample data
-- ------------------------------------------------------------
INSERT INTO insurance_company (companyName, coveragePercentage, contactPhone, contactEmail) VALUES
    ('RSSB Health',    80.00, '+250788000001', 'info@rssb.rw'),
    ('MMI Rwanda',     70.00, '+250788000002', 'contact@mmi.rw'),
    ('Radiant Health', 60.00, '+250788000003', 'hello@radiant.rw');

INSERT INTO patient (fullName, age, gender, diagnosis, consultationFee) VALUES
    ('Jean Pierre Habimana',   35, 'Male',   'Hypertension',   20000.00),
    ('Marie Claire Uwase',      8, 'Female', 'Malaria',         5000.00),
    ('Emmanuel Nkurunziza',    65, 'Male',   'Diabetes Type 2', 30000.00);

-- To inspect generated IDs:
-- SELECT patientID, fullName FROM patient;

INSERT INTO patient_insurance (patientID, companyID, policyNumber, valid) VALUES
    ('PAT-0001', 1, 'RSSB-2024-001', 1),
    ('PAT-0003', 2, 'MMI-2024-003',  1);
