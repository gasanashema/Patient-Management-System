/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author shema
 */
public class App extends javax.swing.JFrame {

    /**
     * Creates new form App
     */
    public App() {
        initCustomComponents();
    }

    private void initCustomComponents() {
        setTitle("PMIS - Main Dashboard");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new java.awt.BorderLayout());
        
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.GridLayout(3, 1, 20, 20));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.setBackground(new java.awt.Color(15, 15, 30));
        
        javax.swing.JLabel title = new javax.swing.JLabel("Patient Management System", javax.swing.SwingConstants.CENTER);
        title.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 24));
        title.setForeground(new java.awt.Color(165, 140, 255));
        
        javax.swing.JButton btnPatient = new javax.swing.JButton("Manage Patients");
        btnPatient.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));
        btnPatient.setBackground(new java.awt.Color(79, 70, 229));
        btnPatient.setForeground(java.awt.Color.WHITE);
        btnPatient.setFocusPainted(false);
        btnPatient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPatient.addActionListener(e -> {
            new PatientView().setVisible(true);
            this.setVisible(false);
        });
        
        javax.swing.JButton btnInsurance = new javax.swing.JButton("Manage Insurance");
        btnInsurance.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16));
        btnInsurance.setBackground(new java.awt.Color(79, 70, 229));
        btnInsurance.setForeground(java.awt.Color.WHITE);
        btnInsurance.setFocusPainted(false);
        btnInsurance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInsurance.addActionListener(e -> {
            new InsuranceUI().setVisible(true);
            this.setVisible(false);
        });
        
        panel.add(title);
        panel.add(btnPatient);
        panel.add(btnInsurance);
        
        add(panel, java.awt.BorderLayout.CENTER);
    }

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
    // End of variables declaration//GEN-END:variables
}
