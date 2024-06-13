package tp3.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import tp3.controller.ManageReviews;
import tp3.model.Review;

public class AuthorScreen extends JFrame implements ActionListener {

    private Container container;
    private JFrame frame;
    private JButton searchButton;
    private JTable reviewsTable;
    String[] columnNames = new String[]{"Data de Submissão", "Número de Série", "Título", "Estado"};

    public AuthorScreen() {
        this.frame = this;
        this.container = getContentPane();
        this.container.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Autor");
        this.setMinimumSize(new Dimension(800, 800));
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setIconImage(Components.getLogoIcon().getImage());
        this.container.setBackground(Components.BACKGROUND_COLOR);

        JTabbedPane tabbedPanel = new JTabbedPane();

        JPanel booksPanel = new JPanel();
        JPanel profilePanel = new JPanel(new BorderLayout());

        // =================== Reviews ========================= //
        JTabbedPane reviewsPanel = new JTabbedPane();
        JPanel listReviewsPanel = new JPanel(new BorderLayout());
        JPanel requestReviewPanel = new JPanel(new GridBagLayout());

        // List Reviews
        JPanel searchPanel = new JPanel(new FlowLayout());
        JTextField searchField = Components.getTextField("Insira a pesquisa");
        searchButton = Components.getSecondaryButton("Pesquisar", "Pesquisar Revisões");

        ManageReviews manageReviews = new ManageReviews();
        manageReviews.getReviewsByAuthor(Main.getLoggedUser().getId(), "ASC", 1);
        reviewsTable = new JTable(manageReviews.toAuthorReviewArray(), columnNames);
        reviewsTable.setAutoCreateRowSorter(true);
        reviewsTable.setDefaultEditor(Object.class, null);

        listReviewsPanel.add(reviewsTable);

        JScrollPane reviewsTableScroll = new JScrollPane(reviewsTable);
        listReviewsPanel.add(reviewsTableScroll, BorderLayout.CENTER);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        listReviewsPanel.add(searchPanel, BorderLayout.NORTH);

        // Request Review
        reviewsPanel.add("Visualizar", listReviewsPanel);
        reviewsPanel.add("Inserir", requestReviewPanel);

        // ===================================================== //
        tabbedPanel.add("Obras", booksPanel);
        tabbedPanel.add("Revisões", reviewsPanel);
        tabbedPanel.add("Perfil", profilePanel);
        
        new Profile(this, profilePanel);

        this.container.add(tabbedPanel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            ManageReviews manageReviews = new ManageReviews();
            manageReviews.getReviewsByAuthor(Main.getLoggedUser().getId(), "ASC", 1);
            reviewsTable = new JTable(manageReviews.toAuthorReviewArray(), columnNames);
        }
    }
}
