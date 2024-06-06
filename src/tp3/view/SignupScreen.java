package tp3.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class SignupScreen extends JFrame implements ActionListener {

    private Container container;
    private JButton signupButton;

    public SignupScreen() {
        this.container = getContentPane();
        this.container.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Registo");
        this.setMinimumSize(new Dimension());

        Components components = new Components();

        this.container.setBackground(components.BACKGROUND_COLOR);

        JTabbedPane pane = new JTabbedPane();
        pane.setPreferredSize(new Dimension(500, 800));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;

        // Author Fields
        JPanel authorPanel = new JPanel(new GridBagLayout());
        authorPanel.setBackground(components.BACKGROUND_COLOR);

        JLabel authorNameLabel = components.getLabel("Nome:");
        JTextField authorNameField = components.getTextField("Insira o nome");
        JLabel authorUsernameLabel = components.getLabel("Nome de utilizador:");
        JTextField authorUsernameField = components.getTextField("Insira o nome de utilizador");
        JLabel authorPasswordLabel = components.getLabel("Palavra Passe:");
        JPasswordField authorPasswordField = components.getPasswordField("Insira a palavra-passe");
        JLabel authorNifLabel = components.getLabel("NIF:");
        JTextField authorNifField = components.getTextField("Insira o NIF");
        JLabel authorPhoneLabel = components.getLabel("Telefone:");
        JTextField authorPhoneField = components.getTextField("Insira o numero de telefone");
        JLabel authorAddressLabel = components.getLabel("Morada:");
        JTextField authorAddressField = components.getTextField("Insira a morada");
        JButton authorSignupButton = components.getPrimaryButton("Registar");
        JLabel literacyStyleLabel = components.getLabel("Estilo Literário:");
        JComboBox literacyStylesComboBox = new JComboBox();
        literacyStylesComboBox.setSize(500, literacyStylesComboBox.getHeight());
        literacyStylesComboBox.addItem("Drama");
        literacyStylesComboBox.addItem("Ação");
        literacyStylesComboBox.addItem("Ficção Cientifica");
        literacyStylesComboBox.addItem("Romance");
        literacyStylesComboBox.addItem("Comedia");
        literacyStylesComboBox.addItem("Artigo Cientifico");

        // Reviewer Fields
        JPanel reviewerPanel = new JPanel(new GridBagLayout());
        reviewerPanel.setBackground(components.BACKGROUND_COLOR);
        JLabel reviewerNameLabel = components.getLabel("Nome");
        JTextField reviewerNameField = components.getTextField("Insira o nome");
        JLabel reviewerUsernameLabel = components.getLabel("Nome de utilizador");
        JTextField reviewerUsernameField = components.getTextField("Insira o nome de utilizador");
        JLabel reviewerPasswordLabel = components.getLabel("Palavra Passe");
        JPasswordField reviewerPasswordField = components.getPasswordField("Insira a palavra-passe");
        JLabel reviewerNifLabel = components.getLabel("NIF");
        JTextField reviewerNifField = components.getTextField("Insira o NIF");
        JLabel reviewerPhoneLabel = components.getLabel("Telefone");
        JTextField reviewerPhoneField = components.getTextField("Insira o numero de telefone");
        JLabel reviewerAddressLabel = components.getLabel("Morada");
        JTextField reviewerAddressField = components.getTextField("Insira a morada");
        JLabel formationLabel = components.getLabel("Formação Académica");
        JTextField formationField = components.getTextField("Insira a formação académica");
        JLabel specializationLabel = components.getLabel("Especialização");
        JTextField specializationField = components.getTextField("Insira a especialização");
        JButton reviewerSignupButton = components.getPrimaryButton("Registar");

        constraints.gridy = 0;
        authorPanel.add(authorNameLabel, constraints);
        reviewerPanel.add(reviewerNameLabel, constraints);

        constraints.gridy = 1;
        authorPanel.add(authorNameField, constraints);
        reviewerPanel.add(reviewerNameField, constraints);

        constraints.gridy = 2;
        authorPanel.add(authorUsernameLabel, constraints);
        reviewerPanel.add(reviewerUsernameLabel, constraints);

        constraints.gridy = 3;
        authorPanel.add(authorUsernameField, constraints);
        reviewerPanel.add(reviewerUsernameField, constraints);

        constraints.gridy = 4;
        authorPanel.add(authorPasswordLabel, constraints);
        reviewerPanel.add(reviewerPasswordLabel, constraints);

        constraints.gridy = 5;
        authorPanel.add(authorPasswordField, constraints);
        reviewerPanel.add(reviewerPasswordField, constraints);

        constraints.gridy = 6;
        authorPanel.add(authorNifLabel, constraints);
        reviewerPanel.add(reviewerNifLabel, constraints);

        constraints.gridy = 7;
        authorPanel.add(authorNifField, constraints);
        reviewerPanel.add(reviewerNifField, constraints);

        constraints.gridy = 8;
        authorPanel.add(authorPhoneLabel, constraints);
        reviewerPanel.add(reviewerPhoneLabel, constraints);

        constraints.gridy = 9;
        authorPanel.add(authorPhoneField, constraints);
        reviewerPanel.add(reviewerPhoneField, constraints);

        constraints.gridy = 10;
        authorPanel.add(authorAddressLabel, constraints);
        reviewerPanel.add(reviewerAddressLabel, constraints);

        constraints.gridy = 11;
        authorPanel.add(authorAddressField, constraints);
        reviewerPanel.add(reviewerAddressField, constraints);

        constraints.gridy = 12;
        authorPanel.add(literacyStyleLabel, constraints);
        reviewerPanel.add(formationLabel, constraints);

        constraints.gridy = 13;
        authorPanel.add(literacyStylesComboBox, constraints);
        reviewerPanel.add(formationField, constraints);

        constraints.insets = components.getVInsets(Components.Spacing.LARGE);

        constraints.gridy = 14;
        authorPanel.add(authorSignupButton, constraints);
        reviewerPanel.add(specializationLabel, constraints);

        constraints.gridy = 15;
        reviewerPanel.add(specializationField, constraints);

        constraints.insets = components.getVInsets(Components.Spacing.LARGE);

        constraints.gridy = 16;
        reviewerPanel.add(reviewerSignupButton, constraints);

        pane.add("Autor", authorPanel);
        pane.add("Revisor", reviewerPanel);

        this.container.add(pane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(signupButton)) {

        }
    }
}
