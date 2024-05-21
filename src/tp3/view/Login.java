package tp3.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;

public class Login extends JFrame{
    private Container container;
    public Login(){
        this.container = getContentPane();
        this.container.setLayout (new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Iniciar Sessão");
        this.setMinimumSize(new Dimension(500, 500));
    }

}
