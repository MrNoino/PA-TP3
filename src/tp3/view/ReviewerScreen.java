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

public class ReviewerScreen extends JFrame implements ActionListener {

    private Container container;
    private JFrame frame;

    public ReviewerScreen() {
        this.frame = this;
        this.container = getContentPane();
        this.container.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Revisor");
        this.setMinimumSize(new Dimension(800, 800));
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setIconImage(Components.getLogoIcon().getImage());
        this.container.setBackground(Components.BACKGROUND_COLOR);

        JTabbedPane tabbedPanel = new JTabbedPane();
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel reviewRequestsPanel = new JPanel();
        JPanel reviewBooksPanel = new JPanel();
        JPanel profilePanel = new JPanel();

        tabbedPanel.add("Pedidos de Revisão", reviewRequestsPanel);
        tabbedPanel.add("Rever Obra", reviewBooksPanel);
        tabbedPanel.add("Perfil", profilePanel);

        this.container.add(tabbedPanel);
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
