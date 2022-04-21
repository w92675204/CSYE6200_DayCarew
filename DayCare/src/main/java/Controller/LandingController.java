/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Helper.ImageHelper;
import Business.DB4OUtil;
import Model.Admin;
import Model.School;
import View.DashboardPanel;
import View.Landing;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author vedan
 */
public class LandingController {

    private School school;
    private Admin admin = new Admin();    
    private DB4OUtil dB4OUtil = DB4OUtil.getInstance();
    private Landing form;
    private JLabel daycareLabel; 
    private JButton loginButton;    
    private JPanel mainContainer;
    private JPasswordField passwordField;        
    private JTextField usernameText;
    
    public LandingController() {      
        school = dB4OUtil.retrieveSystem();        
        form = new Landing();
        form.pack();
        form.setVisible(true);
        form.setLocationRelativeTo(null);
        
        
        // Get JComponents from the form that the controller wants to manipulate
        this.daycareLabel = form.daycareLabel;
        this.mainContainer = form.mainContainer;
        this.usernameText =  form.usernameText;
        this.passwordField = form.passwordField;
        this.loginButton = form.loginButton;        
        
        buildIconsForLandingPage();
        
        // Add event listener to perform action on close (X) operation
        form.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dB4OUtil.storeSystem(school);
            }
        });
        
        loginButton.addActionListener(e -> {
            loginToApplication();
        });
        
    }
    
    private void buildIconsForLandingPage() {
         ImageHelper imageHelper = new ImageHelper();         
         imageHelper.scaleAndSetLabelIcon("/icons/day.png", daycareLabel, 100, 100, "Daycare logo");
    }
    
    private void loginToApplication() {
        String username = usernameText.getText();        
        String password = String.valueOf(passwordField.getPassword());

        if (admin.signIn(username, password)) {
            DashboardPanel dp = new DashboardPanel(mainContainer, admin, school);
            CardLayout layout = (CardLayout) mainContainer.getLayout();
            mainContainer.add(dp);
            layout.next(mainContainer);
        } else {
            JOptionPane.showMessageDialog(form,
                    "Invalid username/password. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        LandingController lc = new LandingController();
    }
    
}
