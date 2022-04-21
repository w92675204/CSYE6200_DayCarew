/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AbstractPerson;
import Model.School;
import View.Students.StudentInformationPanel;
import View.Students.StudentsPanel;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author vedan
 */
public class StudentsInformationController {

    private JPanel container;
    private School school;
    private AbstractPerson student;
    private StudentsPanelController prevController;
    
    private StudentInformationPanel panel;
    
    public JButton backButton;

    public StudentsInformationController(JPanel container, AbstractPerson student,School school, StudentsPanelController prevController) {
        this.container = container;
        this.school = school;
        this.student = student;
        
        this.prevController = prevController;
        
        panel = new StudentInformationPanel(container, student, school);
        
        this.backButton = panel.backButton;
        
        backButton.addActionListener(l -> {
            goBack();
        });
        
        
    }
    
    public StudentInformationPanel getStudentInformationPanel() {
        return panel;
    }
    
    private void goBack() {
        container.remove(panel);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
        Component[] componentArray = container.getComponents();
        Component component = componentArray[componentArray.length - 1];
        StudentsPanel spanel = (StudentsPanel) component;
        prevController.populateTable();
    }
    
    
    
}
