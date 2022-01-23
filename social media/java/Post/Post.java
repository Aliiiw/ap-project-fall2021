
import com.mysql.jdbc.Connection;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
post page

/**
 *
 * @author Ali Rahimi
 */
public class Post extends javax.swing.JFrame {

    /**
     * Creates new form Post
     */
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
            
            Class.forName("com.mysql.jdbc.Driver");            
            Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
            String sqlOrder = "SELECT picture FROM post WHERE username = ? AND text = ? AND date = ?";
            PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, post);
            preparedStatement.setString(3, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                InputStream inputStream = resultSet.getBinaryStream("picture");                                 //binary ax
                BufferedImage im = ImageIO.read(inputStream);
                Image scaleImage = im.getScaledInstance(labelImage.getWidth(), labelImage.getHeight(), WIDTH);
                ImageIcon icon = new ImageIcon(scaleImage);
                labelImage.setIcon(icon);
                
            }
            connector.close();
         
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!");
        }
        
        
        // number of likes
        try {
            
            Class.forName("com.mysql.jdbc.Driver");            
            Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
            String sqlOrder = "SELECT * FROM likepost WHERE usernameLiked = ? AND post = ? AND date = ?";
            PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, post);
            preparedStatement.setString(3, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                numberOfLikes++;
                labelNumberOfLikes.setText(numberOfLikes.toString());
                
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
                labelNumberOfComments.setText(numberOfComments.toString());
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
        buttonLike = new javax.swing.JToggleButton();
        labelImage = new javax.swing.JLabel();
        buttonComment = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        textComment = new javax.swing.JTextField();
        labelNumberOfComments = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelNumberOfLikes = new javax.swing.JLabel();
        labelLikes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textPost = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        labelDate = new javax.swing.JLabel();

        setTitle("Post");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        buttonLike.setText("Like");
        buttonLike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLikeActionPerformed(evt);
            }
        });

        buttonComment.setText("send Comment");
        buttonComment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCommentActionPerformed(evt);
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
        jScrollPane2.setViewportView(table);

        textComment.setText("write your comment");
        textComment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textCommentMouseClicked(evt);
            }
        });

        labelNumberOfComments.setText("0");

        jLabel3.setText("Comments: ");

        labelNumberOfLikes.setText("0");

        labelLikes.setText("Likes: ");

        textPost.setEditable(false);
        jScrollPane1.setViewportView(textPost);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/50.png"))); // NOI18N

        jLabel2.setText("Date:");

        dateLabel.setText("date Label");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNumberOfComments)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelNumberOfLikes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonLike))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateLabel)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textComment, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonComment)))))
                .addGap(48, 48, 48))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelLikes))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(labelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(labelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLikes)
                    .addComponent(labelNumberOfLikes)
                    .addComponent(buttonLike))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(labelNumberOfComments))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dateLabel))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonComment))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelDate)
                .addGap(250, 250, 250))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLikeActionPerformed
        
        //if check liked   and uncheck unlike
        
        if(buttonLike.isSelected()){
            
            try {
                Class.forName("com.mysql.jdbc.Driver");             
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
                Class.forName("com.mysql.jdbc.Driver");            
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

    private void buttonCommentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCommentActionPerformed
        
        try {
            Class.forName("com.mysql.jdbc.Driver");             
            Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", ""); 
            String sqlOrder = "INSERT INTO comments(usernameCommenter,usernamePoster,post,date,comment) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connector.prepareStatement(sqlOrder);
            preparedStatement.setString(1, Login.usernameHelper);
            preparedStatement.setString(2, pUsername);
            preparedStatement.setString(3, pPost);
            preparedStatement.setString(4, pDate);
            preparedStatement.setString(5, textComment.getText());
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
    }//GEN-LAST:event_buttonCommentActionPerformed

    private void textCommentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textCommentMouseClicked
        //clear
        textComment.setText("");
    }//GEN-LAST:event_textCommentMouseClicked

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
    private javax.swing.JButton buttonComment;
    private javax.swing.JToggleButton buttonLike;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelImage;
    private javax.swing.JLabel labelLikes;
    private javax.swing.JLabel labelNumberOfComments;
    private javax.swing.JLabel labelNumberOfLikes;
    private javax.swing.JTable table;
    private javax.swing.JTextField textComment;
    private javax.swing.JTextPane textPost;
    // End of variables declaration//GEN-END:variables
}
