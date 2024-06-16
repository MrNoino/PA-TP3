package tp3.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import tp3.controller.ManageBooks;
import tp3.controller.ManageLiteraryStyles;
import tp3.model.Book;
import tp3.model.LiteraryStyle;

/**
 * A class that represents an author books screen 
 */
public class AuthorBooksScreen extends JFrame implements ActionListener, ListSelectionListener {

    private JFrame frame;
    private JTabbedPane booksTabbedPane;
    private JTextField searchField, addTitleField, addSubtitleField, addISBNField, addEditionField, addPublicationTypeField,
            updateTitleField, updateSubtitleField, updateISBNField, updateEditionField, updatePublicationTypeField;
    private JButton searchButton, addButton, updateButton;
    private JComboBox searchComboBox, addLiteraryStyleComboBox, updateLiteraryStyleComboBox;
    private JSpinner addPagesSpinner, addWordsSpinner, updatePagesSpinner, updateWordsSpinner;
    private JTable table;
    private DefaultTableModel tableModel;
    private String[] columnNames;
    private JPanel updatePanel;

    /**
     * Class constructor 
     * @param frame the frame used in the parent screen
     * @param booksTabbedPane the tabbed pane used in the parent screen
     */
    public AuthorBooksScreen(JFrame frame, JTabbedPane booksTabbedPane) {
        this.frame = frame;
        this.booksTabbedPane = booksTabbedPane;

        JPanel viewPanel = new JPanel(new BorderLayout());
        JPanel addPanel = new JPanel(new GridBagLayout());
        this.updatePanel = new JPanel(new GridBagLayout());
        
        this.addTitleField = Components.getTextField("Insira o título da obra");
        this.addSubtitleField = Components.getTextField("Insira o subtítulo da obra");
        this.addPagesSpinner = Components.getSpinner("Insira o número de páginas");
        this.addWordsSpinner = Components.getSpinner("Insira o número de palavras");
        this.addISBNField = Components.getTextField("Insira o ISBN da obra");
        this.addEditionField = Components.getTextField("Insira a edição da obra");
        this.addPublicationTypeField = Components.getTextField("Insira o tipo de publicação da obra");
        this.addLiteraryStyleComboBox = Components.getComboBox(new ManageLiteraryStyles().toArray(), "Escolha o estilo literário da obra");
        this.addButton = Components.getPrimaryButton("Inserir", "Inserir a obra");
        this.addButton.addActionListener(this);
        
        this.updateTitleField = Components.getTextField("Atualize o título da obra");
        this.updateSubtitleField = Components.getTextField("Atualize o subtítulo da obra");
        this.updatePagesSpinner = Components.getSpinner("Atualize o número de páginas");
        this.updateWordsSpinner = Components.getSpinner("Atualize o número de palavras");
        this.updateISBNField = Components.getTextField("Atualize o ISBN da obra");
        this.updateEditionField = Components.getTextField("Atualize a edição da obra");
        this.updatePublicationTypeField = Components.getTextField("Atualize o tipo de publicação da obra");
        this.updateLiteraryStyleComboBox = Components.getComboBox(new ManageLiteraryStyles().toArray(), "Atualize o estilo literário da obra");
        this.updateButton = Components.getPrimaryButton("Atualizar", "Atualizar a obra");
        this.updateButton.addActionListener(this);

        JPanel searchPanel = new JPanel(new FlowLayout());
        this.searchField = Components.getTextField(new Dimension(200, Components.getSpacing(Components.Spacing.MEDIUM) * 2), "Conteúdo a pesquisar");
        this.searchButton = Components.getSecondaryButton("Pesquisar", new Dimension(125, Components.getSpacing(Components.Spacing.MEDIUM) * 2), "Pesquisar obras");
        this.searchButton.addActionListener(this);
        this.searchComboBox = Components.getComboBox(new Object[]{"ISBN", "Data de Submissão"}, "Campo a pesquisar");
        searchPanel.add(this.searchField);
        searchPanel.add(this.searchComboBox);
        searchPanel.add(this.searchButton);

        this.columnNames = new String[]{"ID", "Título", "Subtítulo", "Nº Páginas", "Nº Palavras", "ISBN", "Edição", "Data de Submissão", "Data de Aprovação", "ID do Estilo Literário", "Tipo de Publicação"};
        ManageBooks manageBooks = new ManageBooks();
        manageBooks.getBooks(Main.getLoggedUser().getId());
        this.tableModel = new DefaultTableModel(manageBooks.toArray(), columnNames);
        this.table = new JTable(this.tableModel);
        this.table.setAutoCreateRowSorter(true);
        this.table.setDefaultEditor(Object.class, null);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = this.table.getSelectionModel();
        selectionModel.addListSelectionListener(this);
        if (this.table.getRowCount() > 0) {
            selectionModel.setSelectionInterval(0, 0);
        }
        JScrollPane tableScrollTable = new JScrollPane(this.table);

        viewPanel.add(searchPanel, BorderLayout.NORTH);
        viewPanel.add(tableScrollTable, BorderLayout.CENTER);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        addPanel.add(Components.getHeader("Inserir Obra", Components.Alignment.CENTER), constraints);

        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        addPanel.add(Components.getLabel("Título:"), constraints);

        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        addPanel.add(this.addTitleField, constraints);

        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        addPanel.add(Components.getLabel("Subtítulo:"), constraints);

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        addPanel.add(this.addSubtitleField, constraints);

        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        addPanel.add(Components.getLabel("Nº Páginas:"), constraints);

        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        addPanel.add(this.addPagesSpinner, constraints);

        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        addPanel.add(Components.getLabel("Nº Palavras:"), constraints);

        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        addPanel.add(this.addWordsSpinner, constraints);

        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        addPanel.add(Components.getLabel("ISBN:"), constraints);

        constraints.gridy = 10;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        addPanel.add(this.addISBNField, constraints);

        constraints.gridy = 11;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        addPanel.add(Components.getLabel("Edição:"), constraints);

        constraints.gridy = 12;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        addPanel.add(this.addEditionField, constraints);

        constraints.gridy = 13;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        addPanel.add(Components.getLabel("Tipo de Publicação:"), constraints);

        constraints.gridy = 14;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        addPanel.add(this.addPublicationTypeField, constraints);

        constraints.gridy = 15;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        addPanel.add(Components.getLabel("Estilo Literário:"), constraints);

        constraints.gridy = 16;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        addPanel.add(this.addLiteraryStyleComboBox, constraints);

        constraints.gridy = 17;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        addPanel.add(this.addButton, constraints);
        
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        this.updatePanel.add(Components.getHeader("Atualizar Obra", Components.Alignment.CENTER), constraints);

        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        this.updatePanel.add(Components.getLabel("Título:"), constraints);

        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        this.updatePanel.add(this.updateTitleField, constraints);

        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        this.updatePanel.add(Components.getLabel("Subtítulo:"), constraints);

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        this.updatePanel.add(this.updateSubtitleField, constraints);

        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        this.updatePanel.add(Components.getLabel("Nº Páginas:"), constraints);

        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        this.updatePanel.add(this.updatePagesSpinner, constraints);

        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        this.updatePanel.add(Components.getLabel("Nº Palavras:"), constraints);

        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        this.updatePanel.add(this.updateWordsSpinner, constraints);

        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        this.updatePanel.add(Components.getLabel("ISBN:"), constraints);

        constraints.gridy = 10;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        this.updatePanel.add(this.updateISBNField, constraints);

        constraints.gridy = 11;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        this.updatePanel.add(Components.getLabel("Edição:"), constraints);

        constraints.gridy = 12;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        this.updatePanel.add(this.updateEditionField, constraints);

        constraints.gridy = 13;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        this.updatePanel.add(Components.getLabel("Tipo de Publicação:"), constraints);

        constraints.gridy = 14;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        this.updatePanel.add(this.updatePublicationTypeField, constraints);

        constraints.gridy = 15;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        this.updatePanel.add(Components.getLabel("Estilo Literário:"), constraints);

        constraints.gridy = 16;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.updatePanel.add(this.updateLiteraryStyleComboBox, constraints);

        constraints.gridy = 17;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        this.updatePanel.add(this.updateButton, constraints);
        
        JScrollPane addScrollPane = new JScrollPane(addPanel);
        JScrollPane updateScrollPane = new JScrollPane(this.updatePanel);

        this.booksTabbedPane.add("Visualizar", viewPanel);
        this.booksTabbedPane.add("Inserir", addScrollPane);
        this.booksTabbedPane.add("Atualizar", updateScrollPane);
    }

