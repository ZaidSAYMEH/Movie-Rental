/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_movie_rental;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ads
 */
public class search_movie extends javax.swing.JDialog {

    /**
     * Creates new form search_movie
     */
    private Connection con = null;
    private DefaultTableModel model = null;
    
    public search_movie(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        model = new DefaultTableModel();   
        model.addColumn("Movie_Name");
        model.addColumn("Length");
        model.addColumn("Rated");
        model.addColumn("Release_Date");
        model.addColumn("Rented_To");
        model.addColumn("Date_Of_Rental");
        model.addColumn("Date_Of_Return");
        model.addColumn("Category");
        movies_table.setModel(model);
        
        movies_table.getColumnModel().getColumn(0).setPreferredWidth(100);
        movies_table.getColumnModel().getColumn(1).setPreferredWidth(20);
        movies_table.getColumnModel().getColumn(2).setPreferredWidth(40);
        movies_table.setEnabled(false);
        
        
        
        movie_name.getDocument().addDocumentListener(new DocumentListener()
                {
            @Override
            public void insertUpdate(DocumentEvent de) {
                clearTable();
                result.setText("");
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                clearTable();
                result.setText("");
                
                if(movie_name.getText().isEmpty() == false)
                   search();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
            }
                    
                });
    }

    
    
    public void clearTable()
    {
        while (model.getRowCount()>0)
          {
             model.removeRow(0);
          }
    }    

    
    public void search()
    {
       
        try {
                con = conn_db.getCon();
                ResultSet rs;
                PreparedStatement  prep = con.prepareStatement("select * from MoviesTable "
                                                             + "where  Movie_Name=?;");
                prep.setString(1,movie_name.getText());
                rs = prep.executeQuery();
                
            
            
            if(rs.next())
            {    
            model.addRow(new Object [] {rs.getString(1) , rs.getInt(2)    , rs.getString(3)
                                      , rs.getString(4) , rs.getString(5) , rs.getString(6)
                                      , rs.getString(7) , rs.getString(8)});
            result.setText("*Movie Is Found");
            result.setForeground(Color.BLUE);
            }
            
            else
            {
              result.setText("*Movie Is Not Found");
              result.setForeground(Color.RED);
            }
            
           
                } catch (SQLException ex) {
                    Logger.getLogger(search_movie.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(search_movie.class.getName()).log(Level.SEVERE, null, ex);
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

        label_enter_movie = new javax.swing.JLabel();
        movie_name = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        movies_table = new javax.swing.JTable();
        result = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search For A Movie");
        setResizable(false);

        label_enter_movie.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        label_enter_movie.setText("Enter Name Of Movie To Search : ");

        movies_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        movies_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(movies_table);

        result.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_enter_movie)
                .addGap(18, 18, 18)
                .addComponent(movie_name, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_enter_movie)
                    .addComponent(movie_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(result))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(search_movie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(search_movie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(search_movie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(search_movie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                search_movie dialog = new search_movie(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_enter_movie;
    private javax.swing.JTextField movie_name;
    private javax.swing.JTable movies_table;
    private javax.swing.JLabel result;
    // End of variables declaration//GEN-END:variables
}