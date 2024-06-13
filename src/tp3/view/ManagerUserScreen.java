package tp3.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import tp3.controller.ManageAuthors;
import tp3.controller.ManageLiteraryStyles;
import tp3.controller.ManageManagers;
import tp3.controller.ManageReviewers;
import tp3.controller.ManageUsers;
import tp3.model.Author;
import tp3.model.LiteraryStyle;
import tp3.model.Manager;
import tp3.model.Reviewer;
import tp3.model.User;

public class ManagerUserScreen extends JFrame implements ActionListener, ItemListener, ListSelectionListener {

    private JFrame frame;
    private JTabbedPane usersTabbedPanel;
    private JTextField addUserNameField, addUserUsernameField, addUserEmailField, addUserNifField, addUserPhoneField, addUserAddressField, addUserGraduationField, addUserSpecializationField, viewUsersSearchField,
            updateUserNameField, updateUserUsernameField, updateUserEmailField, updateUserNifField, updateUserPhoneField, updateUserAddressField, updateUserGraduationField, updateUserSpecializationField;
    private JPasswordField addUserPasswordField, updateUserPasswordField;
    private JComboBox addUserLiteracyStylesComboBox, viewUsersSearchCombobox, updateUserLiteracyStylesComboBox;
    private JButton addUserButton, viewUsersSearchButton, updateUserButton, updateUserStatusButton, updateUserDeletedButton;
    ButtonGroup userRoleGroup;
    private JRadioButton managerRadio, authorRadio, reviewerRadio;
    private JPanel userRolePanel, addUserPanel, updateUserPanel;
    private JTable viewUsersTable;
    private String[] columnNames;
    private DefaultTableModel viewUserTableModel;

