

import com.mysql.jdbc.Connection;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ali Rahimi
 */

public class Post extends javax.swing.JFrame {

   
    static String pUsername, pPost, pDate;
    public Post(String username, String post, String date) {
        initComponents();
        pUsername = username;
        pPost = post;
        pDate = date;
        dateLabel.setText(date);
        textPost.setText(post);
        Integer numberOfLikes = 0;
        Integer numberOfComments = 0;
        
        //show image
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");             //connect to database from localhost
            Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
            String sqlOrder = "SELECT picture FROM post WHERE username = ? AND text = ? AND date = ?";
            PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, post);
            preparedStatement.setString(3, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                InputStream inputStream = resultSet.getBinaryStream("picture"); //binary ax
                BufferedImage im = ImageIO.read(inputStream);
                Image scaleImage = im.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), WIDTH);
                ImageIcon icon = new ImageIcon(scaleImage);
                imageLabel.setIcon(icon);
                
            }
            connector.close();
         
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!");
        }
        
        // number of likes
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");             //connect to database from localhost
            Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
            String sqlOrder = "SELECT * FROM likepost WHERE usernameLiked = ? AND post = ? AND date = ?";
            PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, post);
            preparedStatement.setString(3, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                numberOfLikes++;
                numberOfLikesLabel.setText(numberOfLikes.toString());
                
            }
            connector.close();
            
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!");
            
        }
        
        //chcek that if we like the post or not
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");             //connect to database from localhost
            Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
            String sqlOrder = "SELECT * FROM likepost WHERE usernameLiker = ? AND usernameLiked = ? AND post = ? AND date = ?";
            PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
            preparedStatement.setString(1, Login.usernameHelper);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, post);
            preparedStatement.setString(4, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
                buttonLike.setSelected(true);
                
            }else{
                buttonLike.setSelected(false);
            }
            
            connector.close();
            
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!");
        }
        
        //show comments
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");             //connect to database from localhost
            Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
            String sqlOrder = "SELECT usernameCommenter,comment FROM comments WHERE usernamePoster = ? AND post = ? AND date = ? ORDER BY dateComment DESC";
            PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, post);
            preparedStatement.setString(3, date);
            ResultSet resultSet = preparedStatement.executeQuery(); 
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            while(table.getRowCount() > 0){
                model.removeRow(0);
            }
            
            while(resultSet.next()){
                numberOfComments++;
                numberOfCommentsLabel.setText(numberOfComments.toString());
                model.addRow(new Object[]{resultSet.getString("usernameCommenter"), resultSet.getString("comment")});
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
        imageLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        numberOfLikesLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numberOfCommentsLabel = new javax.swing.JLabel();
        buttonLike = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        commentBox = new javax.swing.JTextField();
        buutonSendComment = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textPost = new javax.swing.JTextField();

        setTitle("Post");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        imageLabel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Number of Likes: ");

        numberOfLikesLabel.setText("0");

        jLabel2.setText("Number of Comments: ");

        numberOfCommentsLabel.setText("0");

        buttonLike.setBackground(new java.awt.Color(255, 255, 255));
        buttonLike.setText("Like");
        buttonLike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLikeActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Comment"
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
        jScrollPane1.setViewportView(table);

        commentBox.setText("write your comment");
        commentBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                commentBoxMouseClicked(evt);
            }
        });
        commentBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentBoxActionPerformed(evt);
            }
        });

        buutonSendComment.setBackground(new java.awt.Color(255, 255, 255));
        buutonSendComment.setText("send comment");
        buutonSendComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buutonSendCommentActionPerformed(evt);
            }
        });

        jLabel3.setText("Date: ");

        dateLabel.setText("jLabel4");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/50.png"))); // NOI18N

        textPost.setEditable(false);
        textPost.setText("caption post");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateLabel)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numberOfLikesLabel)
                            .addComponent(numberOfCommentsLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 99, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(79, 79, 79))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textPost, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
                                    .addGap(102, 102, 102)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(commentBox, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buutonSendComment, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonLike)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(buttonLike)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(numberOfLikesLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numberOfCommentsLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dateLabel))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(commentBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buutonSendComment, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buutonSendCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buutonSendCommentActionPerformed
        // send comment
        
        try {
            Class.forName("com.mysql.jdbc.Driver");             //connect to database from localhost
            Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
            String sqlOrder = "INSERT INTO comments(usernameCommenter,usernamePoster,post,date,comment) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
            preparedStatement.setString(1, Login.usernameHelper);
            preparedStatement.setString(2, pUsername);
            preparedStatement.setString(3, pPost);
            preparedStatement.setString(4, pDate);
            preparedStatement.setString(5, commentBox.getText());
            preparedStatement.executeUpdate();
            
            
            Post postFrame = new Post(pUsername, pPost, pDate);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            postFrame.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
            postFrame.show();
            this.hide();
            
            connector.close();
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!");
        }
    }//GEN-LAST:event_buutonSendCommentActionPerformed

    private void commentBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentBoxActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_commentBoxActionPerformed

    private void commentBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_commentBoxMouseClicked
        //clear
        commentBox.setText("");
    }//GEN-LAST:event_commentBoxMouseClicked

    private void buttonLikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLikeActionPerformed
        // like
        
        if(buttonLike.isSelected()){
            
            try {
                
                Class.forName("com.mysql.jdbc.Driver");             //connect to database from localhost
                Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
                String sqlOrder = "INSERT INTO likepost VALUES(?,?,?,?)";
                PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
                preparedStatement.setString(1, Login.usernameHelper);
                preparedStatement.setString(2, pUsername);
                preparedStatement.setString(3, pPost);
                preparedStatement.setString(4, pDate);
                preparedStatement.executeUpdate();
                
                Post postFrame = new Post(pUsername, pPost, pDate);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                postFrame.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
                postFrame.show();
                this.hide();
                connector.close();
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error!");
            }
            
            
            
        }else{
           
            try {
                Class.forName("com.mysql.jdbc.Driver");             //connect to database from localhost
                Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
                String sqlOrder = "DELETE FROM likepost WHERE usernameLiker = ? AND usernameLiked = ? AND post = ? AND date = ?";
                PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
                preparedStatement.setString(1, Login.usernameHelper);
                preparedStatement.setString(2, pUsername);
                preparedStatement.setString(3, pPost);
                preparedStatement.setString(4, pDate);
                preparedStatement.executeUpdate();
                
                Post postFrame = new Post(pUsername, pPost, pDate);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                postFrame.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
                postFrame.show();
                this.hide();
                connector.close();
                
                
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        }
    }//GEN-LAST:event_buttonLikeActionPerformed

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
            java.util.logging.Logger.getLogger(Post.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Post.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Post.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Post.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Post("username", "post", "date").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLike;
    private javax.swing.JButton buutonSendComment;
    private javax.swing.JTextField commentBox;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numberOfCommentsLabel;
    private javax.swing.JLabel numberOfLikesLabel;
    private javax.swing.JTable table;
    private javax.swing.JTextField textPost;
    // End of variables declaration//GEN-END:variables
}
