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
public class Patient {
    private String patientID;
    private String fullName;
    private int age;
    private String gender;
    private String diagnosis;
    private double consultationFee;
    private String registrationDate;
    private boolean hasInsurance;

    // default constructor
    public Patient() {
    }

    // constructor with args
    public Patient(String patientID, String fullName, int age, String gender,
                   String diagnosis, double consultationFee,
                   String registrationDate, boolean hasInsurance) {
        this.patientID = patientID;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.diagnosis = diagnosis;
        this.consultationFee = consultationFee;
        this.registrationDate = registrationDate;
        this.hasInsurance = hasInsurance;
    }

    // getters and setters
    public String getPatientID() { return patientID; }
    public void setPatientID(String patientID) { this.patientID = patientID; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(double consultationFee) { this.consultationFee = consultationFee; }

    public String getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(String registrationDate) { this.registrationDate = registrationDate; }

    public boolean isHasInsurance() { return hasInsurance; }
    public void setHasInsurance(boolean hasInsurance) { this.hasInsurance = hasInsurance; }
}
