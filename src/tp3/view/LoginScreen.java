package tp3.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import tp3.controller.ManageUsers;
import tp3.model.User;

public class LoginScreen extends JFrame implements ActionListener {

    private Container container;
    private JButton loginButton;
    private JFrame frame;
    private JTextField usernameTextField;
    private JPasswordField passwordField;

    public LoginScreen() {
        this.frame = this;
        this.container = getContentPane();
        this.container.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Editora");
        this.setMinimumSize(new Dimension(500, 500));
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setIconImage(Components.getLogoIcon().getImage());

        this.container.setBackground(Components.BACKGROUND_COLOR);

        JLabel titleLabel = Components.getHeader("Iniciar Sessão", Components.Alignment.CENTER);
        JLabel usernameLabel = Components.getLabel("Nome de Utilizador:");
        JLabel passwordLabel = Components.getLabel("Palavra Passe:");

        this.usernameTextField = Components.getTextField("Insira o seu nome de utilizador");
        this.passwordField = Components.getPasswordField("Insira a sua palavra passe");

        loginButton = Components.getPrimaryButton("Iniciar Sessão", "Autenticar o utilizador");
        loginButton.addActionListener(this);

        JLabel dontHaveAccountLabel = Components.getLabel("Ainda não tem conta?"),
                registerLabel = Components.getLabel("Registar", Components.ACCENT_COLOR);
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame signupFrame = new SignupScreen();
                frame.dispose();
            }
        });

        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registerPanel.setBackground(Components.BACKGROUND_COLOR);

        registerPanel.add(dontHaveAccountLabel);
        registerPanel.add(registerLabel);

        GridBagConstraints gridBadConstraints = new GridBagConstraints();
        gridBadConstraints.gridy = 0;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        this.container.add(titleLabel, gridBadConstraints);
        gridBadConstraints.gridy = 1;
        gridBadConstraints.anchor = GridBagConstraints.WEST;
        gridBadConstraints.insets = new Insets(0, 0, 0, 0);
        this.container.add(usernameLabel, gridBadConstraints);
        gridBadConstraints.gridy = 2;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        this.container.add(this.usernameTextField, gridBadConstraints);
        gridBadConstraints.gridy = 3;
        gridBadConstraints.anchor = GridBagConstraints.WEST;
        gridBadConstraints.insets = Components.getVInsets(Components.Spacing.MEDIUM);
        this.container.add(passwordLabel, gridBadConstraints);
        gridBadConstraints.gridy = 4;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = new Insets(0, 0, 0, 0);
        this.container.add(this.passwordField, gridBadConstraints);
        gridBadConstraints.gridy = 5;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        this.container.add(loginButton, gridBadConstraints);
        gridBadConstraints.gridy = 6;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = new Insets(10, 0, 0, 0);
        this.container.add(registerPanel, gridBadConstraints);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.loginButton)) {
            String username = this.usernameTextField.getText(),
                    password = new String(this.passwordField.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this.container, "Campos vazios", "Aviso", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ManageUsers manageUsers = new ManageUsers();
            User user = manageUsers.login(username, password);
            if (user == null) {
                JOptionPane.showMessageDialog(this.container, "Credenciais inválidas", "Aviso", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(this.container, "Bem vindo " + user.getUsername(), "Autenticado", JOptionPane.INFORMATION_MESSAGE);
            Main.login(user);
            JFrame userFrame;
            switch (user.getRoleId()) {
                case 1:
                    userFrame = new ManagerScreen();
                    break;
                case 2:
                    userFrame = new ReviewerScreen();
                    break;
                case 3:
                    userFrame = new AuthorScreen();
                    break;
                default:
                    break;  
            }
            this.dispose();
        }
    }

}
