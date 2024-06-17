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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import tp3.controller.ManageReviews;
import tp3.model.Review;

/**
 * A class that represents a reviewer screen
 */
public class ReviewerScreen extends JFrame implements ActionListener, ListSelectionListener {

    private Container container;
    private JFrame frame;

    private final String[] reviewsTableFields = new String[]{
        "Id",
        "Código",
        "Número de Série",
        "Data de Submissão",
        "Data de Aprovação",
        "Data de Completion",
        "Tempo Decorrido",
        "Observações",
        "Custo",
        "Id do Livro",
        "Id do Autor",
        "Id do Gestor",
        "Estado"
    };

    ArrayList<Review> reviews;
    ArrayList<Review> tableReviews;

    // ==================== UI Components ============================ //
    private JTabbedPane mainPanel;
    // =============== Reviews Tab
    private JPanel reviewsPanel;
    private JPanel searchPanel;
    private JTextField reviewsSearchField;
    private JButton searchButton;
    private JTable reviewsTable;
    private DefaultTableModel reviewsTableModel;
    // =============== Review Book Tab
    private JPanel reviewBooksPanel;
    private JLabel reviewHeader;
    private JLabel reviewObservationsLabel;
    private JTextArea reviewObservationsField;
    private JLabel reviewCostLabel;
    private JTextField reviewCostField;
    private JLabel reviewStatusLabel;
    private JComboBox reviewStatusComboBox;
    private JButton reviewSaveButton;
    private JButton rejectButton;
    // =============== Profile Tab
    private JPanel profilePanel;

    /**
     * Class constructor that builds the UI
     */
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
        mainPanel.setBackground(Components.BACKGROUND_COLOR);
        GridBagConstraints constraints = new GridBagConstraints();

        setupReviewBookTab();
        setupReviewsTab();
        setupProfileTab();

        mainPanel.add("Pedidos de Revisão", reviewsPanel);
        mainPanel.add("Rever Obra", reviewBooksPanel);
        mainPanel.add("Perfil", profilePanel);

