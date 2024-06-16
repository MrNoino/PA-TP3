package tp3.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import tp3.controller.ManageAuthors;
import tp3.controller.ManageLiteraryStyles;
import tp3.controller.ManageManagers;
import tp3.controller.ManageReviewers;
import tp3.controller.ManageUsers;
import tp3.model.Author;
import tp3.model.LiteraryStyle;
import tp3.model.Manager;
import tp3.model.Reviewer;

public class ProfileScreen extends JFrame implements ActionListener {

    private JFrame frame;
    private JPanel profilePanel;
    private JButton logoutButton, updateButton;
    private JTextField nameField, usernameField, emailField, nifField, phoneField, addressField, graduationField, specializationField;
    private JPasswordField passwordField;
    private JComboBox literaryStylesComboBox;

    public ProfileScreen(JFrame frame, JPanel profilePanel) {
        this.frame = frame;
        this.profilePanel = profilePanel;

        this.logoutButton = Components.getSecondaryButton("Terminar Sessão",
                new Dimension(175, Components.getSpacing(Components.Spacing.MEDIUM) * 2),
                "Termina a sessão do utilizador",
                Components.DANGER_COLOR,
                Components.ON_ACCENT_COLOR);
        logoutButton.addActionListener(this);
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutButton, BorderLayout.SOUTH);

        this.nameField = Components.getTextField("Atualize o seu nome");
        this.usernameField = Components.getTextField("Atualize o seu nome de utilizador");
        this.passwordField = Components.getPasswordField("Atualize o seu nome");
        this.emailField = Components.getTextField("Atualize o seu email");
        this.updateButton = Components.getPrimaryButton("Atualizar", "Atualizar o perfil");
        this.updateButton.addActionListener(this);

        JPanel profileInfoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        int gridy = 0;

        constraints.gridy = gridy += 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getTopInsets(Components.Spacing.LARGE);
        profileInfoPanel.add(Components.getHeader("Atualizar Perfil", Components.Alignment.CENTER), constraints);

        constraints.gridy = gridy += 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getEmptyInsets();
        profileInfoPanel.add(Components.getLabel("Nome:"), constraints);

        constraints.gridy = gridy += 1;
        constraints.anchor = GridBagConstraints.CENTER;
        profileInfoPanel.add(this.nameField, constraints);

        constraints.gridy = gridy += 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        profileInfoPanel.add(Components.getLabel("Nome de Utilizador:"), constraints);

        constraints.gridy = gridy += 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        profileInfoPanel.add(this.usernameField, constraints);

        constraints.gridy = gridy += 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        profileInfoPanel.add(Components.getLabel("Palavra Passe:"), constraints);

        constraints.gridy = gridy += 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        profileInfoPanel.add(this.passwordField, constraints);

        constraints.gridy = gridy += 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
        profileInfoPanel.add(Components.getLabel("Email:"), constraints);

        constraints.gridy = gridy += 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        profileInfoPanel.add(this.emailField, constraints);

