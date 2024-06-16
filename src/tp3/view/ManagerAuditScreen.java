package tp3.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import tp3.controller.ManageLogs;

public class ManagerAuditScreen extends JFrame implements  ActionListener{
    private JFrame frame;
    private JPanel auditPanel;
    private JTextField searchField;
    private JButton searchButton;
    private String[] columnNames;
    private DefaultTableModel tableModel;
    
    public ManagerAuditScreen(JFrame frame, JPanel auditPanel){
        this.frame = frame;
        this.auditPanel = auditPanel;
        
        this.searchField = Components.getTextField(new Dimension(250, Components.getSpacing(Components.Spacing.MEDIUM) * 2), "Pesquisar por id de utilizador");
        this.searchButton = Components.getSecondaryButton("Pesquisar", new Dimension(125, Components.getSpacing(Components.Spacing.MEDIUM) * 2), "Pesquisar logs");
        this.searchButton.addActionListener(this);
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        
        this.columnNames = new String[]{"ID", "ID do Utilizador", "Data e Hora", "Ação"};
        ManageLogs manageLogs = new ManageLogs();
        manageLogs.getLogs();
        this.tableModel = new DefaultTableModel(manageLogs.toArray(), this.columnNames);
        JTable logTable = new JTable(this.tableModel);
        logTable.setAutoCreateRowSorter(true);
        
        JScrollPane tableScroll = new JScrollPane(logTable);
        
        this.auditPanel.add(searchPanel, BorderLayout.NORTH);
        this.auditPanel.add(tableScroll, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.searchButton){
            ManageLogs manageLogs = new ManageLogs();
            if(this.searchField.getText().isEmpty()){
                manageLogs.getLogs();
            }else{
                try{
                    manageLogs.getLogsByUser(Long.parseLong(this.searchField.getText()));
                }catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(this.frame, "Valor inválido, introduza apenas números", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
            }
            this.tableModel.setDataVector(manageLogs.toArray(), this.columnNames);
        }
    }
}