        this.container.add(mainPanel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Builds the reviews tab UI
     */
    private void setupReviewsTab() {
        reviewsPanel = new JPanel(new BorderLayout());

        searchPanel = new JPanel(new FlowLayout());
        reviewsSearchField = Components.getTextField("Insira a pesquisa");
        searchButton = Components.getSecondaryButton("Pesquisar", new Dimension(125, Components.getSpacing(Components.Spacing.MEDIUM) * 2), "Pesquisar Revisões por codigo, observações e estado");
        searchButton.addActionListener(this);

        ManageReviews manageReviews = new ManageReviews();
        reviews = manageReviews.getReviewerReviews(Main.getLoggedUser().getId());
        tableReviews = reviews;
        reviewsTableModel = new DefaultTableModel(manageReviews.toReviewerReviewsArray(), reviewsTableFields);
        reviewsTable = new JTable(reviewsTableModel);
        reviewsTable.setBackground(Components.BACKGROUND_COLOR);
        reviewsTable.setAutoCreateRowSorter(true);
        reviewsTable.setDefaultEditor(Object.class, null);
        reviewsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = reviewsTable.getSelectionModel();
        selectionModel.addListSelectionListener(this);
        if (reviewsTable.getRowCount() > 0) {
            selectionModel.setSelectionInterval(0, 0);
        }
        searchPanel.add(reviewsSearchField);
        searchPanel.add(searchButton);
        reviewsPanel.add(searchPanel, BorderLayout.NORTH);
        reviewsPanel.add(new JScrollPane(reviewsTable), BorderLayout.CENTER);
    }

    /**
     * Builds the review book tab UI
     */
    private void setupReviewBookTab() {
        reviewBooksPanel = new JPanel(new GridBagLayout());
        reviewBooksPanel.setBackground(Components.BACKGROUND_COLOR);

        GridBagConstraints constraints = new GridBagConstraints();

        reviewHeader = Components.getHeader("Revisão", Components.Alignment.CENTER);
        reviewObservationsLabel = Components.getLabel("Observações:");
        reviewObservationsField = Components.getTextArea("Observações da revisão");
        reviewCostLabel = Components.getLabel("Custo:");
        reviewCostField = Components.getTextField("Custo da revisão");
        reviewSaveButton = Components.getPrimaryButton("Guardar", "Guardar as mudanças na revisão");
        reviewSaveButton.addActionListener(this);
        reviewStatusLabel = Components.getLabel("Estado:");
        reviewStatusComboBox = Components.getComboBox(new Object[]{"Iniciada", "Aceite", "A Decorrer", "Finalizada", "Arquivada"}, "");
        rejectButton = Components.getPrimaryButton("Rejeitar Revisão", "Rejeitar revisao", Components.DANGER_COLOR, Components.ON_ACCENT_COLOR);
        rejectButton.addActionListener(this);

        reviewBooksPanel.add(reviewHeader, constraints);
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridy = 2;
        reviewBooksPanel.add(reviewObservationsLabel, constraints);
        constraints.gridy = 3;
        reviewBooksPanel.add(reviewObservationsField, constraints);
        constraints.gridy = 4;
        reviewBooksPanel.add(reviewCostLabel, constraints);
        constraints.gridy = 5;
        reviewBooksPanel.add(reviewCostField, constraints);
        constraints.gridy = 6;
        reviewBooksPanel.add(reviewStatusLabel, constraints);
        constraints.gridy = 7;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        reviewBooksPanel.add(reviewStatusComboBox, constraints);
        constraints.gridy = 8;
        constraints.insets = Components.getTopInsets(Components.Spacing.LARGE);
        reviewBooksPanel.add(reviewSaveButton, constraints);
    }

    /**
     * Builds the profile screen UI
     */
    private void setupProfileTab() {
        profilePanel = new JPanel(new BorderLayout());
        new ProfileScreen(this, profilePanel);
    }

    /**
     * Handles the click events
     *
     * @param e the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(searchButton)) {
            search();
        }

        if (e.getSource().equals(reviewSaveButton)) {

            String status;

            switch (reviewStatusComboBox.getSelectedIndex()) {
                case 0 ->
                    status = "iniciada";
                case 1 ->
                    status = "aceite";
                case 2 ->
                    status = "a decorrer";
                case 3 ->
                    status = "finalizada";
                case 4 ->
                    status = "arquivada";
                default ->
                    throw new AssertionError();
            }

            boolean success = new ManageReviews().updateReview(getSelectedReview().getId(), reviewObservationsField.getText(), Float.parseFloat(reviewCostField.getText()), status);

            if (success) {
                JOptionPane.showMessageDialog(this, "Atualizado com sucesso");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar revisão");
            }

            reviews = new ManageReviews().getReviewerReviews(Main.getLoggedUser().getId());
            search();
        }
    }

    /**
     * Filters the table according to the search text
     */
    private void search() {
        ArrayList<Review> filteredReviews = new ArrayList();
        String searchText = reviewsSearchField.getText().toLowerCase();

        reviews.forEach(review -> {

            String observations;
            if (review.getObservations() == null) {
                observations = "";
            } else {
                observations = review.getObservations();
            }

            if (Integer.toString(review.getRandomCode()).contains(searchText)
                    || observations.toLowerCase().contains(searchText)
                    || review.getStatus().toLowerCase().contains(searchText)) {

                filteredReviews.add(review);
            }
        });

        tableReviews = filteredReviews;

        Object[][] newTableObjects = new Object[filteredReviews.size()][13];

        for (int i = 0; i < filteredReviews.size(); i++) {
            newTableObjects[i] = filteredReviews.get(i).toReviewerReviewsArray();
        }

        reviewsTableModel.setDataVector(newTableObjects, reviewsTableFields);
    }

    /**
     * Gets the currently selected review on the table
     * @return a review
     */
    private Review getSelectedReview() {
        ManageReviews manageReviews = new ManageReviews();
        ArrayList<Review> reviews = manageReviews.getReviewerReviews(Main.getLoggedUser().getId());
        return reviews.get(reviewsTable.getSelectedRow());
    }

    /**
     * Handles the selection change on the table
     * @param e the event
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && this.reviewsTable.getSelectedRowCount() > 0) {

            Review review = getSelectedReview();

            reviewHeader.setText("Revisão (" + review.getId() + ")");
            reviewObservationsField.setText(review.getObservations());
            reviewCostField.setText(review.getCost() + "");

            switch (review.getStatus()) {
                case "iniciada" ->
                    reviewStatusComboBox.setSelectedIndex(0);
                case "aceite" ->
                    reviewStatusComboBox.setSelectedIndex(1);
                case "a decorrer" ->
                    reviewStatusComboBox.setSelectedIndex(2);
                case "finalizada" ->
                    reviewStatusComboBox.setSelectedIndex(3);
                case "arquivada" ->
                    reviewStatusComboBox.setSelectedIndex(4);
                default ->
                    throw new AssertionError();
            }
        }
    }
}
