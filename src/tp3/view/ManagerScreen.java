package tp3.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import tp3.controller.ManageLiteraryStyles;

public class ManagerScreen extends JFrame implements ActionListener, ItemListener {

    private Container container;
    private JFrame frame;
    private JTextField nameField, usernameField, emailField, nifField, phoneField, addressField, graduationField, specializationField;
    private JPasswordField passwordField;
    private JComboBox literacyStylesComboBox;
    private JButton addUserButton;
    ButtonGroup userRoleGroup;
    private JRadioButton managerRadio, authorRadio, reviewerRadio;
    private JPanel userRolePanel, addUserPanel;
    private JTable viewUsersTable;

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

        JTabbedPane mainTabbedPanel = new JTabbedPane();

        JTabbedPane usersTabbedPanel = new JTabbedPane();
        JTabbedPane reviewsTabbedPanel = new JTabbedPane();
        JTabbedPane licensesTabbedPanel = new JTabbedPane();
        JTabbedPane auditTabbedPanel = new JTabbedPane();
        JTabbedPane profileTabbedPanel = new JTabbedPane();

        this.addUserPanel = new JPanel(new GridBagLayout());
        JPanel updateUserPanel = new JPanel();
        JPanel viewUsersPanel = new JPanel(new BorderLayout());

        this.nameField = Components.getTextField("Insira o nome");
        this.usernameField = Components.getTextField("Insira o nome de utilizador");
        this.passwordField = Components.getPasswordField("Insira a palavra passe");
        this.emailField = Components.getTextField("Insira o email");

        this.managerRadio = new JRadioButton("Gestor", true);
        this.authorRadio = new JRadioButton("Autor", false);
        this.reviewerRadio = new JRadioButton("Revisor", false);

        this.managerRadio.addItemListener(this);
        this.authorRadio.addItemListener(this);
        this.reviewerRadio.addItemListener(this);

        this.userRoleGroup = new ButtonGroup();
        userRoleGroup.add(managerRadio);
        userRoleGroup.add(authorRadio);
        userRoleGroup.add(reviewerRadio);

        this.userRolePanel = new JPanel(new FlowLayout());
        this.userRolePanel.add(managerRadio);
        this.userRolePanel.add(authorRadio);
        this.userRolePanel.add(reviewerRadio);
        
        this.redesignAddUserPanel("Gestor");
        
        JScrollPane addUserScrollPanel = new JScrollPane(this.addUserPanel);
        this.addUserPanel.setAutoscrolls(true);
        
        JTextField viewUsersSearchField = Components.getTextField(new Dimension(250, Components.getSpacing(Components.Spacing.MEDIUM) *2),"Insira a pesquisa");
        JButton viewUsersSearchButton = Components.getSecondaryButton("Pesquisar", new Dimension(125, Components.getSpacing(Components.Spacing.MEDIUM) *2),"Pesquisar utilizadores");
        JPanel viewUsersSearchPanel = new JPanel(new FlowLayout());
        viewUsersSearchPanel.add(viewUsersSearchField);
        viewUsersSearchPanel.add(viewUsersSearchButton);

        String[] columnNames = new String[]{"ID", "Nome"};
        this.viewUsersTable = new JTable(new Object[][]{}, columnNames);
        JScrollPane viewUsersTableScroll = new JScrollPane(this.viewUsersTable);
        
        viewUsersPanel.add(viewUsersSearchPanel, BorderLayout.NORTH);
        viewUsersPanel.add(viewUsersTableScroll, BorderLayout.CENTER);

        usersTabbedPanel.add("Visualizar", viewUsersPanel);
        usersTabbedPanel.add("Inserir", addUserScrollPanel);
        usersTabbedPanel.add("Atualizar", updateUserPanel);

        mainTabbedPanel.add("Utilizadores", usersTabbedPanel);
        mainTabbedPanel.add("Pedidos de Revisão", reviewsTabbedPanel);
        mainTabbedPanel.add("Licenças", licensesTabbedPanel);
        mainTabbedPanel.add("Auditoria", auditTabbedPanel);
        mainTabbedPanel.add("Perfil", profileTabbedPanel);

        this.container.add(mainTabbedPanel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == 1) {
            if (e.getSource() == this.managerRadio) {
                this.redesignAddUserPanel(this.managerRadio.getText());
            } else if (e.getSource() == this.authorRadio) {
                this.redesignAddUserPanel(this.authorRadio.getText());
            } else if (e.getSource() == this.reviewerRadio) {
                this.redesignAddUserPanel(this.reviewerRadio.getText());
            }
        }
    }

    private void redesignAddUserPanel(String userRole) {
        this.addUserButton = Components.getPrimaryButton("Inserir", "Inserir o " + userRole);
        GridBagConstraints constraints = new GridBagConstraints();
        this.addUserPanel.removeAll();
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addUserPanel.add(Components.getHeader("Inserir " + userRole, Components.Alignment.CENTER), constraints);

        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        this.addUserPanel.add(Components.getLabel("Nome:"), constraints);

        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addUserPanel.add(this.nameField, constraints);

        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.WEST;
        this.addUserPanel.add(Components.getLabel("Nome de utilizador:"), constraints);

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addUserPanel.add(this.usernameField, constraints);

        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.WEST;
        this.addUserPanel.add(Components.getLabel("Palavra Passe:"), constraints);

        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addUserPanel.add(this.passwordField, constraints);

        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.WEST;
        this.addUserPanel.add(Components.getLabel("Email:"), constraints);

        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addUserPanel.add(this.emailField, constraints);

        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.WEST;
        this.addUserPanel.add(Components.getLabel("Tipo de Utilizador:"), constraints);

        constraints.gridy = 10;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addUserPanel.add(this.userRolePanel, constraints);

        int gridy = 10;
        if(!userRole.equals("Gestor")){
            this.nifField = Components.getTextField("Insira o NIF");
            this.phoneField = Components.getTextField("Insira o Telefone");
            this.addressField = Components.getTextField("Insira a morada");
            
            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.addUserPanel.add(Components.getLabel("NIF:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.addUserPanel.add(this.nifField, constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.addUserPanel.add(Components.getLabel("Telefone:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.addUserPanel.add(this.phoneField, constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.addUserPanel.add(Components.getLabel("Morada:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.addUserPanel.add(this.addressField, constraints);
                
            if(userRole.equals("Autor")){
                
                this.literacyStylesComboBox = Components.getComboBox(new ManageLiteraryStyles().toArray(), "Escolha o estilo literário");
                
                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.addUserPanel.add(Components.getLabel("Estilo Literário:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                this.addUserPanel.add(this.literacyStylesComboBox, constraints);

            }else if(userRole.equals("Revisor")){
                this.graduationField = Components.getTextField("Insira a graduação");
                this.specializationField = Components.getTextField("Insira a especialização");
                
                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.addUserPanel.add(Components.getLabel("Graduação:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                this.addUserPanel.add(this.graduationField, constraints);
                
                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.addUserPanel.add(Components.getLabel("Especialização:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                this.addUserPanel.add(this.specializationField, constraints);
                
            }
        }
        
        constraints.gridy = gridy+1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        this.addUserPanel.add(this.addUserButton, constraints);
        this.addUserPanel.revalidate();
        this.addUserPanel.repaint();
    }

}
