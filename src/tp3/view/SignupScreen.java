package tp3.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import tp3.controller.ManageAuthors;
import tp3.controller.ManageLiteraryStyles;
import tp3.controller.ManageReviewers;
import tp3.controller.ManageUsers;
import tp3.model.Author;
import tp3.model.Reviewer;

public class SignupScreen extends JFrame implements ActionListener {

    private Container container;
    private JFrame frame;

    JLabel authorNameLabel;
    JTextField authorNameField;
    JLabel authorUsernameLabel;
    JTextField authorUsernameField;
    JLabel authorEmailLabel;
    JTextField authorEmailField;
    JLabel authorPasswordLabel;
    JPasswordField authorPasswordField;
    JLabel authorNifLabel;
    JTextField authorNifField;
    JLabel authorPhoneLabel;
    JTextField authorPhoneField;
    JLabel authorAddressLabel;
    JTextField authorAddressField;
    JButton authorSignupButton;
    JLabel literacyStyleLabel;
    JComboBox literacyStylesComboBox;

    JLabel reviewerNameLabel;
    JTextField reviewerNameField;
    JLabel reviewerUsernameLabel;
    JTextField reviewerUsernameField;
    JLabel reviewerEmailLabel;
    JTextField reviewerEmailField;
    JLabel reviewerPasswordLabel;
    JPasswordField reviewerPasswordField;
    JLabel reviewerNifLabel;
    JTextField reviewerNifField;
    JLabel reviewerPhoneLabel;
    JTextField reviewerPhoneField;
    JLabel reviewerAddressLabel;
    JTextField reviewerAddressField;
    JLabel formationLabel;
    JTextField formationField;
    JLabel specializationLabel;
    JTextField specializationField;
    JButton reviewerSignupButton;

