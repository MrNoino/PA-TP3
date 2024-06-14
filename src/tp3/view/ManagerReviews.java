package tp3.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ManagerReviews extends JFrame{
    private JFrame frame;
    private JTabbedPane reviewsTabbedPanel;
    
    public ManagerReviews(JFrame frame, JTabbedPane reviewsTabbedPanel){
        this.frame = frame;
        this.reviewsTabbedPanel = reviewsTabbedPanel;
        
        JPanel viewReviewsPanel = new JPanel(), 
                updateReviewsPanel = new JPanel();
        
        viewReviewsPanel.add(Components.getLabel("Funcionalidade não implementada"));
        updateReviewsPanel.add(Components.getLabel("Funcionalidade não implementada"));
        
        this.reviewsTabbedPanel.add("Visualizar", viewReviewsPanel);
        this.reviewsTabbedPanel.add("Atualizar", updateReviewsPanel);
    }
}
