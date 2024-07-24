import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

 class Student {
    private String name;
    private int rollNumber;
    private int grade;

    public Student(String n, int roll, int g) {
        name = n;
        if (roll <= 0) {
            throw new IllegalArgumentException("Roll number must be greater than 0.");
        }
        if (g < 1 || g > 12) {
            throw new IllegalArgumentException("Grade must be between 1 and 12.");
        }
        rollNumber = roll;
        grade = g;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public int getGrade() {
        return grade;
    }

    
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

 class StudentManagementSystem {
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(s -> s.getRollNumber()==(rollNumber));
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }
}

public class StudentManagementSystemGUI {
    private StudentManagementSystem sms;
    private JFrame frame;
    private JTextField nameField, rollNumberField, gradeField;
    private JTextArea displayArea;

    public StudentManagementSystemGUI() {
        sms = new StudentManagementSystem();
        frame = new JFrame("Student Management System");

       
        JLabel nameLabel = new JLabel("Name:");
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        JLabel gradeLabel = new JLabel("Grade:");
        nameField = new JTextField(20);
        rollNumberField = new JTextField(20);
        gradeField = new JTextField(20);
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton searchButton = new JButton("Search Student");
        JButton displayButton = new JButton("Display All Students");
        displayArea = new JTextArea(25, 30);
        displayArea.setEditable(false);

        frame.setLayout(new FlowLayout());
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(rollNumberLabel);
        frame.add(rollNumberField);
        frame.add(gradeLabel);
        frame.add(gradeField);
        frame.add(addButton);
        frame.add(removeButton);
        frame.add(searchButton);
        frame.add(displayButton);
        frame.add(new JScrollPane(displayArea));

      
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });

       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        int rollNumber;
        int grade;

        try {
            rollNumber = Integer.parseInt(rollNumberField.getText().trim());
            grade = Integer.parseInt(gradeField.getText().trim());

            if (name.isEmpty() || rollNumber <= 0 || grade < 1 || grade > 12) {
                JOptionPane.showMessageDialog(frame, "All fields are required, roll number must be > 0, and grade must be between 1 and 12!");
                return;
            }

            Student student = new Student(name, rollNumber, grade);
            sms.addStudent(student);
            JOptionPane.showMessageDialog(frame, "Student added successfully!");
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Roll number and grade must be valid integers!");
        }
    }

    private void removeStudent() {
        try {
            int rollNumber = Integer.parseInt(rollNumberField.getText().trim());
            sms.removeStudent(rollNumber);
            JOptionPane.showMessageDialog(frame, "Student removed successfully!");
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Roll number must be a valid integer!");
        }
    }

    private void searchStudent() {
        try {
            int rollNumber = Integer.parseInt(rollNumberField.getText().trim());
            Student student = sms.searchStudent(rollNumber);
            if (student != null) {
                displayArea.setText(student.toString());
            } else {
                JOptionPane.showMessageDialog(frame, "Student not found!");
            }
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Roll number must be a valid integer!");
        }
    }

    private void displayAllStudents() {
        StringBuilder builder = new StringBuilder();
        for (Student student : sms.getAllStudents()) {
            builder.append(student).append("\n");
        }
        displayArea.setText(builder.toString());
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
    }

    public static void main(String[] args) {
        new StudentManagementSystemGUI();
    }
}