    public SignupScreen() {
        this.frame = this;
        this.container = getContentPane();
        this.container.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Editora");
        this.setMinimumSize(new Dimension(800, 800));
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setIconImage(Components.getLogoIcon().getImage());

        this.container.setBackground(Components.BACKGROUND_COLOR);

        JTabbedPane tabbedPanel = new JTabbedPane();
        //tabbedPanel.setPreferredSize(new Dimension(500,600));

        JLabel titleAuthorLabel = Components.getHeader("Registo de Autor", Components.Alignment.CENTER),
                titleReviewerLabel = Components.getHeader("Registo de Revisor", Components.Alignment.CENTER);

        GridBagConstraints constraints = new GridBagConstraints();

        // Author Fields
        JPanel authorPanel = new JPanel(new GridBagLayout());
        authorPanel.setBackground(Components.BACKGROUND_COLOR);

        authorNameLabel = Components.getLabel("Nome:");
        authorNameField = Components.getTextField("Insira o nome");
        authorUsernameLabel = Components.getLabel("Nome de utilizador:");
        authorUsernameField = Components.getTextField("Insira o nome de utilizador");
        authorEmailLabel = Components.getLabel("Email:");
        authorEmailField = Components.getTextField("Insira o email");
        authorPasswordLabel = Components.getLabel("Palavra Passe:");
        authorPasswordField = Components.getPasswordField("Insira a palavra-passe");
        authorNifLabel = Components.getLabel("NIF:");
        authorNifField = Components.getTextField("Insira o NIF");
        authorPhoneLabel = Components.getLabel("Telefone:");
        authorPhoneField = Components.getTextField("Insira o numero de telefone");
        authorAddressLabel = Components.getLabel("Morada:");
        authorAddressField = Components.getTextField("Insira a morada");
        authorSignupButton = Components.getPrimaryButton("Registar", "Registar o Autor");
        literacyStyleLabel = Components.getLabel("Estilo Literário:");
        literacyStylesComboBox = Components.getComboBox(new ManageLiteraryStyles().toArray(), "Escolha o estilo literário");
        authorSignupButton.addActionListener(this);

        // Reviewer Fields
        JPanel reviewerPanel = new JPanel(new GridBagLayout());
        reviewerPanel.setBackground(Components.BACKGROUND_COLOR);

        reviewerNameLabel = Components.getLabel("Nome");
        reviewerNameField = Components.getTextField("Insira o nome");
        reviewerUsernameLabel = Components.getLabel("Nome de utilizador");
        reviewerUsernameField = Components.getTextField("Insira o nome de utilizador");
        reviewerEmailLabel = Components.getLabel("Email:");
        reviewerEmailField = Components.getTextField("Insira o email");
        reviewerPasswordLabel = Components.getLabel("Palavra Passe");
        reviewerPasswordField = Components.getPasswordField("Insira a palavra-passe");
        reviewerNifLabel = Components.getLabel("NIF");
        reviewerNifField = Components.getTextField("Insira o NIF");
        reviewerPhoneLabel = Components.getLabel("Telefone");
        reviewerPhoneField = Components.getTextField("Insira o numero de telefone");
        reviewerAddressLabel = Components.getLabel("Morada");
        reviewerAddressField = Components.getTextField("Insira a morada");
        formationLabel = Components.getLabel("Formação Académica");
        formationField = Components.getTextField("Insira a formação académica");
        specializationLabel = Components.getLabel("Especialização");
        specializationField = Components.getTextField("Insira a especialização");
        reviewerSignupButton = Components.getPrimaryButton("Registar", "Registar o Revisor");
        reviewerSignupButton.addActionListener(this);

        JLabel alreadyHaveAccountLabel1 = Components.getLabel("Já tens conta?"),
                loginLabel1 = Components.getLabel("Autenticar", Components.ACCENT_COLOR);
        loginLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame signupFrame = new LoginScreen();
                frame.dispose();
            }
        });

        JLabel alreadyHaveAccountLabel2 = Components.getLabel("Já tens conta?"),
                loginLabel2 = Components.getLabel("Autenticar", Components.ACCENT_COLOR);

        loginLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame signupFrame = new LoginScreen();
                frame.dispose();
            }
        });

        JPanel loginPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginPanel1.setBackground(Components.BACKGROUND_COLOR);
        loginPanel1.add(alreadyHaveAccountLabel1);
        loginPanel1.add(loginLabel1);

        JPanel loginPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginPanel2.setBackground(Components.BACKGROUND_COLOR);
        loginPanel2.add(alreadyHaveAccountLabel2);
        loginPanel2.add(loginLabel2);

        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(titleAuthorLabel, constraints);
        reviewerPanel.add(titleReviewerLabel, constraints);

        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorNameLabel, constraints);
        reviewerPanel.add(reviewerNameLabel, constraints);

        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorNameField, constraints);
        reviewerPanel.add(reviewerNameField, constraints);

        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorUsernameLabel, constraints);
        reviewerPanel.add(reviewerUsernameLabel, constraints);

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorUsernameField, constraints);
        reviewerPanel.add(reviewerUsernameField, constraints);

        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorEmailLabel, constraints);
        reviewerPanel.add(reviewerEmailLabel, constraints);

        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorEmailField, constraints);
        reviewerPanel.add(reviewerEmailField, constraints);

        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorPasswordLabel, constraints);
        reviewerPanel.add(reviewerPasswordLabel, constraints);

        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorPasswordField, constraints);
        reviewerPanel.add(reviewerPasswordField, constraints);

        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorNifLabel, constraints);
        reviewerPanel.add(reviewerNifLabel, constraints);

        constraints.gridy = 10;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorNifField, constraints);
        reviewerPanel.add(reviewerNifField, constraints);

        constraints.gridy = 11;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorPhoneLabel, constraints);
        reviewerPanel.add(reviewerPhoneLabel, constraints);

        constraints.gridy = 12;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorPhoneField, constraints);
        reviewerPanel.add(reviewerPhoneField, constraints);

        constraints.gridy = 13;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorAddressLabel, constraints);
        reviewerPanel.add(reviewerAddressLabel, constraints);

        constraints.gridy = 14;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorAddressField, constraints);
        reviewerPanel.add(reviewerAddressField, constraints);

        constraints.gridy = 15;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(literacyStyleLabel, constraints);
        reviewerPanel.add(formationLabel, constraints);

        constraints.gridy = 16;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        authorPanel.add(literacyStylesComboBox, constraints);
        constraints.fill = GridBagConstraints.NONE;
        reviewerPanel.add(formationField, constraints);

        constraints.gridy = 17;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getTopInsets(Components.Spacing.LARGE);
        authorPanel.add(authorSignupButton, constraints);
        constraints.insets = Components.getEmptyInsets();
        constraints.anchor = GridBagConstraints.WEST;
        reviewerPanel.add(specializationLabel, constraints);

        constraints.gridy = 18;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getTopInsets(Components.Spacing.LARGE);
        authorPanel.add(loginPanel1, constraints);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getEmptyInsets();
        reviewerPanel.add(specializationField, constraints);

        constraints.gridy = 19;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getTopInsets(Components.Spacing.LARGE);
        reviewerPanel.add(reviewerSignupButton, constraints);

        constraints.gridy = 20;
        reviewerPanel.add(loginPanel2, constraints);

        tabbedPanel.add("Autor", authorPanel);
        tabbedPanel.add("Revisor", reviewerPanel);

        this.container.add(tabbedPanel, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(authorSignupButton)) {
            String name = authorNameField.getText();
            String username = authorUsernameField.getText();
            String email = authorEmailField.getText();
            String password = new String(authorPasswordField.getPassword());
            String nif = authorNifField.getText();
            String phone = authorPhoneField.getText();
            String address = authorPhoneField.getText();
            int styleId = new ManageLiteraryStyles().toArray()[literacyStylesComboBox.getSelectedIndex()].getId();
            ManageUsers manageUsers = new ManageUsers();

            if (name.isEmpty() || username.isEmpty() || password.isEmpty() || nif.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(container, "Prencha todos os campos !!");
                return;
            }

            if (!email.matches("[\\w._-]{3,}@[\\w_]{3,}.\\w{2,5}")) {
                JOptionPane.showMessageDialog(container, "Email inválido");
                return;
            }

            if (!phone.matches("[239]\\d{8}")) {
                JOptionPane.showMessageDialog(container, "Número de telefone inválido");
                return;
            }

            if (!nif.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(container, "NIF inválido");
                return;
            }

            if (manageUsers.existsEmail(email)) {
                JOptionPane.showMessageDialog(container, "Email já em uso");
            }

            if (manageUsers.existsNIF(nif)) {
                JOptionPane.showMessageDialog(container, "NIF já em uso");
            }

            ManageAuthors manageAuthors = new ManageAuthors();
            if (manageAuthors.insertAuthor(new Author(-1,
                    name,
                    username,
                    password,
                    email,
                    false,
                    false,
                    3,
                    nif,
                    phone,
                    address,
                    new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                    styleId))) {

                JOptionPane.showMessageDialog(container, "Registado com sucesso");

                JFrame screen = new LoginScreen();
                this.frame.dispose();

            } else {
                JOptionPane.showMessageDialog(container, "Erro ao registar");
            }
        }

        if (e.getSource().equals(reviewerSignupButton)) {
            String name = reviewerNameField.getText();
            String username = reviewerUsernameField.getText();
            String email = reviewerEmailField.getText();
            String password = new String(reviewerPasswordField.getPassword());
            String nif = reviewerNifField.getText();
            String phone = reviewerPhoneField.getText();
            String address = reviewerPhoneField.getText();
            String formation = formationField.getText();
            String specialization = specializationField.getText();
            ManageUsers manageUsers = new ManageUsers();

            if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || nif.isEmpty() || phone.isEmpty() || address.isEmpty() || formation.isEmpty() || specialization.isEmpty()) {
                JOptionPane.showMessageDialog(container, "Prencha todos os campos !!");
                return;
            }

            if (!email.matches("[\\w._-]{3,}@[\\w_]{3,}.\\w{2,5}")) {
                JOptionPane.showMessageDialog(container, "Email inválido");
                return;
            }

            if (!phone.matches("[239]\\d{8}")) {
                JOptionPane.showMessageDialog(container, "Número de telefone inválido");
                return;
            }

            if (!nif.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(container, "NIF inválido");
                return;
            }

            if (manageUsers.existsEmail(email)) {
                JOptionPane.showMessageDialog(container, "Email já em uso");
            }

            if (manageUsers.existsNIF(nif)) {
                JOptionPane.showMessageDialog(container, "NIF já em uso");
            }

            ManageReviewers manageReviewers = new ManageReviewers();
            if (manageReviewers.insertReviewer(new Reviewer(-1,
                    name,
                    username,
                    password,
                    email,
                    false,
                    false,
                    2,
                    nif,
                    phone,
                    address,
                    formation,
                    specialization))) {

                JOptionPane.showMessageDialog(container, "Registado com sucesso");

                JFrame screen = new LoginScreen();
                this.frame.dispose();

            } else {

                JOptionPane.showMessageDialog(container, "Erro ao registar");
            }
        }
    }
}
