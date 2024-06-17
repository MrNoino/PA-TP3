package tp3.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import tp3.controller.ManageAuthors;
import tp3.controller.ManageLiteraryStyles;
import tp3.controller.ManageReviewers;
import tp3.controller.ManageUsers;
import tp3.model.Author;
import tp3.model.EmailWrapper;
import tp3.model.Reviewer;

/**
 * A class that represents a signup screen
 */
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
    JLabel authorProfileImageLabel;
    JButton authorProfileImageButton;

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
    JLabel reviewerProfileImageLabel;
    JButton reviewerProfileImageButton;

    File authorProfileImage = null, reviewerProfileImage = null;

    /**
     * Class constructor that builds the UI
     */
    public SignupScreen() {
        this.frame = this;
        this.container = getContentPane();
        this.container.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Editora");
        this.setMinimumSize(new Dimension(500, 500));
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setIconImage(Components.getLogoIcon().getImage());

        this.container.setBackground(Components.BACKGROUND_COLOR);

        JTabbedPane tabbedPanel = new JTabbedPane();

        JLabel titleAuthorLabel = Components.getHeader("Registo de Autor", Components.Alignment.CENTER),
                titleReviewerLabel = Components.getHeader("Registo de Revisor", Components.Alignment.CENTER);

        GridBagConstraints constraints = new GridBagConstraints();

        // Author Fields
        JPanel authorPanel = new JPanel(new GridBagLayout());
        authorPanel.setBackground(Components.BACKGROUND_COLOR);

        authorProfileImageLabel = Components.getLabel("Foto de Perfil:");
        authorProfileImageButton = Components.getSecondaryButton(null, new Dimension(150, 150), "Escolha a sua foto de perfil");
        authorProfileImageButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../assets/no_profile_image.png")).getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
        authorProfileImageButton.setBorder(BorderFactory.createEmptyBorder());
        authorProfileImageButton.addActionListener(this);
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
        
        JScrollPane authorScrollPane = new JScrollPane(authorPanel);

        // Reviewer Fields
        JPanel reviewerPanel = new JPanel(new GridBagLayout());
        reviewerPanel.setBackground(Components.BACKGROUND_COLOR);

        reviewerProfileImageLabel = Components.getLabel("Foto de Perfil:");
        reviewerProfileImageButton = Components.getSecondaryButton(null, new Dimension(150, 150), "Escolha a sua foto de perfil");
        reviewerProfileImageButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../assets/no_profile_image.png")).getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
        reviewerProfileImageButton.setBorder(BorderFactory.createEmptyBorder());
        reviewerProfileImageButton.addActionListener(this);
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
        JScrollPane reviewerScrollPane = new JScrollPane(reviewerPanel);

        JLabel alreadyHaveAccountLabel1 = Components.getLabel("Já tens conta?"),
                loginLabel1 = Components.getLabel("Autenticar", Components.ACCENT_COLOR);
        loginLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginScreen();
                frame.dispose();
            }
        });

        JLabel alreadyHaveAccountLabel2 = Components.getLabel("Já tens conta?"),
                loginLabel2 = Components.getLabel("Autenticar", Components.ACCENT_COLOR);

        loginLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginScreen();
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
        constraints.insets = Components.getTopInsets(Components.Spacing.MEDIUM);
        authorPanel.add(authorProfileImageLabel, constraints);
        reviewerPanel.add(reviewerProfileImageLabel, constraints);
        
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        authorPanel.add(authorProfileImageButton, constraints);
        reviewerPanel.add(reviewerProfileImageButton, constraints);

        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorNameLabel, constraints);
        reviewerPanel.add(reviewerNameLabel, constraints);

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorNameField, constraints);
        reviewerPanel.add(reviewerNameField, constraints);

        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorUsernameLabel, constraints);
        reviewerPanel.add(reviewerUsernameLabel, constraints);

        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorUsernameField, constraints);
        reviewerPanel.add(reviewerUsernameField, constraints);

        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorEmailLabel, constraints);
        reviewerPanel.add(reviewerEmailLabel, constraints);

        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorEmailField, constraints);
        reviewerPanel.add(reviewerEmailField, constraints);

        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorPasswordLabel, constraints);
        reviewerPanel.add(reviewerPasswordLabel, constraints);

        constraints.gridy = 10;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorPasswordField, constraints);
        reviewerPanel.add(reviewerPasswordField, constraints);

        constraints.gridy = 11;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorNifLabel, constraints);
        reviewerPanel.add(reviewerNifLabel, constraints);

        constraints.gridy = 12;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorNifField, constraints);
        reviewerPanel.add(reviewerNifField, constraints);

        constraints.gridy = 13;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorPhoneLabel, constraints);
        reviewerPanel.add(reviewerPhoneLabel, constraints);

        constraints.gridy = 14;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorPhoneField, constraints);
        reviewerPanel.add(reviewerPhoneField, constraints);

        constraints.gridy = 15;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(authorAddressLabel, constraints);
        reviewerPanel.add(reviewerAddressLabel, constraints);

        constraints.gridy = 16;
        constraints.anchor = GridBagConstraints.CENTER;
        authorPanel.add(authorAddressField, constraints);
        reviewerPanel.add(reviewerAddressField, constraints);

        constraints.gridy = 17;
        constraints.anchor = GridBagConstraints.WEST;
        authorPanel.add(literacyStyleLabel, constraints);
        reviewerPanel.add(formationLabel, constraints);

        constraints.gridy = 18;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        authorPanel.add(literacyStylesComboBox, constraints);
        constraints.fill = GridBagConstraints.NONE;
        reviewerPanel.add(formationField, constraints);

        constraints.gridy = 19;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getTopInsets(Components.Spacing.LARGE);
        authorPanel.add(authorSignupButton, constraints);
        constraints.insets = Components.getEmptyInsets();
        constraints.anchor = GridBagConstraints.WEST;
        reviewerPanel.add(specializationLabel, constraints);

        constraints.gridy = 20;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getTopInsets(Components.Spacing.LARGE);
        authorPanel.add(loginPanel1, constraints);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getEmptyInsets();
        reviewerPanel.add(specializationField, constraints);

        constraints.gridy = 21;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getTopInsets(Components.Spacing.LARGE);
        reviewerPanel.add(reviewerSignupButton, constraints);

        constraints.gridy = 22;
        reviewerPanel.add(loginPanel2, constraints);

        tabbedPanel.add("Autor", authorScrollPane);
        tabbedPanel.add("Revisor", reviewerScrollPane);

        this.container.add(tabbedPanel, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Handles the click events
     *
     * @param e the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(authorSignupButton)) {
            String name = authorNameField.getText();
            String username = authorUsernameField.getText();
            String email = authorEmailField.getText();
            String password = new String(authorPasswordField.getPassword());
            String nif = authorNifField.getText();
            String phone = authorPhoneField.getText();
            String address = authorAddressField.getText();
            int styleId = new ManageLiteraryStyles().toArray()[literacyStylesComboBox.getSelectedIndex()].getId();
            ManageUsers manageUsers = new ManageUsers();

            if (name.isEmpty() || username.isEmpty() || password.isEmpty() || nif.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(container, "Prencha todos os campos", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            if (manageUsers.existsUsername(username)) {
                JOptionPane.showMessageDialog(container, "Nome de utilizador já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            if (!email.matches("[\\w._-]{3,}@[\\w_.]{3,}.\\w{2,5}")) {
                JOptionPane.showMessageDialog(container, "Email inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            if (manageUsers.existsEmail(email)) {
                JOptionPane.showMessageDialog(container, "Email já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            if (!phone.matches("[239]\\d{8}")) {
                JOptionPane.showMessageDialog(container, "Número de telefone inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            if (!nif.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(container, "NIF inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            if (manageUsers.existsNIF(nif)) {
                JOptionPane.showMessageDialog(container, "NIF já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            ManageAuthors manageAuthors = new ManageAuthors();
            try {
                if (manageAuthors.insertAuthor(new Author(-1,
                        name,
                        username,
                        password,
                        email,
                        false,
                        false,
                        3,
                        (this.authorProfileImage != null)? Files.readAllBytes(this.authorProfileImage.toPath()): null,
                        nif,
                        phone,
                        address,
                        new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                        styleId))) {
                    
                    JOptionPane.showMessageDialog(container, "Registado com sucesso");
                    
                    new EmailWrapper().sendMail(email, "Registo Na Editora", "Caro utilizador " + name + ". É com alegria que o recebemos na nossa plataforma");
                    
                    new LoginScreen();
                    this.frame.dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(container, "Erro ao registar", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                }
            } catch (IOException ex) {
                Logger.getLogger(SignupScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource().equals(reviewerSignupButton)) {
            String name = reviewerNameField.getText();
            String username = reviewerUsernameField.getText();
            String email = reviewerEmailField.getText();
            String password = new String(reviewerPasswordField.getPassword());
            String nif = reviewerNifField.getText();
            String phone = reviewerPhoneField.getText();
            String address = reviewerAddressField.getText();
            String formation = formationField.getText();
            String specialization = specializationField.getText();
            ManageUsers manageUsers = new ManageUsers();

            if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || nif.isEmpty() || phone.isEmpty() || address.isEmpty() || formation.isEmpty() || specialization.isEmpty()) {
                JOptionPane.showMessageDialog(container, "Prencha todos os campos", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            } 

            if (manageUsers.existsUsername(username)) {
                JOptionPane.showMessageDialog(container, "Nome de utilizador já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            if (!email.matches("[\\w._-]{3,}@[\\w_.]{3,}.\\w{2,5}")) {
                JOptionPane.showMessageDialog(container, "Email inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            if (manageUsers.existsEmail(email)) {
                JOptionPane.showMessageDialog(container, "Email já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            if (!phone.matches("[239]\\d{8}")) {
                JOptionPane.showMessageDialog(container, "Número de telefone inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            if (!nif.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(container, "NIF inválido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            if (manageUsers.existsNIF(nif)) {
                JOptionPane.showMessageDialog(container, "NIF já em uso", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            ManageReviewers manageReviewers = new ManageReviewers();
            try {
                if (manageReviewers.insertReviewer(new Reviewer(-1,
                        name,
                        username,
                        password,
                        email,
                        false,
                        false,
                        2,
                        (this.reviewerProfileImage != null) ? Files.readAllBytes(this.reviewerProfileImage.toPath()): null,
                        nif,
                        phone,
                        address,
                        formation,
                        specialization))) {
                    
                    JOptionPane.showMessageDialog(container, "Registado com sucesso");
                    
                    new EmailWrapper().sendMail(email, "Registo Na Editora", "Caro utilizador " + name + ". É com alegria que o recebemos na nossa plataforma");
                    
                    new LoginScreen();
                    this.frame.dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(container, "Erro ao registar", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this.frame, "Erro a obter a foto de perfil", "Aviso", JOptionPane.ERROR_MESSAGE, null);
            }
        } else if (e.getSource() == this.authorProfileImageButton) {
            this.getImageFromUser("Author");
        } else if (e.getSource() == this.reviewerProfileImageButton) {
            this.getImageFromUser("Reviewer");
        }
    }
    
    private void getImageFromUser(String userType) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecionar foto de perfil");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //fileChooser.setAcceptAllFileFilterUsed(false);
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        fileChooser.setFileFilter(imageFilter);
        int fileChooserResult = fileChooser.showOpenDialog(this);
        if (fileChooserResult == JFileChooser.APPROVE_OPTION) {
            if(userType.equals("Author")){
                this.authorProfileImage = fileChooser.getSelectedFile();
                this.authorProfileImageButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.authorProfileImage.getAbsolutePath()).getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
            }else{
                this.reviewerProfileImage = fileChooser.getSelectedFile();
               this.reviewerProfileImageButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.reviewerProfileImage.getAbsolutePath()).getScaledInstance(150, 150, Image.SCALE_SMOOTH))); 
            }
        }else if(fileChooserResult == JFileChooser.CANCEL_OPTION){
            if(userType.equals("Author")){
                this.authorProfileImage = null;
                this.authorProfileImageButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../assets/no_profile_image.png")).getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
            }else{
                this.reviewerProfileImage = null;
               this.reviewerProfileImageButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../assets/no_profile_image.png")).getScaledInstance(150, 150, Image.SCALE_DEFAULT))); 
            }
        }
    }
}
