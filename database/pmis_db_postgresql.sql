-- ============================================================
--  PMIS Database Schema — PostgreSQL
--  Database: PMIS_db
--  Run: psql -U postgres -f pmis_db_postgresql.sql
-- ============================================================

CREATE DATABASE "PMIS_db";
\c "PMIS_db";

-- ------------------------------------------------------------
-- 1. patient  (patientID auto-generated as PAT-0001, PAT-0002 …)
-- ------------------------------------------------------------TESTINTRETE
CREATE SEQUENCE patient_seq START 1;

CREATE TABLE patient (
    patientID        VARCHAR(10)   PRIMARY KEY DEFAULT 'PAT-' || LPAD(NEXTVAL('patient_seq')::TEXT, 4, '0'),
    fullName         VARCHAR(100)  NOT NULL,
    age              INT           NOT NULL CHECK (age BETWEEN 0 AND 120),
    gender           VARCHAR(10)   NOT NULL,
    diagnosis        VARCHAR(255)  NOT NULL,
    consultationFee  DECIMAL(10,2) NOT NULL CHECK (consultationFee >= 0),
    registrationDate DATE          NOT NULL DEFAULT CURRENT_DATE
);

-- ------------------------------------------------------------
-- 2. insurance_company
-- ------------------------------------------------------------
CREATE TABLE insurance_company (
    companyID          SERIAL        PRIMARY KEY,
    companyName        VARCHAR(100)  NOT NULL UNIQUE,
    coveragePercentage DECIMAL(5,2)  NOT NULL CHECK (coveragePercentage BETWEEN 0 AND 100),
    contactPhone       VARCHAR(20),
    contactEmail       VARCHAR(100)
);

-- ------------------------------------------------------------
-- 3. patient_insurance  (links patient <-> insurance_company)
-- ------------------------------------------------------------
CREATE TABLE patient_insurance (
    patientInsuranceID SERIAL       PRIMARY KEY,
    patientID          VARCHAR(10)  NOT NULL REFERENCES patient(patientID) ON DELETE CASCADE,
    companyID          INT          NOT NULL REFERENCES insurance_company(companyID) ON DELETE RESTRICT,
    policyNumber       VARCHAR(50)  NOT NULL UNIQUE,
    valid              BOOLEAN      NOT NULL DEFAULT TRUE
);

-- ------------------------------------------------------------
-- 4. receipt
-- ------------------------------------------------------------
CREATE TABLE receipt (
    receiptID                SERIAL        PRIMARY KEY,
    patientID                VARCHAR(10)   NOT NULL REFERENCES patient(patientID) ON DELETE RESTRICT,
    receiptDate              TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    originalFee              DECIMAL(10,2) NOT NULL,
    ageDiscountPercent       DECIMAL(5,2)  NOT NULL DEFAULT 0,
    ageDiscountAmount        DECIMAL(10,2) NOT NULL DEFAULT 0,
    insuranceCoveragePercent DECIMAL(5,2)  NOT NULL DEFAULT 0,
    insuranceCoverageAmount  DECIMAL(10,2) NOT NULL DEFAULT 0,
    finalAmountPaid          DECIMAL(10,2) NOT NULL,
    paymentStatus            VARCHAR(20)   NOT NULL DEFAULT 'PENDING'
                                           CHECK (paymentStatus IN ('PENDING', 'PAID', 'CANCELLED'))
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

INSERT INTO patient_insurance (patientID, companyID, policyNumber, valid) VALUES
    ('PAT-0001', 1, 'RSSB-2024-001', TRUE),
    ('PAT-0003', 2, 'MMI-2024-003',  TRUE);

-- To inspect generated IDs:
-- SELECT patientID, fullName FROM patient;
