package tp3.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SignupScreen extends JFrame implements ActionListener {

    private Container container;
    private JButton signupButton;

    public SignupScreen() {
        this.container = getContentPane();
        this.container.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Registo");
        this.setMinimumSize(new Dimension());

        Components components = new Components();
        
        this.container.setBackground(components.BACKGROUND_COLOR);

        JLabel hello_label = components.getHeader("Hello World", Components.Alignment.START);

        this.container.add(hello_label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(signupButton)) {

        }
    }
}
