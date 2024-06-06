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

public class Login extends JFrame implements ActionListener {

    private Container container;
    private JButton loginButton;
    private JFrame frame;

    public Login() {
        this.frame = this;
        this.container = getContentPane();
        this.container.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Editora");
        this.setMinimumSize(new Dimension(500, 500));

        this.container.setBackground(Components.BACKGROUND_COLOR);

        JLabel titleLabel = Components.getHeader("Iniciar Sessão", Components.Alignment.CENTER);
        JLabel usernameLabel = Components.getLabel("Nome de Utilizador:");
        JLabel passwordLabel = Components.getLabel("Palavra Passe:");

        JTextField usernameTextField = Components.getTextField("Insira o seu nome de utilizador");
        JPasswordField passwordField = Components.getPasswordField("Insira a sua palavra passe");

        loginButton = Components.getPrimaryButton("Iniciar Sessão");
        loginButton.addActionListener(this);

        JLabel dontHaveAccountLabel = Components.getLabel("Ainda não tem conta?"),
                registerLabel = Components.getLabel("Registar", Components.ACCENT_COLOR);
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame signupFrame = new SignupScreen();
                signupFrame.setSize(500, 500);
                signupFrame.setLocationRelativeTo(null);
                signupFrame.setVisible(true);
                frame.dispose();
            }
        });

        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registerPanel.setBackground(Components.BACKGROUND_COLOR);

        registerPanel.add(dontHaveAccountLabel);
        registerPanel.add(registerLabel);

        GridBagConstraints gridBadConstraints = new GridBagConstraints();
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 0;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        this.container.add(titleLabel, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 1;
        gridBadConstraints.anchor = GridBagConstraints.WEST;
        gridBadConstraints.insets = new Insets(0, 0, 0, 0);
        this.container.add(usernameLabel, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 2;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        this.container.add(usernameTextField, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 3;
        gridBadConstraints.anchor = GridBagConstraints.WEST;
        gridBadConstraints.insets = Components.getVInsets(Components.Spacing.MEDIUM);
        this.container.add(passwordLabel, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 4;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = new Insets(0, 0, 0, 0);
        this.container.add(passwordField, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 5;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = Components.getVInsets(Components.Spacing.LARGE);
        this.container.add(loginButton, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 6;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = new Insets(10, 0, 0, 0);
        this.container.add(registerPanel, gridBadConstraints);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(loginButton)) {
            JOptionPane.showMessageDialog(this.container, "Botão clicado");
        }
    }

}
