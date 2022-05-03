/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Students;

import Model.AbstractPerson;
import Model.School;
import Model.Student;
import View.Students.StuInfo;
import java.awt.CardLayout;
import java.time.LocalDate;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vedan
 */
public class Registration extends javax.swing.JPanel {

    private JPanel container;
    private School school;

    /**
     * Creates new form RegistrationPanel
     */
    public Registration(JPanel container, School school) {
        initComponents();
        this.container = container;
        this.school = school;
        populateTable();
    }

    public void populateTable() {
        expiredTable.setAutoCreateRowSorter(true);
        DefaultTableModel model = (DefaultTableModel) expiredTable.getModel();
        model.setRowCount(0);

        for (AbstractPerson student : school.getStudentList()) {
            Object[] row = new Object[6];
            Student s = (Student) student;
            row[0] = student.getId();
            row[1] = student.getFirstName();
            row[2] = student.getLastName();
            row[3] = s.getLastRegDate().toString();
            row[4] = s.getExpectReNewDate().toString();
            row[5] = s.getParentEmail();
            model.addRow(row);
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

        viewTeacherButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        expiredTable = new javax.swing.JTable();

        viewTeacherButton.setText("Enroll student");
        viewTeacherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewTeacherButtonActionPerformed(evt);
            }
        });

        setMinimumSize(new java.awt.Dimension(1100, 700));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Track registrations");

        backButton.setText("< Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        expiredTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Firstname", "Lastname", "Registration date", "Renewal date", "Parent's email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(expiredTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(215, 215, 215)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        container.remove(this);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
    }//GEN-LAST:event_backButtonActionPerformed

    private void viewTeacherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewTeacherButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = expiredTable.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        int studentId = (int) expiredTable.getValueAt(selectedRow, 0);
        AbstractPerson selectedStudent = school.findStudentById(studentId);
        System.out.println(selectedStudent);
        if (selectedStudent != null) {
            Student s = (Student) selectedStudent;
            s.setLastRegDate(LocalDate.now());
            populateTable();
            JOptionPane.showMessageDialog(this,
                    "Updated",
                    "Success",
                    JOptionPane.OK_OPTION);
            return;

        }
        // System.out.println("Teacher not found");        
    }//GEN-LAST:event_viewTeacherButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTable expiredTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton viewTeacherButton;
    // End of variables declaration//GEN-END:variables
}
