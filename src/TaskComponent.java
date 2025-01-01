import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskComponent extends JPanel implements ActionListener {
    private JCheckBox checkBox;
    private JTextPane taskfield;
    private JButton deletebutton;

    JPanel parentPanel;

    public JTextPane getTaskfield() {
        return taskfield;
    }

    public TaskComponent(JPanel parentPanel){
        this.parentPanel=parentPanel;
        taskfield = new JTextPane();
        taskfield.setPreferredSize(CommonConstants.TASKFIELD_SIZE);
        taskfield.setContentType("text/html");

        deletebutton=new JButton("X");
        deletebutton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE);
        deletebutton.addActionListener(this);
        checkBox=new JCheckBox();
        checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);
        checkBox.addActionListener(this);
        add(checkBox);
        add(taskfield);
        add(deletebutton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkBox.isSelected()){
            String taskText = taskfield.getText().replaceAll("<[^>]*>","");

            taskfield.setText("<html><s>"+taskText+"</s></html>");
        } else if (!checkBox.isSelected()) {
            String taskText = taskfield.getText().replaceAll("<[^>]*>","");

            taskfield.setText(taskText);

        }

        if(e.getActionCommand().equalsIgnoreCase("X")){
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
        }
    }
}
