package frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import entity.Task;
import entity.TaskInfo;
import entity.User;

public class MainFrame {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://db4free.net:3306/ollehhh";

	static final String USER = "ollehhh";
	static final String PASS = "lhgfei111";
	private JFrame mainFrame = new JFrame();
	private JPanel mainPan = new JPanel();
	private JPanel taskManage = new JPanel();
	private JPanel userMessages = new JPanel();
	private JTabbedPane tabs = new JTabbedPane();
	private JButton editButton = new JButton("Edit");
	private Task task;
	private List<Task> taskList = new ArrayList<Task>();
	private TaskInfo taskInfo;
	private List<TaskInfo> taskInfoList = new ArrayList<TaskInfo>();

	private JComboBox taskComboBox = new JComboBox();
	private JTextArea taskInfoArea = new JTextArea(20, 60);

	public void createMainFrame(User user) {
		mainFrame.setTitle("Hello dear " + user.getFirstName() + " " + user.getSurName());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(700, 500);
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);

		mainFrame.add(tabs);
		tabs.addTab("Main Panel of Program", mainPan);
		tabs.addTab("Task manager", taskManage);
		tabs.addTab("Comunication", userMessages);

		mainPan.setLayout(new GridLayout(10, 2));
		mainPan.add(new JLabel("Login name:"));
		mainPan.add(new JLabel(user.getLogin()));
		mainPan.add(new JLabel("Name:"));
		mainPan.add(new JLabel(user.getFirstName()));
		mainPan.add(new JLabel("Surname:"));
		mainPan.add(new JLabel(user.getSurName()));
		mainPan.add(new JLabel("Email:"));
		mainPan.add(new JLabel(user.getEmail()));
		mainPan.add(new JLabel("Phone:"));
		mainPan.add(new JLabel(String.valueOf(user.getPhone())));
		mainPan.add(new JLabel("Company:"));
		mainPan.add(new JLabel(user.getCompanyName()));
		mainPan.add(new JLabel("Specialization of Company"));
		mainPan.add(new JLabel(user.getSpecialization()));
		mainPan.add(new JLabel("Manager ID"));
		mainPan.add(new JLabel(String.valueOf(user.getManager_id())));
		mainPan.add(editButton);
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Edit ed = new Edit();
				ed.createEdit(user);
				mainFrame.dispose();
			}
		});

		takeTask(user.getId());
		JPanel taskInfoPan = new JPanel();

		taskInfoArea.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(taskInfoArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JPanel managePan = new JPanel();
		JButton addTaskBut = new JButton("Add task");

		JButton selTaskBut = new JButton("Select task");
		JButton addMessageBut = new JButton("Add message");
		JTextField statusField = new JTextField();
		JButton setStatusBut = new JButton("Set status");
		statusField.setEnabled(false);
		for (int j = 0; j < taskList.size(); j++) {
			taskComboBox.addItem(taskList.get(j).getTaskName());
		}

		managePan.setLayout(new GridLayout(2, 6));
		managePan.add(addTaskBut);
		addTaskBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addTask(user.getId());

			}
		});
		managePan.add(taskComboBox);
		managePan.add(selTaskBut);
		selTaskBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectTask(String.valueOf(taskComboBox.getSelectedItem()));
			}
		});

		managePan.add(addMessageBut);
		addMessageBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < taskList.size(); i++) {
					if (taskList.get(i).getTaskName().equals(String.valueOf(taskComboBox.getSelectedItem()))) {
						addMessage(taskList.get(i).getId());
					}
				}
			}
		});
		managePan.add(statusField);
		managePan.add(setStatusBut);
		setStatusBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < taskList.size(); i++) {
					if (taskList.get(i).getTaskName().equals(String.valueOf(taskComboBox.getSelectedItem()))) {
						setStatus(taskList.get(i).getId());
					}
				}
			}
		});

		taskInfoPan.add(scroll);
		taskManage.add(taskInfoPan, BorderLayout.CENTER);
		taskManage.add(managePan, BorderLayout.SOUTH);

	}

	private void takeInfo(int ID) {
		taskInfoList.clear();
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM `TASKINFO` WHERE `TASK_ID` = " + ID + ";";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				taskInfo = new TaskInfo();
				taskInfo.setId(rs.getInt("ID"));
				taskInfo.setDate(rs.getTimestamp("DATEOFMESSAGE").toLocalDateTime());
				taskInfo.setTaskId(rs.getInt("TASK_ID"));
				taskInfo.setTaskMessage(rs.getString("TASKMESSAGE"));

				taskInfoList.add(taskInfo);
			}
			System.out.println();
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

	private void takeTask(int ID) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM `TASK` WHERE `COMPANY_ID` = " + ID + ";";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				task = new Task();
				task.setId(rs.getInt("ID"));
				task.setCompanyId(rs.getInt("COMPANY_ID"));
				task.setStartDate(rs.getTimestamp("DATESTART").toLocalDateTime());
				task.setEndDate(rs.getTimestamp("DATEEND").toLocalDateTime());
				task.setTaskName(rs.getString("TASKNAME"));
				task.setStatus(rs.getString("STATUS"));

				taskList.add(task);

			}

			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	private void addTask(int ID) {
		JFrame addTaskFrame = new JFrame("Add task");
		addTaskFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addTaskFrame.setSize(300, 200);
		addTaskFrame.setVisible(true);
		addTaskFrame.setLocationRelativeTo(null);
		addTaskFrame.setResizable(false);

		addTaskFrame.setLayout(new GridLayout(4, 2));

		JTextField taskNameFld = new JTextField();
		JTextField daysForTask = new JTextField();
		JButton addTaskBut = new JButton("Add");
		JComboBox<String> statusBox = new JComboBox<String>();
		statusBox.addItem("To do");
		statusBox.addItem("In proces");
		statusBox.addItem("Done");

		addTaskFrame.add(new JLabel("Task name:"));
		addTaskFrame.add(taskNameFld);
		addTaskFrame.add(new JLabel("Days for task:"));
		addTaskFrame.add(daysForTask);
		addTaskFrame.add(new JLabel("Status:"));
		addTaskFrame.add(statusBox);
		addTaskFrame.add(addTaskBut);

		addTaskBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				task = new Task();
				task.setCompanyId(ID);
				task.setTaskName(taskNameFld.getText());
				task.setStatus(String.valueOf(statusBox.getSelectedItem()));
				LocalDateTime lt = LocalDateTime.now();
				task.setStartDate(lt);
				task.setEndDate(lt.plusDays(Integer.parseInt(daysForTask.getText())));
				taskComboBox.addItem(task.getTaskName());
				Connection conn = null;
				Statement stmt = null;
				String sql = "INSERT INTO `TASK`( `COMPANY_ID`, `TASKNAME`, `DATESTART`, `DATEEND`, `STATUS`) VALUES ("
						+ task.getCompanyId() + ",'" + task.getTaskName() + "', '" + task.getStartDate().toString()
						+ "', '" + task.getEndDate().toString() + "', '" + task.getStatus() + "');";
				try {
					Class.forName("com.mysql.jdbc.Driver");

					conn = DriverManager.getConnection(DB_URL, USER, PASS);

					stmt = conn.createStatement();
					stmt.executeUpdate(sql);

				} catch (SQLException se) {

					se.printStackTrace();
				} catch (Exception e1) {

					e1.printStackTrace();
				} finally {

					try {
						if (stmt != null)
							stmt.close();
					} catch (SQLException se2) {
					}
					try {
						if (conn != null)
							conn.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				addTaskFrame.dispose();
			}
		});
	

	}

	private void selectTask(String taskName) {
		task = new Task();
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getTaskName().equals(taskName)) {
				task = taskList.get(i);
				break;
			}

		}
		takeInfo(task.getId());
		taskInfoArea.setText("Hello user \n\n");
		taskInfoArea.append("Date of task start:" + task.getStartDate() + "\n\n");
		taskInfoArea.append("Deadline:" + task.getEndDate() + "\n\n");
		taskInfoArea.append("Task name:" + task.getTaskName() + "\n\n");
		taskInfoArea.append("Status:" + task.getStatus() + "\n\n\n");
		for (int i = 0; i < taskInfoList.size(); i++) {
			taskInfoArea.append("Date of message:" + taskInfoList.get(i).getDate() + "\n");
			taskInfoArea.append(taskInfoList.get(i).getTaskMessage() + "\n\n");
		}

	}

	private void addMessage(int ID) {
		JFrame addMessageFrame = new JFrame("Add Message");
		addMessageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMessageFrame.setSize(300, 100);
		addMessageFrame.setVisible(true);
		addMessageFrame.setLocationRelativeTo(null);
		addMessageFrame.setResizable(false);

		addMessageFrame.setLayout(new GridLayout(2, 1));
		JTextField messageField = new JTextField();
		JButton addMessageBut = new JButton("Add");
		addMessageFrame.add(messageField);
		addMessageFrame.add(addMessageBut);
		addMessageBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				taskInfo = new TaskInfo();
				taskInfo.setTaskId(ID);
				taskInfo.setTaskMessage(messageField.getText());
				taskInfo.setDate(LocalDateTime.now());
				Connection conn = null;
				Statement stmt = null;
				String sql = "INSERT INTO `TASKINFO`(`TASK_ID`, `TASKMESSAGE`, `DATEOFMESSAGE`) VALUES ("
						+ taskInfo.getTaskId() + ", '" + taskInfo.getTaskMessage() + "', '" + taskInfo.getDate().toString()
						+ "');";
				try {
					Class.forName("com.mysql.jdbc.Driver");

					conn = DriverManager.getConnection(DB_URL, USER, PASS);

					stmt = conn.createStatement();
					stmt.executeUpdate(sql);

				} catch (SQLException se) {

					se.printStackTrace();
				} catch (Exception e1) {

					e1.printStackTrace();
				} finally {

					try {
						if (stmt != null)
							stmt.close();
					} catch (SQLException se2) {
					}
					try {
						if (conn != null)
							conn.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				addMessageFrame.dispose();
			}
		});

	}

	private void setStatus(int ID) {
		JFrame addStatusFrame = new JFrame("Add Message");
		addStatusFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addStatusFrame.setSize(300, 100);
		addStatusFrame.setVisible(true);
		addStatusFrame.setLocationRelativeTo(null);
		addStatusFrame.setResizable(false);

		addStatusFrame.setLayout(new GridLayout(2, 1));
		JButton addBut = new JButton("Add");
		JComboBox<String> statusBox = new JComboBox<String>();
		statusBox.addItem("To do");
		statusBox.addItem("In proces");
		statusBox.addItem("Done");

		addStatusFrame.add(statusBox);
		addStatusFrame.add(addBut);
		addBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				Statement stmt = null;
				String sql = "UPDATE `TASK` SET `STATUS` = '" + String.valueOf(statusBox.getSelectedItem())
						+ "' WHERE `ID` = " + ID + ";";

				try {
					Class.forName("com.mysql.jdbc.Driver");

					conn = DriverManager.getConnection(DB_URL, USER, PASS);

					stmt = conn.createStatement();
					stmt.executeUpdate(sql);

				} catch (SQLException se) {

					se.printStackTrace();
				} catch (Exception e1) {

					e1.printStackTrace();
				} finally {

					try {
						if (stmt != null)
							stmt.close();
					} catch (SQLException se2) {
					}
					try {
						if (conn != null)
							conn.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				addStatusFrame.dispose();
			}
		});

	}

}
