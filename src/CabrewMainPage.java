import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class CabrewMainPage extends javax.swing.JFrame {
        
    private JScrollPane scrollPane;
    private String currentProductId;
    private JButton activeSizeOption = null;
    private DefaultTableModel tableModel;
    private JButton previousClickedButton = null;
    
    public CabrewMainPage() {
        initComponents();    
        initializeUI();  
        initializeTable();
        customizeTableLook();
        addListenersToComponents();
    }    
    
    private void initializeUI() {
        setTitle("Cabrew Main Page");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        parentPanel.add(homePagePanel, "homePagePanel");
        parentPanel.add(orderCMPPanel, "orderCMPPanel");
        parentPanel.add(historyPanel, "historyPanel");
        // add other panels if needed

        CardLayout card = (CardLayout) parentPanel.getLayout();
        card.show(parentPanel, "homePagePanel");
        
        initializeOrderPanel();
        
        setVisible(true);
    }
    
    private void initializeOrderPanel(){
        
        allPD = new JPanel(new GridLayout(0, 5));  // Set grid with 5 columns and gaps
        allPD.setBackground(new Color(255, 237, 219));
        
        scrollPane = new JScrollPane(allPD);
        scrollPane.setBackground(new Color(255, 237, 219));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
                
        
        btnOption1.setBackground(new Color(219,173,138));
        btnOption1.setUI(new BasicButtonUI());
        

        btnOption2.setBackground(new Color(219,173,138));
        btnOption2.setUI(new BasicButtonUI());
        

        btnOption3.setBackground(new Color(219,173,138));
        btnOption3.setUI(new BasicButtonUI());
       
        
        productDisplayPanel.add(scrollPane, "allPD");
        loadItemsByCategory("All Categories"); 
        
        applyBackground(productDisplayPanel, new Color(255, 237, 219));
        productDisplayPanel.revalidate();
        productDisplayPanel.repaint();
        setVisible(true);
    }
    
    private void applyBackground(JPanel panel, Color color) {
    panel.setBackground(color);
    Component[] components = panel.getComponents();
    for (Component c : components) {
        if (c instanceof JPanel) {
            applyBackground((JPanel) c, color);
        } else if (c instanceof JScrollPane) {
            JScrollPane scrollPane = (JScrollPane) c;
            scrollPane.getViewport().setBackground(color);
            scrollPane.setBackground(color);
        } else {
            c.setBackground(color);
        }
    }
}
    
    private void loadItemsByCategory(String category) {
    
    allPD.removeAll();
    allPD.revalidate();
    allPD.repaint();
    
    if (category.equals("Snacks")) {
        loadSnackItems();  // Special method for snacks
    } else {        
    Connection conn = connectToDB();
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Failed to connect to database", "Database Error", JOptionPane.ERROR_MESSAGE);
        return;  // Exit if connection failed
    }    
    
    System.out.println("Loading items for category: " + category);
    
    String query = "SELECT id, images FROM items";
    if (!category.equals("All Categories")) {
    query += " WHERE category = ?";
    }
    try (PreparedStatement pst = conn.prepareStatement(query)) {
        if (!category.equals("All Categories")) {
        pst.setString(1, category);
        }
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            String productId = rs.getString("id");
            String imagePath = rs.getString("images");
            if (!imagePath.startsWith("/")) {
                imagePath = "/" + imagePath; // Ensure path starts with a "/"
            }
            
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl == null) {
                System.err.println("Resource not found: " + imagePath);
                continue; // Skip this iteration if the image cannot be found
            }
            
            ImageIcon icon = new ImageIcon(imageUrl);
            JButton button = new JButton(icon);
            button.setPreferredSize(new Dimension(200, 200));
            button.addActionListener(e -> updateProductDetails(productId));                    
                allPD.add(button);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + ex.getMessage(), "Data Fetch Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            allPD.revalidate();
            allPD.repaint();
            scrollPane.revalidate();
            scrollPane.repaint();
        }
    }      
}

    private void updateProductDetails(String productId) {
        currentProductId = productId;
        
        try (Connection conn = connectToDB()) {
            String query = "SELECT name, category FROM items WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, productId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                txtProductName.setText(rs.getString("name"));
                txtProductCategory.setText(rs.getString("category"));
            }
            updateOptionButtons("Snacks", productId);
            updateOptionButtons("Other", productId);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateOptionButtons(String category, String productId) {
    try (Connection conn = connectToDB()) {
        String query;
        if ("Snacks".equals(category)) {
            query = "SELECT medium, large, barkada FROM pricesnacks WHERE id = ?";
        } else {
            query = "SELECT 8_oz, 16_oz, 22_oz FROM price WHERE id = ?";
        }

        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, productId);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            if ("Snacks".equals(category)) {
                updateButtonWithPrice(btnOption1, "MED", rs.getDouble("medium"));
                updateButtonWithPrice(btnOption2, "LAR", rs.getDouble("large"));
                updateButtonWithPrice(btnOption3, "BAR", rs.getDouble("barkada"));
            } else {
                updateButtonWithPrice(btnOption1, "8 OZ", rs.getDouble("8_oz"));
                updateButtonWithPrice(btnOption2, "16 OZ", rs.getDouble("16_oz"));
                updateButtonWithPrice(btnOption3, "22 OZ", rs.getDouble("22_oz"));
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
        private void updateButtonWithPrice(JButton button, String label, double price) {
            button.setText(label);  // Show both size and price on button
            button.setActionCommand(String.valueOf(price));  // Store the price as command
            button.setEnabled(price > 0);
            
            for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
    }       
            button.addActionListener(e -> {
        if (activeSizeOption != null) {
            activeSizeOption.setBackground(new Color(255, 237, 219));
            activeSizeOption.setForeground(new Color(163,120,87));
        }
        button.setBackground(new Color(219,173,138));
        button.setForeground(new Color(255, 237, 219));
        activeSizeOption = button;  // Set new active button
        txtQuantity.setText("1");  // Reset quantity to 1
        updateTotalPrice();  // Update the total price display
    });
}
    
    private void addListenersToComponents() {
        ActionListener sizeOptionListener = e -> {
            JButton clickedButton = (JButton) e.getSource();
            if (activeSizeOption != null) {
                activeSizeOption.setBackground(new Color(219,173,138));  // Reset old active button color
            }
          
            if (previousClickedButton != null) {
            previousClickedButton.setBackground(new Color(219, 173, 138));
            }
            
            clickedButton.setBackground(new Color(163,120,87));  // Highlight new active button
            clickedButton.setForeground(new Color(255, 237, 219));
            
            previousClickedButton = clickedButton;  // Remember the current clicked button
            activeSizeOption = clickedButton;  // Set new active button
            txtQuantity.setText("1");  // Reset quantity
            updateTotalPrice();  // Update price display
        };
        
        ButtonGroup sizeOptionGroup = new ButtonGroup();
        sizeOptionGroup.add(btnOption1);
        sizeOptionGroup.add(btnOption2);
        sizeOptionGroup.add(btnOption3);
        
        btnOption1.addActionListener(sizeOptionListener);
        btnOption2.addActionListener(sizeOptionListener);
        btnOption3.addActionListener(sizeOptionListener);
        
        btnAddQuan.addActionListener(e -> increaseQuantity());
        btnMinusQuan.addActionListener(e -> decreaseQuantity());
    }
    
    private void decreaseQuantity() {
        try {
        int quantity = Integer.parseInt(txtQuantity.getText());
        if (quantity > 1) {
            quantity--;
            txtQuantity.setText(Integer.toString(quantity));
            updateTotalPrice();
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid number format in quantity", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void increaseQuantity() {
        try {
        int quantity = Integer.parseInt(txtQuantity.getText());
        quantity++;
        txtQuantity.setText(Integer.toString(quantity));
        updateTotalPrice();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid number format in quantity", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void updateTotalPrice() {
        if (activeSizeOption != null && activeSizeOption.isEnabled()) {
        String priceStr = activeSizeOption.getActionCommand();
        System.out.println("Price to parse: " + priceStr);  // Check what's actually there.
        try {            
            double price = Double.parseDouble(priceStr);
            int quantity = Integer.parseInt(txtQuantity.getText());
            double totalPrice = price * quantity;
            txtTotalPrice.setText(String.format("PHP %.2f", totalPrice));
        } catch (NumberFormatException ex) {
            txtTotalPrice.setText("Unavailable");
            JOptionPane.showMessageDialog(this, "Error with input formats: " + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    } else {
        txtTotalPrice.setText("Unavailable");
    }
}
    
    private double getCurrentSelectedPrice() {
        if (activeSizeOption == null) {
            return 0.0;
        }
        String size = activeSizeOption.getText();
        try {        
        Map<String, Double> prices = fetchPricesForCurrentProduct(); // fetchPricesForCurrentProduct() should return current prices for the selected product
        return prices.getOrDefault(size, 0.0);
    } catch (Exception e) {
        e.printStackTrace();
        return 0.0;
    }
}
    private Map<String, Double> fetchPricesForCurrentProduct() {
    Map<String, Double> prices = new HashMap<>();
    if (currentProductId == null) {
        return prices; // Return an empty map if no product is selected
    }

    String query = "SELECT 8_oz, 16_oz, 22_oz FROM price WHERE id = ?";
    try (Connection conn = connectToDB();
         PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, currentProductId);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            prices.put("8 OZ", rs.getDouble("8_oz"));
            prices.put("16 OZ", rs.getDouble("16_oz"));
            prices.put("22 OZ", rs.getDouble("22_oz"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        System.err.println("SQL Exception: " + e.getMessage());
        e.printStackTrace();
    }
    return prices;
}
    
private void updateTotalPriceDisplay() {
    DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
    double total = 0.0;
    int priceColumnIndex = 4;  // Assuming the price is in the 4th column (index 3)

    for (int i = 0; i < model.getRowCount(); i++) {
        double price = 0;
        try {
            price = Double.parseDouble(model.getValueAt(i, priceColumnIndex).toString());
        } catch (NumberFormatException e) {
            // Handle the case where the price is not a valid double
            System.err.println("Error parsing price in row " + i);
        }
        total += price;
    }

    // Optionally, format the output to show two decimal places
    txtAllTotalPrice.setText(String.format("PHP %.2f", total));
}

    private ImageIcon createImageIcon(String path, String description) {
    URL imgURL = getClass().getResource(path);
    if (imgURL == null) {
        System.err.println("Resource not found: " + path);
        return null;
    }
    return new ImageIcon(imgURL, description);
}

        public Connection connectToDB() {
                String url = "jdbc:mysql://localhost:3306/cabrewdb";
                String user = "root";
                String password = ""; 
                try {
                    return DriverManager.getConnection(url, user, password);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Unable to connect to database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
            }
                
    public void setUser(String username) {
        usernameCMPBtn.setText(username); // This directly updates the button's text
        }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupOptions = new javax.swing.ButtonGroup();
        cmpNavigationPanel = new javax.swing.JPanel();
        usernameCMPBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnorderNav = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        parentPanel = new javax.swing.JPanel();
        homePagePanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnLogOutHPC = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        historyPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btn7D = new javax.swing.JButton();
        btn15D = new javax.swing.JButton();
        btn30D = new javax.swing.JButton();
        btn1Y = new javax.swing.JButton();
        btnAllDays = new javax.swing.JButton();
        label1 = new java.awt.Label();
        orderCMPPanel = new javax.swing.JPanel();
        categoryCMPPanel = new javax.swing.JPanel();
        btnAllCatCMP = new javax.swing.JButton();
        btnMilkteaClassicaCatCMP = new javax.swing.JButton();
        btnMilkTeaSupCatCMP = new javax.swing.JButton();
        btnCofChoCatCMP = new javax.swing.JButton();
        btnFrappeSeriesCatCMP = new javax.swing.JButton();
        btnFruitTeaClCatCMP = new javax.swing.JButton();
        btnFruitSodaSupCatCMP = new javax.swing.JButton();
        btnBrewedCoffeeSeriesCatCMP = new javax.swing.JButton();
        btnMilkSeriesCatCMP = new javax.swing.JButton();
        btnSnacksCatCMP = new javax.swing.JButton();
        orderDetailPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtProductName = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        txtTotalPrice = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnAddQuan = new javax.swing.JButton();
        btnMinusQuan = new javax.swing.JButton();
        btnOption1 = new javax.swing.JButton();
        btnOption2 = new javax.swing.JButton();
        btnOption3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtProductCategory = new javax.swing.JTextField();
        btnAddToOrder = new javax.swing.JButton();
        productDisplayPanel = new javax.swing.JPanel();
        allPD = new javax.swing.JPanel();
        brewedCoffeeSeriesPD = new javax.swing.JPanel();
        frappeSeriesPD = new javax.swing.JPanel();
        fruitTeaPD = new javax.swing.JPanel();
        fruitSodaPD = new javax.swing.JPanel();
        milkSeriesPD = new javax.swing.JPanel();
        milkteaClassicaPD = new javax.swing.JPanel();
        milkTeaSupremaPD = new javax.swing.JPanel();
        snacksPD = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        txtAllTotalPrice = new javax.swing.JTextField();
        btnConfirmOrder = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1540, 850));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmpNavigationPanel.setBackground(new java.awt.Color(219, 173, 138));
        cmpNavigationPanel.setPreferredSize(new java.awt.Dimension(200, 1030));

        usernameCMPBtn.setBackground(new java.awt.Color(219, 173, 138));
        usernameCMPBtn.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 10)); // NOI18N
        usernameCMPBtn.setForeground(new java.awt.Color(255, 237, 219));
        usernameCMPBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setBackground(new java.awt.Color(219, 173, 138));
        jButton1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 237, 219));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Icons/1.png"))); // NOI18N
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnorderNav.setBackground(new java.awt.Color(219, 173, 138));
        btnorderNav.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        btnorderNav.setForeground(new java.awt.Color(255, 237, 219));
        btnorderNav.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Icons/2.png"))); // NOI18N
        btnorderNav.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnorderNav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnorderNavActionPerformed(evt);
            }
        });

        btnHistory.setBackground(new java.awt.Color(219, 173, 138));
        btnHistory.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        btnHistory.setForeground(new java.awt.Color(255, 237, 219));
        btnHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Icons/3.png"))); // NOI18N
        btnHistory.setToolTipText("");
        btnHistory.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cmpNavigationPanelLayout = new javax.swing.GroupLayout(cmpNavigationPanel);
        cmpNavigationPanel.setLayout(cmpNavigationPanelLayout);
        cmpNavigationPanelLayout.setHorizontalGroup(
            cmpNavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cmpNavigationPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(cmpNavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usernameCMPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cmpNavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                        .addComponent(btnorderNav, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        cmpNavigationPanelLayout.setVerticalGroup(
            cmpNavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cmpNavigationPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(btnorderNav, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(261, 261, 261)
                .addComponent(usernameCMPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        getContentPane().add(cmpNavigationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 150, 830));

        parentPanel.setBackground(new java.awt.Color(255, 237, 219));
        parentPanel.setPreferredSize(new java.awt.Dimension(1800, 980));
        parentPanel.setLayout(new java.awt.CardLayout());

        homePagePanel.setBackground(new java.awt.Color(255, 237, 219));
        homePagePanel.setPreferredSize(new java.awt.Dimension(1530, 820));
        homePagePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Microsoft YaHei", 1, 58)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(163, 120, 87));
        jLabel7.setText("Welcome to CaBrew!");
        homePagePanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Untitled design (4)/3.png"))); // NOI18N
        homePagePanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, -1, -1));

        btnLogOutHPC.setBackground(new java.awt.Color(163, 120, 87));
        btnLogOutHPC.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
        btnLogOutHPC.setForeground(new java.awt.Color(255, 237, 219));
        btnLogOutHPC.setText("Logout");
        btnLogOutHPC.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLogOutHPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutHPCActionPerformed(evt);
            }
        });
        homePagePanel.add(btnLogOutHPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 750, 176, 52));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Untitled design (4)/2.png"))); // NOI18N
        homePagePanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 150, -1, -1));

        jLabel8.setBackground(new java.awt.Color(219, 173, 138));
        jLabel8.setFont(new java.awt.Font("Microsoft YaHei", 3, 45)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(219, 173, 138));
        jLabel8.setText("steadfast companion.");
        homePagePanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 850, 70));

        jLabel9.setBackground(new java.awt.Color(219, 173, 138));
        jLabel9.setFont(new java.awt.Font("Microsoft YaHei", 1, 45)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(219, 173, 138));
        jLabel9.setText("Transforming chaos");
        homePagePanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 600, 70));

        jLabel17.setBackground(new java.awt.Color(219, 173, 138));
        jLabel17.setFont(new java.awt.Font("Microsoft YaHei", 3, 45)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(219, 173, 138));
        jLabel17.setText("into brewtiful order,");
        homePagePanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, 510, 70));

        jLabel18.setBackground(new java.awt.Color(219, 173, 138));
        jLabel18.setFont(new java.awt.Font("Microsoft YaHei", 1, 45)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(219, 173, 138));
        jLabel18.setText("CaBrew is your");
        homePagePanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 430, 850, 70));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Untitled design (4)/1.png"))); // NOI18N
        homePagePanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 480, -1));

        jLabel20.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jLabel20.setText("In 2024, CaBrew emerged from the collaborative efforts of MS3, a dedicated team of students fulfilling their final requirements for ITEC 103 and ITEP");
        homePagePanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 570, -1, -1));

        jLabel21.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jLabel21.setText(" 101 at Laguna State Polytechnic University, Sta. Cruz Campus. Crafted as a response to the common challenges faced by businesses grappling with\n");
        homePagePanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 610, -1, -1));

        jLabel22.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jLabel22.setText("disorganized and unsustainable order management, CaBrew stands as a cashiering system designed to streamline operations. With CaBrew, businesses\n");
        homePagePanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 650, -1, -1));

        jLabel23.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        jLabel23.setText("can effortlessly transform chaos into a symphony of efficient and organized orders.");
        homePagePanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 690, -1, -1));

        parentPanel.add(homePagePanel, "card3");

        historyPanel.setBackground(new java.awt.Color(255, 237, 219));
        historyPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setBackground(new java.awt.Color(219, 173, 138));
        jLabel12.setFont(new java.awt.Font("Microsoft YaHei", 1, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(163, 120, 87));
        jLabel12.setText("HISTORY");
        historyPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        historyTable.setBackground(new java.awt.Color(255, 237, 219));
        historyTable.setForeground(new java.awt.Color(163, 120, 87));
        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date and Time", "Cashier", "Product Name", "Product Category", "Product Size", "Product Quantity", "Product Price", "Total Price"
            }
        ));
        jScrollPane6.setViewportView(historyTable);

        historyPanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 1330, 640));

        jPanel4.setBackground(new java.awt.Color(255, 237, 219));

        jLabel13.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(163, 120, 87));
        jLabel13.setText("Filter by:");
        jPanel4.add(jLabel13);

        btn7D.setBackground(new java.awt.Color(163, 120, 87));
        btn7D.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        btn7D.setForeground(new java.awt.Color(255, 237, 219));
        btn7D.setText("7 days");
        btn7D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7DActionPerformed(evt);
            }
        });
        jPanel4.add(btn7D);

        btn15D.setBackground(new java.awt.Color(163, 120, 87));
        btn15D.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        btn15D.setForeground(new java.awt.Color(255, 237, 219));
        btn15D.setText("15 days");
        btn15D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn15DActionPerformed(evt);
            }
        });
        jPanel4.add(btn15D);

        btn30D.setBackground(new java.awt.Color(163, 120, 87));
        btn30D.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        btn30D.setForeground(new java.awt.Color(255, 237, 219));
        btn30D.setText("30 days");
        btn30D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn30DActionPerformed(evt);
            }
        });
        jPanel4.add(btn30D);

        btn1Y.setBackground(new java.awt.Color(163, 120, 87));
        btn1Y.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        btn1Y.setForeground(new java.awt.Color(255, 237, 219));
        btn1Y.setText("1 year");
        btn1Y.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1YActionPerformed(evt);
            }
        });
        jPanel4.add(btn1Y);

        btnAllDays.setBackground(new java.awt.Color(163, 120, 87));
        btnAllDays.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        btnAllDays.setForeground(new java.awt.Color(255, 237, 219));
        btnAllDays.setText("All");
        btnAllDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllDaysActionPerformed(evt);
            }
        });
        jPanel4.add(btnAllDays);

        historyPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 490, 50));

        label1.setText("label1");
        historyPanel.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 440, -1, -1));

        parentPanel.add(historyPanel, "card4");

        orderCMPPanel.setBackground(new java.awt.Color(255, 237, 219));
        orderCMPPanel.setPreferredSize(new java.awt.Dimension(1500, 1050));
        orderCMPPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        categoryCMPPanel.setBackground(new java.awt.Color(255, 237, 219));

        btnAllCatCMP.setBackground(new java.awt.Color(163, 120, 87));
        btnAllCatCMP.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        btnAllCatCMP.setForeground(new java.awt.Color(255, 237, 219));
        btnAllCatCMP.setText("All");
        btnAllCatCMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllCatCMPActionPerformed(evt);
            }
        });
        categoryCMPPanel.add(btnAllCatCMP);

        btnMilkteaClassicaCatCMP.setBackground(new java.awt.Color(163, 120, 87));
        btnMilkteaClassicaCatCMP.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        btnMilkteaClassicaCatCMP.setForeground(new java.awt.Color(255, 237, 219));
        btnMilkteaClassicaCatCMP.setText("Milktea Classica");
        btnMilkteaClassicaCatCMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMilkteaClassicaCatCMPActionPerformed(evt);
            }
        });
        categoryCMPPanel.add(btnMilkteaClassicaCatCMP);

        btnMilkTeaSupCatCMP.setBackground(new java.awt.Color(163, 120, 87));
        btnMilkTeaSupCatCMP.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        btnMilkTeaSupCatCMP.setForeground(new java.awt.Color(255, 237, 219));
        btnMilkTeaSupCatCMP.setText("Milktea Suprema");
        btnMilkTeaSupCatCMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMilkTeaSupCatCMPActionPerformed(evt);
            }
        });
        categoryCMPPanel.add(btnMilkTeaSupCatCMP);

        btnCofChoCatCMP.setBackground(new java.awt.Color(163, 120, 87));
        btnCofChoCatCMP.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        btnCofChoCatCMP.setForeground(new java.awt.Color(255, 237, 219));
        btnCofChoCatCMP.setText("Coffee/Choco");
        btnCofChoCatCMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCofChoCatCMPActionPerformed(evt);
            }
        });
        categoryCMPPanel.add(btnCofChoCatCMP);

        btnFrappeSeriesCatCMP.setBackground(new java.awt.Color(163, 120, 87));
        btnFrappeSeriesCatCMP.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        btnFrappeSeriesCatCMP.setForeground(new java.awt.Color(255, 237, 219));
        btnFrappeSeriesCatCMP.setText("Frappe Series");
        btnFrappeSeriesCatCMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrappeSeriesCatCMPActionPerformed(evt);
            }
        });
        categoryCMPPanel.add(btnFrappeSeriesCatCMP);

        btnFruitTeaClCatCMP.setBackground(new java.awt.Color(163, 120, 87));
        btnFruitTeaClCatCMP.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        btnFruitTeaClCatCMP.setForeground(new java.awt.Color(255, 237, 219));
        btnFruitTeaClCatCMP.setText("Fruit Tea Classica");
        btnFruitTeaClCatCMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFruitTeaClCatCMPActionPerformed(evt);
            }
        });
        categoryCMPPanel.add(btnFruitTeaClCatCMP);

        btnFruitSodaSupCatCMP.setBackground(new java.awt.Color(163, 120, 87));
        btnFruitSodaSupCatCMP.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        btnFruitSodaSupCatCMP.setForeground(new java.awt.Color(255, 237, 219));
        btnFruitSodaSupCatCMP.setText("Fruit Soda Suprema");
        btnFruitSodaSupCatCMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFruitSodaSupCatCMPActionPerformed(evt);
            }
        });
        categoryCMPPanel.add(btnFruitSodaSupCatCMP);

        btnBrewedCoffeeSeriesCatCMP.setBackground(new java.awt.Color(163, 120, 87));
        btnBrewedCoffeeSeriesCatCMP.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        btnBrewedCoffeeSeriesCatCMP.setForeground(new java.awt.Color(255, 237, 219));
        btnBrewedCoffeeSeriesCatCMP.setText("Brewed Coffee Series");
        btnBrewedCoffeeSeriesCatCMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrewedCoffeeSeriesCatCMPActionPerformed(evt);
            }
        });
        categoryCMPPanel.add(btnBrewedCoffeeSeriesCatCMP);

        btnMilkSeriesCatCMP.setBackground(new java.awt.Color(163, 120, 87));
        btnMilkSeriesCatCMP.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        btnMilkSeriesCatCMP.setForeground(new java.awt.Color(255, 237, 219));
        btnMilkSeriesCatCMP.setText("Milk Series");
        btnMilkSeriesCatCMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMilkSeriesCatCMPActionPerformed(evt);
            }
        });
        categoryCMPPanel.add(btnMilkSeriesCatCMP);

        btnSnacksCatCMP.setBackground(new java.awt.Color(163, 120, 87));
        btnSnacksCatCMP.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        btnSnacksCatCMP.setForeground(new java.awt.Color(255, 237, 219));
        btnSnacksCatCMP.setText("Snacks");
        btnSnacksCatCMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSnacksCatCMPActionPerformed(evt);
            }
        });
        categoryCMPPanel.add(btnSnacksCatCMP);

        orderCMPPanel.add(categoryCMPPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1110, 40));

        orderDetailPanel.setBackground(new java.awt.Color(219, 173, 138));
        orderDetailPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 237, 219));
        jPanel2.setForeground(new java.awt.Color(255, 237, 219));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtProductName.setEditable(false);
        txtProductName.setBackground(new java.awt.Color(219, 173, 138));
        txtProductName.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        txtProductName.setToolTipText("");
        jPanel2.add(txtProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 215, 35));

        txtQuantity.setBackground(new java.awt.Color(219, 173, 138));
        txtQuantity.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        txtQuantity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 100, 35));

        txtTotalPrice.setEditable(false);
        txtTotalPrice.setBackground(new java.awt.Color(219, 173, 138));
        txtTotalPrice.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jPanel2.add(txtTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 215, 35));

        jLabel1.setBackground(new java.awt.Color(219, 173, 138));
        jLabel1.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(163, 120, 87));
        jLabel1.setText("PRODUCT DETAILS");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        jLabel2.setBackground(new java.awt.Color(219, 173, 138));
        jLabel2.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(163, 120, 87));
        jLabel2.setText("PRODUCT NAME");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel3.setBackground(new java.awt.Color(219, 173, 138));
        jLabel3.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(163, 120, 87));
        jLabel3.setText("OPTIONS");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jLabel4.setBackground(new java.awt.Color(219, 173, 138));
        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(163, 120, 87));
        jLabel4.setText("QUANTITY");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jLabel5.setBackground(new java.awt.Color(219, 173, 138));
        jLabel5.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(163, 120, 87));
        jLabel5.setText("TOTAL PRICE");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, -1));

        btnAddQuan.setBackground(new java.awt.Color(219, 173, 138));
        btnAddQuan.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        btnAddQuan.setForeground(new java.awt.Color(163, 120, 87));
        btnAddQuan.setText("+");
        btnAddQuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddQuanActionPerformed(evt);
            }
        });
        jPanel2.add(btnAddQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 45, 35));

        btnMinusQuan.setBackground(new java.awt.Color(219, 173, 138));
        btnMinusQuan.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        btnMinusQuan.setForeground(new java.awt.Color(163, 120, 87));
        btnMinusQuan.setText("-");
        btnMinusQuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusQuanActionPerformed(evt);
            }
        });
        jPanel2.add(btnMinusQuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 45, 35));

        btnOption1.setBackground(new java.awt.Color(219, 173, 138));
        btnOption1.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        btnOption1.setText("8 OZ");
        btnOption1.setBorder(null);
        jPanel2.add(btnOption1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 65, 35));

        btnOption2.setBackground(new java.awt.Color(219, 173, 138));
        btnOption2.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        btnOption2.setText("16 OZ");
        btnOption2.setBorder(null);
        jPanel2.add(btnOption2, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 260, 65, 35));

        btnOption3.setBackground(new java.awt.Color(219, 173, 138));
        btnOption3.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        btnOption3.setText("22 OZ");
        btnOption3.setBorder(null);
        btnOption3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOption3ActionPerformed(evt);
            }
        });
        jPanel2.add(btnOption3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 65, 35));

        jLabel11.setBackground(new java.awt.Color(219, 173, 138));
        jLabel11.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(163, 120, 87));
        jLabel11.setText("CATEGORY");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        txtProductCategory.setEditable(false);
        txtProductCategory.setBackground(new java.awt.Color(219, 173, 138));
        txtProductCategory.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jPanel2.add(txtProductCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 215, 35));

        orderDetailPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 240, 495));

        btnAddToOrder.setBackground(new java.awt.Color(163, 120, 87));
        btnAddToOrder.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        btnAddToOrder.setForeground(new java.awt.Color(255, 237, 219));
        btnAddToOrder.setText("Add to Order");
        btnAddToOrder.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAddToOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToOrderActionPerformed(evt);
            }
        });
        orderDetailPanel.add(btnAddToOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 540, 150, 50));

        orderCMPPanel.add(orderDetailPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 0, 270, 610));

        productDisplayPanel.setBackground(new java.awt.Color(255, 237, 219));
        productDisplayPanel.setLayout(new java.awt.CardLayout());

        allPD.setBackground(new java.awt.Color(255, 237, 219));
        allPD.setLayout(new java.awt.GridLayout(1, 0));
        productDisplayPanel.add(allPD, "card2");

        javax.swing.GroupLayout brewedCoffeeSeriesPDLayout = new javax.swing.GroupLayout(brewedCoffeeSeriesPD);
        brewedCoffeeSeriesPD.setLayout(brewedCoffeeSeriesPDLayout);
        brewedCoffeeSeriesPDLayout.setHorizontalGroup(
            brewedCoffeeSeriesPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1110, Short.MAX_VALUE)
        );
        brewedCoffeeSeriesPDLayout.setVerticalGroup(
            brewedCoffeeSeriesPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        productDisplayPanel.add(brewedCoffeeSeriesPD, "card3");

        javax.swing.GroupLayout frappeSeriesPDLayout = new javax.swing.GroupLayout(frappeSeriesPD);
        frappeSeriesPD.setLayout(frappeSeriesPDLayout);
        frappeSeriesPDLayout.setHorizontalGroup(
            frappeSeriesPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1110, Short.MAX_VALUE)
        );
        frappeSeriesPDLayout.setVerticalGroup(
            frappeSeriesPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        productDisplayPanel.add(frappeSeriesPD, "card4");

        javax.swing.GroupLayout fruitTeaPDLayout = new javax.swing.GroupLayout(fruitTeaPD);
        fruitTeaPD.setLayout(fruitTeaPDLayout);
        fruitTeaPDLayout.setHorizontalGroup(
            fruitTeaPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1110, Short.MAX_VALUE)
        );
        fruitTeaPDLayout.setVerticalGroup(
            fruitTeaPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        productDisplayPanel.add(fruitTeaPD, "card5");

        javax.swing.GroupLayout fruitSodaPDLayout = new javax.swing.GroupLayout(fruitSodaPD);
        fruitSodaPD.setLayout(fruitSodaPDLayout);
        fruitSodaPDLayout.setHorizontalGroup(
            fruitSodaPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1110, Short.MAX_VALUE)
        );
        fruitSodaPDLayout.setVerticalGroup(
            fruitSodaPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        productDisplayPanel.add(fruitSodaPD, "card6");

        javax.swing.GroupLayout milkSeriesPDLayout = new javax.swing.GroupLayout(milkSeriesPD);
        milkSeriesPD.setLayout(milkSeriesPDLayout);
        milkSeriesPDLayout.setHorizontalGroup(
            milkSeriesPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1110, Short.MAX_VALUE)
        );
        milkSeriesPDLayout.setVerticalGroup(
            milkSeriesPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        productDisplayPanel.add(milkSeriesPD, "card7");

        milkteaClassicaPD.setBackground(new java.awt.Color(255, 237, 219));

        javax.swing.GroupLayout milkteaClassicaPDLayout = new javax.swing.GroupLayout(milkteaClassicaPD);
        milkteaClassicaPD.setLayout(milkteaClassicaPDLayout);
        milkteaClassicaPDLayout.setHorizontalGroup(
            milkteaClassicaPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1110, Short.MAX_VALUE)
        );
        milkteaClassicaPDLayout.setVerticalGroup(
            milkteaClassicaPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        productDisplayPanel.add(milkteaClassicaPD, "card8");

        javax.swing.GroupLayout milkTeaSupremaPDLayout = new javax.swing.GroupLayout(milkTeaSupremaPD);
        milkTeaSupremaPD.setLayout(milkTeaSupremaPDLayout);
        milkTeaSupremaPDLayout.setHorizontalGroup(
            milkTeaSupremaPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1110, Short.MAX_VALUE)
        );
        milkTeaSupremaPDLayout.setVerticalGroup(
            milkTeaSupremaPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        productDisplayPanel.add(milkTeaSupremaPD, "card9");

        snacksPD.setBackground(new java.awt.Color(255, 237, 219));

        javax.swing.GroupLayout snacksPDLayout = new javax.swing.GroupLayout(snacksPD);
        snacksPD.setLayout(snacksPDLayout);
        snacksPDLayout.setHorizontalGroup(
            snacksPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1110, Short.MAX_VALUE)
        );
        snacksPDLayout.setVerticalGroup(
            snacksPDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        productDisplayPanel.add(snacksPD, "card10");

        orderCMPPanel.add(productDisplayPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1110, 490));

        jPanel3.setBackground(new java.awt.Color(219, 173, 138));

        jScrollPane1.setBackground(new java.awt.Color(255, 237, 219));
        jScrollPane1.setBorder(null);

        orderTable.setBackground(new java.awt.Color(219, 173, 138));
        orderTable.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Category", "Size", "Quantity", "Price"
            }
        ));
        orderTable.setGridColor(new java.awt.Color(255, 237, 219));
        orderTable.setSelectionBackground(new java.awt.Color(219, 173, 138));
        jScrollPane1.setViewportView(orderTable);

        txtAllTotalPrice.setBackground(new java.awt.Color(255, 237, 219));
        txtAllTotalPrice.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        txtAllTotalPrice.setForeground(new java.awt.Color(163, 120, 87));

        btnConfirmOrder.setBackground(new java.awt.Color(163, 120, 87));
        btnConfirmOrder.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        btnConfirmOrder.setForeground(new java.awt.Color(255, 237, 219));
        btnConfirmOrder.setText("Confirm Order");
        btnConfirmOrder.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnConfirmOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmOrderActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 237, 219));
        jLabel6.setText("Order Total:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(28, 28, 28)
                .addComponent(txtAllTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfirmOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAllTotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        orderCMPPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 1380, 210));

        jLabel10.setBackground(new java.awt.Color(219, 173, 138));
        jLabel10.setFont(new java.awt.Font("Microsoft YaHei", 1, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(163, 120, 87));
        jLabel10.setText("ORDER");
        orderCMPPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        parentPanel.add(orderCMPPanel, "card2");

        getContentPane().add(parentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void btnAllCatCMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllCatCMPActionPerformed
        CardLayout cl = (CardLayout) (productDisplayPanel.getLayout());
        cl.show(productDisplayPanel, "allpd");
        loadItemsByCategory("All Categories");
    }//GEN-LAST:event_btnAllCatCMPActionPerformed

    private void btnMilkteaClassicaCatCMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMilkteaClassicaCatCMPActionPerformed
    CardLayout cl = (CardLayout) (productDisplayPanel.getLayout());
    cl.show(productDisplayPanel, "allPD");
    loadItemsByCategory("Milktea Classica");
    }//GEN-LAST:event_btnMilkteaClassicaCatCMPActionPerformed

    private void btnMilkTeaSupCatCMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMilkTeaSupCatCMPActionPerformed
    CardLayout cl = (CardLayout) (productDisplayPanel.getLayout());
    cl.show(productDisplayPanel, "allPD");
    loadItemsByCategory("Milktea Suprema");
    }//GEN-LAST:event_btnMilkTeaSupCatCMPActionPerformed

    private void btnCofChoCatCMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCofChoCatCMPActionPerformed
    CardLayout cl = (CardLayout) (productDisplayPanel.getLayout());
    cl.show(productDisplayPanel, "allPD");
    loadItemsByCategory("Coffee/Choco");
    }//GEN-LAST:event_btnCofChoCatCMPActionPerformed

    private void btnFrappeSeriesCatCMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFrappeSeriesCatCMPActionPerformed
    CardLayout cl = (CardLayout) (productDisplayPanel.getLayout());
    cl.show(productDisplayPanel, "allPD");
    loadItemsByCategory("Frappe");
    }//GEN-LAST:event_btnFrappeSeriesCatCMPActionPerformed

    private void btnFruitTeaClCatCMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFruitTeaClCatCMPActionPerformed
    CardLayout cl = (CardLayout) (productDisplayPanel.getLayout());
    cl.show(productDisplayPanel, "allPD");
    loadItemsByCategory("Fruit Tea Classica");
    }//GEN-LAST:event_btnFruitTeaClCatCMPActionPerformed

    private void btnFruitSodaSupCatCMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFruitSodaSupCatCMPActionPerformed
    CardLayout cl = (CardLayout) (productDisplayPanel.getLayout());
    cl.show(productDisplayPanel, "allPD");
    loadItemsByCategory("Fruit Soda Suprema");
    }//GEN-LAST:event_btnFruitSodaSupCatCMPActionPerformed

    private void btnBrewedCoffeeSeriesCatCMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrewedCoffeeSeriesCatCMPActionPerformed
    CardLayout cl = (CardLayout) (productDisplayPanel.getLayout());
    cl.show(productDisplayPanel, "allPD");
    loadItemsByCategory("Brewed Coffee Series");
    }//GEN-LAST:event_btnBrewedCoffeeSeriesCatCMPActionPerformed

    private void btnMinusQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusQuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMinusQuanActionPerformed
    
    private void initializeTable() {
    tableModel = new DefaultTableModel(
        new Object [][] {}, // Initially empty data
        new String [] { "Product Name", "Category", "Size", "Quantity", "Price" } // Column names
    );
    orderTable.setModel(tableModel); // Setting the model to the table
}

    private void btnAddToOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToOrderActionPerformed
         if (activeSizeOption == null) {
        JOptionPane.showMessageDialog(null, "Please select a size first.");
        return;
    }
        int confirmed = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to add this item to your order?", 
                "Confirm Order", JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
                // Fetching product details
                String productName = txtProductName.getText();
                String productCategory = txtProductCategory.getText();
                String size = getSelectedSize();
                
                if (activeSizeOption == null || !activeSizeOption.isEnabled()) {
                    JOptionPane.showMessageDialog(null, "The size that you selected is currently unavailable. Please select another size.", 
                    "Size Unavailable", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                 
                String quantity = txtQuantity.getText();
                String price = txtTotalPrice.getText().startsWith("PHP ") ? txtTotalPrice.getText().substring(4) : txtTotalPrice.getText(); // Strip "PHP " if present
                
                if (productName.isEmpty() || size.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields before adding to the order.", 
                                          "Incomplete Details", JOptionPane.ERROR_MESSAGE);
        } else {            
            tableModel.addRow(new Object[]{productName, productCategory, size, quantity, price});
            updateTotalPriceDisplay();
        }
    }
}
    
    private void customizeTableLook() {
    // Setting the default renderer for the table cells
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Centers text
    
    // Applying the renderer to each column
    for (int columnIndex = 0; columnIndex < orderTable.getColumnCount(); columnIndex++) {
        orderTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
    }

    // Setting the table row height and font
    orderTable.setRowHeight(30); // Adjust row height as needed
    orderTable.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18)); // Set font and size

    // Setting the background color of the table and scroll pane
    orderTable.setFillsViewportHeight(true);
    orderTable.setBackground(new Color(219, 173, 138)); // Adjust the color
    jScrollPane1.getViewport().setBackground(new Color(219, 173, 138));
    
    // Ensure table changes are visible
    orderTable.revalidate();
    orderTable.repaint();
}

    
    private String getSelectedSize() {
    return activeSizeOption != null ? activeSizeOption.getText() : null;   
    }//GEN-LAST:event_btnAddToOrderActionPerformed

    private void btnorderNavActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnorderNavActionPerformed
        CardLayout card = (CardLayout) parentPanel.getLayout();
        card.show(parentPanel, "orderCMPPanel");  // Ensure the name matches the one used when adding the panel
        initializeOrderPanel();        
    }//GEN-LAST:event_btnorderNavActionPerformed

    private void btnMilkSeriesCatCMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMilkSeriesCatCMPActionPerformed
    CardLayout cl = (CardLayout) (productDisplayPanel.getLayout());
    cl.show(productDisplayPanel, "allPD");
    loadItemsByCategory("Milk Series");
    }//GEN-LAST:event_btnMilkSeriesCatCMPActionPerformed

    private void btnSnacksCatCMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSnacksCatCMPActionPerformed
        loadItemsByCategory("Snacks"); // Assuming this method can now handle snacks
        updateSizeOptionButtons("Medium", "Large", "Barkada");
    }//GEN-LAST:event_btnSnacksCatCMPActionPerformed

    private Connection getConnection() throws SQLException {    
    
        String SUrl, SUser, SPass;
        SUrl = "jdbc:mysql://localhost:3306/cabrewDB";        
        SUser = "root";
        SPass = "";
    return DriverManager.getConnection(SUrl, SUser, SPass);
}

    private void btnConfirmOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmOrderActionPerformed
    try {
        int confirmed = JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to add this item to your order?", 
            "Confirm Order", JOptionPane.YES_NO_OPTION);

        if (confirmed != JOptionPane.YES_OPTION) {
            // User chose NO, just exit the method
            return;
        }
        
        int rowCount = orderTable.getRowCount();
        int colCount = orderTable.getColumnCount();

        
        System.out.println("Row count: " + rowCount);
        System.out.println("Column count: " + colCount);
        
        int row = 0; 
        int col = 3; 

        if (row < rowCount && col < colCount) {
            Object value = orderTable.getValueAt(row, col);
            System.out.println("Value at row " + row + ", column " + col + ": " + value);
        } else {
            System.out.println("Invalid row or column index");
        }
        
        Connection con = connectToDB(); 
        if (con == null) {
            JOptionPane.showMessageDialog(null, "Failed to establish a connection with the database.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String dateAndTime = java.time.LocalDateTime.now().toString();
        String totalOrderPrice = txtAllTotalPrice.getText().replaceAll("[^0-9.]", "");
        
        try {
            con.setAutoCommit(false); 
            
            int orderId = generateOrderId();
            
        for (int i = 0; i < rowCount; i++) {
                String productName = orderTable.getValueAt(i, 0).toString();
                String productCategory = orderTable.getValueAt(i, 1).toString();
                String productSize = orderTable.getValueAt(i, 2).toString();
                String productQuantity = orderTable.getValueAt(i, 3).toString(); 
                String productPrice = orderTable.getValueAt(i, 4).toString(); 
                
                String queryInsert = "INSERT INTO history(dateAndTime, productName, productCategory, productTotalPrice, cashier, productSize, price, productQuantity, order_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstInsert = con.prepareStatement(queryInsert)) {
                    pstInsert.setString(1, dateAndTime);
                    pstInsert.setString(2, productName);
                    pstInsert.setString(3, productCategory);
                    pstInsert.setString(4, totalOrderPrice);               
                    pstInsert.setString(5, usernameCMPBtn.getText());
                    pstInsert.setString(6, productSize);                
                    pstInsert.setString(7, productPrice);
                    pstInsert.setString(8, productQuantity);
                    pstInsert.setInt(9, orderId);
                    
                    pstInsert.executeUpdate();
                }
            }
            con.commit(); // Commit transaction
            JOptionPane.showMessageDialog(new JFrame(), "All orders confirmed!");
            clearOrderTable(); 
            txtAllTotalPrice.setText("0.00"); 
        } catch (SQLException e) {
            try {
                con.rollback(); 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(new JFrame(), "Error confirming order: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true); 
                    con.close(); 
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(new JFrame(), "Error occurred during order confirmation. Try again later.", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnConfirmOrderActionPerformed

private int generateOrderId() {    
    return (int) (Math.random() * 1000000);
}

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        CardLayout card = (CardLayout) parentPanel.getLayout();
        card.show(parentPanel, "historyPanel"); 
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CardLayout card = (CardLayout) parentPanel.getLayout();
        card.show(parentPanel, "homePagePanel"); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn7DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7DActionPerformed
        fetchData("7 days");
    }//GEN-LAST:event_btn7DActionPerformed

    private void btnAddQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddQuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddQuanActionPerformed

    private void btnLogOutHPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutHPCActionPerformed
        this.setVisible(false);
        this.dispose();
        
        LoginRegister loginRegisterFrame = new LoginRegister();
        loginRegisterFrame.setVisible(true);
        loginRegisterFrame.pack();
        loginRegisterFrame.setLocationRelativeTo(null);     
    }//GEN-LAST:event_btnLogOutHPCActionPerformed

    private void btnOption3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOption3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOption3ActionPerformed

    private void btn15DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn15DActionPerformed
        fetchData("15 days");
    }//GEN-LAST:event_btn15DActionPerformed

    private void btn30DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn30DActionPerformed
        fetchData("30 days");
    }//GEN-LAST:event_btn30DActionPerformed

    private void btn1YActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1YActionPerformed
        fetchData("1 year");
    }//GEN-LAST:event_btn1YActionPerformed

    private void btnAllDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllDaysActionPerformed
        fetchData("All");
    }//GEN-LAST:event_btnAllDaysActionPerformed

    
    private void clearOrderTable() {
    DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
    model.setRowCount(0);  // Clears the table
}

    private void loadSnackItems() {
    Connection conn = connectToDB();
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Failed to connect to database", "Database Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String query = "SELECT id, images FROM snacks";
    try (PreparedStatement pst = conn.prepareStatement(query)) {
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            String productId = rs.getString("id");
            String imagePath = rs.getString("images");
            if (!imagePath.startsWith("/")) {
                imagePath = "/" + imagePath; // Ensure path starts with a "/"
            }
            
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl == null) {
                System.err.println("Resource not found: " + imagePath);
                continue; // Skip this iteration if the image cannot be found
            }
            
            ImageIcon icon = new ImageIcon(imageUrl);
            JButton button = new JButton(icon);
            button.setPreferredSize(new Dimension(200, 200));
            button.addActionListener(e -> updateSnackProductDetails(productId));                    
                allPD.add(button);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + ex.getMessage(), "Data Fetch Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            allPD.revalidate();
            allPD.repaint();
        }
    }          
    private void updateSnackProductDetails(String productId) {
    try (Connection conn = connectToDB()) {
        String snackQuery = "SELECT name, category FROM snacks WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(snackQuery);
        pst.setString(1, productId);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            txtProductName.setText(rs.getString("name"));
            txtProductCategory.setText(rs.getString("category"));
        }
        
        updateSnackSizeOptions(productId);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void updateSnackSizeOptions(String productId) {
    String query = "SELECT medium, large, barkada FROM pricesnacks WHERE id = ?";
    try (Connection conn = connectToDB();
         PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, productId);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            updateButtonWithPrice(btnOption1, "Medium", rs.getDouble("medium"));
            updateButtonWithPrice(btnOption2, "Large", rs.getDouble("large"));
            updateButtonWithPrice(btnOption3, "Barkada", rs.getDouble("barkada"));
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}
    private void updateSizeOptionButtons(String first, String second, String third) {
    btnOption1.setText(first);
    btnOption2.setText(second);
    btnOption3.setText(third);
}
    
private void fetchData(String filter) {
    String query = "SELECT order_id, dateAndTime, cashier, productName, productCategory, productSize, " +
           "productQuantity, price, productTotalPrice " +
           "FROM history ";

    String lastDateTime = null;
    String lastCashier = null;

    // Add WHERE clause based on selected filter
    switch (filter) {
        case "7 days":
            query += "WHERE dateAndTime >= DATE_SUB(NOW(), INTERVAL 7 DAY)";
            break;
        case "15 days":
            query += "WHERE dateAndTime >= DATE_SUB(NOW(), INTERVAL 15 DAY)";
            break;
        case "30 days":
            query += "WHERE dateAndTime >= DATE_SUB(NOW(), INTERVAL 30 DAY)";
            break;
        case "1 year":
            query += "WHERE dateAndTime >= DATE_SUB(NOW(), INTERVAL 1 YEAR)";
            break;
        // For "All" filter, no WHERE clause is added
        default:
            break;
    }

    if (!filter.equals("All")) {
        query += " ORDER BY dateAndTime DESC";
    }
    
    try {
        Connection conn = connectToDB();
        PreparedStatement pst = conn.prepareStatement(query); // Add semicolon here
        ResultSet rs = pst.executeQuery();

        if (!rs.isBeforeFirst()) {
            // No data found, return or display a message
            JOptionPane.showMessageDialog(null, "No data found for the selected filter.", "Empty Result", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // Create a new table model to hold the data
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };
        tableModel.addColumn("Order ID");
        tableModel.addColumn("Date and Time");
        tableModel.addColumn("Cashier");
        tableModel.addColumn("Product Name");
        tableModel.addColumn("Product Category");
        tableModel.addColumn("Product Size");
        tableModel.addColumn("Product Quantity");
        tableModel.addColumn("Price");
        tableModel.addColumn("Total Price");

        int currentOrderId = -1;
        int startRow = 0; // Variable to keep track of the start row for each order
        int rowCount = 0; // Variable to keep track of the row count for each order

       while (rs.next()) {
            int orderId = rs.getInt("order_id");

            if (orderId != currentOrderId) {
                currentOrderId = orderId;
                startRow = rowCount; // Update startRow for the new order
                rowCount = 0; // Reset rowCount
            }

            // Add a row for each product in the order
            tableModel.addRow(new Object[]{rs.getString("order_id"), rs.getString("dateAndTime"),
                    rs.getString("cashier"), rs.getString("productName"),
                    rs.getString("productCategory"), rs.getString("productSize"),
                    rs.getInt("productQuantity"), rs.getDouble("price"), rs.getDouble("productTotalPrice")});
            rowCount++;
        }

        // Set the table model to the historyTable
        historyTable.setModel(tableModel);
        historyTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer());

        for (int i = 0; i < historyTable.getColumnCount(); i++) {
            mergeCells(0, historyTable.getRowCount(), i, historyTable);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error fetching data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void mergeCells(int startRow, int rowCount, int column, JTable table) {
    System.out.println("startRow: " + startRow + ", rowCount: " + rowCount + ", column: " + column);
    try {
        // Set the initial height to the height of the first row
        int height = table.getRowHeight(startRow);

        // Center-align the content in the cells
        ((DefaultTableCellRenderer) table.getCellRenderer(startRow, column)).setHorizontalAlignment(SwingConstants.CENTER);

        // Calculate the maximum height among the cells in the current column
        for (int row = startRow + 1; row < startRow + rowCount; row++) {
            Component component = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
            height = Math.max(height, component.getPreferredSize().height);
        }

        // Set the height of the cells in the current column
        for (int row = startRow; row < startRow + rowCount; row++) {
            table.setRowHeight(row, height);
        }
    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("ArrayIndexOutOfBoundsException occurred in mergeCells method:");
        e.printStackTrace();
    }
}
    
private class CustomTableCellRenderer extends DefaultTableCellRenderer {
    public CustomTableCellRenderer() {
        setHorizontalAlignment(SwingConstants.CENTER); // Center-align the content
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Format the price and total price columns to show two decimal places
        if (value instanceof Double) {
            value = String.format("%.2f", value);
        }

        // Set the formatted value to the renderer component
        setText(value.toString());

        return rendererComponent;
    }
}    
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
            java.util.logging.Logger.getLogger(CabrewMainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CabrewMainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CabrewMainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CabrewMainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CabrewMainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel allPD;
    private javax.swing.JPanel brewedCoffeeSeriesPD;
    private javax.swing.JButton btn15D;
    private javax.swing.JButton btn1Y;
    private javax.swing.JButton btn30D;
    private javax.swing.JButton btn7D;
    private javax.swing.JButton btnAddQuan;
    private javax.swing.JButton btnAddToOrder;
    private javax.swing.JButton btnAllCatCMP;
    private javax.swing.JButton btnAllDays;
    private javax.swing.JButton btnBrewedCoffeeSeriesCatCMP;
    private javax.swing.JButton btnCofChoCatCMP;
    private javax.swing.JButton btnConfirmOrder;
    private javax.swing.JButton btnFrappeSeriesCatCMP;
    private javax.swing.JButton btnFruitSodaSupCatCMP;
    private javax.swing.JButton btnFruitTeaClCatCMP;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnLogOutHPC;
    private javax.swing.JButton btnMilkSeriesCatCMP;
    private javax.swing.JButton btnMilkTeaSupCatCMP;
    private javax.swing.JButton btnMilkteaClassicaCatCMP;
    private javax.swing.JButton btnMinusQuan;
    private javax.swing.JButton btnOption1;
    private javax.swing.JButton btnOption2;
    private javax.swing.JButton btnOption3;
    private javax.swing.JButton btnSnacksCatCMP;
    private javax.swing.JButton btnorderNav;
    private javax.swing.ButtonGroup buttonGroupOptions;
    private javax.swing.JPanel categoryCMPPanel;
    private javax.swing.JPanel cmpNavigationPanel;
    private javax.swing.JPanel frappeSeriesPD;
    private javax.swing.JPanel fruitSodaPD;
    private javax.swing.JPanel fruitTeaPD;
    private javax.swing.JPanel historyPanel;
    private javax.swing.JTable historyTable;
    private javax.swing.JPanel homePagePanel;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private java.awt.Label label1;
    private javax.swing.JPanel milkSeriesPD;
    private javax.swing.JPanel milkTeaSupremaPD;
    private javax.swing.JPanel milkteaClassicaPD;
    private javax.swing.JPanel orderCMPPanel;
    private javax.swing.JPanel orderDetailPanel;
    private javax.swing.JTable orderTable;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JPanel productDisplayPanel;
    private javax.swing.JPanel snacksPD;
    private javax.swing.JTextField txtAllTotalPrice;
    private javax.swing.JTextField txtProductCategory;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtTotalPrice;
    private javax.swing.JButton usernameCMPBtn;
    // End of variables declaration//GEN-END:variables
}
