package tp3.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{
    private Container container;
    public Login(){
        this.container = getContentPane();
        this.container.setLayout (new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Editora");
        this.setMinimumSize(new Dimension(500, 500));
        
        JLabel titleLabel = new JLabel("Iniciar Sessão", (int)CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setForeground(new Color(70, 130, 180));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        JLabel usernameLabel = new JLabel("Nome de Utilizador:", (int)LEFT_ALIGNMENT),
                passwordLabel = new JLabel("Palavra Passe:", (int)LEFT_ALIGNMENT);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        JTextField usernameTextField = new JTextField(30);
        JPasswordField passwordField = new JPasswordField(30);
        usernameTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        JButton loginButton = new JButton("Autenticar");
        loginButton.setBackground(Color.white);
        loginButton.setForeground(new Color(60, 179, 113));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 20));
        loginButton.setPreferredSize(new Dimension(200, 30));
        
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBadConstraints = new GridBagConstraints();
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 0;
        gridBadConstraints.anchor = GridBagConstraints.WEST;
        centerPanel.add(usernameLabel, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 1;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        centerPanel.add(usernameTextField, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 2;
        gridBadConstraints.anchor = GridBagConstraints.WEST;
        gridBadConstraints.insets = new Insets(20,0,0,0);
        centerPanel.add(passwordLabel, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 3;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = new Insets(0,0,0,0);
        centerPanel.add(passwordField, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 4;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = new Insets(20,0,0,0);
        centerPanel.add(loginButton, gridBadConstraints);
        
        this.container.add(titleLabel, BorderLayout.NORTH);
        this.container.add(centerPanel, BorderLayout.CENTER);
        
    }

}
