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
public class Receipt {
    private int receiptID;
    private String patientID;
    private String receiptDate;
    private double originalFee;
    private double ageDiscountPercent;
    private double ageDiscountAmount;
    private double insuranceCoveragePercent;
    private double insuranceCoverageAmount;
    private double finalAmountPaid;
    private String paymentStatus;

    // default constructor
    public Receipt() {
    }

    // constructor with args
    public Receipt(int receiptID, String patientID, String receiptDate,
                   double originalFee, double ageDiscountPercent,
                   double ageDiscountAmount, double insuranceCoveragePercent,
                   double insuranceCoverageAmount, double finalAmountPaid,
                   String paymentStatus) {
        this.receiptID = receiptID;
        this.patientID = patientID;
        this.receiptDate = receiptDate;
        this.originalFee = originalFee;
        this.ageDiscountPercent = ageDiscountPercent;
        this.ageDiscountAmount = ageDiscountAmount;
        this.insuranceCoveragePercent = insuranceCoveragePercent;
        this.insuranceCoverageAmount = insuranceCoverageAmount;
        this.finalAmountPaid = finalAmountPaid;
        this.paymentStatus = paymentStatus;
    }

    // getters and setters
    public int getReceiptID() { return receiptID; }
    public void setReceiptID(int receiptID) { this.receiptID = receiptID; }

    public String getPatientID() { return patientID; }
    public void setPatientID(String patientID) { this.patientID = patientID; }

    public String getReceiptDate() { return receiptDate; }
    public void setReceiptDate(String receiptDate) { this.receiptDate = receiptDate; }

    public double getOriginalFee() { return originalFee; }
    public void setOriginalFee(double originalFee) { this.originalFee = originalFee; }

    public double getAgeDiscountPercent() { return ageDiscountPercent; }
    public void setAgeDiscountPercent(double ageDiscountPercent) { this.ageDiscountPercent = ageDiscountPercent; }

    public double getAgeDiscountAmount() { return ageDiscountAmount; }
    public void setAgeDiscountAmount(double ageDiscountAmount) { this.ageDiscountAmount = ageDiscountAmount; }

    public double getInsuranceCoveragePercent() { return insuranceCoveragePercent; }
    public void setInsuranceCoveragePercent(double insuranceCoveragePercent) { this.insuranceCoveragePercent = insuranceCoveragePercent; }

    public double getInsuranceCoverageAmount() { return insuranceCoverageAmount; }
    public void setInsuranceCoverageAmount(double insuranceCoverageAmount) { this.insuranceCoverageAmount = insuranceCoverageAmount; }

    public double getFinalAmountPaid() { return finalAmountPaid; }
    public void setFinalAmountPaid(double finalAmountPaid) { this.finalAmountPaid = finalAmountPaid; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}
