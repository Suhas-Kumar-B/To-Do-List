import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.*;

public class TaskComponent extends JPanel implements ActionListener {
    private JCheckBox checkBox;
    private JTextPane taskField;
    private JButton deleteButton;
    private JPanel parentPanel;
    private StyledDocument doc;

    public TaskComponent(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        // Set layout and appearance for the task panel
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.decode("#FFFFFF"));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        setMaximumSize(new Dimension(450, 50)); // Maximum size for task components

        // Initialize and configure the checkbox
        checkBox = new JCheckBox();
        checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);
        checkBox.addActionListener(this);

        // Initialize and configure the JTextPane for task description
        taskField = new JTextPane();
        taskField.setPreferredSize(CommonConstants.TASKFIELD_SIZE);
        taskField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        taskField.setBackground(Color.decode("#F8F8F8")); // Light background for better readability

        // Use StyledDocument to allow text formatting
        doc = taskField.getStyledDocument();

        // Initialize and configure the delete button
        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE);
        deleteButton.setBackground(Color.decode("#F44336")); // Red color for delete button
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Ensure font supports the "X" symbol
        deleteButton.setFocusPainted(false); // Remove focus painting for a cleaner look
        deleteButton.setToolTipText("Delete task"); // Add a tooltip for better UX
        deleteButton.addActionListener(this);

        // Add components to the panel
        add(checkBox);
        add(taskField);
        add(deleteButton);
    }

    // Getter for taskField to allow access from TodoListGUI
    public JTextPane getTaskfield() {
        return taskField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle checkbox selection (strikethrough task text)
        if (e.getSource() == checkBox) {
            String taskText = taskField.getText();
            if (checkBox.isSelected()) {
                // Apply strikethrough using StyledDocument
                SimpleAttributeSet strikeThrough = new SimpleAttributeSet();
                StyleConstants.setStrikeThrough(strikeThrough, true);
                doc.setCharacterAttributes(0, taskText.length(), strikeThrough, false);
            } else {
                // Remove strikethrough
                SimpleAttributeSet normal = new SimpleAttributeSet();
                StyleConstants.setStrikeThrough(normal, false);
                doc.setCharacterAttributes(0, taskText.length(), normal, false);
            }
        }
        // Handle delete button action (remove task component from parent panel)
        else if (e.getSource() == deleteButton) {
            parentPanel.remove(this); // Remove the current task component
            parentPanel.repaint(); // Repaint the parent panel to reflect the changes
            parentPanel.revalidate(); // Revalidate layout of the parent panel
        }
    }
}
