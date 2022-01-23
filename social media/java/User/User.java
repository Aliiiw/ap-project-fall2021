


import com.mysql.jdbc.Connection;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
user
 */

/**
 *
 * @author Shayan BagherNejad
 */
public class User extends javax.swing.JFrame {

    /**
     * Creates new form User2
     */
    static String userName;
    public User(String username) {
        initComponents();
        userName = username;
        usernameLabel.setText(username);
        
        Integer numberOfPosts = 0;
        Integer numberOfFollowers = 0;
        Integer numberOfFollowings = 0;
        
        
        //show posts
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");             
            Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
            String sqlOrder = "SELECT text,date FROM post WHERE username = ?";
            PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
            preparedStatement.setString(1, userName); 
            ResultSet resultSet = preparedStatement.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            while(table.getRowCount() > 0){
                model.removeRow(0);  
            }
            while(resultSet.next()){
                numberOfPosts++;
                numberOfPostLabel.setText(numberOfPosts.toString());
                model.addRow(new Object[]{resultSet.getString("text"),resultSet.getString("date")});
                
            }  
            connector.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!");
        }
        
                    
            //follower number
            try {
                Class.forName("com.mysql.jdbc.Driver");             
                Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
                String sqlOrder = "SELECT * FROM follow WHERE usernameno2 = ?";
                PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
                preparedStatement.setString(1, userName); 
                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                numberOfFollowers++;
                numberOfFollowersLabel.setText(numberOfFollowers.toString());
               }
                connector.close();
                
                
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error!");
            }
            
            
            //following number
            try {
                Class.forName("com.mysql.jdbc.Driver");             
                Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
                String sqlOrder = "SELECT * FROM follow WHERE usernameno1 = ?";
                PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
                preparedStatement.setString(1, userName); 
                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                numberOfFollowings++;
                numberOfFollowingLabel.setText(numberOfFollowings.toString());
               }
                connector.close();
                
                
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error!");
            }
            
            
            
            //only one button
            
            
            try {
                Class.forName("com.mysql.jdbc.Driver");             
                Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
                String sqlOrder = "SELECT * FROM follow WHERE usernameno1 = ? AND usernameno2 = ? ";
                PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
                preparedStatement.setString(1, Login.usernameHelper);
                preparedStatement.setString(2, userName);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if(resultSet.next()){
                   buttonFollow.setVisible(false);
                }else{
                buttonUnfollow.setVisible(false);
                }
                

                connector.close();
                
                
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        numberOfFollowersLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        numberOfPostLabel = new javax.swing.JLabel();
        numberOfFollowingLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        buttonFollow = new javax.swing.JButton();
        buttonUnfollow = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setTitle("User Page");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setText("username:");

        usernameLabel.setText("usernameLabel");

        jLabel2.setText("Number of Followings: ");

        jLabel3.setText("Number of Followers: ");

        numberOfFollowersLabel.setText("0");

        jLabel4.setText("Number of Posts: ");

        numberOfPostLabel.setText("0");

        numberOfFollowingLabel.setText("0");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Text", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        buttonFollow.setBackground(new java.awt.Color(255, 255, 255));
        buttonFollow.setText("Follow");
        buttonFollow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFollowActionPerformed(evt);
            }
        });

        buttonUnfollow.setBackground(new java.awt.Color(255, 255, 255));
        buttonUnfollow.setText("UnFollow");
        buttonUnfollow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUnfollowActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Send Message");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/50.png"))); // NOI18N

        jButton2.setText("Block");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(usernameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(numberOfFollowingLabel))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(numberOfPostLabel))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(numberOfFollowersLabel))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonFollow)
                            .addComponent(buttonUnfollow)
                            .addComponent(jButton1)
                            .addComponent(jButton2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(usernameLabel)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numberOfFollowingLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(numberOfFollowersLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(numberOfPostLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonFollow)
                        .addGap(33, 33, 33)
                        .addComponent(buttonUnfollow)
                        .addGap(29, 29, 29)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(27, 27, 27))))
        );

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

    private void buttonFollowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFollowActionPerformed
        //follow
        try {
            Class.forName("com.mysql.jdbc.Driver");             
            Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
            String sqlOrder = "INSERT INTO follow VALUES(?,?)";
            PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
            preparedStatement.setString(1, Login.usernameHelper);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
            
            buttonFollow.setVisible(false);
            buttonUnfollow.setVisible(true);
            connector.close();
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!");
        }
        
        User userPage = new User(userName);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        userPage.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        userPage.show();
        this.hide();
    }//GEN-LAST:event_buttonFollowActionPerformed

    private void buttonUnfollowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUnfollowActionPerformed
        //unfollow
        try {
            Class.forName("com.mysql.jdbc.Driver");            
            Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
            String sqlOrder = "DELETE FROM follow WHERE usernameno1 = ? AND usernameno2 = ?";
            PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
            preparedStatement.setString(1, Login.usernameHelper);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
            
            buttonFollow.setVisible(true);
            buttonUnfollow.setVisible(false);
            connector.close();
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!");
        }
        
        User userPage = new User(userName);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        userPage.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        userPage.show();
        this.hide();
    }//GEN-LAST:event_buttonUnfollowActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // send message
        SendMessage sendMessage = new SendMessage(Login.usernameHelper, userName);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        sendMessage.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        sendMessage.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // mouse click
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int selectRow = table.getSelectedRow();
        String post = model.getValueAt(selectRow, 0).toString();
        String date = model.getValueAt(selectRow, 1).toString();
        Post postFrame = new Post(userName, post, date);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        postFrame.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        postFrame.show();
    }//GEN-LAST:event_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // block
        try {
            Class.forName("com.mysql.jdbc.Driver");             
            Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
            String sqlOrder = "INSERT INTO block VALUES(?,?)";
            PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
            preparedStatement.setString(1, Login.usernameHelper);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, userName + "has been blocked!");
            
            connector.close();
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!");
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User(userName).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonFollow;
    private javax.swing.JButton buttonUnfollow;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numberOfFollowersLabel;
    private javax.swing.JLabel numberOfFollowingLabel;
    private javax.swing.JLabel numberOfPostLabel;
    private javax.swing.JTable table;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