    public ManagerUserScreen(JFrame frame, JTabbedPane usersTabbedPanel) {
        this.frame = frame;
        this.usersTabbedPanel = usersTabbedPanel;
        this.addUserPanel = new JPanel(new GridBagLayout());
        this.updateUserPanel = new JPanel(new GridBagLayout());
        JPanel viewUsersPanel = new JPanel(new BorderLayout());

        this.addUserNameField = Components.getTextField("Insira o nome");
        this.addUserUsernameField = Components.getTextField("Insira o nome de utilizador");
        this.addUserPasswordField = Components.getPasswordField("Insira a palavra passe");
        this.addUserEmailField = Components.getTextField("Insira o email");

        this.managerRadio = new JRadioButton("Gestor", true);
        this.authorRadio = new JRadioButton("Autor", false);
        this.reviewerRadio = new JRadioButton("Revisor", false);

        this.managerRadio.addItemListener(this);
        this.authorRadio.addItemListener(this);
        this.reviewerRadio.addItemListener(this);

        this.userRoleGroup = new ButtonGroup();
        this.userRoleGroup.add(this.managerRadio);
        this.userRoleGroup.add(this.authorRadio);
        this.userRoleGroup.add(this.reviewerRadio);

        this.userRolePanel = new JPanel(new FlowLayout());
        this.userRolePanel.add(this.managerRadio);
        this.userRolePanel.add(this.authorRadio);
        this.userRolePanel.add(this.reviewerRadio);

        this.redesignAddUserPanel("Gestor");

        JScrollPane addUserScrollPanel = new JScrollPane(this.addUserPanel);
        this.addUserPanel.setAutoscrolls(true);

        this.viewUsersSearchField = Components.getTextField(new Dimension(250, Components.getSpacing(Components.Spacing.MEDIUM) * 2), "Insira a pesquisa");
        this.viewUsersSearchCombobox = Components.getComboBox(new String[]{"Nome", "Nome de Utilizador", "Tipo de Utilizador"}, "Escolha o campo a pesquisar");
        this.viewUsersSearchButton = Components.getSecondaryButton("Pesquisar", new Dimension(125, Components.getSpacing(Components.Spacing.MEDIUM) * 2), "Pesquisar utilizadores");
        this.viewUsersSearchButton.addActionListener(this);
        JPanel viewUsersSearchPanel = new JPanel(new FlowLayout());
        viewUsersSearchPanel.add(viewUsersSearchField);
        viewUsersSearchPanel.add(viewUsersSearchCombobox);
        viewUsersSearchPanel.add(viewUsersSearchButton);

        columnNames = new String[]{"ID", "Nome", "Nome de Utilizador", "Email", "Ativo", "Eliminado", "ID do Tipo"};
        ManageUsers manageUsers = new ManageUsers();
        manageUsers.getUsers();
        this.viewUserTableModel = new DefaultTableModel(manageUsers.toArray(), this.columnNames);
        this.viewUsersTable = new JTable(this.viewUserTableModel);
        this.viewUsersTable.setAutoCreateRowSorter(true);
        this.viewUsersTable.setDefaultEditor(Object.class, null);
        this.viewUsersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = this.viewUsersTable.getSelectionModel();
        if (this.viewUsersTable.getRowCount() > 0) {
            selectionModel.setSelectionInterval(0, 0);
            valueChanged(null);
        }
        selectionModel.addListSelectionListener(this);
        JScrollPane viewUsersTableScroll = new JScrollPane(this.viewUsersTable);

        viewUsersPanel.add(viewUsersSearchPanel, BorderLayout.NORTH);
        viewUsersPanel.add(viewUsersTableScroll, BorderLayout.CENTER);

        usersTabbedPanel.add("Visualizar", viewUsersPanel);
        usersTabbedPanel.add("Inserir", addUserScrollPanel);
        usersTabbedPanel.add("Atualizar", updateUserPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewUsersSearchButton) {
            String item = (String) this.viewUsersSearchCombobox.getSelectedItem();
            ManageUsers manageUsers = new ManageUsers();
            switch (item) {
                case "Nome":
                    manageUsers.getUsersByName(this.viewUsersSearchField.getText());
                    break;
                case "Nome de Utilizador":
                    manageUsers.getUsersByUsername(this.viewUsersSearchField.getText());
                    break;

                case "Tipo de Utilizador":
                    manageUsers.getUsersByRole(this.viewUsersSearchField.getText());
                    break;
                default:
                    return;
            }
            Object[][] users = manageUsers.toArray();
            this.viewUserTableModel.setDataVector(users, this.columnNames);
            if (this.viewUsersTable.getRowCount() > 0) {
                this.viewUsersTable.getSelectionModel().setSelectionInterval(0, 0);
            }
        } else if (e.getSource() == this.addUserButton) {
            if (this.addUserNameField.getText().isEmpty() || this.addUserUsernameField.getText().isEmpty() || this.addUserPasswordField.getPassword().length == 0 || this.addUserEmailField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            boolean inserted = false;
            if (this.managerRadio.isSelected()) {
                inserted = new ManageManagers().insertManager(new Manager(-1,
                        this.addUserNameField.getText(),
                        this.addUserUsernameField.getText(),
                        new String(this.addUserPasswordField.getPassword()),
                        this.addUserEmailField.getText(),
                        true,
                        false,
                        1));
            } else {

                if (this.addUserNifField.getText().isEmpty() || this.addUserPhoneField.getText().isEmpty() || this.addUserAddressField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }

                if (this.authorRadio.isSelected()) {
                    inserted = new ManageAuthors().insertAuthor(new Author(-1,
                            this.addUserNameField.getText(),
                            this.addUserUsernameField.getText(),
                            new String(this.addUserPasswordField.getPassword()),
                            this.addUserEmailField.getText(),
                            true,
                            false,
                            3,
                            this.addUserNifField.getText(),
                            this.addUserPhoneField.getText(),
                            this.addUserAddressField.getText(),
                            new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                            1));
                } else {
                    if (this.addUserGraduationField.getText().isEmpty() || this.addUserSpecializationField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                        return;
                    }
                    inserted = new ManageReviewers().insertReviewer(new Reviewer(-1,
                            this.addUserNameField.getText(),
                            this.addUserUsernameField.getText(),
                            new String(this.addUserPasswordField.getPassword()),
                            this.addUserEmailField.getText(),
                            true,
                            false,
                            2,
                            this.addUserNifField.getText(),
                            this.addUserPhoneField.getText(),
                            this.addUserAddressField.getText(),
                            this.addUserGraduationField.getText(),
                            this.addUserSpecializationField.getText()));
                }
            }
            if (inserted) {
                JOptionPane.showMessageDialog(this.frame, "Inserido com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);
                this.addUserNameField.setText("");
                this.addUserUsernameField.setText("");
                this.addUserPasswordField.setText("");
                this.addUserEmailField.setText("");
                this.addUserNifField.setText("");
                this.addUserPhoneField.setText("");
                this.addUserAddressField.setText("");
                this.addUserGraduationField.setText("");
                this.addUserSpecializationField.setText("");
                if (this.addUserLiteracyStylesComboBox != null) {
                    this.addUserLiteracyStylesComboBox.setSelectedIndex(0);
                }
            } else {
                JOptionPane.showMessageDialog(this.frame, "Não inserido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
            }
        }else if (e.getSource() == this.updateUserButton) {
            if (this.viewUsersTable.getSelectedRowCount() != 1) {
                JOptionPane.showMessageDialog(this.frame, "Deve ter um registo selecionado na tabela de visualização", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            long id = (long) this.viewUsersTable.getValueAt(this.viewUsersTable.getSelectedRow(), 0);
            int roleId = (int) this.viewUsersTable.getValueAt(this.viewUsersTable.getSelectedRow(), 6);
            boolean updated = false;
            switch (roleId) {
                case 1:
                    updated = new ManageManagers().updateManager(new Manager(
                            id,
                            this.updateUserNameField.getText(),
                            this.updateUserUsernameField.getText(),
                            new String(this.updateUserPasswordField.getPassword()),
                            this.updateUserEmailField.getText(),
                            true,
                            false,
                            roleId));
                    break;
                case 2:
                    updated = new ManageReviewers().updateReviewer(new Reviewer(
                            id,
                            this.updateUserNameField.getText(),
                            this.updateUserUsernameField.getText(),
                            new String(this.updateUserPasswordField.getPassword()),
                            this.updateUserEmailField.getText(),
                            true,
                            false,
                            roleId,
                            this.updateUserNifField.getText(),
                            this.updateUserPhoneField.getText(),
                            this.updateUserAddressField.getText(),
                            this.updateUserGraduationField.getText(),
                            this.updateUserSpecializationField.getText()));
                    break;
                default:
                    updated = new ManageAuthors().updateAuthor(new Author(
                            id,
                            this.updateUserNameField.getText(),
                            this.updateUserUsernameField.getText(),
                            new String(this.updateUserPasswordField.getPassword()),
                            this.updateUserEmailField.getText(),
                            true,
                            false,
                            roleId,
                            this.updateUserNifField.getText(),
                            this.updateUserPhoneField.getText(),
                            this.updateUserAddressField.getText(),
                            new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                            ((LiteraryStyle) this.updateUserLiteracyStylesComboBox.getSelectedItem()).getId()));
                    break;
            }

            if (updated) {
                JOptionPane.showMessageDialog(this.frame, "Atualizado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);
            } else {
                JOptionPane.showMessageDialog(this.frame, "Não atualizado", "Aviso", JOptionPane.ERROR_MESSAGE, null);
            }

        }else if(e.getSource() == this.updateUserStatusButton){
            if (this.viewUsersTable.getSelectedRowCount() != 1) {
                JOptionPane.showMessageDialog(this.frame, "Deve ter um registo selecionado na tabela de visualização", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            long id = (long) this.viewUsersTable.getValueAt(this.viewUsersTable.getSelectedRow(), 0);
            ManageUsers manageUsers = new ManageUsers();
            User user = manageUsers.getUser(id);
            if(user != null){
                if(manageUsers.updateUserStatus(id, !user.isActive())){
                    JOptionPane.showMessageDialog(this.frame, "Estado atualizado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);
                }else{
                    JOptionPane.showMessageDialog(this.frame, "Erro ao atualizar o estado", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                }
            }else{
                JOptionPane.showMessageDialog(this.frame, "Não foi possível obter o estado do utilizador", "Aviso", JOptionPane.ERROR_MESSAGE, null);
            }
        }else if(e.getSource() == this.updateUserDeletedButton){
            if (this.viewUsersTable.getSelectedRowCount() != 1) {
                JOptionPane.showMessageDialog(this.frame, "Deve ter um registo selecionado na tabela de visualização", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            
            long id = (long) this.viewUsersTable.getValueAt(this.viewUsersTable.getSelectedRow(), 0);
            ManageUsers manageUsers = new ManageUsers();
            if(manageUsers.deleteUser(id)){
                JOptionPane.showMessageDialog(this.frame, "Eliminado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);
            }else{
                JOptionPane.showMessageDialog(this.frame, "Erro ao eliminar conta", "Aviso", JOptionPane.ERROR_MESSAGE, null);
            }
            
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
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
        this.addUserButton.addActionListener(this);
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
        this.addUserPanel.add(this.addUserNameField, constraints);

        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.WEST;
        this.addUserPanel.add(Components.getLabel("Nome de utilizador:"), constraints);

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addUserPanel.add(this.addUserUsernameField, constraints);

        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.WEST;
        this.addUserPanel.add(Components.getLabel("Palavra Passe:"), constraints);

        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addUserPanel.add(this.addUserPasswordField, constraints);

        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.WEST;
        this.addUserPanel.add(Components.getLabel("Email:"), constraints);

        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addUserPanel.add(this.addUserEmailField, constraints);

        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.WEST;
        this.addUserPanel.add(Components.getLabel("Tipo de Utilizador:"), constraints);

        constraints.gridy = 10;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addUserPanel.add(this.userRolePanel, constraints);

        int gridy = 10;
        if (!userRole.equals("Gestor")) {
            this.addUserNifField = Components.getTextField("Insira o NIF");
            this.addUserPhoneField = Components.getTextField("Insira o Telefone");
            this.addUserAddressField = Components.getTextField("Insira a morada");

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.addUserPanel.add(Components.getLabel("NIF:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.addUserPanel.add(this.addUserNifField, constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.addUserPanel.add(Components.getLabel("Telefone:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.addUserPanel.add(this.addUserPhoneField, constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.addUserPanel.add(Components.getLabel("Morada:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.addUserPanel.add(this.addUserAddressField, constraints);

            if (userRole.equals("Autor")) {

                this.addUserLiteracyStylesComboBox = Components.getComboBox(new ManageLiteraryStyles().toArray(), "Escolha o estilo literário");

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.addUserPanel.add(Components.getLabel("Estilo Literário:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                this.addUserPanel.add(this.addUserLiteracyStylesComboBox, constraints);

            } else if (userRole.equals("Revisor")) {
                this.addUserGraduationField = Components.getTextField("Insira a graduação");
                this.addUserSpecializationField = Components.getTextField("Insira a especialização");

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.addUserPanel.add(Components.getLabel("Graduação:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                this.addUserPanel.add(this.addUserGraduationField, constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.addUserPanel.add(Components.getLabel("Especialização:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                this.addUserPanel.add(this.addUserSpecializationField, constraints);

            }
        }

        constraints.gridy = gridy + 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        this.addUserPanel.add(this.addUserButton, constraints);
        this.addUserPanel.revalidate();
        this.addUserPanel.repaint();
    }

    private void redesignUpdateUserPanel() {
        String userRole;
        int roleId = (int) this.viewUsersTable.getValueAt(this.viewUsersTable.getSelectedRow(), 6);
        switch (roleId) {
            case 1:
                userRole = "Gestor";
                break;
            case 2:
                userRole = "Revisor";
                break;
            default:
                userRole = "Autor";
                break;
        }

        this.updateUserPanel.removeAll();

        this.updateUserNameField = Components.getTextField("Atualize o nome");
        this.updateUserUsernameField = Components.getTextField("Atualize o nome de utilizador");
        this.updateUserPasswordField = Components.getPasswordField("Atualize a palavra passe");
        this.updateUserEmailField = Components.getTextField("Atualize o email");
        this.updateUserButton = Components.getPrimaryButton("Atualizar", "Atualizar o Utilizador");
        this.updateUserButton.addActionListener(this);
        this.updateUserStatusButton = Components.getPrimaryButton("Ativar/Desativar Conta", "Ativar/Desativar conta de utilizador", Components.WARNING_COLOR, Components.ON_ACCENT_COLOR);
        this.updateUserStatusButton.addActionListener(this);
        this.updateUserDeletedButton = Components.getPrimaryButton("Eliminar Conta", "Eliminar conta de utilizador", Components.DANGER_COLOR, Components.ON_ACCENT_COLOR);
        this.updateUserDeletedButton.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        this.updateUserPanel.removeAll();
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        this.updateUserPanel.add(Components.getHeader("Atualizar " + userRole, Components.Alignment.CENTER), constraints);

        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        this.updateUserPanel.add(Components.getLabel("Nome:"), constraints);

        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        this.updateUserPanel.add(this.updateUserNameField, constraints);

        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.WEST;
        this.updateUserPanel.add(Components.getLabel("Nome de utilizador:"), constraints);

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        this.updateUserPanel.add(this.updateUserUsernameField, constraints);

        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.WEST;
        this.updateUserPanel.add(Components.getLabel("Palavra Passe:"), constraints);

        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        this.updateUserPanel.add(this.updateUserPasswordField, constraints);

        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.WEST;
        this.updateUserPanel.add(Components.getLabel("Email:"), constraints);

        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.CENTER;
        this.updateUserPanel.add(this.updateUserEmailField, constraints);

        int gridy = 9;
        if (!userRole.equals("Gestor")) {
            this.updateUserNifField = Components.getTextField("Atualize o NIF");
            this.updateUserPhoneField = Components.getTextField("Atualize o Telefone");
            this.updateUserAddressField = Components.getTextField("Atualize a morada");

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.updateUserPanel.add(Components.getLabel("NIF:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.updateUserPanel.add(this.updateUserNifField, constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.updateUserPanel.add(Components.getLabel("Telefone:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.updateUserPanel.add(this.updateUserPhoneField, constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.updateUserPanel.add(Components.getLabel("Morada:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.updateUserPanel.add(this.updateUserAddressField, constraints);

            if (userRole.equals("Autor")) {

                this.updateUserLiteracyStylesComboBox = Components.getComboBox(new ManageLiteraryStyles().toArray(), "Atualize o estilo literário");

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.updateUserPanel.add(Components.getLabel("Estilo Literário:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                this.updateUserPanel.add(this.updateUserLiteracyStylesComboBox, constraints);

                Author author = new ManageAuthors().getAuthor((long) this.viewUsersTable.getValueAt(this.viewUsersTable.getSelectedRow(), 0));
                if (author != null) {
                    this.updateUserNameField.setText(author.getName());
                    this.updateUserUsernameField.setText(author.getUsername());
                    this.updateUserPasswordField.setText(author.getPassword());
                    this.updateUserEmailField.setText(author.getEmail());
                    this.updateUserNifField.setText(author.getNif());
                    this.updateUserPhoneField.setText(author.getPhone());
                    this.updateUserAddressField.setText(author.getAddress());
                    for (int i = 0; i < this.updateUserLiteracyStylesComboBox.getItemCount(); i++) {
                        if (((LiteraryStyle) this.updateUserLiteracyStylesComboBox.getItemAt(i)).getId() == author.getLiteraryStyleId()) {
                            this.updateUserLiteracyStylesComboBox.setSelectedIndex(i);
                            break;
                        }
                    }

                }

            } else if (userRole.equals("Revisor")) {
                this.updateUserGraduationField = Components.getTextField("Atualize a graduação");
                this.updateUserSpecializationField = Components.getTextField("Atualize a especialização");

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.updateUserPanel.add(Components.getLabel("Graduação:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                this.updateUserPanel.add(this.updateUserGraduationField, constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.updateUserPanel.add(Components.getLabel("Especialização:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                this.updateUserPanel.add(this.updateUserSpecializationField, constraints);

                Reviewer reviewer = new ManageReviewers().getReviewer((long) this.viewUsersTable.getValueAt(this.viewUsersTable.getSelectedRow(), 0));
                if (reviewer != null) {
                    this.updateUserNameField.setText(reviewer.getName());
                    this.updateUserUsernameField.setText(reviewer.getUsername());
                    this.updateUserPasswordField.setText(reviewer.getPassword());
                    this.updateUserEmailField.setText(reviewer.getEmail());
                    this.updateUserNifField.setText(reviewer.getNif());
                    this.updateUserPhoneField.setText(reviewer.getPhone());
                    this.updateUserAddressField.setText(reviewer.getAddress());
                    this.updateUserGraduationField.setText(reviewer.getGraduation());
                    this.updateUserSpecializationField.setText(reviewer.getSpecialization());
                }
            }
        } else {
            Manager manager = new ManageManagers().getManager((long) this.viewUsersTable.getValueAt(this.viewUsersTable.getSelectedRow(), 0));
            if (manager != null) {
                this.updateUserNameField.setText(manager.getName());
                this.updateUserUsernameField.setText(manager.getUsername());
                this.updateUserPasswordField.setText(manager.getPassword());
                this.updateUserEmailField.setText(manager.getEmail());
            }
        }

        constraints.gridy = gridy += 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getVInsets(Components.Spacing.SMALL);
        this.updateUserPanel.add(this.updateUserButton, constraints);
        
        constraints.gridy = gridy += 1;
        constraints.insets = Components.getVInsets(Components.Spacing.SMALL);
        this.updateUserPanel.add(this.updateUserStatusButton, constraints);
        
        constraints.gridy = gridy + 1;
        this.updateUserPanel.add(this.updateUserDeletedButton, constraints);
        this.updateUserPanel.revalidate();
        this.updateUserPanel.repaint();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if ((e == null || !e.getValueIsAdjusting()) && (this.viewUsersTable.getSelectedRowCount() > 0)) {
            redesignUpdateUserPanel();
        }
    }
}
