package tp3.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A class that contains components and other UI related content
 */
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

    /**
     * The alignment types
     */
    public static enum Alignment {
        START, TOP, CENTER, END, BOTTOM
    }
    
    /**
     * The spacing types
     */
    public static enum Spacing{
        SMALL, MEDIUM, LARGE
    }

    /**
     * Gets the equivalent java swing alignment
     * @param alignment the component alignment
     * @return the java swing alignment
     */
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

    /**
     * Gets the spacing size
     * @param spacing the spacing
     * @return a spacing size
     */
    public static int getSpacing(Spacing spacing){
        return switch (spacing) {
            case SMALL -> SMALL_PADDING;
            case MEDIUM -> MEDIUM_PADDING;
            default -> LARGE_PADDING;
        };
    }

    /**
     * Gets a label with a header size
     * @param text the label text
     * @param alignment the alignment
     * @return a header
     */
    public static JLabel getHeader(String text, Alignment alignment) {
        JLabel label = new JLabel(text, getAlignment(alignment));
        label.setFont(new Font("Arial", Font.PLAIN, HEADER_TEXT_SIZE));
        label.setForeground(ACCENT_COLOR);
        label.setBorder(BorderFactory.createEmptyBorder(LARGE_PADDING, 0, LARGE_PADDING, 0));
        return label;
    }

    /**
     * Gets a label
     * @param text the label text
     * @return a label
     */
    public static JLabel getLabel(String text) {
        JLabel label = new JLabel(text, getAlignment(Alignment.START));
        label.setFont(new Font("Arial", Font.PLAIN, MEDIUM_TEXT_SIZE));
        return label;
    }
    
    /**
     * Gets a label
     * @param text the label text
     * @param font the font to be used
     * @return a label
     */
    public static JLabel getLabel(String text, Font font) {
        JLabel label = new JLabel(text, getAlignment(Alignment.START));
        label.setFont(font);
        return label;
    }
    
    /**
     * Gets a label
     * @param text the label text
     * @param color the label color
     * @return a label
     */
    public static JLabel getLabel(String text, Color color) {
        JLabel label = new JLabel(text, getAlignment(Alignment.START));
        label.setFont(new Font("Arial", Font.PLAIN, MEDIUM_TEXT_SIZE));
        label.setForeground(color);
        return label;
    }
    
    /**
     * Gets a label
     * @param text the label text
     * @param font the label font
     * @param color the label color
     * @return a label
     */
    public static JLabel getLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text, getAlignment(Alignment.START));
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    /**
     * Gets a label
     * @param text the label text
     * @param alignment the label alignment
     * @return a label
     */
    public static JLabel getLabel(String text, Alignment alignment) {
        JLabel label = new JLabel(text, getAlignment(alignment));
        label.setFont(new Font("Arial", Font.PLAIN, MEDIUM_TEXT_SIZE));
        return label;
    }

    /**
     * Gets a textfield
     * @param tooltip the textfield tooltip
     * @return a textfield
     */
    public static JTextField getTextField(String tooltip) {
        return getTextField(new Dimension(300, MEDIUM_PADDING * 2), tooltip);
    }
    
    /**
     * Gets a textfield
     * @param size the textfield dimensions
     * @param tooltip the textfield tooltip
     * @return 
     */
    public static JTextField getTextField(Dimension size, String tooltip) {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, SMALL_TEXT_SIZE));
        field.setPreferredSize(size);
        field.setToolTipText(tooltip);
        return field;
    }
    
    /**
     * Gets a text area
     * @param tooltip the text area tooltip
     * @return 
     */
    public static JTextArea getTextArea(String tooltip){
        JTextArea component = new JTextArea();
        component.setFont(new Font("Arial", Font.PLAIN, SMALL_TEXT_SIZE));
        component.setToolTipText(tooltip);
        component.setPreferredSize(new Dimension(300, 200));
        component.setLineWrap(true);
        return component;
    }
    
    /**
     * Gets a spinner
     * @param tooltip the spinner tooltip
     * @return a spinner
     */
    public static JSpinner getSpinner(String tooltip) {
        return getSpinner(new Dimension(300, MEDIUM_PADDING * 2), tooltip);
    }
    
    /**
     * Gets a spinner
     * @param size the spinner dimensions
     * @param tooltip the spinner tooltip
     * @return a spinner
     */
    public static JSpinner getSpinner(Dimension size, String tooltip) {
        JSpinner field = new JSpinner();
        field.setFont(new Font("Arial", Font.PLAIN, SMALL_TEXT_SIZE));
        field.setPreferredSize(size);
        field.setToolTipText(tooltip);
        return field;
    }

    /**
     * Gets a password field
     * @param tooltip the password field tooltip
     * @return a password field
     */
    public static JPasswordField getPasswordField(String tooltip) {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Arial", Font.PLAIN, SMALL_TEXT_SIZE));
        field.setPreferredSize(new Dimension(300, MEDIUM_PADDING * 2));
        field.setToolTipText(tooltip);
        return field;
    }
    
    /**
     * Gets a combobox
     * @param tooltip the combobox tooltip
     * @return a combobox
     */
    public static JComboBox getComboBox(String tooltip){
        JComboBox comboBox = new JComboBox();
        comboBox.setSize(500, 50);
        comboBox.setToolTipText(tooltip);
        return comboBox;
    }
    
    /**
     * Gets a combobox
     * @param items the combobox items
     * @param tooltip the combobox tooltip
     * @return a combobox
     */
    public static JComboBox getComboBox(Object[] items, String tooltip){
        JComboBox comboBox = new JComboBox(items);
        comboBox.setSize(500, 50);
        comboBox.setToolTipText(tooltip);
        return comboBox;
    }
    
    /**
     * Gets a primary button
     * @param text the button text
     * @param tooltip the button tolltip
     * @param bgColor the button background color
     * @param fgColor the button foreground color
     * @return a primary button
     */
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
    
    /**
     * Gets a primary button
     * @param text the button text
     * @param tooltip the button toolip
     * @return a primary button
     */
    public static JButton getPrimaryButton(String text, String tooltip){
        return getPrimaryButton(text, tooltip, ACCENT_COLOR, ON_ACCENT_COLOR);
    }
    
    /**
     * Gets a secondary button
     * @param text the button text
     * @param tooltip the button tooltip
     * @return a secondary button
     */
    public static JButton getSecondaryButton(String text, String tooltip){
        return getSecondaryButton(text, new Dimension(100, MEDIUM_PADDING * 2), tooltip);
    }
    
    /**
     * Gets a secondary button
     * @param text the button text
     * @param size the button dimensions
     * @param tooltip the button tooltip
     * @return a secondary button
     */
    public static JButton getSecondaryButton(String text, Dimension size, String tooltip){
        return getSecondaryButton(text, size, tooltip, null, null);
    }
    
    /**
     * Gets a secondary button
     * @param text the button text
     * @param size the button size
     * @param tooltip the button tooltip
     * @param bgColor the button background color
     * @param fgColor the button foreground color
     * @return a secondary button
     */
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
    
    /**
     * Gets a insets with 0 spacing on every side
     * @return Insets
     */
    public static Insets getEmptyInsets(){
        return new Insets(0, 0, 0, 0);
    }
    
    /**
     * Gets the app logo
     * @return the app logo
     */
    public static ImageIcon getLogoIcon(){
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Components.class.getResource("../assets/editora.jpeg")));
    }
}
