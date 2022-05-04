
package Controller;

import Model.AbstractPerson;
import Model.School;
import View.Students.StuInfo;
import View.Students.Students;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;


public class StudentsInformationController {

    private JPanel container;
    private School school;
    private AbstractPerson student;
    private StudentsPanelController prevController;
    
    private StuInfo panel;
    
    public JButton backButton;

    public StudentsInformationController(JPanel container, AbstractPerson student,School school, StudentsPanelController prevController) {
        this.container = container;
        this.school = school;
        this.student = student;
        
        this.prevController = prevController;
        
        panel = new StuInfo(container, student, school);
        
        this.backButton = panel.backButton;
        
        backButton.addActionListener(l -> {
            goBack();
        });
        
        
    }
    
    public StuInfo getStudentInformationPanel() {
        return panel;
    }
    
    private void goBack() {
        container.remove(panel);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
        Component[] componentArray = container.getComponents();
        Component component = componentArray[componentArray.length - 1];
        Students spanel = (Students) component;
        prevController.populateTable();
    }
    
    
    
}
