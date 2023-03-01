/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.airline_reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cjaya
 */
public class GetTicket1 extends javax.swing.JInternalFrame {

    /**
     * Creates new form GetTicket1
     */
    public GetTicket1() {
        initComponents();
        LoadData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        customerid = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);

        table.setBackground(new java.awt.Color(51, 51, 255));
        table.setForeground(new java.awt.Color(255, 255, 255));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TicketNo", "CustomerName", "CustomerID", "Price", "Date", "Seats", "FlightID", "PassportID", "Class Type"
            }
        ));
        jScrollPane1.setViewportView(table);

        jButton1.setText("Search Tickets");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        table1.setBackground(new java.awt.Color(255, 255, 0));
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TicketID", "CustomerName", "FlightName", "Date", "FlightID", "PassportID", "Arrival", "Departure"
            }
        ));
        jScrollPane2.setViewportView(table1);

        jLabel1.setBackground(new java.awt.Color(255, 255, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Search Tickets Here Based On Customer ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(54, 54, 54)
                            .addComponent(customerid, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton1)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerid, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(236, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            String CustomerID = customerid.getText();
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/reservation_system","root","Jaya@8186");
            pre = con.prepareStatement("select * from ticket where binary CustomerId=?");
            pre.setString(1, CustomerID);
            
            ResultSet rs = pre.executeQuery();
            ResultSetMetaData RSMD = rs.getMetaData();
            int cc = RSMD.getColumnCount();
            DefaultTableModel DFT = (DefaultTableModel) table1.getModel();
            DFT.setRowCount(0);
            boolean flag = false;
            while(rs.next()) {
                Vector v2 = new Vector();
                for(int i =1; i <= cc; i++) {
                    v2.add(rs.getString("TicketID"));
                    v2.add(rs.getString("CustomerName"));
                    v2.add(rs.getString("FlightName"));
                    v2.add(rs.getString("Date"));
//                    v2.add(rs.getString("Date"));
                    v2.add(rs.getString("FlightID"));
                    v2.add(rs.getString("PassportID"));
                    v2.add(rs.getString("Arrival"));
                    v2.add(rs.getString("Departure"));
                }
                flag = true;
                DFT.addRow(v2);
            }
            if(flag == false) {
               JOptionPane.showMessageDialog(null,"No tickets found try again later");
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetTicket1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetTicket1.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }//GEN-LAST:event_jButton1ActionPerformed

    Connection con;
    PreparedStatement pre;
    
    
    public void LoadData()
    {
        
        try {
            // TODO add your handling code here:
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/reservation_system","root","Jaya@8186");
            pre = con.prepareStatement("select * from ticket");
            
            ResultSet rs = pre.executeQuery();
            ResultSetMetaData RSMD = rs.getMetaData();
            int cc = RSMD.getColumnCount();
            DefaultTableModel DFT = (DefaultTableModel) table.getModel();
            DFT.setRowCount(0);
            boolean flag = false;
            while(rs.next()) {
                Vector v2 = new Vector();
                for(int i =1; i <= cc; i++) {
                    v2.add(rs.getString("TicketID"));
                    v2.add(rs.getString("CustomerName"));
                    v2.add(rs.getString("CustomerId"));
                    v2.add(rs.getString("Price"));
                    v2.add(rs.getString("Date"));
                    v2.add(rs.getString("Seats"));
                    v2.add(rs.getString("FlightID"));
                    v2.add(rs.getString("PassportID"));
                    v2.add(rs.getString("ClassType"));
                }
                flag = true;
                DFT.addRow(v2);
            }
            if(flag == false) {
               JOptionPane.showMessageDialog(null,"No tickets found try again later");
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookTicket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField customerid;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
