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
public class InsuranceCompany {
    private int companyID;
    private String companyName;
    private double coveragePercentage;
    private String contactPhone;
    private String contactEmail;

    public InsuranceCompany() {
    }

    public InsuranceCompany(int companyID, String companyName, double coveragePercentage, String contactPhone, String contactEmail) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.coveragePercentage = coveragePercentage;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getCoveragePercentage() {
        return coveragePercentage;
    }

    public void setCoveragePercentage(double coveragePercentage) {
        this.coveragePercentage = coveragePercentage;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return companyName;
    }
 
}