    /**
     * Handles the click events
     * @param e the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.searchButton) {
            String item = (String) this.searchComboBox.getSelectedItem();
            ManageBooks manageBooks = new ManageBooks();
            switch (item) {
                case "ISBN":
                    manageBooks.getBooksByIsbn(Main.getLoggedUser().getId(), this.searchField.getText());
                    break;
                default:
                    if(this.searchField.getText().isEmpty()){
                        manageBooks.getBooks(Main.getLoggedUser().getId());
                        break;
                    }
                    String date = this.searchField.getText().replace("/", "-");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    dateFormat.setLenient(false);
                    try {
                        dateFormat.parse(date);
                    } catch (ParseException exception) {
                        JOptionPane.showMessageDialog(this.frame, "Data de submissão inválida", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                        return;
                    }
                    manageBooks.getBooksBySubmissionDate(Main.getLoggedUser().getId(), date);
            }
            this.tableModel.setDataVector(manageBooks.toArray(), this.columnNames);
            if (this.table.getRowCount() > 0) {
                this.table.getSelectionModel().setSelectionInterval(0, 0);
            }
        } else if (e.getSource() == this.addButton) {
            if(this.addTitleField.getText().isEmpty() || this.addISBNField.getText().isEmpty() || this.addPublicationTypeField.getText().isEmpty()){
                JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            ManageBooks manageBooks = new ManageBooks();
            if(manageBooks.existsTitle(this.addTitleField.getText())){
                JOptionPane.showMessageDialog(this.frame, "Título já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            if(manageBooks.existsIsbn(this.addISBNField.getText())){
                JOptionPane.showMessageDialog(this.frame, "ISBN já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            int pages, words;
            try {
                pages = (int) this.addPagesSpinner.getValue();
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this.frame, "O nº de páginas tem de ser número inteiro", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            try {
                words = (int) this.addWordsSpinner.getValue();
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this.frame, "O nº de palavras tem de ser número inteiro", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            boolean inserted = manageBooks.insertBook(new Book(
                    -1,
                    this.addTitleField.getText(),
                    (this.addSubtitleField.getText().isEmpty() ? null : this.addSubtitleField.getText()),
                    pages,
                    words,
                    this.addISBNField.getText(),
                    (this.addEditionField.getText().isEmpty() ? null : this.addEditionField.getText()),
                    new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                    null,
                    ((LiteraryStyle) this.addLiteraryStyleComboBox.getSelectedItem()).getId(),
                    this.addPublicationTypeField.getText(),
                    Main.getLoggedUser().getId()));
            if (inserted) {
                JOptionPane.showMessageDialog(this.frame, "Inserido com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);
                this.cleanAddForm();
            } else {
                JOptionPane.showMessageDialog(this.frame, "Não inserido", "Aviso", JOptionPane.INFORMATION_MESSAGE, null);
            }
        } else if (e.getSource() == this.updateButton) {
            if(this.updateTitleField.getText().isEmpty() || this.updateISBNField.getText().isEmpty() || this.updatePublicationTypeField.getText().isEmpty()){
                JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            ManageBooks manageBooks = new ManageBooks();
            Book book = manageBooks.getBookById(Main.getLoggedUser().getId(), (long) this.table.getValueAt(this.table.getSelectedRow(), 0));
            if(!book.getTitle().equals(this.updateTitleField.getText()) && manageBooks.existsTitle(this.updateTitleField.getText())){
                JOptionPane.showMessageDialog(this.frame, "Título já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            if(!book.getIsbn().equals(this.updateISBNField.getText()) && manageBooks.existsIsbn(this.updateISBNField.getText())){
                JOptionPane.showMessageDialog(this.frame, "ISBN já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            int pages, words;
            try {
                pages = (int) this.updatePagesSpinner.getValue();
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this.frame, "O nº de páginas tem de ser número inteiro", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            try {
                words = (int) this.updateWordsSpinner.getValue();
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this.frame, "O nº de palavras tem de ser número inteiro", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            boolean updated = manageBooks.updateBook(new Book(
                    (long) this.table.getValueAt(this.table.getSelectedRow(), 0),
                    this.updateTitleField.getText(),
                    (this.updateSubtitleField.getText().isEmpty() ? null : this.updateSubtitleField.getText()),
                    pages,
                    words,
                    this.updateISBNField.getText(),
                    (this.updateEditionField.getText().isEmpty() ? null : this.updateEditionField.getText()),
                    new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                    null,
                    ((LiteraryStyle) this.updateLiteraryStyleComboBox.getSelectedItem()).getId(),
                    this.updatePublicationTypeField.getText(),
                    Main.getLoggedUser().getId()));
            if (updated) {
                JOptionPane.showMessageDialog(this.frame, "Atualizado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);
                this.cleanAddForm();
            } else {
                JOptionPane.showMessageDialog(this.frame, "Não atualizado", "Aviso", JOptionPane.INFORMATION_MESSAGE, null);
            }
        }
    }

    /**
     * Handles the selection change on the table
     * @param e the selection event
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && this.table.getSelectedRowCount() > 0) {
            this.redesignUpdateBookPanel();
        }
    }
    
    /**
     * Clears all the fields in the add book form
     */
    private void cleanAddForm(){
        this.addTitleField.setText("");
        this.addSubtitleField.setText("");
        this.addPagesSpinner.setValue(0);
        this.addWordsSpinner.setValue(0);
        this.addISBNField.setText("");
        this.addEditionField.setText("");
        this.addPublicationTypeField.setText("");
        if(this.addLiteraryStyleComboBox.getItemCount()>0)
            this.addLiteraryStyleComboBox.setSelectedIndex(0);
    }
    
    /**
     * Sets the proper book information in the edit panel
     */
    private void redesignUpdateBookPanel(){
        Book book = new ManageBooks().getBookById(Main.getLoggedUser().getId(), (long) this.table.getValueAt(this.table.getSelectedRow(), 0));
        if(book != null){
            this.updateTitleField.setText(book.getTitle());
            this.updateSubtitleField.setText(book.getSubtitle());
            this.updatePagesSpinner.setValue(book.getPages());
            this.updateWordsSpinner.setValue(book.getWords());
            this.updateISBNField.setText(book.getIsbn());
            this.updateEditionField.setText(book.getEdition());
            this.updatePublicationTypeField.setText(book.getPublicationType());
            for(int i = 0; i < this.updateLiteraryStyleComboBox.getItemCount(); i++){
                if(((LiteraryStyle)this.updateLiteraryStyleComboBox.getItemAt(i)).getId() == book.getLiteraryStyleId()){
                    this.updateLiteraryStyleComboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
}
