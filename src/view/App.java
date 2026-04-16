/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.InsuranceCompanyDao;
import dao.PatientDao;
import model.InsuranceCompany;
import model.Patient;
import model.PatientInsurance;
import model.Receipt;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author shema
 */
public class App extends javax.swing.JFrame {

    PatientDao patientDao = new PatientDao();
    InsuranceCompanyDao insuranceDao = new InsuranceCompanyDao();
    private String selectedPatientId = null;
    private int selectedCompanyId = -1;
    private Receipt currentReceipt = null;

    public App() {
        initComponents();
        loadInsuranceCombo();
        loadInvoicePatientCombo();
        refreshPatientsTable();
        refreshInsuranceTable();

        companiesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = companiesTable.getSelectedRow();
                if (row == -1) return;
                selectedCompanyId = (int) companiesTable.getValueAt(row, 0);
                companyNameTxt.setText(companiesTable.getValueAt(row, 1).toString());
                coveragePercentageTxt.setText(companiesTable.getValueAt(row, 2).toString());
                phoneCompanyTxt.setText(companiesTable.getValueAt(row, 3).toString());
                emailCompanyTxt.setText(companiesTable.getValueAt(row, 4).toString());
            }
        });

        // mutual exclusion for valid/invalid checkboxes
        insuranceValid.addActionListener(e -> { if (insuranceValid.isSelected()) insuranceInvalid.setSelected(false); });
        insuranceInvalid.addActionListener(e -> { if (insuranceInvalid.isSelected()) insuranceValid.setSelected(false); });

        patientsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = patientsTable.getSelectedRow();
                if (row == -1) return;
                selectedPatientId = patientsTable.getValueAt(row, 0).toString();
                fullNamesTxt.setText(patientsTable.getValueAt(row, 1).toString());
                ageTxt.setText(patientsTable.getValueAt(row, 2).toString());
                genderTxt.setSelectedItem(patientsTable.getValueAt(row, 3).toString());
                ageTxt1.setText(patientsTable.getValueAt(row, 4).toString());
                // fee column is "original → discounted", extract the original for editing
                // fill insurance fields
                String companyName = patientsTable.getValueAt(row, 6).toString();
                policyNumber.setText(patientsTable.getValueAt(row, 7).toString());
                String valid = patientsTable.getValueAt(row, 8).toString();
                insuranceValid.setSelected("Yes".equals(valid));
                insuranceInvalid.setSelected("No".equals(valid));
                for (int i = 0; i < patientInsuranceCompany.getItemCount(); i++) {
                    if (patientInsuranceCompany.getItemAt(i).equals(companyName)) {
                        patientInsuranceCompany.setSelectedIndex(i);
                        break;
                    }
                }
            }
        });
    }

    private void loadInvoicePatientCombo() {
        invoicePatientSelect.removeAllItems();
        invoicePatientSelect.addItem("-- Select Patient --");
        List<Patient> list = patientDao.allPatient();
        if (list != null) {
            for (Patient p : list) {
                invoicePatientSelect.addItem(p.getPatientId() + " - " + p.getFullName());
            }
        }
    }

    private void loadInsuranceCombo() {
        patientInsuranceCompany.removeAllItems();
        patientInsuranceCompany.addItem("-- None --");
        for (InsuranceCompany ic : insuranceDao.findAllCompanies()) {
            patientInsuranceCompany.addItem(ic.getCompanyName());
        }
    }

    private void refreshPatientsTable() {
        DefaultTableModel model = (DefaultTableModel) patientsTable.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{
            "Patient ID", "Full Name", "Age", "Gender", "Diagnosis",
            "Registration Date", "Insurance Company", "Policy Number", "Valid"
        });
        for (Object[] row : patientDao.allPatientsWithInsurance()) {
            model.addRow(row);
        }
    }

    private void refreshInsuranceTable() {
        List<InsuranceCompany> list = insuranceDao.findAllCompanies();
        DefaultTableModel model = (DefaultTableModel) companiesTable.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Company ID", "Company Name", "Coverage %", "Phone", "Email"});
        if (list == null) return;
        for (InsuranceCompany ic : list) {
            model.addRow(new Object[]{
                ic.getCompanyID(), ic.getCompanyName(), ic.getCoveragePercentage(),
                ic.getContactPhone(), ic.getContactEmail()
            });
        }
    }

    private void clearCompanyFields() {
        companyNameTxt.setText("");
        coveragePercentageTxt.setText("");
        phoneCompanyTxt.setText("");
        emailCompanyTxt.setText("");
        selectedCompanyId = -1;
        companiesTable.clearSelection();
    }

    private void clearPatientFields() {
        fullNamesTxt.setText("");
        ageTxt.setText("");
        ageTxt1.setText("");
        consultationFeeTxt.setText("");
        policyNumber.setText("");
        genderTxt.setSelectedIndex(0);
        patientInsuranceCompany.setSelectedIndex(0);
        insuranceValid.setSelected(false);
        insuranceInvalid.setSelected(false);
        selectedPatientId = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        tabsPane = new javax.swing.JTabbedPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientsTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fullNamesTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ageTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ageTxt1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        genderTxt = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        consultationFeeTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        addPatientButton = new javax.swing.JButton();
        updatePatientButton = new javax.swing.JButton();
        deletePatientButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        patientInsuranceCompany = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        policyNumber = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        insuranceValid = new javax.swing.JCheckBox();
        insuranceInvalid = new javax.swing.JCheckBox();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        companiesTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        companyNameTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        coveragePercentageTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        phoneCompanyTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        emailCompanyTxt = new javax.swing.JTextField();
        updateCompanyBtn = new javax.swing.JButton();
        deleteCompanyBtn = new javax.swing.JButton();
        saveCompanyBtn2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        invoicePatientSelect = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        invoiceAmount = new javax.swing.JTextField();
        previewInvoice = new javax.swing.JButton();
        saveInvoice = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        invoicePreviewNameLabel = new javax.swing.JLabel();
        invoicePreviewAgeLabel = new javax.swing.JLabel();
        invoicePreviewInsuranceCompanyNameLabel = new javax.swing.JLabel();
        invoicePreviewInsuranceDicountLabel = new javax.swing.JLabel();
        invoicePreviewAgeDicountLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        invoicePreviewAmountToPay = new javax.swing.JLabel();
        invoicePreviewAgeDiscount = new javax.swing.JLabel();
        invoicePreviewInsuranceCoverage = new javax.swing.JLabel();
        invoicePreviewFinalAmountToPay = new javax.swing.JLabel();
        invoicePreviewPaymentStatus = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        invoicesTable = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        invoiceViewNameLabel = new javax.swing.JLabel();
        invoiceViewAgeLabel = new javax.swing.JLabel();
        invoiceViewInsuranceCompanyNameLabel = new javax.swing.JLabel();
        invoiceViewInsuranceDicountLabel = new javax.swing.JLabel();
        invoiceViewAgeDicountLabel = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        invoiceViewAmountToPay = new javax.swing.JLabel();
        invoiceViewAgeDiscount = new javax.swing.JLabel();
        invoiceViewInsuranceCoverage = new javax.swing.JLabel();
        invoiceViewFinalAmountToPay = new javax.swing.JLabel();
        invoiceViewPaymentStatus = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(33, 37, 41));

        container.setBackground(new java.awt.Color(33, 37, 41));

        tabsPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        patientsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(patientsTable);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jPanel1.setBackground(new java.awt.Color(33, 37, 41));

        jLabel1.setBackground(new java.awt.Color(33, 37, 41));
        jLabel1.setFont(new java.awt.Font("Ubuntu Sans", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 193, 7));
        jLabel1.setText("ADD NEW PATIENT");

        fullNamesTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullNamesTxtActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 102, 204));
        jLabel2.setFont(new java.awt.Font("Ubuntu Sans", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 193, 7));
        jLabel2.setText("FULL NAMES");

        ageTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ageTxtActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 102, 204));
        jLabel3.setFont(new java.awt.Font("Ubuntu Sans", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 193, 7));
        jLabel3.setText("AGE");

        ageTxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ageTxt1ActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(0, 102, 204));
        jLabel4.setFont(new java.awt.Font("Ubuntu Sans", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 193, 7));
        jLabel4.setText("GENDER");

        genderTxt.setBackground(new java.awt.Color(255, 255, 255));
        genderTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        genderTxt.setToolTipText("");
        genderTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderTxtActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 102, 204));
        jLabel5.setFont(new java.awt.Font("Ubuntu Sans", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 193, 7));
        jLabel5.setText("DIAGNOSIS");

        consultationFeeTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultationFeeTxtActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 102, 204));
        jLabel6.setFont(new java.awt.Font("Ubuntu Sans", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 193, 7));
        jLabel6.setText("CONSULTATION FEE");

        addPatientButton.setBackground(new java.awt.Color(255, 193, 7));
        addPatientButton.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        addPatientButton.setForeground(new java.awt.Color(33, 37, 41));
        addPatientButton.setText("SAVE PATIENT");
        addPatientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPatientButtonActionPerformed(evt);
            }
        });

        updatePatientButton.setBackground(new java.awt.Color(255, 193, 7));
        updatePatientButton.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        updatePatientButton.setForeground(new java.awt.Color(33, 37, 41));
        updatePatientButton.setText("UPDATE PATIENT");
        updatePatientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePatientButtonActionPerformed(evt);
            }
        });

        deletePatientButton.setBackground(new java.awt.Color(255, 193, 7));
        deletePatientButton.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        deletePatientButton.setForeground(new java.awt.Color(33, 37, 41));
        deletePatientButton.setText("DELETE PATIENT");
        deletePatientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePatientButtonActionPerformed(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(0, 102, 204));
        jLabel13.setFont(new java.awt.Font("Ubuntu Sans", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 193, 7));
        jLabel13.setText("INSURANCE COMPANY");

        patientInsuranceCompany.setBackground(new java.awt.Color(255, 255, 255));
        patientInsuranceCompany.setToolTipText("");
        patientInsuranceCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientInsuranceCompanyActionPerformed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(0, 102, 204));
        jLabel14.setFont(new java.awt.Font("Ubuntu Sans", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 193, 7));
        jLabel14.setText("INSURANCE VALIDITY");

        policyNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                policyNumberActionPerformed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(0, 102, 204));
        jLabel15.setFont(new java.awt.Font("Ubuntu Sans", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 193, 7));
        jLabel15.setText("POLICY NUMBER");

        insuranceValid.setFont(new java.awt.Font("Ubuntu Sans", 1, 12)); // NOI18N
        insuranceValid.setForeground(new java.awt.Color(255, 255, 255));
        insuranceValid.setText("VALID");
        insuranceValid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insuranceValidActionPerformed(evt);
            }
        });

        insuranceInvalid.setFont(new java.awt.Font("Ubuntu Sans", 1, 12)); // NOI18N
        insuranceInvalid.setForeground(new java.awt.Color(255, 255, 255));
        insuranceInvalid.setText("INVALID");
        insuranceInvalid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insuranceInvalidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel13))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(insuranceValid)
                                .addGap(54, 54, 54)
                                .addComponent(insuranceInvalid))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ageTxt1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                .addComponent(ageTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                .addComponent(fullNamesTxt)
                                .addComponent(genderTxt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(consultationFeeTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                .addComponent(patientInsuranceCompany, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(policyNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel15)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(updatePatientButton)
                            .addGap(18, 18, 18)
                            .addComponent(addPatientButton)
                            .addGap(18, 18, 18)
                            .addComponent(deletePatientButton)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(301, 301, 301)))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fullNamesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ageTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(genderTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ageTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(consultationFeeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(patientInsuranceCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(policyNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(insuranceValid)
                    .addComponent(insuranceInvalid))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updatePatientButton)
                    .addComponent(addPatientButton)
                    .addComponent(deletePatientButton))
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel1);

        tabsPane.addTab("PATIENTS", jSplitPane1);

        companiesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(companiesTable);

        jSplitPane2.setLeftComponent(jScrollPane2);

        jPanel2.setBackground(new java.awt.Color(33, 37, 41));

        jLabel8.setFont(new java.awt.Font("Ubuntu Sans", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 193, 7));
        jLabel8.setText("COMPANY MANAGEMENT");

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Ubuntu Sans", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 193, 7));
        jLabel9.setText("NAME");

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 193, 7));
        jLabel10.setText("COVERAGE PERCENTAGE");

        coveragePercentageTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coveragePercentageTxtActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Ubuntu Sans", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 193, 7));
        jLabel11.setText("CONTACT PHONE");

        phoneCompanyTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneCompanyTxtActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Ubuntu Sans", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 193, 7));
        jLabel12.setText("CONTACT EMAIL");

        emailCompanyTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailCompanyTxtActionPerformed(evt);
            }
        });

        updateCompanyBtn.setBackground(new java.awt.Color(255, 193, 7));
        updateCompanyBtn.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        updateCompanyBtn.setForeground(new java.awt.Color(33, 37, 41));
        updateCompanyBtn.setText("UPDATE COMPANY");
        updateCompanyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCompanyBtnActionPerformed(evt);
            }
        });

        deleteCompanyBtn.setBackground(new java.awt.Color(255, 193, 7));
        deleteCompanyBtn.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        deleteCompanyBtn.setForeground(new java.awt.Color(33, 37, 41));
        deleteCompanyBtn.setText("DELETE COMPANY");
        deleteCompanyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCompanyBtnActionPerformed(evt);
            }
        });

        saveCompanyBtn2.setBackground(new java.awt.Color(255, 193, 7));
        saveCompanyBtn2.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        saveCompanyBtn2.setForeground(new java.awt.Color(33, 37, 41));
        saveCompanyBtn2.setText("SAVE COMPANY");
        saveCompanyBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveCompanyBtn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(updateCompanyBtn)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(saveCompanyBtn2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(deleteCompanyBtn))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(companyNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10)
                                .addComponent(jLabel12))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(coveragePercentageTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(phoneCompanyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(emailCompanyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companyNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(coveragePercentageTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(phoneCompanyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(emailCompanyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateCompanyBtn)
                    .addComponent(deleteCompanyBtn)
                    .addComponent(saveCompanyBtn2))
                .addContainerGap(537, Short.MAX_VALUE))
        );

        jSplitPane2.setRightComponent(jPanel2);

        tabsPane.addTab("INSURANCE COMPANIES", jSplitPane2);

        jPanel4.setBackground(new java.awt.Color(33, 37, 41));

        jLabel16.setFont(new java.awt.Font("Ubuntu Sans", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 193, 7));
        jLabel16.setText("INVOICE PROCESSING");

        invoicePatientSelect.setBackground(new java.awt.Color(255, 255, 255));
        invoicePatientSelect.setToolTipText("");
        invoicePatientSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoicePatientSelectActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Ubuntu Sans", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 193, 7));
        jLabel17.setText("SELECT PATIENT");

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Ubuntu Sans", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 193, 7));
        jLabel18.setText("AMOUNT");

        invoiceAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceAmountActionPerformed(evt);
            }
        });

        previewInvoice.setBackground(new java.awt.Color(255, 193, 7));
        previewInvoice.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        previewInvoice.setForeground(new java.awt.Color(33, 37, 41));
        previewInvoice.setText("PREVIEW INVOICE");
        previewInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewInvoiceActionPerformed(evt);
            }
        });

        saveInvoice.setBackground(new java.awt.Color(255, 193, 7));
        saveInvoice.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        saveInvoice.setForeground(new java.awt.Color(33, 37, 41));
        saveInvoice.setText("SAVE INVOICE");
        saveInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveInvoiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(previewInvoice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveInvoice)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(invoiceAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(invoicePatientSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(75, 75, 75))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invoicePatientSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(invoiceAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previewInvoice)
                    .addComponent(saveInvoice))
                .addContainerGap(376, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Ubuntu Sans", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(33, 37, 41));
        jLabel19.setText("INVOICE PREVIEW");

        jLabel20.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(33, 37, 41));
        jLabel20.setText("PATIENT INFORAMATION");

        jLabel21.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(33, 37, 41));
        jLabel21.setText("TRANSACTION INFORMATION");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel22.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(33, 37, 41));
        jLabel22.setText("NAMES");

        jLabel23.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(33, 37, 41));
        jLabel23.setText("AGE");

        jLabel24.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(33, 37, 41));
        jLabel24.setText("INSURANCE COMPANY");

        jLabel25.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(33, 37, 41));
        jLabel25.setText("INSURANCE COVERAGE");

        jLabel26.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(33, 37, 41));
        jLabel26.setText("AGE DISCOUNT");

        invoicePreviewNameLabel.setText(" ");
        invoicePreviewNameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoicePreviewAgeLabel.setText(" ");
        invoicePreviewAgeLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoicePreviewInsuranceCompanyNameLabel.setText(" ");
        invoicePreviewInsuranceCompanyNameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoicePreviewInsuranceDicountLabel.setText(" ");
        invoicePreviewInsuranceDicountLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoicePreviewAgeDicountLabel.setText(" ");
        invoicePreviewAgeDicountLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(invoicePreviewInsuranceCompanyNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addGap(121, 121, 121)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(invoicePreviewNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(invoicePreviewAgeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(invoicePreviewAgeDicountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(invoicePreviewInsuranceDicountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(invoicePreviewNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(invoicePreviewAgeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(invoicePreviewInsuranceCompanyNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(invoicePreviewInsuranceDicountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(invoicePreviewAgeDicountLabel))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel27.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(33, 37, 41));
        jLabel27.setText("AMOUNT TO PAY");

        jLabel28.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(33, 37, 41));
        jLabel28.setText("AGE DISCOUNT AMOUNT");

        jLabel29.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(33, 37, 41));
        jLabel29.setText("INSURANCE COVERAGE");

        jLabel30.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(33, 37, 41));
        jLabel30.setText("FINAL AMOUNT TO PAY");

        jLabel31.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(33, 37, 41));
        jLabel31.setText("PAYMENT STATUS");

        invoicePreviewAmountToPay.setText(" ");
        invoicePreviewAmountToPay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoicePreviewAgeDiscount.setText(" ");
        invoicePreviewAgeDiscount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoicePreviewInsuranceCoverage.setText(" ");
        invoicePreviewInsuranceCoverage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoicePreviewFinalAmountToPay.setText(" ");
        invoicePreviewFinalAmountToPay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoicePreviewPaymentStatus.setText(" ");
        invoicePreviewPaymentStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(invoicePreviewAgeDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(18, 18, 18)
                                .addComponent(invoicePreviewInsuranceCoverage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel31)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(invoicePreviewPaymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel30)
                                    .addGap(18, 18, 18)
                                    .addComponent(invoicePreviewFinalAmountToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(invoicePreviewAmountToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(invoicePreviewAmountToPay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(invoicePreviewAgeDiscount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(invoicePreviewInsuranceCoverage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(invoicePreviewFinalAmountToPay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(invoicePreviewPaymentStatus))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(108, 108, 108))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 225, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 272, Short.MAX_VALUE))
        );

        tabsPane.addTab("INVOICE PROCESSING", jPanel3);

        jPanel14.setBackground(new java.awt.Color(33, 37, 41));

        jLabel48.setFont(new java.awt.Font("Ubuntu Sans", 1, 24)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 193, 7));
        jLabel48.setText("AVAILABLE INVOICES LIST");

        invoicesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(invoicesTable);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel48)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jLabel51.setFont(new java.awt.Font("Ubuntu Sans", 1, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(33, 37, 41));
        jLabel51.setText("INVOICE PREVIEW");

        jLabel52.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(33, 37, 41));
        jLabel52.setText("PATIENT INFORAMATION");

        jLabel53.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(33, 37, 41));
        jLabel53.setText("TRANSACTION INFORMATION");

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel54.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(33, 37, 41));
        jLabel54.setText("NAMES");

        jLabel55.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(33, 37, 41));
        jLabel55.setText("AGE");

        jLabel56.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(33, 37, 41));
        jLabel56.setText("INSURANCE COMPANY");

        jLabel57.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(33, 37, 41));
        jLabel57.setText("INSURANCE COVERAGE");

        jLabel58.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(33, 37, 41));
        jLabel58.setText("AGE DISCOUNT");

        invoiceViewNameLabel.setText(" ");
        invoiceViewNameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoiceViewAgeLabel.setText(" ");
        invoiceViewAgeLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoiceViewInsuranceCompanyNameLabel.setText(" ");
        invoiceViewInsuranceCompanyNameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoiceViewInsuranceDicountLabel.setText(" ");
        invoiceViewInsuranceDicountLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoiceViewAgeDicountLabel.setText(" ");
        invoiceViewAgeDicountLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(invoiceViewInsuranceCompanyNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54)
                            .addComponent(jLabel55))
                        .addGap(121, 121, 121)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(invoiceViewNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(invoiceViewAgeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addComponent(jLabel58))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(invoiceViewAgeDicountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(invoiceViewInsuranceDicountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(invoiceViewNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(invoiceViewAgeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(invoiceViewInsuranceCompanyNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(invoiceViewInsuranceDicountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(invoiceViewAgeDicountLabel))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel59.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(33, 37, 41));
        jLabel59.setText("AMOUNT TO PAY");

        jLabel60.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(33, 37, 41));
        jLabel60.setText("AGE DISCOUNT AMOUNT");

        jLabel61.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(33, 37, 41));
        jLabel61.setText("INSURANCE COVERAGE");

        jLabel62.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(33, 37, 41));
        jLabel62.setText("FINAL AMOUNT TO PAY");

        jLabel63.setFont(new java.awt.Font("Ubuntu Sans", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(33, 37, 41));
        jLabel63.setText("PAYMENT STATUS");

        invoiceViewAmountToPay.setText(" ");
        invoiceViewAmountToPay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoiceViewAgeDiscount.setText(" ");
        invoiceViewAgeDiscount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoiceViewInsuranceCoverage.setText(" ");
        invoiceViewInsuranceCoverage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoiceViewFinalAmountToPay.setText(" ");
        invoiceViewFinalAmountToPay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        invoiceViewPaymentStatus.setText(" ");
        invoiceViewPaymentStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel60)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(invoiceViewAgeDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel61)
                                .addGap(18, 18, 18)
                                .addComponent(invoiceViewInsuranceCoverage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                    .addComponent(jLabel63)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(invoiceViewPaymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                    .addComponent(jLabel62)
                                    .addGap(18, 18, 18)
                                    .addComponent(invoiceViewFinalAmountToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(invoiceViewAmountToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(invoiceViewAmountToPay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(invoiceViewAgeDiscount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(invoiceViewInsuranceCoverage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(invoiceViewFinalAmountToPay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(invoiceViewPaymentStatus))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addComponent(jLabel51)
                .addGap(108, 108, 108))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel53)
                    .addComponent(jLabel52)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 177, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 272, Short.MAX_VALUE))
        );

        tabsPane.addTab("INVOICES", jPanel13);

        jLabel7.setFont(new java.awt.Font("Ubuntu Sans", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 193, 7));
        jLabel7.setText("PATIENT AND INSURANCE MANAGEMENT SYSTEM");

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(tabsPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabsPane)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addPatientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPatientButtonActionPerformed
        String fullName = fullNamesTxt.getText().trim();
        String ageStr   = ageTxt.getText().trim();
        String diagnosis = ageTxt1.getText().trim();
        String gender   = (String) genderTxt.getSelectedItem();
        String feeStr   = consultationFeeTxt.getText().trim();
        String policy   = policyNumber.getText().trim();
        String selectedCompany = (String) patientInsuranceCompany.getSelectedItem();

        if (fullName.isEmpty() || ageStr.isEmpty() || diagnosis.isEmpty() || feeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }
        int age; double fee;
        try {
            age = Integer.parseInt(ageStr);
            fee = Double.parseDouble(feeStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age and Consultation Fee must be numbers!");
            return;
        }
        Patient p = new Patient(null, fullName, age, gender, diagnosis, fee, LocalDate.now());
        String generatedId = patientDao.addPatient(p);
        if (generatedId != null) {
            // insert insurance if a company and policy number are provided
            if (selectedCompany != null && !selectedCompany.equals("-- None --") && !policy.isEmpty()) {
                InsuranceCompany ic = insuranceDao.findAllCompanies().stream()
                    .filter(c -> c.getCompanyName().equals(selectedCompany)).findFirst().orElse(null);
                if (ic != null) {
                    PatientInsurance pi = new PatientInsurance();
                    pi.setPatientID(generatedId);
                    pi.setCompanyID(ic.getCompanyID());
                    pi.setPolicyNumber(policy);
                    pi.setValid(insuranceValid.isSelected());
                    patientDao.addPatientInsurance(pi);
                }
            }
            JOptionPane.showMessageDialog(this, "Patient saved successfully!");
            refreshPatientsTable();
            clearPatientFields();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save patient.");
        }
    }//GEN-LAST:event_addPatientButtonActionPerformed

    private void consultationFeeTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultationFeeTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_consultationFeeTxtActionPerformed

    private void genderTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderTxtActionPerformed

    private void ageTxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ageTxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ageTxt1ActionPerformed

    private void ageTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ageTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ageTxtActionPerformed

    private void fullNamesTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullNamesTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fullNamesTxtActionPerformed

    private void coveragePercentageTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coveragePercentageTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_coveragePercentageTxtActionPerformed

    private void phoneCompanyTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneCompanyTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneCompanyTxtActionPerformed

    private void emailCompanyTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailCompanyTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailCompanyTxtActionPerformed

    private void updatePatientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePatientButtonActionPerformed
        if (selectedPatientId == null) {
            JOptionPane.showMessageDialog(this, "Select a patient from the table first.");
            return;
        }
        String fullName  = fullNamesTxt.getText().trim();
        String ageStr    = ageTxt.getText().trim();
        String diagnosis = ageTxt1.getText().trim();
        String gender    = (String) genderTxt.getSelectedItem();
        String feeStr    = consultationFeeTxt.getText().trim();
        if (fullName.isEmpty() || ageStr.isEmpty() || diagnosis.isEmpty() || feeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }
        try {
            int row = patientsTable.getSelectedRow();
            LocalDate regDate = LocalDate.parse(patientsTable.getValueAt(row, 5).toString());
            int age = Integer.parseInt(ageStr);
            double fee = Double.parseDouble(feeStr);
            Patient p = new Patient(selectedPatientId, fullName, age, gender, diagnosis, fee, regDate);
            if (patientDao.updateClient(p) > 0) {
                JOptionPane.showMessageDialog(this, "Patient updated successfully!");
                refreshPatientsTable();
                clearPatientFields();
            } else {
                JOptionPane.showMessageDialog(this, "Update failed.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age and Consultation Fee must be numbers!");
        }
    }//GEN-LAST:event_updatePatientButtonActionPerformed

    private void deletePatientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePatientButtonActionPerformed
        if (selectedPatientId == null) {
            JOptionPane.showMessageDialog(this, "Select a patient from the table first.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
            "Delete patient " + selectedPatientId + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Patient p = new Patient();
            p.setPatientId(selectedPatientId);
            if (patientDao.deletePatient(p) > 0) {
                JOptionPane.showMessageDialog(this, "Patient deleted successfully!");
                refreshPatientsTable();
                clearPatientFields();
            } else {
                JOptionPane.showMessageDialog(this, "Delete failed.");
            }
        }
    }//GEN-LAST:event_deletePatientButtonActionPerformed

    private void deleteCompanyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCompanyBtnActionPerformed
        if (selectedCompanyId == -1) {
            JOptionPane.showMessageDialog(this, "Select a company from the table first.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
            "Delete company ID " + selectedCompanyId + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (insuranceDao.deleteCompany(selectedCompanyId) > 0) {
                JOptionPane.showMessageDialog(this, "Company deleted successfully!");
                refreshInsuranceTable();
                loadInsuranceCombo();
                clearCompanyFields();
            } else {
                JOptionPane.showMessageDialog(this, "Delete failed.");
            }
        }
    }//GEN-LAST:event_deleteCompanyBtnActionPerformed

    private void patientInsuranceCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientInsuranceCompanyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientInsuranceCompanyActionPerformed

    private void policyNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_policyNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_policyNumberActionPerformed

    private void insuranceValidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insuranceValidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insuranceValidActionPerformed

    private void insuranceInvalidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insuranceInvalidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insuranceInvalidActionPerformed

    private void saveCompanyBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveCompanyBtn2ActionPerformed
        String name     = companyNameTxt.getText().trim();
        String coverage = coveragePercentageTxt.getText().trim();
        String phone    = phoneCompanyTxt.getText().trim();
        String email    = emailCompanyTxt.getText().trim();
        if (name.isEmpty() || coverage.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }
        double coverageVal;
        try {
            coverageVal = Double.parseDouble(coverage);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Coverage must be a number!");
            return;
        }
        InsuranceCompany ic = new InsuranceCompany(0, name, coverageVal, phone, email);
        if (insuranceDao.addCompany(ic) > 0) {
            JOptionPane.showMessageDialog(this, "Company saved successfully!");
            refreshInsuranceTable();
            loadInsuranceCombo();
            clearCompanyFields();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save company.");
        }
    }//GEN-LAST:event_saveCompanyBtn2ActionPerformed

    private void updateCompanyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCompanyBtnActionPerformed
        if (selectedCompanyId == -1) {
            JOptionPane.showMessageDialog(this, "Select a company from the table first.");
            return;
        }
        String name     = companyNameTxt.getText().trim();
        String coverage = coveragePercentageTxt.getText().trim();
        String phone    = phoneCompanyTxt.getText().trim();
        String email    = emailCompanyTxt.getText().trim();
        if (name.isEmpty() || coverage.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }
        double coverageVal;
        try {
            coverageVal = Double.parseDouble(coverage);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Coverage must be a number!");
            return;
        }
        InsuranceCompany ic = new InsuranceCompany(selectedCompanyId, name, coverageVal, phone, email);
        if (insuranceDao.updateCompany(ic) > 0) {
            JOptionPane.showMessageDialog(this, "Company updated successfully!");
            refreshInsuranceTable();
            loadInsuranceCombo();
            clearCompanyFields();
        } else {
            JOptionPane.showMessageDialog(this, "Update failed.");
        }
    }//GEN-LAST:event_updateCompanyBtnActionPerformed

    private void invoicePatientSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoicePatientSelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_invoicePatientSelectActionPerformed

    private void invoiceAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_invoiceAmountActionPerformed

    private void previewInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewInvoiceActionPerformed
        String selected = (String) invoicePatientSelect.getSelectedItem();
        String amountStr = invoiceAmount.getText().trim();
        if (selected == null || selected.startsWith("--")) {
            JOptionPane.showMessageDialog(this, "Please select a patient.");
            return;
        }
        if (amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an amount.");
            return;
        }
        double amount;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Amount must be a number.");
            return;
        }
        String patientID = selected.split(" - ")[0].trim();
        Object[] data = patientDao.getPatientWithInsurance(patientID);
        if (data == null) {
            JOptionPane.showMessageDialog(this, "Patient not found.");
            return;
        }
        String fullName        = data[1].toString();
        int    age             = (int) data[2];
        String companyName     = data[4].toString();
        double coveragePct     = (double) data[5];

        // age discount
        double ageDiscountPct = 0;
        if (age < 12)      ageDiscountPct = 50;
        else if (age > 60) ageDiscountPct = 30;
        double ageDiscountAmt      = amount * ageDiscountPct / 100;
        double afterAgeDiscount    = amount - ageDiscountAmt;
        double insuranceCoverageAmt = afterAgeDiscount * coveragePct / 100;
        double finalAmount         = afterAgeDiscount - insuranceCoverageAmt;

        // populate preview labels
        invoicePreviewNameLabel.setText(fullName);
        invoicePreviewAgeLabel.setText(String.valueOf(age));
        invoicePreviewInsuranceCompanyNameLabel.setText(companyName);
        invoicePreviewInsuranceDicountLabel.setText(coveragePct + "%");
        invoicePreviewAgeDicountLabel.setText(ageDiscountPct + "%");
        invoicePreviewAmountToPay.setText(String.format("%.2f", amount));
        invoicePreviewAgeDiscount.setText(String.format("%.2f", ageDiscountAmt));
        invoicePreviewInsuranceCoverage.setText(String.format("%.2f", insuranceCoverageAmt));
        invoicePreviewFinalAmountToPay.setText(String.format("%.2f", finalAmount));
        invoicePreviewPaymentStatus.setText("PENDING");

        // build receipt object ready for saving
        currentReceipt = new Receipt();
        currentReceipt.setPatientID(patientID);
        currentReceipt.setReceiptDate(LocalDateTime.now());
        currentReceipt.setOriginalFee(amount);
        currentReceipt.setAgeDiscountPercent(ageDiscountPct);
        currentReceipt.setAgeDiscountAmount(ageDiscountAmt);
        currentReceipt.setInsuranceCoveragePercent(coveragePct);
        currentReceipt.setInsuranceCoverageAmount(insuranceCoverageAmt);
        currentReceipt.setFinalAmountPaid(finalAmount);
        currentReceipt.setPaymentStatus("PENDING");
    }//GEN-LAST:event_previewInvoiceActionPerformed

    private void saveInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveInvoiceActionPerformed
        if (currentReceipt == null) {
            JOptionPane.showMessageDialog(this, "Please preview the invoice first.");
            return;
        }
        if (patientDao.saveReceipt(currentReceipt) > 0) {
            JOptionPane.showMessageDialog(this, "Invoice saved successfully!");
            currentReceipt = null;
            invoicePatientSelect.setSelectedIndex(0);
            invoiceAmount.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save invoice.");
        }
    }//GEN-LAST:event_saveInvoiceActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPatientButton;
    private javax.swing.JTextField ageTxt;
    private javax.swing.JTextField ageTxt1;
    private javax.swing.JTable companiesTable;
    private javax.swing.JTextField companyNameTxt;
    private javax.swing.JTextField consultationFeeTxt;
    private javax.swing.JPanel container;
    private javax.swing.JTextField coveragePercentageTxt;
    private javax.swing.JButton deleteCompanyBtn;
    private javax.swing.JButton deletePatientButton;
    private javax.swing.JTextField emailCompanyTxt;
    private javax.swing.JTextField fullNamesTxt;
    private javax.swing.JComboBox<String> genderTxt;
    private javax.swing.JCheckBox insuranceInvalid;
    private javax.swing.JCheckBox insuranceValid;
    private javax.swing.JTextField invoiceAmount;
    private javax.swing.JComboBox<String> invoicePatientSelect;
    private javax.swing.JLabel invoicePreviewAgeDicountLabel;
    private javax.swing.JLabel invoicePreviewAgeDiscount;
    private javax.swing.JLabel invoicePreviewAgeLabel;
    private javax.swing.JLabel invoicePreviewAmountToPay;
    private javax.swing.JLabel invoicePreviewFinalAmountToPay;
    private javax.swing.JLabel invoicePreviewInsuranceCompanyNameLabel;
    private javax.swing.JLabel invoicePreviewInsuranceCoverage;
    private javax.swing.JLabel invoicePreviewInsuranceDicountLabel;
    private javax.swing.JLabel invoicePreviewNameLabel;
    private javax.swing.JLabel invoicePreviewPaymentStatus;
    private javax.swing.JLabel invoiceViewAgeDicountLabel;
    private javax.swing.JLabel invoiceViewAgeDiscount;
    private javax.swing.JLabel invoiceViewAgeLabel;
    private javax.swing.JLabel invoiceViewAmountToPay;
    private javax.swing.JLabel invoiceViewFinalAmountToPay;
    private javax.swing.JLabel invoiceViewInsuranceCompanyNameLabel;
    private javax.swing.JLabel invoiceViewInsuranceCoverage;
    private javax.swing.JLabel invoiceViewInsuranceDicountLabel;
    private javax.swing.JLabel invoiceViewNameLabel;
    private javax.swing.JLabel invoiceViewPaymentStatus;
    private javax.swing.JTable invoicesTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JComboBox<String> patientInsuranceCompany;
    private javax.swing.JTable patientsTable;
    private javax.swing.JTextField phoneCompanyTxt;
    private javax.swing.JTextField policyNumber;
    private javax.swing.JButton previewInvoice;
    private javax.swing.JButton saveCompanyBtn2;
    private javax.swing.JButton saveInvoice;
    private javax.swing.JTabbedPane tabsPane;
    private javax.swing.JButton updateCompanyBtn;
    private javax.swing.JButton updatePatientButton;
    // End of variables declaration//GEN-END:variables
}
