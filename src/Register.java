import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Register extends javax.swing.JFrame {

    public Register() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        usernameRegister = new javax.swing.JTextField();
        label2 = new java.awt.Label();
        label4 = new java.awt.Label();
        passwordRegister = new javax.swing.JPasswordField();
        label5 = new java.awt.Label();
        confirmPassRegister = new javax.swing.JPasswordField();
        signUpButtonReg = new javax.swing.JButton();
        label6 = new java.awt.Label();
        label3 = new java.awt.Label();
        loginButtonReg = new javax.swing.JButton();
        nameRegister = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1920, 1080));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label1.setFont(new java.awt.Font("Microsoft YaHei", 1, 48)); // NOI18N
        label1.setForeground(new java.awt.Color(51, 51, 51));
        label1.setText("Register");
        jPanel1.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, -1));

        usernameRegister.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.add(usernameRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 300, 45));

        label2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        label2.setForeground(new java.awt.Color(51, 51, 51));
        label2.setText("Enter Name:");
        jPanel1.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, -1, -1));

        label4.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        label4.setForeground(new java.awt.Color(51, 51, 51));
        label4.setText("Enter Username:");
        jPanel1.add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, -1, -1));

        passwordRegister.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.add(passwordRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 300, 45));

        label5.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        label5.setForeground(new java.awt.Color(51, 51, 51));
        label5.setText("Enter Password:");
        jPanel1.add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, -1, -1));

        confirmPassRegister.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.add(confirmPassRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 400, 300, 45));

        signUpButtonReg.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        signUpButtonReg.setText("Sign Up");
        signUpButtonReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpButtonRegActionPerformed(evt);
            }
        });
        jPanel1.add(signUpButtonReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 500, -1, -1));

        label6.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        label6.setForeground(new java.awt.Color(51, 51, 51));
        label6.setText("Confirm Password:");
        jPanel1.add(label6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, -1, -1));

        label3.setAlignment(java.awt.Label.RIGHT);
        label3.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        label3.setForeground(new java.awt.Color(51, 51, 51));
        label3.setText("Already have an account?");
        jPanel1.add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 590, -1, -1));

        loginButtonReg.setFont(new java.awt.Font("Microsoft YaHei", 0, 14)); // NOI18N
        loginButtonReg.setText("Login");
        loginButtonReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonRegActionPerformed(evt);
            }
        });
        jPanel1.add(loginButtonReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 620, 100, -1));

        nameRegister.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.add(nameRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 300, 45));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 720, 770));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Coffee Banner.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signUpButtonRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpButtonRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_signUpButtonRegActionPerformed

    private void loginButtonRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonRegActionPerformed
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null); 
        this.dispose();
    }//GEN-LAST:event_loginButtonRegActionPerformed

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField confirmPassRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private javax.swing.JButton loginButtonReg;
    private javax.swing.JTextField nameRegister;
    private javax.swing.JPasswordField passwordRegister;
    private javax.swing.JButton signUpButtonReg;
    private javax.swing.JTextField usernameRegister;
    // End of variables declaration//GEN-END:variables
}
