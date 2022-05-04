
package Controller;

import Helper.ImageHelper;
import Business.DB4OUtil;
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


public class LandingController {

    private School school;  
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
        
        
        this.daycareLabel = form.daycareLabel;
        this.mainContainer = form.mainContainer;
        this.usernameText =  form.usernameText;
        this.passwordField = form.passwordField;
        this.loginButton = form.loginButton;        
        

            loginToApplication();
        
    }
    
    private void buildIconsForLandingPage() {
         ImageHelper imageHelper = new ImageHelper();         
         imageHelper.scaleAndSetLabelIcon("/icons/day.png", daycareLabel, 100, 100, "Daycare logo");
    }
    
    private void loginToApplication(){
        DashboardPanel dp = new DashboardPanel(mainContainer, school);
        CardLayout layout = (CardLayout) mainContainer.getLayout();
        mainContainer.add(dp);
        layout.next(mainContainer);
    }

    
    public static void main(String[] args) {
        LandingController lc = new LandingController();
    }
    
}
