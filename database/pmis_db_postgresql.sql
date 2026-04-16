-- ============================================================
--  PMIS Database Schema — PostgreSQL
--  Database: PMIS_db
--  Run: psql -U postgres -f pmis_db_postgresql.sql
-- ============================================================

CREATE DATABASE "PMIS_db";
\c "PMIS_db";

-- ------------------------------------------------------------
-- 1. patient  (patientID auto-generated as PAT-0001, PAT-0002 …)
-- ------------------------------------------------------------
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
-- Demo data — insurance_company
-- ------------------------------------------------------------
INSERT INTO insurance_company (companyName, coveragePercentage, contactPhone, contactEmail) VALUES
    ('RSSB Health',    80.00, '+250788000001', 'info@rssb.rw'),
    ('MMI Rwanda',     70.00, '+250788000002', 'contact@mmi.rw'),
    ('Radiant Health', 60.00, '+250788000003', 'hello@radiant.rw'),
    ('SORAS Group',    50.00, '+250788000004', 'info@soras.rw'),
    ('UAP Insurance',  40.00, '+250788000005', 'support@uap.rw');

-- ------------------------------------------------------------
-- Demo data — patient
-- ------------------------------------------------------------
INSERT INTO patient (fullName, age, gender, diagnosis, consultationFee, registrationDate) VALUES
    ('Jean Pierre Habimana',   35, 'Male',   'Hypertension',        20000.00, '2024-01-10'),
    ('Marie Claire Uwase',      8, 'Female', 'Malaria',              5000.00, '2024-01-15'),
    ('Emmanuel Nkurunziza',    65, 'Male',   'Diabetes Type 2',     30000.00, '2024-02-03'),
    ('Aline Mukamana',         28, 'Female', 'Typhoid Fever',       15000.00, '2024-02-20'),
    ('Patrick Bizimana',       72, 'Male',   'Arthritis',           25000.00, '2024-03-05'),
    ('Claudine Uwineza',       10, 'Female', 'Respiratory Infection', 8000.00, '2024-03-18'),
    ('Innocent Niyonzima',     45, 'Male',   'Peptic Ulcer',        18000.00, '2024-04-02'),
    ('Solange Ingabire',       55, 'Female', 'Hypertension',        22000.00, '2024-04-14');

-- ------------------------------------------------------------
-- Demo data — patient_insurance
-- ------------------------------------------------------------
INSERT INTO patient_insurance (patientID, companyID, policyNumber, valid) VALUES
    ('PAT-0001', 1, 'RSSB-2024-001', TRUE),
    ('PAT-0002', 3, 'RAD-2024-002',  TRUE),
    ('PAT-0003', 2, 'MMI-2024-003',  TRUE),
    ('PAT-0004', 4, 'SOR-2024-004',  TRUE),
    ('PAT-0005', 1, 'RSSB-2024-005', FALSE),
    ('PAT-0007', 2, 'MMI-2024-007',  TRUE),
    ('PAT-0008', 5, 'UAP-2024-008',  TRUE);

-- ------------------------------------------------------------
-- Demo data — receipt
-- ------------------------------------------------------------
INSERT INTO receipt (patientID, receiptDate, originalFee, ageDiscountPercent, ageDiscountAmount,
                     insuranceCoveragePercent, insuranceCoverageAmount, finalAmountPaid, paymentStatus) VALUES
    ('PAT-0001', '2024-01-12 09:30:00', 20000.00,  0.00,    0.00,   80.00, 16000.00,  4000.00, 'PAID'),
    ('PAT-0002', '2024-01-16 10:00:00',  5000.00, 50.00, 2500.00,   60.00,  1500.00,  1000.00, 'PAID'),
    ('PAT-0003', '2024-02-05 11:15:00', 30000.00, 30.00, 9000.00,   70.00, 14700.00,  6300.00, 'PAID'),
    ('PAT-0004', '2024-02-22 08:45:00', 15000.00,  0.00,    0.00,   50.00,  7500.00,  7500.00, 'PENDING'),
    ('PAT-0005', '2024-03-07 14:00:00', 25000.00, 30.00, 7500.00,    0.00,     0.00, 17500.00, 'PENDING'),
    ('PAT-0006', '2024-03-20 09:00:00',  8000.00, 50.00, 4000.00,    0.00,     0.00,  4000.00, 'PAID'),
    ('PAT-0007', '2024-04-04 13:30:00', 18000.00,  0.00,    0.00,   70.00, 12600.00,  5400.00, 'CANCELLED'),
    ('PAT-0008', '2024-04-16 10:45:00', 22000.00,  0.00,    0.00,   40.00,  8800.00, 13200.00, 'PAID');
