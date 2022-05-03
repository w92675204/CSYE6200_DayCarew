/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.School;
import Model.Student;
import Model.StudentFactory;
import View.Students.AddStu;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class AddStudentController {

    JPanel container;
    private School school;
    private StudentsPanelController prevController;

    private AddStu panel;
    
    public javax.swing.JTextField idTextField;
    public javax.swing.JTextField studentLastNameText;
    public javax.swing.JTextField studentFirstNameTf;
    public javax.swing.JButton addStudentButton;
    public javax.swing.JTextField ageText;
    public javax.swing.JButton backButton;
    public javax.swing.JTextField gpaTf;
    
//    public javax.swing.JTextField parentEmailTf;
//    public javax.swing.JTextField parentNameTf;
    public javax.swing.JTextField registrationTf;
    


    public AddStudentController(JPanel container, School school, StudentsPanelController prevController) {
        this.container = container;
        this.school = school;
        this.prevController = prevController;

        this.panel = new AddStu();

        this.addStudentButton = panel.addStudentButton;
        this.ageText = panel.ageText;
        this.backButton = panel.backButton;
        this.gpaTf = panel.gpaTf;
        this.idTextField = panel.idTextField;
//        this.parentEmailTf = panel.parentEmailTf;
//        this.parentNameTf = panel.parentNameTf;
        this.registrationTf = panel.registrationTf;
        this.studentFirstNameTf = panel.studentFirstNameTf;
        this.studentLastNameText = panel.studentLastNameText;

        backButton.addActionListener(l -> {
            goBack();
        });

        addStudentButton.addActionListener(l -> {
            addStudent();
        });

    }

    public AddStu getAddStudentsPanel() {
        return panel;
    }

    private void goBack() {
        container.remove(panel);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
        Component[] componentArray = container.getComponents();
        Component component = componentArray[componentArray.length - 1];
        prevController.populateTable();
    }

    private void addStudent() {
        // TODO add your handling code here:
        /**
         * 1. Get all the info from the text fields 2. Use the student factory
         * to get the Student object and populate the information from here
         */

        String id = idTextField.getText();
        String age = ageText.getText();
        String studentFirstName = studentFirstNameTf.getText();
        String studentLastName = studentLastNameText.getText();
        String regDate = registrationTf.getText();
        String gpa = gpaTf.getText();
//        String parentName = parentNameTf.getText();
//        String parentEmail = parentEmailTf.getText();

        if (studentFirstName.equals("") || age.equals("") || id.equals("") || studentLastName.equals("") || regDate.equals("") || gpa.equals("")) {
            JOptionPane.showMessageDialog(panel,
                    "Please fill all the required fields",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

//        String address = addressText.getText();
//        String gender = genderCombo.getSelectedItem().toString();
        String studentString = id + "," + age + "," + studentFirstName + "," + studentLastName + "," + regDate + "," + gpa ;

        try {
            Student s = StudentFactory.getInstance().getObject(studentString);//有错原因：添加时间格式要按照2008-03-06格式来写
            school.addStudent(s);
            // school.addAllStudentToCSV();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel,
                    "Error. Unable to create the student",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(panel,
                "Student successfully added.",
                "Success",
                JOptionPane.OK_OPTION);
    }

}
