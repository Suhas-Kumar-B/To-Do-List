import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoListGUI extends JFrame implements ActionListener {
    private JPanel taskPanel, taskComponentPanel;
    public TodoListGUI(){
        super("To Do List Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstants.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        addGUIComponents();
    }
    private void addGUIComponents(){
        JLabel bannerLabel =new JLabel("To Do List");
        bannerLabel.setBounds(
                (CommonConstants.GUI_SIZE.width-bannerLabel.getPreferredSize().width)/2,
                15,
                CommonConstants.BANNER_SIZE.width,
                CommonConstants.BANNER_SIZE.height
        );
        taskPanel = new JPanel();

        taskComponentPanel =new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel,BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(8,70,CommonConstants.TASKPANEL_SIZE.width,CommonConstants.TASKPANEL_SIZE.height);
        scrollPane.setMaximumSize(CommonConstants.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setBounds(-5,CommonConstants.GUI_SIZE.height-88,CommonConstants.ADDTASK_BUTTON_SIZE.width,CommonConstants.ADDTASK_BUTTON_SIZE.height);
        addTaskButton.addActionListener(this);
        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command =e.getActionCommand();
        if(command.equalsIgnoreCase("Add Task")){
            TaskComponent taskComponent=new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);


            taskComponent.getTaskfield().requestFocus();
            repaint();
            revalidate();
        }
    }
}
