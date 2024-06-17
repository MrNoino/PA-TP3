package tp3.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import tp3.controller.ManageReviews;
import tp3.model.Review;

/**
 * A class that represents the manager review screen
 */
public class ManagerReviews extends JFrame implements ActionListener, ListSelectionListener {

    private JFrame frame;
    private JTabbedPane mainPanel;

    // =================== UI Components ======================== //
    // ========== List Tab
    private JPanel listReviewsPanel;
    private JPanel searchPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JTable reviewsTable;
    private DefaultTableModel reviewsTableModel;
    // ========== Assign Tab
    JPanel assignReviewPanel;

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
        "Id do Revisor",
        "Estado"
    };

    ArrayList<Review> reviews = new ArrayList();
    ArrayList<Review> tableReviews = new ArrayList();

    /**
     * Class constructor that builds the UI
     * @param frame the main frame from the parent screen
     * @param mainPanel the main panel from the parent screen
     */
    public ManagerReviews(JFrame frame, JTabbedPane mainPanel) {
        this.frame = frame;
        this.mainPanel = mainPanel;

        setupListReviewsTab();
        setupAssignReviewTab();
    }

    /**
     * Builds the list reviews tab UI
     */
    private void setupListReviewsTab() {
        listReviewsPanel = new JPanel(new BorderLayout());

        searchPanel = new JPanel(new FlowLayout());
        searchField = Components.getTextField("Pesquisar por revisões");
        searchButton = Components.getSecondaryButton("Pesquisar", new Dimension(125, Components.getSpacing(Components.Spacing.MEDIUM) * 2), "Pesquisar Revisões por codigo, observações e estado");
        searchButton.addActionListener(this);
        
        ManageReviews manageReviews = new ManageReviews();
        reviews = manageReviews.getReviews();

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        reviewsTableModel = new DefaultTableModel(manageReviews.toArray(), reviewsTableFields);
        reviewsTable = new JTable(reviewsTableModel);
        reviewsTable.setBackground(Components.BACKGROUND_COLOR);
        reviewsTable.setAutoCreateRowSorter(true);
        reviewsTable.setDefaultEditor(Object.class, null);
        reviewsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listReviewsPanel.add(searchPanel, BorderLayout.NORTH);
        listReviewsPanel.add(new JScrollPane(reviewsTable), BorderLayout.CENTER);

        mainPanel.add("Listar Revisões", listReviewsPanel);
    }

    /**
     * Builds the assign review tab UI
     */
    private void setupAssignReviewTab() {
        assignReviewPanel = new JPanel(new BorderLayout());

        assignReviewPanel.add(Components.getLabel("Funcionalidade Não Implementada"), BorderLayout.NORTH);

        mainPanel.add("Atribuir Revisor", assignReviewPanel);
    }

    private void search() {
        ArrayList<Review> filteredReviews = new ArrayList();
        String searchText = searchField.getText().toLowerCase();

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
     * Handles the click events
     *
     * @param e the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(searchButton)) {
            search();
        }
    }


    /**
     * Handles the table listening events
     *
     * @param e the event
     */    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && this.reviewsTable.getSelectedRowCount() > 0) {
            //Nao Implementado
        }
    }
}
