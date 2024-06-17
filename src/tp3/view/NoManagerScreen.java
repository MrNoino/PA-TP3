package tp3.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import tp3.controller.ManageManagers;
import tp3.model.EmailWrapper;
import tp3.model.Manager;

/**
 * A class that represents the no manager screen
 */
public class NoManagerScreen extends JFrame implements ActionListener {

    private Container container;
    private JFrame frame;
    private JTextField nameField, usernameField, emailField;
    private JPasswordField passwordField;
    private JButton signupButton;

    /**
     * A constructor class that builds the UI
     */
    public NoManagerScreen() {
        this.frame = this;
        ManageManagers manageManagers = new ManageManagers();
        if (manageManagers.getTotalManagers() > 0) {
            this.openLoginFrame();
            return;
        }
        this.container = getContentPane();
        this.setTitle("Editora");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 600));
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setIconImage(Components.getLogoIcon().getImage());

        this.container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel titleLabel = Components.getHeader("Registo de Gestor", Components.Alignment.CENTER),
                nameLabel = Components.getLabel("Nome:"),
                usernameLabel = Components.getLabel("Nome de Utilizador:"),
                passwordLabel = Components.getLabel("Palavra Passe:"),
                emailLabel = Components.getLabel("Email:");

        this.nameField = Components.getTextField("Insira o seu nome");
        this.usernameField = Components.getTextField("Insira o seu nome de utilizador");
        this.emailField = Components.getTextField("Insira o seu email");
        this.passwordField = Components.getPasswordField("Insira a palavra passe");

        this.signupButton = Components.getPrimaryButton("Registar", "Registar o Gestor");
        this.signupButton.addActionListener(this);

        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        this.container.add(titleLabel, constraints);

        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        this.container.add(nameLabel, constraints);

        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        this.container.add(nameField, constraints);

        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.WEST;
        this.container.add(usernameLabel, constraints);

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getEmptyInsets();
        this.container.add(usernameField, constraints);

        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.WEST;
        this.container.add(passwordLabel, constraints);

        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.WEST;
        this.container.add(passwordField, constraints);

        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.WEST;
        this.container.add(emailLabel, constraints);

        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.WEST;
        this.container.add(emailField, constraints);

        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getTopInsets(Components.Spacing.LARGE);
        this.container.add(signupButton, constraints);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Handles the click events
     *
     * @param e the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.signupButton)) {
            String name = this.nameField.getText(),
                    username = this.usernameField.getText(),
                    email = this.emailField.getText(),
                    password = new String(this.passwordField.getPassword());

            if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this.frame, "Campos Vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            ManageManagers manageManagers = new ManageManagers();

            boolean inserted = manageManagers.insertManager(new Manager(-1,
                    name,
                    username,
                    password,
                    email,
                    true,
                    false,
                    1,
                    null));

            if (!inserted) {
                JOptionPane.showMessageDialog(this.frame, "Não inserido", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }
            JOptionPane.showMessageDialog(this.frame, "Inserido com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);
            
            new EmailWrapper().sendMail(email, "Registo Na Editora", "Caro utilizador " + name + ". É com alegria que o recebemos na nossa plataforma");
            
            this.openLoginFrame();
        }

    }

    /**
     * Opens the login screen
     */
    private void openLoginFrame() {
        JFrame loginFrame = new LoginScreen();
        this.dispose();
    }
}
