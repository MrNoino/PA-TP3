package tp3.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 * A class that represents a email properties screen
 */
public class EmailPropertiesScreen extends JFrame implements ActionListener {

    private Container container;
    private JFrame frame;
    private JButton saveButton;
    private JTextField emailField;
    private JPasswordField passwordField;

    /**
     * Class constructor that builds the UI
     */
    public EmailPropertiesScreen() {
        this.frame = this;
        File file = new File("email.cfg.dat");
        if (file.exists()) {
            this.openNoManagerFrame();
            return;
        }
        this.setTitle("Editora");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 500));
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setIconImage(Components.getLogoIcon().getImage());

        this.container = getContentPane();
        this.container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel titleLabel = Components.getHeader("Configuração de Email", Components.Alignment.CENTER),
                emailLabel = Components.getLabel("Email:"),
                passwordLabel = Components.getLabel("Palavra Passe: ");

        this.emailField = Components.getTextField("Insira o email");
        this.passwordField = Components.getPasswordField("Insira a palavra passe");

        this.saveButton = Components.getPrimaryButton("Guardar", "Gaurdar as informações de email");
        this.saveButton.addActionListener(this);

        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getTopInsets(Components.Spacing.LARGE);
        this.container.add(titleLabel, constraints);

        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.LARGE);
        this.container.add(emailLabel, constraints);

        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        this.container.add(this.emailField, constraints);

        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = Components.getTopInsets(Components.Spacing.LARGE);
        this.container.add(passwordLabel, constraints);

        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getEmptyInsets();
        this.container.add(this.passwordField, constraints);

        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = Components.getTopInsets(Components.Spacing.LARGE);
        this.container.add(this.saveButton, constraints);

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
        if (e.getSource().equals(this.saveButton)) {
            String email = this.emailField.getText(),
                    password = new String(this.passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this.container, "Campos Vazios", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                return;
            }

            ArrayList<String> emailConfig = new ArrayList<String>(Arrays.asList(email, password));
            File file = new File("email.cfg.dat");
            try {
                ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(file));
                objectStream.writeObject(emailConfig);
                objectStream.close();
                JOptionPane.showMessageDialog(this.container, "Gravado com  sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE, null);
                this.openNoManagerFrame();
            } catch (IOException error) {
                JOptionPane.showMessageDialog(this.container, "Não gravado", "Aviso", JOptionPane.ERROR_MESSAGE, null);
            }
        }
    }

    /**
     * Opens the No Manager screen
     */
    private void openNoManagerFrame() {
        JFrame noManagerFrame = new NoManagerScreen();
        this.dispose();
    }
}
