
package Controller;

import Model.AbstractPerson;
import Model.School;
import Model.Student;
import Model.Vax;
import View.Immunization.ImmunizationInformationPanel;
import java.awt.CardLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


public class ImmunizationPanelController {

    private JPanel container;
    private School school;
    private List<AbstractPerson> studentsList;

    private ImmunizationInformationPanel panel;

    private javax.swing.JButton backButton;
    public javax.swing.JTable immuTable;

    public ImmunizationPanelController(JPanel container, School school) {
        this.container = container;
        this.school = school;

        this.panel = new ImmunizationInformationPanel();
        this.backButton = panel.backButton;
        this.immuTable = panel.immuTable;

        backButton.addActionListener(l -> {
            goBack();
        });

    }

    public ImmunizationInformationPanel getImmunizationPanel() {
        populateTable();
        return panel;
    }

    public void populateTable() {
        immuTable.setAutoCreateRowSorter(true);
        DefaultTableModel model = (DefaultTableModel) immuTable.getModel();
        model.setRowCount(0);

        for (AbstractPerson student : school.getStudentList()) {
            Object[] row = new Object[6];
            Student s = (Student) student;
            for (Vax v : s.getVaxList()) {
                row[0] = s.getId();
                row[1] = student.getFirstName() + " " + s.getLastName();
                row[2] = v.getName();
                row[3] = v.getNoOfDoses();
                row[4] = v.getLastDoseDate().toString();
                row[5] = v.getNextDoseDate().toString();
                model.addRow(row);
            }
        }
    }

    private void goBack() {
        container.remove(panel);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
    }

}
