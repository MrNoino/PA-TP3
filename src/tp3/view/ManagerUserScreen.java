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
    private JTextField addNameField, addUsernameField, addEmailField, addNifField, addPhoneField, addAddressField, addGraduationField, addSpecializationField, searchField,
            updateNameField, updateUsernameField, updateEmailField, updateNifField, updatePhoneField, updateAddressField, updateGraduationField, updateSpecializationField;
    private JPasswordField addPasswordField, updatePasswordField;
    private JComboBox addLiteracyStylesComboBox, searchCombobox, updateLiteracyStylesComboBox;
    private JButton addButton, searchButton, updateButton, updateStatusButton, updateDeletedButton;
    ButtonGroup userRoleGroup;
    private JRadioButton managerRadio, authorRadio, reviewerRadio;
    private JPanel userRolePanel, addPanel, updatePanel;
    private JTable table;
    private String[] columnNames;
    private DefaultTableModel tableModel;

    public ManagerUserScreen(JFrame frame, JTabbedPane usersTabbedPanel) {
        this.frame = frame;
        this.usersTabbedPanel = usersTabbedPanel;
        this.addPanel = new JPanel(new GridBagLayout());
        this.updatePanel = new JPanel(new GridBagLayout());
        JPanel viewUsersPanel = new JPanel(new BorderLayout());

        this.addNameField = Components.getTextField("Insira o nome");
        this.addUsernameField = Components.getTextField("Insira o nome de utilizador");
        this.addPasswordField = Components.getPasswordField("Insira a palavra passe");
        this.addEmailField = Components.getTextField("Insira o email");

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

        JScrollPane addUserScrollPanel = new JScrollPane(this.addPanel);
        
        this.updateNameField = Components.getTextField("Atualize o nome");
        this.updateUsernameField = Components.getTextField("Atualize o nome de utilizador");
        this.updatePasswordField = Components.getPasswordField("Atualize a palavra passe");
        this.updateEmailField = Components.getTextField("Atualize o email");
        this.updateButton = Components.getPrimaryButton("Atualizar", "Atualizar o Utilizador");
        this.updateButton.addActionListener(this);
        this.updateStatusButton = Components.getPrimaryButton("Ativar/Desativar Conta", "Ativar/Desativar conta de utilizador", Components.WARNING_COLOR, Components.ON_ACCENT_COLOR);
        this.updateStatusButton.addActionListener(this);
        this.updateDeletedButton = Components.getPrimaryButton("Eliminar Conta", "Eliminar conta de utilizador", Components.DANGER_COLOR, Components.ON_ACCENT_COLOR);
        this.updateDeletedButton.addActionListener(this);
        
        JScrollPane updateUserScrollPanel = new JScrollPane(this.updatePanel);

        this.searchField = Components.getTextField(new Dimension(250, Components.getSpacing(Components.Spacing.MEDIUM) * 2), "Insira a pesquisa");
        this.searchCombobox = Components.getComboBox(new String[]{"Nome", "Nome de Utilizador", "Tipo de Utilizador"}, "Escolha o campo a pesquisar");
        this.searchButton = Components.getSecondaryButton("Pesquisar", new Dimension(125, Components.getSpacing(Components.Spacing.MEDIUM) * 2), "Pesquisar utilizadores");
        this.searchButton.addActionListener(this);
        JPanel viewUsersSearchPanel = new JPanel(new FlowLayout());
        viewUsersSearchPanel.add(searchField);
        viewUsersSearchPanel.add(searchCombobox);
        viewUsersSearchPanel.add(searchButton);

        columnNames = new String[]{"ID", "Nome", "Nome de Utilizador", "Email", "Ativo", "Eliminado", "ID do Tipo"};
        ManageUsers manageUsers = new ManageUsers();
        manageUsers.getUsers();
        this.tableModel = new DefaultTableModel(manageUsers.toArray(), this.columnNames);
        this.table = new JTable(this.tableModel);
        this.table.setAutoCreateRowSorter(true);
        this.table.setDefaultEditor(Object.class, null);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = this.table.getSelectionModel();
        selectionModel.addListSelectionListener(this);
        if (this.table.getRowCount() > 0) {
            selectionModel.setSelectionInterval(0, 0);
        }
        JScrollPane viewUsersTableScroll = new JScrollPane(this.table);

        viewUsersPanel.add(viewUsersSearchPanel, BorderLayout.NORTH);
        viewUsersPanel.add(viewUsersTableScroll, BorderLayout.CENTER);

        this.usersTabbedPanel.add("Visualizar", viewUsersPanel);
        this.usersTabbedPanel.add("Inserir", addUserScrollPanel);
        this.usersTabbedPanel.add("Atualizar", updateUserScrollPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ManageUsers manageUsers = new ManageUsers();
        if (e.getSource() == this.searchButton) {
            String item = (String) this.searchCombobox.getSelectedItem();
            switch (item) {
                case "Nome":
                    manageUsers.getUsersByName(this.searchField.getText());
                    break;
                case "Nome de Utilizador":
                    manageUsers.getUsersByUsername(this.searchField.getText());
                    break;

                case "Tipo de Utilizador":
                    manageUsers.getUsersByRole(this.searchField.getText());
                    break;
                default:
                    return;
            }
            this.tableModel.setDataVector(manageUsers.toArray(), this.columnNames);
            if (this.table.getRowCount() > 0) {
                this.table.getSelectionModel().setSelectionInterval(0, 0);
            }
        } else if (e.getSource() == this.addButton) {
            if (this.addNameField.getText().isEmpty() || this.addUsernameField.getText().isEmpty() || this.addPasswordField.getPassword().length == 0 || this.addEmailField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            if (manageUsers.existsUsername(this.addUsernameField.getText())) {
                JOptionPane.showMessageDialog(this.frame, "Nome de utilizador já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            if (!this.addEmailField.getText().matches("[\\w._-]{3,}@[\\w_]{3,}.\\w{2,5}")) {
                JOptionPane.showMessageDialog(this.frame, "Email de formato inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            if (manageUsers.existsEmail(this.addEmailField.getText())) {
                JOptionPane.showMessageDialog(this.frame, "Email já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            boolean inserted = false;
            if (this.managerRadio.isSelected()) {
                inserted = new ManageManagers().insertManager(new Manager(-1,
                        this.addNameField.getText(),
                        this.addUsernameField.getText(),
                        new String(this.addPasswordField.getPassword()),
                        this.addEmailField.getText(),
                        true,
                        false,
                        1));
            } else {
                if (this.addNifField.getText().isEmpty() || this.addPhoneField.getText().isEmpty() || this.addAddressField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
                if (!this.addNifField.getText().matches("\\d{9}")) {
                    JOptionPane.showMessageDialog(this.frame, "NIF de formato inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
                if (manageUsers.existsNIF(this.addNifField.getText())) {
                    JOptionPane.showMessageDialog(this.frame, "NIF já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
                if (!this.addPhoneField.getText().matches("[239]\\d{8}")) {
                    JOptionPane.showMessageDialog(this.frame, "Telefone de formato inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
                if (this.authorRadio.isSelected()) {
                    inserted = new ManageAuthors().insertAuthor(new Author(-1,
                            this.addNameField.getText(),
                            this.addUsernameField.getText(),
                            new String(this.addPasswordField.getPassword()),
                            this.addEmailField.getText(),
                            true,
                            false,
                            3,
                            this.addNifField.getText(),
                            this.addPhoneField.getText(),
                            this.addAddressField.getText(),
                            new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                            1));
                } else {
                    if (this.addGraduationField.getText().isEmpty() || this.addSpecializationField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                        return;
                    }
                    inserted = new ManageReviewers().insertReviewer(new Reviewer(-1,
                            this.addNameField.getText(),
                            this.addUsernameField.getText(),
                            new String(this.addPasswordField.getPassword()),
                            this.addEmailField.getText(),
                            true,
                            false,
                            2,
                            this.addNifField.getText(),
                            this.addPhoneField.getText(),
                            this.addAddressField.getText(),
                            this.addGraduationField.getText(),
                            this.addSpecializationField.getText()));
                }
            }
            if (inserted) {
                JOptionPane.showMessageDialog(this.frame, "Inserido com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);
                this.clearAddForm();
            } else {
                JOptionPane.showMessageDialog(this.frame, "Não inserido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
            }
        } else if (e.getSource() == this.updateButton) {
            User user = manageUsers.getUser(Main.getLoggedUser().getId());
            if (this.table.getSelectedRowCount() != 1) {
                JOptionPane.showMessageDialog(this.frame, "Deve ter um registo selecionado na tabela de visualização", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            if (this.updateNameField.getText().isEmpty() || this.updateUsernameField.getText().isEmpty() || this.updatePasswordField.getPassword().length == 0 || this.updateEmailField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            if (!this.updateUsernameField.getText().equals(user.getUsername()) && manageUsers.existsUsername(this.updateUsernameField.getText())) {
                JOptionPane.showMessageDialog(this.frame, "Nome de utilizador já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            if (!this.updateEmailField.getText().matches("[\\w._-]{3,}@[\\w_]{3,}.\\w{2,5}")) {
                JOptionPane.showMessageDialog(this.frame, "Email de formato inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            if (!this.updateEmailField.getText().equals(user.getEmail()) && manageUsers.existsEmail(this.updateEmailField.getText())) {
                JOptionPane.showMessageDialog(this.frame, "Email já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            long id = (long) this.table.getValueAt(this.table.getSelectedRow(), 0);
            int roleId = (int) this.table.getValueAt(this.table.getSelectedRow(), 6);
            boolean updated = false;
            if (roleId == 1) {
                updated = new ManageManagers().updateManager(new Manager(
                        id,
                        this.updateNameField.getText(),
                        this.updateUsernameField.getText(),
                        new String(this.updatePasswordField.getPassword()),
                        this.updateEmailField.getText(),
                        true,
                        false,
                        roleId));
            } else {
                if (this.updateNifField.getText().isEmpty() || this.updatePhoneField.getText().isEmpty() || this.updateAddressField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
                if (!this.updateNifField.getText().matches("\\d{9}")) {
                    JOptionPane.showMessageDialog(this.frame, "NIF de formato inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
                if (!this.updatePhoneField.getText().matches("[239]\\d{8}")) {
                    JOptionPane.showMessageDialog(this.frame, "Telefone de formato inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
                if (roleId == 2) {
                    if (this.addGraduationField.getText().isEmpty() || this.addSpecializationField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                        return;
                    }
                    Reviewer reviewer = new ManageReviewers().getReviewer(Main.getLoggedUser().getId());
                    if (!this.updateNifField.getText().equals(reviewer.getNif()) && manageUsers.existsNIF(this.updateNifField.getText())) {
                        JOptionPane.showMessageDialog(this.frame, "NIF já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                        return;
                    }
                    updated = new ManageReviewers().updateReviewer(new Reviewer(
                            id,
                            this.updateNameField.getText(),
                            this.updateUsernameField.getText(),
                            new String(this.updatePasswordField.getPassword()),
                            this.updateEmailField.getText(),
                            true,
                            false,
                            roleId,
                            this.updateNifField.getText(),
                            this.updatePhoneField.getText(),
                            this.updateAddressField.getText(),
                            this.updateGraduationField.getText(),
                            this.updateSpecializationField.getText()));
                } else {
                    Author author = new ManageAuthors().getAuthor(Main.getLoggedUser().getId());
                    if (!this.updateNifField.getText().equals(author.getNif()) && manageUsers.existsNIF(this.updateNifField.getText())) {
                        JOptionPane.showMessageDialog(this.frame, "NIF já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                        return;
                    }
                    updated = new ManageAuthors().updateAuthor(new Author(
                            id,
                            this.updateNameField.getText(),
                            this.updateUsernameField.getText(),
                            new String(this.updatePasswordField.getPassword()),
                            this.updateEmailField.getText(),
                            true,
                            false,
                            roleId,
                            this.updateNifField.getText(),
                            this.updatePhoneField.getText(),
                            this.updateAddressField.getText(),
                            new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                            ((LiteraryStyle) this.updateLiteracyStylesComboBox.getSelectedItem()).getId()));
                }
            }

            if (updated) {
                JOptionPane.showMessageDialog(this.frame, "Atualizado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);
            } else {
                JOptionPane.showMessageDialog(this.frame, "Não atualizado", "Aviso", JOptionPane.ERROR_MESSAGE, null);
            }

        } else if (e.getSource() == this.updateStatusButton) {
            if (this.table.getSelectedRowCount() != 1) {
                JOptionPane.showMessageDialog(this.frame, "Deve ter um registo selecionado na tabela de visualização", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            long id = (long) this.table.getValueAt(this.table.getSelectedRow(), 0);
            User user = manageUsers.getUser(id);
            if (user != null) {
                if (manageUsers.updateUserStatus(id, !user.isActive())) {
                    JOptionPane.showMessageDialog(this.frame, "Estado atualizado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);
                } else {
                    JOptionPane.showMessageDialog(this.frame, "Erro ao atualizar o estado", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                }
            } else {
                JOptionPane.showMessageDialog(this.frame, "Não foi possível obter o estado do utilizador", "Aviso", JOptionPane.ERROR_MESSAGE, null);
            }
        } else if (e.getSource() == this.updateDeletedButton) {
            if (this.table.getSelectedRowCount() != 1) {
                JOptionPane.showMessageDialog(this.frame, "Deve ter um registo selecionado na tabela de visualização", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            long id = (long) this.table.getValueAt(this.table.getSelectedRow(), 0);
            if (manageUsers.deleteUser(id)) {
                JOptionPane.showMessageDialog(this.frame, "Eliminado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);
            } else {
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
        this.addButton = Components.getPrimaryButton("Inserir", "Inserir o " + userRole);
        this.addButton.addActionListener(this);
        GridBagConstraints constraints = new GridBagConstraints();
        this.addPanel.removeAll();
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addPanel.add(Components.getHeader("Inserir " + userRole, Components.Alignment.CENTER), constraints);

        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        this.addPanel.add(Components.getLabel("Nome:"), constraints);

        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addPanel.add(this.addNameField, constraints);

        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.WEST;
        this.addPanel.add(Components.getLabel("Nome de utilizador:"), constraints);

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addPanel.add(this.addUsernameField, constraints);

        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.WEST;
        this.addPanel.add(Components.getLabel("Palavra Passe:"), constraints);

        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addPanel.add(this.addPasswordField, constraints);

        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.WEST;
        this.addPanel.add(Components.getLabel("Email:"), constraints);

        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addPanel.add(this.addEmailField, constraints);

        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.WEST;
        this.addPanel.add(Components.getLabel("Tipo de Utilizador:"), constraints);

        constraints.gridy = 10;
        constraints.anchor = GridBagConstraints.CENTER;
        this.addPanel.add(this.userRolePanel, constraints);

        int gridy = 10;
        if (!userRole.equals("Gestor")) {
            this.addNifField = Components.getTextField("Insira o NIF");
            this.addPhoneField = Components.getTextField("Insira o Telefone");
            this.addAddressField = Components.getTextField("Insira a morada");

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.addPanel.add(Components.getLabel("NIF:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.addPanel.add(this.addNifField, constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.addPanel.add(Components.getLabel("Telefone:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.addPanel.add(this.addPhoneField, constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.addPanel.add(Components.getLabel("Morada:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.addPanel.add(this.addAddressField, constraints);

            if (userRole.equals("Autor")) {

                this.addLiteracyStylesComboBox = Components.getComboBox(new ManageLiteraryStyles().toArray(), "Escolha o estilo literário");

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.addPanel.add(Components.getLabel("Estilo Literário:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                this.addPanel.add(this.addLiteracyStylesComboBox, constraints);

            } else if (userRole.equals("Revisor")) {
                this.addGraduationField = Components.getTextField("Insira a graduação");
                this.addSpecializationField = Components.getTextField("Insira a especialização");

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.addPanel.add(Components.getLabel("Graduação:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                this.addPanel.add(this.addGraduationField, constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.addPanel.add(Components.getLabel("Especialização:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                this.addPanel.add(this.addSpecializationField, constraints);

            }
        }

        constraints.gridy = gridy + 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        this.addPanel.add(this.addButton, constraints);
        this.addPanel.revalidate();
        this.addPanel.repaint();
    }

    private void redesignUpdateUserPanel() {
        String userRole;
        int roleId = (int) this.table.getValueAt(this.table.getSelectedRow(), 6);
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
        this.updatePanel.removeAll();

        GridBagConstraints constraints = new GridBagConstraints();
        this.updatePanel.removeAll();
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        this.updatePanel.add(Components.getHeader("Atualizar " + userRole, Components.Alignment.CENTER), constraints);

        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        this.updatePanel.add(Components.getLabel("Nome:"), constraints);

        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        this.updatePanel.add(this.updateNameField, constraints);

        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.WEST;
        this.updatePanel.add(Components.getLabel("Nome de utilizador:"), constraints);

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        this.updatePanel.add(this.updateUsernameField, constraints);

        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.WEST;
        this.updatePanel.add(Components.getLabel("Palavra Passe:"), constraints);

        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        this.updatePanel.add(this.updatePasswordField, constraints);

        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.WEST;
        this.updatePanel.add(Components.getLabel("Email:"), constraints);

        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.CENTER;
        this.updatePanel.add(this.updateEmailField, constraints);

        int gridy = 9;
        if (!userRole.equals("Gestor")) {
            this.updateNifField = Components.getTextField("Atualize o NIF");
            this.updatePhoneField = Components.getTextField("Atualize o Telefone");
            this.updateAddressField = Components.getTextField("Atualize a morada");

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.updatePanel.add(Components.getLabel("NIF:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.updatePanel.add(this.updateNifField, constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.updatePanel.add(Components.getLabel("Telefone:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.updatePanel.add(this.updatePhoneField, constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            this.updatePanel.add(Components.getLabel("Morada:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            this.updatePanel.add(this.updateAddressField, constraints);

            if (userRole.equals("Autor")) {

                this.updateLiteracyStylesComboBox = Components.getComboBox(new ManageLiteraryStyles().toArray(), "Atualize o estilo literário");

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.updatePanel.add(Components.getLabel("Estilo Literário:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                this.updatePanel.add(this.updateLiteracyStylesComboBox, constraints);

                Author author = new ManageAuthors().getAuthor((long) this.table.getValueAt(this.table.getSelectedRow(), 0));
                if (author != null) {
                    this.updateNameField.setText(author.getName());
                    this.updateUsernameField.setText(author.getUsername());
                    this.updatePasswordField.setText(author.getPassword());
                    this.updateEmailField.setText(author.getEmail());
                    this.updateNifField.setText(author.getNif());
                    this.updatePhoneField.setText(author.getPhone());
                    this.updateAddressField.setText(author.getAddress());
                    for (int i = 0; i < this.updateLiteracyStylesComboBox.getItemCount(); i++) {
                        if (((LiteraryStyle) this.updateLiteracyStylesComboBox.getItemAt(i)).getId() == author.getLiteraryStyleId()) {
                            this.updateLiteracyStylesComboBox.setSelectedIndex(i);
                            break;
                        }
                    }

                }

            } else if (userRole.equals("Revisor")) {
                this.updateGraduationField = Components.getTextField("Atualize a graduação");
                this.updateSpecializationField = Components.getTextField("Atualize a especialização");

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.updatePanel.add(Components.getLabel("Graduação:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                this.updatePanel.add(this.updateGraduationField, constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                this.updatePanel.add(Components.getLabel("Especialização:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                this.updatePanel.add(this.updateSpecializationField, constraints);

                Reviewer reviewer = new ManageReviewers().getReviewer((long) this.table.getValueAt(this.table.getSelectedRow(), 0));
                if (reviewer != null) {
                    this.updateNameField.setText(reviewer.getName());
                    this.updateUsernameField.setText(reviewer.getUsername());
                    this.updatePasswordField.setText(reviewer.getPassword());
                    this.updateEmailField.setText(reviewer.getEmail());
                    this.updateNifField.setText(reviewer.getNif());
                    this.updatePhoneField.setText(reviewer.getPhone());
                    this.updateAddressField.setText(reviewer.getAddress());
                    this.updateGraduationField.setText(reviewer.getGraduation());
                    this.updateSpecializationField.setText(reviewer.getSpecialization());
                }
            }
        } else {
            Manager manager = new ManageManagers().getManager((long) this.table.getValueAt(this.table.getSelectedRow(), 0));
            if (manager != null) {
                this.updateNameField.setText(manager.getName());
                this.updateUsernameField.setText(manager.getUsername());
                this.updatePasswordField.setText(manager.getPassword());
                this.updateEmailField.setText(manager.getEmail());
            }
        }

        constraints.gridy = gridy += 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getVInsets(Components.Spacing.SMALL);
        this.updatePanel.add(this.updateButton, constraints);

        constraints.gridy = gridy += 1;
        constraints.insets = Components.getVInsets(Components.Spacing.SMALL);
        this.updatePanel.add(this.updateStatusButton, constraints);

        constraints.gridy = gridy + 1;
        this.updatePanel.add(this.updateDeletedButton, constraints);
        this.updatePanel.revalidate();
        this.updatePanel.repaint();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && this.table.getSelectedRowCount() > 0) {
            this.redesignUpdateUserPanel();
        }
    }
    
    private void clearAddForm(){
        this.addNameField.setText("");
        this.addUsernameField.setText("");
        this.addPasswordField.setText("");
        this.addEmailField.setText("");
        if(this.addNifField != null)
            this.addNifField.setText("");
        if(this.addPhoneField != null)
            this.addPhoneField.setText("");
        if(this.addAddressField != null)
            this.addAddressField.setText("");
        if(this.addGraduationField != null)
            this.addGraduationField.setText("");
        if(this.addSpecializationField != null)
            this.addSpecializationField.setText("");
        if (this.addLiteracyStylesComboBox != null)
            this.addLiteracyStylesComboBox.setSelectedIndex(0);
    }
}
