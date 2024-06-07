package tp3.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import tp3.controller.ManageLogs;
import tp3.model.Log;
import tp3.model.User;

public class Main extends JFrame{
    
    private static User loggedUser;
    
    public static void main(String[] args) {   
        JFrame frame = new LoginScreen();
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
     * get the user logged in
     *
     * @return the user logged in
     */
    public static User getLoggedUser() {
        return Main.loggedUser;
    }

    /**
     * Assigns the user that logged in and inserts a log
     *
     * @param loggedUser the user logged in
     */
    public static void login(User loggedUser) {
        Main.loggedUser = loggedUser;
        new ManageLogs().insertLog(new Log(Main.loggedUser.getId(), 
                        new SimpleDateFormat("yyyy-mm-dd").format(new Date()), 
                        Main.loggedUser.getUsername() + " Iniciou Sessão"));
    }

    /**
     * Logout by inserting a log and print a goodbye message
     */
    public static void logout() {
        if (Main.getLoggedUser() != null) {
            new ManageLogs().insertLog(new Log(Main.getLoggedUser().getId(),
                new SimpleDateFormat("yyyy-mm-dd").format(new Date()),
                Main.getLoggedUser().getUsername() + " Terminou Sessão"));
        }
        Main.loggedUser = null;
    }
}
