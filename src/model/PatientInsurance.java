/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author HP
 */
public class PatientInsurance {
    private int patientInsuranceID;
    private String patientID;
    private int companyID;
    private String policyNumber;
    private boolean isValid;

    // default constructor
    public PatientInsurance() {
    }

    // constructor with args
    public PatientInsurance(int patientInsuranceID, String patientID,
                            int companyID, String policyNumber,
                            boolean isValid) {
        this.patientInsuranceID = patientInsuranceID;
        this.patientID = patientID;
        this.companyID = companyID;
        this.policyNumber = policyNumber;
        this.isValid = isValid;
    }

    // getters and setters
    public int getPatientInsuranceID() { return patientInsuranceID; }
    public void setPatientInsuranceID(int patientInsuranceID) { this.patientInsuranceID = patientInsuranceID; }

    public String getPatientID() { return patientID; }
    public void setPatientID(String patientID) { this.patientID = patientID; }

    public int getCompanyID() { return companyID; }
    public void setCompanyID(int companyID) { this.companyID = companyID; }

    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) { this.policyNumber = policyNumber; }

    public boolean isValid() { return isValid; }
    public void setValid(boolean valid) { this.isValid = valid; }
}
