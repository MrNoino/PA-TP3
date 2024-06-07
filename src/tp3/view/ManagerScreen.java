package tp3.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ManagerScreen extends JFrame implements ActionListener {

    private Container container;
    private JFrame frame;

    public ManagerScreen() {
        this.frame = this;
        this.container = getContentPane();
        this.container.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Manager");
        this.setMinimumSize(new Dimension(800, 800));
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setIconImage(Components.getLogoIcon().getImage());
        this.container.setBackground(Components.BACKGROUND_COLOR);

        JTabbedPane tabbedPanel = new JTabbedPane();
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel usersPanel = new JPanel();
        JPanel reviewsPanel = new JPanel();
        JPanel licensesPanel = new JPanel();
        JPanel logsPanel = new JPanel();
        JPanel profilePanel = new JPanel();

        tabbedPanel.add("Utilizadores", usersPanel);
        tabbedPanel.add("Pedidos de Revisão", reviewsPanel);
        tabbedPanel.add("Licenças", licensesPanel);
        tabbedPanel.add("Auditoria", logsPanel);
        tabbedPanel.add("Perfil", profilePanel);


        this.container.add(tabbedPanel);
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
