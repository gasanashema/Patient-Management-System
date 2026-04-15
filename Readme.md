# PMIS — Patient Management Information System

A desktop application for managing patient records, insurance, and billing in a clinical setting.

---

## Problem Statement

Health facilities managing patient records manually face challenges with accuracy, speed, and data consistency — especially when calculating bills that involve age-based discounts and insurance coverage. Staff often make errors in fee calculation, and there is no single place to track patient registration, insurance, and payment history.

---

## Solution

PMIS is a Java Swing desktop application connected to a MySQL database (`PMIS_db`). It allows clinic staff to:

- Register and manage patients
- Assign insurance companies to patients
- Automatically calculate consultation fees with age discounts and insurance deductions
- Generate and store billing receipts
- Search, update, and delete patient records
- Manage insurance companies in the system

---

## Objectives

1. Provide a simple interface for patient registration with input validation
2. Support age-based discounts (under 12 → 50% off, over 60 → 30% off)
3. Apply insurance coverage on the discounted amount automatically
4. Store all billing records with full breakdown (original fee, discount, insurance, final amount)
5. Allow staff to search patients by ID or name
6. Support full CRUD operations on patients and insurance companies
7. Generate viewable receipts per patient

---

## Team Members & Roles

| Member | Role |
|---|---|
| Shema Gasana | Database setup & all table relationships |
| Genga Chris | Models (Patient, Insurance, Receipt, etc.) |
| Klein Christ | Patient CRUD (Create, Read, Update, Delete) |
| Kelly Kabucye | Insurance Company CRUD |
| *(unassigned)* | Receipt processing & discount logic |
| *(unassigned)* | Reporting & viewing receipts |
| Nelly Isange | Documentation (README) |
| Baributsa Jacques | UI polishing |

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java (JDK 8+) |
| UI Framework | Java Swing (NetBeans) |
| Database | MySQL |
| Connector | MySQL JDBC Driver |
| IDE | NetBeans |

---

## Database

**Database name:** `PMIS_db`

**Core tables:**

| Table | Purpose |
|---|---|
| `patient` | Core patient information |
| `insurance_company` | Insurance providers and their coverage percentages |
| `patient_insurance` | Links patients to their insurance policy |
| `receipts` | All billing transactions with discount and insurance breakdown |

**Fee Calculation Logic:**

```
Step 1 — Age Discount:
  - Age < 12  →  50% off original fee
  - Age > 60  →  30% off original fee
  - Otherwise →  no discount

Step 2 — Insurance (applied on remaining amount after age discount):
  insuranceAmount = afterAgeDiscount × coverage%

Step 3 — Final Amount:
  finalAmountPaid = afterAgeDiscount − insuranceAmount
```

---

## How to Run the Project

### Prerequisites

- Java JDK 8 or later installed
- NetBeans IDE installed
- MySQL Server running locally
- MySQL JDBC Driver (Connector/J) added to the project

### Step 1 — Set up the database

1. Open MySQL Workbench or any MySQL client
2. Create the database:
   ```sql
   CREATE DATABASE PMIS_db;
   USE PMIS_db;
   ```
3. Run the provided SQL script (`database/schema.sql`) to create all tables

### Step 2 — Configure the connection

Open the database connection file in the project (usually `DBConnection.java` or similar) and update:

```java
String url      = "jdbc:mysql://localhost:3306/PMIS_db";
String username = "root";          // your MySQL username
String password = "your_password"; // your MySQL password
```

### Step 3 — Open the project in NetBeans

1. Launch NetBeans
2. Go to **File → Open Project**
3. Select the project folder
4. Make sure the MySQL JDBC `.jar` file is added under **Libraries**

### Step 4 — Run the project

- Press **F6** or click the green **Run** button in NetBeans
- The application main window will launch

---

## Patient ID Format

Patient IDs must follow the pattern `PAT-###` (e.g., `PAT-001`, `PAT-042`).

---

## Validation Rules

| Field | Rule |
|---|---|
| Full Name | Minimum 5 characters |
| Age | Between 0 and 120 |
| Diagnosis | Minimum 5 characters |
| Consultation Fee | Between 5,000 and 50,000 RWF |
| Patient ID | Format `PAT-###`, must be unique |

---

## Payment Status Values

| Status | Meaning |
|---|---|
| `PENDING` | Bill generated but not yet paid |
| `PAID` | Full payment received |
| `PARTIAL` | Partial payment made |

---

*Developed as part of an academic group project.*
