
public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblLoginUsername = new java.awt.Label();
        txtLogUName = new javax.swing.JTextField();
        lblLoginPassword = new java.awt.Label();
        jLabel3 = new javax.swing.JLabel();
        passLoginPass = new javax.swing.JPasswordField();
        btnLoginLogin = new javax.swing.JButton();
        label4 = new java.awt.Label();
        btnLoginRegister = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1920, 1080));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));
        jPanel1.setMinimumSize(new java.awt.Dimension(950, 540));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 204, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLoginUsername.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        lblLoginUsername.setForeground(new java.awt.Color(51, 51, 51));
        lblLoginUsername.setText("Enter Username:");
        jPanel2.add(lblLoginUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, -1, -1));

        txtLogUName.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        txtLogUName.setForeground(new java.awt.Color(51, 51, 51));
        jPanel2.add(txtLogUName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 380, 45));

        lblLoginPassword.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        lblLoginPassword.setForeground(new java.awt.Color(51, 51, 51));
        lblLoginPassword.setText("Enter Password");
        jPanel2.add(lblLoginPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logo.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 460, 110));

        passLoginPass.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        passLoginPass.setForeground(new java.awt.Color(51, 51, 51));
        jPanel2.add(passLoginPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 380, 45));

        btnLoginLogin.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        btnLoginLogin.setForeground(new java.awt.Color(51, 51, 51));
        btnLoginLogin.setText("Login");
        btnLoginLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginLoginActionPerformed(evt);
            }
        });
        jPanel2.add(btnLoginLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 490, 105, 31));

        label4.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        label4.setForeground(new java.awt.Color(51, 51, 51));
        label4.setText("Don't have an account yet?");
        jPanel2.add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 610, -1, -1));

        btnLoginRegister.setFont(new java.awt.Font("Microsoft YaHei", 0, 14)); // NOI18N
        btnLoginRegister.setText("Register Now");
        btnLoginRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginRegisterActionPerformed(evt);
            }
        });
        jPanel2.add(btnLoginRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 640, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 720, 770));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Coffee Banner.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginRegisterActionPerformed
        Register RegisterFrame = new Register();
        RegisterFrame.setVisible(true);
        RegisterFrame.pack();
        RegisterFrame.setLocationRelativeTo(null); 
        this.dispose();
    }//GEN-LAST:event_btnLoginRegisterActionPerformed

    private void btnLoginLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginLoginActionPerformed
        HomePage HomePageFrame = new HomePage();
        HomePageFrame.setVisible(true);
        HomePageFrame.pack();
        HomePageFrame.setLocationRelativeTo(null); 
        this.dispose();
    }//GEN-LAST:event_btnLoginLoginActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoginLogin;
    private javax.swing.JButton btnLoginRegister;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private java.awt.Label label4;
    private java.awt.Label lblLoginPassword;
    private java.awt.Label lblLoginUsername;
    private javax.swing.JPasswordField passLoginPass;
    private javax.swing.JTextField txtLogUName;
    // End of variables declaration//GEN-END:variables
}