        if (Main.getLoggedUser().getRoleId() != 1) {
            this.nifField = Components.getTextField("Atualize o seu NIF");
            this.phoneField = Components.getTextField("Atualize o seu Telefone");
            this.addressField = Components.getTextField("Atualize a sua morada");

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
            profileInfoPanel.add(Components.getLabel("NIF:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.insets = Components.getEmptyInsets();
            profileInfoPanel.add(this.nifField, constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
            profileInfoPanel.add(Components.getLabel("Phone:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.insets = Components.getEmptyInsets();
            profileInfoPanel.add(this.phoneField, constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
            profileInfoPanel.add(Components.getLabel("Morada:"), constraints);

            constraints.gridy = gridy += 1;
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.insets = Components.getEmptyInsets();
            profileInfoPanel.add(this.addressField, constraints);

            if (Main.getLoggedUser().getRoleId() == 2) {
                this.graduationField = Components.getTextField("Atualize a sua graduação");
                this.specializationField = Components.getTextField("Atualize a sua especialização");

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
                profileInfoPanel.add(Components.getLabel("Graduação:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                constraints.insets = Components.getEmptyInsets();
                profileInfoPanel.add(this.graduationField, constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
                profileInfoPanel.add(Components.getLabel("Especialização:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                constraints.insets = Components.getEmptyInsets();
                profileInfoPanel.add(this.specializationField, constraints);

                Reviewer reviewer = new ManageReviewers().getReviewer(Main.getLoggedUser().getId());

                if (reviewer != null) {
                    this.nameField.setText(reviewer.getName());
                    this.usernameField.setText(reviewer.getUsername());
                    this.passwordField.setText(reviewer.getPassword());
                    this.emailField.setText(reviewer.getEmail());
                    this.nifField.setText(reviewer.getNif());
                    this.phoneField.setText(reviewer.getPhone());
                    this.addressField.setText(reviewer.getAddress());
                    this.graduationField.setText(reviewer.getGraduation());
                    this.specializationField.setText(reviewer.getSpecialization());
                }

            } else {
                this.literaryStylesComboBox = Components.getComboBox(new ManageLiteraryStyles().toArray(), "Atualize o seu estilo literário");

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.WEST;
                constraints.insets = Components.getTopInsets(Components.Spacing.SMALL);
                profileInfoPanel.add(Components.getLabel("Estilo Literário:"), constraints);

                constraints.gridy = gridy += 1;
                constraints.anchor = GridBagConstraints.CENTER;
                constraints.insets = Components.getEmptyInsets();
                constraints.fill = GridBagConstraints.HORIZONTAL;
                profileInfoPanel.add(this.literaryStylesComboBox, constraints);

                Author author = new ManageAuthors().getAuthor(Main.getLoggedUser().getId());

                if (author != null) {
                    this.nameField.setText(author.getName());
                    this.usernameField.setText(author.getUsername());
                    this.passwordField.setText(author.getPassword());
                    this.emailField.setText(author.getEmail());
                    this.nifField.setText(author.getNif());
                    this.phoneField.setText(author.getPhone());
                    this.addressField.setText(author.getAddress());
                    for (int i = 0; i < this.literaryStylesComboBox.getItemCount(); i++) {
                        if (((LiteraryStyle) this.literaryStylesComboBox.getItemAt(i)).getId() == author.getLiteraryStyleId()) {
                            this.literaryStylesComboBox.setSelectedIndex(i);
                            break;
                        }
                    }
                }

            }
        } else {
            Manager manager = new ManageManagers().getManager(Main.getLoggedUser().getId());

            if (manager != null) {
                this.nameField.setText(manager.getName());
                this.usernameField.setText(manager.getUsername());
                this.passwordField.setText(manager.getPassword());
                this.emailField.setText(manager.getEmail());
            }
        }

        constraints.gridy = gridy += 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        profileInfoPanel.add(this.updateButton, constraints);

        JScrollPane scrollInfoProfile = new JScrollPane(profileInfoPanel);

        this.profilePanel.add(scrollInfoProfile, BorderLayout.CENTER);
        this.profilePanel.add(logoutPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.logoutButton) {
            Main.logout();
            new LoginScreen();
            this.frame.dispose();
        } else if (e.getSource() == this.updateButton) {
            if (this.nameField.getText().isEmpty() || this.usernameField.getText().isEmpty() || this.passwordField.getPassword().length == 0 || this.emailField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            ManageUsers manageUsers = new ManageUsers();
            if (!this.usernameField.getText().equals(Main.getLoggedUser().getUsername()) && manageUsers.existsUsername(this.usernameField.getText())) {
                JOptionPane.showMessageDialog(this.frame, "Nome de utilizador já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            if (!this.emailField.getText().matches("[\\w._-]{3,}@[\\w_]{3,}.\\w{2,5}")) {
                JOptionPane.showMessageDialog(this.frame, "Email de formato inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            if (!this.emailField.getText().equals(Main.getLoggedUser().getEmail()) && manageUsers.existsEmail(this.emailField.getText())) {
                JOptionPane.showMessageDialog(this.frame, "Email já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            boolean updated;
            if (Main.getLoggedUser().getRoleId() != 1) {
                if (this.nifField.getText().isEmpty() || this.phoneField.getText().isEmpty() || this.addressField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
                if (!this.nifField.getText().matches("\\d{9}")) {
                    JOptionPane.showMessageDialog(this.frame, "NIF de formato inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
                if (!this.phoneField.getText().matches("[239]\\d{8}")) {
                    JOptionPane.showMessageDialog(this.frame, "Telefone de formato inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                    return;
                }
                if (Main.getLoggedUser().getRoleId() == 2) {
                    if (this.graduationField.getText().isEmpty() || this.specializationField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this.frame, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                        return;
                    }
                    Reviewer reviewer = new ManageReviewers().getReviewer(Main.getLoggedUser().getId());
                    if (!this.nifField.getText().equals(reviewer.getNif()) && manageUsers.existsNIF(this.nifField.getText())) {
                        JOptionPane.showMessageDialog(this.frame, "NIF já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                        return;
                    }
                    updated = new ManageReviewers().updateReviewer(new Reviewer(
                            Main.getLoggedUser().getId(),
                            this.nameField.getText(),
                            this.usernameField.getText(),
                            new String(this.passwordField.getPassword()),
                            this.emailField.getText(),
                            Main.getLoggedUser().isActive(),
                            Main.getLoggedUser().isDeleted(),
                            Main.getLoggedUser().getRoleId(),
                            this.nifField.getText(),
                            this.phoneField.getText(),
                            this.addressField.getText(),
                            this.graduationField.getText(),
                            this.specializationField.getText()));
                } else {
                    Author author = new ManageAuthors().getAuthor(Main.getLoggedUser().getId());
                    if (!this.nifField.getText().equals(author.getNif()) && manageUsers.existsNIF(this.nifField.getText())) {
                        JOptionPane.showMessageDialog(this.frame, "NIF já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                        return;
                    }
                    updated = new ManageAuthors().updateAuthor(new Author(
                            Main.getLoggedUser().getId(),
                            this.nameField.getText(),
                            this.usernameField.getText(),
                            new String(this.passwordField.getPassword()),
                            this.emailField.getText(),
                            Main.getLoggedUser().isActive(),
                            Main.getLoggedUser().isDeleted(),
                            Main.getLoggedUser().getRoleId(),
                            this.nifField.getText(),
                            this.phoneField.getText(),
                            this.addressField.getText(),
                            new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                            ((LiteraryStyle) this.literaryStylesComboBox.getSelectedItem()).getId()));
                }
            } else {
                updated = new ManageManagers().updateManager(new Manager(
                        Main.getLoggedUser().getId(),
                        this.nameField.getText(),
                        this.usernameField.getText(),
                        new String(this.passwordField.getPassword()),
                        this.emailField.getText(),
                        Main.getLoggedUser().isActive(),
                        Main.getLoggedUser().isDeleted(),
                        Main.getLoggedUser().getRoleId()));
            }
            if(updated){
                JOptionPane.showMessageDialog(this.frame, "Atualizado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);  
            }else{
                JOptionPane.showMessageDialog(this.frame, "Não atualizado", "Aviso", JOptionPane.ERROR_MESSAGE, null); 
            }
        }
    }
}
