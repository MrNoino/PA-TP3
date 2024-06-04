package tp3.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Components {

    // Constantes usadas em toda a aplicação
    
    final Color BACKGROUND_COLOR = new Color(255, 249, 240);
    final Color TEXT_COLOR = new Color(62, 25, 0);
    final Color ACCENT_COLOR = new Color(82, 132, 190);
    final Color ON_ACCENT_COLOR = new Color(255, 252, 248);
    final int HEADER_TEXT_SIZE = 24;
    final int MEDIUM_TEXT_SIZE = 18;
    final int SMALL_TEXT_SIZE = 14;
    final int SMALL_PADDING = 8;
    final int MEDIUM_PADDING = 12;
    final int LARGE_PADDING = 16;

    public enum Alignment {
        START, TOP, CENTER, END, BOTTOM
    }
    
    public enum Spacing{
        SMALL, MEDIUM, LARGE
    }

    private int getAlignment(Alignment alignment) {
        switch (alignment) {
            case START -> {
                return (int) Component.LEFT_ALIGNMENT;
            }
            case TOP -> {
                return (int) Component.TOP_ALIGNMENT;
            }
            case CENTER -> {
                return (int) Component.CENTER_ALIGNMENT;
            }
            case END -> {
                return (int) Component.RIGHT_ALIGNMENT;
            }
            default -> {
                return (int) Component.BOTTOM_ALIGNMENT;
            }
        }
    }

    public JLabel getHeader(String text, Alignment alignment) {
        JLabel label = new JLabel(text, getAlignment(alignment));
        label.setFont(new Font("Arial", Font.PLAIN, HEADER_TEXT_SIZE));
        label.setForeground(ACCENT_COLOR);
        label.setBorder(BorderFactory.createEmptyBorder(LARGE_PADDING, 0, LARGE_PADDING, 0));
        return label;
    }

    public JLabel getLabel(String text) {
        JLabel label = new JLabel(text, getAlignment(Alignment.START));
        label.setFont(new Font("Arial", Font.PLAIN, MEDIUM_TEXT_SIZE));
        return label;
    }

    public JLabel getLabel(String text, Alignment alignment) {
        JLabel label = new JLabel(text, getAlignment(alignment));
        label.setFont(new Font("Arial", Font.PLAIN, MEDIUM_TEXT_SIZE));
        return label;
    }

    public JTextField getTextField(String tooltip) {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, SMALL_TEXT_SIZE));
        field.setPreferredSize(new Dimension(300, MEDIUM_PADDING * 2));
        field.setToolTipText(tooltip);
        return field;
    }

    public JPasswordField getPasswordField(String tooltip) {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Arial", Font.PLAIN, SMALL_TEXT_SIZE));
        field.setPreferredSize(new Dimension(300, MEDIUM_PADDING * 2));
        field.setToolTipText(tooltip);
        return field;
    }
    
    public JButton getPrimaryButton(String text){
        JButton button = new JButton(text);
        button.setBackground(ACCENT_COLOR);
        button.setForeground(ON_ACCENT_COLOR);
        button.setFont(new Font("Arial", Font.BOLD, SMALL_TEXT_SIZE));
        button.setPreferredSize(new Dimension(300, MEDIUM_PADDING * 2));
        button.setFocusPainted(false);
        return button;
    }
    
    /**
     * Get vertical spacing insets
     * @return Insets
     */
    public Insets getVInsets(Spacing spacing){
        
        int size = 0;
        
        switch(spacing){
            case SMALL:
                size = SMALL_PADDING;
                break;
            case MEDIUM:
                size = MEDIUM_PADDING;
                break;
            default:
                size = LARGE_PADDING;
                break;
        }
        
        return new Insets(size, 0, 0, 0);
    }
}
