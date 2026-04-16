
package model;

import java.time.LocalDate;


public class Patient {
    private String patientId;
    private String fullName;
    private int age;
    private String gender;
    private String diagnosis;
    private double consultationFee;
    private double originalFee;
    private LocalDate registrationDate;

    public Patient() {
    }

    public Patient(String patientId, String fullName, int age, String gender, String diagnosis, double consultationFee, LocalDate registrationDate) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.diagnosis = diagnosis;
        this.consultationFee = consultationFee;
        this.originalFee = consultationFee;
        this.registrationDate = registrationDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public double getOriginalFee() {
        return originalFee;
    }

    public void setOriginalFee(double originalFee) {
        this.originalFee = originalFee;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    
    
}
