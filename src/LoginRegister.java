import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LoginRegister extends javax.swing.JFrame {

    public LoginRegister() {
        initComponents();
        CardLayout cl = (CardLayout)(jPanel3.getLayout());
        cl.show(jPanel3, "card2");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        registerPanel = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label5 = new java.awt.Label();
        label6 = new java.awt.Label();
        label7 = new java.awt.Label();
        nameRegister = new javax.swing.JTextField();
        usernameRegister = new javax.swing.JTextField();
        passwordRegister = new javax.swing.JPasswordField();
        confirmPassRegister = new javax.swing.JPasswordField();
        signUpButtonReg = new javax.swing.JButton();
        label3 = new java.awt.Label();
        loginButtonReg = new javax.swing.JButton();
        loginPanel = new javax.swing.JPanel();
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
        setPreferredSize(new java.awt.Dimension(1540, 850));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));
        jPanel1.setMinimumSize(new java.awt.Dimension(1800, 980));
        jPanel1.setPreferredSize(new java.awt.Dimension(1800, 980));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new java.awt.CardLayout());

        registerPanel.setBackground(new java.awt.Color(219, 173, 138));

        label1.setFont(new java.awt.Font("Microsoft YaHei", 1, 48)); // NOI18N
        label1.setForeground(new java.awt.Color(0, 0, 0));
        label1.setText("Register");

        label2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        label2.setForeground(new java.awt.Color(0, 0, 0));
        label2.setText("Enter Name:");

        label5.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        label5.setForeground(new java.awt.Color(0, 0, 0));
        label5.setText("Enter Username:");

        label6.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        label6.setForeground(new java.awt.Color(0, 0, 0));
        label6.setText("Enter Password:");

        label7.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        label7.setForeground(new java.awt.Color(0, 0, 0));
        label7.setText("Confirm Password:");

        nameRegister.setBackground(new java.awt.Color(255, 237, 219));

        usernameRegister.setBackground(new java.awt.Color(255, 237, 219));

        passwordRegister.setBackground(new java.awt.Color(255, 237, 219));

        confirmPassRegister.setBackground(new java.awt.Color(255, 237, 219));

        signUpButtonReg.setBackground(new java.awt.Color(163, 120, 87));
        signUpButtonReg.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        signUpButtonReg.setForeground(new java.awt.Color(255, 237, 219));
        signUpButtonReg.setText("Sign Up");
        signUpButtonReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpButtonRegActionPerformed(evt);
            }
        });

        label3.setAlignment(java.awt.Label.RIGHT);
        label3.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        label3.setForeground(new java.awt.Color(0, 0, 0));
        label3.setText("Already have an account?");

        loginButtonReg.setBackground(new java.awt.Color(163, 120, 87));
        loginButtonReg.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        loginButtonReg.setForeground(new java.awt.Color(255, 237, 219));
        loginButtonReg.setText("Login");
        loginButtonReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonRegActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout registerPanelLayout = new javax.swing.GroupLayout(registerPanel);
        registerPanel.setLayout(registerPanelLayout);
        registerPanelLayout.setHorizontalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(loginButtonReg, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registerPanelLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(registerPanelLayout.createSequentialGroup()
                                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(registerPanelLayout.createSequentialGroup()
                                        .addGap(130, 130, 130)
                                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(registerPanelLayout.createSequentialGroup()
                                        .addGap(190, 190, 190)
                                        .addComponent(signUpButtonReg, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registerPanelLayout.createSequentialGroup()
                                            .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(confirmPassRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registerPanelLayout.createSequentialGroup()
                                            .addGap(20, 20, 20)
                                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(31, 31, 31)
                                            .addComponent(passwordRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registerPanelLayout.createSequentialGroup()
                                            .addGap(40, 40, 40)
                                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(nameRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registerPanelLayout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(37, 37, 37)
                                            .addComponent(usernameRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(1, 1, 1)))))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        registerPanelLayout.setVerticalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(usernameRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(passwordRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(confirmPassRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58)
                .addComponent(signUpButtonReg, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(loginButtonReg, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        jPanel3.add(registerPanel, "card3");

        loginPanel.setBackground(new java.awt.Color(219, 173, 138));
        loginPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        loginPanel.setPreferredSize(new java.awt.Dimension(656, 689));
        loginPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLoginUsername.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        lblLoginUsername.setForeground(new java.awt.Color(0, 0, 0));
        lblLoginUsername.setText("Enter Username:");
        loginPanel.add(lblLoginUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, -1, -1));

        txtLogUName.setBackground(new java.awt.Color(255, 237, 219));
        txtLogUName.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        loginPanel.add(txtLogUName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 380, 45));

        lblLoginPassword.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        lblLoginPassword.setForeground(new java.awt.Color(0, 0, 0));
        lblLoginPassword.setText("Enter Password");
        loginPanel.add(lblLoginPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logo.png"))); // NOI18N
        loginPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 460, 110));

        passLoginPass.setBackground(new java.awt.Color(255, 237, 219));
        passLoginPass.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        loginPanel.add(passLoginPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 380, 45));

        btnLoginLogin.setBackground(new java.awt.Color(163, 120, 87));
        btnLoginLogin.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        btnLoginLogin.setForeground(new java.awt.Color(255, 237, 219));
        btnLoginLogin.setText("Login");
        btnLoginLogin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLoginLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginLoginActionPerformed(evt);
            }
        });
        loginPanel.add(btnLoginLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 490, 150, 45));

        label4.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        label4.setForeground(new java.awt.Color(0, 0, 0));
        label4.setText("Don't have an account yet?");
        loginPanel.add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 610, -1, -1));

        btnLoginRegister.setBackground(new java.awt.Color(163, 120, 87));
        btnLoginRegister.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        btnLoginRegister.setForeground(new java.awt.Color(255, 237, 219));
        btnLoginRegister.setText("Register Now");
        btnLoginRegister.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLoginRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginRegisterActionPerformed(evt);
            }
        });
        loginPanel.add(btnLoginRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 640, 150, 45));

        jPanel3.add(loginPanel, "card2");

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 15, 750, 780));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Coffee Banner.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(1850, 820));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1700, 980));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 280, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginRegisterActionPerformed
        CardLayout cl = (CardLayout)(jPanel3.getLayout());
        cl.show(jPanel3, "card3");
    }//GEN-LAST:event_btnLoginRegisterActionPerformed

    private void btnLoginLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginLoginActionPerformed
    String username, password, query;
    String SUrl, SUser, SPass;
    SUrl = "jdbc:mysql://localhost:3306/cabrewdb";        
    SUser = "root";
    SPass = "";
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
             PreparedStatement pst = con.prepareStatement("SELECT password FROM accountdetail WHERE username = ?")) {
            
            if ("".equals(txtLogUName.getText())){
                JOptionPane.showMessageDialog(new JFrame(), "Username is required", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else if (passLoginPass.getPassword().length == 0) {
                JOptionPane.showMessageDialog(new JFrame(), "Password is required", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                username = txtLogUName.getText();
                char[] passwordChars = passLoginPass.getPassword();
                password = new String(passwordChars);
                
                query = "SELECT password FROM accountdetail WHERE username = '"+username+"'";  
                System.out.println("Query: " + query);
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                
                if (rs.next()){
                    String retrievedPassword = rs.getString("password");
                    System.out.println("Pass: " + rs.getString("password"));
                    if (password.equals(retrievedPassword)) {
                        System.out.print("Tama password");
                        CabrewMainPage CabrewMainPageFrame = new CabrewMainPage(); 
                        System.out.println("Nagproceed");
                        CabrewMainPageFrame.setUser(username);
                        CabrewMainPageFrame.setVisible(true);
                        CabrewMainPageFrame.pack();
                        CabrewMainPageFrame.setLocationRelativeTo(null);
                        this.dispose(); 
                        JOptionPane.showMessageDialog(new JFrame(), "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Incorrect Username or Password", "ERROR", JOptionPane.ERROR_MESSAGE);
                        passLoginPass.setText("");
                    }

                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Incorrect Username or Password", "ERROR", JOptionPane.ERROR_MESSAGE);
                    passLoginPass.setText("");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), "Error occurred during login. Try again later.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(new JFrame(), "Database driver not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
    }                                        
         
    }//GEN-LAST:event_btnLoginLoginActionPerformed

    private void signUpButtonRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpButtonRegActionPerformed
        String username, password, name;
        String SUrl, SUser, SPass;
        SUrl = "jdbc:mysql://localhost:3306/cabrewDB";
        SUser = "root";
        SPass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(SUrl, SUser, SPass)) {
                if (usernameRegister.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Username is required", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else if (passwordRegister.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Password is required", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else if (!Arrays.equals(passwordRegister.getPassword(), confirmPassRegister.getPassword())) {
                    JOptionPane.showMessageDialog(new JFrame(), "Passwords do not match", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else if (nameRegister.getText().isEmpty()) {  // Check if name field is empty
                    JOptionPane.showMessageDialog(new JFrame(), "Name is required", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    username = usernameRegister.getText();
                    char[] passwordChars = passwordRegister.getPassword();
                    password = new String(passwordChars);
                    name = nameRegister.getText();

                    String queryCheck = "SELECT COUNT(*) FROM accountdetail WHERE username = ?";
                    try (PreparedStatement pstCheck = con.prepareStatement(queryCheck)) {
                        pstCheck.setString(1, username);
                        ResultSet rs = pstCheck.executeQuery();
                        if (rs.next() && rs.getInt(1) > 0) {
                            JOptionPane.showMessageDialog(new JFrame(), "The username already exists. Please log in if you have already created an account.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String queryInsert = "INSERT INTO accountdetail(username, name, password) VALUES(?, ?, ?)";
                            try (PreparedStatement pstInsert = con.prepareStatement(queryInsert)) {
                                pstInsert.setString(1, username);
                                pstInsert.setString(2, name); // Set the name in the SQL query
                                pstInsert.setString(3, password);
                                pstInsert.executeUpdate();
                                JOptionPane.showMessageDialog(new JFrame(), "Account created successfully!");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
            JOptionPane.showMessageDialog(new JFrame(), "Error occurred during registration. Try again later.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_signUpButtonRegActionPerformed

    private void loginButtonRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonRegActionPerformed
        CardLayout cl = (CardLayout)(jPanel3.getLayout());
        cl.show(jPanel3, "card2");
    }//GEN-LAST:event_loginButtonRegActionPerformed
   
    
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
                java.util.logging.Logger.getLogger(LoginRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(LoginRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(LoginRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(LoginRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new LoginRegister().setVisible(true);
                }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoginLogin;
    private javax.swing.JButton btnLoginRegister;
    private javax.swing.JPasswordField confirmPassRegister;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label lblLoginPassword;
    private java.awt.Label lblLoginUsername;
    private javax.swing.JButton loginButtonReg;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JTextField nameRegister;
    private javax.swing.JPasswordField passLoginPass;
    private javax.swing.JPasswordField passwordRegister;
    private javax.swing.JPanel registerPanel;
    private javax.swing.JButton signUpButtonReg;
    private javax.swing.JTextField txtLogUName;
    private javax.swing.JTextField usernameRegister;
    // End of variables declaration//GEN-END:variables
}
