package tp3.view;

import java.awt.Color;
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

public class Login extends JFrame implements ActionListener{
    private Container container;
    private JButton loginButton;
    public Login(){
        
        this.container = getContentPane();
        this.container.setLayout (new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Editora");
        this.setMinimumSize(new Dimension(500, 500));
        
        JLabel titleLabel = new JLabel("Iniciar Sessão", (int)CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setForeground(new Color(70, 130, 180));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        JLabel usernameLabel = new JLabel("Nome de Utilizador:"),
                passwordLabel = new JLabel("Palavra Passe:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        
        JTextField usernameTextField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        usernameTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameTextField.setPreferredSize(new Dimension(300, 25));
        passwordField.setPreferredSize(new Dimension(300, 25));
        usernameTextField.setToolTipText("Insira o seu nome de utilizador");
        usernameTextField.setToolTipText("Insira a sua palavra passe");
        
        loginButton = new JButton("Autenticar");
        loginButton.setBackground(Color.white);
        loginButton.setForeground(new Color(100, 149, 237));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 20));
        loginButton.setPreferredSize(new Dimension(300, 30));
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237)));
        loginButton.addActionListener(this);
        loginButton.setFocusPainted(false);
        
        JLabel dontHaveAccountLabel = new JLabel("Ainda não tens conta?"),
                registerLabel = new JLabel("Registar");
        registerLabel.setForeground(new Color(30, 144, 255));
        registerLabel.addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent e)  
            {  
               JOptionPane.showMessageDialog(getContentPane(), "Label clicado");
            }  
        }); 
        
        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        registerPanel.add(dontHaveAccountLabel);
        registerPanel.add(registerLabel);
        
        GridBagConstraints gridBadConstraints = new GridBagConstraints();
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 0;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = new Insets(0,0,20,0);
        this.container.add(titleLabel, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 1;
        gridBadConstraints.anchor = GridBagConstraints.WEST;
        gridBadConstraints.insets = new Insets(0,0,0,0);
        this.container.add(usernameLabel, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 2;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        this.container.add(usernameTextField, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 3;
        gridBadConstraints.anchor = GridBagConstraints.WEST;
        gridBadConstraints.insets = new Insets(20,0,0,0);
        this.container.add(passwordLabel, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 4;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = new Insets(0,0,0,0);
        this.container.add(passwordField, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 5;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = new Insets(30,0,0,0);
        this.container.add(loginButton, gridBadConstraints);
        gridBadConstraints.gridx = 0;
        gridBadConstraints.gridy = 6;
        gridBadConstraints.anchor = GridBagConstraints.CENTER;
        gridBadConstraints.insets = new Insets(10,0,0,0);
        this.container.add(registerPanel, gridBadConstraints);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(loginButton)) {
            JOptionPane.showMessageDialog(this.container, "Botão clicado");
        }
    }

}
