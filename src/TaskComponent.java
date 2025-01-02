import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.*;

public class TaskComponent extends JPanel implements ActionListener {
    private final JCheckBox checkBox;
    private final JTextPane taskField;
    private final JButton deleteButton;
    private final JPanel parentPanel;
    private final StyledDocument doc;

    public TaskComponent(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.decode("#FFFFFF"));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        setMaximumSize(CommonConstants.TASKPANEL_SIZE);

        checkBox = new JCheckBox();
        checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);
        checkBox.addActionListener(this);

        taskField = new JTextPane();
        taskField.setPreferredSize(CommonConstants.TASKFIELD_SIZE);
        taskField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        taskField.setBackground(Color.decode("#F8F8F8"));

        doc = taskField.getStyledDocument();

        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE);
        deleteButton.setBackground(Color.decode("#F44336"));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 18));
        deleteButton.setFocusPainted(false);
        deleteButton.setToolTipText("Delete task");
        deleteButton.addActionListener(this);

        add(checkBox);
        add(taskField);
        add(deleteButton);
    }

    // Getter for taskField to allow access from TodoListGUI
    public JTextPane getTaskfield() {
        return taskField;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    // Method to set task and apply strikethrough if the task is completed
    public void setTask(String text, boolean completed) {
        taskField.setText(text);
        checkBox.setSelected(completed);

        if (completed) {
            // Apply strikethrough using StyledDocument
            SimpleAttributeSet strikeThrough = new SimpleAttributeSet();
            StyleConstants.setStrikeThrough(strikeThrough, true);
            doc.setCharacterAttributes(0, text.length(), strikeThrough, false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkBox) {
            String taskText = taskField.getText();
            SimpleAttributeSet attributeSet = new SimpleAttributeSet();
            StyleConstants.setStrikeThrough(attributeSet, checkBox.isSelected());
            doc.setCharacterAttributes(0, taskText.length(), attributeSet, false);
        } else if (e.getSource() == deleteButton) {
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
        }
    }
}
