/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_movie_rental;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.*;

/**
 *
 * @author ads
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private Connection con = null;
    public Main() {
        initComponents(); 
    }
    
    
    public void import_movies()
    {
        File file;
        JFileChooser choose = new JFileChooser();
        choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        int x = choose.showOpenDialog(null);
        
        if(x == JFileChooser.APPROVE_OPTION)
        {
        file = choose.getSelectedFile();
        
            try {
                int count_excluded =0;
                int count_inserted =0;
                Scanner in = new Scanner(file);
               
                while(in.hasNext())
                {
                   /*
                    <important> : movie name , renter name , rated  cause problems 
                                  if it contains spaces because scanner read each
                                  part separately ... 
                    To solve the problem : i replaced these spaces with underline's (_)
                    when export the movies to a file.
                    and undo it when import by replace underline's (_) for these columns
                    with spaces
                   
                    ***if the imported file is written manually 
                    you should use underline's (_) for columns with multiple space***
                    */ 
                    
                    String movie_name = in.next().replace('_', ' ');
                    int length = in.nextInt();
                    String rated = in.next().replace('_', ' ');
                    String release_date = in.next();
                    String rented_to = in.next().replace('_', ' ');
                    String rental_date = in.next();
                    String return_date = in.next();
                    String category = in.next();
                    
                    try {
                        con = conn_db.getCon();
                        ResultSet rs;
                        PreparedStatement prep = con.prepareStatement("select * from MoviesTable"
                                                                    + " where Movie_Name=?");
                        
                        prep.setString(1,movie_name);
                        rs = prep.executeQuery();
                        
                        if(rs.next())
                        {
                            count_excluded++;
                        }
                        else
                        {
                            count_inserted++;
                            
                            prep = con.prepareStatement("insert into MoviesTable "
                                                       +"values(?,?,?,?,?,?,?,?);");
                            prep.setString(1,movie_name);
                            prep.setInt(2,length);
                            prep.setString(3,rated);
                            prep.setString(4,release_date);
                            prep.setString(5,rented_to);
                            prep.setString(6,rental_date);
                            prep.setString(7,return_date);
                            prep.setString(8,category);
                            prep.executeUpdate();
                        }
                        
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                in.close();

                if((count_inserted + count_excluded) != 0)
                {
                showMessageDialog(null,"Number Of Inserted Movies = " + count_inserted +"\n\n"
                                 + "(Number Of Excluded Movies = " + count_excluded 
                                 + ") --> These Movies Already Exists In The Database","Imported Movies",
                                  INFORMATION_MESSAGE,new ImageIcon("icons/info_success.png"));
                }
                
                else
                {
                showMessageDialog(null,"Imported File Is Empty !!" , "Empty File"
                                 ,INFORMATION_MESSAGE,new ImageIcon("icons/info_fail.png"));
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }  
    }
    
    
    public void export_movies()
    {
        File file;
        JFileChooser choose = new JFileChooser();
        choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       
        int x = choose.showOpenDialog(null);
        
        if(x == JFileChooser.APPROVE_OPTION)
        {
        try {
            file = choose.getSelectedFile();
            PrintStream p = new PrintStream(file.getPath() + "/Movies.txt");
          
             System.setOut(p);
           try {
               con = conn_db.getCon();
               ResultSet rs;
               PreparedStatement prep = con.prepareStatement("select * from MoviesTable;");
               
               rs=prep.executeQuery();
               
               int count=0;
               while(rs.next())
               {
                   /*
                    <important> : movie name , renter name , rated  cause problems 
                                  if it contains spaces because scanner read each
                                  part separately ... 
                    To solve the problem : i replaced these spaces with underline's (_)
                    when export the movies to a file.
                    and undo it when import by replace underline's (_) for these columns
                    with spaces
                   
                    ***if the imported file is written manually 
                    you should use underline's (_) for columns with multiple space***
                    */ 
                   
                   System.out.println(rs.getString("Movie_Name").replace(" ", "_") + "\t"
                                     +rs.getInt("Length")        + "\t"
                                     +rs.getString("Rated").replace(" ", "_")      + "\t"
                                     +rs.getString("Release_Date")+"\t"
                                     +rs.getString("Rented_To").replace(" ", "_")  +"\t"
                                     +rs.getString("Date_Of_Rental")+"\t"
                                     +rs.getString("Date_Of_Return")+"\t"
                                     +rs.getString("Category"));
                   count++;
               }
               
               
               p.close();
               
               if(count != 0)
               {
               showMessageDialog(null,"Total Number Of Movies Exported To A File Named (Movie.txt)"
                                 + " = " + count,"Exported Movies",
                                 INFORMATION_MESSAGE,new ImageIcon("icons/info_success.png"));
               }
               
               else
               {
                showMessageDialog(null,"No Movies Exported Because DataBase Is Empty","No Exporte Movies"
                                 ,INFORMATION_MESSAGE,new ImageIcon("icons/info_fail.png"));
               }
               
           } catch (SQLException ex) {
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
               
           } catch (Exception ex) { 
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
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

        jMenuBar1 = new javax.swing.JMenuBar();
        file_menu = new javax.swing.JMenu();
        import_file = new javax.swing.JMenuItem();
        export_file = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exit = new javax.swing.JMenuItem();
        operations_menu = new javax.swing.JMenu();
        add_video = new javax.swing.JMenuItem();
        delete_video = new javax.swing.JMenuItem();
        search_movie = new javax.swing.JMenuItem();
        rent_video = new javax.swing.JMenuItem();
        return_video = new javax.swing.JMenuItem();
        search_customer = new javax.swing.JMenuItem();
        display_all = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Movie Rental Application");

        file_menu.setText("File");

        import_file.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_movie_rental/icons/import.png"))); // NOI18N
        import_file.setText("import");
        import_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                import_fileActionPerformed(evt);
            }
        });
        file_menu.add(import_file);

        export_file.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_movie_rental/icons/export.png"))); // NOI18N
        export_file.setText("export");
        export_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                export_fileActionPerformed(evt);
            }
        });
        file_menu.add(export_file);
        file_menu.add(jSeparator1);

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_movie_rental/icons/exit.png"))); // NOI18N
        exit.setText("exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        file_menu.add(exit);

        jMenuBar1.add(file_menu);

        operations_menu.setText("Operations");

        add_video.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_movie_rental/icons/new_movie.png"))); // NOI18N
        add_video.setText("Add a new movie");
        add_video.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_videoActionPerformed(evt);
            }
        });
        operations_menu.add(add_video);

        delete_video.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_movie_rental/icons/delete.png"))); // NOI18N
        delete_video.setText("Delete a movie");
        delete_video.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_videoActionPerformed(evt);
            }
        });
        operations_menu.add(delete_video);

        search_movie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_movie_rental/icons/search.png"))); // NOI18N
        search_movie.setText("Search for a movie");
        search_movie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_movieActionPerformed(evt);
            }
        });
        operations_menu.add(search_movie);

        rent_video.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_movie_rental/icons/movie.png"))); // NOI18N
        rent_video.setText("Rent a movie");
        rent_video.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rent_videoActionPerformed(evt);
            }
        });
        operations_menu.add(rent_video);

        return_video.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_movie_rental/icons/return.png"))); // NOI18N
        return_video.setText("Return a movie");
        return_video.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                return_videoActionPerformed(evt);
            }
        });
        operations_menu.add(return_video);

        search_customer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_movie_rental/icons/search_customer.png"))); // NOI18N
        search_customer.setText("Search for a customer");
        search_customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_customerActionPerformed(evt);
            }
        });
        operations_menu.add(search_customer);

        display_all.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui_movie_rental/icons/display.png"))); // NOI18N
        display_all.setText("Display all movies");
        display_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                display_allActionPerformed(evt);
            }
        });
        operations_menu.add(display_all);

        jMenuBar1.add(operations_menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void import_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_import_fileActionPerformed
        import_movies();
    }//GEN-LAST:event_import_fileActionPerformed

    private void export_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_export_fileActionPerformed
       export_movies();
    }//GEN-LAST:event_export_fileActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        dispose();
    }//GEN-LAST:event_exitActionPerformed

    private void add_videoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_videoActionPerformed
        new add_new_movie(this,true).setVisible(true);
    }//GEN-LAST:event_add_videoActionPerformed

    private void display_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_display_allActionPerformed
        new display_all(this,true).setVisible(true);        
    }//GEN-LAST:event_display_allActionPerformed

    private void search_movieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_movieActionPerformed
        new search_movie(this,true).setVisible(true);
    }//GEN-LAST:event_search_movieActionPerformed

    private void rent_videoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rent_videoActionPerformed
        new rent_movie(this,true).setVisible(true);
    }//GEN-LAST:event_rent_videoActionPerformed

    private void search_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_customerActionPerformed
        new search_customer(this,true).setVisible(true);
    }//GEN-LAST:event_search_customerActionPerformed

    private void return_videoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_return_videoActionPerformed
        new return_movie(this,true).setVisible(true);
    }//GEN-LAST:event_return_videoActionPerformed

    private void delete_videoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_videoActionPerformed
        new delete_movie(this,true).setVisible(true);
    }//GEN-LAST:event_delete_videoActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
             
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem add_video;
    private javax.swing.JMenuItem delete_video;
    private javax.swing.JMenuItem display_all;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenuItem export_file;
    private javax.swing.JMenu file_menu;
    private javax.swing.JMenuItem import_file;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu operations_menu;
    private javax.swing.JMenuItem rent_video;
    private javax.swing.JMenuItem return_video;
    private javax.swing.JMenuItem search_customer;
    private javax.swing.JMenuItem search_movie;
    // End of variables declaration//GEN-END:variables
}
