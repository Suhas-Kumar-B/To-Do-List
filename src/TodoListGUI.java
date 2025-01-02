import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoListGUI extends JFrame implements ActionListener {
    private JPanel taskPanel, taskComponentPanel;
    private JButton addTaskButton;

    public TodoListGUI() {
        super("To-Do List Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstants.GUI_SIZE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        // Set modern look
        setBackground(Color.decode("#F5F5F5"));

        // Add components
        addTitleBar();
        addTaskListPanel();
        addFooter();

        pack();
    }

    private void addTitleBar() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.decode("#4CAF50")); // Green
        titlePanel.setPreferredSize(CommonConstants.BANNER_SIZE);

        JLabel titleLabel = new JLabel("To-Do List");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
    }

    private void addTaskListPanel() {
        taskPanel = new JPanel();
        taskPanel.setLayout(new BorderLayout());

        taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskComponentPanel.setBackground(Color.decode("#FFFFFF"));

        JScrollPane scrollPane = new JScrollPane(taskComponentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        taskPanel.add(scrollPane, BorderLayout.CENTER);
        add(taskPanel, BorderLayout.CENTER);
    }

    private void addFooter() {
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.decode("#F5F5F5"));
        footerPanel.setPreferredSize(new Dimension(500, 60));

        addTaskButton = new JButton("Add Task");
        addTaskButton.setPreferredSize(CommonConstants.ADDTASK_BUTTON_SIZE);
        addTaskButton.setFont(new Font("Arial", Font.PLAIN, 16));
        addTaskButton.setBackground(Color.decode("#4CAF50"));
        addTaskButton.setForeground(Color.WHITE);
        addTaskButton.setFocusPainted(false);
        addTaskButton.setBorder(BorderFactory.createLineBorder(Color.decode("#388E3C")));
        addTaskButton.setToolTipText("Click to add a new task");
        addTaskButton.addActionListener(this);

        footerPanel.add(addTaskButton);
        add(footerPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTaskButton) {
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            taskComponent.getTaskfield().requestFocus();
            taskComponentPanel.repaint();
            taskComponentPanel.revalidate();
        }
    }
}
