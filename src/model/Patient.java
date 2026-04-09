/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

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
    private LocalDate registrationDate;
    private boolean insured;

    public Patient() {
    }

    public Patient(String patientID, String fullName, int age, String gender, String diagnosis, double consultationFee, LocalDate registrationDate, boolean insured) {
        this.patientID = patientID;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.diagnosis = diagnosis;
        this.consultationFee = consultationFee;
        this.registrationDate = registrationDate;
        this.insured = insured;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isInsured() {
        return insured;
    }

    public void setInsured(boolean insured) {
        this.insured = insured;
    }

   
}
 