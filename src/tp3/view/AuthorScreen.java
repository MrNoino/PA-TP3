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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import tp3.controller.ManageBooks;
import tp3.controller.ManageReviews;
import tp3.model.Book;
import tp3.model.Review;

public class AuthorScreen extends JFrame implements ActionListener {

    private Container container;
    private JFrame frame;
    private final String[] columnNames = new String[]{"Data de Submissão", "Número de Série", "Título", "Estado"};
    private long authorId;

    // ============ UI COMPONENTS ============== //
    JTabbedPane mainPanel;
    // ================ Books Panel
    JTabbedPane booksPanel;
    // ======= List
    // ======= Insert
    // ======= Update
    // ================ Reviews Panel
    JTabbedPane reviewsPanel;
    // ======= List
    JPanel listReviewsPanel;
    private JPanel searchPanel;
    private JTextField listReviewsSearchField;
    private JButton searchButton;
    private JTable reviewsTable;
    private DefaultTableModel reviewsTableModel;
    // ======= Request
    JPanel requestReviewPanel;
    private JLabel requestReviewHeader;
    private JLabel bookLabel;
    private JComboBox booksComboBox;
    private JButton requestReviewButton;
    // ================ Profile Panel
    private JTabbedPane profilePanel;

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

        authorId = Main.getLoggedUser().getId();

        JPanel booksPanel = new JPanel();
        JPanel profilePanel = new JPanel(new BorderLayout());
        
        setupBooksPanel();
        setupReviewsPanel();
        setupProfilePanel();

        mainPanel = new JTabbedPane();
        mainPanel.add("Obras", booksPanel);
        mainPanel.add("Revisões", reviewsPanel);
        mainPanel.add("Perfil", profilePanel);

        this.container.add(mainPanel);

        new Profile(this, profilePanel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setupBooksPanel() {
        booksPanel = new JTabbedPane();
    }

    private void setupReviewsPanel() {
        listReviewsPanel = new JPanel(new BorderLayout());
        requestReviewPanel = new JPanel(new GridBagLayout());

        // ====================== List Reviews =========================== //
        searchPanel = new JPanel(new FlowLayout());
        listReviewsSearchField = Components.getTextField("Insira a pesquisa");
        searchButton = Components.getSecondaryButton("Pesquisar", "Pesquisar Revisões");
        searchButton.addActionListener(this);

        ManageReviews manageReviews = new ManageReviews();
        manageReviews.getReviewsByAuthor(authorId, "ASC", 1);
        reviewsTableModel = new DefaultTableModel(manageReviews.toAuthorReviewArray(), columnNames);
        reviewsTable = new JTable(reviewsTableModel);
        reviewsTable.setAutoCreateRowSorter(true);
        reviewsTable.setDefaultEditor(Object.class, null);

        listReviewsPanel.add(reviewsTable);

        JScrollPane reviewsTableScroll = new JScrollPane(reviewsTable);
        listReviewsPanel.add(reviewsTableScroll, BorderLayout.CENTER);

        searchPanel.add(listReviewsSearchField);
        searchPanel.add(searchButton);
        listReviewsPanel.add(searchPanel, BorderLayout.NORTH);

        // ====================== Request Review =========================== //
        requestReviewHeader = Components.getHeader("Pedir Revisão", Components.Alignment.CENTER);
        ManageBooks manageBooks = new ManageBooks();

        booksComboBox = Components.getComboBox("Selecione a livro para pedir revisão");
        manageBooks.getBooksByAuthor(authorId).forEach(book -> {
            booksComboBox.addItem(book.getTitle());
        });

        requestReviewButton = Components.getPrimaryButton("Pedir Revisão", "Pedir Revisão");
        requestReviewButton.addActionListener(this);
        bookLabel = Components.getLabel("Livro: ");

        GridBagConstraints constraints = new GridBagConstraints();

        requestReviewPanel.add(requestReviewHeader, constraints);
        constraints.gridy = 1;
        requestReviewPanel.add(bookLabel, constraints);
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        requestReviewPanel.add(booksComboBox, constraints);
        constraints.gridy = 3;
        constraints.insets = Components.getTopInsets(Components.Spacing.LARGE);
        requestReviewPanel.add(requestReviewButton, constraints);

        reviewsPanel = new JTabbedPane();
        reviewsPanel.add("Visualizar", listReviewsPanel);
        reviewsPanel.add("Inserir", requestReviewPanel);
    }

        
        
    private void setupProfilePanel() {
        profilePanel = new JTabbedPane();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(searchButton)) {
            String searchText = listReviewsSearchField.getText().toLowerCase();
            ArrayList<Review> filteredReviews = new ArrayList();

            ManageReviews manageReviews = new ManageReviews();
            manageReviews.getReviewsByAuthor(Main.getLoggedUser().getId(), "ASC", 1).forEach(review -> {
                if (review.getSubmissionDate().contains(searchText)
                        || review.getSerialNumber().toLowerCase().contains(searchText)
                        || review.getBook().getTitle().toLowerCase().contains(searchText)) {

                    filteredReviews.add(review);
                }
            });

            Object[][] newReviewsFields = new Object[filteredReviews.size()][4];

            for (int i = 0; i < filteredReviews.size(); i++) {
                Review review = filteredReviews.get(i);
                newReviewsFields[i] = new Object[]{review.getSubmissionDate(), review.getSerialNumber(), review.getBook().getTitle(), review.getStatus()};
            }

            this.reviewsTableModel.setDataVector(newReviewsFields, this.columnNames);
        }

        if (e.getSource().equals(requestReviewButton)) {

            ManageReviews manageReviews = new ManageReviews();
            Book book = new ManageBooks().getBooksByAuthor(authorId).get(booksComboBox.getSelectedIndex());

            boolean success = manageReviews.insertReview(book.getId(), authorId);

            if (success) {
                JOptionPane.showMessageDialog(this.container, "Pedido de revisão feito com sucesso");
                manageReviews.getReviewsByAuthor(authorId, "ASC", 1);
                this.reviewsTableModel.setDataVector(manageReviews.toAuthorReviewArray(), this.columnNames);
            }
        }
    }
}
