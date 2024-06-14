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
import tp3.controller.ManageLicenses;
import tp3.model.License;

public class ManagerLicensesScreen extends JFrame implements ActionListener, ListSelectionListener{

    private JFrame frame;
    private JTabbedPane licensesTabbedPanel;
    private DefaultTableModel tableModel;
    private JTable table;
    private String[] columnNames;
    private JTextField designationField, expireDateField, quantityField;
    private JButton refreshButton, addButton, updateButton;
    private JSpinner quantitySpinner;

    public ManagerLicensesScreen(JFrame frame, JTabbedPane licensesTabbedPanel) {
        this.frame = frame;
        this.licensesTabbedPanel = licensesTabbedPanel;

        JPanel viewPanel = new JPanel(new BorderLayout());
        JPanel addPanel = new JPanel(new GridBagLayout());
        JPanel updatePanel = new JPanel(new GridBagLayout());

        this.columnNames = new String[]{"ID", "Designação", "Data de Expiração", "Quantidade"};
        ManageLicenses manageLicenses = new ManageLicenses();
        manageLicenses.getLicenses();
        this.tableModel = new DefaultTableModel(manageLicenses.toArray(), columnNames);
        this.table = new JTable(this.tableModel);
        this.table.setAutoCreateRowSorter(true);
        this.quantitySpinner = Components.getSpinner("Quantidade de licenças");
        this.table.setDefaultEditor(Object.class, null);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = this.table.getSelectionModel();
        selectionModel.addListSelectionListener(this);
        if (this.table.getRowCount() > 0) {
            selectionModel.setSelectionInterval(0, 0);
        }
        JScrollPane tableScrollTable = new JScrollPane(this.table);
        
        this.refreshButton = Components.getSecondaryButton("Atualizar", new Dimension(125, Components.getSpacing(Components.Spacing.MEDIUM) *2), "Atualizar o conteúdo da tabela");
        this.refreshButton.addActionListener(this);
        JPanel refreshPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        refreshPanel.add(this.refreshButton);
        viewPanel.add(tableScrollTable, BorderLayout.CENTER);
        viewPanel.add(refreshPanel, BorderLayout.SOUTH);

        this.designationField = Components.getTextField("Insira a designação da licença");
        this.expireDateField = Components.getTextField("Insira a data de expiração da licensa");
        this.quantityField = Components.getTextField("Insira a quantidade de linceças");
        this.addButton = Components.getPrimaryButton("Inserir", "Inserir a licença");
        this.addButton.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        addPanel.add(Components.getHeader("Inserir Licença", Components.Alignment.CENTER), constraints);

        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        addPanel.add(Components.getLabel("Designação:"), constraints);

        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        addPanel.add(this.designationField, constraints);

        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        addPanel.add(Components.getLabel("Data de Expiração:"), constraints);

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        addPanel.add(this.expireDateField, constraints);

        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        addPanel.add(Components.getLabel("Quantidade:"), constraints);

        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        addPanel.add(this.quantityField, constraints);

        constraints.gridy = 7;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        addPanel.add(this.addButton, constraints);
        
        this.updateButton = Components.getPrimaryButton("Atualizar", "Atualizar a quantidade de licenças");
        this.updateButton.addActionListener(this);
        
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        updatePanel.add(Components.getHeader("Atualizar Licença", Components.Alignment.CENTER), constraints);

        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        updatePanel.add(Components.getLabel("Quantidade:"), constraints);

        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        updatePanel.add(this.quantitySpinner, constraints);
        
        constraints.gridy = 3;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        updatePanel.add(this.updateButton, constraints);

        this.licensesTabbedPanel.add("Visualizar", viewPanel);
        this.licensesTabbedPanel.add("Inserir", addPanel);
        this.licensesTabbedPanel.add("Atualizar", updatePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.refreshButton){
            ManageLicenses manageLicenses = new ManageLicenses();
            manageLicenses.getLicenses();
            this.tableModel .setDataVector(manageLicenses.toArray(), columnNames);
            if(this.table.getRowCount() > 0){
                this.table.getSelectionModel().setSelectionInterval(0, 0);
            }
        }else if (e.getSource() == this.addButton) {
            if (this.designationField.getText().isEmpty() || this.expireDateField.getText().isEmpty() || this.quantityField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            int quantity;
            try {
                quantity = Integer.parseInt(this.quantityField.getText());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this.frame, "Quantidade tem de ser um número inteiro", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            String date = this.expireDateField.getText().replace("/", "-");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
            } catch (ParseException exception) {
                JOptionPane.showMessageDialog(this.frame, "Data de expiração inválida", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            
            boolean inserted = new ManageLicenses().insertLicense(new License(-1,
                    this.designationField.getText(),
                    date,
                    quantity));
            if(inserted){
                JOptionPane.showMessageDialog(this.frame, "Inserido com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);
            }else{
                JOptionPane.showMessageDialog(this.frame, "Não inserido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
            }
        }else if(e.getSource() == this.updateButton){
            if(this.table.getSelectedRowCount() != 1){
                JOptionPane.showMessageDialog(this.frame, "Tem de selecionar uma licença da tabela", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            int quantity;
            try{
                quantity = (int) this.quantitySpinner.getValue();
            }catch(NumberFormatException exception){
                JOptionPane.showMessageDialog(this.frame, "Quantidade tem de ser um número inteiro", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            boolean updated = new ManageLicenses().updateLicenseQuantity((int)this.table.getValueAt(this.table.getSelectedRow(), 0), quantity);
            if(updated){
                JOptionPane.showMessageDialog(this.frame, "Atualizado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);
            }else{
                JOptionPane.showMessageDialog(this.frame, "Não atualizado", "Aviso", JOptionPane.ERROR_MESSAGE, null);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && this.table.getSelectedRowCount() > 0) {
            ManageLicenses manageLicenses = new ManageLicenses();
            License license = manageLicenses.getLicense((int)this.table.getValueAt(this.table.getSelectedRow(), 0));
            if(license != null){
                this.quantitySpinner.setValue(license.getQuantity());
            }
        }
    }

}
