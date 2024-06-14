package tp3.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ManagerScreen extends JFrame {

    private Container container;

    public ManagerScreen() {
        this.container = getContentPane();
        this.container.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Manager");
        this.setMinimumSize(new Dimension(800, 800));
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setIconImage(Components.getLogoIcon().getImage());
        this.container.setBackground(Components.BACKGROUND_COLOR);

        JTabbedPane mainTabbedPanel = new JTabbedPane();

        JTabbedPane usersTabbedPanel = new JTabbedPane();
        JTabbedPane reviewsTabbedPanel = new JTabbedPane();
        JTabbedPane licensesTabbedPanel = new JTabbedPane();
        JPanel auditPanel = new JPanel(new BorderLayout());
        JPanel profilePanel = new JPanel(new BorderLayout());

        mainTabbedPanel.add("Utilizadores", usersTabbedPanel);
        mainTabbedPanel.add("Pedidos de Revisão", reviewsTabbedPanel);
        mainTabbedPanel.add("Licenças", licensesTabbedPanel);
        mainTabbedPanel.add("Auditoria", auditPanel);
        mainTabbedPanel.add("Perfil", profilePanel);
        
        new ManagerUserScreen(this, usersTabbedPanel);
        new ManagerLicenses(this, licensesTabbedPanel);
        new ManagerAudit(this, auditPanel);
        new Profile(this, profilePanel);
        

        this.container.add(mainTabbedPanel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    

}
