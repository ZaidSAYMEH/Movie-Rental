/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_movie_rental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author ads
 */
public class return_movie extends javax.swing.JDialog {

    /**
     * Creates new form return_movie
     */
    private Connection con = null;
    
    public return_movie(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        label_movie_name = new javax.swing.JLabel();
        label_return_date = new javax.swing.JLabel();
        movie_name = new javax.swing.JTextField();
        return_movie = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        dd = new javax.swing.JComboBox<>();
        mm = new javax.swing.JComboBox<>();
        yy = new javax.swing.JComboBox<>();
        l1 = new javax.swing.JLabel();
        l2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search A Movie");
        setResizable(false);

        label_movie_name.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        label_movie_name.setText("Enter Movie Name You want To Return : ");

        label_return_date.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        label_return_date.setText("Enter Date Of Return : ");

        return_movie.setBackground(new java.awt.Color(0, 204, 204));
        return_movie.setForeground(new java.awt.Color(255, 255, 255));
        return_movie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_movie_rental/icons/return_ic.png"))); // NOI18N
        return_movie.setMnemonic('R');
        return_movie.setText("Return Movie");
        return_movie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                return_movieActionPerformed(evt);
            }
        });

        clear.setBackground(new java.awt.Color(153, 153, 255));
        clear.setForeground(new java.awt.Color(255, 255, 255));
        clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_movie_rental/icons/clear.png"))); // NOI18N
        clear.setMnemonic('C');
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        dd.setBackground(new java.awt.Color(254, 254, 254));
        dd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        mm.setBackground(new java.awt.Color(254, 254, 254));
        mm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        yy.setBackground(new java.awt.Color(254, 254, 254));
        yy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022", "2023", "2024", "2025" }));

        l1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        l1.setText("/");

        l2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        l2.setText("/");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label_movie_name)
                                .addGap(18, 18, 18)
                                .addComponent(movie_name, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label_return_date)
                                .addGap(18, 18, 18)
                                .addComponent(dd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(l1)
                                .addGap(5, 5, 5)
                                .addComponent(mm, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(l2)
                                .addGap(8, 8, 8)
                                .addComponent(yy, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(79, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(return_movie)
                        .addGap(18, 18, 18)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(189, 189, 189))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_movie_name)
                    .addComponent(movie_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_return_date)
                    .addComponent(dd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l1)
                    .addComponent(l2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(return_movie, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void return_movieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_return_movieActionPerformed
        
        if(movie_name.getText().isEmpty())
      {      
             showMessageDialog(null,"Movie Name Input Field Must Be Filled !","Empty Input Field",
                               ERROR_MESSAGE,new ImageIcon("icons/warning.png"));
      }
      
     
      else
      {
        try {
            con = conn_db.getCon();
            PreparedStatement  prep =
            con.prepareStatement("update MoviesTable set"
                                + " Rented_To=?" + " , Date_Of_Rental=?" + " , Date_Of_Return=?"
                                + " where Movie_Name=? and Date_Of_Return=?");
           
                prep.setString(1,"none");
                prep.setString(2,"none");
                prep.setString(3,"none");
                prep.setString(4,movie_name.getText());
                prep.setString(5,dd.getSelectedItem() + "/" + mm.getSelectedItem() + "/" +
                                 yy.getSelectedItem());
            int x = prep.executeUpdate();
            
            if(x != 0)
            {
                showMessageDialog(null,"Movie Is Returned Successfully","Movie Returned",
                                 INFORMATION_MESSAGE,new ImageIcon("icons/success.png"));
            }

            else
            {
                showMessageDialog(null,"Either Movie Is Not Found in The database"
                                     + " or Return Date is Wrong","Error",
                                     ERROR_MESSAGE,new ImageIcon("icons/fail.png"));
            }
                  
        } catch (SQLException ex) {
            Logger.getLogger(rent_movie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(rent_movie.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }//GEN-LAST:event_return_movieActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        movie_name.setText("");
        dd.setSelectedIndex(0);
        mm.setSelectedIndex(0);
        yy.setSelectedIndex(0);
    }//GEN-LAST:event_clearActionPerformed

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
            java.util.logging.Logger.getLogger(return_movie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(return_movie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(return_movie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(return_movie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                return_movie dialog = new return_movie(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clear;
    private javax.swing.JComboBox<String> dd;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel label_movie_name;
    private javax.swing.JLabel label_return_date;
    private javax.swing.JComboBox<String> mm;
    private javax.swing.JTextField movie_name;
    private javax.swing.JButton return_movie;
    private javax.swing.JComboBox<String> yy;
    // End of variables declaration//GEN-END:variables
}
