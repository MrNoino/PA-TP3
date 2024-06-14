package tp3.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import tp3.controller.ManageReviews;

public class ReviewerScreen extends JFrame implements ActionListener {

    private Container container;
    private JFrame frame;

    // ==================== UI Components ============================ //
    private JTabbedPane mainPanel;
    // =============== Reviews Tab
    private JPanel reviewsPanel;
    private JPanel searchPanel;
    private JTextField reviewsSearchField;
    private JButton searchButton;
    // =============== Review Book Tab
    private JPanel reviewBooksPanel;
    // =============== Profile Tab
    private JPanel profilePanel;

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

        mainPanel = new JTabbedPane();
        GridBagConstraints constraints = new GridBagConstraints();
        
        setupReviewsTab();
        setupReviewBookTab();
        setupProfileTab();

        mainPanel.add("Pedidos de Revisão", reviewsPanel);
        mainPanel.add("Rever Obra", reviewBooksPanel);
        mainPanel.add("Perfil", profilePanel);

        new Profile(this, profilePanel);

        this.container.add(mainPanel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        ManageReviews manageReviews = new ManageReviews();
        //ArrayList<Review> reviews = manageReviews.get

    }

    private void setupReviewsTab() {
        reviewsPanel = new JPanel(new BorderLayout());
        
        searchPanel = new JPanel(new FlowLayout());
        reviewsSearchField = Components.getTextField("Insira a pesquisa");
        searchButton = Components.getSecondaryButton("Pesquisar", "Pesquisar Revisões");
        searchButton.addActionListener(this);
        
        ManageReviews manageReviews = new ManageReviews();
        System.out.println(manageReviews.getReviewerReviews(Main.getLoggedUser().getId()));
        
        searchPanel.add(reviewsSearchField);
        searchPanel.add(searchButton);
        reviewsPanel.add(searchPanel, BorderLayout.NORTH);
    }

    private void setupReviewBookTab() {
        reviewBooksPanel = new JPanel();
    }

    private void setupProfileTab() {
        profilePanel = new JPanel(new BorderLayout());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
