package tp3.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
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

    public Login() {

        this.container = getContentPane();
        this.container.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Editora");
        this.setMinimumSize(new Dimension(500, 500));

        Components components = new Components();

        this.container.setBackground(components.BACKGROUND_COLOR);

        JLabel titleLabel = components.getHeader("Iniciar Sessão", Components.Alignment.CENTER);
        JLabel usernameLabel = components.getLabel("Nome de Utilizador:");
        JLabel passwordLabel = components.getLabel("Palavra Passe:");

        JTextField usernameTextField = components.getTextField("Insira o seu nome de utilizador");
        JPasswordField passwordField = components.getPasswordField("Insira a sua palavra passe");

        loginButton = components.getPrimaryButton("Iniciar Sessão");
        loginButton.addActionListener(this);

        JLabel dontHaveAccountLabel = new JLabel("Ainda não tem conta?"),
                registerLabel = new JLabel("Registar");
        registerLabel.setForeground(components.ACCENT_COLOR);
        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new SignupScreen();
                frame.setSize(500, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registerPanel.setBackground(components.BACKGROUND_COLOR);

        registerPanel.add(dontHaveAccountLabel);
        registerPanel.add(registerLabel);

        GridBagConstraints gridBadConstraints = new GridBagConstraints();
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 0;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = components.getVInsets(Components.Spacing.LARGE);
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
        gridBadConstraints.insets = components.getVInsets(Components.Spacing.MEDIUM);
        this.container.add(passwordLabel, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 4;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = new Insets(0, 0, 0, 0);
        this.container.add(passwordField, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 5;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = components.getVInsets(Components.Spacing.LARGE);
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
