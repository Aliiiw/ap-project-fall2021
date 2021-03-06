
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/*
 Edit user info
 */

/**
 *
 * @author Ali Rahimi
 */
public class EditInformation extends javax.swing.JFrame {

    /**
     * Creates new form EditInformation
     */
    public static String username;
    public static String password;
    public static String email;
    public static String phoneNumber;
    public static String age;
    public static String bio;
    public static String fullName;
    public EditInformation() {
        initComponents();
       
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        newName = new javax.swing.JTextField();
        newBio = new javax.swing.JTextField();
        newNumber = new javax.swing.JTextField();
        newAge = new javax.swing.JTextField();
        editbutton = new javax.swing.JButton();

        setTitle("Edit Profile");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel2.setText("new Full name: ");

        jLabel3.setText("new Bio: ");

        jLabel4.setText("new Phone number: ");

        jLabel6.setText("new Age: ");

        editbutton.setBackground(new java.awt.Color(255, 255, 255));
        editbutton.setText("Edit");
        editbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(newAge, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(newNumber)
                            .addComponent(newBio, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(newName, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(editbutton)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(newName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(newBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(newNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(newAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(editbutton)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbuttonActionPerformed
        // to get new records for edit
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");             
            Connection connector = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/instagram?autoReconnect=true&useSSL=false", "root", "");
//            String sqlorderSelect = "SELECT * FROM user WHERE username = ?";
//            PreparedStatement preparedStatement = connector.prepareStatement(sqlorderSelect);
//            preparedStatement.setString(1, Login.usernameHelper);
//            ResultSet resultSet2 = preparedStatement.executeQuery();
//            
//            
//            while(resultSet2.next()){
//                
//                username = resultSet2.getString("username");
//                password = resultSet2.getString("password");
//                email = resultSet2.getString("email");
//                phoneNumber = resultSet2.getString("phoneNumber");
//                age = resultSet2.getString("age");
//                bio = resultSet2.getString("bio");
//                fullName = resultSet2.getString("fullName");
//                 
//            }

            
            String sqlOrder = "INSERT INTO info(username,phoneNumber, age, bio, fullName) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement1 = connector.prepareStatement(sqlOrder);
            
            preparedStatement1.setString(1, Login.usernameHelper);
            preparedStatement1.setString(2, newNumber.getText());
            preparedStatement1.setString(3, newAge.getText());
            preparedStatement1.setString(4, newBio.getText());
            preparedStatement1.setString(5, newName.getText());
            
            preparedStatement1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Changes added succesfully!");
            connector.close();
            this.hide();
            
            
//            if(newUsername.getText().trim().isEmpty()){    //username
//                preparedStatement1.setString(1, username);
//                
//                
//            }else{
//                preparedStatement1.setString(1, newUsername.getText());
//                
//            }
//            
//            if(newPassword.getText().trim().isEmpty()){ //password
//                preparedStatement1.setString(2, password);
//            }else{
//                preparedStatement1.setString(2, newUsername.getText());
//            }
//            
//            if(newEmail.getText().trim().isEmpty()){ //phone
//                preparedStatement1.setString(3, email);
//            }else{
//                preparedStatement1.setString(3, newEmail.getText());
//            }
//            
//            
//            
//            if(newNumber.getText().trim().isEmpty()){ //phone
//                preparedStatement1.setString(4, phoneNumber);
//            }else{
//                preparedStatement1.setString(4, newNumber.getText());
//            }
//            
//            if(newAge.getText().trim().isEmpty()){ //age
//                preparedStatement1.setString(5, age);
//            }else{
//                preparedStatement1.setString(5, newAge.getText());
//            }
//            
//            
//            if(newBio.getText().trim().isEmpty()){ //bio
//                preparedStatement1.setString(6, bio);
//            }else{
//                preparedStatement.setString(6, newBio.getText());
//            }
//            
//            if(newName.getText().trim().isEmpty()){ //fullname
//                preparedStatement1.setString(7, fullName);
//            }else{
//                preparedStatement1.setString(7, newName.getText());
//            }

//            preparedStatement1.executeUpdate();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!");    
        }
        

        
    }//GEN-LAST:event_editbuttonActionPerformed

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
            java.util.logging.Logger.getLogger(EditInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditInformation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editbutton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField newAge;
    private javax.swing.JTextField newBio;
    private javax.swing.JTextField newName;
    private javax.swing.JTextField newNumber;
    // End of variables declaration//GEN-END:variables
}
