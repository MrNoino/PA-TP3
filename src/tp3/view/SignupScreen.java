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
    private JFrame frame;

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

        JLabel authorNameLabel = Components.getLabel("Nome:");
        JTextField authorNameField = Components.getTextField("Insira o nome");
        JLabel authorUsernameLabel = Components.getLabel("Nome de utilizador:");
        JTextField authorUsernameField = Components.getTextField("Insira o nome de utilizador");
        JLabel authorPasswordLabel = Components.getLabel("Palavra Passe:");
        JPasswordField authorPasswordField = Components.getPasswordField("Insira a palavra-passe");
        JLabel authorNifLabel = Components.getLabel("NIF:");
        JTextField authorNifField = Components.getTextField("Insira o NIF");
        JLabel authorPhoneLabel = Components.getLabel("Telefone:");
        JTextField authorPhoneField = Components.getTextField("Insira o numero de telefone");
        JLabel authorAddressLabel = Components.getLabel("Morada:");
        JTextField authorAddressField = Components.getTextField("Insira a morada");
        JButton authorSignupButton = Components.getPrimaryButton("Registar");
        JLabel literacyStyleLabel = Components.getLabel("Estilo Literário:");
        JComboBox literacyStylesComboBox = new JComboBox();
        literacyStylesComboBox.addItem("Drama");
        literacyStylesComboBox.addItem("Ação");
        literacyStylesComboBox.addItem("Ficção Cientifica");
        literacyStylesComboBox.addItem("Romance");
        literacyStylesComboBox.addItem("Comedia");
        literacyStylesComboBox.addItem("Artigo Cientifico");
        literacyStylesComboBox.setSize(500, 50);

        // Reviewer Fields
        JPanel reviewerPanel = new JPanel(new GridBagLayout());
        reviewerPanel.setBackground(Components.BACKGROUND_COLOR);
        JLabel reviewerNameLabel = Components.getLabel("Nome");
        JTextField reviewerNameField = Components.getTextField("Insira o nome");
        JLabel reviewerUsernameLabel = Components.getLabel("Nome de utilizador");
        JTextField reviewerUsernameField = Components.getTextField("Insira o nome de utilizador");
        JLabel reviewerPasswordLabel = Components.getLabel("Palavra Passe");
        JPasswordField reviewerPasswordField = Components.getPasswordField("Insira a palavra-passe");
        JLabel reviewerNifLabel = Components.getLabel("NIF");
        JTextField reviewerNifField = Components.getTextField("Insira o NIF");
        JLabel reviewerPhoneLabel = Components.getLabel("Telefone");
        JTextField reviewerPhoneField = Components.getTextField("Insira o numero de telefone");
        JLabel reviewerAddressLabel = Components.getLabel("Morada");
        JTextField reviewerAddressField = Components.getTextField("Insira a morada");
        JLabel formationLabel = Components.getLabel("Formação Académica");
        JTextField formationField = Components.getTextField("Insira a formação académica");
        JLabel specializationLabel = Components.getLabel("Especialização");
        JTextField specializationField = Components.getTextField("Insira a especialização");
        JButton reviewerSignupButton = Components.getPrimaryButton("Registar");
        
        JLabel alreadyHaveAccountLabel1 = Components.getLabel("Já tens conta?"),
                loginLabel1 = Components.getLabel("Autenticar", Components.ACCENT_COLOR);
        loginLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame signupFrame = new LoginScreen();
                signupFrame.setSize(500, 500);
                signupFrame.setLocationRelativeTo(null);
                signupFrame.setVisible(true);
                frame.dispose();
            }
        });
        
        JLabel alreadyHaveAccountLabel2 = Components.getLabel("Já tens conta?"),
                loginLabel2 = Components.getLabel("Autenticar", Components.ACCENT_COLOR);
        
        loginLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame signupFrame = new LoginScreen();
                signupFrame.setSize(500, 500);
                signupFrame.setLocationRelativeTo(null);
                signupFrame.setVisible(true);
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
        authorPanel.add(authorPasswordLabel, constraints);
        reviewerPanel.add(reviewerPasswordLabel, constraints);

        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorPasswordField, constraints);
        reviewerPanel.add(reviewerPasswordField, constraints);

        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorNifLabel, constraints);
        reviewerPanel.add(reviewerNifLabel, constraints);

        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorNifField, constraints);
        reviewerPanel.add(reviewerNifField, constraints);

        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorPhoneLabel, constraints);
        reviewerPanel.add(reviewerPhoneLabel, constraints);

        constraints.gridy = 10;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorPhoneField, constraints);
        reviewerPanel.add(reviewerPhoneField, constraints);

        constraints.gridy = 11;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorAddressLabel, constraints);
        reviewerPanel.add(reviewerAddressLabel, constraints);

        constraints.gridy = 12;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorAddressField, constraints);
        reviewerPanel.add(reviewerAddressField, constraints);

        constraints.gridy = 13;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(literacyStyleLabel, constraints);
        reviewerPanel.add(formationLabel, constraints);

        constraints.gridy = 14;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        authorPanel.add(literacyStylesComboBox, constraints);
        constraints.fill = GridBagConstraints.NONE;
        reviewerPanel.add(formationField, constraints);

        constraints.gridy = 15;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        authorPanel.add(authorSignupButton, constraints);
        constraints.insets = Components.getEmptyInsets();
        constraints.anchor = GridBagConstraints.WEST;
        reviewerPanel.add(specializationLabel, constraints);

        constraints.gridy = 16;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        authorPanel.add(loginPanel1, constraints);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getEmptyInsets();
        reviewerPanel.add(specializationField, constraints);

        constraints.gridy = 17;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        reviewerPanel.add(reviewerSignupButton, constraints);
        
        constraints.gridy = 18;
        reviewerPanel.add(loginPanel2, constraints);

        tabbedPanel.add("Autor", authorPanel);
        tabbedPanel.add("Revisor", reviewerPanel);

        this.container.add(tabbedPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(signupButton)) {

        }
    }
}
