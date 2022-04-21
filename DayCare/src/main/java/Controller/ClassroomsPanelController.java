/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Classroom;
import Model.School;
import View.Classrooms.ClassroomsPanel;
import View.Classrooms.ClassroomsInformationPanel;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vedan
 */
public class ClassroomsPanelController {

    private JPanel container;
    private School school;

    private ClassroomsPanel panel;

    private JButton backButton;
    private JTable classroomsTable;
    private JButton viewDetailsButton;

    public ClassroomsPanelController(JPanel container, School school) {
        this.container = container;
        this.school = school;

        this.panel = new ClassroomsPanel();

        this.backButton = panel.backButton;
        this.viewDetailsButton = panel.viewDetailsButton;
        this.classroomsTable = panel.classroomsTable;

        backButton.addActionListener(l -> {
            goBack();
        });

        viewDetailsButton.addActionListener(l -> {
            viewClassroomDetails();
        });

    }

    public ClassroomsPanel getClassroomsPanel() {
        populateTable();
        return panel;
    }

    public void populateTable() {
        classroomsTable.setAutoCreateRowSorter(true);
        DefaultTableModel model = (DefaultTableModel) classroomsTable.getModel();
        model.setRowCount(0);
        school.showAll();
        // TODO: Add wage column to the table
        for (Classroom classroom
                : school.getClassroomList()) {
            Object[] row = new Object[4];
            row[0] = classroom.getId();
            int low = classroom.getAgeRange()[0];
            int high = classroom.getAgeRange()[1];
            String range = String.valueOf(low) + " - " + String.valueOf(high);
            row[1] = range;
            row[2] = classroom.getCount();
            row[3] = classroom.getSize();
            model.addRow(row);
        }
    }

    private void goBack() {
        container.remove(panel);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
    }

    private void viewClassroomDetails() {
        int selectedRow = classroomsTable.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        // Get the teacherId of the selected row from the table
        int classroomId = (int) classroomsTable.getValueAt(selectedRow, 0);

        for (Classroom classroom : school.getClassroomList()) {
            if (classroom.getId() == classroomId) {
                // ClassroomsInformationPanel classroomInfoPanel = new ClassroomsInformationPanel(container, classroom);
                ClassroomsInformationPanel classroomInfoPanel = new ClassroomsInformationPanel(container, classroom);
                container.add(classroomInfoPanel);
                CardLayout layout = (CardLayout) container.getLayout();
                layout.next(container);
                return;
            }

        }
        System.out.println("Teacher not found");
    }

}
