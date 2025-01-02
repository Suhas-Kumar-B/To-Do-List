import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TodoListGUI extends JFrame implements ActionListener {
    private JPanel taskComponentPanel;
    private JButton addTaskButton, saveTasksButton;
    private final String FILE_NAME = "tasks.json";
    private final Gson gson;
    private static final Logger logger = Logger.getLogger(TodoListGUI.class.getName());

    public TodoListGUI() {
        gson = new Gson();

        setTitle("To-Do List Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstants.GUI_SIZE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        addTitleBar();
        addTaskListPanel();
        addFooter();
        loadTasks();

        pack();
    }

    private void addTitleBar() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.decode("#4CAF50"));
        titlePanel.setPreferredSize(CommonConstants.BANNER_SIZE);

        JLabel titleLabel = new JLabel("To-Do List");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
    }

    private void addTaskListPanel() {
        taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskComponentPanel.setBackground(Color.decode("#FFFFFF"));

        JScrollPane scrollPane = new JScrollPane(taskComponentPanel);
        scrollPane.setPreferredSize(CommonConstants.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(scrollPane, BorderLayout.CENTER);
    }

    private void addFooter() {
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.decode("#F5F5F5"));
        footerPanel.setPreferredSize(CommonConstants.FOOTER_SIZE);

        // Add Task Button
        addTaskButton = new JButton("Add Task");
        addTaskButton.setPreferredSize(CommonConstants.ADDTASK_BUTTON_SIZE);
        addTaskButton.setFont(new Font("Arial", Font.PLAIN, 16));
        addTaskButton.setBackground(Color.decode("#4CAF50"));
        addTaskButton.setForeground(Color.WHITE);
        addTaskButton.setFocusPainted(false);
        addTaskButton.setToolTipText("Click to add a new task");
        addTaskButton.addActionListener(this);

        // Save Tasks Button
        saveTasksButton = new JButton("Save Tasks");
        saveTasksButton.setPreferredSize(CommonConstants.ADDTASK_BUTTON_SIZE);
        saveTasksButton.setFont(new Font("Arial", Font.PLAIN, 16));
        saveTasksButton.setBackground(Color.decode("#FF9800"));
        saveTasksButton.setForeground(Color.WHITE);
        saveTasksButton.setFocusPainted(false);
        saveTasksButton.setToolTipText("Click to save all tasks");
        saveTasksButton.addActionListener(this);

        footerPanel.add(addTaskButton);
        footerPanel.add(saveTasksButton);  // Add the Save button to the footer
        add(footerPanel, BorderLayout.SOUTH);
    }

    private void saveTasks() {
        List<TaskData> tasks = new ArrayList<>();
        for (Component component : taskComponentPanel.getComponents()) {
            if (component instanceof TaskComponent) {
                TaskComponent task = (TaskComponent) component;
                tasks.add(new TaskData(task.getTaskfield().getText(), task.getCheckBox().isSelected()));
            }
        }

        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(tasks, writer); // Serialize tasks to JSON and save it to a file
            JOptionPane.showMessageDialog(this, "Tasks successfully saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            logger.severe("Error saving tasks: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error saving tasks: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTasks() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            java.lang.reflect.Type listType = new TypeToken<List<TaskData>>() {}.getType();
            List<TaskData> tasks = gson.fromJson(reader, listType);

            if (tasks != null) {
                for (Component component : taskComponentPanel.getComponents()) {
                    if (component instanceof TaskComponent task) {
                        tasks.add(new TaskData(task.getTaskfield().getText(), task.getCheckBox().isSelected()));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // No file found, continue without tasks
        } catch (IOException e) {
            logger.severe("Error loading tasks: " + e.getMessage());
        }
    }

    @Override
    public void dispose() {
        saveTasks();  // Optional: Save tasks when the window is closed
        super.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTaskButton) {
            TaskComponent task = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(task);
            taskComponentPanel.revalidate();
            taskComponentPanel.repaint();
        }

        if (e.getSource() == saveTasksButton) {
            saveTasks();  // Save the tasks when the "Save Tasks" button is clicked
        }
    }

    private static class TaskData {
        private final String text;
        private final boolean completed;

        public TaskData(String text, boolean completed) {
            this.text = text;
            this.completed = completed;
        }

        public String getText() {
            return text;
        }

        public boolean isCompleted() {
            return completed;
        }
    }
}
