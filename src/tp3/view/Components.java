package tp3.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public final class Components {
    
    private Components(){}

    // Constantes usadas em toda a aplicação
    
    public static final Color BACKGROUND_COLOR = new Color(255, 249, 240);
    public static final Color TEXT_COLOR = new Color(62, 25, 0);
    public static final Color ACCENT_COLOR = new Color(82, 132, 190);
    public static final Color ON_ACCENT_COLOR = new Color(255, 252, 248);
    public static final Color WARNING_COLOR = new Color(168, 123, 10);
    public static final Color DANGER_COLOR = new Color(180, 58, 42);
    public static final int HEADER_TEXT_SIZE = 24;
    public static final int MEDIUM_TEXT_SIZE = 18;
    public static final int SMALL_TEXT_SIZE = 14;
    public static final int SMALL_PADDING = 8;
    public static final int MEDIUM_PADDING = 12;
    public static final int LARGE_PADDING = 16;

    public static enum Alignment {
        START, TOP, CENTER, END, BOTTOM
    }
    
    public static enum Spacing{
        SMALL, MEDIUM, LARGE
    }

    private static int getAlignment(Alignment alignment) {
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
    
    public static int getSpacing(Spacing spacing){
        return switch (spacing) {
            case SMALL -> SMALL_PADDING;
            case MEDIUM -> MEDIUM_PADDING;
            default -> LARGE_PADDING;
        };
    }

    public static JLabel getHeader(String text, Alignment alignment) {
        JLabel label = new JLabel(text, getAlignment(alignment));
        label.setFont(new Font("Arial", Font.PLAIN, HEADER_TEXT_SIZE));
        label.setForeground(ACCENT_COLOR);
        label.setBorder(BorderFactory.createEmptyBorder(LARGE_PADDING, 0, LARGE_PADDING, 0));
        return label;
    }

    public static JLabel getLabel(String text) {
        JLabel label = new JLabel(text, getAlignment(Alignment.START));
        label.setFont(new Font("Arial", Font.PLAIN, MEDIUM_TEXT_SIZE));
        return label;
    }
    
    public static JLabel getLabel(String text, Font font) {
        JLabel label = new JLabel(text, getAlignment(Alignment.START));
        label.setFont(font);
        return label;
    }
    
    public static JLabel getLabel(String text, Color color) {
        JLabel label = new JLabel(text, getAlignment(Alignment.START));
        label.setFont(new Font("Arial", Font.PLAIN, MEDIUM_TEXT_SIZE));
        label.setForeground(color);
        return label;
    }
    
    public static JLabel getLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text, getAlignment(Alignment.START));
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    public static JLabel getLabel(String text, Alignment alignment) {
        JLabel label = new JLabel(text, getAlignment(alignment));
        label.setFont(new Font("Arial", Font.PLAIN, MEDIUM_TEXT_SIZE));
        return label;
    }

    public static JTextField getTextField(String tooltip) {
        return getTextField(new Dimension(300, MEDIUM_PADDING * 2), tooltip);
    }
    
    public static JTextField getTextField(Dimension size, String tooltip) {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, SMALL_TEXT_SIZE));
        field.setPreferredSize(size);
        field.setToolTipText(tooltip);
        return field;
    }

    public static JPasswordField getPasswordField(String tooltip) {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Arial", Font.PLAIN, SMALL_TEXT_SIZE));
        field.setPreferredSize(new Dimension(300, MEDIUM_PADDING * 2));
        field.setToolTipText(tooltip);
        return field;
    }
    
    public static JComboBox getComboBox(String tooltip){
        JComboBox comboBox = new JComboBox();
        comboBox.setSize(500, 50);
        comboBox.setToolTipText(tooltip);
        return comboBox;
    }
    
    public static JComboBox getComboBox(Object[] items, String tooltip){
        JComboBox comboBox = new JComboBox(items);
        comboBox.setSize(500, 50);
        comboBox.setToolTipText(tooltip);
        return comboBox;
    }
    
    public static JButton getPrimaryButton(String text, String tooltip, Color bgColor, Color fgColor){
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Arial", Font.BOLD, SMALL_TEXT_SIZE));
        button.setPreferredSize(new Dimension(300, MEDIUM_PADDING * 2));
        button.setFocusPainted(false);
        button.setToolTipText(tooltip);
        return button;
    }
    
    public static JButton getPrimaryButton(String text, String tooltip){
        return getPrimaryButton(text, tooltip, ACCENT_COLOR, ON_ACCENT_COLOR);
    }
    
    public static JButton getSecondaryButton(String text, String tooltip){
        return getSecondaryButton(text, new Dimension(100, MEDIUM_PADDING * 2), tooltip);
    }
    
    public static JButton getSecondaryButton(String text, Dimension size, String tooltip){
        return getSecondaryButton(text, size, tooltip, null, null);
    }
    
    public static JButton getSecondaryButton(String text, Dimension size, String tooltip, Color bgColor, Color fgColor){
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, SMALL_TEXT_SIZE));
        button.setPreferredSize(size);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setToolTipText(tooltip);
        return button;
    }
    
    
    
    /**
     * Get top spacing insets
     * @return Insets
     */
    public static Insets getTopInsets(Spacing spacing){
        
        int size = getSpacing(spacing);
        
        return new Insets(size, 0, 0, 0);
    }
    
    /**
     * Get vertical spacing insets
     * @return Insets
     */
    public static Insets getVInsets(Spacing spacing){
        
        int size = getSpacing(spacing);
        
        return new Insets(size, 0, size, 0);
    }
    
    public static Insets getEmptyInsets(){
        return new Insets(0, 0, 0, 0);
    }
    
    public static ImageIcon getLogoIcon(){
        return new ImageIcon("src/tp3/assets/editora.jpeg");
    }
}
