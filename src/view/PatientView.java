package view;

import dao.PatientDao;
import model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class PatientView extends javax.swing.JFrame {

    //  DAO 
    PatientDao patientDao = new PatientDao();

    // ── Labels
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;

    
    private javax.swing.JTextField patientIdtxt;
    private javax.swing.JTextField fullNameTxt;
    private javax.swing.JTextField ageTxt;
    private javax.swing.JTextField genderTxt;
    private javax.swing.JTextField diagnosisTxt;
    private javax.swing.JTextField consultationTxt;
    private javax.swing.JTextField searchTxt;

    
    private javax.swing.JButton registerBtn;
    private javax.swing.JButton updateBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton viewAllBtn;

    
    private javax.swing.JTable PatientTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jPanel1;

    
    public PatientView() {
        initComponents();
        loadAllPatients();
    }

    
    private void initComponents() {

        setTitle("Patient Management — PMIS");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 680);
        setLocationRelativeTo(null);
        setLayout(new java.awt.BorderLayout(10, 10));
        getContentPane().setBackground(new Color(15, 15, 30)); 

        
        jLabel1 = new javax.swing.JLabel("PATIENT", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Tahoma", Font.BOLD, 36));
        jLabel1.setForeground(new Color(165, 140, 255));       
        jLabel1.setBackground(new Color(15, 15, 30));
        jLabel1.setOpaque(true);
        jLabel1.setBorder(BorderFactory.createEmptyBorder(18, 0, 8, 0));
        add(jLabel1, java.awt.BorderLayout.NORTH);

        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(25, 25, 50));       
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 12, 10, 6),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                "Patient Information",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Tahoma", Font.BOLD, 14),
                new Color(165, 140, 255)                 
            )
        ));
        formPanel.setPreferredSize(new Dimension(370, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill   = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Tahoma", Font.BOLD, 16);
        Font fieldFont = new Font("Tahoma", Font.PLAIN, 15);

        jLabel2 = makeLabel("Patient id :",       labelFont);
        patientIdtxt    = makeField(fieldFont);
        patientIdtxt.setToolTipText("Format: PAT-0001");
        addRow(formPanel, gbc, 0, jLabel2, patientIdtxt);

        jLabel4 = makeLabel("Full Name :",         labelFont);
        fullNameTxt     = makeField(fieldFont);
        addRow(formPanel, gbc, 1, jLabel4, fullNameTxt);

        jLabel6 = makeLabel("Age :",               labelFont);
        ageTxt          = makeField(fieldFont);
        addRow(formPanel, gbc, 2, jLabel6, ageTxt);

        jLabel7 = makeLabel("Gender :",            labelFont);
        genderTxt       = makeField(fieldFont);
        genderTxt.setToolTipText("Male / Female / Other");
        addRow(formPanel, gbc, 3, jLabel7, genderTxt);

        jLabel8 = makeLabel("Diagnosis :",         labelFont);
        diagnosisTxt    = makeField(fieldFont);
        addRow(formPanel, gbc, 4, jLabel8, diagnosisTxt);

        jLabel9 = makeLabel("Consultation Fee :",  labelFont);
        consultationTxt = makeField(fieldFont);
        consultationTxt.setToolTipText("Entre 5000 et 50000 Rwf");
        addRow(formPanel, gbc, 5, jLabel9, consultationTxt);

        
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        JLabel discountLabel = new JLabel(
            "<html><i style='color:#555'>Remise auto : &lt;12 ans → 50%  |  &gt;60 ans → 30%</i></html>");
        discountLabel.setFont(new Font("Tahoma", Font.ITALIC, 12));
        formPanel.add(discountLabel, gbc);
        gbc.gridwidth = 1;

        
        jPanel1 = new JPanel(new GridLayout(3, 2, 8, 8));
        jPanel1.setBackground(new Color(25, 25, 50));          
        jPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        registerBtn = makeButton("REGISTER", new Color(79, 70, 229));  
        updateBtn   = makeButton("UPDATE",   new Color(55, 48, 163));
        deleteBtn   = makeButton("DELETE",   new Color(30, 10, 80));    
        searchBtn   = makeButton("SEARCH",   new Color(99, 71, 255));  
        viewAllBtn  = makeButton("VIEW ALL", new Color(20, 20, 40));
        clearBtn    = makeButton("CLEAR",    new Color(40, 40, 60));  

        jPanel1.add(registerBtn);
        jPanel1.add(updateBtn);
        jPanel1.add(deleteBtn);
        jPanel1.add(searchBtn);
        jPanel1.add(viewAllBtn);
        jPanel1.add(clearBtn);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        gbc.insets = new Insets(16, 10, 10, 10);
        formPanel.add(jPanel1, gbc);

        // ── Champ de recherche
        gbc.gridy = 8;
        gbc.insets = new Insets(10, 10, 10, 10);
        JPanel searchPanel = new JPanel(new BorderLayout(6, 0));
        searchPanel.setBackground(new Color(25, 25, 50));
        JLabel searchLabel = new JLabel("Search (ID): ");
        searchLabel.setForeground(new Color(165, 140, 255));
        searchLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchTxt = makeField(fieldFont);
        searchTxt.setToolTipText("Entrer un ID ex: PAT-0001");
        searchPanel.add(searchTxt, BorderLayout.CENTER);
        formPanel.add(searchPanel, gbc);

        add(formPanel, java.awt.BorderLayout.WEST);

        
        JPanel tablePanel = new JPanel(new BorderLayout(5, 5));
        tablePanel.setBackground(new Color(25, 25, 50));
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 6, 10, 12),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                "Patient Summary",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Tahoma", Font.BOLD, 14),
                new Color(165, 140, 255)               
            )
        ));

        String[] columns = {
            "Patient ID", "Full Name", "Age", "Gender",
            "Diagnosis", "Fee ", "Reg. Date"
        };
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        PatientTable = new JTable(model);
        PatientTable.setFont(new Font("Tahoma", Font.PLAIN, 13));
        PatientTable.setRowHeight(26);
        PatientTable.setBackground(new Color(20, 20, 45));      
        PatientTable.setForeground(Color.WHITE);             
        PatientTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
        PatientTable.getTableHeader().setBackground(new Color(79, 70, 229));
        PatientTable.getTableHeader().setForeground(Color.WHITE);
        PatientTable.setSelectionBackground(new Color(99, 71, 255)); 
        PatientTable.setSelectionForeground(Color.WHITE);
        PatientTable.setGridColor(Color.BLACK);                
        PatientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
        PatientTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) fillFormFromTable();
        });

        jScrollPane1 = new JScrollPane(PatientTable);
        tablePanel.add(jScrollPane1, BorderLayout.CENTER);
        add(tablePanel, java.awt.BorderLayout.CENTER);

        
        registerBtn.addActionListener(e -> handleRegister());
        updateBtn  .addActionListener(e -> handleUpdate());
        deleteBtn  .addActionListener(e -> handleDelete());
        searchBtn  .addActionListener(e -> handleSearch());
        viewAllBtn .addActionListener(e -> loadAllPatients());
        clearBtn   .addActionListener(e -> clearForm());
    }

    
    private void handleRegister() {
        String id        = patientIdtxt.getText().trim().toUpperCase();
        String name      = fullNameTxt.getText().trim();
        String ageStr    = ageTxt.getText().trim();
        String gender    = genderTxt.getText().trim();
        String diagnosis = diagnosisTxt.getText().trim();
        String feeStr    = consultationTxt.getText().trim();

        String error = validateInputs(id, name, ageStr, diagnosis, feeStr);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error,
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int    age      = Integer.parseInt(ageStr);
        double fee      = Double.parseDouble(feeStr);
        double finalFee = applyDiscount(age, fee);

        String discountMsg = "";
        if      (age < 12) discountMsg = "\n(50% discount applied — minor patient)";
        else if (age > 60) discountMsg = "\n(30% discount applied — senior patient)";

        Patient p = new Patient();
        p.setPatientId(id);
        p.setFullName(name);
        p.setAge(age);
        p.setGender(gender);
        p.setDiagnosis(diagnosis);
        p.setConsultationFee(finalFee);
        p.setRegistrationDate(LocalDate.now()); 

        
        int rows = patientDao.addPatient(p);

        if (rows > 0) {
            JOptionPane.showMessageDialog(this,
                "Patient registered successfully !" + discountMsg,
                "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            loadAllPatients();
        } else {
            JOptionPane.showMessageDialog(this,
                "Failed to register patient. Please try again.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void handleUpdate() {
        String id        = patientIdtxt.getText().trim().toUpperCase();
        String name      = fullNameTxt.getText().trim();
        String ageStr    = ageTxt.getText().trim();
        String gender    = genderTxt.getText().trim();
        String diagnosis = diagnosisTxt.getText().trim();
        String feeStr    = consultationTxt.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please select a patient from the table first.",
                "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String error = validateInputs(id, name, ageStr, diagnosis, feeStr);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error,
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int    age      = Integer.parseInt(ageStr);
        double fee      = Double.parseDouble(feeStr);
        double finalFee = applyDiscount(age, fee);

        Patient p = new Patient();
        p.setPatientId(id);
        p.setFullName(name);
        p.setAge(age);
        p.setGender(gender);
        p.setDiagnosis(diagnosis);
        p.setConsultationFee(finalFee);
        p.setRegistrationDate(LocalDate.now());

        
        int rows = patientDao.updateClient(p);

        if (rows > 0) {
            JOptionPane.showMessageDialog(this,
                "Patient updated successfully !",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            loadAllPatients();
        } else {
            JOptionPane.showMessageDialog(this,
                "Update failed. Patient ID not found.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void handleDelete() {
        String id = patientIdtxt.getText().trim().toUpperCase();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please select a patient from the table first.",
                "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete patient: " + id + " ?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
           
            Patient p = new Patient();
            p.setPatientId(id);

            int rows = patientDao.deletePatient(p);

            if (rows > 0) {
                JOptionPane.showMessageDialog(this,
                    "Patient deleted successfully !",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllPatients();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Delete failed. Patient ID not found.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

  
    private void handleSearch() {
        String query = searchTxt.getText().trim();

        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a Patient ID to search.",
                "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

      
        Patient p = patientDao.findPatient(query.toUpperCase());

        DefaultTableModel model = (DefaultTableModel) PatientTable.getModel();
        model.setRowCount(0);

        if (p != null) {
            addPatientRow(model, p);
        } else {
            JOptionPane.showMessageDialog(this,
                "No patient found with ID: " + query,
                "Not Found", JOptionPane.INFORMATION_MESSAGE);
        }
    }

   
    private void loadAllPatients() {
        DefaultTableModel model = (DefaultTableModel) PatientTable.getModel();
        model.setRowCount(0);

        
        List<Patient> list = patientDao.allPatient();

        if (list != null) {
            for (Patient p : list) {
                addPatientRow(model, p);
            }
        }
    }

    
    private void addPatientRow(DefaultTableModel model, Patient p) {
        model.addRow(new Object[]{
            p.getPatientId(),   
            p.getFullName(),
            p.getAge(),
            p.getGender(),
            p.getDiagnosis(),
            String.format("%.2f", p.getConsultationFee()),
            p.getRegistrationDate() != null
                ? p.getRegistrationDate().toString() : ""
        });
    }

    
    private void fillFormFromTable() {
        int row = PatientTable.getSelectedRow();
        if (row == -1) return;
        DefaultTableModel model = (DefaultTableModel) PatientTable.getModel();
        patientIdtxt.setText    (model.getValueAt(row, 0).toString());
        fullNameTxt.setText     (model.getValueAt(row, 1).toString());
        ageTxt.setText          (model.getValueAt(row, 2).toString());
        genderTxt.setText       (model.getValueAt(row, 3).toString());
        diagnosisTxt.setText    (model.getValueAt(row, 4).toString());
        consultationTxt.setText (model.getValueAt(row, 5).toString());
    }

    
    private void clearForm() {
        patientIdtxt.setText("");
        fullNameTxt.setText("");
        ageTxt.setText("");
        genderTxt.setText("");
        diagnosisTxt.setText("");
        consultationTxt.setText("");
        searchTxt.setText("");
        PatientTable.clearSelection();
    }

  
    private String validateInputs(String id, String name, String ageStr,
                                  String diagnosis, String feeStr) {
       
        if (!id.matches("PAT-\\d{4}")) {
            return "Patient ID must follow the format PAT-#### (e.g. PAT-0001)";
        }
        
        if (name.length() < 5) {
            return "Full Name must be at least 5 characters.";
        }
       
        try {
            int age = Integer.parseInt(ageStr);
            if (age < 0 || age > 120) {
                return "Age must be between 0 and 120.";
            }
        } catch (NumberFormatException e) {
            return "Age must be a valid number.";
        }
        
        if (diagnosis.length() < 5) {
            return "Diagnosis must be at least 5 characters.";
        }
        
        try {
            double fee = Double.parseDouble(feeStr);
            if (fee < 5000 || fee > 50000) {
                return "Consultation Fee must be between 5,000 and 50,000 Rwf.";
            }
        } catch (NumberFormatException e) {
            return "Consultation Fee must be a valid number.";
        }
        return null; 
    }

 
    private double applyDiscount(int age, double fee) {
        if      (age < 12) return fee * 0.50; 
        else if (age > 60) return fee * 0.70; 
        else               return fee;         
    }

   
    private JLabel makeLabel(String text, Font f) {
        JLabel l = new JLabel(text);
        l.setFont(f);
        l.setForeground(new Color(165, 140, 255));           
        return l;
    }

    private JTextField makeField(Font f) {
        JTextField tf = new JTextField(14);
        tf.setFont(f);
        tf.setBackground(new Color(35, 35, 65));                
        tf.setForeground(Color.WHITE);                           
        tf.setCaretColor(new Color(165, 140, 255));             
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK), 
            BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
        return tf;
    }

    private JButton makeButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        return btn;
    }

    private void addRow(JPanel p, GridBagConstraints gbc, int row,
                        JLabel label, JTextField field) {
        gbc.gridx   = 0; gbc.gridy = row; gbc.gridwidth = 1;
        gbc.weightx = 0;
        p.add(label, gbc);
        gbc.gridx   = 1;
        gbc.weightx = 1.0;
        p.add(field, gbc);
    }


    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new PatientView().setVisible(true);
        });
    }
}
