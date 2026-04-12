package dao;

import model.InsuranceCompany;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InsuranceCompanyDao {
    // JDBC properties
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/PMIS_db";
    private String dbUsername = "postgres";
    private String dbPassword = "postgres";

    // CREATE: Add a new insurance company
    public int addCompany(InsuranceCompany ic) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "INSERT INTO insurance_company (companyName, coveragePercentage, contactPhone, contactEmail) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ic.getCompanyName());
            pst.setDouble(2, ic.getCoveragePercentage());
            pst.setString(3, ic.getContactPhone());
            pst.setString(4, ic.getContactEmail());
            int rows = pst.executeUpdate();
            con.close();
            return rows;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    // READ: Get all insurance companies
    public List<InsuranceCompany> findAllCompanies() {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM insurance_company";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            List<InsuranceCompany> list = new ArrayList<>();
            while(rs.next()) {
                InsuranceCompany ic = new InsuranceCompany();
                ic.setCompanyID(rs.getInt("companyID"));
                ic.setCompanyName(rs.getString("companyName"));
                ic.setCoveragePercentage(rs.getDouble("coveragePercentage"));
                ic.setContactPhone(rs.getString("contactPhone"));
                ic.setContactEmail(rs.getString("contactEmail"));
                list.add(ic);
            }
            con.close();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    // UPDATE: Update an insurance company
    public int updateCompany(InsuranceCompany ic) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "UPDATE insurance_company SET companyName=?, coveragePercentage=?, contactPhone=?, contactEmail=? WHERE companyID=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ic.getCompanyName());
            pst.setDouble(2, ic.getCoveragePercentage());
            pst.setString(3, ic.getContactPhone());
            pst.setString(4, ic.getContactEmail());
            pst.setInt(5, ic.getCompanyID());
            int rows = pst.executeUpdate();
            con.close();
            return rows;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    // SEARCH: Find company by ID
    public InsuranceCompany findCompanyById(int companyID) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM insurance_company WHERE companyID=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, companyID);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                InsuranceCompany ic = new InsuranceCompany();
                ic.setCompanyID(rs.getInt("companyID"));
                ic.setCompanyName(rs.getString("companyName"));
                ic.setCoveragePercentage(rs.getDouble("coveragePercentage"));
                ic.setContactPhone(rs.getString("contactPhone"));
                ic.setContactEmail(rs.getString("contactEmail"));
                return ic;
            }
            con.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // DELETE: Remove company
    public int deleteCompany(int companyID) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "DELETE FROM insurance_company WHERE companyID=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, companyID);
            int rows = pst.executeUpdate();
            con.close();
            return rows;
        } catch(Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}