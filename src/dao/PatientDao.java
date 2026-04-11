
package dao;

import model.Patient;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PatientDao {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/pmis_db";
    private String dbUsername = "-------";
    private String dbPassword ="--------";
    
    // method to add a patient 
    public int addPatient(Patient patientObj){
        
       try{
           String sql = "INSERT INTO patient (patientID,fullName,age,gender,diagnosis,consultationFee,registrationDate)VALUES (?,?,?,?,?,?,?)";
           Connection con = DriverManager.
                   getConnection(jdbcUrl,dbUsername,dbPassword);
           PreparedStatement pst = con.prepareStatement(sql);
           pst.setString(1,patientObj.getPatientId());
           pst.setString(2,patientObj.getFullName());
           pst.setInt(3,patientObj.getAge());
           pst.setString(4,patientObj.getGender());
           pst.setString(5,patientObj.getDiagnosis());
           pst.setDouble(6,patientObj.getConsultationFee());
           pst.setDate(7,Date.valueOf(patientObj.getRegistrationDate()));
           
           int rowsAffected = pst.executeUpdate();
           con.close();
            return rowsAffected;
           
       }catch(Exception ex){
           ex.printStackTrace();
       }
        return 0;  
    }
    
    // method to update client informations 
    public int updateClient(Patient patientObj){
        String sql = "UPDATE patient set fullName=?,age=?,gender=?,diagnosis=?,consultationFee=?,registrationDate=? where patientID=?";
        
    try{
       Connection con =DriverManager.
               getConnection(jdbcUrl,dbUsername,dbPassword);
       PreparedStatement pst = con.prepareStatement(sql);
       pst.setString(1,patientObj.getFullName());
       pst.setInt(2,patientObj.getAge());
       pst.setString(3, patientObj.getGender());
       pst.setString(4,patientObj.getDiagnosis());
       pst.setDouble(5,patientObj.getConsultationFee());
       pst.setDate(6,Date.valueOf(patientObj.getRegistrationDate()));
       pst.setString(7,patientObj.getPatientId());
       
       int rowsAffected = pst.executeUpdate();
       con.close();
       return rowsAffected;
       
    }catch(Exception ex){
        ex.printStackTrace();
    }
        
        return 0;
        
    }
    
    // method to delete a client
    public int deletePatient(Patient patientObj){
        String sql = "DELETE FROM patient where patientID = ?";
        try{
            Connection con = DriverManager.
                    getConnection(jdbcUrl, dbUsername, dbPassword);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,patientObj.getPatientId());
            
            return pst.executeUpdate();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
        
    }
    
    // method to find a Patient and displays informations 
    public Patient findPatient(String PatientID){
      String sql= "SELECT * FROM patient where PatientID=?";
      Patient patientObj= null;
      try{
          Connection con = DriverManager.
                  getConnection(jdbcUrl,dbUsername,dbPassword);
         PreparedStatement pst = con.prepareStatement(sql);
         pst.setString(1, PatientID);
         
         ResultSet rs = pst.executeQuery();
         if(rs.next()){
             patientObj = new Patient();
             
             patientObj.setPatientId(rs.getString("PatientID"));
             patientObj.setFullName(rs.getString("fullName"));
             patientObj.setAge(rs.getInt("age"));
             patientObj.setGender(rs.getString("gender"));
             patientObj.setDiagnosis(rs.getString("diagnosis"));
             patientObj.setConsultationFee(rs.getDouble("consultationFee"));
             patientObj.setRegistrationDate(rs.getDate("registrationDate").toLocalDate());
             con.close();
         }
      }catch(Exception ex){
          ex.printStackTrace();
      }
        return patientObj;
        
        
    }
    
    // method to displays all Patient 
   public List<Patient> allPatient(){
       String sql = "SELECT * FROM Patient";
        List<Patient> patientList = new ArrayList<>();
    try{
        Connection con = DriverManager.
                getConnection(jdbcUrl,dbUsername, dbPassword);
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            Patient patientObj = new Patient();
            patientObj.setPatientId(rs.getString("PatientID"));
            patientObj.setFullName(rs.getString("fullName"));
            patientObj.setAge(rs.getInt("age"));
            patientObj.setGender(rs.getString("gender"));
            patientObj.setDiagnosis(rs.getString("diagnosis"));
            patientObj.setConsultationFee(rs.getDouble("consultationFee"));
            patientObj.setRegistrationDate(rs.getDate("registrationDate").toLocalDate());
             
            patientList.add(patientObj);
        }
        con.close();
        return patientList;
    }catch(Exception ex){
        ex.printStackTrace();
    }
        return null;
    
}
}
